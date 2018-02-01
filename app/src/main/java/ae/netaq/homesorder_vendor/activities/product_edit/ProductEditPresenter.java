package ae.netaq.homesorder_vendor.activities.product_edit;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import ae.netaq.homesorder_vendor.db.data_manager.ProductsManager;
import ae.netaq.homesorder_vendor.db.data_manager.UserDataManager;
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

public class ProductEditPresenter {


    private final Context mContext;
    private ProductEditView productUpdateView;

    public ProductEditPresenter(Context mContext, ProductEditView productUpdateView) {
        this.mContext = mContext;
        this.productUpdateView = productUpdateView;
    }

    public void updateProduct(final RemoteProduct productToUpdate) {
        Call<ResponseAddProduct> updateProductRequest = RestClient.getAdapter().
                                 productUpdate(productToUpdate,
                                 UserDataManager.getPersistedUser().getUserToken());


        updateProductRequest.enqueue(new Callback<ResponseAddProduct>() {
            @Override
            public void onResponse(Call<ResponseAddProduct> call, Response<ResponseAddProduct> response) {
                if(response.isSuccessful()){
                    NotificationHelper.showProgressNotification(mContext,
                                                                false,
                                                                 productToUpdate.getProductID(),
                                                                "Product Update",
                                                                "Updated Product Successfully");
                    ProductsManager.updateExistingProduct(mContext,response.body().getProduct());
                    //Event will be posted on SimpleProductsFragment.onProductUpdatedEvent
                    EventBus.getDefault().post(new ProductUpdatedEvent());
                    productUpdateView.onProductUpdateSuccess();

                }else{
                    //TODO: Handle exception from API and pass in description
                    NotificationHelper.showExceptionNotification(mContext,
                            (int) productToUpdate.getProductID(),
                            "Failed to update product",
                            "Product update cannnot be done this time, please try again");
                    productUpdateView.onProductUpdateFailure();


                }

            }

            @Override
            public void onFailure(Call<ResponseAddProduct> call, Throwable t) {
                productUpdateView.onProductUpdateFailure();

            }
        });

        NotificationHelper.showProgressNotification(mContext,true,
                                                    productToUpdate.getProductID(),
                                                    "Product Update",
                                                    "Updating Product");

    }

}
