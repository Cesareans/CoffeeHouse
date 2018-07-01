package Database;

import java.sql.*;
import java.util.ArrayList;
import Entity.*;
public class DBCart {
    private String url = "com.mysql.jdbc.Driver"; //加载驱动包
    private String connectSql = "jdbc:mysql://127.0.0.1:3306/caffe"; //链接MySQL数据库
    private String sqlUser = "root"; //数据库账号
    private String sqlPasswd = "admin"; //你的数据库密码
    private Connection con = null;
    private PreparedStatement psm = null;
    private ResultSet rs = null;

    public ArrayList<Order> getUserCart(String usertel){
        ArrayList<Order> userlist = new ArrayList<Order>();
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlUser,sqlPasswd);
            psm = con.prepareStatement("select * from orders where userTel="+usertel);
            rs = psm.executeQuery();
            while(rs.next()){
                Order u = new Order();
                u.setOrderSN(rs.getString(1));
                u.setUser(rs.getString(2));
                u.setMealSerialNumber(rs.getString(3));
                u.setMealName(rs.getString(4));
                u.setMealPrice(rs.getDouble(5));
                u.setQty(rs.getInt(6));
                u.setDate(rs.getString(7));
                userlist.add(u);
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
        return userlist;
    }

    //to be deleted
    public void setLocation(String ip)
    {
        connectSql="jdbc:mysql://"+ip+":3306/caffe";
    }

    //to be deleted
    public void displayOrderInfo()
    {
        System.out.println("History orders' list");
        System.out.printf("%-14s%-14s%-14s%-14s%-14s%-14s%-14s\n","OrderSN","UserTel","MealSN","MealName","MealPrice","Quantity","Date");
        System.out.println("-----------------------------------------------------------------------------");
        ArrayList<Order> list = getUserCart("18750516372");
        if(list.size() == 0){
            System.out.println("暂无数据");
        }else{
            for(Order u: list){  //遍历集合数据
                System.out.printf("%-14s%-14s%-14s%-14s%-14f%-14d%-14s\n",u.getOrderSN(),u.getUser(),u.getMealSerialNumber(),u.getMealName(),u.getMealPrice(),u.getQty(),u.getDate());
            }
            System.out.println("-----------------------------------------------------------------------------");
        }
    }

    public boolean insertNewOrder(String orderSN,String user,String mealSerialNumber,int qty,String date)
    {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlUser,sqlPasswd);
            String sqlInset = "insert into orders(orderSN,userTel,mealSerialNumber,qty,orderDate) values(?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sqlInset);
            stmt.setString(1, orderSN);
            stmt.setString(2, user);
            stmt.setString(3, mealSerialNumber);
            stmt.setInt(4, qty);
            stmt.setString(5, date);
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

    public boolean deleteOrder(String orderSN)
    {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlUser,sqlPasswd);
            String sql = "delete from orders where orderSN="+orderSN;
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

    public boolean updateOrderQty(String orderSN,int newQty)
    {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlUser,sqlPasswd);
            String sql = "update orders set qty="+"'"+newQty+"'"+" where orderSN="+"'"+orderSN+"'";
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

    public boolean updateOrderDate(String orderSN,String newdate)
    {
        boolean result =false;
        try {
            //加载驱动包
            Class.forName(url);
            //连接MYSQL
            con = DriverManager.getConnection(connectSql,sqlUser,sqlPasswd);
            String sql = "update orders set orderDate="+"'"+newdate+"'"+" where orderSN="+"'"+orderSN+"'";
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

}
