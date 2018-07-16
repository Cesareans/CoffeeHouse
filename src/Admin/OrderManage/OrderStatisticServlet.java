package Admin.OrderManage;

import Database.DBMenu;
import Database.DBOrder;
import DebugUtil.Debug;
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
import java.util.Map;

@WebServlet(name = "OrderStatisticServlet")
public class OrderStatisticServlet extends HttpServlet {
    class SalesbyMenu{
        String mealName;
        int sales = 0;
        SalesbyMenu(String mealName){
            this.mealName = mealName;
        }

        public int getSales() {
            return sales;
        }

        public String getMealName() {
            return mealName;
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
    private void processRequest(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException{
        DBOrder dbOrder = new DBOrder();
        ArrayList<Order> orderList = dbOrder.getAllOrders();

        DBMenu dbMenu = new DBMenu();
        ArrayList<Menu> menuList = dbMenu.getAllmenu();

        Map<String , SalesbyMenu> salesByMenu = new HashMap<>();
        menuList.stream().map(Menu::getName).forEach(menuName->salesByMenu.put(menuName, new SalesbyMenu(menuName)));

        orderList.forEach(order -> order.getOrderlist().forEach(
                orderItem -> {salesByMenu.get(orderItem.getMealName()).sales += orderItem.getQuantity();
        }));


        Map<String , Object> jsonMap = new HashMap<>();
        jsonMap.put("data" , salesByMenu.values());

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        pw.write(JSON.toJSONString(jsonMap));
        pw.close();
        dbMenu.close();
        dbOrder.close();
    }
}
