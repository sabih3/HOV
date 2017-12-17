package ae.netaq.homesorder_vendor.models;

import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by Netaq on 12/7/2017.
 */

public class Product {

    private static Product product;
    private long productID;
    private int targetGroup;
    private int dailyLimit;
    private int HandlingTime;
    private int parentCategoryID;
    private int subCategoryID;
    private ArrayList<byte[]> imagesArray;


    private Product() {
    }

    public static Product getInstance( ) {
        if(product == null){
            product = new Product();
        }
        return product;
    }

    private String productNameEN;
    private String productNameAR;
    private Float productPrice;
    private String productDescriptionEN;
    private String productDescriptionAR;
    private int mainCategory;
    private ProductCategories.Category subCategory;
    private ProductGroups.Group group;
    private int productCategory;
    private String productSubCategory;
    private String productGroup;
    private ArrayList<Uri> productImagesUri;

    public String getProductNameEN() {
        return productNameEN;
    }

    public void setProductNameEN(String productNameEN) {
        this.productNameEN = productNameEN;
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

    public String getProductDescriptionEN() {
        return productDescriptionEN;
    }

    public void setProductDescriptionEN(String productDescriptionEN) {
        this.productDescriptionEN = productDescriptionEN;
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

    public String getProductGroup() {
        return productGroup;
    }

    public String getProductNameAR() {
        return productNameAR;
    }

    public void setProductNameAR(String productNameAR) {
        this.productNameAR = productNameAR;
    }

    public String getProductDescriptionAR() {
        return productDescriptionAR;
    }

    public void setProductDescriptionAR(String productDescriptionAR) {
        this.productDescriptionAR = productDescriptionAR;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }


    public long getProductID() {
        return this.productID;
    }

    public int getTargetGroup() {
        return this.targetGroup;
    }

    public int getDailyOrderLimit() {
        return this.dailyLimit;
    }

    public int getHandlingTime() {
        return this.HandlingTime;
    }

    public int getParentCategoryID() {
        return this.parentCategoryID;
    }

    public int getSubCategoryID() {
        return subCategoryID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public void setTargetGroup(int targetGroup) {
        this.targetGroup = targetGroup;
    }

    public void setDailyLimit(int dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public void setHandlingTime(int handlingTime) {
        HandlingTime = handlingTime;
    }

    public void setParentCategoryID(int parentCategoryID) {
        this.parentCategoryID = parentCategoryID;
    }

    public ArrayList<byte[]> getImagesArray() {
        return imagesArray;
    }

    public void setSubCategoryID(int subCategoryID) {
        this.subCategoryID = subCategoryID;
    }

    public void setImagesArray(ArrayList<byte[]> imagesArray) {
        this.imagesArray = imagesArray;
    }
}
