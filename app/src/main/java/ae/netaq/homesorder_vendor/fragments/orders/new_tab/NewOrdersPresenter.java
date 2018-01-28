package ae.netaq.homesorder_vendor.fragments.orders.new_tab;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import ae.netaq.homesorder_vendor.db.data_manager.OrderDataManager;
import ae.netaq.homesorder_vendor.db.data_manager.UserDataManager;
import ae.netaq.homesorder_vendor.db.data_manager.tables.OrderTable;
import ae.netaq.homesorder_vendor.models.Orders;
import ae.netaq.homesorder_vendor.network.core.RestClient;
import ae.netaq.homesorder_vendor.network.model.APIError;
import ae.netaq.homesorder_vendor.network.model.GeneralResponse;
import ae.netaq.homesorder_vendor.network.model.ResponseOrderList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Netaq on 11/22/2017.
 */

public class NewOrdersPresenter {

    private NewOrdersView newOrdersView;

    public NewOrdersPresenter(NewOrdersView newOrdersView) {
        this.newOrdersView = newOrdersView;
    }

    public void getNewOrdersList(Context context){


        Call<List<ResponseOrderList>> newOrdersRequest = RestClient.getAdapter().
                getNewOrders(UserDataManager.getPersistedUser().getUserToken());
        newOrdersView.showProgress();
        newOrdersRequest.enqueue(new Callback<List<ResponseOrderList>>() {
            @Override
            public void onResponse(Call<List<ResponseOrderList>> call, Response<List<ResponseOrderList>> response) {
                newOrdersView.hideProgress();
                if(response.isSuccessful()){
                    if(response.body().isEmpty()){

                        newOrdersView.showEmptyDataView();
                    }else{
                        newOrdersView.onNewOrdersFetched(response.body());
                    }
                }else{
                    //Todo (1): code 3001 for token expired exception
                    //Todo (2):Extract other codes and return them on view
                    //extract
                }
            }

            @Override
            public void onFailure(Call<List<ResponseOrderList>> call, Throwable t) {
                newOrdersView.hideProgress();

            }
        });


//        List<OrderTable> newOrdersList = null;
//        try {
//            newOrdersList = OrderDataManager.getNewOrdersList(context);
//            newOrdersView.onNewOrdersFetched(newOrdersList);
//        } catch (SQLException e) {
//            //Todo: handle exception here for view
//            e.printStackTrace();
//        }

    }

    public void updateOrderAsProcessing(long orderID) {
        Call<GeneralResponse> updateAsProcessingRequest = RestClient.getAdapter().updateOrderProcessing(orderID,
                UserDataManager.getPersistedUser().getUserToken());
        newOrdersView.showProgress();
        updateAsProcessingRequest.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                newOrdersView.hideProgress();
                if(response.isSuccessful()){
                    newOrdersView.onOrderUpdatedSuccessfully();
                }else{
                    String resolvedError = "Your order cannot be updated this time";
                    newOrdersView.onOrderUpdateException(resolvedError);
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                //TODO: handle network failure properly
                newOrdersView.hideProgress();
                newOrdersView.onNetworkFailure();
            }
        });
    }
}
