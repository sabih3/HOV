package ae.netaq.homesorder_vendor.activities.register;

import android.content.Context;

import ae.netaq.homesorder_vendor.db.data_manager.UserDataManager;
import ae.netaq.homesorder_vendor.network.core.ErrorUtils;
import ae.netaq.homesorder_vendor.network.core.ResponseCodes;
import ae.netaq.homesorder_vendor.network.core.RestClient;
import ae.netaq.homesorder_vendor.network.model.APIError;
import ae.netaq.homesorder_vendor.network.model.AuthenticationResponse;
import ae.netaq.homesorder_vendor.network.model.NetworkUser;
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

    public void registerUser(final NetworkUser user) {
//        NetworkUser user = new NetworkUser();
//        user.setEmail(NetworkUser.getInstance().getUserEmail());
//        user.setName(NetworkUser.getInstance().getPersonName());
//        user.setPassword(NetworkUser.getInstance().getUserPassword());
//        user.setPhone(NetworkUser.getInstance().getUserPhone());
//        user.setVendorName(NetworkUser.getInstance().getVendorName());
//        user.setDevideID("1212");
//        user.setProfileImage(NetworkUser.getInstance().getLogoString());


        Call<AuthenticationResponse> registerRequest = RestClient.getAdapter().registerUser(user);

        registerRequest.enqueue(new Callback<AuthenticationResponse>() {
            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                if(response.body() != null){
                    //No App Server exception
                    if(response.body().getCode()== ResponseCodes.SUCCESS){

                        UserDataManager.persistUser(response, user.getPassword());


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
