package Order;

import Entity.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ConvertServlet")
public class ConvertServlet extends HttpServlet {
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
        DBCart dbcart = new DBCart();
        Cart cart =dbcart.getUserCart(usertel);

            if(!dbcart.convertToOrder(cart)) {
                pw.write("false");
                return;
            }

        pw.write("true");
    }
}
