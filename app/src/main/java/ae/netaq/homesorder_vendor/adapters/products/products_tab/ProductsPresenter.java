package ae.netaq.homesorder_vendor.adapters.products.products_tab;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import ae.netaq.homesorder_vendor.db.data_manager.ProductsManager;
import ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable;
import ae.netaq.homesorder_vendor.fragments.products.products_tab.SimpleProductsFragment;

/**
 * Created by sabih on 13-Dec-17.
 */

public class ProductsPresenter {

    private final ProductsView productsView;

    public ProductsPresenter(ProductsView productsView) {
        this.productsView = productsView;
    }

    public void fetchProducts(Context context) {
        try {
            List<ProductTable> allProducts = ProductsManager.getAllProducts();
            productsView.onProductsFetched(allProducts);
        } catch (SQLException e) {
            //TODO:Handle exception in view

        }


    }
}
