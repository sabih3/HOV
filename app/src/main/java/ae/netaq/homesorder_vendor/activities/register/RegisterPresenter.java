package ae.netaq.homesorder_vendor.activities.register;

import android.content.Context;

import ae.netaq.homesorder_vendor.models.User;
import ae.netaq.homesorder_vendor.network.core.ErrorUtils;
import ae.netaq.homesorder_vendor.network.core.ResponseCodes;
import ae.netaq.homesorder_vendor.network.core.RestClient;
import ae.netaq.homesorder_vendor.network.model.APIError;
import ae.netaq.homesorder_vendor.network.model.GeneralResponse;
import ae.netaq.homesorder_vendor.network.model.UserToRegister;
import ae.netaq.homesorder_vendor.utils.ErrorResolver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Netaq on 12/17/2017.
 */

public class RegisterPresenter {

    private final RegisterView viewListener;
    private final Context mContext;

    public RegisterPresenter(Context context, RegisterView registerView) {
        this.mContext = context;
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

        Call<AuthenticationResponse> registerRequest = RestClient.getAdapter().registerUser(user);

        registerRequest.enqueue(new Callback<AuthenticationResponse>() {
            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                if(response.body() != null){
                    //No App Server exception
                    if(response.body().getCode()== ResponseCodes.SUCCESS){
                        String token = response.body().getToken();
                        viewListener.onRegistrationSuccess();
                    }
                }

                if(response.errorBody() !=null){
                    // App Server Exception
                    APIError apiError = ErrorUtils.parseError(response);
                    int code = apiError.getCode();
                    switch (code){

                        case 1001:
                            String localizedError = ErrorResolver.getLocalizedError(mContext,
                                    code);
                            viewListener.onEmailTaken(localizedError);
                        break;

                        case 1002:
                            String localizedError2 = ErrorResolver.getLocalizedError(mContext,
                                    code);
                            viewListener.onVendorNameTaken(localizedError2);
                        break;

                        default:
                            String localizedError3 = ErrorResolver.getLocalizedError(mContext,
                                    code);
                            viewListener.onUnDefinedException(localizedError3);
                        break;
                    }


                }

            }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                    //TODO: Handle Network failure here
                    viewListener.onNetworkFailure();
            }
        });


    }
}
