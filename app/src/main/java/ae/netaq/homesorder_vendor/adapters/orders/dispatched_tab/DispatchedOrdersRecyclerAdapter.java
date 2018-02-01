package ae.netaq.homesorder_vendor.adapters.orders.dispatched_tab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.orders.viewholder.NewOrderHolder;
import ae.netaq.homesorder_vendor.db.tables.OrderTable;

/**
 * Created by Netaq on 11/23/2017.
 */

public class DispatchedOrdersRecyclerAdapter extends RecyclerView.Adapter<NewOrderHolder>{

    private List<OrderTable> mDataset;
    private Context mContext;

    public DispatchedOrdersRecyclerAdapter(List<OrderTable> mDataset) {

        this.mDataset = mDataset;
    }

    @Override
    public NewOrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        NewOrderHolder viewHolder = new NewOrderHolder(LayoutInflater.from(mContext).inflate(R.layout.order_list_item, parent, false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewOrderHolder holder, int position) {
        OrderTable dispatchedorder = mDataset.get(position);

        long orderID = dispatchedorder.getOrderID();
        String address = dispatchedorder.getAddress();
        String name = dispatchedorder.getName();

        holder.orderId.setText(String.valueOf(orderID));
        holder.customerAddress.setText(address);
        holder.CustomerName.setText(name);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
