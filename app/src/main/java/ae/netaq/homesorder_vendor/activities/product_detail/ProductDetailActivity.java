package ae.netaq.homesorder_vendor.activities.product_detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ae.netaq.homesorder_vendor.AppController;
import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.SliderPagerAdapter;
import ae.netaq.homesorder_vendor.db.data_manager.tables.ImageTable;
import ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable;
import ae.netaq.homesorder_vendor.utils.Common;
import ae.netaq.homesorder_vendor.utils.DevicePreferences;
import ae.netaq.homesorder_vendor.utils.NavigationController;
import ae.netaq.homesorder_vendor.utils.ProductDetailUtility;
import ae.netaq.homesorder_vendor.utils.ProductGroupsManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Deena on 29/11/2017.
 */

public class ProductDetailActivity extends AppCompatActivity implements ProductDetailView{

    @BindView(R.id.slider_pager)
    ViewPager sliderPager;

    @BindView(R.id.slider_indicator)
    CircleIndicator circleIndicator;

    @BindView(R.id.toolbar) Toolbar toolbar;

    @BindView(R.id.tv_category) TextView tv_categoryName;

    @BindView(R.id.tv_prod_title) TextView tv_productTitle;

    @BindView(R.id.value_price) TextView value_price;

    @BindView(R.id.value_size) TextView val_size;

    @BindView(R.id.value_color) TextView val_color;

    @BindView(R.id.value_group) TextView val_group;

    @BindView(R.id.value_limit) TextView val_limit;

    @BindView(R.id.value_desc_en) TextView val_desc_en;

    @BindView(R.id.value_time) TextView val_time;

    @BindView(R.id.value_desc_ar) TextView val_desc_ar;

    private ProductTable product;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);

        setToolbar();

        try {
            product = (ProductTable) getIntent().getSerializableExtra(NavigationController.KEY_PRODUCT);
        }catch (NullPointerException npe){

        }

        setupProductImageSlider(product.getImagesArray());

        ProductDetailUtility detailUtility = new ProductDetailUtility(this,
                             getWindow().findViewById(android.R.id.content));
        detailUtility.bindValues(product);

//        setCategory(product);
//        setProductTitle(product);
//        setValues(product);


    }

    private void setToolbar() {
        toolbar.setTitle(R.string.product_detail);
        setSupportActionBar(toolbar);

        if(DevicePreferences.isLocaleSetToArabic()){
            toolbar.setNavigationIcon(R.drawable.ic_prev_ar);
        }else{
            toolbar.setNavigationIcon(R.drawable.ic_prev);
        }
    }

    private void setProductTitle(ProductTable product) {
        String productTitle = "";
        if(Common.isAPPLocaleArabic(this)){

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

        if(Common.isAPPLocaleArabic(this)){

            productCategory = product.getSubCategoryNameAR();

        }else{

            productCategory = product.getSubCategoryNameEN();
        }

        tv_categoryName.setText(productCategory);
    }

    private void setupProductImageSlider(List<ImageTable> imagesArray) {


        sliderPager.setAdapter(new SliderPagerAdapter(ProductDetailActivity.this,
                imagesArray,null));

        circleIndicator.setViewPager(sliderPager);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    @Override
    public void OnProductFetched() {

    }

    private void setValues(ProductTable product){
        Double productPrice = product.getProductPrice();

        String size = product.getSize();

        String color = product.getColor();

        int targetGroupID = product.getTargetGroupID();

        String groupName = ProductGroupsManager.getLocaleBasedName(this,targetGroupID);

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



}
