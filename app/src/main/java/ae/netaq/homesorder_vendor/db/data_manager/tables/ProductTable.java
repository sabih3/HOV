package ae.netaq.homesorder_vendor.db.data_manager.tables;

import android.net.Uri;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable.ColumnNames.DESCRIPTION_AR;
import static ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable.ColumnNames.DESCRIPTION_EN;
import static ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable.ColumnNames.FEATURED;
import static ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable.ColumnNames.HANDLING_TIME;
import static ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable.ColumnNames.NAME_AR;
import static ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable.ColumnNames.NAME_EN;
import static ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable.ColumnNames.ORDER_LIMIT;
import static ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable.ColumnNames.PARENT_CATEGORY_AR;
import static ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable.ColumnNames.PARENT_CATEGORY_EN;
import static ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable.ColumnNames.PARENT_ID;
import static ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable.ColumnNames.PRODUCT_ID;
import static ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable.ColumnNames.PROMOTION;
import static ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable.ColumnNames.SUB_CATEGORY;
import static ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable.ColumnNames.TABLE_NAME;
import static ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable.ColumnNames.TARGET_GROUP;

/**
 * Created by sabih on 05-Dec-17.
 */

@DatabaseTable(tableName = TABLE_NAME)
public class ProductTable implements Serializable{

    @DatabaseField(generatedId = true,columnName = ColumnNames.ID)
    public long id;

    @DatabaseField(columnName = PRODUCT_ID)
    public long productID;

    @DatabaseField(columnName = NAME_AR)
    public String productNameAR;

    @DatabaseField(columnName = NAME_EN)
    public String productNameEN;

    @DatabaseField(columnName = PARENT_CATEGORY_AR)
    public String parentCategoryNameAR;

    @DatabaseField(columnName = PARENT_CATEGORY_EN)
    public String parentCategoryNameEN;

    @DatabaseField(columnName = PARENT_ID)
    public int parentCategoryID;

    @DatabaseField(columnName = PROMOTION)
    public int isOnPromotion;

    @DatabaseField(columnName = FEATURED)
    public int isFeatured;

    @DatabaseField(columnName = DESCRIPTION_EN)
    private String descriptionEN;

    @DatabaseField(columnName = DESCRIPTION_AR)
    private String descriptionAR;

    @DatabaseField(columnName = TARGET_GROUP)
    private int targetGroupID;

    @DatabaseField(columnName = SUB_CATEGORY)
    private int subCategoryID;

    @DatabaseField(columnName = ORDER_LIMIT)
    private int perDayOrderLimit;

    @DatabaseField(columnName = HANDLING_TIME)
    private int handlingTime;

    private List<ImageTable> imagesArray;

    private ArrayList<Uri> imagesLocalURI;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public String getProductNameAR() {
        return productNameAR;
    }

    public void setProductNameAR(String productNameAR) {
        this.productNameAR = productNameAR;
    }

    public String getProductNameEN() {
        return productNameEN;
    }

    public void setProductNameEN(String productNameEN) {
        this.productNameEN = productNameEN;
    }

    public String getParentCategoryNameAR() {
        return parentCategoryNameAR;
    }

    public void setParentCategoryNameAR(String parentCategoryNameAR) {
        this.parentCategoryNameAR = parentCategoryNameAR;
    }

    public String getParentCategoryNameEN() {
        return parentCategoryNameEN;
    }

    public void setParentCategoryNameEN(String parentCategoryNameEN) {
        this.parentCategoryNameEN = parentCategoryNameEN;
    }

    public int getParentCategoryID() {
        return parentCategoryID;
    }

    public void setParentCategoryID(int parentCategoryID) {
        this.parentCategoryID = parentCategoryID;
    }

    public int getIsOnPromotion() {
        return isOnPromotion;
    }

    public void setIsOnPromotion(int isOnPromotion) {
        this.isOnPromotion = isOnPromotion;
    }

    public int getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(int isFeatured) {
        this.isFeatured = isFeatured;
    }

    public void setTargetGroup(int targetGroupID) {
        this.targetGroupID = targetGroupID;
    }

    public void setSubCategoryID(int subCategoryID) {
        this.subCategoryID = subCategoryID;
    }

    public void setPerDayOrderLimit(int perDayOrderLimit) {
        this.perDayOrderLimit = perDayOrderLimit;
    }

    public void setHandlingTime(int handlingTime) {
        this.handlingTime = handlingTime;
    }

    public void setDescriptionEN(String descriptionEN) {
        this.descriptionEN = descriptionEN;
    }

    public void setDescriptionAR(String descriptionAR) {
        this.descriptionAR = descriptionAR;
    }

    public void setImagesArray(List<ImageTable> imagesArray) {
        this.imagesArray = imagesArray;
    }

    public List<ImageTable> getImagesArray() {
        return imagesArray;
    }


    public interface ColumnNames{
        String TABLE_NAME = "products";
        String ID = "id";
        String PRODUCT_ID = "product_id";
        String NAME_AR = "name_ar";
        String NAME_EN = "name_en";
        String PARENT_CATEGORY_AR = "parent_name_ar";
        String PARENT_CATEGORY_EN = "parent_name_en";
        String PARENT_ID = "parent_id";
        String PROMOTION = "on_promotion";
        String FEATURED = "is_featured";


        String DESCRIPTION_AR = "desc_AR";
        String DESCRIPTION_EN = "desc_EN";
        String ORDER_LIMIT = "daily_order_limit";
        String HANDLING_TIME = "handling_time";
        String TARGET_GROUP = "target_group";
        String SUB_CATEGORY = "sub_category";

    }
}
