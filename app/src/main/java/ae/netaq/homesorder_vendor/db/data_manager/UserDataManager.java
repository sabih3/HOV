package ae.netaq.homesorder_vendor.db.data_manager;

import ae.netaq.homesorder_vendor.activities.register.AuthenticationResponse;
import ae.netaq.homesorder_vendor.models.User;
import ae.netaq.homesorder_vendor.utils.DevicePreferences;
import retrofit2.Response;

/**
 * Created by sabih on 27-Dec-17.
 */

public class UserDataManager {


    public static void persistUser(Response<AuthenticationResponse> response) {
        String token = response.body().getToken();
        String personName = response.body().getProfile().getUsername();
        String email = response.body().getProfile().getEmail();
        String image = response.body().getProfile().getImage();
        String phoneNumber = response.body().getProfile().getPhoneNumber();
        String vendorName = response.body().getProfile().getVendorName();


        User.getInstance().setPersonName(personName);
        User.getInstance().setUserEmail(email);
        User.getInstance().setLogoURL(image);
        User.getInstance().setUserPhone(phoneNumber);
        User.getInstance().setVendorName(vendorName);
        User.getInstance().setUserToken(token);

        DevicePreferences.getInstance().saveUserInfo(User.getInstance());
    }

    public static void clearUserData() {
        DevicePreferences.getInstance().saveUserInfo(null);
    }
}
