package ae.netaq.homesorder_vendor.adapters.nav_menu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.nav_menu.viewholder.NavMenuItemViewHolder;

/**
 * Created by Netaq on 11/23/2017.
 */

public class NavMenuItemsRecyclerAdapter extends RecyclerView.Adapter<NavMenuItemViewHolder> {

    private List<String> mDataSet;

    private NavMenuItemSelectedInterface navMenuItemSelectedInterface;

    public NavMenuItemsRecyclerAdapter(List<String> mDataSet, NavMenuItemSelectedInterface navMenuItemSelectedInterface) {
        this.mDataSet = mDataSet;
        this.navMenuItemSelectedInterface = navMenuItemSelectedInterface;
    }

    @Override
    public NavMenuItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new NavMenuItemViewHolder
                (LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_menu_item,parent,false));
    }

    @Override
    public void onBindViewHolder(final NavMenuItemViewHolder holder, final int position) {

        holder.navMenuItemTitle.setText(mDataSet.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navMenuItemSelectedInterface.onNavMenuItemSelectedListener(holder.navMenuItemTitle.getText().toString(), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    public interface NavMenuItemSelectedInterface{
        void onNavMenuItemSelectedListener(String title, int position);
    }
}
