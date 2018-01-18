package ae.netaq.homesorder_vendor.adapters.products.products_tab;

import java.util.List;

import ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable;

/**
 * Created by sabih on 13-Dec-17.
 */

public interface ProductsView {

    void onProductsFetched(List<ProductTable> allProducts);
    void showProgress();
    void onException();
}
