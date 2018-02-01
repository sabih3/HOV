package ae.netaq.homesorder_vendor.fragments.orders.dispatched_tab;

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

import java.util.List;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.orders.dispatched_tab.DispatchedOrdersRecyclerAdapter;
import ae.netaq.homesorder_vendor.db.tables.OrderTable;
import ae.netaq.homesorder_vendor.event_bus.OrderMoveToDispatch;
import ae.netaq.homesorder_vendor.event_bus.OrderReloadEvent;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 11/22/2017.
 */

public class DispatchedOrdersFragment extends Fragment implements DispatchedOrdersView{

    @BindView(R.id.listing_recycler)
    RecyclerView processingOrdersRecycler;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.empty_orders_parent)
    RelativeLayout emptyOrdersParent;

    @BindView(R.id.image_no_orders)
    ImageView emptyOrderImageView;

    @BindView(R.id.text_no_orders)
    TextView emptyOrdersText;

    private DispatchedOrdersPresenter dispatchedOrdersPresenter;

    public DispatchedOrdersFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listing_layout, container, false);
        ButterKnife.bind(this, view);

        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        emptyOrderImageView.setImageResource(R.drawable.ic_empty_dispatched);
        emptyOrdersText.setText("You dont have dispatched any orders");

        dispatchedOrdersPresenter = new DispatchedOrdersPresenter(this);
        dispatchedOrdersPresenter.getDispatchedOrdersList(getActivity());
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshListener());
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onOrdersReload(OrderReloadEvent orderReloadEvent){
        dispatchedOrdersPresenter.getDispatchedOrdersList(getContext());
    }

    //ReadyOrderFragment.onContextItemSelected
    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onOrderedMovedToDispatch(OrderMoveToDispatch orderMoveToDispatch){
        dispatchedOrdersPresenter.getDispatchedOrdersList(getActivity());
    }

    @Override
    public void showDataView(List<OrderTable> orders) {
        swipeRefreshLayout.setRefreshing(false);
        processingOrdersRecycler.setVisibility(View.VISIBLE);
        emptyOrdersParent.setVisibility(View.GONE);
        DispatchedOrdersRecyclerAdapter dispatchedOrdersRecyclerAdapter =
                                        new DispatchedOrdersRecyclerAdapter(orders);

        processingOrdersRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        processingOrdersRecycler.setAdapter(dispatchedOrdersRecyclerAdapter);
    }

    @Override
    public void showEmptyView() {
        swipeRefreshLayout.setRefreshing(false);
        processingOrdersRecycler.setVisibility(View.GONE);
        emptyOrdersParent.setVisibility(View.VISIBLE);
    }

    private class SwipeRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            dispatchedOrdersPresenter.getDispatchedOrdersList(getContext());
        }
    }
}
