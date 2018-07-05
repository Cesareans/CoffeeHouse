package Entity;

public class OrderItem {
    private String mealSerialNumber;
    private String mealName;
    private double mealPrice;
    private int quantity;

    public OrderItem()
    {
        mealSerialNumber="";
        mealName="";
        mealPrice=0;
        quantity =0;
    }

    public OrderItem(String orderSN, String user, String mealSerialNumber, String mealName, double mealPrice, String date, int quantity)
    {
        this.mealSerialNumber=mealSerialNumber;
        this.mealPrice=mealPrice;
        this.mealName=mealName;
        this.quantity = quantity;
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMealSerialNumber() {
        return mealSerialNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getMealName() {
        return mealName;
    }

    public double getMealPrice() {
        return mealPrice;
    }
}
