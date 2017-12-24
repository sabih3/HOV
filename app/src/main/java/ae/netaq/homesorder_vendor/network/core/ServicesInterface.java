package ae.netaq.homesorder_vendor.network.core;

import ae.netaq.homesorder_vendor.network.model.GeneralResponse;
import ae.netaq.homesorder_vendor.network.model.Login;
import ae.netaq.homesorder_vendor.network.model.UserToRegister;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by sabih on 21-Dec-17.
 * sabih.ahmed@netaq.ae
 */

public interface ServicesInterface {


    @POST(Endpoints.USER_REGISTER)
    Call<GeneralResponse> registerUser(@Body UserToRegister user);

    @POST(Endpoints.USER_LOGIN)
    Call<GeneralResponse> loginUser(@Body Login login);
}
