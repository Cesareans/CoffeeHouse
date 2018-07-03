package Admin;

import Database.DBManager;
import Database.DBUser;
import Debug.Debug;
import User.LoginSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request , response);
    }
    private void processRequest(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        String admin = request.getParameter("admin");
        String password = request.getParameter("password");
        Debug.log("admin=" + admin + "&password=" + password);
        if(admin == null || password == null)
            return;
        if(admin.equals("")|| password.equals(""))
            return;

        PrintWriter pw = response.getWriter();
        DBManager dbManager = new DBManager();
        if(dbManager.matchManager(admin , password)){
            AdminLoginSession.startSession(request,admin,password);
            pw.write("success");
        }else{
            pw.write("fail");
        }
        pw.close();
    }
}
