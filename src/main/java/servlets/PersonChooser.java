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

@WebServlet(name="PersonChooser", urlPatterns= "/personchooser")
public class PersonChooser extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //this part is to check if optional id is given and if its a number
        if(!req.getParameter("id").isEmpty()){
            try {
                showDataTable(req, resp, MySQLConnector.getConnector().selectQuery("allCoursesForStudent", req.getParameter("fname"), req.getParameter("lname"), req.getParameter("id")));
            }catch (NumberFormatException e){
            }
        }else {
            showDataTable(req, resp, MySQLConnector.getConnector().selectQuery("allCoursesForStudentNoID", req.getParameter("fname"), req.getParameter("lname")));
        }

            showForm(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
    private void showDataTable(HttpServletRequest req, HttpServletResponse resp,LinkedList<String[]> data) throws ServletException, IOException {


        String top = "<head><title>Hello " + req.getParameter("name") +  "</title></head>"
                + "<body>"
                + "<nav>"
                +   "<a href=/>HOME</a>"
                +   "<a href=/personchooser>Show Person Classes</a>"
                +   "<a href=/home>Servlet Redirect</a>"
                + "</nav>"
                + "<h2>Hello from Java Servlet!</h2>"
                + "<table style='margin-left: auto; margin-right: auto; padding:20px;'>";

        String bottom = "</table>"
                + "<button style='display:block; margin-left:auto; margin-right:auto; margin-top:5px; margin-bottom:5px; padding:5px;' id=reset onclick=location.href='/personchooser'>RESET</button></div>"
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
     * This input data is sent to the /personchooser. from there all of the courser for a given student are shown
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
                + "<form style='margin:5px;' action=/personchooser method=POST>"
                + "            <label for=fname>First Name:</label>"
                + "            <input type=text id=fname name=fname ><br><br>"
                + "             <label for=fname>First Name:</label>"
                + "            <input type=text id=lname name=lname><br><br>"
                 + "             <label for=_id>Student id (optional):</label>"
                 + "            <input type=text id=_id name=id><br><br>"
                + "            <input type=submit value=Submit>"
                + "        </form>"
                + "</div>"
                + "<br>"
                );
    }
}
