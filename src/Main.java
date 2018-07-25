import Database.DBCart;
import Database.DBOrder;

public class Main {
    public static void main(String[] args) {
        DBOrder o = new DBOrder();
        DBCart c = new DBCart();
        String sn = c.getNewSN();
            sn = o.getNewSN();

        System.out.println(sn);
    }
}
