package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Entity.*;
public class DBManager {
    private String url = "com.mysql.jdbc.Driver"; //加载驱动包
    private String connectSql = "jdbc:mysql://127.0.0.1:3306/caffe"; //链接MySQL数据库
    private String sqlManager = "root"; //数据库账号
    private String sqlPasswd = "admin"; //你的数据库密码
    private Connection con = null;
    private PreparedStatement psm = null;
    private ResultSet rs = null;

    public ArrayList<Manager> getAllManagers(){
        ArrayList<Manager> managerlist = new ArrayList<Manager>();
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlManager,sqlPasswd);
            psm = con.prepareStatement("select * from manager");
            rs = psm.executeQuery();

            while(rs.next()){
                Manager m = new Manager();
                m.setTel(rs.getString(1));
                m.setPassword(rs.getString(2));
                m.setName(rs.getString(3));

                managerlist.add(m);
            }
            //关闭数据库连接
            rs.close();
            psm.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return managerlist;
    }

    //to be deleted
    public void setLocation(String ip)
    {
        connectSql="jdbc:mysql://"+ip+":3306/caffe";
    }

    //to be deleted
    public void displayManagerInfo()
    {
        System.out.println("Managers' information");
        System.out.printf("%-14s%-14s%-14s\n","Tel","Password","Name");
        System.out.println("--------------------------------------------------------");
        ArrayList<Manager> list = getAllManagers();
        if(list.size() == 0){
            System.out.println("暂无数据");
        }else{
            for(Manager u: list){  //遍历集合数据
                System.out.printf("%-14s%-14s%-14s\n",u.getTel(),u.getPassword(),u.getName());
            }
            System.out.println("--------------------------------------------------------");
        }
    }

    public boolean matchManager(String tel,String key)
    {
        boolean success = false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlManager,sqlPasswd);
            String sql = "select * from manager where mtel like "+tel;
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

    public boolean insertNewManager(String tel,String password)
    {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlManager,sqlPasswd);
            String sqlInset = "insert into manager(mtel,mpassword) values(?, ?)";
            PreparedStatement stmt = con.prepareStatement(sqlInset);
            stmt.setString(1, tel);
            stmt.setString(2, password);
            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;
            //关闭数据库连接

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean insertNewManager(String tel,String password,String name)
    {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlManager,sqlPasswd);
            String sqlInset = "insert into manager(mtel,mpassword,mname) values(?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sqlInset);
            stmt.setString(1, tel);
            stmt.setString(2, password);
            stmt.setString(3, name);

            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;
            //关闭数据库连接

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean deleteManager(String tel)
    {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlManager,sqlPasswd);
            String sql = "delete from manager where mtel="+"'"+tel+"'";
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;
            //关闭数据库连接

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
            con = DriverManager.getConnection(connectSql,sqlManager,sqlPasswd);
            String sql = "update manager set mtel="+"'"+newtel+"'"+" where mtel="+"'"+oldtel+"'";
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;
            //关闭数据库连接

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
            con = DriverManager.getConnection(connectSql,sqlManager,sqlPasswd);
            String sql = "update manager set mpassword="+"'"+newpassword+"'"+" where mtel="+"'"+tel+"'";
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;
            //关闭数据库连接

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateManagerName(String tel,String newname)
    {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlManager,sqlPasswd);
            String sql = "update manager set mname="+"'"+newname+"'"+" where mtel="+"'"+tel+"'";
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;
            //关闭数据库连接

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
