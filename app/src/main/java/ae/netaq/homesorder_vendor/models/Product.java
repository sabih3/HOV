package ae.netaq.homesorder_vendor.models;

/**
 * Created by sabih on 03-Dec-17.
 */
public class Product {
    public long productID;
    public String productNameAR;
    public String productNameEN;
    public String parentCategoryNameAR;
    public String parentCategoryNameEN;
    public int parentCategoryID;
    public int isOnPromotion;
    public int isFeatured;
    public String comments;
    public String parentCategoryName;

    public long getProductID() {
        return productID;
    }

    public String getProductNameAR() {
        return productNameAR;
    }

    public String getProductNameEN() {
        return productNameEN;
    }

    public String getParentCategoryNameAR() {
        return parentCategoryNameAR;
    }

    public String getParentCategoryNameEN() {
        return parentCategoryNameEN;
    }

    public int getParentCategoryID() {
        return parentCategoryID;
    }

    public int getIsOnPromotion() {
        return isOnPromotion;
    }

    public int getIsFeatured() {
        return isFeatured;
    }

    public String getComments() {
        return comments;
    }

    public String getParentCategoryName() {
        return parentCategoryName;
    }
}
