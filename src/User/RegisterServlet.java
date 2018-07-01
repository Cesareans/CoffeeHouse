package User;

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
        String regUsername = request.getParameter("username");
        String regPassword = request.getParameter("password");

        if(regUsername==null || regPassword == null)
            return;
        if(regUsername.equals("") || regPassword.equals(""))
            return;

        DBUser dbUser = new DBUser();
        dbUser.insertNewUser(regUsername , regPassword);

        LoginSession.startSession(request,regUsername,regPassword);

        response.sendRedirect("getSession.jsp");
    }
}
