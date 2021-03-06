package Admin;

import Database.DBAdmin;
import DebugUtil.Debug;

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
        String adminName = request.getParameter("adminName");
        String password = request.getParameter("password");
        if(adminName == null || password == null)
            return;
        if(adminName.equals("")|| password.equals(""))
            return;

        PrintWriter pw = response.getWriter();
        DBAdmin dbManager = new DBAdmin();
        if(dbManager.matchAdmin(adminName , password)){
            AdminLoginSession.startSession(request,adminName,password);
            pw.write("success");
        }else{
            pw.write("fail");
        }
        pw.close();
    }
}
