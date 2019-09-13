package questionerRefined;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.RoutingHandler;
import io.undertow.server.handlers.form.EagerFormParsingHandler;
import io.undertow.server.session.*;
import questionerRefined.beans.Question;
import questionerRefined.beans.QuestionBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        final Map<String, List<Boolean>> rightAnswers = new HashMap<>();
        final List<Question> questions = QuestionBuilder.getQuestions();

        RoutingHandler routingHandler = Handlers.routing();

        //routingHandler.get("auth", new ResourceHandler(new FileResourceManager(new File("questionerRefined/templates"))).setWelcomeFiles("authorization.html"));

        routingHandler.get("/auth", new AuthHandler());
        routingHandler.post("setSession", new EagerFormParsingHandler().setNext(new SetSessionHandler()));
        routingHandler.get("/", new MainGetHandler(questions));
        routingHandler.post("/", new MainPostHandler(questions, rightAnswers));
        routingHandler.get("results", new ResultsHandler(rightAnswers));

        SessionManager sessionManager = new InMemorySessionManager(
                "SESSION_MANAGER");
        SessionCookieConfig sessionConfig = new SessionCookieConfig();

        SessionAttachmentHandler sessionAttachmentHandler = new SessionAttachmentHandler(
                sessionManager, sessionConfig);

        sessionAttachmentHandler.setNext(routingHandler);

        Undertow server = Undertow.builder()
                .addHttpListener(5555, "192.168.1.51")
                .setHandler(exchange -> {
                    System.out.println(exchange.getHostAndPort());
                })
                .setHandler(sessionAttachmentHandler).build();
        server.start();
    }

}