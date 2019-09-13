package questionerRefined;

import io.undertow.server.HttpHandler;

public interface MappedHandler extends HttpHandler {
    String path();
    String getOrPost();
}