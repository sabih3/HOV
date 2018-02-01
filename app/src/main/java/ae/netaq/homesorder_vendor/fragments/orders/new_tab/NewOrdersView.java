package ae.netaq.homesorder_vendor.fragments.orders.new_tab;

import java.util.List;

import ae.netaq.homesorder_vendor.activities.GeneralView;
import ae.netaq.homesorder_vendor.db.tables.OrderTable;

/**
 * Created by Netaq on 11/22/2017.
 */

public interface NewOrdersView extends GeneralView{


    void showProgress();

    void hideProgress();

    void showEmptyView();

    void showDataView(List<OrderTable> orders);

    void onOrderUpdatedSuccessfully();

    void onOrderUpdateException(String resolvedError);

    void onAllOrdersSynced();
}
