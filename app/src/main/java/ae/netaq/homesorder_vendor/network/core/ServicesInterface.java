package ae.netaq.homesorder_vendor.network.core;

import ae.netaq.homesorder_vendor.activities.register.AuthenticationResponse;
import ae.netaq.homesorder_vendor.network.model.GeneralResponse;
import ae.netaq.homesorder_vendor.network.model.Login;
import ae.netaq.homesorder_vendor.network.model.RemoteProduct;
import ae.netaq.homesorder_vendor.network.model.ResponseAddProduct;
import ae.netaq.homesorder_vendor.network.model.UserToRegister;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by sabih on 21-Dec-17.
 * sabih.ahmed@netaq.ae
 */

public interface ServicesInterface {


    @POST(Endpoints.USER_REGISTER)
    Call<AuthenticationResponse> registerUser(@Body UserToRegister user);

    @POST(Endpoints.USER_LOGIN)
    Call<AuthenticationResponse> loginUser(@Body Login login);

    @POST(Endpoints.PRODUCT_ADD)
    Call<ResponseAddProduct> productAdd(@Body RemoteProduct remoteProduct,
                                        @Path(value = "userToken") String token);

    @POST(Endpoints.PRODUCT_UPDATE)
    Call<ResponseAddProduct> productUpdate(@Body RemoteProduct product,
                                           @Path(value = "userToken") String token);

    
}
