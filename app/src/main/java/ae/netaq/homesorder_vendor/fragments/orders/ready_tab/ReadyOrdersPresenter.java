package ae.netaq.homesorder_vendor.fragments.orders.ready_tab;

import android.content.Context;

import ae.netaq.homesorder_vendor.data_manager.DataManager;

/**
 * Created by Netaq on 11/22/2017.
 */

public class ReadyOrdersPresenter {

    private ReadyOrdersView readyOrdersView;

    public ReadyOrdersPresenter(ReadyOrdersView readyOrdersView) {
        this.readyOrdersView = readyOrdersView;
    }

    public void getReadyOrdersList(Context context){
        readyOrdersView.onReadyOrdersFetched(DataManager.getReadyOrdersList(context));
    }
}
