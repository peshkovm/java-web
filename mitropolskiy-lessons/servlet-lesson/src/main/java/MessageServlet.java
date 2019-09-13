import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Message Servlet", urlPatterns = {"/MessageServlet"},
        initParams = {@WebInitParam(name = "message", value = "Hi!")})
public class MessageServlet extends HttpServlet {

    public static final String MESSAGE = "message";

    private String message;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        message = config.getInitParameter(MESSAGE);
    }

/*    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        //writer.write(message);
        resp.addCookie(new Cookie("furry", "kihu"));
        writer.write("Kihu dog");
        writer.close();
    }*/

/*    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }*/
}
