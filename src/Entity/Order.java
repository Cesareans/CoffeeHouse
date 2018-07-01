package Entity;

public class Order {
    private String orderSN;
    private String user;
    private String mealSerialNumber;
    private String mealName;
    private double mealPrice;
    private int qty;
    private String date;

    public Order()
    {
        orderSN="";
        user="";
        mealSerialNumber="";
        mealName="";
        mealPrice=0;
        qty=0;
        date="";
    }

    public Order(String orderSN,String user, String mealSerialNumber, String mealName,double mealPrice,String date, int qty)
    {
        this.orderSN=orderSN;
        this.user=user;
        this.mealSerialNumber=mealSerialNumber;
        this.mealPrice=mealPrice;
        this.mealName=mealName;
        this.date=date;
        this.qty=qty;
    }

    public void setOrderSN(String orderSN) {
        this.orderSN = orderSN;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public void setMealPrice(double mealPrice) {
        this.mealPrice = mealPrice;
    }

    public void setMealSerialNumber(String mealSerialNumber) {
        this.mealSerialNumber = mealSerialNumber;
    }

    public void setQty(int qty) {
        this.qty = qty;
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

    public int getQty() {
        return qty;
    }

    public String getDate() {
        return date;
    }

    public String getOrderSN() {
        return orderSN;
    }

    public String getMealName() {
        return mealName;
    }

    public double getMealPrice() {
        return mealPrice;
    }
}
