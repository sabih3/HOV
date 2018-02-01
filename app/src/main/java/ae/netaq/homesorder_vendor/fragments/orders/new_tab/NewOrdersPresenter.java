package ae.netaq.homesorder_vendor.fragments.orders.new_tab;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ae.netaq.homesorder_vendor.db.data_manager.OrderDataManager;
import ae.netaq.homesorder_vendor.db.data_manager.UserDataManager;
import ae.netaq.homesorder_vendor.db.tables.OrderTable;
import ae.netaq.homesorder_vendor.event_bus.OrderReloadEvent;
import ae.netaq.homesorder_vendor.network.core.RestClient;
import ae.netaq.homesorder_vendor.network.model.GeneralResponse;
import ae.netaq.homesorder_vendor.network.model.ResponseOrderList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sabih Ahmed on 11/22/2017.
 */

public class NewOrdersPresenter {

    private NewOrdersView newOrdersView;

    public NewOrdersPresenter(NewOrdersView newOrdersView) {
        this.newOrdersView = newOrdersView;
    }

    /**Fetches orders from db with status as new only
     *
     * @param context
     */
    public void getNewOrdersList(Context context){

        List<OrderTable> newOrdersList = new ArrayList<>();
        try {
            newOrdersList = OrderDataManager.getNewOrdersList(context);
            if(newOrdersList.isEmpty()){
                newOrdersView.showEmptyView();
            }else{
                newOrdersView.showDataView(newOrdersList);
            }

        } catch (SQLException e) {
            //Todo: handle exception here for view
            e.printStackTrace();
        }

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

    public void getAllOrdersSnapshot(final Context context){
        Call<List<ResponseOrderList>> allOrdersRequest = RestClient.getAdapter().
                getAllOrdersList(UserDataManager.getPersistedUser().getUserToken());
        newOrdersView.showProgress();
        allOrdersRequest.enqueue(new Callback<List<ResponseOrderList>>() {
            @Override
            public void onResponse(Call<List<ResponseOrderList>> call, Response<List<ResponseOrderList>> response) {
                newOrdersView.hideProgress();
                if(response.isSuccessful()){

                    if(response.body().isEmpty()){
                        //TODO: HANDLE Empty Case
                    }else{
                        OrderDataManager.persistAllOrders(context,response.body(), new OrderDataManager.OrderPersistenceListener() {
                            @Override
                            public void onOrdersPersisted() {
                                //Broadcast all fragment so that they can re load their views
                                EventBus.getDefault().post(new OrderReloadEvent());
                                newOrdersView.onAllOrdersSynced();
                            }
                        });
                    }
                }else{
                    //TODO: Handle Exceptional case
                }
            }

            @Override
            public void onFailure(Call<List<ResponseOrderList>> call, Throwable t) {
                newOrdersView.hideProgress();
                newOrdersView.onNetworkFailure();
            }
        });

    }
}
