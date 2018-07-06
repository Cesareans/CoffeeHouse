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

@WebServlet(name = "UserQueryServlet")
public class UserQueryServlet extends HttpServlet {
    private final int defaultCount = 10;
    private ArrayList<User> userList;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
    private void processRequest(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
        int start;
        int count;
        try {
            start = Integer.parseInt(((String) request.getAttribute("start")));
            count = Integer.parseInt((String) request.getAttribute("count"));
        }catch (Exception ex) {
            start = 0;
            count = defaultCount;
        }
        if(start == 0 || userList == null){
            DBUser dbUser = new DBUser();
            userList = dbUser.getAllUsers();//未考虑效率
        }

        Map<String , Object> jsonMap = new HashMap<>();
        jsonMap.put("userList" , userList);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.write(JSON.toJSONString(jsonMap));
        pw.close();
    }
    private List<User> getUsers(int start , int count){
        if(start < 0)
            start = 0;
        if(start >= userList.size())
            return null;
        if(start + count > userList.size())
            return userList.subList(start,userList.size());
        else
            return userList.subList(start,start+count);
    }
}
