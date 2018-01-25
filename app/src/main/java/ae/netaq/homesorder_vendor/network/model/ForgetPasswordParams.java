package ae.netaq.homesorder_vendor.network.model;

public class ForgetPasswordParams {

    public String email;

    public ForgetPasswordParams(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
