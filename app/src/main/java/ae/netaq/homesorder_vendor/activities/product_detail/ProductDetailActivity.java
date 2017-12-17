package ae.netaq.homesorder_vendor.activities.product_detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
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
import ae.netaq.homesorder_vendor.utils.NavigationController;
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

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_category)
    TextView tv_categoryName;

    @BindView(R.id.tv_prod_title)
    TextView tv_productTitle;

    @BindView(R.id.prod_values_layout)
    LinearLayout valuesLayout;

    private Picasso picasso;
    private ProductTable product;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);

        toolbar.setTitle(R.string.product_detail);
        setSupportActionBar(toolbar);

        picasso = AppController.get(this).getPicasso();

        try {
            product = (ProductTable) getIntent().getSerializableExtra(NavigationController.KEY_PRODUCT);
        }catch (NullPointerException npe){

        }


        setupProductImageSlider(product.getImagesArray());
        setCategory(product);
        setProductTitle(product);
        setValues(product);



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

        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.fashion);
        images.add(R.drawable.food);

        sliderPager.setAdapter(new SliderPagerAdapter(ProductDetailActivity.this,
                imagesArray,null, picasso));

        circleIndicator.setViewPager(sliderPager);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initViews() {


    }

    @Override
    public void OnProductFetched() {

    }

    private void setValues(ProductTable product){
        valuesLayout.removeAllViews();
        ArrayList<Object> values = new ArrayList<>();

        int targetGroupID = product.getTargetGroupID();
        int perDayOrderLimit = product.getPerDayOrderLimit();
        int handlingTime = product.getHandlingTime();
        String size = product.getSize();
        String color = product.getColor();

        values.add(targetGroupID);
        values.add(perDayOrderLimit);
        values.add(handlingTime);
        values.add(size);
        values.add(color);

        for(Object vals : values){
            TextView valueTextView = new TextView(this);
            valueTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                                      getResources().getDimension(R.dimen.body_font_large));
            valueTextView.setTextColor(ContextCompat.getColor(this,R.color.black));

            valueTextView.setText(vals.toString());
            valuesLayout.addView(valueTextView);
        }


    }
}
