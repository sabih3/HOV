package ae.netaq.homesorder_vendor.activities.sign_in;

/**
 * Created by Netaq on 12/18/2017.
 */

public interface SignInView {

    void onLoggedIn();
    void onLoginFailure(String failureMessage);
}
