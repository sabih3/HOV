package ae.netaq.homesorder_vendor.adapters.orders.processing_tab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.orders.viewholder.OrdersRecyclerViewHolder;
import ae.netaq.homesorder_vendor.models.Order;

/**
 * Created by Netaq on 11/23/2017.
 */

public class ProcessingOrdersRecyclerAdapter extends RecyclerView.Adapter<OrdersRecyclerViewHolder>{

    ArrayList<Order> mDataset;

    public ProcessingOrdersRecyclerAdapter(ArrayList<Order> mDataset) {
        this.mDataset = mDataset;
    }

    @Override
    public OrdersRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        OrdersRecyclerViewHolder viewHolder = new OrdersRecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list_item, parent, false));

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(OrdersRecyclerViewHolder holder, int position) {
        holder.orderId.setText(String.valueOf(mDataset.get(position).getOrderID()));
        holder.customerAddress.setText(mDataset.get(position).getCustomer().getAddress());
        holder.CustomerName.setText(mDataset.get(position).getCustomer().getName());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}