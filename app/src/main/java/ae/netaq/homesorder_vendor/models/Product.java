package ae.netaq.homesorder_vendor.models;

import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by Netaq on 12/7/2017.
 */

public class Product {

    private static Product product = new Product();

    private Product() {
    }

    public static Product getInstance( ) {
        return product;
    }

    private String productName;
    private Float productPrice;
    private String productDescription;
    private int productCategory;
    private String productSubCategory;
    private String productGroup;
    private ArrayList<Uri> productImagesUri;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public static Product getProduct() {
        return product;
    }

    public static void setProduct(Product product) {
        Product.product = product;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(int productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductSubCategory() {
        return productSubCategory;
    }

    public void setProductSubCategory(String productSubCategory) {
        this.productSubCategory = productSubCategory;
    }

    public ArrayList<Uri> getProductImagesUri() {
        return productImagesUri;
    }

    public void setProductImagesUri(ArrayList<Uri> productImagesUri) {
        this.productImagesUri = productImagesUri;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }
}
