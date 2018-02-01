package ae.netaq.homesorder_vendor.adapters.nav_menu.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ae.netaq.homesorder_vendor.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 11/23/2017.
 */

public class NavMenuItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.nav_menu_item_title)
    public TextView navMenuItemTitle;

    public NavMenuItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
