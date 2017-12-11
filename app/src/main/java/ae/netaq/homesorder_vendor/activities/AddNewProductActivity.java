package ae.netaq.homesorder_vendor.activities;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
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
import ae.netaq.homesorder_vendor.utils.NavigationController;
import ae.netaq.homesorder_vendor.utils.NonSwipeableViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.internal.Utils;

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

        initViews();
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

    @Override
    public void onAddImagesCompleted(ArrayList<Uri> imagesUri) {
        product.setProductImagesUri(imagesUri);
        pager.setCurrentItem(pager.getCurrentItem()+1, true);
        nextBtn.setText(getString(R.string.add_product_caps));
        ((ProductPreviewFragment) pagerAdapter.getFragment(3)).setupProductImageSlider(imagesUri);

    }

    @Override
    public void onProductInformationAdded(String productName, Float productPrice, String productDescription) {
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        product.setProductPrice(productPrice);
        pager.setCurrentItem(pager.getCurrentItem()+1, true);
        nextBtn.setText(getString(R.string.preview));
    }

    @Override
    public void onCategoryChosen(int productCategory, String productSubCategory, String productGroup) {
        product.setProductCategory(productCategory);
        product.setProductSubCategory(productSubCategory);
        product.setProductGroup(productGroup);
        pager.setCurrentItem(pager.getCurrentItem()+1, true);
        nextBtn.setText(getString(R.string.add_images));
    }

    @Override
    public void onAddProductRequested() {
        Toast.makeText(this, "Product Added Successfully", Toast.LENGTH_LONG).show();
        AddNewProductActivity.this.finish();
    }
}
