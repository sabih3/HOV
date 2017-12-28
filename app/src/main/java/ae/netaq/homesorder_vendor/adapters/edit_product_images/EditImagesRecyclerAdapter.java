package ae.netaq.homesorder_vendor.adapters.edit_product_images;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.add_new_product_images.viewholder.AddImagesViewHolder;
import ae.netaq.homesorder_vendor.adapters.edit_product_images.viewholder.EditImagesViewHolder;
import ae.netaq.homesorder_vendor.db.data_manager.tables.ImageTable;

/**
 * Created by Deena on 29/11/2017.
 */

public class EditImagesRecyclerAdapter extends RecyclerView.Adapter<EditImagesViewHolder> {

    private Context mContext;

    private List<ImageTable> mDataSet;

    private Picasso picasso;


    public EditImagesRecyclerAdapter(Context mContext, List<ImageTable> mDataSet, Picasso picasso) {
        this.mContext = mContext;
        this.mDataSet = mDataSet;
        this.picasso = picasso;
    }

    @Override
    public EditImagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EditImagesViewHolder(LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.add_new_product_image_item,parent,false));
    }

    @Override
    public void onBindViewHolder(EditImagesViewHolder holder, final int position) {
        picasso.load(mDataSet.get(position).getImageURI()).resize(200, 200).centerCrop().into(holder.imageView);
        holder.removeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDataSet.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();

    }
}
