package questioner;

import freemarker.template.Template;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.Cookie;
import io.undertow.server.handlers.form.EagerFormParsingHandler;
import io.undertow.server.handlers.form.FormData;
import io.undertow.server.handlers.form.FormDataParser;
import questioner.beans.Question;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static int q = 0;

    public static void main(String[] args) {
        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(Handlers.path()
                        .addExactPath("/", exchange -> {
                            Template questionTemplate = TemplateLoader.getTemplate("questioner/templates", "question.html");

                            List<Question> questions = Arrays.asList(
                                    new Question("2 + 2", Arrays.asList("2", "5", "4"), 2)
                                    , new Question("3 + 3", Arrays.asList("3", "15", "6"), 2)
                                    , new Question("4 + 4", Arrays.asList("4", "8", "8"), 2)
                                    , new Question("5 + 5", Arrays.asList("5", "10", "10"), 2));

                            for (int i = 0; i < questions.size(); i++) {
                                questions.get(i).setNum(i);
                            }

                            StringWriter stringWriter = new StringWriter();
                            questionTemplate.process(questions.get(q), stringWriter);

                            exchange.getResponseSender().send(stringWriter.toString());
                        })
                        .addExactPath("/answer", new EagerFormParsingHandler()
                                .setNext(exchange -> {
                                    FormData attachment = exchange.getAttachment(FormDataParser.FORM_DATA);
                                    int q = Integer.parseInt(attachment.get("q").getFirst().getValue());
                                    int answer = Integer.parseInt(attachment.get("answer").getFirst().getValue());

                                    System.out.println("q = " + q);
                                    System.out.println("answer = " + answer);

                                    Main.q++;
                                    Handlers.redirect("/").handleRequest(exchange);
                                }))
                        .addExactPath("/results", exchange -> {
                            FormData attachment = exchange.getAttachment(FormDataParser.FORM_DATA);
                            int q = Integer.parseInt(attachment.get("q").getFirst().getValue());
                            int answer = Integer.parseInt(attachment.get("answer").getFirst().getValue());
                            Map<String, Integer> resultMap = new HashMap<>();
                            resultMap.put("q", q);
                            resultMap.put("answer", answer);

                            Template answerTemplate = TemplateLoader.getTemplate("questioner/templates", "answer.html");
                            StringWriter writer = new StringWriter();

                            answerTemplate.process(resultMap, writer);

                            exchange.getResponseSender().send(writer.toString());
                        })
                ).build();
        server.start();
    }
}