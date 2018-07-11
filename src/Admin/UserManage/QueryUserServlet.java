package Admin.UserManage;

import Entity.User;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "QueryUserServlet")
public class QueryUserServlet extends HttpServlet {
    ArrayList<User> userList;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
        DBUser dbUser = new DBUser();
        String telephone = request.getParameter("telephone");
        String username = request.getParameter("username");
        //未考虑效率
        if(telephone!=null) {
            userList.clear();
            userList.add(dbUser.getTelUsers(telephone));
        }else if(username != null)
            userList = dbUser.getNameUsers(username);
        else
            userList = dbUser.getAllUsers();
        int page , limit;
        try {
            page = Integer.parseInt(request.getParameter("page"));
            limit = Integer.parseInt(request.getParameter("limit"));
        }catch (Exception ex){
            page = 1;
            limit = 1;
        }

        Map<String , Object> jsonMap = new HashMap<>();
        jsonMap.put("code" , 0);
        jsonMap.put("msg" , "");
        jsonMap.put("count" , userList.size());
        jsonMap.put("data" , getUsers(page,limit));
        response.setContentType("text/html;charset=utf-8");

        PrintWriter pw = response.getWriter();
        pw.write(JSON.toJSONString(jsonMap));
        pw.close();
    }
    private List<User> getUsers(int page , int limit){
        if(page*limit > userList.size())
            return userList.subList((page-1)*limit , userList.size());
        else
            return userList.subList((page-1)*limit , page*limit);
    }
}
