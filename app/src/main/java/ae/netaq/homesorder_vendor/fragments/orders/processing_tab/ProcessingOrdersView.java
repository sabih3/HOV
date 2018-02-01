package ae.netaq.homesorder_vendor.fragments.orders.processing_tab;

import java.util.List;

import ae.netaq.homesorder_vendor.activities.GeneralView;
import ae.netaq.homesorder_vendor.db.tables.OrderTable;

/**
 * Created by Netaq on 11/22/2017.
 */

public interface ProcessingOrdersView extends GeneralView{


    void showProgress();

    void hideProgress();

    void showDataView(List<OrderTable> orders);

    void showEmptyView();

    void onProductsSynced();

    void onOrderUpdateSuccessfully();

    void onOrderUpdateException(String resolvedError);
}
