package ae.netaq.homesorder_vendor.adapters.products.promotions_tab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.products.viewholder.ProductsRecyclerViewHolder;
import ae.netaq.homesorder_vendor.models.OrdersResponse;
import ae.netaq.homesorder_vendor.models.ProductsResponse;

/**
 * Created by Netaq on 11/23/2017.
 */

public class PromotionsProductsRecyclerAdapter extends RecyclerView.Adapter<ProductsRecyclerViewHolder>{

    ArrayList<ProductsResponse> mDataset;

    public PromotionsProductsRecyclerAdapter(ArrayList<ProductsResponse> mDataset) {
        this.mDataset = mDataset;
    }

    @Override
    public ProductsRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ProductsRecyclerViewHolder viewHolder = new ProductsRecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item, parent, false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProductsRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
