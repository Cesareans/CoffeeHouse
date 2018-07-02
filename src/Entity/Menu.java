package Entity;
public class Menu {
    private String serialNumber;
    private String name;
    private double price;
    private String type;
    private int qty;
    private int sales;
    private String pictureUrl;

    public Menu()
    {
        serialNumber="";
        name="";
        price=0;
        type="";
        qty=0;
        sales=0;
        pictureUrl="";
    }

    public Menu(String serialNumber,String name,double price,String type,int qty,int sales,String pictureUrl)
    {
        this.serialNumber=serialNumber;
        this.name = name;
        this.price = price;
        this.qty=qty;
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

    public void setQty(int qty) {
        this.qty = qty;
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

    public int getQty() {
        return qty;
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
