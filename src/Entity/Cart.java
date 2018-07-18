package Entity;

import java.util.ArrayList;

public class Cart {
    private String userTel;
    private String cartSN;
    private ArrayList<OrderItem> orderlist = new ArrayList<OrderItem>();

    public void setOrderlist(ArrayList<OrderItem> orderlist) {
        this.orderlist = orderlist;
    }

    public void setCartSN(String cartSN) {
        this.cartSN = cartSN;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public ArrayList<OrderItem> getOrderlist() {
        return orderlist;
    }

    public String getCartSN() {
        return cartSN;
    }

    public String getUserTel() {
        return userTel;
    }


    public void addItem(OrderItem item)
    {
        orderlist.add(item);
    }

    public void displayOrder()
    {
        System.out.println(cartSN +"  "+ userTel +"  ");
        for(OrderItem u:orderlist)
        {
            System.out.printf("      %-14s%-14s%-14f%-14d\n",u.getMealSerialNumber(),u.getMealName(),u.getMealPrice(),u.getQuantity());
        }
    }


}
