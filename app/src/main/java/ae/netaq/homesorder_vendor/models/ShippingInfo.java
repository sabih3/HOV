package ae.netaq.homesorder_vendor.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by sabih on 05-Dec-17.
 */

public class ShippingInfo {

    private long id;

    private long orderID;

    private long customerID;

    private String address;

    private String phone;

    private String shippingNotes;


    private boolean isPrimary;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public long getOrderID() {
        return orderID;
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

}

