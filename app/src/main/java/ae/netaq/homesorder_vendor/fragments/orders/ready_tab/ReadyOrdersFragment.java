package ae.netaq.homesorder_vendor.fragments.orders.ready_tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.orders.processing_tab.ProcessingOrdersRecyclerAdapter;
import ae.netaq.homesorder_vendor.adapters.orders.ready_tab.ReadyOrdersRecyclerAdapter;
import ae.netaq.homesorder_vendor.models.OrdersResponse;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 11/22/2017.
 */

public class ReadyOrdersFragment extends Fragment {

    @BindView(R.id.listing_recycler)
    RecyclerView processingOrdersRecycler;

    public ReadyOrdersFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listing_layout, container, false);
        ButterKnife.bind(this, view);
        initViews();

        return view;
    }

    private void initViews() {

        ReadyOrdersRecyclerAdapter readyOrdersRecyclerAdapter = new ReadyOrdersRecyclerAdapter(OrdersResponse.getOrdersList());
        processingOrdersRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        processingOrdersRecycler.setAdapter(readyOrdersRecyclerAdapter);

    }
}
