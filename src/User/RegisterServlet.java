package User;

import Database.DBUser;
import Debug.Debug;
import Session.LoginSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
        String regUsertel = request.getParameter("usertel");
        String regPassword = request.getParameter("password");

        if(regUsertel==null || regPassword == null)
            return;
        if(regUsertel.equals("") || regPassword.equals(""))
            return;

        DBUser dbUser = new DBUser();
        Debug.log(dbUser.insertNewUser(regUsertel , regPassword)?"true":"false");

        LoginSession.startSession(request,regUsertel,regPassword);

        response.sendRedirect("getSession.jsp");
    }
}
