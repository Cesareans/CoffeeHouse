package Entity;

public class Cart {
    private String mealSerialNumber;
    private String orderSN;
    private String date;
    private int qty;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setOrderSN(String orderSN) {
        this.orderSN = orderSN;
    }

    public String getOrderSN() {
        return orderSN;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getQty() {
        return qty;
    }

    public void setMealSerialNumber(String mealSerialNumber) {
        this.mealSerialNumber = mealSerialNumber;
    }

    public String getMealSerialNumber() {
        return mealSerialNumber;
    }
}
