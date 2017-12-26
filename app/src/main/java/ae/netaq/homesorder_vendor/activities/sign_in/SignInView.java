package ae.netaq.homesorder_vendor.activities.sign_in;

import ae.netaq.homesorder_vendor.activities.GeneralView;

/**
 * Created by Netaq on 12/18/2017.
 */

public interface SignInView extends GeneralView{

    void onLoggedIn(String token);
    void onLoginFailure(String failureMessage);
}
