package ae.netaq.homesorder_vendor.db.data_manager.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by sabih on 05-Dec-17.
 */
@DatabaseTable(tableName = OrderedProductsTable.columnNames.TABLE_NAME)
public class OrderedProductsTable {

    @DatabaseField(columnName = columnNames.ORDER_ID)
    private long orderID;

    @DatabaseField(columnName = columnNames.PRODUCT_ID)
    private long productID;

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public interface columnNames{
        String TABLE_NAME = "ordered_products";
        String ID = "id";
        String ORDER_ID = "order_id";
        String PRODUCT_ID = "product_id";

    }
}
