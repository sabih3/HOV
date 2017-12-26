package ae.netaq.homesorder_vendor.utils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.activities.product_detail.ProductDetailActivity;
import ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable;
import ae.netaq.homesorder_vendor.models.Product;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sabih on 18-Dec-17.
 */

public class ProductDetailUtility {

    private final ProductTable product = null;

    @BindView(R.id.tv_category)
    TextView tv_categoryName;

    @BindView(R.id.tv_prod_title) TextView tv_productTitle;

    @BindView(R.id.value_price) TextView value_price;

    @BindView(R.id.value_size) TextView val_size;

    @BindView(R.id.value_color) TextView val_color;

    @BindView(R.id.value_group) TextView val_group;

    @BindView(R.id.value_limit) TextView val_limit;

    @BindView(R.id.value_desc_en) TextView val_desc_en;

    @BindView(R.id.value_time) TextView val_time;

    @BindView(R.id.value_desc_ar) TextView val_desc_ar;

    View view = null;
    private Context context;

    public ProductDetailUtility(Context context, View view) {
        this.view = view;
        this.context = context;
        ButterKnife.bind(this,view);
    }


    public void bindValues(ProductTable product) {

        setCategory(product);
        setProductTitle(product);
        setValues(product);

    }

    private void setProductTitle(ProductTable product) {
        String productTitle = "";
        if(Common.isAPPLocaleArabic(context)){

            productTitle = product.getProductNameAR();

            if(productTitle.isEmpty()){
                productTitle = product.getProductNameEN();
            }
        }else{

            productTitle = product.getProductNameEN();
        }

        tv_productTitle.setText(productTitle);


    }

    private void setCategory(ProductTable product) {
        String productCategory = "";

        if(Common.isAPPLocaleArabic(context)){

            productCategory = product.getSubCategoryNameAR();

        }else{

            productCategory = product.getSubCategoryNameEN();
        }

        tv_categoryName.setText(productCategory);
    }

    private void setValues(ProductTable product){
        Double productPrice = product.getProductPrice();

        String size = product.getSize();

        String color = product.getColor();

        int targetGroupID = product.getTargetGroupID();

        String groupName = "N/A";

        // -1 will be set in case of food
        if(targetGroupID != -1){
            groupName = ProductGroupsManager.getLocaleBasedName(context,targetGroupID);
        }


        int perDayOrderLimit = product.getPerDayOrderLimit();
        int handlingTime = product.getHandlingTime();

        String descEN = product.getDescriptionEN();

        String descAR = product.getDescriptionAR();

        value_price.setText(String.valueOf(productPrice));
        val_size.setText(size);
        val_color.setText(color);
        val_group.setText(groupName);
        val_limit.setText(String.valueOf(perDayOrderLimit));
        val_time.setText(String.valueOf(handlingTime));
        val_desc_en.setText(descEN);

        val_desc_ar.setText(descAR);

    }

    //For Preview Fragment
    public void bindValues(Product product) {
        setCategory(product);
        setProductTitle(product);
        setValues(product);
    }

    //For Preview Fragment
    private void setValues(Product product) {
        Double productPrice = product.getProductPrice();

        String size = product.getSize();

        String color = product.getColor();

       try {
           int targetGroupID = product.getGroup().getId();

           String groupName = ProductGroupsManager.getLocaleBasedName(context,targetGroupID);
           val_group.setText(groupName);
       }catch (NullPointerException npe){
           val_group.setText("N/A");
       }

        int perDayOrderLimit = product.getDailyOrderLimit();
        int handlingTime = product.getHandlingTime();

        String descEN = product.getProductDescriptionEN();

        String descAR = product.getProductDescriptionAR();

        value_price.setText(String.valueOf(productPrice));
        val_size.setText(size);
        val_color.setText(color);

        val_limit.setText(String.valueOf(perDayOrderLimit));
        val_time.setText(String.valueOf(handlingTime));
        val_desc_en.setText(descEN);

        val_desc_ar.setText(descAR);
    }

    ////For Preview Fragment
    private void setProductTitle(Product product) {
        String productTitle = "";
        if(Common.isAPPLocaleArabic(context)){

            productTitle = product.getProductNameAR();

            if(productTitle.isEmpty()){
                productTitle = product.getProductNameEN();
            }
        }else{

            productTitle = product.getProductNameEN();
        }

        tv_productTitle.setText(productTitle);
    }

    //For Preview Fragment
    private void setCategory(Product product) {
        String productCategory = "";
        if(product.getSubCategory() != null){
            if(Common.isAPPLocaleArabic(context)){


                productCategory = product.getSubCategory().getSubCategoryAR();

            }else{

                productCategory = product.getSubCategory().getSubCategoryEN();
            }

            tv_categoryName.setText(productCategory);
        }

    }
}
