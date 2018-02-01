package ae.netaq.homesorder_vendor.activities.sign_in;

import android.content.Context;

import ae.netaq.homesorder_vendor.network.model.AuthenticationResponse;
import ae.netaq.homesorder_vendor.db.data_manager.UserDataManager;
import ae.netaq.homesorder_vendor.network.core.ErrorUtils;
import ae.netaq.homesorder_vendor.network.core.ResponseCodes;
import ae.netaq.homesorder_vendor.network.core.RestClient;
import ae.netaq.homesorder_vendor.network.model.APIError;
import ae.netaq.homesorder_vendor.network.model.ForgetPasswordParams;
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
    private ForgetPasswordView forgetPasswordListener;

    public SignInPresenter(Context mContext, SignInView viewListener, ForgetPasswordView forgetPasswordListener) {
        this.mContext = mContext;
        this.viewListener = viewListener;
        this.forgetPasswordListener = forgetPasswordListener;
    }

    /** This method logs the user in and persist user
     *
     * @param userName
     * @param password
     */
    public void requestLogin(String userName, final String password) {
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
                        UserDataManager.persistUser(response, password);
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


    public void requestForgetPassword(ForgetPasswordParams params){

        Call<GeneralResponse> forgetPwdRequest = RestClient.getAdapter().forgetPwd(params);

        forgetPwdRequest.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                if(response.isSuccessful()){
                    if (response.body() != null) {
                        if(response.body().getCode() == 200){
                            forgetPasswordListener.onEmailSentSuccessfully();
                        }
                    }
                }else if(response.errorBody() !=null){
                    APIError apiError = ErrorUtils.parseError(response);
                    int code = apiError.getCode();

                    switch (code){

                        case 3002:
                            forgetPasswordListener.onEmailDoesNotExists();
                            break;

                        case 3001:
                            forgetPasswordListener.onLimitExceeded();
                            break;

                        default:
                            forgetPasswordListener.onForgetPasswordRequestFailure();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                forgetPasswordListener.onForgetPasswordRequestFailure();
            }
        });
    }
}
