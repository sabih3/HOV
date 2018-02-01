package ae.netaq.homesorder_vendor.db.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Sabih Ahmed on 05-Dec-17.
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
    public Double price;

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
    public int desiredQuantity;

    @DatabaseField(columnName = ColumnNames.COLUMN_OFFER_PRICE)
    public Double offerPrice;

    @DatabaseField(columnName = ColumnNames.COLUMN_ORDERED_SIZE)
    public String desiredSize;

    @DatabaseField(columnName = ColumnNames.COLUMN_ORDERED_COLOR)
    public String desiredColor;

    @DatabaseField(columnName = ColumnNames.COLUMN_ORDERED_WEIGHT)
    public String desiredWeight;

    @DatabaseField(columnName = ColumnNames.COLUMN_COMMENTS)
    public String itemComments;

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getNameAR() {
        return nameAR;
    }

    public void setNameAR(String nameAR) {
        this.nameAR = nameAR;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getMainCategoryEN() {
        return mainCategoryEN;
    }

    public void setMainCategoryEN(String mainCategoryEN) {
        this.mainCategoryEN = mainCategoryEN;
    }

    public String getMainCategoryAR() {
        return mainCategoryAR;
    }

    public void setMainCategoryAR(String mainCategoryAR) {
        this.mainCategoryAR = mainCategoryAR;
    }

    public String getSubCategoryEN() {
        return subCategoryEN;
    }

    public void setSubCategoryEN(String subCategoryEN) {
        this.subCategoryEN = subCategoryEN;
    }

    public String getSubCategoryAR() {
        return subCategoryAR;
    }

    public void setSubCategoryAR(String subCategoryAR) {
        this.subCategoryAR = subCategoryAR;
    }

    public String getTargetGroupEN() {
        return targetGroupEN;
    }

    public void setTargetGroupEN(String targetGroupEN) {
        this.targetGroupEN = targetGroupEN;
    }

    public String getTargetGroupAR() {
        return targetGroupAR;
    }

    public void setTargetGroupAR(String targetGroupAR) {
        this.targetGroupAR = targetGroupAR;
    }

    public String getPreviewImage() {
        return previewImage;
    }

    public void setPreviewImage(String previewImage) {
        this.previewImage = previewImage;
    }

    public int getDesiredQuantity() {
        return desiredQuantity;
    }

    public void setDesiredQuantity(int desiredQuantity) {
        this.desiredQuantity = desiredQuantity;
    }

    public Double getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(Double offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getDesiredSize() {
        return desiredSize;
    }

    public void setDesiredSize(String desiredSize) {
        this.desiredSize = desiredSize;
    }

    public String getDesiredColor() {
        return desiredColor;
    }

    public void setDesiredColor(String desiredColor) {
        this.desiredColor = desiredColor;
    }

    public String getDesiredWeight() {
        return desiredWeight;
    }

    public void setDesiredWeight(String desiredWeight) {
        this.desiredWeight = desiredWeight;
    }

    public String getItemComments() {
        return itemComments;
    }

    public void setItemComments(String itemComments) {
        this.itemComments = itemComments;
    }

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
