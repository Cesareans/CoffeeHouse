import Database.DBCart;
import Database.DBOrder;
import com.alibaba.fastjson.JSON;

public class Main {
    public static void main(String[] args) {
        DBCart dbCart = new DBCart();

        System.out.println(JSON.toJSONString(dbCart.getUserCart("18850516371") , true));
    }
}
