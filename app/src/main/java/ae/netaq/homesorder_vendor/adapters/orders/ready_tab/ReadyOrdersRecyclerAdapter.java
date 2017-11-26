package ae.netaq.homesorder_vendor.adapters.orders.ready_tab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.orders.viewholder.OrdersRecyclerViewHolder;
import ae.netaq.homesorder_vendor.models.OrdersResponse;

/**
 * Created by Netaq on 11/23/2017.
 */

public class ReadyOrdersRecyclerAdapter extends RecyclerView.Adapter<OrdersRecyclerViewHolder>{

    ArrayList<OrdersResponse> mDataset;

    public ReadyOrdersRecyclerAdapter(ArrayList<OrdersResponse> mDataset) {
        this.mDataset = mDataset;
    }

    @Override
    public OrdersRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        OrdersRecyclerViewHolder viewHolder = new OrdersRecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list_item, parent, false));

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(OrdersRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
