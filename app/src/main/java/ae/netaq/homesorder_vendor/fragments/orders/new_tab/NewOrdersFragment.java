package ae.netaq.homesorder_vendor.fragments.orders.new_tab;

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

import java.sql.SQLException;
import java.util.List;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.orders.new_tab.NewOrdersRecyclerAdapter;
import ae.netaq.homesorder_vendor.db.data_manager.OrderDataManager;
import ae.netaq.homesorder_vendor.db.data_manager.tables.OrderTable;
import ae.netaq.homesorder_vendor.event_bus.OrderMovedToProcess;
import ae.netaq.homesorder_vendor.utils.Common;
import ae.netaq.homesorder_vendor.utils.OrderManagementUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 11/22/2017.
 */

public class NewOrdersFragment extends Fragment implements NewOrdersView,
             NewOrdersRecyclerAdapter.OptionButtonClickListener{

    @BindView(R.id.listing_recycler)
    RecyclerView newOrdersRecycler;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private NewOrdersPresenter newOrdersPresenter;
    private long orderID ;

    public NewOrdersFragment() {
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

        Common.changeViewWithLocale(getActivity(), view);
        newOrdersPresenter = new NewOrdersPresenter(this);
        newOrdersPresenter.getNewOrdersList(getActivity());


        return view;
    }


    @Override
    public void onNewOrdersFetched(List<OrderTable> orders) {

        NewOrdersRecyclerAdapter newOrdersRecyclerAdapter = new NewOrdersRecyclerAdapter(orders);
        newOrdersRecyclerAdapter.setOptionButtonListener(this);
        newOrdersRecycler.setLayoutManager(new LinearLayoutManager(getContext(),
                                           LinearLayoutManager.VERTICAL,false));
        newOrdersRecycler.setAdapter(newOrdersRecyclerAdapter);
    }

    @Override
    public void onOptionButtonSelected(final long orderID) {
        this.orderID = orderID;
        OrderManagementUtils.showDialogForNew(getContext(),
                             new OrderManagementUtils.StageSelectListener() {
            @Override
            public void onStageSelected() {
                try {
                    OrderDataManager.updateOrder(orderID,OrderDataManager.STATUS_PROCESSING);
                    newOrdersPresenter.getNewOrdersList(getActivity());
                    EventBus.getDefault().post(new OrderMovedToProcess());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
