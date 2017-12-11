package ae.netaq.homesorder_vendor.db.data_manager.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import static ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable.ColumnNames.FEATURED;
import static ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable.ColumnNames.NAME_AR;
import static ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable.ColumnNames.NAME_EN;
import static ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable.ColumnNames.PARENT_CATEGORY_AR;
import static ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable.ColumnNames.PARENT_CATEGORY_EN;
import static ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable.ColumnNames.PARENT_ID;
import static ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable.ColumnNames.PRODUCT_ID;
import static ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable.ColumnNames.PROMOTION;
import static ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable.ColumnNames.TABLE_NAME;

/**
 * Created by sabih on 05-Dec-17.
 */

@DatabaseTable(tableName = TABLE_NAME)
public class ProductTable {

    @DatabaseField(generatedId = true)
    public long id;

    @DatabaseField(unique = true,columnName = PRODUCT_ID)
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

    public interface ColumnNames{
        String TABLE_NAME = "products";
        String PRODUCT_ID = "product_id";
        String NAME_AR = "name_ar";
        String NAME_EN = "name_en";
        String PARENT_CATEGORY_AR = "parent_name_ar";
        String PARENT_CATEGORY_EN = "parent_name_en";
        String PARENT_ID = "parent_id";
        String PROMOTION = "on_promotion";
        String FEATURED = "is_featured";


    }
}
