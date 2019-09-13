package questionerRefined;

import freemarker.template.Template;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.session.Session;
import io.undertow.server.session.SessionConfig;
import io.undertow.server.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
class ResultsHandler implements MappedHandler {
    private final Map<String, List<Boolean>> rightAnswers;

    @Autowired
    public ResultsHandler(Map<String, List<Boolean>> rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        Template resultsTemplate = TemplateLoader.getTemplate("questionerRefined/templates", "results.html");

        SessionManager sessionManager = exchange.getAttachment(SessionManager.ATTACHMENT_KEY);
        SessionConfig sessionConfig = exchange.getAttachment(SessionConfig.ATTACHMENT_KEY);

        Session session = sessionManager.getSession(exchange, sessionConfig);
        if (session == null)
            session = sessionManager.createSession(exchange, sessionConfig);

        String name = (String) session.getAttribute("name");
        String username = (String) session.getAttribute("username");

        Map<String, Integer> resultsMap = new HashMap<>();
        int numCorrect = 0;
        for (boolean answer : rightAnswers.get(name))
            if (answer)
                numCorrect++;

        resultsMap.put("numCorrect", numCorrect);
        StringWriter stringWriter = new StringWriter();
        resultsTemplate.process(resultsMap, stringWriter);

        exchange.getResponseSender().send(stringWriter.toString());
    }

    @Override
    public String path() {
        return "results";
    }

    @Override
    public String getOrPost() {
        return "get";
    }
}
