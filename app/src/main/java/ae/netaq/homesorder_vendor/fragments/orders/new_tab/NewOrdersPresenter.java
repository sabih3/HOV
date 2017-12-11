package ae.netaq.homesorder_vendor.fragments.orders.new_tab;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import ae.netaq.homesorder_vendor.db.data_manager.OrderDataManager;
import ae.netaq.homesorder_vendor.db.data_manager.tables.OrderTable;
import ae.netaq.homesorder_vendor.models.Orders;

/**
 * Created by Netaq on 11/22/2017.
 */

public class NewOrdersPresenter {

    private NewOrdersView newOrdersView;

    public NewOrdersPresenter(NewOrdersView newOrdersView) {
        this.newOrdersView = newOrdersView;
    }

    public void getNewOrdersList(Context context){
        List<OrderTable> newOrdersList = null;
        try {
            newOrdersList = OrderDataManager.getNewOrdersList(context);
            newOrdersView.onNewOrdersFetched(newOrdersList);
        } catch (SQLException e) {
            //Todo: handle exception here for view
            e.printStackTrace();
        }

    }
}
