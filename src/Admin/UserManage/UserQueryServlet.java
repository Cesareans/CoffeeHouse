package Admin.UserManage;

import Database.DBUser;
import Debug.Debug;
import Entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.*;

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
    private void processRequest(HttpServletRequest request , HttpServletResponse response){
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
        JSONObject jsonObject = new JSONObject();

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

    public static void main(String[] args) {
        DBUser dbUser = new DBUser();
        ArrayList<User> userList = dbUser.getAllUsers();//未考虑效率

        System.out.println(JSONSerializer.toJSON(userList));
    }
}
