package User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String regUsertel = request.getParameter("usertel");
        String regPassword = request.getParameter("password");
        String regConfpsw = request.getParameter("confirmpsw");
        if(regUsertel==null || regPassword == null||regConfpsw==null)
            return;
        if(regUsertel.equals("") || regPassword.equals("")||regConfpsw.equals(""))
            return;
        LoginSession.startSession(request,response, regUsertel,regPassword);
        DBUser dbuser = new DBUser();
        dbuser.insertNewUser(regUsertel, regPassword);

        response.sendRedirect("information.html");
    }
}
