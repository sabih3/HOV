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
import ae.netaq.homesorder_vendor.network.model.ResponseAddProduct;
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

    public static long addProduct(ProductTable product){
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
            long productID = product.getProductID();
            Dao<ImageTable, Integer> imageDao = getImageDao();

            List<ImageTable> imageTables = imageDao.
                             queryForEq(ProductTable.ColumnNames.PRODUCT_ID, productID);
            product.setImagesArray(imageTables);

        }

        return persistedproducts;
    }

    public static void insertRemoteProduct(ResponseAddProduct.Product product) {
        ProductTable productToPersist = new ProductTable();
        productToPersist.setProductID(product.getProductID());
        productToPersist.setParentCategoryID(Integer.valueOf(product.getMainCategoryID().get(0)));

        productToPersist.setParentCategoryNameEN(product.getMainCategorynameEN().get(0));
        productToPersist.setParentCategoryNameAR(product.getMainCategorynameAR().get(0));

        int targetGroupID = -1;
        try {

            targetGroupID = Integer.valueOf(product.getTargetGroupID().get(0));

        }catch (Exception exc){

        }

        productToPersist.setTargetGroup(targetGroupID);

        productToPersist.setSubCategoryID(Integer.valueOf(product.getSubCategoryID().get(0)));
        productToPersist.setSubCategoryNameAR(product.getSubCategoryNameAR().get(0));
        productToPersist.setSubCategoryNameEN(product.getSubCategoryNameEn().get(0));
        productToPersist.setProductNameEN(product.getProductNameEN());
        productToPersist.setProductNameAR(product.getProductNameAR());
        productToPersist.setPerDayOrderLimit(Integer.valueOf(product.getPerDayOrderLimit()));
        productToPersist.setHandlingTime(Integer.valueOf(product.getHandlingTime()));
        productToPersist.setProductPrice(Double.valueOf(product.getPrice()));
        productToPersist.setDescriptionAR(product.getDescriptionAR());
        productToPersist.setDescriptionEN(product.getDescriptionEN());

        String productColor = "";

        try {
            productColor = product.getColor().get(0);
        }catch (Exception exc){
            productColor = "";
        }

        productToPersist.setColor(productColor);

        String size = "";

        try {
            size = product.getSize().get(0);
        }catch (Exception exc){
            size = "";
        }

        productToPersist.setSize(size);


        long localDbID = ProductsManager.addProduct(productToPersist);

        //Local URI Array, to be replaced with absolute URLs of backend
        List<String > productImagePaths = product.getMedia();
        for(String  url: productImagePaths){
            ImageTable productImage = new ImageTable();
            productImage.setProductID(product.getProductID());
            productImage.setImage(null);
            productImage.setImageURI(url);

            ProductsManager.insertImage(productImage);
        }
    }

    public static void updateExistingProduct(ResponseAddProduct.Product product){
        try {
            List<ProductTable> productTables = getProductDao().queryForEq(ProductTable.
                                               ColumnNames.PRODUCT_ID,
                                               product.getProductID());

            ProductTable productToUpdate = productTables.get(0);

            //ProductTable productToPersist = new ProductTable();
            //productToPersist.setProductID(product.getProductID());
            productToUpdate.setParentCategoryID(Integer.valueOf(product.getMainCategoryID().get(0)));

            productToUpdate.setParentCategoryNameEN(product.getMainCategorynameEN().get(0));
            productToUpdate.setParentCategoryNameAR(product.getMainCategorynameAR().get(0));

            int targetGroupID = -1;
            try {

                targetGroupID = Integer.valueOf(product.getTargetGroupID().get(0));

            }catch (Exception exc){

            }

            productToUpdate.setTargetGroup(targetGroupID);

            productToUpdate.setSubCategoryID(Integer.valueOf(product.getSubCategoryID().get(0)));
            productToUpdate.setSubCategoryNameAR(product.getSubCategoryNameAR().get(0));
            productToUpdate.setSubCategoryNameEN(product.getSubCategoryNameEn().get(0));
            productToUpdate.setProductNameEN(product.getProductNameEN());
            productToUpdate.setProductNameAR(product.getProductNameAR());
            productToUpdate.setPerDayOrderLimit(Integer.valueOf(product.getPerDayOrderLimit()));
            productToUpdate.setHandlingTime(Integer.valueOf(product.getHandlingTime()));
            productToUpdate.setProductPrice(Double.valueOf(product.getPrice()));
            productToUpdate.setDescriptionAR(product.getDescriptionAR());
            productToUpdate.setDescriptionEN(product.getDescriptionEN());

            String productColor = "";

            try {
                productColor = product.getColor().get(0);
            }catch (Exception exc){
                productColor = "";
            }

            productToUpdate.setColor(productColor);

            String size = "";

            try {
                size = product.getSize().get(0);
            }catch (Exception exc){
                size = "";
            }

            productToUpdate.setSize(size);

            getProductDao().update(productToUpdate);

            List<ImageTable> imageTables = getImageDao().queryForEq(ProductTable.
                            ColumnNames.PRODUCT_ID,
                    product.getProductID());

            ImageTable imageTable = imageTables.get(0);
            getImageDao().delete(imageTable);

            //Local URI Array, to be replaced with absolute URLs of backend
            List<String > productImagePaths = product.getMedia();
            for(String  url: productImagePaths){
                ImageTable productImage = new ImageTable();
                productImage.setProductID(product.getProductID());
                productImage.setImage(null);
                productImage.setImageURI(url);

                ProductsManager.insertImage(productImage);
            }


        } catch (SQLException e) {

        }



    }
}
