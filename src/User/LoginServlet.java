package User;

import Database.DBUser;
import Debug.Debug;
import Session.LoginSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Debug.log("get login message");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username == null || password == null)
            return;
        if(username.equals("")|| password.equals(""))
            return;

        DBUser dbUser = new DBUser();
        if(dbUser.matchUser(username , password)){
            LoginSession.startSessionWithCookie(request,response,username,password);
            response.sendRedirect("getSession.jsp");
        }else{

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
