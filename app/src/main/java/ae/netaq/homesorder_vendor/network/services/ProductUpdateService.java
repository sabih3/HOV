package ae.netaq.homesorder_vendor.network.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.db.data_manager.ProductsManager;
import ae.netaq.homesorder_vendor.event_bus.ProductUpdatedEvent;
import ae.netaq.homesorder_vendor.network.core.RestClient;
import ae.netaq.homesorder_vendor.network.model.RemoteProduct;
import ae.netaq.homesorder_vendor.network.model.ResponseAddProduct;
import ae.netaq.homesorder_vendor.utils.NotificationHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sabih on 16-Jan-18.
 */

public class ProductUpdateService extends IntentService{

    public static String KEY_UPDATE_PRODUCT = "updateProductKey";

    public ProductUpdateService() {
        super(ProductUpdateService.class.getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        RemoteProduct productToUpdate = (RemoteProduct) intent.getSerializableExtra(KEY_UPDATE_PRODUCT);
        updateProduct(productToUpdate);
    }

    private void updateProduct(final RemoteProduct productToUpdate) {
        Call<ResponseAddProduct> updateProductRequest = RestClient.getAdapter().
                                 productUpdate(productToUpdate, "mogqx1uf5n1bfejv5llfsyta9hco3ncf");


        updateProductRequest.enqueue(new Callback<ResponseAddProduct>() {
            @Override
            public void onResponse(Call<ResponseAddProduct> call, Response<ResponseAddProduct> response) {
                if(response.isSuccessful()){
                    NotificationHelper.showProgressNotification(ProductUpdateService.this,
                                                                false,
                                                                 productToUpdate.getProductID());
                    ProductsManager.updateExistingProduct(response.body().getProduct());
                    EventBus.getDefault().post(new ProductUpdatedEvent());

                }else{
                    //notify user about this
                }

            }

            @Override
            public void onFailure(Call<ResponseAddProduct> call, Throwable t) {

            }
        });

        NotificationHelper.showProgressNotification(this,true,
                                                    productToUpdate.getProductID());

    }

    /**
    private void showProgressNotification(Context context, boolean showProgress, long productID) {
        final int id = 1;
        final Notification.Builder mBuilder;

        if(showProgress){
            final NotificationManager mNotifyManager = (NotificationManager)
                    getSystemService(Context.NOTIFICATION_SERVICE);
            mBuilder = new Notification.Builder(this);
            mBuilder.setContentTitle("Product Update")
                    .setContentText("Updating Product")
                    .setSmallIcon(android.R.drawable.stat_notify_sync)
                    .setLargeIcon(BitmapFactory.decodeResource(this.getResources(),
                                 android.R.drawable.stat_notify_sync))
                    .setOngoing(true)
                    .setOnlyAlertOnce(true);


            mBuilder.setProgress(0, 0, true);
            // Issues the notification
            mNotifyManager.notify((int) productID, mBuilder.build());
        }else{
            final NotificationManager mNotifyManager = (NotificationManager)
                    getSystemService(Context.NOTIFICATION_SERVICE);
            mBuilder = new Notification.Builder(this);
            mBuilder.setContentTitle("Product Update")
                    .setContentText("Updated Product Successfully")
                    .setSmallIcon(android.R.drawable.stat_sys_upload_done);

            // Issues the notification
            mNotifyManager.notify((int) productID, mBuilder.build());
        }

    }**/
}
