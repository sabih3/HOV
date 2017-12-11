package ae.netaq.homesorder_vendor.fragments.orders.ready_tab;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import ae.netaq.homesorder_vendor.db.data_manager.OrderDataManager;
import ae.netaq.homesorder_vendor.db.data_manager.tables.OrderTable;
import ae.netaq.homesorder_vendor.models.Orders;

/**
 * Created by Netaq on 11/22/2017.
 */

public class ReadyOrdersPresenter {

    private ReadyOrdersView readyOrdersView;

    public ReadyOrdersPresenter(ReadyOrdersView readyOrdersView) {
        this.readyOrdersView = readyOrdersView;
    }

    public void getReadyOrdersList(Context context){
        List<OrderTable> readyOrdersList = null;
        try {
            readyOrdersList = OrderDataManager.getReadyOrdersList(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        readyOrdersView.onReadyOrdersFetched(readyOrdersList);
    }
}
