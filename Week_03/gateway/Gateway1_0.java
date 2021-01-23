package gateway;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.concurrent.*;

public class Gateway1_0 extends ChannelInboundHandlerAdapter {
    private ExecutorService proxyService;

    public Gateway1_0() {
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

        String url = "http://localhost:8801" + fullHttpRequest.uri();

        proxyService.execute(() -> {
        });
    }
}
