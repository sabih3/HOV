package ae.netaq.homesorder_vendor.db.data_manager.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;



/**
 * Created by sabih on 05-Dec-17.
 */

@DatabaseTable(tableName = ShippingInfoTable.ColumnNames.TABLENAME)
public class ShippingInfoTable {

    @DatabaseField(id = true,unique = true)
    private long id;

    @DatabaseField(columnName = ColumnNames.COLUMN_ORDER_ID)
    private long orderID;

    @DatabaseField(columnName = ColumnNames.COLUMN_CUSTOMER_ID)
    private long customerID;

    @DatabaseField(columnName = ColumnNames.COLUMN_ADDRESS)
    private String address;

    @DatabaseField(columnName = ColumnNames.COLUMN_PHONE)
    private String phone;

    @DatabaseField(columnName = ColumnNames.COLUMN_SHIPPING_NOTES)
    private String shippingNotes;

    @DatabaseField(columnName = ColumnNames.COLUMN_IS_PRIMARY)
    private boolean isPrimary;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShippingNotes() {
        return shippingNotes;
    }

    public void setShippingNotes(String shippingNotes) {
        this.shippingNotes = shippingNotes;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public interface ColumnNames{


        String TABLENAME = "shipping_info";
        String COLUMN_ORDER_ID = "order_id";
        String COLUMN_CUSTOMER_ID = "customer_id";
        String COLUMN_ADDRESS = "address";
        String COLUMN_PHONE = "phone";
        String COLUMN_SHIPPING_NOTES = "notes";
        String COLUMN_IS_PRIMARY="is_primary";
    }
}
