package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Entity.*;
public class DBUser {

    private String url = "com.mysql.jdbc.Driver"; //加载驱动包
    private String connectSql = "jdbc:mysql://127.0.0.1:3306/caffe"; //链接MySQL数据库
    private String sqlUser = "root"; //数据库账号
    private String sqlPasswd = "admin"; //你的数据库密码
    private Connection con = null;
    private PreparedStatement psm = null;
    private ResultSet rs = null;

    //采用集合的方法，返回数据集合
    public ArrayList<User> getAllUsers(){
        ArrayList<User> userlist = new ArrayList<User>();
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlUser,sqlPasswd);
            psm = con.prepareStatement("select * from user");
            rs = psm.executeQuery();

            while(rs.next()){
                User u = new User();
                u.setTel(rs.getString(1));
                u.setUpassword(rs.getString(2));
                u.setUname(rs.getString(3));
                u.setBirthday(rs.getString(4));
                u.setEmail(rs.getString(5));
                userlist.add(u);
            }
            //关闭数据库连接
            rs.close();
            psm.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userlist;
    }

    public void setLocation(String ip)
    {
        connectSql="jdbc:mysql://"+ip+":3306/caffe";
    }

    public void displayUserInfo()
    {
        System.out.println("Users' information");
        System.out.printf("%-14s%-14s%-14s%-14s%-14s\n","Tel","Password","Name","Birthday","Email");
        System.out.println("-----------------------------------------------------------------------------");
        ArrayList<User> list = getAllUsers();
        if(list.size() == 0){
            System.out.println("暂无数据");
        }else{
            for(User u: list){  //遍历集合数据
                System.out.printf("%-14s%-14s%-14s%-14s%-14s\n",u.getTel(),u.getUpassword(),u.getUname(),u.getBirthday(),u.getEmail());
            }
            System.out.println("-----------------------------------------------------------------------------");
        }
    }

    public boolean isSuccess(String tel,String key)
    {
        boolean success = false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlUser,sqlPasswd);
            String sql = "select * from user where utel like "+tel;
            psm = con.prepareStatement(sql);
            rs = psm.executeQuery();
            if(!rs.next())
                success=false;
            else
            {
                String password = rs.getString(2);
                if(password.equals(key))
                    success=true;
                else
                    success=false;
            }

            //关闭数据库连接
            rs.close();
            psm.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean insertNewUser(String tel,String password)
    {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlUser,sqlPasswd);
            String sqlInset = "insert into user(utel,upassword) values(?, ?)";
            PreparedStatement stmt = con.prepareStatement(sqlInset);
            stmt.setString(1, tel);
            stmt.setString(2, password);
            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;
            //关闭数据库连接
            rs.close();
            psm.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean insertNewUser(String tel,String password,String name,String birthday,String email)
    {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlUser,sqlPasswd);
            String sqlInset = "insert into user(utel,upassword,uname,birthday,email) values(?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sqlInset);
            stmt.setString(1, tel);
            stmt.setString(2, password);
            stmt.setString(3, name);
            stmt.setString(4, birthday);
            stmt.setString(5, email);
            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;
            //关闭数据库连接
            rs.close();
            psm.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean deleteUser(String tel)
    {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlUser,sqlPasswd);
            String sql = "delete from user where utel="+tel;
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;
            //关闭数据库连接
            rs.close();
            psm.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateTel(String oldtel,String newtel)
    {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlUser,sqlPasswd);
            String sql = "update user set utel="+newtel+" where utel="+oldtel;
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;
            //关闭数据库连接
            rs.close();
            psm.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updatePassword(String tel,String newpassword)
    {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlUser,sqlPasswd);
            String sql = "update user set upassword="+newpassword+" where utel="+tel;
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;
            //关闭数据库连接
            rs.close();
            psm.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateUserName(String tel,String newname)
    {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlUser,sqlPasswd);
            String sql = "update user set uname="+newname+" where utel="+tel;
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;
            //关闭数据库连接
            rs.close();
            psm.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateUserBirthday(String tel,String birthday)
    {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlUser,sqlPasswd);
            String sql = "update user set birthday="+birthday+" where utel="+tel;
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;
            //关闭数据库连接
            rs.close();
            psm.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateUserEmail(String tel,String email)
    {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlUser,sqlPasswd);
            String sql = "update user set email="+email+" where utel="+tel;
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;
            //关闭数据库连接
            rs.close();
            psm.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}

