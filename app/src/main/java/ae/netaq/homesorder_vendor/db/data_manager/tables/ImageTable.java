package ae.netaq.homesorder_vendor.db.data_manager.tables;

import android.net.Uri;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

import static ae.netaq.homesorder_vendor.db.data_manager.tables.ImageTable.columnNames.COLUMN_IMAGE;
import static ae.netaq.homesorder_vendor.db.data_manager.tables.ImageTable.columnNames.TABLE_NAME_IMAGES;

/**
 * Created by Sabih Ahmed on 13-Dec-17.
 */

@DatabaseTable(tableName = TABLE_NAME_IMAGES)
public class ImageTable implements Serializable{

    @DatabaseField(generatedId = true,columnName = columnNames.COLUMN_ID)
    private long id;

    @DatabaseField(columnName = COLUMN_IMAGE,dataType=DataType.BYTE_ARRAY)
    private byte[] image;

    @DatabaseField(columnName = columnNames.COLUMN_PRODUCT_ID)
    private long productID;

    @DatabaseField(columnName = columnNames.COLUMN_LOCAL_URI)
    private String imageURI;
    
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    public String getImageURI() {
        return imageURI;
    }

    public interface columnNames{

        String TABLE_NAME_IMAGES = "images";

        String COLUMN_ID = "id";

        String COLUMN_IMAGE = "image_blob";

        String COLUMN_PRODUCT_ID = "product_id";

        String COLUMN_LOCAL_URI = "image_uri";
    }
}
