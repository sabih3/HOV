package ae.netaq.homesorder_vendor.adapters.orders.new_tab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.orders.viewholder.NewOrderHolder;
import ae.netaq.homesorder_vendor.db.data_manager.tables.OrderTable;
import ae.netaq.homesorder_vendor.utils.NavigationController;

/**
 * Created by Netaq on 11/23/2017.
 */

public class NewOrdersRecyclerAdapter extends RecyclerView.Adapter<NewOrderHolder>{

    private List<OrderTable> mDataset;
    private Context mContext;
    private OptionButtonClickListener optionButtonListener;

    public void setOptionButtonListener(OptionButtonClickListener optionButtonListener) {
        this.optionButtonListener = optionButtonListener;
    }

    public NewOrdersRecyclerAdapter(List<OrderTable> mDataset) {
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
        OrderTable orderTable = mDataset.get(position);
        final long orderID = orderTable.getOrderID();
        String address = orderTable.getAddress();
        String name = orderTable.getName();

        holder.orderId.setText(String.valueOf(orderID));
        holder.customerAddress.setText(address);
        holder.CustomerName.setText(name);

        holder.contextMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionButtonListener.onOptionButtonSelected(orderID);
            }
        });

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

    public interface OptionButtonClickListener{

        void onOptionButtonSelected(long orderID);
    }

}
