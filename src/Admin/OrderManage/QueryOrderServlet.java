package Admin.OrderManage;

import Database.DBOrder;
import Database.DBUser;
import Entity.Order;
import Entity.User;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

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
        DBOrder dbOrder = new DBOrder();
        String orderSerial = request.getParameter("orderSerial");
        String userTel = request.getParameter("userTel");
        String userNameMatcher = request.getParameter("userNameMatcher");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        //未考虑效率
        if(userNameMatcher != null){
            DBUser dbUser = new DBUser();
            ArrayList<User> users = dbUser.getUsersByName(userNameMatcher);
            orderList.clear();
            users.forEach((user -> {
                orderList.addAll(dbOrder.getOrderByUser(user.getTel()));
            }));
            dbUser.close();
        } else if(userTel != null)
            orderList=dbOrder.getOrderByUser(userTel);
        else if(orderSerial != null) {
            orderList.clear();
            orderList.add(dbOrder.getOrderBySerial(orderSerial));
        }else if(startDate != null && endDate != null){
            orderList=dbOrder.getOrderByDate(startDate , endDate);
        }
        else
            orderList = dbOrder.getAllOrders();

        dbOrder.close();

        int page , limit;
        try {
            limit = Integer.parseInt(request.getParameter("limit"));
            page = Integer.parseInt(request.getParameter("page"));
        }catch (Exception ex){
            limit = orderList.size();
            page = 1;
        }

        Map<String , Object> jsonMap = new HashMap<>();
        jsonMap.put("code" , 0);
        jsonMap.put("msg" , "");
        jsonMap.put("count" , orderList.size());
        jsonMap.put("data" , getMenuItems(page,limit));

        response.setContentType("text/html;charset=utf-8");
        response.getOutputStream().write(JSON.toJSONString(jsonMap).getBytes());
    }
    private List<Order> getMenuItems(int page , int limit){
        if(page*limit > orderList.size())
            return orderList.subList((page-1)*limit , orderList.size());
        else
            return orderList.subList((page-1)*limit , page*limit);
    }
}
