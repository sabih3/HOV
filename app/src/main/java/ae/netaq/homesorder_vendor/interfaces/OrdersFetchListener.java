package ae.netaq.homesorder_vendor.interfaces;

import ae.netaq.homesorder_vendor.models.OrdersResponse;

/**
 * Created by Netaq on 11/28/2017.
 */

public interface OrdersFetchListener {
    void onOrdersFetched(OrdersResponse response);
    void onOrdersNotFetched();
}
