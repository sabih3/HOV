package ae.netaq.homesorder_vendor.activities.sign_in;

import android.content.Context;

import ae.netaq.homesorder_vendor.activities.register.AuthenticationResponse;
import ae.netaq.homesorder_vendor.db.data_manager.UserDataManager;
import ae.netaq.homesorder_vendor.network.core.ErrorUtils;
import ae.netaq.homesorder_vendor.network.core.ResponseCodes;
import ae.netaq.homesorder_vendor.network.core.RestClient;
import ae.netaq.homesorder_vendor.network.model.APIError;
import ae.netaq.homesorder_vendor.network.model.GeneralResponse;
import ae.netaq.homesorder_vendor.network.model.Login;
import ae.netaq.homesorder_vendor.utils.ErrorResolver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Netaq on 12/18/2017.
 */

public class SignInPresenter {

    private final Context mContext;
    private SignInView viewListener;

    public SignInPresenter(Context context,SignInView signInView) {
        this.mContext = context;
        this.viewListener = signInView;
    }


    /** This method logs the user in and persist user
     *
     * @param userName
     * @param password
     */
    public void requestLogin(String userName, String password) {
        Login login = new Login();
        login.setUsername(userName);
        login.setPassword(password);

        Call<AuthenticationResponse> loginRequest = RestClient.getAdapter().loginUser(login);

        loginRequest.enqueue(new Callback<AuthenticationResponse>() {
            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                if(response.body() != null){
                    //No App Server exception
                    if(response.body().getCode()== ResponseCodes.SUCCESS){
                        UserDataManager.persistUser(response);
                        viewListener.onLoggedIn();
                    }
                }

                if(response.errorBody() !=null){
                    // App Server Exception
                    APIError apiError = ErrorUtils.parseError(response);
                    int code = apiError.getCode();

                    switch (code){

                        case 2001:
                            String localizedError = ErrorResolver.getLocalizedError(mContext, code);
                            viewListener.onLoginFailure(localizedError);
                        break;

                        default:
                            String localizedError1 = ErrorResolver.getLocalizedError(mContext, code);
                            viewListener.onUnDefinedException(localizedError1);
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


    public void requestForgetPassword(){
        
        RestClient.getAdapter().forgetPwd("");
    }
}
