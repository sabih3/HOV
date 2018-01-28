package ae.netaq.homesorder_vendor.fragments.orders.processing_tab;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import ae.netaq.homesorder_vendor.db.data_manager.OrderDataManager;
import ae.netaq.homesorder_vendor.db.data_manager.UserDataManager;
import ae.netaq.homesorder_vendor.db.data_manager.tables.OrderTable;
import ae.netaq.homesorder_vendor.network.core.RestClient;
import ae.netaq.homesorder_vendor.network.model.GeneralResponse;
import ae.netaq.homesorder_vendor.network.model.ResponseOrderList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Netaq on 11/22/2017.
 */

public class ProcessingOrdersPresenter {

    private ProcessingOrdersView processingOrderView;

    public ProcessingOrdersPresenter(ProcessingOrdersView processingOrderView) {
        this.processingOrderView = processingOrderView;
    }

    public void getProcessingOrdersList(Context context){
        try {

            List<OrderTable> processingOrdersList = OrderDataManager.getProcessingOrdersList(context);
            processingOrderView.onProcessingOrdersFetched(processingOrdersList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void syncProcessingOrders() {
        Call<List<ResponseOrderList>> processingOrdersRequest = RestClient.getAdapter().
                getProcessingOrders(UserDataManager.getPersistedUser().getUserToken());
        processingOrderView.showProgress();

        processingOrdersRequest.enqueue(new Callback<List<ResponseOrderList>>() {
            @Override
            public void onResponse(Call<List<ResponseOrderList>> call, Response<List<ResponseOrderList>> response) {
                processingOrderView.hideProgress();
                if(response.isSuccessful()){
                    if(response.body().isEmpty()){
                        //processingOrderView.showEmptyView();
                    }else{
                        //Persist processing Orders via persisting policy
                        OrderDataManager.persistAllOrders(response.body(),
                                        new OrderDataManager.OrderPersistenceListener() {
                            @Override
                            public void onOrdersPersisted() {
                                processingOrderView.onProductsSynced();
                            }
                        });



                    }
                }else{
                    //TODO:Extract the exceptions and show in view

                }
            }

            @Override
            public void onFailure(Call<List<ResponseOrderList>> call, Throwable t) {
                //TODO: Hanlde Other Exception
                processingOrderView.onNetworkFailure();
            }
        });
    }

    public void updateOrderAsReady(long orderID) {
        Call<GeneralResponse> updateOrderRequest = RestClient.getAdapter().updateOrderAsReady(orderID,
                UserDataManager.getPersistedUser().getUserToken());
        processingOrderView.showProgress();
        updateOrderRequest.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                processingOrderView.hideProgress();

                if(response.isSuccessful()){
                    processingOrderView.onOrderUpdateSuccessfully();
                }else{
                    //TODO: Handle Exceptions for Order Update As processing, and fill in the resolved
                    //error from API Error Resolver

                    String resolvedError = "Your order cannot be updated this time";
                    processingOrderView.onOrderUpdateException(resolvedError);

                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                //TODO: Handle Network failure while update order as Processing
                processingOrderView.hideProgress();
                processingOrderView.onNetworkFailure();
            }
        });
    }
}
