package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="homeservlet", urlPatterns = {"/home","/hus"})
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String top = "<head><title>Hello " + req.getParameter("name") +  "</title></head>"
                + "<body>"
                + "<nav>"
                +   "<a href=/>HOME</a>"
                +   "<a href=/personchooser>Show Person Classes</a>"
                +   "<a href=/home>Servlet Redirect</a>"
                + "</nav>"
                + "<h2>Hello from Java Servlet!</h2>";

        String bottom =
                "</body>"
                        + "</html>";
        PrintWriter out = resp.getWriter();
        out.println(top);
        out.println(bottom);
   }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println("redirecting");
        //getServletContext().getRequestDispatcher("/dbconnect").forward(req, resp);
        String top = "<head><title>Hello " + req.getParameter("name") +  "</title></head>"
                + "<body>"
                + "<nav>"
                +   "<a href=/>HOME</a>"
                +   "<a href=/personchooser>Show Person Classes</a>"
                +   "<a href=/home>Servlet Redirect</a>"
                + "</nav>"
                + "<h2>Hello from Java Servlet!</h2>";

        String bottom =
                "</body>"
                + "</html>";
        PrintWriter out = resp.getWriter();
        out.println(top);
        out.println(bottom);
    }
}
