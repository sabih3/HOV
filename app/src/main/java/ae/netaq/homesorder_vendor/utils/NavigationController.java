package ae.netaq.homesorder_vendor.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.activities.AddNewProductActivity;
import ae.netaq.homesorder_vendor.activities.MainActivity;
import ae.netaq.homesorder_vendor.activities.OrderDetailActivity;
import ae.netaq.homesorder_vendor.activities.ProductDetailActivity;
import ae.netaq.homesorder_vendor.activities.RegisterActivity;
import ae.netaq.homesorder_vendor.activities.SettingsActivity;
import ae.netaq.homesorder_vendor.adapters.FragmentViewPager;
import ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable;
import ae.netaq.homesorder_vendor.fragments.PagerFragment;
import ae.netaq.homesorder_vendor.fragments.add_new_product.add_product_images.AddProductImagesFragment;
import ae.netaq.homesorder_vendor.fragments.add_new_product.add_product_information.AddProductInformationFragment;
import ae.netaq.homesorder_vendor.fragments.add_new_product.choose_category.ChooseCategoryFragment;
import ae.netaq.homesorder_vendor.fragments.add_new_product.product_preview.ProductPreviewFragment;
import ae.netaq.homesorder_vendor.fragments.orders.dispatched_tab.DispatchedOrdersFragment;
import ae.netaq.homesorder_vendor.fragments.orders.new_tab.NewOrdersFragment;
import ae.netaq.homesorder_vendor.fragments.orders.processing_tab.ProcessingOrdersFragment;
import ae.netaq.homesorder_vendor.fragments.orders.ready_tab.ReadyOrdersFragment;
import ae.netaq.homesorder_vendor.fragments.products.products_tab.SimpleProductsFragment;
import ae.netaq.homesorder_vendor.fragments.products.promotions_tab.PromotionProductsFragment;

/**
 * Created by Netaq on 11/21/2017.
 */

public class NavigationController {

    public static final String KEY_PRODUCT = "key_product";

    public static FragmentViewPager getOrdersPagerAdapter(Context context, FragmentManager supportFragmentManager) {

        FragmentViewPager viewPagerAdapter ;

        ArrayList<PagerFragment> fragmentList = new ArrayList<>();

        Fragment newOrdersFragment = new NewOrdersFragment();
        Fragment processingOrdersFragment = new ProcessingOrdersFragment();
        Fragment readyOrdersFragment = new ReadyOrdersFragment();
        Fragment dispatchedOrdersFragment = new DispatchedOrdersFragment();

        PagerFragment newOrdersPagerFragment = new PagerFragment(newOrdersFragment, context.getString(R.string.new_tab_title));
        PagerFragment processingOrdersPagerFragment = new PagerFragment(processingOrdersFragment, context.getString(R.string.processing_tab_title));
        PagerFragment readyOrdersPagerFragment = new PagerFragment(readyOrdersFragment, context.getString(R.string.ready_tab_title));
        PagerFragment dispatchedOrdersPagerFragment = new PagerFragment(dispatchedOrdersFragment, context.getString(R.string.dispatched_tab_title));

        fragmentList.add(newOrdersPagerFragment);
        fragmentList.add(processingOrdersPagerFragment);
        fragmentList.add(readyOrdersPagerFragment);
        fragmentList.add(dispatchedOrdersPagerFragment);

        viewPagerAdapter = new FragmentViewPager(supportFragmentManager, fragmentList);

        return viewPagerAdapter;
    }

    public static FragmentViewPager getProductsPagerAdapter(Context context, FragmentManager supportFragmentManager) {

        FragmentViewPager viewPagerAdapter;

        ArrayList<PagerFragment> fragmentList = new ArrayList<>();

        Fragment simpleProductsFragment = new SimpleProductsFragment();
        Fragment promotionProductsFragment = new PromotionProductsFragment();

        PagerFragment simpleProductsPagerFragment = new PagerFragment(simpleProductsFragment, context.getString(R.string.products_tab_title));
        PagerFragment promotionProductsPagerFragment = new PagerFragment(promotionProductsFragment, context.getString(R.string.promotions_tabs_title));

        fragmentList.add(simpleProductsPagerFragment);
        fragmentList.add(promotionProductsPagerFragment);

        viewPagerAdapter = new FragmentViewPager(supportFragmentManager, fragmentList);

        return viewPagerAdapter;
    }

    public static FragmentViewPager getAddNewProductPagerAdapter(FragmentManager supportFragmentManager) {

        FragmentViewPager viewPagerAdapter;

        ArrayList<PagerFragment> fragmentList = new ArrayList<>();

        Fragment chooseCategoryFragment = new ChooseCategoryFragment();
        Fragment addProductInformationFragment = new AddProductInformationFragment();
        Fragment addProductImagesFragment = new AddProductImagesFragment();
        Fragment productPreviewFragment = new ProductPreviewFragment();


        PagerFragment chooseCategoryPagerFragment = new PagerFragment(chooseCategoryFragment, "");
        PagerFragment addProductInformationPagerFragment = new PagerFragment(addProductInformationFragment, "");
        PagerFragment addProductImagesPagerFragment = new PagerFragment(addProductImagesFragment, "");
        PagerFragment productPreviewPagerFragment = new PagerFragment(productPreviewFragment, "");

        fragmentList.add(chooseCategoryPagerFragment);
        fragmentList.add(addProductInformationPagerFragment);
        fragmentList.add(addProductImagesPagerFragment);
        fragmentList.add(productPreviewPagerFragment);

        viewPagerAdapter = new FragmentViewPager(supportFragmentManager, fragmentList);

        return viewPagerAdapter;
    }

    public static void startActivityAddNewProduct(Context context){
        Intent intent = new Intent(context, AddNewProductActivity.class);
        context.startActivity(intent);
    }

    public static void startActivityOrderDetail(Context context){
        Intent intent = new Intent(context, OrderDetailActivity.class);
        context.startActivity(intent);
    }

    public static void startActivityProductDetail(Context context, ProductTable product){
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra(KEY_PRODUCT,product);
        context.startActivity(intent);
    }

    public static void startActivitySettings(Context context){
        Intent intent = new Intent(context, SettingsActivity.class);
        context.startActivity(intent);
    }

    public static void startActivityRegister(Context context){
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    public static void showMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);

    }
}
