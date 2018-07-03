package Filter;

import Debug.Debug;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String uri = ((HttpServletRequest) req).getRequestURI();
        Debug.log(uri);
        if(uri.startsWith("/admin")){
            if(uri.endsWith("login.html")){
                chain.doFilter(req,resp);
                return;
            }
            HttpSession session = ((HttpServletRequest) req).getSession();
            String admin = ((String) session.getAttribute("admin"));
            if(admin == null){
                Debug.log("Redirect to Admin Login.");
                ((HttpServletResponse) resp).sendRedirect("admin-login.html");
            }

        }else {
            if (uri.endsWith("index.jsp") || uri.endsWith("login.jsp") || uri.endsWith("register.html") || uri.endsWith("/")) {
                chain.doFilter(req, resp);
                return;
            }

            HttpSession session = ((HttpServletRequest) req).getSession();
            String usertel = ((String) session.getAttribute("usertel"));
            if (usertel == null) {
                Debug.log("Redirect to Login.");
                ((HttpServletResponse) resp).sendRedirect("login.jsp");
            }
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
    }

}
