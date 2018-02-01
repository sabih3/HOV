package ae.netaq.homesorder_vendor.fragments.products.products_tab;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.products.products_tab.ProductsPresenter;
import ae.netaq.homesorder_vendor.adapters.products.products_tab.ProductsView;
import ae.netaq.homesorder_vendor.adapters.products.products_tab.SimpleProductsRecyclerAdapter;
import ae.netaq.homesorder_vendor.db.tables.ProductTable;
import ae.netaq.homesorder_vendor.event_bus.ProductSyncFailedEvent;
import ae.netaq.homesorder_vendor.event_bus.ProductSyncFinishEvent;
import ae.netaq.homesorder_vendor.event_bus.ProductUpdatedEvent;
import ae.netaq.homesorder_vendor.utils.Common;
import ae.netaq.homesorder_vendor.utils.DevicePreferences;
import ae.netaq.homesorder_vendor.utils.NavigationController;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 11/22/2017.
 */

public class SimpleProductsFragment extends Fragment implements
             ProductsView,SimpleProductsRecyclerAdapter.ProductSelectionListener,
             SimpleProductsRecyclerAdapter.ProductActionsClick, PopupMenu.OnMenuItemClickListener {

    @BindView(R.id.listing_recycler)
    RecyclerView newOrdersRecycler;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.sync_layout)
    RelativeLayout syncLayout;

    @BindView(R.id.sync_image_view)
    ImageView syncImage;

    @BindView(R.id.sync_text_view)
    TextView syncTextView;

    private ProductsPresenter presenter;
    private ProductTable productToEdit;

    private Animation rotation;

    public SimpleProductsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listing_layout_products, container, false);
        ButterKnife.bind(this, view);
        Common.changeViewWithLocale(getContext(),view);
        initViews();




        return view;
    }

    private void initViews() {
        rotation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_anim);
        rotation.setRepeatCount(Animation.INFINITE);
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshListener());
        presenter = new ProductsPresenter(this);

        if (DevicePreferences.getProductSyncStatus() == 1) {
            //In case the products are currently syncing.
            setSyncLayoutSyncing();
        }else if (DevicePreferences.getProductSyncStatus() == 0){
            //In case the products sync is finished.
            setSyncLayoutSyncFinished();
        }else if (DevicePreferences.getProductSyncStatus() == 3){
            //In case products sync failed.
            setSyncLayoutSyncFailed("Sync Failed!");
        }else if(DevicePreferences.getProductSyncStatus() == 4){
            //In case no products found.
            setSyncLayoutSyncFailed("No products found!");
        }


    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    //Event Dispatched from ProductEditPresenter.updateProduct
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onProductUpdatedEvent(ProductUpdatedEvent productUpdatedEvent){
        presenter = new ProductsPresenter(this);
        presenter.fetchProducts(getContext());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onProductSyncFinishedEvent(ProductSyncFinishEvent syncFinishEvent){
        //Product Sync Finished
        //Event Received from ProductSyncService.retrieveProductList
        //Hide Sync in Progress from UI
        setSyncLayoutSyncFinished();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onProductSyncFailedEvent(ProductSyncFailedEvent syncFailedEvent){
        //Product Sync Failed
        //Event Received from ProductSyncService.retrieveProductList
        //hide sync icon, show information to refresh
        setSyncLayoutSyncFailed("Sync Failed!");
    }

    @Override
    public void onProductsFetched(List<ProductTable> allProducts) {
        swipeRefreshLayout.setRefreshing(false);
        List<ProductTable> productList = allProducts;
        setProductsInList(allProducts);

    }

    @Override
    public void showProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }


    @Override
    public void onException() {
        //TODO: Handle exception of product list
        swipeRefreshLayout.setRefreshing(false);
    }

    private void setProductsInList(List<ProductTable> allProducts){
        SimpleProductsRecyclerAdapter simpleProductsRecyclerAdapter =
                new SimpleProductsRecyclerAdapter(allProducts, getActivity());

        simpleProductsRecyclerAdapter.setProductListener(this);
        newOrdersRecycler.setLayoutManager(new GridLayoutManager(getContext(),2,

                LinearLayoutManager.VERTICAL,false));
        newOrdersRecycler.setAdapter(simpleProductsRecyclerAdapter);
        simpleProductsRecyclerAdapter.setActionListener(this);
    }

    @Override
    public void onProductSelected(ProductTable product) {
        NavigationController.startActivityProductDetail(getContext(),product);
    }

    @Override
    public void onProductActionListener(ProductTable product, View itemView) {
        this.productToEdit = product;
        Context wrapper = new ContextThemeWrapper(getActivity(), R.style.PopupMenu);
        PopupMenu popup = new PopupMenu(wrapper,itemView);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.product_actions);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId){

            case R.id.option_edit:
                NavigationController.showProductEdit(getContext(),productToEdit);
            break;
            case R.id.option_promote:
                NavigationController.showActivityProductPromotion(getContext(),productToEdit);
                break;


        }
        return false;
    }

    private class SwipeRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {

            //TODO: Hard Sync Products
            //Call Network to Hard Sync Products
            //presenter.fetchProducts(getActivity());
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    void setSyncLayoutSyncing(){
        syncLayout.setVisibility(View.VISIBLE);
        syncImage.startAnimation(rotation);
    }

    void setSyncLayoutSyncFinished(){
        syncLayout.setVisibility(View.GONE);
        presenter.fetchProducts(getContext());
    }
    void setSyncLayoutSyncFailed(String s){
        syncLayout.setVisibility(View.VISIBLE);
        syncImage.setImageResource(R.drawable.ic_sync_problem);
        syncImage.clearAnimation();
        syncTextView.setText(s);
    }
}
