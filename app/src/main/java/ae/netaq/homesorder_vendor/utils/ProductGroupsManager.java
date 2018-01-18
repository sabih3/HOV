package ae.netaq.homesorder_vendor.utils;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import ae.netaq.homesorder_vendor.activities.product_edit.ProductEditActivity;
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

    /**Get product Group based on ID
     *
     * @param context
     * @param id
     * @return
     */
    public static ProductGroups.Group getProductGroup(Context context, int id) {
        String jsonString = JSONUtils.loadJSONFromAsset(context, FILENAME_PRODUCT_GROUPS);

        Gson gson = new Gson();

        ProductGroups productGroup = gson.fromJson(jsonString, ProductGroups.class);

        List<ProductGroups.Group> groups = productGroup.getGroups();

        ProductGroups.Group group = null;
        for(ProductGroups.Group iterator : groups ){

            if(iterator.getId()==id){
                group = iterator;
            }
        }



        return group;
    }

    public static ProductGroups.Group getGroupByPosition(Context context, int groupPosition){
        ProductGroups.Group group = getProductGroups(context,
                ProductGroupsManager.FILENAME_PRODUCT_GROUPS).getGroups().get(groupPosition);

        return group;
    }

    public static String getLocaleBasedName(Context context,int id){
        String groupName = "";

        ProductGroups.Group productGroup = getProductGroup(context, id);
        if(Common.isAPPLocaleArabic(context)){
            groupName = productGroup.getSubCategoryAR();
        }else{
            groupName = productGroup.getSubCategoryEN();
        }

        return groupName;

    }

    public static int getproductGroupPosition(Context context, ProductGroups.Group groupToFind) {
        String jsonString = JSONUtils.loadJSONFromAsset(context, FILENAME_PRODUCT_GROUPS);

        Gson gson = new Gson();

        ProductGroups productGroups = gson.fromJson(jsonString, ProductGroups.class);
        List<ProductGroups.Group> groups = productGroups.getGroups();
        int groupPosition = 0;

        for(int i=0;i<groups.size();i++){
            if(groups.get(i).getId()==groupToFind.getId()){

                groupPosition = i;
                break;
            }

        }

        return groupPosition;
    }
}
