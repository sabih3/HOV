package ae.netaq.homesorder_vendor.adapters.order_detail_products_listing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.order_detail_products_listing.viewholder.OrderDetailProductItemViewHolder;
import ae.netaq.homesorder_vendor.utils.NavigationController;

/**
 * Created by Deena on 29/11/2017.
 */

public class OrderDetailProductItemRecyclerAdapter extends RecyclerView.Adapter<OrderDetailProductItemViewHolder> {

    Context mContext;

    public OrderDetailProductItemRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public OrderDetailProductItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OrderDetailProductItemViewHolder viewHolder =new OrderDetailProductItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_detail_product_listing_item,parent,false)) ;
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(OrderDetailProductItemViewHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationController.startActivityProductDetail(mContext);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 7;
    }
}
