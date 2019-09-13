package questionerRefined;

import io.undertow.Handlers;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.form.EagerFormParsingHandler;
import io.undertow.server.handlers.form.FormData;
import io.undertow.server.handlers.form.FormDataParser;
import io.undertow.server.session.Session;
import io.undertow.server.session.SessionConfig;
import io.undertow.server.session.SessionManager;
import org.springframework.stereotype.Component;

@Component
class SetSessionHandler implements MappedHandler {
    private final EagerFormParsingHandler inner = new EagerFormParsingHandler();

    SetSessionHandler() {
        inner.setNext(this::handle);
    }


    public void handle(HttpServerExchange exchange) throws Exception {
        final FormData attachment = exchange.getAttachment(FormDataParser.FORM_DATA);
        final String name = attachment.getFirst("name").getValue();
        final String surname = attachment.getFirst("surname").getValue();

        SessionManager sessionManager = exchange.getAttachment(SessionManager.ATTACHMENT_KEY);
        SessionConfig sessionConfig = exchange.getAttachment(SessionConfig.ATTACHMENT_KEY);

        Session session = sessionManager.getSession(exchange, sessionConfig);
        if (session == null)
            session = sessionManager.createSession(exchange, sessionConfig);

        session.setAttribute("name", name);
        session.setAttribute("surname", surname);

        Handlers.redirect("/").handleRequest(exchange);
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        inner.handleRequest(exchange);
    }

    @Override
    public String path() {
        return "setSession";
    }

    @Override
    public String getOrPost() {
        return "post";
    }
}
