package Database;

import java.sql.*;
import java.util.ArrayList;
import Entity.*;
public class DBMenu {
    private static String url = "com.mysql.jdbc.Driver"; //加载驱动包
    private String connectSql = "jdbc:mysql://127.0.0.1:3306/caffe"; //链接MySQL数据库
    private String sqlmenu = "root"; //数据库账号
    private String sqlPasswd = "admin"; //你的数据库密码
    private Connection con = null;
    private PreparedStatement psm = null;
    private ResultSet rs = null;

    static{
        try {
            Class.forName(url);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public DBMenu()
    {
        //连接MYSQL
        try {
            con = DriverManager.getConnection(connectSql,sqlmenu,sqlPasswd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Menu> getAllmenu(){
        ArrayList<Menu> menulist = new ArrayList<Menu>();
        try {
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
            rs.close();
            psm.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return menulist;
    }

    public ArrayList<Menu> getMenuByName(String name){
        ArrayList<Menu> menulist = new ArrayList<Menu>();
        try {
            psm = con.prepareStatement("select * from menu where mname like '%"+name+"%' or mname like "+"'"+name+"%' or mname like '%"+name+"'");
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
            rs.close();
            psm.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return menulist;
    }

    public Menu getMenuBySN(String SN){
        Menu u = new Menu();
        try {
            psm = con.prepareStatement("select * from menu where SN="+"'"+SN+"'");
            rs = psm.executeQuery();

            while(rs.next()){

                u.setSerialNumber(rs.getString(1));
                u.setName(rs.getString(2));
                u.setPrice(rs.getDouble(3));
                u.setQuantity(rs.getInt(4));
                u.setType(rs.getString(5));
                u.setSales(rs.getInt(6));
                u.setPictureUrl(rs.getString(7));
            }
            rs.close();
            psm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    public String getNewSN()
    {
        ArrayList<Menu> menulist = getAllmenu();
        int SN=0;
        for(int i=0;i<menulist.size();i++)
        {
            if(SN<Integer.valueOf(menulist.get(i).getSerialNumber()))
                SN=Integer.valueOf(menulist.get(i).getSerialNumber());
        }
        SN++;
        String sn=String.format("%04d",SN);
        return sn;

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
        }
        return result;
    }

    public boolean insertNewmenu(Menu menu)
    {
        boolean result =false;
        if(menu.getSerialNumber().equals(""))
            return false;
        try {
            String sqlInset = "insert into menu(serialnumber,mname,price,qty,mtype,picture,sales) values(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sqlInset);
            stmt.setString(1, menu.getSerialNumber());
            stmt.setString(2, menu.getName());
            stmt.setDouble(3, menu.getPrice());
            stmt.setInt(4, menu.getQuantity());
            stmt.setString(5, menu.getType());
            stmt.setString(6,menu.getPictureUrl());
            stmt.setInt(7, 0);
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

    public boolean deleteMenu(String serial)
    {
        boolean result =false;
        try {
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
        }
        return result;
    }

    public boolean updateMenu(Menu m)
    {
        boolean result =true;
        updateMenuName(m.getSerialNumber(),m.getName());
        updateMenuType(m.getSerialNumber(),m.getType());
        updateMenuQty(m.getSerialNumber(),m.getQuantity());
        updateMenuPrice(m.getSerialNumber(),m.getPrice());
        updateMenuPictureUrl(m.getSerialNumber(),m.getPictureUrl());
        updateMenuSales(m.getSerialNumber(),m.getSales());
        return result;
    }

    public boolean updateSerial(String oldserial,String newserial)
    {
        boolean result =false;
        try {
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
        }
        return result;
    }

    public boolean updateMenuName(String serial,String newname)
    {
        boolean result =false;
        try {
            String sql = "update menu set mname="+"'"+newname+"'"+" where serialnumber="+"'"+serial+"'";
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

    public boolean updateMenuPrice(String serial,double newprice)
    {
        boolean result =false;
        try {
            String sql = "update menu set price="+"'"+newprice+"'"+" where serialnumber="+"'"+serial+"'";
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

    public boolean updateMenuQty(String serial,int newqty)
    {
        boolean result =false;
        try {

            String sql = "update menu set qty="+"'"+newqty+"'"+" where serialnumber="+"'"+serial+"'";
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

    public boolean updateMenuSales(String serial,int newsale)
    {
        boolean result =false;
        try {
            String sql = "update menu set sales="+"'"+newsale+"'"+" where serialnumber="+"'"+serial+"'";
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

    public boolean updateMenuType(String serial,String newtype)
    {
        boolean result =false;
        try {

            String sql = "update menu set mtype="+"'"+newtype+"'"+" where serialnumber="+"'"+serial+"'";
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

    public boolean updateMenuPictureUrl(String serial,String newUrl)
    {
        boolean result =false;
        try {

            String sql = "update menu set picture="+"'"+newUrl+"'"+" where serialnumber="+"'"+serial+"'";
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

    public ArrayList<Menu> getMenuByType(String type){
        ArrayList<Menu> menulist = new ArrayList<Menu>();
        try {
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
            rs.close();
            psm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menulist;
    }

    public Menu getMeal(String mealSN)
    {
        Menu u = new Menu();
        try {
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
            rs.close();
            psm.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
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
