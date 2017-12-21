package ae.netaq.homesorder_vendor.network.model;

/**
 * Created by sabih on 21-Dec-17.
 */

public class UserToRegister {

    private String email;
    private String username;
    private String password;
    private String phone;
    private String vendorName;
    private String device_id;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.username = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public void setDevideID(String devideID) {
        this.device_id = devideID;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getVendorName() {
        return vendorName;
    }

    public String getDevideID() {
        return device_id;
    }
}
