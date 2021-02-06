package router;

import java.util.List;

public class HttpRouter implements HttpEndpointRouter {
    @Override
    public String route(List<String> endpoints) {
        int index = (int) System.currentTimeMillis() % endpoints.size();
        return endpoints.get(index);
    }
}
