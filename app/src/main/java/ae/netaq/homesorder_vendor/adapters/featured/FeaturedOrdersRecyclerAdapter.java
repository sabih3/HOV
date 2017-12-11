package ae.netaq.homesorder_vendor.adapters.featured;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.featured.viewholder.FeaturedOrdersRecyclerViewHolder;

/**
 * Created by Netaq on 11/23/2017.
 */

public class FeaturedOrdersRecyclerAdapter extends RecyclerView.Adapter<FeaturedOrdersRecyclerViewHolder> {


    @Override
    public FeaturedOrdersRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        FeaturedOrdersRecyclerViewHolder viewHolder = new FeaturedOrdersRecyclerViewHolder
                (LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_list_item,parent,false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FeaturedOrdersRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
