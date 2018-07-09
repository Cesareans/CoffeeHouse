package Admin.MenuManage;

import Database.DBMenu;
import Database.DBUser;
import DebugUtil.Debug;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteMenuServlet")
public class DeleteMenuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBMenu dbMenu = new DBMenu();
        String serialNumber = request.getParameter("serialNumber");
        PrintWriter pw = response.getWriter();
        if(dbMenu.deleteMenu(serialNumber))
            pw.write("success");
        else
            pw.write("fail");
        pw.close();
    }
}
