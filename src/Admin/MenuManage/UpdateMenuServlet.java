package Admin.MenuManage;

import Entity.Menu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpdateMenuServlet")
public class UpdateMenuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
    private void processRequest(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
        DBMenu dbMenu = new DBMenu();
        Menu menu = new Menu();
        menu.setSerialNumber(request.getParameter("serialNumber"));
        menu.setName(request.getParameter("name"));
        menu.setType(request.getParameter("type"));
        menu.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        menu.setPrice(Double.parseDouble(request.getParameter("price")));

        PrintWriter pw = response.getWriter();
        if(dbMenu.updateMenu(menu))
            pw.write("success");
        else
            pw.write("fail");
        pw.close();
    }
}
