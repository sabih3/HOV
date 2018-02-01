package ae.netaq.homesorder_vendor.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.activities.AddNewProductActivity;
import ae.netaq.homesorder_vendor.activities.country_area_selection.AreaSelectionUAE;
import ae.netaq.homesorder_vendor.activities.MainActivity;
import ae.netaq.homesorder_vendor.activities.OrderDetailActivity;
import ae.netaq.homesorder_vendor.activities.bank_information.BankInformationActivity;
import ae.netaq.homesorder_vendor.activities.country_area_selection.AreaSelectionKSA;
import ae.netaq.homesorder_vendor.activities.country_area_selection.SelectCountryActivity;
import ae.netaq.homesorder_vendor.activities.delivery_setup.DeliverySetupActvity;
import ae.netaq.homesorder_vendor.activities.product_detail.ProductDetailActivity;
import ae.netaq.homesorder_vendor.activities.ProfileActivity;
import ae.netaq.homesorder_vendor.activities.product_edit.ProductEditActivity;
import ae.netaq.homesorder_vendor.activities.product_promotion.ProductPromotionActivity;
import ae.netaq.homesorder_vendor.activities.register.RegisterActivity;
import ae.netaq.homesorder_vendor.activities.SettingsActivity;
import ae.netaq.homesorder_vendor.activities.sign_in.SignInActivity;
import ae.netaq.homesorder_vendor.activities.update_profile.UpdateProfileActivity;
import ae.netaq.homesorder_vendor.adapters.FragmentViewPager;
import ae.netaq.homesorder_vendor.db.tables.ProductTable;
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
    public static final int REQUEST_PERMISSION_STORAGE = 232;
    public static final String KEY_SELECTED_REGION = "selectedRegion";

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

    public static void startActivitySignIn(Context context){
        Intent intent = new Intent(context, SignInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public static void startActivityProfile(Context context){
        Intent intent = new Intent(context, ProfileActivity.class);
        context.startActivity(intent);
    }
    public static void showMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
    public static void showUpdateProfileActivity(Context context) {
        Intent intent = new Intent(context, UpdateProfileActivity.class);
        context.startActivity(intent);

    }
    public static void showCountrySelectActivity(Context context) {
        Intent intent = new Intent(context, SelectCountryActivity.class);
        context.startActivity(intent);
    }

    public static void showAreaSelectionUAE(Context context) {
        Intent intent = new Intent(context, AreaSelectionUAE.class);
        context.startActivity(intent);
    }

    public static void showAreaSelectionKSA(Context context) {
        Intent intent = new Intent(context, AreaSelectionKSA.class);
        context.startActivity(intent);

    }

    public static void showBankInformationActivity(Context context) {
        Intent intent = new Intent(context, BankInformationActivity.class);
        context.startActivity(intent);
    }

    public static void showDeliverySetupActivity(Context context) {
        Intent intent = new Intent(context, DeliverySetupActvity.class);
        context.startActivity(intent);
    }

    public static void showProductEdit(Context context,ProductTable product) {
        Intent intent = new Intent(context, ProductEditActivity.class);
        intent.putExtra(KEY_PRODUCT,product);
        context.startActivity(intent);
    }

    public static void showActivityProductPromotion(Context context,ProductTable product) {
        Intent intent = new Intent(context, ProductPromotionActivity.class);
        intent.putExtra(KEY_PRODUCT,product);
        context.startActivity(intent);
    }
}
