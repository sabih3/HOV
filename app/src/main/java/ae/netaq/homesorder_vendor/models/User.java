package ae.netaq.homesorder_vendor.models;

import android.net.Uri;

import java.util.ArrayList;

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
    private String userToken;
    private String profileImagePath;
    private String logoString;
    private String logoURL;
    private Country uaeRegionAreas;
    private Country ksaRegion;

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

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }


    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    public void setLogoString(String logoString) {
        this.logoString = logoString;
    }

    public String getLogoString() {
        return logoString;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public void setUAERegion(Country uaeRegionAreas) {
        this.uaeRegionAreas = uaeRegionAreas;
    }

    public Country getUAERegionAreas() {
        return uaeRegionAreas;
    }

    public void setKSARegion(Country ksaRegion) {
        this.ksaRegion = ksaRegion;
    }

    public Country getKsaRegion() {
        return ksaRegion;
    }
}
