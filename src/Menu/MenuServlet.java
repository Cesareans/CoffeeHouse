package Menu;
import Database.DBMenu;
import Entity.Menu;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "MenuServlet")
public class MenuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        response.setContentType("text/html;charset=utf-8;");
        PrintWriter pw = response.getWriter();
        DBMenu dbmenu = new DBMenu();
        ArrayList<Menu> u = dbmenu.getAllmenu();
        String result = JSON.toJSONString(u);
        pw.write(result);
        dbmenu.close();
        pw.close();
    }
}
