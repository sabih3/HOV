package ae.netaq.homesorder_vendor.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sabih on 21-Dec-17.
 */

public class UserToRegister {

    private String email;
    private String username;
    private String password;
    private String phone;
    private String vendorname;
    private String deviceid;

    @SerializedName("profile")
    private String profileImage;

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
        this.vendorname = vendorName;
    }

    public void setDevideID(String devideID) {
        this.deviceid = devideID;
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
        return vendorname;
    }

    public String getDevideID() {
        return deviceid;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
