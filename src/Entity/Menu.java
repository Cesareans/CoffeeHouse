package Entity;

public class Menu {
    private String serialNumber;
    private String name;
    private double price;
    private String type;
    private int quantity;
    private int sales;
    private String pictureUrl;

    public Menu()
    {
        serialNumber="";
        name="";
        price=0;
        type="";
        quantity =0;
        sales=0;
        pictureUrl="";
    }

    public Menu(String serialNumber, String name, double price, String type, int quantity, int sales, String pictureUrl)
    {
        this.serialNumber=serialNumber;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.sales=sales;
        this.pictureUrl=pictureUrl;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSales() {
        return sales;
    }

    public String getType() {
        return type;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }
}
