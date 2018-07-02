package Database;

import java.sql.*;
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
                m.setAdmin(rs.getString(1));
                m.setPassword(rs.getString(2));
                managerlist.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭数据库连接
            try {
                rs.close();
                psm.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

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
        System.out.printf("%-14s%-14s\n","Admin","Password");
        System.out.println("--------------------------------------------------------");
        ArrayList<Manager> list = getAllManagers();
        if(list.size() == 0){
            System.out.println("暂无数据");
        }else{
            for(Manager u: list){  //遍历集合数据
                System.out.printf("%-14s%-14s\n",u.getAdmin(),u.getPassword());
            }
            System.out.println("--------------------------------------------------------");
        }
    }

    public boolean matchManager(String admin,String key)
    {
        boolean success = false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlManager,sqlPasswd);
            String sql = "select * from manager where admin like "+admin;
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

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                psm.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public boolean insertNewManager(String admin,String password)
    {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlManager,sqlPasswd);
            String sqlInset = "insert into manager(admin,mpassword) values(?, ?)";
            PreparedStatement stmt = con.prepareStatement(sqlInset);
            stmt.setString(1, admin);
            stmt.setString(2, password);
            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭数据库连接
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    public boolean deleteManager(String admin)
    {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlManager,sqlPasswd);
            String sql = "delete from manager where admin="+"'"+admin+"'";
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭数据库连接
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public boolean updateAdmin(String oldadmin,String newadmin)
    {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlManager,sqlPasswd);
            String sql = "update manager set admin="+"'"+newadmin+"'"+" where admin="+"'"+oldadmin+"'";
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭数据库连接
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public boolean updatePassword(String admin,String newpassword) throws SQLException {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlManager,sqlPasswd);
            String sql = "update manager set mpassword="+"'"+newpassword+"'"+" where admin="+"'"+admin+"'";
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            if(i==1)
                result=true;
            else
                result=false;

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭数据库连接
            con.close();
        }
        return result;
    }

}
