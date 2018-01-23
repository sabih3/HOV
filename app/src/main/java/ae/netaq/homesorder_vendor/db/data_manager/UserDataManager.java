package ae.netaq.homesorder_vendor.db.data_manager;

import ae.netaq.homesorder_vendor.activities.register.AuthenticationResponse;
import ae.netaq.homesorder_vendor.models.Country;
import ae.netaq.homesorder_vendor.models.User;
import ae.netaq.homesorder_vendor.utils.DevicePreferences;
import retrofit2.Response;

/**
 * Created by sabih on 27-Dec-17.
 */

public class UserDataManager {


    public static void persistUser(Response<AuthenticationResponse> response, String password) {
        String token = response.body().getToken();
        String personName = response.body().getProfile().getUsername();
        String email = response.body().getProfile().getEmail();
        String image = response.body().getProfile().getImage();
        String phoneNumber = response.body().getProfile().getPhoneNumber();
        String vendorName = response.body().getProfile().getVendorName();


        User.getInstance().setPersonName(personName);
        User.getInstance().setUserEmail(email);
        User.getInstance().setProfileImagePath(image);
        User.getInstance().setUserPhone(phoneNumber);
        User.getInstance().setVendorName(vendorName);
        User.getInstance().setUserToken(token);
        User.getInstance().setUserPassword(password);

        DevicePreferences.getInstance().saveUserInfo(User.getInstance());

    }

    public static void persistUpdatedUser(Response<AuthenticationResponse> response) {


        User.getInstance().setPersonName(response.body().getProfile().getUsername());
        User.getInstance().setUserPhone(response.body().getProfile().getPhoneNumber());
        User.getInstance().setProfileImagePath(response.body().getProfile().getImage());

        DevicePreferences.getInstance().saveUserInfo(User.getInstance());
    }


    public static User getPersistedUser(){
        User user = DevicePreferences.getInstance().getUserInfo();

        return user;
    }

    public static void clearUserData() {
        DevicePreferences.getInstance().saveUserInfo(null);
    }


    public static void persistUAERegion(Country selectedRegions) {
        User.getInstance().setUAERegion(selectedRegions);
        DevicePreferences.getInstance().saveUserInfo(User.getInstance());
    }


    public static Country getUAERegion() {
        Country selectedRegions = User.getInstance().getUAERegionAreas();
        return selectedRegions;
    }

    public static void persistKSARegion(Country ksaRegion) {
        User.getInstance().setKSARegion(ksaRegion);
        DevicePreferences.getInstance().saveUserInfo(User.getInstance());
    }

    public static Country getKSARegion() {
        Country selectedRegions = User.getInstance().getKsaRegion();
        return selectedRegions;
    }
}
