package Database;

import java.sql.*;
import java.util.ArrayList;
import Entity.*;
public class DBMenu {
    private String url = "com.mysql.jdbc.Driver"; //加载驱动包
    private String connectSql = "jdbc:mysql://127.0.0.1:3306/caffe"; //链接MySQL数据库
    private String sqlmenu = "root"; //数据库账号
    private String sqlPasswd = "admin"; //你的数据库密码
    private Connection con = null;
    private PreparedStatement psm = null;
    private ResultSet rs = null;

    public ArrayList<Menu> getAllmenu(){
        ArrayList<Menu> menulist = new ArrayList<Menu>();
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlmenu,sqlPasswd);
            psm = con.prepareStatement("select * from menu");
            rs = psm.executeQuery();

            while(rs.next()){
                Menu u = new Menu();
                u.setSerialNumber(rs.getString(1));
                u.setName(rs.getString(2));
                u.setPrice(rs.getDouble(3));
                u.setQuantity(rs.getInt(4));
                u.setType(rs.getString(5));
                u.setSales(rs.getInt(6));
                u.setPictureUrl(rs.getString(7));
                menulist.add(u);
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
        return menulist;
    }

    //to be deleted
    public void setLocation(String ip)
    {
        connectSql="jdbc:mysql://"+ip+":3306/caffe";
    }

    //to be deleted
    public void displayMenuInfo()
    {
        System.out.println("menu information");
        System.out.printf("%-14s%-14s%-14s%-14s%-14s%-14s\n","Serial number","Name","Price","Quantity","Type","Sales");
        System.out.println("-----------------------------------------------------------------------------");
        ArrayList<Menu> list = getAllmenu();
        if(list.size() == 0){
            System.out.println("暂无数据");
        }else{
            for(Menu u: list){  //遍历集合数据
                System.out.printf("%-14s%-14s%-14f%-14d%-14s%-14d\n",u.getSerialNumber(),u.getName(),u.getPrice(),u.getQuantity(),u.getType(),u.getSales());
            }
            System.out.println("-----------------------------------------------------------------------------");
        }
    }

    public boolean insertNewmenu(String serial,String name,double price,int qty,String type,String pictureUrl)
    {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlmenu,sqlPasswd);
            String sqlInset = "insert into menu(serialnumber,mname,price,qty,mtype,picture) values(?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sqlInset);
            stmt.setString(1, serial);
            stmt.setString(2, name);
            stmt.setDouble(3, price);
            stmt.setInt(4, qty);
            stmt.setString(5, type);
            stmt.setString(6, pictureUrl);
            int i = stmt.executeUpdate();

            if(i==1)
            {
                result=true;
                String sql = "update menu set sales=0 where serialnumber="+"'"+serial+"'";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.executeUpdate();
            }
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

    public boolean deleteMenu(String serial)
    {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlmenu,sqlPasswd);
            String sql = "delete from menu where serialnumber="+"'"+serial+"'";
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            if(i==1)
            {
                result=true;
                DBHistoryOrders h = new DBHistoryOrders();
                h.updateOrderMSN(serial,null);
                DBCart c =new DBCart();
                c.deleteOrderMSN(serial);
            }

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

    public boolean updateSerial(String oldserial,String newserial)
    {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlmenu,sqlPasswd);
            String sql = "update menu set serialnumber="+"'"+newserial+"'"+" where serialnumber="+"'"+oldserial+"'";
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            if(i==1)
            {
                result=true;
                DBCart c=new DBCart();
                c.updateOrderMSN(oldserial,newserial);
                DBHistoryOrders h = new DBHistoryOrders();
                h.updateOrderMSN(oldserial,newserial);
            }
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

    public boolean updateMenuName(String serial,String newname)
    {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlmenu,sqlPasswd);
            String sql = "update menu set mname="+"'"+newname+"'"+" where serialnumber="+"'"+serial+"'";
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

    public boolean updateMenuPrice(String serial,double newprice)
    {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlmenu,sqlPasswd);
            String sql = "update menu set price="+"'"+newprice+"'"+" where serialnumber="+"'"+serial+"'";
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

    public boolean updateMenuQty(String serial,int newqty)
    {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlmenu,sqlPasswd);
            String sql = "update menu set qty="+"'"+newqty+"'"+" where serialnumber="+"'"+serial+"'";
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

    public boolean updateMenuSales(String serial,int newsale)
    {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlmenu,sqlPasswd);
            String sql = "update menu set sales="+"'"+newsale+"'"+" where serialnumber="+"'"+serial+"'";
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

    public boolean updateMenuType(String serial,String newtype)
    {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlmenu,sqlPasswd);
            String sql = "update menu set mtype="+"'"+newtype+"'"+" where serialnumber="+"'"+serial+"'";
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

    public boolean updateMenuPictureUrl(String serial,String newUrl)
    {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlmenu,sqlPasswd);
            String sql = "update menu set picture="+"'"+newUrl+"'"+" where serialnumber="+"'"+serial+"'";
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

    public ArrayList<Menu> getTypeMenu(String type){
        ArrayList<Menu> menulist = new ArrayList<Menu>();
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlmenu,sqlPasswd);
            psm = con.prepareStatement("select * from menu where mtype="+"'"+type+"'");
            rs = psm.executeQuery();

            while(rs.next()){
                Menu u = new Menu();
                u.setSerialNumber(rs.getString(1));
                u.setName(rs.getString(2));
                u.setPrice(rs.getDouble(3));
                u.setQuantity(rs.getInt(4));
                u.setType(rs.getString(5));
                u.setPictureUrl(rs.getString(6));
                menulist.add(u);
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
        return menulist;
    }

    public Menu getMeal(String mealSN)
    {
        Menu u = new Menu();
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlmenu,sqlPasswd);
            psm = con.prepareStatement("select * from menu where serialnumber="+"'"+mealSN+"'");
            rs = psm.executeQuery();

            while(rs.next()){
                u.setSerialNumber(rs.getString(1));
                u.setName(rs.getString(2));
                u.setPrice(rs.getDouble(3));
                u.setQuantity(rs.getInt(4));
                u.setType(rs.getString(5));
                u.setPictureUrl(rs.getString(6));
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
        return u;
    }
}
