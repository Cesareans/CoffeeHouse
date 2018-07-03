package Admin;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminLoginSession {
    public static void startSession(HttpServletRequest request , String adminName , String password){
        HttpSession session = request.getSession();
        session.setAttribute("adminName" , adminName);
        session.setAttribute("password" , password);
    }
    public static void terminateSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("adminName");
        session.removeAttribute("password");
    }
}
