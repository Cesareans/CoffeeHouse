package User;

import javax.servlet.http.*;

public class LoginSession {
    public static void startSessionWithCookie(HttpServletRequest request , HttpServletResponse response, String usertel , String password){
        startSession(request , usertel , password);

        Cookie usertelCookie = new Cookie("usertel" , usertel);
        usertelCookie.setMaxAge(7*24*60*60);//保存七天
        response.addCookie(usertelCookie);

        Cookie passwordCookie = new Cookie("password" , password);
        passwordCookie.setMaxAge(7*24*60*60);//保存七天
        response.addCookie(passwordCookie);
    }
    public static void startSession(HttpServletRequest request , String usertel , String password){
        HttpSession session = request.getSession();
        session.setAttribute("usertel" , usertel);
        session.setAttribute("password" , password);
    }
    public static void terminateSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("usertel");
        session.removeAttribute("password");
    }
}
