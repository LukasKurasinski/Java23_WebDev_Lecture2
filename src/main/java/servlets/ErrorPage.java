package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="ErrorPage", urlPatterns = "/error")
public class ErrorPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String website="<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            background-color: #f2f2f2;\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            text-align: center;\n" +
                "            padding: 50px;\n" +
                "        }\n" +
                "\n" +
                "        .error-container {\n" +
                "            background-color: #fff;\n" +
                "            max-width: 500px;\n" +
                "            margin: 0 auto;\n" +
                "            padding: 20px;\n" +
                "            border-radius: 4px;\n" +
                "            box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);\n" +
                "        }\n" +
                "\n" +
                "        h1 {\n" +
                "            color: #ff0000;\n" +
                "            font-size: 48px;\n" +
                "        }\n" +
                "\n" +
                "        p {\n" +
                "            color: #666;\n" +
                "            font-size: 16px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"error-container\">\n" +
                "        <h1>Error 404</h1>\n" +
                "        <p>Sorry, the page you are looking for does not exist.</p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";

                out.println(website);
    }
}
