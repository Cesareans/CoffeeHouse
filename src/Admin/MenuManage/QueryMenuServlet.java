package Admin.MenuManage;

import Database.DBMenu;
import Database.DBUser;
import DebugUtil.Debug;
import Entity.Menu;
import Entity.User;
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
        menuList = dbMenu.getAllmenu();//未考虑效率
        int page , limit;
        page = Integer.parseInt(request.getParameter("page"));
        limit = Integer.parseInt(request.getParameter("limit"));
        Map<String , Object> jsonMap = new HashMap<>();
        jsonMap.put("code" , 0);
        jsonMap.put("msg" , "");
        jsonMap.put("count" , menuList.size());
        jsonMap.put("data" , getUsers(page,limit));
        response.setContentType("text/html;charset=utf-8");

        PrintWriter pw = response.getWriter();
        pw.write(JSON.toJSONString(jsonMap));
        pw.close();
    }
    private List<Menu> getUsers(int page , int limit){
        if(page*limit > menuList.size())
            return menuList.subList((page-1)*limit , menuList.size());
        else
            return menuList.subList((page-1)*limit , page*limit);
    }
}
