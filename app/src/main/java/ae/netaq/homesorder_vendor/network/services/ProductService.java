package ae.netaq.homesorder_vendor.network.services;

import android.content.Context;
import android.net.Uri;

import java.io.IOException;
import java.util.ArrayList;

import ae.netaq.homesorder_vendor.interfaces.GeneralNetworkListener;
import ae.netaq.homesorder_vendor.models.Product;
import ae.netaq.homesorder_vendor.models.ProductGroups;
import ae.netaq.homesorder_vendor.network.core.ErrorUtils;
import ae.netaq.homesorder_vendor.network.core.RestClient;
import ae.netaq.homesorder_vendor.network.model.APIError;
import ae.netaq.homesorder_vendor.network.model.RemoteProduct;
import ae.netaq.homesorder_vendor.network.model.ResponseAddProduct;
import ae.netaq.homesorder_vendor.utils.ErrorResolver;
import ae.netaq.homesorder_vendor.utils.ImageUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sabih on 08-Jan-18.
 */

public class ProductService {

    private static ProductService instance;

    private ProductService(){

    }

    public static ProductService getInstance() {
        if(instance == null){
            instance = new ProductService();
        }
        return instance;
    }

    public static void addProduct(final Context context,
                                  final ProductAddCallbak productAddCallbak) {

        RemoteProduct remoteProduct = new RemoteProduct();
        String productNameEN = Product.getInstance().getProductNameEN();
        String productNameAR = Product.getInstance().getProductNameAR();
        Double productPrice = Product.getInstance().getProductPrice();
        String productDescriptionEN = Product.getInstance().getProductDescriptionEN();
        String productDescriptionAR = Product.getInstance().getProductDescriptionAR();
        int dailyOrderLimit = Product.getInstance().getDailyOrderLimit();
        int handlingTime = Product.getInstance().getHandlingTime();

        String color = Product.getInstance().getColor();
        String size = Product.getInstance().getSize();



        ArrayList<String> category = getCategory();


        remoteProduct.setProductname(productNameEN);
        remoteProduct.setProductname_arabic(productNameAR);
        remoteProduct.setPrice(String.valueOf(productPrice));
        remoteProduct.setDescription(productDescriptionEN);
        remoteProduct.setDescription_arabic(productDescriptionAR);
        remoteProduct.setOrderlimitperday(String.valueOf(dailyOrderLimit));
        remoteProduct.setHandlingtime(String.valueOf(handlingTime));

//        if(color.isEmpty()){
//            remoteProduct.setColor(new String[]{});
//        }
//        else{
//            remoteProduct.setColor(new String[]{color});
//        }

        remoteProduct.setColor(new String[]{color});
        remoteProduct.setSize(new String[]{size});
        remoteProduct.setWeight(new String[]{});
        remoteProduct.setCategory(category);
        remoteProduct.setImages(getByteConvertedImages(context));


        Call<ResponseAddProduct> addProductrequest = RestClient.getAdapter().
                productAdd(remoteProduct,"mogqx1uf5n1bfejv5llfsyta9hco3ncf");


        productAddCallbak.onUploadingProduct();

        addProductrequest.enqueue(new Callback<ResponseAddProduct>() {
            @Override
            public void onResponse(Call<ResponseAddProduct> call, Response<ResponseAddProduct> response) {
                if(response.isSuccessful()){
                    productAddCallbak.onProductAdded(response.body().getProduct());
                }else{

                    APIError apiError = ErrorUtils.parseError(response);
                    int code = apiError.getCode();

                    if(code == 4000){
                        productAddCallbak.onAuthTokenExpired();
                    }else{
                        String localizedError = ErrorResolver.getLocalizedError(context,
                                code);
                        productAddCallbak.onProductAddException(localizedError);
                    }

                }


            }

            @Override
            public void onFailure(Call<ResponseAddProduct> call, Throwable t) {
                productAddCallbak.onNetworkFailure();
            }
        });

    }

    private static ArrayList<String> getByteConvertedImages(Context context) {
        ArrayList<String> byteImages = new ArrayList<>();
        ArrayList<Uri> productImagesUri = Product.getInstance().getProductImagesUri();

        for(Uri eachImageUri : productImagesUri){
            try {
                byteImages.add(ImageUtils.getEncodedString(context,eachImageUri));
            } catch (IOException e) {
                byteImages.add("");
            }
        }

        return byteImages;
    }

    private static ArrayList<String> getCategory() {
        ProductGroups.Group group = Product.getInstance().getGroup();
        ArrayList category = new ArrayList();

        int mainCategory = Product.getInstance().getMainCategory();
        int subCategoryID = Product.getInstance().getSubCategory().getId();

        if(group !=null){

            int targetGroup = group.getId();
            category.add(String.valueOf(mainCategory));
            category.add(String.valueOf(targetGroup));
            category.add(String.valueOf(subCategoryID));

        }else{

            category.add(String.valueOf(mainCategory));
            category.add(String.valueOf(subCategoryID));
        }

        return category;
    }

    public interface ProductAddCallbak extends GeneralNetworkListener{

        void onProcessingImages();

        void onUploadingProduct();

        void onProductAdded(ResponseAddProduct.Product product);

        void onProductAddException(String localizedError);

    }
}
