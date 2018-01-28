package ae.netaq.homesorder_vendor.network.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import ae.netaq.homesorder_vendor.db.data_manager.ProductsManager;
import ae.netaq.homesorder_vendor.db.data_manager.UserDataManager;
import ae.netaq.homesorder_vendor.event_bus.ProductSyncFailedEvent;
import ae.netaq.homesorder_vendor.event_bus.ProductSyncFinishEvent;
import ae.netaq.homesorder_vendor.event_bus.ProductSyncNoProductsFound;
import ae.netaq.homesorder_vendor.network.core.ErrorUtils;
import ae.netaq.homesorder_vendor.network.core.ResponseCodes;
import ae.netaq.homesorder_vendor.network.core.RestClient;
import ae.netaq.homesorder_vendor.network.model.APIError;
import ae.netaq.homesorder_vendor.network.model.ResponseAddProduct;
import ae.netaq.homesorder_vendor.network.model.ResponseProductList;
import ae.netaq.homesorder_vendor.utils.DevicePreferences;
import ae.netaq.homesorder_vendor.utils.NotificationHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sabih on 23-Jan-18.
 */

public class ProductsSyncService extends IntentService{

    public ProductsSyncService() {
        super(ProductService.class.getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        retrieveProductList();
    }

    private void retrieveProductList() {

        NotificationHelper.showProgressNotification(this,true,-1,
                "Syncing Products","Downloading your Products");

        Call<ResponseProductList> request = RestClient.getAdapter().getProductList(
                DevicePreferences.getUserInfo().getUserToken());

        //Setting the product Sync status to 1. Means Sync has been started.
        DevicePreferences.setProductSyncStatus(1);

        request.enqueue(new Callback<ResponseProductList>() {
            @Override
            public void onResponse(Call<ResponseProductList> call, Response<ResponseProductList> response) {
                if(response.isSuccessful()){
                    if(response.body().getItems().size()!=0){
                        ArrayList<ResponseAddProduct.Product> products = response.body().getItems();
                        for(ResponseAddProduct.Product product : products){
                            try {
                                ProductsManager.insertRemoteProduct(product);
                            } catch (Exception e) {
                                NotificationHelper.dismissProgressNotification(ProductsSyncService.this,
                                        -1);
                                EventBus.getDefault().post(new ProductSyncFailedEvent());
                            }
                        }

                    }

                    //Setting the product Sync status to 0. Means Sync is finished.
                    DevicePreferences.setProductSyncStatus(0);
                    //Dispatched to SimpleProductsFragment
                    EventBus.getDefault().post(new ProductSyncFinishEvent());

                    NotificationHelper.showProgressNotification(ProductsSyncService.this,
                            false,-1,
                            "Syncing Products","Your products have been updated");
                }else{
                    //TODO: handle exception
                    //for code 4000
                    //handle token expire exception
                    APIError apiError = ErrorUtils.parseError(response);
                    if(apiError.getCode()==(ResponseCodes.PRODUCT_LIST_EMPTY)){
                        //
                        //for code 4001
                        //if code is 4001, then handle no products found
                        NotificationHelper.dismissProgressNotification(ProductsSyncService.this,
                                -1);
                        //Setting the product Sync status to 4. Means no products found.
                        DevicePreferences.setProductSyncStatus(4);
                        //Dispatched to SimpleProductsFragment
                        EventBus.getDefault().post(new ProductSyncNoProductsFound());
                    }else{
                        //Setting the product Sync status to 3. Means Sync is failed.
                        DevicePreferences.setProductSyncStatus(3);
                        //Dispatched to SimpleProductsFragment
                        EventBus.getDefault().post(new ProductSyncFailedEvent());
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseProductList> call, Throwable t) {
                //TODO: Handle Failure
                EventBus.getDefault().post(new ProductSyncFailedEvent());
                //Setting the product Sync status to 3. Means Sync is failed.
                DevicePreferences.setProductSyncStatus(3);
            }
        });
    }
}
