package questionerRefined;

import freemarker.template.Template;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import questionerRefined.beans.Question;

import java.io.StringWriter;
import java.util.List;

class MainGetHandler implements HttpHandler {
    private final List<Question> questions;

    public MainGetHandler(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        Template questionTemplate = TemplateLoader.getTemplate("questionerRefined/templates", "question.html");

        StringWriter stringWriter = new StringWriter();
        questionTemplate.process(questions.get(0), stringWriter);

        exchange.getResponseSender().send(stringWriter.toString());
    }
}
