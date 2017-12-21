package ae.netaq.homesorder_vendor.fragments.products.products_tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.products.products_tab.ProductsPresenter;
import ae.netaq.homesorder_vendor.adapters.products.products_tab.ProductsView;
import ae.netaq.homesorder_vendor.adapters.products.products_tab.SimpleProductsRecyclerAdapter;
import ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable;
import ae.netaq.homesorder_vendor.utils.Common;
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

    private ProductsPresenter presenter;
    private ProductTable productToEdit;

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
        View view = inflater.inflate(R.layout.listing_layout, container, false);
        ButterKnife.bind(this, view);
        Common.changeViewWithLocale(getContext(),view);
        initViews();




        return view;
    }

    private void initViews() {

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter = new ProductsPresenter(this);
        presenter.fetchProducts(getContext());
    }

    @Override
    public void onProductsFetched(List<ProductTable> allProducts) {
        List<ProductTable> productList = allProducts;
        setProductsInList(allProducts);

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
        PopupMenu popup = new PopupMenu(getActivity(),itemView);
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

        }
        return false;
    }
}
