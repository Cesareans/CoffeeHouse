package Admin.MenuManage;

import Database.DBMenu;
import Entity.Menu;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "QueryMenuServlet")
public class QueryMenuServlet extends HttpServlet {
    ArrayList<Menu> menuList;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
    private void processRequest(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
        DBMenu dbMenu = new DBMenu();
        String menuItemName = request.getParameter("menuItemName");
        String type = request.getParameter("type");
        String serialNumber = request.getParameter("serialNumber");
        //未考虑效率
        if(serialNumber != null){
            menuList.clear();
            menuList.add(dbMenu.getSNmenu(serialNumber));
        } else if(type != null)
            menuList=dbMenu.getTypeMenu(type);
        else if(menuItemName != null)
            menuList=dbMenu.getNamemenu(menuItemName);
        else
            menuList = dbMenu.getAllmenu();

        int page , limit;
        try {
            limit = Integer.parseInt(request.getParameter("limit"));
            page = Integer.parseInt(request.getParameter("page"));
        }catch (Exception ex){
            limit = 1;
            page = 1;
        }
        Map<String , Object> jsonMap = new HashMap<>();
        jsonMap.put("code" , 0);
        jsonMap.put("msg" , "");
        jsonMap.put("count" , menuList.size());
        jsonMap.put("data" , getMenuItems(page,limit));
        response.setContentType("text/html;charset=utf-8");

        PrintWriter pw = response.getWriter();
        pw.write(JSON.toJSONString(jsonMap));
        pw.close();
    }
    private List<Menu> getMenuItems(int page , int limit){
        if(page*limit > menuList.size())
            return menuList.subList((page-1)*limit , menuList.size());
        else
            return menuList.subList((page-1)*limit , page*limit);
    }
}
