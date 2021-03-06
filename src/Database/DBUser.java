package Database;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import Entity.*;
public class DBUser {

    private static String url = "com.mysql.jdbc.Driver"; //加载驱动包
    private static String connectSql = "jdbc:mysql://127.0.0.1:3306/caffe"; //链接MySQL数据库
    private static String sqlUser = "root"; //数据库账号
    private static String sqlPasswd = "admin"; //你的数据库密码
    private static Connection con = null;
    private PreparedStatement psm = null;
    private ResultSet rs = null;

    static{
        try {
            Class.forName(url);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public DBUser()
    {
        try {
            con = DriverManager.getConnection(connectSql,sqlUser,sqlPasswd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //采用集合的方法，返回数据集合
    public ArrayList<User> getAllUsers(){
        ArrayList<User> userlist = new ArrayList<User>();
        try {
            psm = con.prepareStatement("select * from user");
            rs = psm.executeQuery();

            while(rs.next()){
                User u = new User();
                u.setTel(rs.getString(1));
                u.setPassword(rs.getString(2));
                u.setName(rs.getString(3));
                u.setBirthday(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setRegisterTime(rs.getString(6));
                u.setIsactivate(rs.getBoolean(7));
                u.setGender(rs.getString(8));
                userlist.add(u);

            }
            rs.close();
            psm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userlist;
    }

    public User getUserByTel(String tel){
        User u = new User();
        try {
            psm = con.prepareStatement("select * from user where utel="+"'"+tel+"'");
            rs = psm.executeQuery();
            while(rs.next()){

                u.setTel(rs.getString(1));
                u.setPassword(rs.getString(2));
                u.setName(rs.getString(3));
                u.setBirthday(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setRegisterTime(rs.getString(6));
                u.setIsactivate(rs.getBoolean(7));
                u.setGender(rs.getString(8));

            }
            rs.close();
            psm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    public ArrayList<User> getUsersByName(String name){
        ArrayList<User> userlist = new ArrayList<User>();
        try {
            psm = con.prepareStatement("select * from user where uname like '%"+name+"%' or uname like "+"'"+name+"%' or uname like '%"+name+"'");
            rs = psm.executeQuery();
            while(rs.next()){
                User u = new User();
                u.setTel(rs.getString(1));
                u.setPassword(rs.getString(2));
                u.setName(rs.getString(3));
                u.setBirthday(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setRegisterTime(rs.getString(6));
                u.setIsactivate(rs.getBoolean(7));
                u.setGender(rs.getString(8));
                userlist.add(u);

            }
            rs.close();
            psm.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userlist;
    }

    //to be deleted
    public void displayUserInfo()
    {
        System.out.println("Users' information");
        System.out.printf("%-14s%-14s%-14s%-14s%-14s%-14s%-14s%-14s\n","Tel","Password","Name","Birthday","Email","ReTime","isActivate","gender");
        System.out.println("-----------------------------------------------------------------------------");
        ArrayList<User> list = getUsersByName("s");
        if(list.size() == 0){
            System.out.println("暂无数据");
        }else{
            for(User u: list){  //遍历集合数据
                System.out.printf("%-14s%-14s%-14s%-14s%-14s%-14s%-14s%-14s\n",u.getTel(),u.getPassword(),u.getName(),u.getBirthday(),u.getEmail(),u.getRegisterTime(),u.isIsactivate(),u.getGender());
            }
            System.out.println("-----------------------------------------------------------------------------");
        }
    }

    public boolean matchUser(String tel,String key)
    {
        boolean success = false;
        try {
            String sql = "select * from user where utel like "+"'"+tel+"'";
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
            rs.close();
            psm.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean insertNewUser(String tel,String password)
    {
        boolean result =false;
        try {

            String sqlInset = "insert into user(utel,upassword,registerTime,isactivate) values(?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sqlInset);
            String registerTime = GetNowDate();
            stmt.setString(1, tel);
            stmt.setString(2, password);
            stmt.setString(3, registerTime);
            stmt.setBoolean(4, true);

            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean insertNewUser(String tel,String password,String name,String birthday,String email,String gender)
    {
        boolean result =false;
        if(existUserTel(tel))
            return false;
        try {
            String sqlInset = "insert into user(utel,upassword,uname,birthday,email,registerTime,isactivate,gender) values(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sqlInset);
            stmt.setString(1, tel);
            stmt.setString(2, password);
            stmt.setString(3, name);
            stmt.setString(4, birthday);
            stmt.setString(5, email);
            String registerTime = GetNowDate();
            stmt.setString(6, registerTime);
            stmt.setBoolean(7, true);
            stmt.setString(8, gender);

            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean insertNewUser(User u)
    {
        boolean result =false;
        if(existUserTel(u.getTel()))
            return false;
        try {
            String sqlInset = "insert into user(utel,upassword,uname,birthday,email,registerTime,isactivate,gender) values(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sqlInset);
            stmt.setString(1, u.getTel());
            stmt.setString(2, u.getPassword());
            stmt.setString(3, u.getName());
            stmt.setString(4, u.getBirthday());
            stmt.setString(5, u.getEmail());
            String registerTime = GetNowDate();
            stmt.setString(6, GetNowDate());
            u.setRegisterTime(GetNowDate());
            stmt.setBoolean(7,true);
            stmt.setString(8, u.getGender());

            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean deleteUser(String tel)
    {
        boolean result =false;
        try {
            String sql = "delete from user where utel="+"'"+tel+"'";
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean updateUser(User u)
    {
        int col=0;
        boolean result = false;
        try {
            String sql = "update user set upassword="+"'"+u.getPassword()+"'"+" where utel="+"'"+u.getTel()+"'";
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            col+=i;
            sql="update user set uname="+"'"+u.getName()+"'"+" where utel="+"'"+u.getTel()+"'";
            stmt = con.prepareStatement(sql);
            i = stmt.executeUpdate();
            col+=i;
            sql="update user set birthday="+"'"+u.getBirthday()+"'"+" where utel="+"'"+u.getTel()+"'";
            stmt = con.prepareStatement(sql);
            i = stmt.executeUpdate();
            col+=i;
            sql="update user set email="+"'"+u.getEmail()+"'"+" where utel="+"'"+u.getTel()+"'";
            stmt = con.prepareStatement(sql);
            i = stmt.executeUpdate();
            col+=i;
            sql="update user set gender="+"'"+u.getGender()+"'"+" where utel="+"'"+u.getTel()+"'";
            stmt = con.prepareStatement(sql);
            i = stmt.executeUpdate();
            col+=i;
            int acint=0;
            if(u.isIsactivate())
                acint=1;
            else
                acint=0;
            sql = "update user set isactivate="+"'"+acint+"'"+" where utel="+"'"+u.getTel()+"'";
            stmt = con.prepareStatement(sql);
            i = stmt.executeUpdate();
            col+=i;
            if(col>=1)
                result=true;
            else
                result=false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public boolean updateTel(String oldtel,String newtel)
    {
        boolean result =false;
        try {
            String sql = "update user set utel="+"'"+newtel+"'"+" where utel="+"'"+oldtel+"'";
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updatePassword(String tel,String newpassword)
    {
        boolean result =false;
        try {

            String sql = "update user set upassword="+"'"+newpassword+"'"+" where utel="+"'"+tel+"'";
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateUserName(String tel,String newname)
    {
        boolean result =false;
        try {
            String sql = "update user set uname="+"'"+newname+"'"+" where utel="+"'"+tel+"'";
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateUserBirthday(String tel,String birthday)
    {
        boolean result =false;
        try {
            String sql = "update user set birthday="+"'"+birthday+"'"+" where utel="+"'"+tel+"'";
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateUserEmail(String tel,String email)
    {
        boolean result =false;
        try {
            String sql = "update user set email="+"'"+email+"'"+" where utel="+"'"+tel+"'";
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateGender(String tel,String newgender)
    {
        boolean result =false;
        try {
            String sql = "update user set gender="+"'"+newgender+"'"+" where utel="+"'"+tel+"'";
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateActivate(String tel,boolean ac)
    {
        boolean result =false;
        try {
            int acint=0;
            if(ac)
                acint=1;
            else
                acint=0;
            String sql = "update user set isactivate="+"'"+acint+"'"+" where utel="+"'"+tel+"'";
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean existUserName(String uname)
    {
        boolean result=false;
        try {
            psm = con.prepareStatement("select * from user where uname="+"'"+uname+"'");
            rs = psm.executeQuery();
            if(rs.next())
                result=true;
            else
                result=false;

            rs.close();
            psm.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean existUserTel(String utel)
    {
        boolean result=false;
        try {

            psm = con.prepareStatement("select * from user where utel="+"'"+utel+"'");
            rs = psm.executeQuery();
            if(rs.next())
                result=true;
            else
                result=false;
            rs.close();
            psm.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private String GetNowDate(){
        String temp_str="";
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        temp_str=sdf.format(dt);
        return temp_str;
    }

    public void close()
    {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

