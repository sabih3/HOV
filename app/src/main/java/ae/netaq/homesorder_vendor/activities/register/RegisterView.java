package ae.netaq.homesorder_vendor.activities.register;

import ae.netaq.homesorder_vendor.activities.GeneralView;

/**
 * Created by Netaq on 12/17/2017.
 */

public interface RegisterView extends GeneralView {
    void onRegistrationSuccess(String token);
    void onEmailTaken(String localizedError);
    void onVendorNameTaken(String localizedError);


}
