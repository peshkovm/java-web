package questionerRefined;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.RoutingHandler;
import io.undertow.server.session.InMemorySessionManager;
import io.undertow.server.session.SessionAttachmentHandler;
import io.undertow.server.session.SessionCookieConfig;
import io.undertow.server.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Component
public class WebServer {

    @Autowired
    List<MappedHandler> handlers;

    Undertow server;

    @PostConstruct
    void startServer() {
        final RoutingHandler routingHandler = Handlers.routing();

        for (MappedHandler handler : handlers) {
            if (handler.getOrPost().equals("get"))
                routingHandler.get(handler.path(), handler);
            else if (handler.getOrPost().equals("post"))
                routingHandler.post(handler.path(), handler);
        }

        SessionManager sessionManager = new InMemorySessionManager(
                "SESSION_MANAGER");
        SessionCookieConfig sessionConfig = new SessionCookieConfig();

        SessionAttachmentHandler sessionAttachmentHandler = new SessionAttachmentHandler(
                sessionManager, sessionConfig);

        sessionAttachmentHandler.setNext(routingHandler);

        server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(sessionAttachmentHandler).build();
        server.start();
        System.out.println("Server started");
    }

    @PreDestroy
    void stop() {
        server.stop();
    }

}