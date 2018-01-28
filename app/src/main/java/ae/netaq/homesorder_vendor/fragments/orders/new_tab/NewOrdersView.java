package ae.netaq.homesorder_vendor.fragments.orders.new_tab;

import java.util.List;

import ae.netaq.homesorder_vendor.activities.GeneralView;
import ae.netaq.homesorder_vendor.network.model.ResponseOrderList;

/**
 * Created by Netaq on 11/22/2017.
 */

public interface NewOrdersView extends GeneralView{
    void onNewOrdersFetched(List<ResponseOrderList> orders);

    void showProgress();

    void hideProgress();

    void showEmptyDataView();
}
