package Filter;

import DebugUtil.Debug;

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
        if(uri.startsWith("/admin")){//后台
            if(uri.endsWith("login.jsp")){
                chain.doFilter(req,resp);
                return;
            }
            //判断管理员是否登陆
            HttpSession session = ((HttpServletRequest) req).getSession();
            String admin = ((String) session.getAttribute("adminName"));
            if(admin == null){
                ((HttpServletResponse) resp).sendRedirect("admin-login.jsp");
            }

        }else {//前台
            if (uri.endsWith("index.jsp") || uri.endsWith("login.jsp") || uri.endsWith("register.jsp") || uri.endsWith("/")) {
                chain.doFilter(req, resp);
                return;
            }
            //判断用户是否登陆
            HttpSession session = ((HttpServletRequest) req).getSession();
            String usertel = ((String) session.getAttribute("usertel"));
            if (usertel == null) {
                ((HttpServletResponse) resp).sendRedirect("login.jsp");
            }
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
    }

}
