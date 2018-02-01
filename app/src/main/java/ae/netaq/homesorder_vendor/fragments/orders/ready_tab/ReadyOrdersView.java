package ae.netaq.homesorder_vendor.fragments.orders.ready_tab;

import java.util.List;

import ae.netaq.homesorder_vendor.activities.GeneralView;
import ae.netaq.homesorder_vendor.db.tables.OrderTable;

/**
 * Created by Netaq on 11/22/2017.
 */

public interface ReadyOrdersView extends GeneralView{
    void onReadyOrdersFetched(List<OrderTable> readyOrders);

    void onReadyOrdersSynced();

    void hideProgress();

    void showProgress();

    void onOrderUpdatedSuccessfully();

    void onOrderUpdateException(String resolvedError);

    void showEmptyView();
}
