package Entity;

import java.util.ArrayList;

public class Order {
    private ArrayList<OrderItem> orderlist = new ArrayList<OrderItem>();
    private String orderSN;
    private String date;
    private String user;

    public void setOrderlist(ArrayList<OrderItem> orderlist) {
        this.orderlist = orderlist;
    }

    public void setOrderSN(String orderSN) {
        this.orderSN = orderSN;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<OrderItem> getOrderlist() {
        return orderlist;
    }

    public String getOrderSN() {
        return orderSN;
    }

    public String getUser() {
        return user;
    }

    public String getDate() {
        return date;
    }

    public void addItem(OrderItem item)
    {
        orderlist.add(item);
    }

    public void displayOrder()
    {
        System.out.println(orderSN+"  "+user+"  "+date);
        for(OrderItem u:orderlist)
        {
            System.out.printf("      %-14s%-14s%-14f%-14d\n",u.getMealSerialNumber(),u.getMealName(),u.getMealPrice(),u.getQuantity());
        }
    }
}
