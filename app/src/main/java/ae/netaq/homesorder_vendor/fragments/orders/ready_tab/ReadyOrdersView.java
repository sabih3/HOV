package ae.netaq.homesorder_vendor.fragments.orders.ready_tab;

import java.util.List;

import ae.netaq.homesorder_vendor.db.data_manager.tables.OrderTable;

/**
 * Created by Netaq on 11/22/2017.
 */

public interface ReadyOrdersView {
    void onReadyOrdersFetched(List<OrderTable> readyOrders);
}
