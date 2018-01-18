package ae.netaq.homesorder_vendor.activities;

import android.app.ProgressDialog;
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
import java.util.List;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.activities.sign_in.SignInActivity;
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
import ae.netaq.homesorder_vendor.models.ProductCategories;
import ae.netaq.homesorder_vendor.models.ProductGroups;
import ae.netaq.homesorder_vendor.network.model.ResponseAddProduct;
import ae.netaq.homesorder_vendor.network.services.ProductService;
import ae.netaq.homesorder_vendor.utils.Common;
import ae.netaq.homesorder_vendor.utils.NavigationController;
import ae.netaq.homesorder_vendor.utils.NonSwipeableViewPager;
import ae.netaq.homesorder_vendor.utils.UIUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 11/29/2017.
 */

public class AddNewProductActivity extends AppCompatActivity implements
             ChooseCategoryView,
             AddProductInformationView,
             AddProductImagesView,
             ProductPreviewView{

    @BindView(R.id.add_product_pager)
    NonSwipeableViewPager pager;

    @BindView(R.id.add_product_indicator)
    StepperIndicator indicator;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.add_product_bottom_btn)
    Button nextBtn;

    private FragmentViewPager pagerAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);
        ButterKnife.bind(this);

        toolbar.setTitle(R.string.add_product);
        setSupportActionBar(toolbar);


        Common.changeViewWithLocale(this,pager);
        Common.changeViewWithLocale(this,indicator);

        initViews();

    }

    @Override
    protected void onStop() {
        super.onStop();
        progressDialog.dismiss();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode){
            case NavigationController.REQUEST_PERMISSION_STORAGE:
                if(grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    //Fire event in AddProductImagesFragment.onPermissionGranted
                    EventBus.getDefault().post(new StoragePermissionGrantedEvent());

                }else{

                    //No need to handle permission denial, as for image extraction, permission
                    //grant is mandatory
                }
                return;
    }

    }

    private void initViews() {
        progressDialog = new ProgressDialog(this, R.style.ProgressDialogTheme);

        pagerAdapter =  NavigationController.
                        getAddNewProductPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setOffscreenPageLimit(3);
        indicator.setViewPager(pager);

        if(Common.isAPPLocaleArabic(this))
        indicator.setDoneIcon(getResources().getDrawable(R.drawable.ic_done_ar_white_18px));

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
                        ((AddProductInformationFragment)
                                pagerAdapter.getFragment(1)).validate();
                        break;
                    case 2:
                        ((AddProductImagesFragment)
                                pagerAdapter.getFragment(2)).validate();
                        break;
                    case 3:
                        ((ProductPreviewFragment)
                                pagerAdapter.getFragment(3)).validate();
                        break;
                    default:
                        ((ChooseCategoryFragment)
                                pagerAdapter.getFragment(0)).validate();
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
        ((ProductPreviewFragment) pagerAdapter.
                                  getFragment(3)).setupProductImageSlider(imagesUri);
    }

    @Override
    public void onProductInformationAdded(String productNameEN, String productNameAR,
                                          Double productPrice,
                                          String descEN,
                                          String descAR,
                                          String size,
                                          String color,
                                          int orderLimit,
                                          int handlingTime) {
        Product.getInstance().setProductNameEN(productNameEN);
        Product.getInstance().setProductNameAR(productNameAR);
        Product.getInstance().setProductPrice(productPrice);
        Product.getInstance().setColor(color);
        Product.getInstance().setSize(size);
        Product.getInstance().setDailyLimit(orderLimit);
        Product.getInstance().setHandlingTime(handlingTime);

        Product.getInstance().setProductDescriptionEN(descEN);
        Product.getInstance().setProductDescriptionAR(descAR);


        pager.setCurrentItem(pager.getCurrentItem()+1, true);
        setButtonTile(pager.getCurrentItem());
    }

    //Fired from ChooseCategoryFragment.validate
    @Override
    public void onCategoryChosen(int mainCategory, ProductCategories.Category subCategory,
                                                   ProductGroups.Group group) {
        Product.getInstance().setMainCategory(mainCategory);
        Product.getInstance().setSubCategory(subCategory);
        Product.getInstance().setGroup(group);

        pager.setCurrentItem(pager.getCurrentItem()+1, true);
        ((AddProductInformationFragment)
                    pagerAdapter.getFragment(1)).setSelectedMainCategory(mainCategory);
        setButtonTile(pager.getCurrentItem());
    }

    @Override
    public void onAddProductRequested() {

        ProductService.getInstance().addProduct(this, new ProductService.ProductAddCallbak() {
            @Override
            public void onProcessingImages() {

            }

            @Override
            public void onUploadingProduct() {
                UIUtils.showProgressDialog(AddNewProductActivity.this,
                        getString(R.string.progress_product_upload), progressDialog);
            }

            @Override
            public void onProductAdded(ResponseAddProduct.Product product) {

                Product.getInstance().reset();

                UIUtils.hideProgressDialog(progressDialog);

                ProductsManager.insertRemoteProduct(product);

                Toast.makeText(AddNewProductActivity.this, "Product Added Successfully",
                              Toast.LENGTH_LONG).show();
                AddNewProductActivity.this.finish();
            }

            @Override
            public void onProductAddException(String localizedError) {
                UIUtils.hideProgressDialog(progressDialog);

                UIUtils.showMessageDialog(AddNewProductActivity.this, localizedError,
                        getString(R.string.dialog_btn_ok), getString(R.string.dialog_btn_cancel),
                        new UIUtils.DialogButtonListener() {
                            @Override
                            public void onPositiveButtonClicked() {

                            }

                            @Override
                            public void onNegativeButtonClicked() {

                            }
                        });
            }

            @Override
            public void onNetworkFailure() {
                UIUtils.hideProgressDialog(progressDialog);
            }

            @Override
            public void onAuthTokenExpired() {
                UIUtils.hideProgressDialog(progressDialog);
                UIUtils.showMessageDialog(AddNewProductActivity.this,
                        "Your session has been timed out, You need to Re login again",
                        "Okay, take me to login screen",
                        "Let me stay here", new UIUtils.DialogButtonListener() {
                            @Override
                            public void onPositiveButtonClicked() {
                                AddNewProductActivity.this.finish();
                                NavigationController.startActivitySignIn(AddNewProductActivity.this);
                            }

                            @Override
                            public void onNegativeButtonClicked() {

                            }
                        });
            }


        });


    }
}
