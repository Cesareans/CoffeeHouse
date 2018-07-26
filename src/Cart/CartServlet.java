package Cart;

import Database.DBCart;
import Database.DBMenu;
import DebugUtil.Debug;
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
import java.util.HashMap;

@WebServlet(name = "CartServlet")
public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //插入到购物车
        PrintWriter pw = response.getWriter();
        HttpSession session = request.getSession();
        String usertel = (String) session.getAttribute("usertel");
        String qty = request.getParameter("qty");
        String serialNumber = request.getParameter("serialNumber");
        DBCart dbcart = new DBCart();
        int num = Integer.parseInt(qty);
        if(dbcart.update(usertel, serialNumber, num)) {
            pw.write("true");
        }
        else {
            pw.write("false");
        }
        dbcart.close();
        pw.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取购物车信息
        response.setContentType("text/html;charset=utf-8;");
        PrintWriter pw = response.getWriter();
        HttpSession session = request.getSession();
        DBCart dbcart = new DBCart();
        String usertel = (String)session.getAttribute("usertel");
        Cart cart = dbcart.getUserCart(usertel);
        DBMenu dbmenu = new DBMenu();
        ArrayList<Menu> m = dbmenu.getAllmenu();
        HashMap<String,Object> h = new HashMap<>();
        h.put("menu", m);
        h.put("items", cart);
        String result = JSON.toJSONString(h);
        pw.write(result);
        dbcart.close();
        dbmenu.close();
        pw.close();
    }
}
