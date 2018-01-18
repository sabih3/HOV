package ae.netaq.homesorder_vendor.activities.product_edit;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shawnlin.numberpicker.NumberPicker;

import java.util.ArrayList;
import java.util.List;

import ae.netaq.homesorder_vendor.AppController;
import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.SliderPagerAdapter;
import ae.netaq.homesorder_vendor.db.data_manager.tables.ImageTable;
import ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable;
import ae.netaq.homesorder_vendor.models.ProductGroups;
import ae.netaq.homesorder_vendor.network.model.RemoteProduct;
import ae.netaq.homesorder_vendor.network.services.ProductUpdateService;
import ae.netaq.homesorder_vendor.utils.DevicePreferences;
import ae.netaq.homesorder_vendor.utils.NavigationController;
import ae.netaq.homesorder_vendor.utils.ProductGroupsManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

import static ae.netaq.homesorder_vendor.network.services.ProductUpdateService.KEY_UPDATE_PRODUCT;

public class ProductEditActivity extends AppCompatActivity implements View.OnClickListener{


    @BindView(R.id.toolbar) Toolbar toolbar;

    @BindView(R.id.slider_pager) ViewPager sliderPager;
    @BindView(R.id.slider_indicator) CircleIndicator circleIndicator;
    @BindView(R.id.btn_edit_images) RelativeLayout btnEditImages;

    @BindView(R.id.tv_category) TextView tvProductCategory;
    @BindView(R.id.et_prod_title) EditText etProductTitle;
    @BindView(R.id.et_prod_title_ar)EditText etProductTitleAR;
    @BindView(R.id.et_price) EditText etProductPrice;
    @BindView(R.id.et_size)EditText etProductSize;
    @BindView(R.id.et_color)EditText etColor;
    @BindView(R.id.layout_target_group)LinearLayout targetGroupParent;
    @BindView(R.id.edit_spinner_group)AppCompatSpinner targetGroupSpinner;
    @BindView(R.id.edit_handling_time)NumberPicker handlingTimePicker;
    @BindView(R.id.edit_order_limit)NumberPicker orderLimitPicker;
    @BindView(R.id.et_desc_en)EditText editDescEN;
    @BindView(R.id.et_desc_ar)EditText editDescAR;
    @BindView(R.id.btn_update_product)Button updateButton;
    @BindView(R.id.tv_product_id)TextView tvProductID;

    private ProductTable product;
    private int updatedTargetGroupID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_edit);

    }

    @Override
    protected void onResume() {
        super.onResume();

        ButterKnife.bind(this);
        btnEditImages.setOnClickListener(this);

        product = (ProductTable) getIntent().getSerializableExtra(NavigationController.KEY_PRODUCT);
        setupProductImages(product.getImagesArray());

        tvProductCategory.setText(product.getSubCategoryNameEN());
        etProductTitle.setText(product.getProductNameEN());
        etProductTitleAR.setText(product.getProductNameAR());
        etProductPrice.setText(product.getProductPrice().toString());
        etProductSize.setText(product.getSize());
        editDescEN.setText(product.getDescriptionEN());
        editDescAR.setText(product.getDescriptionAR());

        handlingTimePicker.setValue(product.getHandlingTime());
        orderLimitPicker.setValue(product.getPerDayOrderLimit());


        setToolbar();


        if(product.getTargetGroupID()!=-1){
            setUpTargetGroupSpinner();
        }

        updateButton.setOnClickListener(new UpdateButtonListener());
        tvProductID.setText(String.valueOf(product.getProductID()));
    }

    @Override
    protected void onPause() {
        super.onPause();
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

    private void setUpTargetGroupSpinner() {
        targetGroupSpinner.setOnItemSelectedListener(null);

        targetGroupParent.setVisibility(View.VISIBLE);
        targetGroupSpinner.setAdapter(ProductGroupsManager.getProductGroupsAdapter(this,
                ProductGroupsManager.FILENAME_PRODUCT_GROUPS));

        int targetGroupID = product.getTargetGroupID();
        ProductGroups.Group productGroup = ProductGroupsManager.getProductGroup(this, targetGroupID);
        int groupPosition = ProductGroupsManager.getproductGroupPosition(this, productGroup);


        targetGroupSpinner.setSelection(groupPosition);

        targetGroupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if(position > 0){

                    ProductGroups.Group group = ProductGroupsManager.
                            getGroupByPosition(ProductEditActivity.this,position);

                    updatedTargetGroupID = group.getId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



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

    private class UpdateButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String productTitle = etProductTitle.getText().toString();
            String productTitleAR = etProductTitleAR.getText().toString();
            String productPrice = etProductPrice.getText().toString();
            String productSize = etProductSize.getText().toString();
            String productColor = etColor.getText().toString();
            int handlingTime = handlingTimePicker.getValue();
            int orderLimit = orderLimitPicker.getValue();
            String descriptionEN = editDescEN.getText().toString();
            String descriptionAR = editDescAR.getText().toString();

            RemoteProduct remoteProduct = new RemoteProduct();

            remoteProduct.setProductID(product.getProductID());
            remoteProduct.setProductname(productTitle);
            remoteProduct.setProductname_arabic(productTitleAR);
            remoteProduct.setPrice(productPrice);
            remoteProduct.setSize(new String[]{productSize});
            remoteProduct.setColor(new String[]{"blue"});
            remoteProduct.setHandlingtime(String.valueOf(handlingTime));
            remoteProduct.setOrderlimitperday(String.valueOf(orderLimit));
            remoteProduct.setDescription(descriptionEN);
            remoteProduct.setDescription_arabic(descriptionAR);

            ArrayList<String> category = new ArrayList();
            if(updatedTargetGroupID == 0){
                category.add(String.valueOf(product.getParentCategoryID()));
                category.add(String.valueOf(product.getSubCategoryID()));
            }else{
                category.add(String.valueOf(product.getParentCategoryID()));
                category.add(String.valueOf(updatedTargetGroupID));
                category.add(String.valueOf(product.getSubCategoryID()));
            }


            remoteProduct.setCategory(category);
            ArrayList<String> imagesList = new ArrayList<>();
            imagesList.add("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");
            remoteProduct.setImages(imagesList);

            Intent intent = new Intent(ProductEditActivity.this, ProductUpdateService.class);
            intent.putExtra(KEY_UPDATE_PRODUCT,remoteProduct);
            startService(intent);

            ProductEditActivity.this.finish();

        }
    }
}
