package questionerRefined;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import org.springframework.stereotype.Component;

@Component
class AuthHandler implements MappedHandler {
    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        System.out.println(exchange.getHostAndPort());

        StringBuilder sb = new StringBuilder();
        sb.append("<form action='setSession' method='post'>");
        sb.append("<label>name</label>");
        sb.append("<input name='name' />");
        sb.append("<label>surname</label>");
        sb.append("<input name='surname' />");
        sb.append("<button>Авторизация</button>");
        sb.append("</form>");

        exchange.getResponseHeaders().add(Headers.CONTENT_TYPE, "text/html;");
        exchange.getResponseSender().send(sb.toString());
    }

    @Override
    public String path() {
        return "auth";
    }

    @Override
    public String getOrPost() {
        return "get";
    }
}
