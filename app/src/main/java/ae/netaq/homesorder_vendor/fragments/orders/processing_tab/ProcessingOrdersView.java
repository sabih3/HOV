package ae.netaq.homesorder_vendor.fragments.orders.processing_tab;

import java.util.List;

import ae.netaq.homesorder_vendor.activities.GeneralView;
import ae.netaq.homesorder_vendor.db.data_manager.tables.OrderTable;

/**
 * Created by Netaq on 11/22/2017.
 */

public interface ProcessingOrdersView extends GeneralView{
    void onProcessingOrdersFetched(List<OrderTable> orders);

    void showProgress();

    void hideProgress();

    void showEmptyView();

    void onProductsSynced();

    void onOrderUpdateSuccessfully();

    void onOrderUpdateException(String resolvedError);
}
