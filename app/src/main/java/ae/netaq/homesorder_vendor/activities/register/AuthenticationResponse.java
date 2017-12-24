package ae.netaq.homesorder_vendor.activities.register;

import ae.netaq.homesorder_vendor.network.model.GeneralResponse;

/**
 * Created by sabih on 24-Dec-17.
 */


//Will be consumed by Register and Login Responses both
public class AuthenticationResponse extends GeneralResponse{

    private String token;

    public String getToken() {
        return token;
    }
}
