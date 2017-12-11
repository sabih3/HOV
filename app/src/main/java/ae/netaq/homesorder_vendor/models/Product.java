package ae.netaq.homesorder_vendor.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Sabih Ahmed on 03-Dec-17.
 */

public class Product implements Serializable{


    public long id;

    public long productID;

    public String productNameAR;


    public String productNameEN;


    public String parentCategoryNameAR;


    public String parentCategoryNameEN;

    public int parentCategoryID;


    public int isOnPromotion;

    public int isFeatured;


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


    }
