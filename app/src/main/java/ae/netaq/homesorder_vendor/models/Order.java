package ae.netaq.homesorder_vendor.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sabih on 03-Dec-17.
 */

public class Order implements Serializable{

    private long id;

    public long orderID;

    public int orderStatus;

    public double orderTotal;

    public int payment_mode;

    public String orderDate;

    public String dueDate;

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

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }


}
