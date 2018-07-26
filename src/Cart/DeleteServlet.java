package Cart;

import Database.DBCart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteServlet")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8;");
        PrintWriter pw = response.getWriter();
        HttpSession session = request.getSession();
        String usertel = (String) session.getAttribute("usertel");
        String serialNumber = request.getParameter("serialNumber");
        DBCart dbcart = new DBCart();
        String cartSN = dbcart.getCartSNByUser(usertel);
        if(dbcart.deleteOrder(cartSN, serialNumber))
            pw.write("true");
        else pw.write("false");
        dbcart.close();
    }
}
