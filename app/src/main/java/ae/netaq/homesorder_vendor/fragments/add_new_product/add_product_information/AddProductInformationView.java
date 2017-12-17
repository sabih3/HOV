package ae.netaq.homesorder_vendor.fragments.add_new_product.add_product_information;

/**
 * Created by Netaq on 12/6/2017.
 */

public interface AddProductInformationView {
    void onProductInformationAdded(String productNameEN, String productNameAR,
                                   Double productPrice,
                                   String descEN, String descAR, String size,
                                   String color, int orderLimit, int handlingTime);
}
