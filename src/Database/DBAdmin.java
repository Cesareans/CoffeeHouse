package Database;

import java.sql.*;
import java.util.ArrayList;
import Entity.*;
public class DBAdmin {
    private static String url = "com.mysql.jdbc.Driver"; //加载驱动包
    private static String connectSql = "jdbc:mysql://127.0.0.1:3306/caffe"; //链接MySQL数据库
    private static String sqlAdmin = "root"; //数据库账号
    private static String sqlPasswd = "admin"; //你的数据库密码
    private static Connection con = null;
    private PreparedStatement psm = null;
    private ResultSet rs = null;

    static {
        try {
            Class.forName(url);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public DBAdmin()
    {
        try {
            con = DriverManager.getConnection(connectSql,sqlAdmin,sqlPasswd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Admin> getAllAdmins(){
        ArrayList<Admin> Adminlist = new ArrayList<Admin>();
        try {
            psm = con.prepareStatement("select * from admin");
            rs = psm.executeQuery();

            while(rs.next()){
                Admin m = new Admin();
                m.setAdmin(rs.getString(1));
                m.setPassword(rs.getString(2));
                Adminlist.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭数据库连接
            try {
                rs.close();
                psm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return Adminlist;
    }

    //to be deleted
    public void displayAdminInfo()
    {
        System.out.println("Admins' information");
        System.out.printf("%-14s%-14s\n","Admin","Password");
        System.out.println("--------------------------------------------------------");
        ArrayList<Admin> list = getAllAdmins();
        if(list.size() == 0){
            System.out.println("暂无数据");
        }else{
            for(Admin u: list){  //遍历集合数据
                System.out.printf("%-14s%-14s\n",u.getAdmin(),u.getPassword());
            }
            System.out.println("--------------------------------------------------------");
        }
    }

    public boolean matchAdmin(String adminName,String key)
    {
        boolean success = false;
        try {
            String sql = "select * from admin where adminName like "+"'"+adminName+"'";
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

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                psm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public boolean existAdmin(String adminName)
    {
        boolean result = false;
        try {
            String sql = "select * from admin where adminName like "+"'"+adminName+"'";
            psm = con.prepareStatement(sql);
            rs = psm.executeQuery();
            if(rs.next())
                result=true;
            else
                result=false;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                psm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }



    public boolean insertNewAdmin(String adminName,String password)
    {
        boolean result =false;
        try {
            String sqlInset = "insert into admin(adminName,mpassword) values(?, ?)";
            PreparedStatement stmt = con.prepareStatement(sqlInset);
            stmt.setString(1, adminName);
            stmt.setString(2, password);
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

    public boolean insertNewAdmin(Admin a)
    {
        boolean result =false;
        try {
            String sqlInset = "insert into admin(adminName,mpassword) values(?, ?)";
            PreparedStatement stmt = con.prepareStatement(sqlInset);
            stmt.setString(1, a.getAdmin());
            stmt.setString(2, a.getPassword());
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



    public boolean deleteAdmin(String adminName)
    {
        boolean result =false;
        try {
            String sql = "delete from admin where adminName="+"'"+adminName+"'";
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

    public boolean updateAdmin(String oldadmin,String newadmin)
    {
        boolean result =false;
        try {
            String sql = "update Admin set adminName="+"'"+newadmin+"'"+" where adminName="+"'"+oldadmin+"'";
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

    public boolean updatePassword(String adminName,String newpassword) throws SQLException {
        boolean result =false;
        try {
            String sql = "update Admin set mpassword="+"'"+newpassword+"'"+" where adminName="+"'"+adminName+"'";
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

    public void closeDB()
    {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
