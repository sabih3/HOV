package ae.netaq.homesorder_vendor.adapters.orders.ready_tab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.orders.viewholder.NewOrderHolder;
import ae.netaq.homesorder_vendor.db.data_manager.tables.OrderTable;
import ae.netaq.homesorder_vendor.models.Order;

/**
 * Created by Netaq on 11/23/2017.
 */

public class ReadyOrdersRecyclerAdapter extends RecyclerView.Adapter<NewOrderHolder>{

    private List<OrderTable> mDataset;
    private Context mContext;

    private OptionButtonClickListener optionButtonClickListener;

    public void setOptionButtonClickListener(OptionButtonClickListener optionButtonClickListener) {
        this.optionButtonClickListener = optionButtonClickListener;
    }

    public ReadyOrdersRecyclerAdapter(List<OrderTable> mDataset) {
        this.mDataset = mDataset;
    }

    @Override
    public NewOrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        NewOrderHolder viewHolder = new NewOrderHolder(LayoutInflater.from(mContext).
                                    inflate(R.layout.order_list_item, parent, false));

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(NewOrderHolder holder, int position) {
        OrderTable readyOrder = mDataset.get(position);
        final long orderID = readyOrder.getOrderID();
        String address = readyOrder.getAddress();
        String name = readyOrder.getName();

        holder.orderId.setText(String.valueOf(orderID));
        holder.customerAddress.setText(address);
        holder.CustomerName.setText(name);

        holder.orderStatus.setText(mContext.getResources().getString(R.string.status_ready));

        holder.contextMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionButtonClickListener.onOptionButtonSelected(orderID);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface OptionButtonClickListener{

        void onOptionButtonSelected(long orderID);
    }
}
