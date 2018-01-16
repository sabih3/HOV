package ae.netaq.homesorder_vendor.adapters.edit_product_images.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import ae.netaq.homesorder_vendor.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 11/23/2017.
 */

public class EditImagesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.add_new_product_image_view_item)
    public ImageView imageView;

    @BindView(R.id.remove_image_layout)
    public RelativeLayout removeImage;

    public EditImagesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
