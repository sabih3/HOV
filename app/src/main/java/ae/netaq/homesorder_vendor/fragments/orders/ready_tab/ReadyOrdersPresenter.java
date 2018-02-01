package ae.netaq.homesorder_vendor.fragments.orders.ready_tab;

import android.content.Context;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ae.netaq.homesorder_vendor.db.data_manager.OrderDataManager;
import ae.netaq.homesorder_vendor.db.data_manager.UserDataManager;
import ae.netaq.homesorder_vendor.db.tables.OrderTable;
import ae.netaq.homesorder_vendor.network.core.RestClient;
import ae.netaq.homesorder_vendor.network.model.GeneralResponse;
import ae.netaq.homesorder_vendor.network.model.ResponseOrderList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sabih Ahmed on 11/22/2017.
 */

public class ReadyOrdersPresenter {

    private ReadyOrdersView readyOrdersView;

    public ReadyOrdersPresenter(ReadyOrdersView readyOrdersView) {
        this.readyOrdersView = readyOrdersView;
    }

    public void getReadyOrdersList(Context context){
        List<OrderTable> readyOrdersList = new ArrayList<>();
        try {
            readyOrdersList = OrderDataManager.getReadyOrdersList(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(readyOrdersList.isEmpty()){
            readyOrdersView.showEmptyView();
        }else{
            readyOrdersView.onReadyOrdersFetched(readyOrdersList);
        }

    }

    public void syncReadyOrders(final Context context) {
        Call<List<ResponseOrderList>> readyOrders = RestClient.getAdapter().
                                getReadyOrders(UserDataManager.getPersistedUser().getUserToken());
        readyOrdersView.showProgress();
        readyOrders.enqueue(new Callback<List<ResponseOrderList>>() {
            @Override
            public void onResponse(Call<List<ResponseOrderList>> call,
                                   Response<List<ResponseOrderList>> response) {
                readyOrdersView.hideProgress();
                if(response.isSuccessful()){
                    if(response.body().isEmpty()){
                        //
                    }else{
                        OrderDataManager.persistAllOrders(context,response.body(), new OrderDataManager.OrderPersistenceListener() {
                            @Override
                            public void onOrdersPersisted() {
                                readyOrdersView.onReadyOrdersSynced();
                            }
                        });
                    }
                }else{

                    //TODO: Extract the exceptions and show in view
                }
            }

            @Override
            public void onFailure(Call<List<ResponseOrderList>> call, Throwable t) {
                readyOrdersView.hideProgress();
                readyOrdersView.onNetworkFailure();
            }
        });
    }

    public void updateOrderAsDispatched(long orderID) {
        Call<GeneralResponse> updateAsDispatchRequest = RestClient.getAdapter().
                updateOrderAsDispatched(orderID, UserDataManager.getPersistedUser().getUserToken());
        readyOrdersView.showProgress();
        updateAsDispatchRequest.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                readyOrdersView.hideProgress();
                if(response.isSuccessful()){
                    readyOrdersView.onOrderUpdatedSuccessfully();
                }else{
                    //TODO: return Resolved Error on view
                    String resolvedError = "Your order cannot be updated this time";
                    readyOrdersView.onOrderUpdateException(resolvedError);
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                readyOrdersView.hideProgress();
                readyOrdersView.onNetworkFailure();
            }
        });
    }
}
