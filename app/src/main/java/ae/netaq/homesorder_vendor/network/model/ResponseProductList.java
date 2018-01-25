package ae.netaq.homesorder_vendor.network.model;

import java.util.ArrayList;

/**
 * Created by sabih on 23-Jan-18.
 */

public class ResponseProductList extends GeneralResponse{

    private int product_count;
    private ArrayList<ResponseAddProduct.Product> items;

    public int getProduct_count() {
        return product_count;
    }

    public ArrayList<ResponseAddProduct.Product> getItems() {
        return items;
    }
}
