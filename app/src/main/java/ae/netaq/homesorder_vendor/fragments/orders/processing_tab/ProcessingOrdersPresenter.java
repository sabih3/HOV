package ae.netaq.homesorder_vendor.fragments.orders.processing_tab;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import ae.netaq.homesorder_vendor.db.data_manager.OrderDataManager;
import ae.netaq.homesorder_vendor.db.data_manager.tables.OrderTable;

/**
 * Created by Netaq on 11/22/2017.
 */

public class ProcessingOrdersPresenter {

    private ProcessingOrdersView processingOrderView;

    public ProcessingOrdersPresenter(ProcessingOrdersView processingOrderView) {
        this.processingOrderView = processingOrderView;
    }

    public void getProcessingOrdersList(Context context){
        try {

            List<OrderTable> processingOrdersList = OrderDataManager.getProcessingOrdersList(context);
            processingOrderView.onProcessingOrdersFetched(processingOrdersList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
