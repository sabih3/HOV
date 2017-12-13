package ae.netaq.homesorder_vendor.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.badoualy.stepperindicator.StepperIndicator;

import java.util.ArrayList;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.FragmentViewPager;
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
import ae.netaq.homesorder_vendor.utils.Common;
import ae.netaq.homesorder_vendor.utils.NavigationController;
import ae.netaq.homesorder_vendor.utils.NonSwipeableViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 11/29/2017.
 */

public class AddNewProductActivity extends AppCompatActivity implements ChooseCategoryView, AddProductInformationView, AddProductImagesView, ProductPreviewView{

    @BindView(R.id.add_product_pager)
    NonSwipeableViewPager pager;

    @BindView(R.id.add_product_indicator)
    StepperIndicator indicator;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.add_product_bottom_btn)
    Button nextBtn;

    private FragmentViewPager pagerAdapter;

    private Product product;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);
        ButterKnife.bind(this);

        toolbar.setTitle(R.string.add_product);
        setSupportActionBar(toolbar);

        product = Product.getInstance();

        Common.changeViewWithLocale(this,pager);
        Common.changeViewWithLocale(this,indicator);

        initViews();

    }

    private void initViews() {
        pagerAdapter =  NavigationController.getAddNewProductPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setOffscreenPageLimit(4);
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
    @Override
    public void onAddImagesCompleted(ArrayList<Uri> imagesUri) {
        product.setProductImagesUri(imagesUri);
        pager.setCurrentItem(pager.getCurrentItem()+1, true);
        setButtonTile(pager.getCurrentItem());
        ((ProductPreviewFragment) pagerAdapter.getFragment(3)).setupProductImageSlider(imagesUri);
    }

    @Override
    public void onProductInformationAdded(String productName, Float productPrice, String productDescription) {
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        product.setProductPrice(productPrice);
        pager.setCurrentItem(pager.getCurrentItem()+1, true);
        setButtonTile(pager.getCurrentItem());
    }

    @Override
    public void onCategoryChosen(int mainCategory, ProductCategories.Category subCategory, ProductGroups.Group group) {
        product.setMainCategory(mainCategory);
        product.setSubCategory(subCategory);
        product.setGroup(group);
        pager.setCurrentItem(pager.getCurrentItem()+1, true);
        ((AddProductInformationFragment) pagerAdapter.getFragment(1)).setSelectedMainCategory(mainCategory);
        setButtonTile(pager.getCurrentItem());
    }

    @Override
    public void onAddProductRequested() {
        Toast.makeText(this, "Product Added Successfully", Toast.LENGTH_LONG).show();
        AddNewProductActivity.this.finish();
    }
}
