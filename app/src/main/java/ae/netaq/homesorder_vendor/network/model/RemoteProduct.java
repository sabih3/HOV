package ae.netaq.homesorder_vendor.network.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sabih on 10-Jan-18.
 */

public class RemoteProduct implements Serializable{

    private String productname;
    private String productname_arabic;
    private String price;
    private String description;
    private String description_arabic;
    private String orderlimitperday;
    private String handlingtime;
    private String[] weight;
    private String[] color;
    private String[] size;
    private ArrayList<String> category;
    private ArrayList<String > images;
    private long productID;


    public void setProductname(String productname) {
        this.productname = productname;
    }

    public void setProductname_arabic(String productname_arabic) {
        this.productname_arabic = productname_arabic;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDescription_arabic(String description_arabic) {
        this.description_arabic = description_arabic;
    }

    public void setOrderlimitperday(String orderlimitperday) {
        this.orderlimitperday = orderlimitperday;
    }

    public void setHandlingtime(String handlingtime) {
        this.handlingtime = handlingtime;
    }

    public void setWeight(String[] weight) {
        this.weight = weight;
    }

    public void setColor(String[] color) {
        this.color = color;
    }

    public void setSize(String[] size) {
        this.size = size;
    }

    public void setCategory(ArrayList<String> category) {
        this.category = category;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public long getProductID() {
        return productID;
    }
}
