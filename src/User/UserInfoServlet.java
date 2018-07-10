package User;

import Database.DBUser;
import Entity.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserInfoServlet")
public class UserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        HttpSession session = request.getSession();
        String usertel = (String)session.getAttribute("usertel");
        String password = (String)session.getAttribute("password");

        String uname = request.getParameter("uname");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthyear")+"-"+request.getParameter("birthmonth")+"-"+request.getParameter("birthday");
        String email = request.getParameter("email");
        DBUser dbuser = new DBUser();
        User u = new User();
        u.setTel(usertel);
        u.setPassword(password);
        u.setBirthday(birthday);
        u.setName(uname);
        u.setEmail(email);
        u.setGender(gender);
        if(dbuser.updateUser(u))
            pw.write("true");
        else pw.write("false");


    }
}
