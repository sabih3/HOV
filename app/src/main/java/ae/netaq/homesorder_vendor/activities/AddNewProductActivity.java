package ae.netaq.homesorder_vendor.activities;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.badoualy.stepperindicator.StepperIndicator;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.FragmentViewPager;
import ae.netaq.homesorder_vendor.db.data_manager.ProductsManager;
import ae.netaq.homesorder_vendor.db.data_manager.tables.ImageTable;
import ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable;
import ae.netaq.homesorder_vendor.event_bus.StoragePermissionGrantedEvent;
import ae.netaq.homesorder_vendor.fragments.add_new_product.add_product_images.AddProductImagesFragment;
import ae.netaq.homesorder_vendor.fragments.add_new_product.add_product_images.AddProductImagesView;
import ae.netaq.homesorder_vendor.fragments.add_new_product.add_product_information.AddProductInformationFragment;
import ae.netaq.homesorder_vendor.fragments.add_new_product.add_product_information.AddProductInformationView;
import ae.netaq.homesorder_vendor.fragments.add_new_product.choose_category.ChooseCategoryFragment;
import ae.netaq.homesorder_vendor.fragments.add_new_product.choose_category.ChooseCategoryView;
import ae.netaq.homesorder_vendor.fragments.add_new_product.product_preview.ProductPreviewFragment;
import ae.netaq.homesorder_vendor.fragments.add_new_product.product_preview.ProductPreviewView;
import ae.netaq.homesorder_vendor.models.Product;
import ae.netaq.homesorder_vendor.utils.NavigationController;
import ae.netaq.homesorder_vendor.utils.NonSwipeableViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 11/29/2017.
 */

public class AddNewProductActivity extends AppCompatActivity implements
             ChooseCategoryView, AddProductInformationView, AddProductImagesView,
             ProductPreviewView{

    public static final int REQUEST_PERMISSION_STORAGE = 232;

    @BindView(R.id.add_product_pager)
    NonSwipeableViewPager pager;

    @BindView(R.id.add_product_indicator)
    StepperIndicator indicator;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.add_product_bottom_btn)
    Button nextBtn;

    private FragmentViewPager pagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);
        ButterKnife.bind(this);

        toolbar.setTitle(R.string.add_product);
        setSupportActionBar(toolbar);

        initViews();
        //Common.changeViewWithLocale(this,pager);
        //Common.changeViewWithLocale(this,indicator);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_PERMISSION_STORAGE:
                if(grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    //Fire event in Image fragment
                    EventBus.getDefault().post(new StoragePermissionGrantedEvent());

                }else{

                    //TODO: Permission denied Handle This
                }
                return;
    }

    }

    private void initViews() {
        pagerAdapter =  NavigationController.getAddNewProductPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setOffscreenPageLimit(4);
        indicator.setViewPager(pager);

        indicator.addOnStepClickListener(new StepperIndicator.OnStepClickListener() {
            @Override
            public void onStepClicked(int step) {
                if(pager.getCurrentItem() > step) {
                    pager.setCurrentItem(step, true);
                    setButtonTile(pager.getCurrentItem());
                }
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
             }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (pager.getCurrentItem()) {
                    case 1:
                        ((AddProductInformationFragment) pagerAdapter.getFragment(1)).validate();
                        break;
                    case 2:
                        ((AddProductImagesFragment) pagerAdapter.getFragment(2)).validate();
                        break;
                    case 3:
                        ((ProductPreviewFragment) pagerAdapter.getFragment(3)).validate();
                        break;
                    default:
                        ((ChooseCategoryFragment) pagerAdapter.getFragment(0)).validate();
                }
            }
        });

    }

    private void setButtonTile(int position){
        if(position == 0){
            nextBtn.setText(getString(R.string.add_details));
        }else if(position == 1){
            nextBtn.setText(getString(R.string.add_images));
        }else if(position == 2){
            nextBtn.setText(getString(R.string.preview));
        }else if(position == 3){
            nextBtn.setText(getString(R.string.add_product_caps));
        }
    }

    //Fired from AddProductImagesFragment.validate
    @Override
    public void onAddImagesCompleted(ArrayList<Uri> imagesUri,
                                     ArrayList<byte[]> bytesArray) {

        Product.getInstance().setProductImagesUri(imagesUri);
        Product.getInstance().setImagesArray(bytesArray);

        pager.setCurrentItem(pager.getCurrentItem()+1, true);
        setButtonTile(pager.getCurrentItem());
        ((ProductPreviewFragment) pagerAdapter.getFragment(3)).setupProductImageSlider(imagesUri);
    }

    @Override
    public void onProductInformationAdded(String productName, Float productPrice, String productDescription) {
        Product.getInstance().setProductNameEN(productName);
        Product.getInstance().setProductDescriptionEN(productDescription);
        Product.getInstance().setProductPrice(productPrice);

        pager.setCurrentItem(pager.getCurrentItem()+1, true);
        setButtonTile(pager.getCurrentItem());
    }

    @Override
    public void onCategoryChosen(int productCategory, String productSubCategory, String productGroup) {
        Product.getInstance().setProductCategory(productCategory);
        Product.getInstance().setProductSubCategory(productSubCategory);
        Product.getInstance().setProductGroup(productGroup);

        pager.setCurrentItem(pager.getCurrentItem()+1, true);
        setButtonTile(pager.getCurrentItem());
    }

    @Override
    public void onAddProductRequested() {
        Product product = Product.getInstance();

        ProductTable productToPersist = new ProductTable();
        productToPersist.setProductID(product.getProductID());
        productToPersist.setParentCategoryID(product.getParentCategoryID());
        productToPersist.setParentCategoryNameEN("");
        productToPersist.setParentCategoryNameEN("");

        productToPersist.setTargetGroup(product.getTargetGroup());
        productToPersist.setSubCategoryID(product.getSubCategoryID());
        productToPersist.setProductNameEN(product.getProductNameEN());
        productToPersist.setProductNameAR("");
        productToPersist.setPerDayOrderLimit(product.getDailyOrderLimit());
        productToPersist.setHandlingTime(product.getHandlingTime());
        productToPersist.setDescriptionEN(product.getProductDescriptionEN());
        productToPersist.setDescriptionAR("arabic description");
        //productToPersist.setImagesLocalURI(product.getProductImagesUri());

        long productID = ProductsManager.persistProduct(productToPersist);


        ArrayList<byte[]> imagesArray = Product.getInstance().getImagesArray(); // byte array of images
        //to be used to upload images in backend

        //Local URI Array, to be replaced with absolute URLs of backend
        ArrayList<Uri> productImagesUri = Product.getInstance().getProductImagesUri();
        for(Uri uri:productImagesUri){
            ImageTable productImage = new ImageTable();
            productImage.setProductID(productID);
            productImage.setImage(null);
            productImage.setImageURI(uri.toString());

            ProductsManager.insertImage(productImage);
        }


        Toast.makeText(this, "Product Added Successfully", Toast.LENGTH_LONG).show();
        AddNewProductActivity.this.finish();
    }
}
