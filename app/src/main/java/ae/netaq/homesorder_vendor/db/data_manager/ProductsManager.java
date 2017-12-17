package ae.netaq.homesorder_vendor.db.data_manager;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ae.netaq.homesorder_vendor.db.DBManager;
import ae.netaq.homesorder_vendor.db.data_manager.tables.ImageTable;
import ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable;
import ae.netaq.homesorder_vendor.models.Product;
import ae.netaq.homesorder_vendor.models.Products;
import ae.netaq.homesorder_vendor.utils.JSONUtils;

/**
 * Created by sabih on 04-Dec-17.
 */

public class ProductsManager {

    private static final String TAG = "ProductsManager";

    public static ArrayList<Product> getProducts(Context context){
        String productsJson = JSONUtils.loadJSONFromAsset(context, "products.json");

        Gson gson = new Gson();
        Products products = gson.fromJson(productsJson, Products.class);
        ArrayList<Product> productsList = products.getProducts();



        return productsList;
    }


    public static void insertAllProducts(ArrayList<ProductTable> productList) {

        for(ProductTable product:productList){
            try {
                getProductDao().createOrUpdate(product);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Dao<ProductTable, Integer> getProductDao(){
        Dao<ProductTable, Integer> productsDao = null;
        try {
            productsDao = DBManager.getInstance().getDbHelper().getDao(ProductTable.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productsDao;
    }

    public static long persistProduct(ProductTable product){
        long productID = 0;
        try {
            productID = getProductDao().create(product);
        } catch (SQLException e) {
            Log.e(TAG,e.toString());
        }

        try {
            List<ProductTable> productTables = getProductDao().queryForAll();
            int size = productTables.size();
            ProductTable persistedProduct = productTables.get(size - 1);
            productID = persistedProduct.getId();
        } catch (SQLException e) {
            Log.e(TAG,e.toString());
        }
        return productID;
    }


    public static void insertImage(ImageTable productImage) {
        int i = -1;
        try {
            i = getImageDao().create(productImage);
        } catch (SQLException e) {
            Log.e(TAG,e.toString());
        }
    }

    private static Dao<ImageTable, Integer> getImageDao() {
        Dao<ImageTable,Integer> imageDAO = null;

        try {
            imageDAO = DBManager.getInstance().getDbHelper().getDao(ImageTable.class);
        } catch (SQLException e) {
            Log.e(TAG,e.toString());
        }

        return imageDAO;
    }

    public static List<ProductTable> getAllProducts() throws SQLException {
        List<ProductTable> persistedproducts = getProductDao().queryForAll();



        for(ProductTable product :persistedproducts ){
            long productID = product.getId();
            Dao<ImageTable, Integer> imageDao = getImageDao();

            List<ImageTable> imageTables = imageDao.
                             queryForEq(ProductTable.ColumnNames.PRODUCT_ID, productID);
            product.setImagesArray(imageTables);

        }

        return persistedproducts;
    }
}
