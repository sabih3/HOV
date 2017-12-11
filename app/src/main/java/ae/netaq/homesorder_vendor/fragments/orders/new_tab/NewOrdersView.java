package ae.netaq.homesorder_vendor.fragments.orders.new_tab;

import java.util.List;

import ae.netaq.homesorder_vendor.db.data_manager.tables.OrderTable;

/**
 * Created by Netaq on 11/22/2017.
 */

public interface NewOrdersView {
    void onNewOrdersFetched(List<OrderTable> orders);
}
