package ae.netaq.homesorder_vendor.db.data_manager;

import android.content.Context;

import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

import ae.netaq.homesorder_vendor.db.DBManager;
import ae.netaq.homesorder_vendor.models.Product;
import ae.netaq.homesorder_vendor.models.Products;
import ae.netaq.homesorder_vendor.utils.JSONUtils;

/**
 * Created by sabih on 04-Dec-17.
 */

public class ProductsManager {

    public static ArrayList<Product> getProducts(Context context){
        String productsJson = JSONUtils.loadJSONFromAsset(context, "products.json");

        Gson gson = new Gson();
        Products products = gson.fromJson(productsJson, Products.class);
        ArrayList<Product> productsList = products.getProducts();



        return productsList;
    }


    public static void insertAllProducts(ArrayList<Product> productList) {

        for(Product product:productList){
            try {
                getProductDao().createOrUpdate(product);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Dao<Product, Integer> getProductDao(){
        Dao<Product, Integer> productsDao = null;
        try {
            productsDao = DBManager.getInstance().getDbHelper().getDao(Product.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productsDao;
    }


}
