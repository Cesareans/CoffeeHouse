package Cart;

import Database.DBCart;
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

@WebServlet(name = "CartServlet")
public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        HttpSession session = request.getSession();
        DBCart dbcart = new DBCart();
        String usertel = (String) session.getAttribute("usertel");
        String serialNumber = request.getParameter("serialNumber");
        String qty = request.getParameter("qty");
        int num = Integer.parseInt(qty);
        if(dbcart.update(usertel,serialNumber,num)) {
            pw.write("true");
        }
        else {
            pw.write("false");
        }
        dbcart.close();
        pw.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8;");
        PrintWriter pw = response.getWriter();
        HttpSession session = request.getSession();
        DBCart dbcart = new DBCart();
        String usertel = (String) session.getAttribute("usertel");
        ArrayList<Cart> c = dbcart.getUserCart(usertel);
        String result = JSON.toJSONString(c);
        pw.write(result);
        dbcart.close();
        pw.close();
    }
}
