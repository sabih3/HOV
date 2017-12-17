package ae.netaq.homesorder_vendor.fragments.add_new_product.choose_category;

import ae.netaq.homesorder_vendor.models.ProductCategories;
import ae.netaq.homesorder_vendor.models.ProductGroups;

/**
 * Created by Netaq on 12/6/2017.
 */

public interface ChooseCategoryView {
    void onCategoryChosen(int mainCategory, ProductCategories.Category subCategory, ProductGroups.Group group);
}
