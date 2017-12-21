package ae.netaq.homesorder_vendor.activities.register;

import ae.netaq.homesorder_vendor.models.User;
import ae.netaq.homesorder_vendor.network.core.ResponseCodes;
import ae.netaq.homesorder_vendor.network.core.RestClient;
import ae.netaq.homesorder_vendor.network.model.GeneralResponse;
import ae.netaq.homesorder_vendor.network.model.UserToRegister;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Netaq on 12/17/2017.
 */

public class RegisterPresenter {

    private final RegisterView viewListener;

    public RegisterPresenter(RegisterView registerView) {

        this.viewListener = registerView;
    }

    public void registerUser() {
        UserToRegister user = new UserToRegister();
        user.setEmail(User.getInstance().getUserEmail());
        user.setName(User.getInstance().getPersonName());
        user.setPassword(User.getInstance().getUserPassword());
        user.setPhone(User.getInstance().getUserPhone());
        user.setVendorName(User.getInstance().getVendorName());
        user.setDevideID("1212");

        Call<GeneralResponse> registerRequest = RestClient.getAdapter().registerUser(user);

        registerRequest.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                if(response.body() != null){
                    //No App Server exception
                    if(response.body().getCode()== ResponseCodes.SUCCESS){

                    }
                }

                if(response.errorBody() !=null){
                    // App Server Exception
                }

            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {

            }
        });


    }
}
