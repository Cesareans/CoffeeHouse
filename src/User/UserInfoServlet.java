package User;

import Database.DBUser;
import Entity.User;
import com.alibaba.fastjson.*;
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
        PrintWriter pw = response.getWriter();
        HttpSession session = request.getSession();
        String usertel = (String)session.getAttribute("usertel");
        String password = (String)session.getAttribute("password");
        String username = request.getParameter("username");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        String email = request.getParameter("email");
        DBUser dbuser = new DBUser();
        User u = new User();
        u.setTel(usertel);
        u.setPassword(password);
        u.setBirthday(birthday);
        u.setName(username);
        u.setEmail(email);
        u.setGender(gender);
        System.out.println(JSON.toJSONString(u , true));
        if(dbuser.updateUser(u))
            pw.write("true");
        else pw.write("false");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8;");
        PrintWriter pw = response.getWriter();
        DBUser dbuser = new DBUser();
        HttpSession session = request.getSession();
        String usertel = (String)session.getAttribute("usertel");
        String password = (String)session.getAttribute("password");
        User u = dbuser.getUserByTel(usertel);
        pw.write(JSON.toJSONString(u));
        pw.close();
    }
}
