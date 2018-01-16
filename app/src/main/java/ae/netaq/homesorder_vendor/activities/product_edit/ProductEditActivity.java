package ae.netaq.homesorder_vendor.activities.product_edit;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shawnlin.numberpicker.NumberPicker;

import java.util.List;

import ae.netaq.homesorder_vendor.AppController;
import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.SliderPagerAdapter;
import ae.netaq.homesorder_vendor.db.data_manager.tables.ImageTable;
import ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable;
import ae.netaq.homesorder_vendor.utils.DevicePreferences;
import ae.netaq.homesorder_vendor.utils.NavigationController;
import ae.netaq.homesorder_vendor.utils.ProductGroupsManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

public class ProductEditActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tv_category) TextView tvProductCategory;
    @BindView(R.id.et_prod_title) EditText etProductTitle;
    @BindView(R.id.et_price) EditText etProductPrice;
    @BindView(R.id.et_size)EditText etProductSize;
    @BindView(R.id.et_color)EditText etColor;
    @BindView(R.id.et_desc_en)EditText editDescEN;
    @BindView(R.id.et_desc_ar)EditText editDescAR;
    @BindView(R.id.edit_spinner_group)AppCompatSpinner targetGroupSpinner;
    @BindView(R.id.edit_order_limit)NumberPicker orderLimitPicker;
    @BindView(R.id.edit_handling_time)NumberPicker handlingTimePicker;
    @BindView(R.id.slider_pager) ViewPager sliderPager;
    @BindView(R.id.slider_indicator) CircleIndicator circleIndicator;
    @BindView(R.id.btn_edit_images)
    RelativeLayout btnEditImages;

    private ProductTable product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_edit);
        ButterKnife.bind(this);

        btnEditImages.setOnClickListener(this);

        product = (ProductTable) getIntent().getSerializableExtra(NavigationController.KEY_PRODUCT);

        tvProductCategory.setText(product.getSubCategoryNameEN());
        etProductTitle.setText(product.getProductNameEN());
        etProductPrice.setText(product.getProductPrice().toString());
        etProductSize.setText(product.getSize());
        editDescEN.setText(product.getDescriptionEN());
        editDescAR.setText(product.getDescriptionAR());

        setToolbar();

        setupProductImages(product.getImagesArray());

        setUpSpinner();
    }

    private void setToolbar() {
        toolbar.setTitle(R.string.edit_product);
        setSupportActionBar(toolbar);

        if(DevicePreferences.isLocaleSetToArabic()){
            toolbar.setNavigationIcon(R.drawable.ic_prev_ar);
        }else{
            toolbar.setNavigationIcon(R.drawable.ic_prev);
        }
    }

    private void setupProductImages(List<ImageTable> imagesArray) {
        sliderPager.setAdapter(new SliderPagerAdapter(this, imagesArray,null,
                AppController.get(this).getPicasso()));
        circleIndicator.setViewPager(sliderPager);
    }

    private void setUpSpinner() {

        targetGroupSpinner.setAdapter(ProductGroupsManager.getProductGroupsAdapter(this,
                ProductGroupsManager.FILENAME_PRODUCT_GROUPS));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                product = (ProductTable) data.getSerializableExtra(NavigationController.KEY_PRODUCT);
                setupProductImages(product.getImagesArray());
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_edit_images){
            Intent i = new Intent(this, ImagesEditActivity.class);
            i.putExtra(NavigationController.KEY_PRODUCT,product);
            startActivityForResult(i, 1);
        }
    }
}
