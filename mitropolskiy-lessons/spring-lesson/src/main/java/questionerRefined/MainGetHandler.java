package questionerRefined;

import freemarker.template.Template;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import questionerRefined.beans.Question;

import java.io.StringWriter;
import java.util.List;

@Component
class MainGetHandler implements MappedHandler {
    private final List<Question> questions;

    @Autowired
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

    @Override
    public String path() {
        return "/";
    }

    @Override
    public String getOrPost() {
        return "get";
    }
}
