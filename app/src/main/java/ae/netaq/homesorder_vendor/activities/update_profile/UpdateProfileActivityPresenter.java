package ae.netaq.homesorder_vendor.activities.update_profile;

import android.content.Context;

import ae.netaq.homesorder_vendor.network.model.AuthenticationResponse;
import ae.netaq.homesorder_vendor.db.data_manager.UserDataManager;
import ae.netaq.homesorder_vendor.network.core.ResponseCodes;
import ae.netaq.homesorder_vendor.network.core.RestClient;
import ae.netaq.homesorder_vendor.network.model.NetworkUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Netaq on 12/20/2017.
 */

public class UpdateProfileActivityPresenter {

    private final Context mContext;
    private UpdateProfileActivityView viewListener;

    public UpdateProfileActivityPresenter(Context mContext, UpdateProfileActivityView viewListener) {
        this.mContext = mContext;
        this.viewListener = viewListener;
    }

    public void requestUpdateUser(NetworkUser user, String userToken){

        Call<AuthenticationResponse> userUpdateRequest = RestClient.getAdapter().userUpdate(user, userToken);

        userUpdateRequest.enqueue(new Callback<AuthenticationResponse>() {
            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                if(response.body() != null){
                    //No App Server exception
                    if(response.body().getCode()== ResponseCodes.SUCCESS) {
                        UserDataManager.persistUpdatedUser(response);
                        viewListener.onProfileUpdated();
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                if(t instanceof IllegalArgumentException){
                    viewListener.onProfileUpdateFailure("Something Went Wrong!");
                }else {
                    viewListener.onNetworkFailure();
                }
            }
        });
    }
}
