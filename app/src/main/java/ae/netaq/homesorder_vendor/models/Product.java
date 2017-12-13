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
    private int mainCategory;
    private ProductCategories.Category subCategory;
    private ProductGroups.Group group;
    private ArrayList<Uri> productImagesUri;

    public static Product getProduct() {
        return product;
    }

    public static void setProduct(Product product) {
        Product.product = product;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public int getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(int mainCategory) {
        this.mainCategory = mainCategory;
    }

    public ProductCategories.Category getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(ProductCategories.Category subCategory) {
        this.subCategory = subCategory;
    }

    public ProductGroups.Group getGroup() {
        return group;
    }

    public void setGroup(ProductGroups.Group group) {
        this.group = group;
    }

    public ArrayList<Uri> getProductImagesUri() {
        return productImagesUri;
    }

    public void setProductImagesUri(ArrayList<Uri> productImagesUri) {
        this.productImagesUri = productImagesUri;
    }
}
