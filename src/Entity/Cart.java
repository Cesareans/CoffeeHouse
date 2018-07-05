package Entity;

public class Cart {
    private String orderSN;
    private String user;
    private String mealSerialNumber;
    private int quantity;
    private String date;

    public Cart()
    {
        orderSN="";
        user="";
        mealSerialNumber="";
        quantity =0;
        date="";
    }

    public Cart(String orderSN, String user, String mealSerialNumber, String date, int quantity)
    {
        this.orderSN=orderSN;
        this.user=user;
        this.mealSerialNumber=mealSerialNumber;
        this.date=date;
        this.quantity = quantity;
    }

    public void setOrderSN(String orderSN) {
        this.orderSN = orderSN;
    }

    public void setMealSerialNumber(String mealSerialNumber) {
        this.mealSerialNumber = mealSerialNumber;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public String getMealSerialNumber() {
        return mealSerialNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDate() {
        return date;
    }

    public String getOrderSN() {
        return orderSN;
    }

}
