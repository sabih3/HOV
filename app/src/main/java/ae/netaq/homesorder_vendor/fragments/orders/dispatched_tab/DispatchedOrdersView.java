package ae.netaq.homesorder_vendor.fragments.orders.dispatched_tab;

import ae.netaq.homesorder_vendor.models.Orders;

/**
 * Created by Netaq on 11/22/2017.
 */

public interface DispatchedOrdersView {
    void onDispatchedOrdersFetched(Orders orders);
}
