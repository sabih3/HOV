package ae.netaq.homesorder_vendor.fragments.orders.ready_tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.sql.SQLException;
import java.util.List;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.orders.ready_tab.ReadyOrdersRecyclerAdapter;
import ae.netaq.homesorder_vendor.db.data_manager.OrderDataManager;
import ae.netaq.homesorder_vendor.db.tables.OrderTable;
import ae.netaq.homesorder_vendor.event_bus.OrderMoveToDispatch;
import ae.netaq.homesorder_vendor.event_bus.OrderMoveToReady;
import ae.netaq.homesorder_vendor.event_bus.OrderReloadEvent;
import ae.netaq.homesorder_vendor.utils.OrderManagementUtils;
import ae.netaq.homesorder_vendor.utils.UIUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 11/22/2017.
 */

public class ReadyOrdersFragment extends Fragment implements ReadyOrdersView,
             ReadyOrdersRecyclerAdapter.OptionButtonClickListener{

    @BindView(R.id.listing_recycler)
    RecyclerView readyOrdersRecycler;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.empty_orders_parent)
    RelativeLayout emptyOrdersParent;

    @BindView(R.id.image_no_orders)
    ImageView emptyOrderImageView;

    @BindView(R.id.text_no_orders)
    TextView emptyOrdersText;

    private ReadyOrdersPresenter readyOrdersPresenter;
    private long orderID;

    public ReadyOrdersFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listing_layout, container, false);
        ButterKnife.bind(this, view);

        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        emptyOrderImageView.setImageResource(R.drawable.ic_empty_ready);
        emptyOrdersText.setText("You don't have ready orders");

        readyOrdersPresenter = new ReadyOrdersPresenter(this);
        readyOrdersPresenter.getReadyOrdersList(getActivity());
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshListener());
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onOrdersReload(OrderReloadEvent orderReloadEvent){
        readyOrdersPresenter.getReadyOrdersList(getContext());
    }

    //ProcessingOrderFragment.onContextItemSelected
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onOrderMovedToReady(OrderMoveToReady orderMoveToReadyEvent){
        readyOrdersPresenter.getReadyOrdersList(getActivity());
    }

    //From DB
    //readyOrdersPresenter.getReadyOrdersList
    @Override
    public void onReadyOrdersFetched(List<OrderTable> readyOrders) {
        swipeRefreshLayout.setRefreshing(false);

        emptyOrdersParent.setVisibility(View.GONE);
        readyOrdersRecycler.setVisibility(View.VISIBLE);

        ReadyOrdersRecyclerAdapter readyOrdersRecyclerAdapter =
                                    new ReadyOrdersRecyclerAdapter(readyOrders);

        readyOrdersRecyclerAdapter.setOptionButtonClickListener(this);
        readyOrdersRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        readyOrdersRecycler.setAdapter(readyOrdersRecyclerAdapter);
    }

    @Override
    public void showEmptyView() {
        swipeRefreshLayout.setRefreshing(false);
        readyOrdersRecycler.setVisibility(View.GONE);
        emptyOrdersParent.setVisibility(View.VISIBLE);
    }

    @Override
    public void onReadyOrdersSynced() {
        readyOrdersPresenter.getReadyOrdersList(getContext());
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onOrderUpdatedSuccessfully() {
        try {
            OrderDataManager.updateOrder(getContext(),orderID,OrderDataManager.STATUS_DISPATCHED);
            readyOrdersPresenter.getReadyOrdersList(getActivity());
            EventBus.getDefault().post(new OrderMoveToDispatch());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onOrderUpdateException(String resolvedError) {
        //TODO: Handle exception in Ready View
    }



    @Override
    public void onOptionButtonSelected(final long orderID) {
        this.orderID = orderID;
        OrderManagementUtils.showDialogForReady(getContext(),
                new OrderManagementUtils.StageSelectListener() {
            @Override
            public void onStageSelected() {
                UIUtils.showMessageDialog(getContext(), getString(R.string.confirmation_update_order_dispatch), getString(R.string.dialog_btn_ok),
                        getString(R.string.dialog_btn_cancel), new UIUtils.DialogButtonListener() {
                            @Override
                            public void onPositiveButtonClicked() {
                                readyOrdersPresenter.updateOrderAsDispatched(orderID);
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
        //TODO: Handle exception in Ready View
    }

    @Override
    public void onUnDefinedException(String localizedError) {
        //TODO: Handle exception in view
    }

    private class SwipeRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            readyOrdersPresenter.getReadyOrdersList(getContext());
            //readyOrdersPresenter.syncReadyOrders();
        }
    }
}
