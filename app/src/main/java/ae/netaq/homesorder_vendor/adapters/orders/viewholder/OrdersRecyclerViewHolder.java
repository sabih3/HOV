package ae.netaq.homesorder_vendor.adapters.orders.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ae.netaq.homesorder_vendor.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 11/23/2017.
 */

public class OrdersRecyclerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.list_item_order_id)
    public TextView orderId;

    @BindView(R.id.list_item_order_status)
    public TextView orderStatus;

    @BindView(R.id.list_item_order_date)
    public TextView orderDate;

    @BindView(R.id.list_item_order_time)
    public TextView orderTime;

    @BindView(R.id.list_item_customer_address)
    public TextView customerAddress;

    @BindView(R.id.list_item_customer_name)
    public TextView CustomerName;

    @BindView(R.id.list_item_context_menu_button)
    public ImageView contextMenuBtn;

    public OrdersRecyclerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
