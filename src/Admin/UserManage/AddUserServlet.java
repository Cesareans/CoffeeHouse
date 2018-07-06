package Admin.UserManage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddUserServlet")
public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
    private void processRequest(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("username");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        String telephone = request.getParameter("telephone");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        PrintWriter pw = response.getWriter();
        pw.write("success");
        pw.close();
    }
}
