package ae.netaq.homesorder_vendor.adapters.products.products_tab;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.products.viewholder.ProductsRecyclerViewHolder;
import ae.netaq.homesorder_vendor.db.data_manager.tables.ImageTable;
import ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable;
import ae.netaq.homesorder_vendor.utils.NavigationController;
import ae.netaq.homesorder_vendor.utils.Utils;

/**
 * Created by Netaq on 11/23/2017.
 */

public class SimpleProductsRecyclerAdapter extends RecyclerView.Adapter<ProductsRecyclerViewHolder>{

    private List<ProductTable> mDataset;

    private Context mContext;

    private ProductSelectionListener productListener;

    public void setProductListener(ProductSelectionListener productListener) {
        this.productListener = productListener;
    }

    public SimpleProductsRecyclerAdapter(List<ProductTable> mDataset,
                                         Context mContext) {
        this.mDataset = mDataset;
        this.mContext = mContext;
    }

    @Override
    public ProductsRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ProductsRecyclerViewHolder viewHolder = new ProductsRecyclerViewHolder(LayoutInflater.
            from(parent.getContext()).inflate(R.layout.product_list_item, parent, false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProductsRecyclerViewHolder holder, int position) {
        ImageTable imageTable = mDataset.get(position).getImagesArray().get(0);
        Uri imageURI = Uri.parse(imageTable.getImageURI());
        final ProductTable product = mDataset.get(position);

        String realPathFromURI = Utils.getPathBasedOnSDK(mContext,imageURI);

        Picasso.with(mContext).load("file://"+realPathFromURI).into(holder.productMainImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productListener.onProductSelected(product);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface ProductSelectionListener{

        void onProductSelected(ProductTable product);
    }
}
