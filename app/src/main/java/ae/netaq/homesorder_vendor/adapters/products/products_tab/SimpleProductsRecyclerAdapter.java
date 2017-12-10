package ae.netaq.homesorder_vendor.adapters.products.products_tab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.products.viewholder.ProductsRecyclerViewHolder;
import ae.netaq.homesorder_vendor.models.ProductsResponse;
import ae.netaq.homesorder_vendor.utils.NavigationController;

/**
 * Created by Netaq on 11/23/2017.
 */

public class SimpleProductsRecyclerAdapter extends RecyclerView.Adapter<ProductsRecyclerViewHolder>{

    ArrayList<ProductsResponse> mDataset;

    Context mContext;

    public SimpleProductsRecyclerAdapter(ArrayList<ProductsResponse> mDataset, Context mContext) {
        this.mDataset = mDataset;
        this.mContext = mContext;
    }

    @Override
    public ProductsRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ProductsRecyclerViewHolder viewHolder = new ProductsRecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item, parent, false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProductsRecyclerViewHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationController.startActivityProductDetail(mContext);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
