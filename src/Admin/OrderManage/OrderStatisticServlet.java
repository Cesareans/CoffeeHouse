package Admin.OrderManage;

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
import java.util.Map;

@WebServlet(name = "OrderStatisticServlet")
public class OrderStatisticServlet extends HttpServlet {
    class OrderByDate {
        class MealItem {
            String mealName;
            int sales = 0;

            public int getSales() {
                return sales;
            }

            public String getMealName() {
                return mealName;
            }
        }

        String date;
        ArrayList<MealItem> items = new ArrayList<>();

        OrderByDate(String date) {
            this.date = date;
        }

        public String getDate() {
            return date;
        }

        public ArrayList<MealItem> getItems() {
            return items;
        }
        void addItem(String mealName, int sales){
            MealItem mealItem = new MealItem();
            mealItem.mealName = mealName;
            mealItem.sales = sales;
            items.add(mealItem);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        DBOrder dbOrder = new DBOrder();
        ArrayList<Order> orderList;
        if (startDate != null && endDate != null) {
            orderList = dbOrder.getOrderByDate(startDate, endDate);
        } else
            orderList = dbOrder.getAllOrders();

        DBMenu dbMenu = new DBMenu();
        ArrayList<Menu> menus = dbMenu.getAllmenu();
        //创建以orderList内所有order的SN为key，OrderByDate为value的map
        Map<String, OrderByDate> orderDateMap = new HashMap<>();
        orderList.forEach(order -> {
            OrderByDate orderByDate = new OrderByDate(order.getDate());
            orderDateMap.put(order.getOrderSN() , orderByDate);
            order.getOrderlist().forEach(orderItem ->
                orderByDate.addItem(orderItem.getMealName() , orderItem.getQuantity())
            );
        });


        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("data", orderDateMap.values());
        jsonMap.put("menu" , menus);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        pw.write(JSON.toJSONString(jsonMap));
        pw.close();
        dbMenu.close();
        dbOrder.close();
    }
}
