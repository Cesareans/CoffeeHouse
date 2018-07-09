package Admin.UserManage;

import Database.DBUser;
import DebugUtil.Debug;
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
        userList= dbUser.getAllUsers();//未考虑效率
        int page , limit;
        page = Integer.parseInt(request.getParameter("page"));
        limit = Integer.parseInt(request.getParameter("limit"));
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
