package gateway;

import filter.GatewayRequestFilter;
import filter.GatewayResponseFilter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import okhttp3.*;
import router.HttpEndpointRouter;
import router.HttpRouter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class Gateway extends ChannelInboundHandlerAdapter {
    private ExecutorService proxyService;

    public Gateway() {
        int cores = Runtime.getRuntime().availableProcessors();
        long keepAliveTime = 1000;
        int queueSize = 2048;
        proxyService = new ThreadPoolExecutor(cores, cores,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
        System.out.println(fullHttpRequest.uri());

        // gateway 2.0: update the request filter in this gateway
        new GatewayRequestFilter().filter(fullHttpRequest, ctx);

        // gateway 3.0: update the router in this gateway
        List<String> urls = new ArrayList<>();
        urls.add("http://localhost:8081");
        urls.add("http://localhost:8082");
        String url = new HttpRouter().route(urls);

        proxyService.execute(() -> {
            OkHttpClient httpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("request_filter", fullHttpRequest.headers().get("request_filter"))
                    .build();

            FullHttpResponse fullHttpResponse = null;
            try {
                Response resp = httpClient.newCall(request).execute();
                Headers requestHeads = resp.request().headers();
                for (int i = 0; i < requestHeads.size(); i++) {
                    System.out.println(requestHeads.name(i) + ": "+ requestHeads.value(i));
                }

                String value = resp.body().string();

                fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
                fullHttpResponse.headers().set("Content-Type", "application/json");
                fullHttpResponse.headers().setInt("Content-Length", fullHttpResponse.content().readableBytes());

                // Gateway 2.0: update the response filter in this gateway
                new GatewayResponseFilter().filter(fullHttpResponse);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("处理出错:" + e.getMessage());
                fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            } finally {
                if (!HttpUtil.isKeepAlive(fullHttpRequest)) {
                    ctx.write(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
                } else {
                    fullHttpResponse.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(fullHttpResponse);
                }
                ctx.flush();
            }
        });
    }
}
