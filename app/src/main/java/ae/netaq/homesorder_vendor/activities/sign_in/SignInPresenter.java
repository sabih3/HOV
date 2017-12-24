package ae.netaq.homesorder_vendor.activities.sign_in;

import ae.netaq.homesorder_vendor.network.core.ResponseCodes;
import ae.netaq.homesorder_vendor.network.core.RestClient;
import ae.netaq.homesorder_vendor.network.model.GeneralResponse;
import ae.netaq.homesorder_vendor.network.model.Login;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Netaq on 12/18/2017.
 */

public class SignInPresenter {

    private SignInView viewListener;

    public SignInPresenter(SignInView signInView) {
        this.viewListener = signInView;
    }

    public void requestLogin(String userName, String password) {
        Login login = new Login();
        login.setUsername(userName);
        login.setPassword(password);

        Call<GeneralResponse> loginRequest = RestClient.getAdapter().loginUser(login);

        loginRequest.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                if(response.body() != null){
                    //No App Server exception
                    if(response.body().getCode()== ResponseCodes.SUCCESS){

                        viewListener.onLoggedIn();
                    }
                }

                if(response.errorBody() !=null){
                    // App Server Exception
                    //TODO: exception message has to be passed here
                    viewListener.onLoginFailure("Loggin in failed due to any issue");
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {

            }
        });
    }
}
