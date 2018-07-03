package Admin;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminLoginSession {
    public static void startSession(HttpServletRequest request , String admin , String password){
        HttpSession session = request.getSession();
        session.setAttribute("admin" , admin);
        session.setAttribute("password" , password);
    }
}
