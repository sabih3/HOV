package ae.netaq.homesorder_vendor.fragments.orders.processing_tab;

import android.content.Context;

import ae.netaq.homesorder_vendor.data_manager.DataManager;

/**
 * Created by Netaq on 11/22/2017.
 */

public class ProcessingOrdersPresenter {

    private ProcessingOrdersView processingOrderView;

    public ProcessingOrdersPresenter(ProcessingOrdersView processingOrderView) {
        this.processingOrderView = processingOrderView;
    }

    public void getProcessingOrdersList(Context context){
        processingOrderView.onProcessingOrdersFetched(DataManager.getProcessingOrdersList(context));
    }
}
