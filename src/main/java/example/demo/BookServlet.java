package example.demo;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(urlPatterns = {"/q","/qq","/qqq"})
public class BookServlet extends HttpServlet {
    private String message;

    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> names = req.getHeaderNames();
        resp.setContentType("text/html");

        // Hello
        PrintWriter out = resp.getWriter();

        while (names.hasMoreElements()){
            String element = names.nextElement();
            String header = req.getHeader(element);
            out.println("<html><body>");
            out.println("<p>" + element + ":"  +  header + "</p>");
            out.println("</body></html>");
        }

        req.getRequestDispatcher("/hello-servlet").forward(req,resp);
    }

    public void destroy() {
    }
}
