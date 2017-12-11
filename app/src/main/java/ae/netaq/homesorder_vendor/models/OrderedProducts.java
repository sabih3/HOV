package ae.netaq.homesorder_vendor.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by sabih on 05-Dec-17.
 */

public class OrderedProducts {

    private long orderID;

    private long productID;


    public long getOrderID() {
        return orderID;
    }

    public long getProductID() {
        return productID;
    }

}
