package ae.netaq.homesorder_vendor.activities.product_edit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shawnlin.numberpicker.NumberPicker;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ae.netaq.homesorder_vendor.AppController;
import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.SliderPagerAdapter;
import ae.netaq.homesorder_vendor.db.tables.ImageTable;
import ae.netaq.homesorder_vendor.db.tables.ProductTable;
import ae.netaq.homesorder_vendor.models.ProductGroups;
import ae.netaq.homesorder_vendor.network.model.RemoteProduct;
import ae.netaq.homesorder_vendor.utils.DevicePreferences;
import ae.netaq.homesorder_vendor.utils.ImageUtils;
import ae.netaq.homesorder_vendor.utils.NavigationController;
import ae.netaq.homesorder_vendor.utils.ProductGroupsManager;
import ae.netaq.homesorder_vendor.utils.UIUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

public class ProductEditActivity extends AppCompatActivity implements View.OnClickListener, ProductEditView{


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

    private ProgressDialog progressDialog;

    private ProductEditPresenter productEditPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_edit);
        ButterKnife.bind(this);
        btnEditImages.setOnClickListener(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        productEditPresenter = new ProductEditPresenter(this, this);

        progressDialog = new ProgressDialog(this, R.style.ProgressDialogTheme);

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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();


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

    @Override
    public void onProductUpdateSuccess() {
        Toast.makeText(this,"Product Updated Successfully!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProductUpdateFailure() {
        Toast.makeText(this,"Product Update Failed! Please try again.", Toast.LENGTH_SHORT).show();
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

            final RemoteProduct remoteProduct = new RemoteProduct();

            remoteProduct.setProductID(product.getProductID());
            remoteProduct.setProductname(productTitle);
            remoteProduct.setProductname_arabic(productTitleAR);
            remoteProduct.setPrice(productPrice);
            remoteProduct.setSize(new String[]{productSize});
            remoteProduct.setColor(new String[]{productColor});
            remoteProduct.setWeight(new String[]{});
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

            UIUtils.showProgressDialog(ProductEditActivity.this, "Processing Images",progressDialog);

            Thread mThread = new Thread() {
                @Override
                public void run() {

                    final ArrayList<String> imagesList = new ArrayList<>();

                    List<ImageTable> imagesArray = product.getImagesArray();

                    for(ImageTable image : imagesArray){
                        if(URLUtil.isContentUrl(image.getImageURI())){
                            try {
                                String imageBinary = ImageUtils.getEncodedString(ProductEditActivity.
                                        this, Uri.parse(image.getImageURI()));
                                imagesList.add(imageBinary);
                                continue;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            //Extract the binary and set in images Array List
                        }
                        if(URLUtil.isNetworkUrl(image.getImageURI())){

                            Bitmap imageBitmap = null;

                            try {
                                URL url = new URL(image.getImageURI());
                                imageBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                            } catch(IOException e) {
                                System.out.println(e);
                            }

                            String encodedString = null;
                            try {
                                encodedString = ImageUtils.getEncodedString(ProductEditActivity.this, imageBitmap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            imagesList.add(encodedString);
                        }
                    }

                    if(imagesList.size()>0){
                        remoteProduct.setImages(imagesList);

                        productEditPresenter.updateProduct(remoteProduct);

                        progressDialog.dismiss();

                        ProductEditActivity.this.finish();
                    }else{
                        progressDialog.dismiss();
                    }
                }
            };

            mThread.start();

            if(product.getImagesArray().size()<=0){
                Toast.makeText(ProductEditActivity.this, "Please select at least 1 image to continue", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
