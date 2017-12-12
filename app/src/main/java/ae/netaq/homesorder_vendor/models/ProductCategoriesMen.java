package ae.netaq.homesorder_vendor.models;

import java.util.ArrayList;

/**
 * Created by Netaq on 12/12/2017.
 */

public class ProductCategoriesMen {

    public ArrayList<FashionMan> fashion_men;

    public ArrayList<FashionMan> getFashion_men() {
        return fashion_men;
    }

    public class FashionMan
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
