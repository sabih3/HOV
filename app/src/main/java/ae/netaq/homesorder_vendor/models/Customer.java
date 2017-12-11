package ae.netaq.homesorder_vendor.models;

import java.io.Serializable;


/**
 * Created by Sabih Ahmed on 03-Dec-17.
 * sabih.ahmed@netaq.ae
 */


public class Customer implements Serializable{


    public long customerID;

    public String name;

    public String email;

    public String address;

    private String phone;

    private String shippingNotes;


    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
