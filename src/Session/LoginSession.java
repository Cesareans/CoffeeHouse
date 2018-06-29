package Session;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginSession {
    public static void startSessionWithCookie(HttpServletRequest request , HttpServletResponse response, String username , String password){
        startSession(request , username , password);

        Cookie usernameCookie = new Cookie("username" , username);
        usernameCookie.setMaxAge(7*24*60*60);//保存七天
        response.addCookie(usernameCookie);

        Cookie passwordCookie = new Cookie("password" , password);
        passwordCookie.setMaxAge(7*24*60*60);//保存七天
        response.addCookie(passwordCookie);
    }
    public static void startSession(HttpServletRequest request , String username , String password){
        HttpSession session = request.getSession();
        session.setAttribute("username" , username);
        session.setAttribute("password" , password);
    }
}
