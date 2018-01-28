package ae.netaq.homesorder_vendor.fragments.orders.processing_tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.sql.SQLException;
import java.util.List;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.orders.processing_tab.ProcessingOrdersRecyclerAdapter;
import ae.netaq.homesorder_vendor.db.data_manager.OrderDataManager;
import ae.netaq.homesorder_vendor.db.data_manager.tables.OrderTable;
import ae.netaq.homesorder_vendor.event_bus.OrderMoveToReady;
import ae.netaq.homesorder_vendor.event_bus.OrderMovedToProcess;
import ae.netaq.homesorder_vendor.utils.OrderManagementUtils;
import ae.netaq.homesorder_vendor.utils.UIUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 11/22/2017.
 */

public class ProcessingOrdersFragment extends Fragment implements ProcessingOrdersView,
             ProcessingOrdersRecyclerAdapter.OptionButtonClickListener {

    @BindView(R.id.listing_recycler)
    RecyclerView processingOrdersRecycler;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private ProcessingOrdersPresenter processingOrdersPresenter;
    private long orderID ;

    public ProcessingOrdersFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listing_layout, container, false);
        ButterKnife.bind(this, view);

        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

        processingOrdersPresenter = new ProcessingOrdersPresenter(this);
        processingOrdersPresenter.getProcessingOrdersList(getActivity());
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshListener());
        return view;
    }


    //NewOrderFragment.onContextItemSelected
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onOrderMovedToProcessing(OrderMovedToProcess orderStatusChangeEvent){
        processingOrdersPresenter.getProcessingOrdersList(getActivity());
    }

    @Override
    public void onProcessingOrdersFetched(List<OrderTable> orders) {
        ProcessingOrdersRecyclerAdapter processingOrdersRecyclerAdapter =
                                        new ProcessingOrdersRecyclerAdapter(orders);
        processingOrdersRecyclerAdapter.setOptionButtonClickListener(this);
        processingOrdersRecycler.setLayoutManager(new LinearLayoutManager(getContext(),
                                 LinearLayoutManager.VERTICAL,false));

        processingOrdersRecycler.setAdapter(processingOrdersRecyclerAdapter);
    }

    @Override
    public void showProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showEmptyView() {
        //TODO: show empty view for Processing Order Fragment
    }

    @Override
    public void onProductsSynced() {
        processingOrdersPresenter.getProcessingOrdersList(getActivity());
    }

    @Override
    public void onOrderUpdateSuccessfully() {
        try {
            OrderDataManager.updateOrder(orderID,
                    OrderDataManager.STATUS_READY);
            processingOrdersPresenter.getProcessingOrdersList(getContext());
            EventBus.getDefault().post(new OrderMoveToReady());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onOrderUpdateException(String resolvedError) {
        UIUtils.showToast(getContext(),resolvedError);
    }

    @Override
    public void onOptionButtonSelected(final long orderID) {
        this.orderID = orderID;
        OrderManagementUtils.showDialogForProcessing(getContext(),
                new OrderManagementUtils.StageSelectListener() {
            @Override
            public void onStageSelected() {

                //Show Dialog, Do this work in confirmation YES
                UIUtils.showMessageDialog(getContext(), getString(R.string.confirmation_update_order_ready), getString(R.string.dialog_btn_ok),
                        getString(R.string.dialog_btn_cancel), new UIUtils.DialogButtonListener() {
                    @Override
                    public void onPositiveButtonClicked() {
                        processingOrdersPresenter.updateOrderAsReady(orderID);
                    }

                    @Override
                    public void onNegativeButtonClicked() {
                        //Nothing needed, dialog will be dismissed automatically
                    }
                });



            }
        });
    }

    @Override
    public void onNetworkFailure() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onUnDefinedException(String localizedError) {

    }

    private class SwipeRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            processingOrdersPresenter.syncProcessingOrders();
        }
    }
}
