package ae.netaq.homesorder_vendor.adapters.products.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import ae.netaq.homesorder_vendor.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 11/23/2017.
 */

public class ProductsRecyclerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.products_list_item_card)
    public CardView productListItem;

    @BindView(R.id.img_view_products_list)
    public ImageView productMainImage;

    public ProductsRecyclerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
