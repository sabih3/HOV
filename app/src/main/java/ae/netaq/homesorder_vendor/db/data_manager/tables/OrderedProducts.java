package ae.netaq.homesorder_vendor.db.data_manager.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by sabih on 24-Jan-18.
 */

@DatabaseTable(tableName = OrderedProducts.ColumnNames.TABLE_NAME)
public class OrderedProducts {

    @DatabaseField(columnName = "order_id")
    public long orderID;

    @DatabaseField(columnName = ColumnNames.COLUMN_PRODUCT_ID)
    public long productID;

    @DatabaseField(columnName = ColumnNames.COLUMN_SKU)
    public String SKU;

    @DatabaseField(columnName = ColumnNames.COLUMN_NAME_EN)
    public String nameEN;

    @DatabaseField(columnName = ColumnNames.COLUMN_NAME_AR)
    public String nameAR;

    @DatabaseField(columnName = ColumnNames.COLUMN_PRICE)
    public long price;

    @DatabaseField(columnName = ColumnNames.COLUMN_MAIN_CATEGORY_EN)
    public String mainCategoryEN;

    @DatabaseField(columnName = ColumnNames.COLUMN_MAIN_CATEGORY_AR)
    public String mainCategoryAR;

    @DatabaseField(columnName = ColumnNames.COLUMN_SUB_CATEGORY_EN)
    public String subCategoryEN;

    @DatabaseField(columnName = ColumnNames.COLUMN_SUB_CATEGORY_AR)
    public String subCategoryAR;

    @DatabaseField(columnName = ColumnNames.COLUMN_TARGET_GROUP_EN)
    public String targetGroupEN;

    @DatabaseField(columnName = ColumnNames.COLUMN_TARGET_GROUP_AR)
    public String targetGroupAR;

    @DatabaseField(columnName = ColumnNames.COLUMN_PREVIEW_IMAGE)
    public String previewImage;

    @DatabaseField(columnName = ColumnNames.COLUMN_QUANTITY)
    public String desiredQuantity;

    @DatabaseField(columnName = ColumnNames.COLUMN_OFFER_PRICE)
    public long offerPrice;

    @DatabaseField(columnName = ColumnNames.COLUMN_ORDERED_SIZE)
    public String desiredSize;

    @DatabaseField(columnName = ColumnNames.COLUMN_ORDERED_COLOR)
    public String desiredColor;

    @DatabaseField(columnName = ColumnNames.COLUMN_ORDERED_WEIGHT)
    public String desiredWeight;

    @DatabaseField(columnName = ColumnNames.COLUMN_COMMENTS)
    public String itemComments;

    public interface ColumnNames{
        String TABLE_NAME = "Ordered_Products";
        String COLUMN_PRODUCT_ID = "product_id";
        String COLUMN_SKU = "sku";
        String COLUMN_NAME_EN = "name_en";
        String COLUMN_NAME_AR = "name_ar";
        String COLUMN_PRICE = "price";
        String COLUMN_MAIN_CATEGORY_EN ="main_category_en";
        String COLUMN_MAIN_CATEGORY_AR = "main_category_ar";
        String COLUMN_SUB_CATEGORY_EN = "sub_category_en";
        String COLUMN_SUB_CATEGORY_AR = "sub_category_ar";
        String COLUMN_TARGET_GROUP_EN = "target_group_en";
        String COLUMN_TARGET_GROUP_AR = "target_group_ar";
        String COLUMN_PREVIEW_IMAGE = "image";
        String COLUMN_QUANTITY = "desired_quantity";
        String COLUMN_OFFER_PRICE = "offer_price";
        String COLUMN_ORDERED_SIZE = "desired_size";
        String COLUMN_ORDERED_COLOR = "desired_color";
        String COLUMN_ORDERED_WEIGHT = "desired_weight";
        String COLUMN_COMMENTS = "item_comments";

    }
}
