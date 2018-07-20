package Database;

import java.sql.*;
import java.util.ArrayList;
import Entity.*;
public class DBOrder {
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

    public DBOrder()
    {
        //连接MYSQL
        try {
            con = DriverManager.getConnection(connectSql,sqlUser,sqlPasswd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Order> getAllOrders(){
        ArrayList<Order> userlist = new ArrayList<Order>();
        try {
            psm = con.prepareStatement("select distinct orderSN from orders");
            rs = psm.executeQuery();
            ArrayList<String> orderSNs = new ArrayList<String>();
            while(rs.next()){
                String s=rs.getString(1);
                orderSNs.add(s);
            }
            for(String o:orderSNs)
            {
                Order order = new Order();
                order.setOrderSN(o);
                psm = con.prepareStatement("select * from orders where orderSN="+"'"+o+"'");
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

    public ArrayList<Order> getOrderByUser(String usertel){
        ArrayList<Order> userlist = new ArrayList<Order>();
        try {
            psm = con.prepareStatement("select distinct orderSN from orders where userTel="+"'"+usertel+"'");
            rs = psm.executeQuery();
            ArrayList<String> orderSNs = new ArrayList<String>();
            while(rs.next()){
                String s=rs.getString(1);
                orderSNs.add(s);
            }
            for(String o:orderSNs)
            {
                Order order = new Order();
                order.setOrderSN(o);
                psm = con.prepareStatement("select * from orders where orderSN="+"'"+o+"'");
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

    public ArrayList<Order> getOrderByDate(String from, String to){
        ArrayList<Order> userlist = new ArrayList<Order>();
        try {
            psm = con.prepareStatement("select distinct orderSN from orders where orderDate between '"+from+"' and '"+to+"'");
            rs = psm.executeQuery();
            ArrayList<String> orderSNs = new ArrayList<String>();
            while(rs.next()){
                String s=rs.getString(1);
                orderSNs.add(s);
            }
            for(String o:orderSNs)
            {
                Order order = new Order();
                order.setOrderSN(o);
                psm = con.prepareStatement("select * from orders where orderSN="+"'"+o+"'");
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

    public Order getOrderBySerial(String sn){
        Order order = new Order();
        try {
            psm = con.prepareStatement("select * from orders where orderSN="+"'"+sn+"'");
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
            rs.close();
            psm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    //to be deleted
    public void displayOrderInfo()
    {
        System.out.println("History orders' list");
        //System.out.printf("%-14s%-14s%-14s%-14s%-14s%-14s%-14s\n","OrderSN","UserTel","MealSN","MealName","MealPrice","Quantity","Date");
        System.out.println("-----------------------------------------------------------------------------");
        ArrayList<Order> list = getAllOrders();
        if(list.size() == 0){
            System.out.println("暂无数据");
        }else{
            for(Order u: list){  //遍历集合数据
                u.displayOrder();
            }
            System.out.println("-----------------------------------------------------------------------------");
        }
    }

    public String getNewSN()
    {
        ArrayList<Order> orderlist = getAllOrders();
        int SN=0;
        for(int i=0;i<orderlist.size();i++)
        {
            if(SN<Integer.valueOf(orderlist.get(i).getOrderSN()))
                SN=Integer.valueOf(orderlist.get(i).getOrderSN());
        }
        SN++;
        String sn=String.format("%06d",SN);
        return sn;
    }

    public boolean insertNewOrder(String orderSN,String user,String mealSerialNumber,int qty,String date)
    {
        boolean result =false;
        try {
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

            DBMenu m =new DBMenu();
            Menu menu = m.getMeal(mealSerialNumber);
            updateOrderMealName(orderSN,mealSerialNumber,menu.getName());
            updateOrderMealPrice(orderSN,mealSerialNumber,menu.getPrice());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean deleteOrder(String orderSN)
    {
        boolean result =false;
        try {
            String sql = "delete from orders where orderSN="+"'"+orderSN+"'";
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


    public boolean updateOrderQty(String orderSN,String mealSerialNumber,int newQty)
    {
        boolean result =false;
        try {
            String sql = "update orders set qty="+"'"+newQty+"'"+" where orderSN="+"'"+orderSN+"'"+"and mealSerialNumber="+"'"+mealSerialNumber+"'";
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
            String sql = "update orders set orderDate="+"'"+newdate+"'"+" where orderSN="+"'"+orderSN+"'"+"and mealSerialNumber="+"'"+mealSerialNumber+"'";
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

    private boolean updateOrderMealName(String orderSN,String mealSerialNumber, String newMealName)
    {
        boolean result =false;
        try {
            String sql = "update orders set mealName="+"'"+newMealName+"'"+" where orderSN="+"'"+orderSN+"'"+"and mealSerialNumber="+"'"+mealSerialNumber+"'";
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

    private boolean updateOrderMealPrice(String orderSN,String mealSerialNumber, double newMealPrice)
    {
        boolean result =false;
        try {

            String sql = "update orders set mealPrice="+"'"+newMealPrice+"'"+" where orderSN="+"'"+orderSN+"'"+"and mealSerialNumber="+"'"+mealSerialNumber+"'";
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

    public boolean updateOrderMSN(String mealSerialNumber, String newMSN)
    {
        boolean result =false;
        try {
            String sql = "update orders set mealSerialNumber="+"'"+newMSN+"'"+" where mealSerialNumber="+"'"+mealSerialNumber+"'";
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
