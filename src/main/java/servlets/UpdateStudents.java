package servlets;

import models.MySQLConnector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;

@WebServlet(name="UpdateStudents", urlPatterns = "/updatestudents")
public class UpdateStudents extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showDataTable(req, resp, MySQLConnector.getConnector().selectQuery("allFromStudenter"));
        showForm(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        MySQLConnector.getConnector().insertQuery("addStudentToStudents", req.getParameter("fname"), req.getParameter("lname"), req.getParameter("town"), req.getParameter("hobby"),"S","S","S","S");
        showDataTable(req, resp, MySQLConnector.getConnector().selectQuery("allFromStudenter"));
        showForm(req, resp);
    }


    /**
     * creates a HTMl table from Db data. Data is sent to this method via parameter list
     * every String array in the data list represents one Db data row
     * @param req
     * @param resp
     * @param data LinkedList<String[]>
     * @throws ServletException
     * @throws IOException
     */
    private void showDataTable(HttpServletRequest req, HttpServletResponse resp, LinkedList<String[]> data) throws ServletException, IOException {


        String top = "<head><title>Hello " + req.getParameter("name") + "</title>"
                + "        <style>\n" +
                "                        a {\n" +
                "                        margin:5px;\n" +
                "                        }\n" +
                "                        nav {\n" +
                "                        display:block;\n" +
                "                        margin-left:auto;\n" +
                "                        margin-right:auto;\n" +
                "                        width:500px\n" +
                "                        }\n" +
                "        </style>"
                + "</head>"
                + "<body>"
                + "<nav>"
                +   "<a href=/>HOME</a>"
                +   "<a href=/personchooser>Show Person Classes</a>"
                +   "<a href=/home>Servlet Redirect</a>"
                +   "<a href=/updatestudents>Update Students</a>"
                + "</nav>"
                + "<h2>Add a student to the Db</h2>"
                + "<table style='margin-left: auto; margin-right: auto; padding:20px;'>";

        String bottom = "</table>"
               + "</body>"
                + "</html>";

        resp.setContentType("text/HTML");
        PrintWriter out = resp.getWriter();
        out.println(top);

        data.forEach(row -> {
            out.println("<tr>");
            Arrays.stream(row).forEach(dataPoint -> {
                out.println("<td style='border: 1px solid black; background-color: #96D4D4;'>" + dataPoint + "</td>");
            });
            out.println("</tr>");
        });

        out.println(bottom);
    }

    /**
     * Creates a HTML POST form for input: first name, and last name of a student in the Db
     * This input data is sent to the /updatestudents. from there all of the courser for a given student are shown
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void showForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println(
                "<br>"
                        + "<div style='border:black solid; width:200px; padding:5px display:block; margin-left:auto; margin-right:auto; margin-top:5px; margin-bottom:5px;'>"
                        + "<form style='margin:5px;' action=/updatestudents method=POST>"
                        + "            <label for=fname>First Name:</label>"
                        + "            <input type=text id=fname name=fname required><br><br>"
                        + "             <label for=fname>First Name:</label>"
                        + "            <input type=text id=lname name=lname required><br><br>"
                        + "             <label for=town>Town:</label>"
                        + "            <input type=text id=town name=town><br><br>"
                        + "             <label for=hobby>Hobby:</label>"
                        + "            <input type=text id=hobby name=hobby><br><br>"
                        + "            <input type=submit value=Submit>"
                        + "        </form>"
                        + "</div>"
                        + "<br>"
        );
    }
}
