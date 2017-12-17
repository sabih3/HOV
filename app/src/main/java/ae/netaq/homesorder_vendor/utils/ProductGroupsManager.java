package ae.netaq.homesorder_vendor.utils;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;

import java.util.ArrayList;

import ae.netaq.homesorder_vendor.models.ProductCategories;
import ae.netaq.homesorder_vendor.models.ProductGroups;

/**
 * Created by Netaq on 12/11/2017.
 */

public class ProductGroupsManager {

    public static String FILENAME_PRODUCT_GROUPS = "fashion_groups";


    public static ArrayAdapter<String> getProductGroupsAdapter(Context context, String filename){

        ArrayList<String> fashionMenCategoriesList = new ArrayList<>();

        for(int i = 0; i < getProductGroups(context, filename).getGroups().size(); i++){
            if(Common.isAPPLocaleArabic(context)){
                fashionMenCategoriesList.add(getProductGroups(context, filename).getGroups().get(i).getSubCategoryAR());
            }else{
                fashionMenCategoriesList.add(getProductGroups(context, filename).getGroups().get(i).getSubCategoryEN());
            }
        }

        ArrayAdapter<String> dataAdapterFashionCategoriesMen = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, fashionMenCategoriesList);

        dataAdapterFashionCategoriesMen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return dataAdapterFashionCategoriesMen;
    }

    public static ProductGroups getProductGroups(Context context, String filename) {
        String jsonString = JSONUtils.loadJSONFromAsset(context, filename);

        Gson gson = new Gson();

        return gson.fromJson(jsonString, ProductGroups.class);
    }
}
