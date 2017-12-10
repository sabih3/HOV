package ae.netaq.homesorder_vendor.adapters.orders.new_tab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.orders.viewholder.OrdersRecyclerViewHolder;
import ae.netaq.homesorder_vendor.models.Orders;
import ae.netaq.homesorder_vendor.utils.NavigationController;

/**
 * Created by Netaq on 11/23/2017.
 */

public class NewOrdersRecyclerAdapter extends RecyclerView.Adapter<OrdersRecyclerViewHolder>{

    private ArrayList<Orders.Order> mDataset;

    private Context mContext;

    public NewOrdersRecyclerAdapter(ArrayList<Orders.Order> mDataset, Context mContext) {
        this.mDataset = mDataset;
        this.mContext = mContext;
    }

    @Override
    public OrdersRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        OrdersRecyclerViewHolder viewHolder =
                new OrdersRecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list_item, parent, false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OrdersRecyclerViewHolder holder, int position) {
        holder.orderId.setText(String.valueOf(mDataset.get(position).getOrderID()));
        holder.customerAddress.setText(mDataset.get(position).getCustomer().getAddress());
        holder.CustomerName.setText(mDataset.get(position).getCustomer().getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationController.startActivityOrderDetail(mContext);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
