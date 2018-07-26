package Order;

import Database.DBCart;
import Database.DBOrder;
import Entity.*;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "OrderServlet")
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8;");
        PrintWriter pw = response.getWriter();
        HttpSession session = request.getSession();
        String usertel = (String)session.getAttribute("usertel");
        DBCart dbcart = new DBCart();
        Cart c = dbcart.getUserCart(usertel);
        if(dbcart.convertToOrder(c)){
            pw.write("true");
        }
        else pw.write("false");
        pw.close();
        dbcart.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8;");
        PrintWriter pw = response.getWriter();
        HttpSession session = request.getSession();
        String usertel = (String)session.getAttribute("usertel");
        DBOrder dborder = new DBOrder();
        ArrayList<Order> order = dborder.getOrderByUser(usertel);
        String result = JSON.toJSONString(order);
        System.out.println(result);
        pw.write(result);
        dborder.close();
        pw.close();
    }

}
