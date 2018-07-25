package Order;

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
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8;");
        PrintWriter pw = response.getWriter();
        HttpSession session = request.getSession();
        String usertel = (String)session.getAttribute("usertel");
        DBOrder dborder = new DBOrder();
        ArrayList<Order> order = dborder.getOrderByUser(usertel);
        String result = JSON.toJSONString(order);
        pw.write(result);
        dborder.close();
        pw.close();
    }
}
