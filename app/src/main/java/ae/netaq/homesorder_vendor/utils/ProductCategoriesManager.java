package ae.netaq.homesorder_vendor.utils;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;

import java.util.ArrayList;

import ae.netaq.homesorder_vendor.models.ProductCategories;

/**
 * Created by Netaq on 12/11/2017.
 */

public class ProductCategoriesManager {

    public static String FILENAME_FOOD_CATEGORIES = "food_categories";

    public static String FILENAME_FASHION_CATEGORIES_MEN = "fashion_men_categories";
    public static String FILENAME_FASHION_CATEGORIES_WOMEN = "fashion_women_categories";
    public static String FILENAME_FASHION_CATEGORIES_KIDS = "fashion_kids_categories";


    public static ArrayAdapter<String> getProductCategoriesAdapter(Context context, String filename){

        ArrayList<String> productCategoriesList = new ArrayList<>();

        for(int i = 0; i < getProductCategories(context, filename).getCategories().size(); i++){
            if(Common.isAPPLocaleArabic(context)){
                productCategoriesList.add(getProductCategories(context, filename).getCategories().get(i).getSubCategoryAR());

            }else{
                productCategoriesList.add(getProductCategories(context, filename).getCategories().get(i).getSubCategoryEN());
            }
        }

        ArrayAdapter<String> dataAdapterFashionCategoriesMen = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, productCategoriesList);

        dataAdapterFashionCategoriesMen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return dataAdapterFashionCategoriesMen;
    }

    public static ProductCategories getProductCategories(Context context, String filename) {
        String jsonString = JSONUtils.loadJSONFromAsset(context, filename);

        Gson gson = new Gson();

        return gson.fromJson(jsonString, ProductCategories.class);
    }
}
