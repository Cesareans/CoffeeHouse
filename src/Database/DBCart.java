package Database;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import Entity.*;
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

    public ArrayList<Cart> getUserCart(String usertel){
        ArrayList<Cart> userlist = new ArrayList<Cart>();
        try {
            psm = con.prepareStatement("select distinct cartSN from cart where userTel="+"'"+usertel+"'");
            rs = psm.executeQuery();
            ArrayList<String> orderSNs = new ArrayList<String>();
            while(rs.next()){
                String s=rs.getString(1);
                //System.out.println(s);
                orderSNs.add(s);
            }
            for(String o:orderSNs)
            {
                Cart order = new Cart();
                order.setCartSN(o);
                psm = con.prepareStatement("select * from cart where cartSN="+"'"+o+"'");
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
                    order.setCartSN(orderSN);
                    order.setUserTel(user);
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

    public Cart getCartBySN(String cartSN){
        Cart cart = new Cart();
        try {

                cart.setCartSN(cartSN);
                psm = con.prepareStatement("select * from cart where cartSN="+"'"+cartSN+"'");
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
                    cart.setCartSN(orderSN);
                    cart.setUserTel(user);
                    cart.addItem(u);
                }

            rs.close();
            psm.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cart;
    }
    //to be deleted
    public void displayOrderInfo()
    {
        System.out.println("Shopping cart list");
        System.out.printf("%-14s%-14s%-14s%-14s%-14s%-14s\n","OrderSN","UserTel","MealSN","MealName","MealPrice","Quantity");
        System.out.println("-----------------------------------------------------------------------------");
        ArrayList<Cart> list = getUserCart("18850516372");
        if(list.size() == 0){
            System.out.println("暂无数据");
        }else{
            for(Cart u: list){  //遍历集合数据
                //System.out.printf("%-14s%-14s%-14s%-14s%-14f%-14d%-14s\n",u.getCartSN(),u.getUserTel(),u.getMealSerialNumber(),u.getMealName(),u.getMealPrice(),u.getQuantity(),u.getDate());
            u.displayOrder();
            }
            System.out.println("-----------------------------------------------------------------------------");
        }
    }

    private boolean haveCart(String usertel)
    {
        boolean result = false;
        try {
            psm = con.prepareStatement("select distinct cartSN from cart where userTel="+"'"+usertel+"'");
            rs = psm.executeQuery();
            ArrayList<String> orderSNs = new ArrayList<String>();
            while(rs.next()){
                String s=rs.getString(1);
                //System.out.println(s);
                orderSNs.add(s);
            }
            if(orderSNs.size()>=1)
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

    public String getSNByUser(String user)
    {
        String sn="";
        try {
            psm = con.prepareStatement("select distinct cartSN from cart where userTel="+"'"+user+"'");
            rs = psm.executeQuery();
            if(rs.next())
               sn=rs.getString(1);
                //System.out.println(s);
            rs.close();
            psm.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sn;
    }

    public boolean insertNewCart(String user, String mealSerialNumber, int qty)
    {
        boolean result =false;
        try {
            String sqlInset = "insert into cart(cartSN,userTel,mealSerialNumber,qty) values(?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sqlInset);
            DBOrder o =new DBOrder();
            if(haveCart(user))
                stmt.setString(1,getSNByUser(user));
            else
                stmt.setString(1, o.getNewSN());
            stmt.setString(2, user);
            stmt.setString(3, mealSerialNumber);
            stmt.setInt(4, qty);
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


    public boolean deleteOrder(String cartSN,String mealSerialNumber)
    {
        boolean result =false;
        try {
            String sql = "delete from cart where cartSN="+"'"+cartSN+"'"+"and mealSerialNumber="+"'"+mealSerialNumber+"'";
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

    public boolean deleteCart(String cartSN)
    {
        boolean result =false;
        try {
            String sql = "delete from cart where cartSN="+"'"+cartSN+"'";
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

    public boolean update(String usertel,String mealSN,int newQty)
    {
        boolean result =false;
        String cartSN = "";
        if(haveCart(usertel))
        {
            cartSN = getSNByUser(usertel);
            if(updateCartQty(cartSN,mealSN,newQty))
                result=true;
        }
        else
        {
            if(insertNewCart(usertel,mealSN,newQty))
                result=true;
        }
        return result;
    }

    private boolean updateCartQty(String cartSN, String mealSerialNumber, int newQty)
    {
        boolean result =false;
        DBMenu m = new DBMenu();
        if(newQty==0)
        {
            deleteOrder(cartSN,mealSerialNumber);
            return true;
        }
        try {
            String sql = "update cart set qty="+"'"+newQty+"'"+" where cartSN="+"'"+cartSN+"'"+"and mealSerialNumber="+"'"+mealSerialNumber+"'";
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

    private String GetNowDate(){
        String temp_str="";
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        temp_str=sdf.format(dt);
        return temp_str;
    }

    public boolean convertToOrder(Cart c)
    {
        boolean result=true;
        DBOrder order = new DBOrder();
        c.setCartSN(order.getNewSN());
        for(OrderItem item:c.getOrderlist())
        {
            order.insertNewOrder(c.getCartSN(),c.getUserTel(),item.getMealSerialNumber(),item.getQuantity(),GetNowDate());
            DBMenu menu = new DBMenu();
            if(menu.getMeal(item.getMealSerialNumber()).getQuantity()-item.getQuantity()>=0)
            menu.updateMenuQty(item.getMealSerialNumber(),menu.getMeal(item.getMealSerialNumber()).getQuantity()-item.getQuantity());
            else
                result=false;
        }
        if(result)
        deleteCart(c.getCartSN());

        return result;
    }

    public boolean convertToOrder(String cartSN)
    {
        boolean result=true;
        Cart c = getCartBySN(cartSN);
        DBOrder order = new DBOrder();
        for(OrderItem item:c.getOrderlist())
        {
            order.insertNewOrder(c.getCartSN(),c.getUserTel(),item.getMealSerialNumber(),item.getQuantity(),GetNowDate());
            DBMenu menu = new DBMenu();
            if(menu.getMeal(item.getMealSerialNumber()).getQuantity()-item.getQuantity()>=0)
            menu.updateMenuQty(item.getMealSerialNumber(),menu.getMeal(item.getMealSerialNumber()).getQuantity()-item.getQuantity());
            else
                result=false;
        }
        if(result)
        deleteCart(c.getCartSN());

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
