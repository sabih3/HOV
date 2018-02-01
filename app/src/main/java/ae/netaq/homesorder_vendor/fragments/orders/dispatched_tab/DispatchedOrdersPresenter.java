package ae.netaq.homesorder_vendor.fragments.orders.dispatched_tab;

import android.content.Context;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ae.netaq.homesorder_vendor.db.data_manager.OrderDataManager;
import ae.netaq.homesorder_vendor.db.tables.OrderTable;

/**
 * Created by Netaq on 11/22/2017.
 */

public class DispatchedOrdersPresenter {

    private DispatchedOrdersView dispatchedOrdersView;

    public DispatchedOrdersPresenter(DispatchedOrdersView dispatchedOrdersView) {
        this.dispatchedOrdersView = dispatchedOrdersView;
    }

    public void getDispatchedOrdersList(Context context){
        List<OrderTable> dispatchedOrdersList = new ArrayList<>();
        try {
            dispatchedOrdersList =
                             OrderDataManager.getDispatchedOrdersList(context);


        } catch (SQLException e) {
            //Todo: Handle exception for view
            e.printStackTrace();
        }

        if(dispatchedOrdersList.isEmpty()){
            dispatchedOrdersView.showEmptyView();
        }else{
            dispatchedOrdersView.showDataView(dispatchedOrdersList);
        }


    }
}
