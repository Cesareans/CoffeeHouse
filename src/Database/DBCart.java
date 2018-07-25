package Database;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import DebugUtil.Debug;
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

    public DBCart() {
        try {
            con = DriverManager.getConnection(connectSql, sqlUser, sqlPasswd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getNewSN()
    {
        ArrayList<Integer> SNs = new ArrayList<>();
        String sn = "";
        try {

            psm = con.prepareStatement("select cartSN from cart");
            rs = psm.executeQuery();
            while (rs.next()) {
                SNs.add(Integer.valueOf(rs.getString(1)));
            }
            rs.close();
            psm.close();

            int SN=0;
            for(Integer i : SNs){
                if(SN < i)
                    SN = i;
            }
            SN++;
            sn=String.format("%06d",SN);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sn;
    }

    public Cart getUserCart(String usertel) {
        Cart userCart = null;
        try {
            psm = con.prepareStatement("select distinct cartSN from cart where userTel=" + "'" + usertel + "'");
            rs = psm.executeQuery();

            if (rs.next())
                userCart = getCartBySN(rs.getString(1));

            rs.close();
            psm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userCart;
    }

    public Cart getCartBySN(String cartSN) {
        Cart cart = new Cart();
        try {
            cart.setCartSN(cartSN);
            psm = con.prepareStatement("select * from cart where cartSN=" + "'" + cartSN + "'");
            rs = psm.executeQuery();
            while (rs.next()) {
                OrderItem u = new OrderItem();
                String user = rs.getString(2);
                u.setMealSerialNumber(rs.getString(3));
                u.setMealName(rs.getString(4));
                u.setMealPrice(rs.getDouble(5));
                u.setQuantity(rs.getInt(6));

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

    public String getCartSNByUser(String usertel) {
        String sn = "";
        try {
            psm = con.prepareStatement("select distinct cartSN from cart where userTel=" + "'" + usertel + "'");
            rs = psm.executeQuery();
            if (rs.next())
                sn = rs.getString(1);
            //System.out.println(s);
            rs.close();
            psm.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sn;
    }

    private boolean haveCart(String usertel) {
        return getUserCart(usertel) != null;
    }

    private boolean haveCartMSN(String cartSN,String MSN)
    {
        boolean result = false;
        try {
            psm = con.prepareStatement("select distinct mealSerialNumber from cart where cartSN="+"'"+cartSN+"' and mealSerialNumber ="+"'"+MSN+"'");
            rs = psm.executeQuery();
            ArrayList<String> MSNs = new ArrayList<String>();
            result = rs.next();
            rs.close();
            psm.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean insertNewCart(String user, String mealSerialNumber, int qty) {
        boolean result = false;
        try {
            String sqlInset = "insert into cart(cartSN,userTel,mealSerialNumber,qty) values(?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sqlInset);
            DBOrder o = new DBOrder();
            String sn = getNewSN();
            if(sn.equals(""))
                sn = o.getNewSN();
            stmt.setString(1, sn);
            stmt.setString(2, user);
            stmt.setString(3, mealSerialNumber);
            stmt.setInt(4, qty);
            int i = stmt.executeUpdate();
            result = i == 1;
            o.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean insertNewItem(String cartSN , String userTel , String mealSerialNumber, int qty ){
        boolean result = false;
        try {
            String sqlInset = "insert into cart(cartSN,userTel,mealSerialNumber,qty) values(?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sqlInset);
            stmt.setString(1, cartSN);
            stmt.setString(2, userTel);
            stmt.setString(3, mealSerialNumber);
            stmt.setInt(4, qty);
            int i = stmt.executeUpdate();
            result = i == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean deleteOrder(String cartSN, String mealSerialNumber) {
        boolean result = false;
        try {
            String sql = "delete from cart where cartSN=" + "'" + cartSN + "'" + "and mealSerialNumber=" + "'" + mealSerialNumber + "'";
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            result = i == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean deleteOrderMSN(String mealSerialNumber) {
        boolean result = false;
        try {
            String sql = "delete from cart where mealSerialNumber=" + "'" + mealSerialNumber + "'";
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            result = i >= 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean deleteCart(String cartSN) {
        boolean result = false;
        try {
            String sql = "delete from cart where cartSN=" + "'" + cartSN + "'";
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            result = i >= 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public boolean update(String usertel, String mealSN, int newQty) {
        boolean result = false;
        String cartSN = "";
        if (haveCart(usertel)) {
            cartSN = getCartSNByUser(usertel);
            if(haveCartMSN(cartSN,mealSN))
            {
                if(updateCartQty(cartSN,mealSN,newQty))
                    result=true;
            }
            else
            {
                if(insertNewItem(cartSN,usertel,mealSN,newQty))
                    result=true;
            }
        } else {
            if (insertNewCart(usertel, mealSN, newQty))
                result = true;
        }
        return result;
    }

    private boolean updateCartQty(String cartSN, String mealSerialNumber, int newQty) {
        boolean result = false;
        DBMenu m = new DBMenu();
        if (newQty == 0) {
            deleteOrder(cartSN, mealSerialNumber);
            return true;
        }
        try {
            String sql = "update cart set qty=" + "'" + newQty + "'" + " where cartSN=" + "'" + cartSN + "'" + "and mealSerialNumber=" + "'" + mealSerialNumber + "'";
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            result = i == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public boolean updateOrderMSN(String mealSerialNumber, String newSN) {
        boolean result = false;
        try {
            String sql = "update cart set mealSerialNumber=" + "'" + newSN + "'" + " where mealSerialNumber=" + "'" + mealSerialNumber + "'";
            PreparedStatement stmt = con.prepareStatement(sql);
            int i = stmt.executeUpdate();
            if (i == 1)
                result = true;
            else
                result = false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private String GetNowDate() {
        String temp_str = "";
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        temp_str = sdf.format(dt);
        return temp_str;
    }

    public boolean convertToOrder(Cart c) {
        boolean result = true;
        DBOrder order = new DBOrder();
        c.setCartSN(order.getNewSN());
        for (OrderItem item : c.getOrderlist()) {
            order.insertNewOrder(c.getCartSN(), c.getUserTel(), item.getMealSerialNumber(), item.getQuantity(), GetNowDate());
            DBMenu menu = new DBMenu();
            if (menu.getMeal(item.getMealSerialNumber()).getQuantity() - item.getQuantity() >= 0)
                menu.updateMenuQty(item.getMealSerialNumber(), menu.getMeal(item.getMealSerialNumber()).getQuantity() - item.getQuantity());
            else
                result = false;
        }
        if (result)
            deleteCart(c.getCartSN());

        return result;
    }

    public boolean convertToOrder(String cartSN) {
        boolean result = true;
        Cart c = getCartBySN(cartSN);
        DBOrder order = new DBOrder();
        for (OrderItem item : c.getOrderlist()) {
            order.insertNewOrder(c.getCartSN(), c.getUserTel(), item.getMealSerialNumber(), item.getQuantity(), GetNowDate());
            DBMenu menu = new DBMenu();
            if (menu.getMeal(item.getMealSerialNumber()).getQuantity() - item.getQuantity() >= 0)
                menu.updateMenuQty(item.getMealSerialNumber(), menu.getMeal(item.getMealSerialNumber()).getQuantity() - item.getQuantity());
            else
                result = false;
        }
        if (result)
            deleteCart(c.getCartSN());

        return result;
    }

    public void close() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
