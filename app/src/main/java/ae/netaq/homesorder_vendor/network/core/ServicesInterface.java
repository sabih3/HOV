package ae.netaq.homesorder_vendor.network.core;

import java.util.List;

import ae.netaq.homesorder_vendor.network.model.AuthenticationResponse;
import ae.netaq.homesorder_vendor.network.model.ForgetPasswordParams;
import ae.netaq.homesorder_vendor.network.model.GeneralResponse;
import java.util.ArrayList;
import java.util.List;

import ae.netaq.homesorder_vendor.activities.register.AuthenticationResponse;
import ae.netaq.homesorder_vendor.network.model.Login;
import ae.netaq.homesorder_vendor.network.model.RemoteProduct;
import ae.netaq.homesorder_vendor.network.model.ResponseAddProduct;
import ae.netaq.homesorder_vendor.network.model.NetworkUser;
import ae.netaq.homesorder_vendor.network.model.ResponseOrderList;
import ae.netaq.homesorder_vendor.network.model.ResponseProductList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by sabih on 21-Dec-17.
 * sabih.ahmed@netaq.ae
 */

public interface ServicesInterface {


    @POST(Endpoints.USER_REGISTER)
    Call<AuthenticationResponse> registerUser(@Body NetworkUser user);

    @POST(Endpoints.USER_LOGIN)
    Call<AuthenticationResponse> loginUser(@Body Login login);

    @POST(Endpoints.PRODUCT_ADD)
    Call<ResponseAddProduct> productAdd(@Body RemoteProduct remoteProduct,
                                        @Path(value = "userToken") String token);

    @POST(Endpoints.PRODUCT_UPDATE)
    Call<ResponseAddProduct> productUpdate(@Body RemoteProduct product,
                                           @Path(value = "userToken") String token);

    @POST(Endpoints.USER_PROFILE_UPDATE)
    Call<AuthenticationResponse> userUpdate(@Body NetworkUser user,
                                           @Path(value = "userToken") String token);

    @POST(Endpoints.USER_FORGET_PWD)
    Call<GeneralResponse> forgetPwd(@Body ForgetPasswordParams params);

    @GET(Endpoints.PRODUCT_LIST)
    Call<ResponseProductList> getProductList(@Path(value = "userToken") String token);

    @GET(Endpoints.ORDER_LIST_NEW)
    Call<List<ResponseOrderList>> getNewOrders(@Path(value = "userToken")String token);

}
