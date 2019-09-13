package questionerRefined;

import freemarker.template.Template;
import io.undertow.Handlers;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.form.EagerFormParsingHandler;
import io.undertow.server.handlers.form.FormData;
import io.undertow.server.handlers.form.FormDataParser;
import io.undertow.server.session.Session;
import io.undertow.server.session.SessionConfig;
import io.undertow.server.session.SessionManager;
import questionerRefined.beans.Question;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class MainPostHandler implements HttpHandler {
    private final List<Question> questions;
    private final Map<String, List<Boolean>> rightAnswers;
    private final EagerFormParsingHandler inner = new EagerFormParsingHandler();

    public MainPostHandler(List<Question> questions, Map<String, List<Boolean>> rightAnswers) {
        this.questions = questions;
        this.rightAnswers = rightAnswers;
        inner.setNext(this::handle);
    }

    public void handle(HttpServerExchange exchange) throws Exception {
        final FormData attachment = exchange.getAttachment(FormDataParser.FORM_DATA);
        int numOfQ = Integer.parseInt(attachment.getFirst("numOfQ").getValue());
        int answer = Integer.parseInt(attachment.getFirst("answer").getValue());

        SessionManager sessionManager = exchange.getAttachment(SessionManager.ATTACHMENT_KEY);
        SessionConfig sessionConfig = exchange.getAttachment(SessionConfig.ATTACHMENT_KEY);

        Session session = sessionManager.getSession(exchange, sessionConfig);
        if (session == null)
            session = sessionManager.createSession(exchange, sessionConfig);

        String name = (String) session.getAttribute("name");
        String username = (String) session.getAttribute("username");

        Question question = questions.get(numOfQ);

        rightAnswers.putIfAbsent(name, new ArrayList<>());

        rightAnswers.get(name).add(question.getRight() == answer);

        if (numOfQ + 1 >= questions.size()) {
            Handlers.redirect("results").handleRequest(exchange);
            return;
        }

        Template questionTemplate = TemplateLoader.getTemplate("questionerRefined/templates", "question.html");

        StringWriter stringWriter = new StringWriter();
        questionTemplate.process(questions.get(numOfQ + 1), stringWriter);

        exchange.getResponseSender().send(stringWriter.toString());
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        inner.handleRequest(exchange);
    }
}
