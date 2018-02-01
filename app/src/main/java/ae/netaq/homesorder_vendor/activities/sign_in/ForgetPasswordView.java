package ae.netaq.homesorder_vendor.activities.sign_in;

public interface ForgetPasswordView {

    void onEmailSentSuccessfully();
    void onLimitExceeded();
    void onForgetPasswordRequestFailure();
    void onEmailDoesNotExists();
}
