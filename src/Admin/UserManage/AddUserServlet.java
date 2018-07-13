package Admin.UserManage;

import Database.DBUser;
import Entity.User;

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
        DBUser dbUser = new DBUser();
        User user = new User();
        user.setName(request.getParameter("username"));
        user.setGender(request.getParameter("gender"));
        user.setBirthday(request.getParameter("birthday"));
        user.setTel(request.getParameter("telephone"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));

        PrintWriter pw = response.getWriter();
        if(dbUser.insertNewUser(user))
            pw.write("success");
        else
            pw.write("fail");
        pw.close();
        dbUser.close();
    }
}
