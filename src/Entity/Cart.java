package Entity;

public class Cart {
    private String cartSN;
    private String user;
    private String mealSerialNumber;
    private int quantity;
    private String date;

    public Cart()
    {
        cartSN ="";
        user="";
        mealSerialNumber="";
        quantity =0;
        date="";
    }

    public Cart(String cartSN, String user, String mealSerialNumber, String date, int quantity)
    {
        this.cartSN = cartSN;
        this.user=user;
        this.mealSerialNumber=mealSerialNumber;
        this.date=date;
        this.quantity = quantity;
    }

    public void setCartSN(String cartSN) {
        this.cartSN = cartSN;
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

    public String getCartSN() {
        return cartSN;
    }

}
