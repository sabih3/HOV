package ae.netaq.homesorder_vendor.fragments.orders.ready_tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.sql.SQLException;
import java.util.List;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.orders.ready_tab.ReadyOrdersRecyclerAdapter;
import ae.netaq.homesorder_vendor.db.data_manager.OrderDataManager;
import ae.netaq.homesorder_vendor.db.data_manager.tables.OrderTable;
import ae.netaq.homesorder_vendor.event_bus.OrderMoveToDispatch;
import ae.netaq.homesorder_vendor.event_bus.OrderMoveToReady;
import ae.netaq.homesorder_vendor.utils.OrderManagementUtils;
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


        readyOrdersPresenter = new ReadyOrdersPresenter(this);
        readyOrdersPresenter.getReadyOrdersList(getActivity());
        return view;
    }


    //ProcessingOrderFragment.onContextItemSelected
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onOrderMovedToReady(OrderMoveToReady orderMoveToReadyEvent){
        readyOrdersPresenter.getReadyOrdersList(getActivity());
    }

    @Override
    public void onReadyOrdersFetched(List<OrderTable> readyOrders) {
        ReadyOrdersRecyclerAdapter readyOrdersRecyclerAdapter =
                                    new ReadyOrdersRecyclerAdapter(readyOrders);

        readyOrdersRecyclerAdapter.setOptionButtonClickListener(this);
        readyOrdersRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        readyOrdersRecycler.setAdapter(readyOrdersRecyclerAdapter);
    }

    @Override
    public void onOptionButtonSelected(final long orderID) {
        this.orderID = orderID;
        OrderManagementUtils.showDialogForReady(getContext(),
                new OrderManagementUtils.StageSelectListener() {
            @Override
            public void onStageSelected() {
                try {
                    OrderDataManager.updateOrder(orderID,OrderDataManager.STATUS_DISPATCHED);
                    readyOrdersPresenter.getReadyOrdersList(getActivity());
                    EventBus.getDefault().post(new OrderMoveToDispatch());

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
