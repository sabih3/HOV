package ae.netaq.homesorder_vendor.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sabih on 10-Jan-18.
 */

public class ResponseAddProduct {

    private int code;
    private Product product;
    private String message;


    public int getCode() {
        return code;
    }

    public Product getProduct() {
        return product;
    }

    public String getMessage() {
        return message;
    }

    public class Product {
        public ArrayList<String> color;
        public List<String> size;
        public int productID;
        public String productNameEN;
        public String productNameAR;
        public Double price;
        public String perDayOrderLimit;
        public String handlingTime;
        public String descriptionEN;
        public String descriptionAR;
        public List<String> mainCategorynameEN;
        public List<String> mainCategorynameAR;
        public List<String> mainCategoryID;
        public List<String> targetedGroupNameEN;
        public List<String> targetedGroupNameAR;
        public List<String> targetGroupID;
        public List<String> subCategoryNameEn;
        public List<String> subCategoryNameAR;
        public List<String> subCategoryID;
        public List<String> media;
        public String created_at;
        public String updated_at;


        public ArrayList<String> getColor() {
            return color;
        }

        public List<String> getSize() {
            return size;
        }

        public int getProductID() {
            return productID;
        }

        public String getProductNameEN() {
            return productNameEN;
        }

        public String getProductNameAR() {
            return productNameAR;
        }

        public Double getPrice() {
            return price;
        }

        public String getPerDayOrderLimit() {
            return perDayOrderLimit;
        }

        public String getHandlingTime() {
            return handlingTime;
        }

        public String getDescriptionEN() {
            return descriptionEN;
        }

        public String getDescriptionAR() {
            return descriptionAR;
        }

        public List<String> getMainCategorynameEN() {
            return mainCategorynameEN;
        }

        public List<String> getMainCategorynameAR() {
            return mainCategorynameAR;
        }

        public List<String> getMainCategoryID() {
            return mainCategoryID;
        }

        public List<String> getTargetedGroupNameEN() {
            return targetedGroupNameEN;
        }

        public List<String> getTargetedGroupNameAR() {
            return targetedGroupNameAR;
        }

        public List<String> getTargetGroupID() {
            return targetGroupID;
        }

        public List<String> getSubCategoryNameEn() {
            return subCategoryNameEn;
        }

        public List<String> getSubCategoryNameAR() {
            return subCategoryNameAR;
        }

        public List<String> getSubCategoryID() {
            return subCategoryID;
        }

        public List<String> getMedia() {
            return media;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }
    }
}
