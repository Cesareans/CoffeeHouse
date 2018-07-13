package SQLGenerator;

import Database.DBMenu;
import Database.DBUser;
import DebugUtil.Debug;
import Entity.Menu;
import Entity.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class OrdersGenerator {
    static Random rd = new Random();
    static final String zeroString = "000000000";

    public static void main(String[] args) {
        DBUser dbUser = new DBUser();
        ArrayList<User> users = dbUser.getAllUsers();
        int userCount = users.size();

        DBMenu dbMenu = new DBMenu();
        ArrayList<Menu> menus = dbMenu.getAllmenu();
        int menuCount = menus.size();

        for (int i = 1; i < 100; ++i) {
            String orderSN = zeroString.substring(0, 6 - ("" + i).length()) + i;
            String userTel = users.get(rd.nextInt(userCount)).getTel();
            String orderDate = generatDate();

            List<Integer> mealIndexes = generateUniqueRandomArray(menuCount , rd.nextInt(8) + 1);
            for (int mealIndex : mealIndexes) {
                Menu menu = menus.get(mealIndex);
                String mealSerialNumber = menu.getSerialNumber();
                String mealName = menu.getName();
                double mealPrice = menu.getPrice();
                int qty = rd.nextInt(3) + 1;

                System.out.println(generateSQL(orderSN,userTel,mealSerialNumber,mealName , mealPrice,qty,orderDate));
            }
        }
        dbUser.close();
    }

    private static String generateSQL(String orderSN, String userTel, String mealSerialNumber, String mealName, double mealPrice, int qty, String orderDate) {
        return "insert into orders values('" + orderSN + "','" + userTel + "','" + mealSerialNumber + "','" + mealName + "'," + mealPrice + ',' + qty + ",'" + orderDate + "');";
    }

    static int year = 2018;
    static int[] month = {6, 7, 8, 9};

    private static String generatDate() {
        int day = (rd.nextInt(30) + 1);
        return year + "-0" + month[rd.nextInt(month.length)] + "-" + zeroString.substring(0 , 2 - ("" + day).length()) + day;
    }

    private static List<Integer> generateUniqueRandomArray(int bound , int count){
        if(count > bound)
            return null;
        LinkedList<Integer> ints = new LinkedList<>();
        for(int i = 0 ; i < bound ; ++i)
            ints.push(i);
        LinkedList<Integer> ret = new LinkedList<>();
        for(int i = 0 ; i < count ; ++i){
            int index = rd.nextInt(ints.size());
            ret.push(ints.get(index));
            ints.remove(index);
        }
        return ret;
    }
}
