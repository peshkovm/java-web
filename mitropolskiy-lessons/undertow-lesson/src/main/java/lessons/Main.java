package lessons;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.form.EagerFormParsingHandler;
import io.undertow.server.handlers.form.FormData;
import io.undertow.server.handlers.form.FormDataParser;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.server.handlers.resource.ResourceHandler;
import io.undertow.util.Headers;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setClassLoaderForTemplateLoading(Main.class.getClassLoader(), "lessons/templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);

        final Template nameTemplate = cfg.getTemplate("name.html");

        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(Handlers.path()
                        .addExactPath("form", new EagerFormParsingHandler().setNext((new HttpHandler() {
                            @Override
                            public void handleRequest(HttpServerExchange exchange) throws Exception {
                                FormData form = exchange.getAttachment(FormDataParser.FORM_DATA);
                                FormData.FormValue firstFv = form.getFirst("name");
                                String name = firstFv.getValue();

                                exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");

                                Map<String, Object> templateData = new HashMap<>();
                                templateData.put("name", name);
                                StringWriter stringWriter = new StringWriter();
                                nameTemplate.process(templateData, stringWriter);

                                exchange.getResponseSender().send(stringWriter.toString());
                            }
                        })))
                        .addPrefixPath("/", new ResourceHandler(new ClassPathResourceManager(Main.class.getClassLoader(), "lessons/html")))
                ).build();
        server.start();
    }
}