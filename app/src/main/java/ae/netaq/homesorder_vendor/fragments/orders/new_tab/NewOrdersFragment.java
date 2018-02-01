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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.sql.SQLException;
import java.util.List;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.orders.new_tab.NewOrdersRecyclerAdapter;
import ae.netaq.homesorder_vendor.db.data_manager.OrderDataManager;
import ae.netaq.homesorder_vendor.db.tables.OrderTable;
import ae.netaq.homesorder_vendor.event_bus.OrderMovedToProcess;
import ae.netaq.homesorder_vendor.utils.Common;
import ae.netaq.homesorder_vendor.utils.OrderManagementUtils;
import ae.netaq.homesorder_vendor.utils.UIUtils;
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

    @BindView(R.id.empty_orders_parent)
    RelativeLayout emptyOrdersParent;

    @BindView(R.id.image_no_orders)
    ImageView emptyOrderImageView;

    @BindView(R.id.text_no_orders)
    TextView emptyOrdersText;

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

        // setting resources for this view's empty Layout
        //Icon and text are different
        emptyOrderImageView.setImageResource(R.drawable.ic_empty_new);
        emptyOrdersText.setText("You dont have any new orders");


        newOrdersPresenter = new NewOrdersPresenter(this);
        newOrdersPresenter.getAllOrdersSnapshot(getContext());

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshListener());

        return view;
    }

    //This will be triggered from NewOrdersPresenter.getAllOrdersSnapshot
    @Override
    public void onAllOrdersSynced() {
        newOrdersPresenter.getNewOrdersList(getContext());

    }

    //This will be triggered from NewOrdersPresenter.getNewOrdersList
    @Override
    public void showDataView(List<OrderTable> orders) {
        swipeRefreshLayout.setRefreshing(false);
        newOrdersRecycler.setVisibility(View.VISIBLE);
        emptyOrdersParent.setVisibility(View.GONE);

        NewOrdersRecyclerAdapter newOrdersRecyclerAdapter = new NewOrdersRecyclerAdapter(orders);
        newOrdersRecyclerAdapter.setOptionButtonListener(this);
        newOrdersRecycler.setLayoutManager(new LinearLayoutManager(getContext(),
                                           LinearLayoutManager.VERTICAL,false));
        newOrdersRecycler.setAdapter(newOrdersRecyclerAdapter);

    }

    //This will be triggered from NewOrdersPresenter.getNewOrdersList
    @Override
    public void showEmptyView() {
        swipeRefreshLayout.setRefreshing(false);
        newOrdersRecycler.setVisibility(View.GONE);
        emptyOrdersParent.setVisibility(View.VISIBLE);
    }


    //To update Order status
    @Override
    public void onOptionButtonSelected(final long orderID) {
        this.orderID = orderID;
        OrderManagementUtils.showDialogForNew(getContext(),
                new OrderManagementUtils.StageSelectListener() {
                    @Override
                    public void onStageSelected() {


                        String confirmationMessageForProcessing =
                                getString(R.string.confirmation_update_order_processing);

                        //Confirming user about really order status update
                        UIUtils.showMessageDialog(getContext(), confirmationMessageForProcessing,
                                getString(R.string.dialog_btn_ok), getString(R.string.dialog_btn_cancel),
                                new UIUtils.DialogButtonListener() {
                                    @Override
                                    public void onPositiveButtonClicked() {
                                        newOrdersPresenter.updateOrderAsProcessing(orderID);
                                    }

                                    @Override
                                    public void onNegativeButtonClicked() {

                                    }
                                });


                    }
                });
    }

    //NewOrdersPresenter.updateOrderAsProcessing
    @Override
    public void onOrderUpdatedSuccessfully() {
        try {
            OrderDataManager.updateOrder(getContext(),orderID,OrderDataManager.STATUS_PROCESSING);
            newOrdersPresenter.getNewOrdersList(getActivity());
            //This event will be posted in ProcessingOrdersFragment,to let it reload its data
            EventBus.getDefault().post(new OrderMovedToProcess());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //NewOrdersPresenter.updateOrderAsProcessing
    @Override
    public void onOrderUpdateException(String resolvedError) {
        UIUtils.showToast(getContext(),resolvedError);
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
    public void onNetworkFailure() {
        //TODO: Handle Network failure in New Order Fragment
    }

    @Override
    public void onUnDefinedException(String localizedError) {
        //TODO: Handle Un defined Exception for New orders Fragment
    }

    private class SwipeRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            newOrdersPresenter.getAllOrdersSnapshot(getContext());
        }
    }
}
