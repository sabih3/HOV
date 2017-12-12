package ae.netaq.homesorder_vendor.utils;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;

import java.util.ArrayList;
import ae.netaq.homesorder_vendor.models.ProductCategoriesKids;
import ae.netaq.homesorder_vendor.models.ProductCategoriesMen;
import ae.netaq.homesorder_vendor.models.ProductCategoriesWomen;
import ae.netaq.homesorder_vendor.models.ProductGroups;

/**
 * Created by Netaq on 12/11/2017.
 */

public class FashionCategoriesManager {

    private static String FILENAME_FASHION_GROUPS = "fashion_groups";

    private static String FILENAME_FASHION_CATEGORIES_MEN = "fashion_men_categories";
    private static String FILENAME_FASHION_CATEGORIES_WOMEN = "fashion_women_categories";
    private static String FILENAME_FASHION_CATEGORIES_KIDS = "fashion_kids_categories";


    public static ArrayAdapter<String> fashionMenCategoriesAdapter(Context context){

        String jsonString = JSONUtils.loadJSONFromAsset(context, FILENAME_FASHION_CATEGORIES_MEN);

        Gson gson = new Gson();
        ProductCategoriesMen categoriesFashionMen = gson.fromJson(jsonString, ProductCategoriesMen.class);

        ArrayList<String> fashionMenCategoriesList = new ArrayList<>();

        for(int i = 0; i < categoriesFashionMen.getFashion_men().size(); i++){
            fashionMenCategoriesList.add(categoriesFashionMen.getFashion_men().get(i).getSubCategoryEN());
        }

        ArrayAdapter<String> dataAdapterFashionCategoriesMen = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, fashionMenCategoriesList);

        dataAdapterFashionCategoriesMen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return dataAdapterFashionCategoriesMen;
    }

    public static ArrayAdapter<String> fashionWomenCategoriesAdapter(Context context){

        String jsonString = JSONUtils.loadJSONFromAsset(context, FILENAME_FASHION_CATEGORIES_WOMEN);

        Gson gson = new Gson();
        ProductCategoriesWomen categoriesFashionWomen = gson.fromJson(jsonString, ProductCategoriesWomen.class);

        ArrayList<String> fashionWomenCategoriesList = new ArrayList<>();

        for(int i = 0; i < categoriesFashionWomen.getFashion_women().size(); i++){
            fashionWomenCategoriesList.add(categoriesFashionWomen.getFashion_women().get(i).getSubCategoryEN());
        }

        ArrayAdapter<String> dataAdapterFashionCategoriesWomen = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, fashionWomenCategoriesList);

        dataAdapterFashionCategoriesWomen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return dataAdapterFashionCategoriesWomen;
    }

    public static ArrayAdapter<String> fashionKidsCategoriesAdapter(Context context){

        String jsonString = JSONUtils.loadJSONFromAsset(context, FILENAME_FASHION_CATEGORIES_KIDS);

        Gson gson = new Gson();

        ProductCategoriesKids categoriesFashionKids = gson.fromJson(jsonString, ProductCategoriesKids.class);

        ArrayList<String> fashionKidsCategoriesList = new ArrayList<>();

        for(int i = 0; i < categoriesFashionKids.getFashion_kids().size(); i++){
            fashionKidsCategoriesList.add(categoriesFashionKids.getFashion_kids().get(i).getSubCategoryEN());
        }

        ArrayAdapter<String> dataAdapterFashionCategoriesKids = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, fashionKidsCategoriesList);

        dataAdapterFashionCategoriesKids.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return dataAdapterFashionCategoriesKids;
    }

    public static ArrayAdapter<String> fashionGroups(Context context){

        String jsonString = JSONUtils.loadJSONFromAsset(context, FILENAME_FASHION_GROUPS);

        Gson gson = new Gson();

        ProductGroups productGroups = gson.fromJson(jsonString, ProductGroups.class);

        ArrayList<String> fashionGroupsList = new ArrayList<>();

        for(int i = 0; i < productGroups.getFashion_groups().size(); i++){
            fashionGroupsList.add(productGroups.getFashion_groups().get(i).getSubCategoryEN());
        }

        ArrayAdapter<String> dataAdapterFashionGroups = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, fashionGroupsList);

        dataAdapterFashionGroups.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return dataAdapterFashionGroups;
    }


}
