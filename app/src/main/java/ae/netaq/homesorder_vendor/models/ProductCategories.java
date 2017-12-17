package ae.netaq.homesorder_vendor.models;

import java.util.List;

/**
 * Created by Netaq on 12/12/2017.
 */

public class ProductCategories {

    public List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public class Category
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
