package ae.netaq.homesorder_vendor.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

/**
 * Created by sabih on 03-Dec-17.
 */

@DatabaseTable(tableName = Order.ColumnNames.TABLE_NAME)
public class Order {

    @DatabaseField(id = true,unique = true,columnName = ColumnNames.ID)
    public long orderID;

    @DatabaseField(columnName = ColumnNames.ORDER_STATUS)
    public int orderStatus;

    @DatabaseField(columnName = ColumnNames.ORDER_AMOUNT)
    public double orderTotal;

    @DatabaseField(columnName = ColumnNames.PAYMENT_MODE)
    public int payment_mode;

    @DatabaseField(columnName = ColumnNames.ORDER_DATE)
    public String orderDate;

    @DatabaseField(columnName = ColumnNames.DUE_DATE)
    public String dueDate;

    @DatabaseField(columnName = ColumnNames.COMMENTS)
    public String comments;

    public Customer customer;
    public ArrayList<Product> products;

    public long getOrderID() {
        return orderID;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public int getPayment_mode() {
        return payment_mode;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getComments() {
        return comments;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public interface ColumnNames{
        String TABLE_NAME = "orders";
        String ID = "id";
        String ORDER_STATUS = "status";
        String ORDER_AMOUNT = "amount";
        String PAYMENT_MODE = "payment_mode";
        String ORDER_DATE = "order_date";
        String DUE_DATE = "due_date";
        String COMMENTS = "comments";


    }
}
