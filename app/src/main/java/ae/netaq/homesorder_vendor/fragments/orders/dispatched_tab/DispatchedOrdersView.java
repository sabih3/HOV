package ae.netaq.homesorder_vendor.fragments.orders.dispatched_tab;

import java.util.List;

import ae.netaq.homesorder_vendor.db.tables.OrderTable;

/**
 * Created by Netaq on 11/22/2017.
 */

public interface DispatchedOrdersView {

    void showDataView(List<OrderTable> orders);

    void showEmptyView();
}
