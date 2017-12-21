package ae.netaq.homesorder_vendor.models;

import android.net.Uri;

/**
 * Created by Netaq on 12/20/2017.
 */

public class User {

    private static User user;
    private String userEmail;
    private String userPassword;
    private String userPhone;
    private String vendorName;
    private String personName;
    private Uri logoUri;

    public static User getInstance( ) {
        if(user == null){
            user = new User();
        }
        return user;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Uri getLogoUri() {
        return logoUri;
    }

    public void setLogoUri(Uri logoUri) {
        this.logoUri = logoUri;
    }
}
