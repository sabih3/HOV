package ae.netaq.homesorder_vendor.network.model;

import com.google.gson.annotations.SerializedName;

import ae.netaq.homesorder_vendor.network.model.GeneralResponse;

/**
 * Created by sabih on 24-Dec-17.
 */


//Will be consumed by Register and Login Responses both
public class AuthenticationResponse extends GeneralResponse{

    private String token;
    private Profile profile;

    public String getToken() {
        return token;
    }

    public Profile getProfile() {
        return profile;
    }

    public class Profile {

        private String username;
        private String email;
        private String image;

        @SerializedName("vendorname")
        private String vendorName;

        @SerializedName("phone_no")
        private String phoneNumber;


        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        public String getImage() {
            return image;
        }

        public String getVendorName() {
            return vendorName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }
    }
}
