package ae.netaq.homesorder_vendor.fragments.add_new_product.add_product_images;

import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by Netaq on 12/5/2017.
 */

public interface AddProductImagesView {
    void onAddImagesCompleted(ArrayList<Uri> imagesUri);
}
