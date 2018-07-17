package Database;
import Entity.*;
import java.sql.*;
import java.util.ArrayList;

public class DBCart {
    private static String url = "com.mysql.jdbc.Driver"; //加载驱动包
    private String connectSql = "jdbc:mysql://127.0.0.1:3306/caffe"; //链接MySQL数据库
    private String sqlUser = "root"; //数据库账号
    private String sqlPasswd = "admin"; //你的数据库密码
    private Connection con = null;
    private PreparedStatement psm = null;
    private ResultSet rs = null;

    static {
        try {
            Class.forName(url);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public DBCart()
    {
        try {
            con = DriverManager.getConnection(connectSql,sqlUser,sqlPasswd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Order> getUserCart(String usertel){
        ArrayList<Order> userlist = new ArrayList<Order>();
        try {
            psm = con.prepareStatement("select distinct orderSN from cart where userTel="+"'"+usertel+"'");
            rs = psm.executeQuery();
            ArrayList<String> orderSNs = new ArrayList<String>();
            while(rs.next()){
                String s=rs.getString(1);
                //System.out.println(s);
                orderSNs.add(s);
            }
            for(String o:orderSNs)
            {
                Order order = new Order();
                order.setOrderSN(o);
                psm = con.prepareStatement("select * from cart where orderSN="+"'"+o+"'");
                rs = psm.executeQuery();
                while(rs.next())
                {
                    OrderItem u = new OrderItem();
                    String orderSN=rs.getString(1);
                    String user=rs.getString(2);
                    u.setMealSerialNumber(rs.getString(3));
                    u.setMealName(rs.getString(4));
                    u.setMealPrice(rs.getDouble(5));
                    u.setQuantity(rs.getInt(6));
                    String date=rs.getString(7);
                    order.setOrderSN(orderSN);
                    order.setUserTel(user);
                    order.setDate(date);
                    order.addItem(u);
                }
                userlist.add(order);
            }
            rs.close();
            psm.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userlist;
    }

    //to be deleted
    public void displayOrderInfo()
    {
        System.out.println("Shopping cart list");
        System.out.printf("%-14s%-14s%-14s%-14s%-14s%-14s%-14s\n","OrderSN","UserTel","MealSN","MealName","MealPrice","Quantity","Date");
        System.out.println("-----------------------------------------------------------------------------");
        ArrayList<Order> list = getUserCart("18850516372");
        if(list.size() == 0){
            System.out.println("暂无数据");
        }else{
            for(Order u: list){  //遍历集合数据
                //System.out.printf("%-14s%-14s%-14s%-14s%-14f%-14d%-14s\n",u.getCartSN(),u.getUserTel(),u.getMealSerialNumber(),u.getMealName(),u.getMealPrice(),u.getQuantity(),u.getDate());
            u.displayOrder();
            }
            System.out.println("-----------------------------------------------------------------------------");
        }
    }

    public boolean insertNewOrder(String orderSN,String user,String mealSerialNumber,int qty,String date)
    {
        boolean result =false;
        try {
            String sqlInset = "insert into cart(orderSN,userTel,mealSerialNumber,qty,orderDate) values(?, ?, ?, ?, ?)";
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
        }
        return result;
    }



    public boolean deleteOrder(String orderSN,String mealSerialNumber)
    {
        boolean result =false;
        try {
            String sql = "delete from cart where orderSN="+"'"+orderSN+"'"+"and mealSerialNumber="+"'"+mealSerialNumber+"'";
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

    public boolean deleteOrderMSN(String mealSerialNumber)
    {
        boolean result =false;
        try {
            String sql = "delete from cart where mealSerialNumber="+"'"+mealSerialNumber+"'";
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            if(i>=1)
                result=true;
            else
                result=false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateOrderQty(String orderSN,String mealSerialNumber,int newQty)
    {
        boolean result =false;
        try {
            String sql = "update cart set qty="+"'"+newQty+"'"+" where orderSN="+"'"+orderSN+"'"+"and mealSerialNumber="+"'"+mealSerialNumber+"'";
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

    public boolean updateOrderDate(String orderSN,String mealSerialNumber,String newdate)
    {
        boolean result =false;
        try {
            String sql = "update cart set orderDate="+"'"+newdate+"'"+" where orderSN="+"'"+orderSN+"'"+"and mealSerialNumber="+"'"+mealSerialNumber+"'";
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

    public boolean updateOrderMSN(String mealSerialNumber,String newSN)
    {
        boolean result =false;
        try {
            String sql = "update cart set mealSerialNumber="+"'"+newSN+"'"+" where mealSerialNumber="+"'"+mealSerialNumber+"'";
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



    public void close()
    {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
