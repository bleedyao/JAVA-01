package filter;

import io.netty.handler.codec.http.FullHttpResponse;

public class GatewayResponseFilter implements HttpResponseFilter{
    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("response_filter", "gateway_response_filter");
    }
}
