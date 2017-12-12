package ae.netaq.homesorder_vendor.utils;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;

import java.util.ArrayList;
import ae.netaq.homesorder_vendor.models.ProductCategoriesFood;
import ae.netaq.homesorder_vendor.utils.JSONUtils;

/**
 * Created by Netaq on 12/11/2017.
 */

public class FoodCategoriesManager {

    private static String FILENAME_FOOD_CATEGORIES = "food_categories";

    public static ArrayAdapter<String>  foodCategoriesAdapter(Context context){

        String jsonString = JSONUtils.loadJSONFromAsset(context, FILENAME_FOOD_CATEGORIES);

        Gson gson = new Gson();
        ProductCategoriesFood categoriesFood = gson.fromJson(jsonString, ProductCategoriesFood.class);

        ArrayList<String> foodCategoriesList = new ArrayList<>();

        for(int i = 0; i < categoriesFood.getFood().size(); i++){
            foodCategoriesList.add(categoriesFood.getFood().get(i).getSubCategoryEN());
        }

        ArrayAdapter<String> dataAdapterFoodCategories = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, foodCategoriesList);

        dataAdapterFoodCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return dataAdapterFoodCategories;
    }

}
