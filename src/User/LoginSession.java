package User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginSession {
    public static void startSessionWithCookie(HttpServletRequest request, HttpServletResponse response, String usertel, String password)
    {
        startSession(request, response, usertel, password);
        Cookie usertelcookie = new Cookie("usertel", usertel);
        response.addCookie(usertelcookie);

        Cookie passwordcookie = new Cookie("password", password);
        response.addCookie(passwordcookie);
    }

    public static void startSession(HttpServletRequest request, HttpServletResponse response, String usertel, String password)
    {
        HttpSession session = request.getSession();
        session.setAttribute("usertel", usertel);
        session.setAttribute("password", password);
    }

    public static void terminateSession(HttpServletRequest request)
    {
        request.removeAttribute("usertel");
        request.removeAttribute("password");
    }
}
