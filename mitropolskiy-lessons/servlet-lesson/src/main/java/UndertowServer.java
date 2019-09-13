import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ServletContainer;


public class UndertowServer {
    public static void main(String[] args) throws Exception {
        DeploymentInfo servletBuilder = Servlets.deployment()
                .setClassLoader(UndertowServer.class.getClassLoader())
                .setContextPath("/UndertowServer")
                .setDeploymentName("test.war")
                .addServlets(
                        Servlets.servlet("MessageServlet", MessageServlet.class)
                                .addInitParam("message", "Hello World")
                                .addMapping("/*"),
                        Servlets.servlet("MyServlet", MessageServlet.class)
                                .addInitParam("message", "MyServlet")
                                .addMapping("/myservlet"));

        ServletContainer container = Servlets.defaultContainer();
        DeploymentManager manager = container.addDeployment(servletBuilder);
        manager.deploy();

        HttpHandler servletHandler = manager.start();
        PathHandler path = Handlers.path(/*Handlers.redirect(servletBuilder.getContextPath())*/)
                .addPrefixPath(servletBuilder.getContextPath(), servletHandler);
        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(path)
                .build();
        server.start();
    }
}