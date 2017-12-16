package ae.netaq.homesorder_vendor.adapters.add_new_product_images;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.add_new_product_images.viewholder.AddImagesViewHolder;

/**
 * Created by Deena on 29/11/2017.
 */

public class AddImagesRecyclerAdapter extends RecyclerView.Adapter<AddImagesViewHolder> {

    private Context mContext;

    private ArrayList<Uri> mDataSet;

    private Picasso picasso;


    public AddImagesRecyclerAdapter(Context mContext, ArrayList<Uri> mDataSet, Picasso picasso) {
        this.mContext = mContext;
        this.mDataSet = mDataSet;
        this.picasso = picasso;
    }

    @Override
    public AddImagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddImagesViewHolder(LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.add_new_product_image_item,parent,false));
    }

    @Override
    public void onBindViewHolder(AddImagesViewHolder holder, final int position) {
        picasso.load(mDataSet.get(position)).resize(200, 200).centerCrop().into(holder.imageView);
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
