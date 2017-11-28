package ae.netaq.homesorder_vendor.models;

import java.util.ArrayList;

/**
 * Created by Netaq on 11/27/2017.
 */

public class ProductsResponse {
    String productName;

    public ProductsResponse() {
    }

    public String getProductName() {
        return productName;
    }

    public static ArrayList<ProductsResponse> getProducts(){

        ArrayList<ProductsResponse> productsResponses = new ArrayList<>();
        productsResponses.add(new ProductsResponse());
        productsResponses.add(new ProductsResponse());
        productsResponses.add(new ProductsResponse());
        productsResponses.add(new ProductsResponse());
        productsResponses.add(new ProductsResponse());
        productsResponses.add(new ProductsResponse());
        return productsResponses;
    }
}
