package ae.netaq.homesorder_vendor.adapters.products.viewholder;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable;
import ae.netaq.homesorder_vendor.utils.Common;
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

    @BindView(R.id.tv_category)
    public TextView categoryName;

    @BindView(R.id.tv_prod_name)
    public TextView productName;

    @BindView(R.id.tv_prod_price)
    public TextView prodPrice;

    public ProductsRecyclerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(Context context, ProductTable product) {

        String categoryName = Common.isAPPLocaleArabic(context) ? product.getSubCategoryNameAR() :
                              product.getSubCategoryNameEN();
        String productName = "";

        Double price = product.getProductPrice();

        if(Common.isAPPLocaleArabic(context)){
            productName = product.getProductNameAR();

            if(productName == null){
                productName = product.getProductNameEN();
            }
        }else{
            productName = product.getProductNameEN();
        }

        this.categoryName.setText(categoryName);
        this.productName.setText(productName);
        this.prodPrice.setText(String.valueOf(price));

    }
}
