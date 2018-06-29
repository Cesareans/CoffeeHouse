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
        if(uri.endsWith("index.jsp") || uri.endsWith("login.jsp") || uri.endsWith("register.jsp") || uri.endsWith("/")){
            chain.doFilter(req, resp);
            return;
        }
        if(uri.endsWith(".css") || uri.endsWith(".js")){
            chain.doFilter(req, resp);
            return;
        }


        HttpSession session = ((HttpServletRequest) req).getSession();
        String username = ((String) session.getAttribute("username"));
        if(username == null){
            ((HttpServletResponse) resp).sendRedirect("login.jsp");
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        Debug.log("Start Login Filter");
    }

}
