package Admin.OrderManage;

import Database.DBHistoryOrders;
import Database.DBMenu;
import Entity.Menu;
import Entity.Order;
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

@WebServlet(name = "QueryOrderServlet")
public class QueryOrderServlet extends HttpServlet {
    ArrayList<Order> orderList;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
    private void processRequest(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
        DBHistoryOrders dbOrder = new DBHistoryOrders();
        String orderSerial = request.getParameter("orderSerial");
        String userTel = request.getParameter("userTel");
        String userNameMatcher = request.getParameter("userNameMatcher");
        //未考虑效率
        /*if(userNameMatcher != null){
            orderList.clear();
            orderList.add(dbOrder.getUserOrders(userNameMatcher));
        } else if(userSerial != null)
            menuList=dbMenu.getMenuByType(userSerial);
        else if(orderSerial != null)
            menuList=dbMenu.getMenuByName(orderSerial);
        else
            menuList = dbMenu.getAllmenu();*/


        orderList = dbOrder.getAllOrders();
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
        jsonMap.put("count" , orderList.size());
        jsonMap.put("data" , getMenuItems(page,limit));
        response.setContentType("text/html;charset=utf-8");

        PrintWriter pw = response.getWriter();
        pw.write(JSON.toJSONString(jsonMap));
        pw.close();
        dbOrder.close();
    }
    private List<Order> getMenuItems(int page , int limit){
        if(page*limit > orderList.size())
            return orderList.subList((page-1)*limit , orderList.size());
        else
            return orderList.subList((page-1)*limit , page*limit);
    }
}
