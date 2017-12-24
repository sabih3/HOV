package ae.netaq.homesorder_vendor.activities.register;

import ae.netaq.homesorder_vendor.activities.GeneralView;
import ae.netaq.homesorder_vendor.interfaces.GeneralNetworkListener;

/**
 * Created by Netaq on 12/17/2017.
 */

public interface RegisterView extends GeneralView {
    void onRegistrationSuccess();
    void onEmailTaken(String localizedError);
    void onVendorNameTaken(String localizedError);


}
