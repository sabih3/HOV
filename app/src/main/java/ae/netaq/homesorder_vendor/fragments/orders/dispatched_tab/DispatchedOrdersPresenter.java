package ae.netaq.homesorder_vendor.fragments.orders.dispatched_tab;

import android.content.Context;

import ae.netaq.homesorder_vendor.data_manager.DataManager;

/**
 * Created by Netaq on 11/22/2017.
 */

public class DispatchedOrdersPresenter {

    private DispatchedOrdersView dispatchedOrdersView;

    public DispatchedOrdersPresenter(DispatchedOrdersView dispatchedOrdersView) {
        this.dispatchedOrdersView = dispatchedOrdersView;
    }

    public void getDispatchedOrdersList(Context context){
        dispatchedOrdersView.onDispatchedOrdersFetched(DataManager.getDispatchedOrdersList(context));
    }
}
