package Check;

import Database.DBUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.String;
@WebServlet(name = "CheckTelServlet")
public class CheckTelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
    private void processRequest(HttpServletRequest request , HttpServletResponse response) throws  ServletException , IOException{
        String usertel = request.getParameter("usertel");
        DBUser dbUser = new DBUser();
        PrintWriter pw = response.getWriter();
        String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        if(usertel == null || usertel.equals("") || dbUser.existUserTel(usertel)||!usertel.matches(REGEX_MOBILE))
            pw.write("false");
        else
            pw.write("true");
        pw.close();
    }
}
