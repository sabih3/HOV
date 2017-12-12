package ae.netaq.homesorder_vendor.models;

import java.util.ArrayList;

/**
 * Created by Netaq on 12/12/2017.
 */

public class ProductCategoriesKids {

    public ArrayList<FashionKids> fashion_kids;

    public ArrayList<FashionKids> getFashion_kids() {
        return fashion_kids;
    }

    public class FashionKids
    {
        public int id;
        public String subCategoryAR;
        public String subCategoryEN;
        public String backGroundImage;

        public int getId() {
            return id;
        }

        public String getSubCategoryAR() {
            return subCategoryAR;
        }

        public String getSubCategoryEN() {
            return subCategoryEN;
        }

        public String getBackGroundImage() {
            return backGroundImage;
        }
    }
}
