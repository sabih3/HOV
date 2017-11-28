package ae.netaq.homesorder_vendor.fragments.orders.new_tab;

import android.content.Context;

import ae.netaq.homesorder_vendor.data_manager.DataManager;

/**
 * Created by Netaq on 11/22/2017.
 */

public class NewOrdersPresenter {

    private NewOrdersView newOrdersView;

    public NewOrdersPresenter(NewOrdersView newOrdersView) {
        this.newOrdersView = newOrdersView;
    }

    public void getNewOrdersList(Context context){
        newOrdersView.onNewOrdersFetched(DataManager.getNewOrdersList(context));
    }
}
