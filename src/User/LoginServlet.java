package User;

import Database.DBUser;
import DebugUtil.Debug;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    private void processRequest(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        String usertel = request.getParameter("usertel");
        String password = request.getParameter("password");
        if(usertel == null || password == null)
            return;
        if(usertel.equals("")|| password.equals(""))
            return;

        PrintWriter pw = response.getWriter();
        DBUser dbUser = new DBUser();
        if(dbUser.matchUser(usertel , password)){
            LoginSession.startSessionWithCookie(request,response,usertel,password);
            pw.write("true");
        }else{
            pw.write("false");
        }
        pw.close();
        dbUser.close();
    }
}
