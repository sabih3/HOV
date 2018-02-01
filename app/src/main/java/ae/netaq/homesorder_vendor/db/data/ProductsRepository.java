package ae.netaq.homesorder_vendor.db.data;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ae.netaq.homesorder_vendor.db.core.DatabaseHelper;
import ae.netaq.homesorder_vendor.db.core.DatabaseManager;
import ae.netaq.homesorder_vendor.db.tables.ImageTable;
import ae.netaq.homesorder_vendor.db.tables.ProductTable;

/**
 * Created by Sabih Ahmed on 05-Dec-17.
 */

public class ProductsRepository {

    private DatabaseHelper dbHelper = null;
    private DatabaseManager dbManager = null;
    private Dao<ProductTable,Integer> productDAO = null;
    private Dao<ImageTable,Integer> productImagesDAO = null;

    private String TAG = ProductsRepository.class.getSimpleName();


    public ProductsRepository(Context context) {
        dbManager = new DatabaseManager();
        dbHelper = dbManager.getHelper(context);
        try {
            productDAO = dbHelper.getProductDAO();
            productImagesDAO = dbHelper.getProductImages();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public long createProduct(ProductTable product){
        long productID = -1;

        try {
            productDAO.create(product);
        } catch (SQLException e) {
            Log.e(TAG,e.toString());
        }

        try {
            List<ProductTable> productTables = productDAO.queryForAll();
            int size = productTables.size();
            ProductTable persistedProduct = productTables.get(size - 1);
            productID = persistedProduct.getId();
        } catch (SQLException e) {
            Log.e(TAG,e.toString());
        }

        return productID;
    }



    public void update(ProductTable productToUpdate) throws SQLException {
        productDAO.update(productToUpdate);
    }

    public ProductTable getProductById(long productID){
        List<ProductTable> productTables = null;
        ProductTable product = null;
        try {
            productTables = productDAO.queryForEq(ProductTable.
                            ColumnNames.PRODUCT_ID,
                    productID);

            product = productTables.get(0);
        } catch (SQLException e) {

            e.printStackTrace();
        }

       return product;
    }

    public void deleteProduct(){

    }

    public List<ProductTable> getAllProducts() throws SQLException {
        List<ProductTable> persistedproducts = productDAO.queryForAll();

        for(ProductTable product :persistedproducts ){
            long productID = product.getProductID();
            Dao<ImageTable, Integer> imageDao = productImagesDAO;

            List<ImageTable> imageTables = imageDao.
                    queryForEq(ProductTable.ColumnNames.PRODUCT_ID, productID);
            product.setImagesArray(imageTables);

        }

        return persistedproducts;
    }

    public void insertProductImages(ImageTable productImage) {

        try {
            productImagesDAO.create(productImage);
        } catch (SQLException e) {
            Log.e(TAG, e.toString());
        }

    }

    public List<ImageTable> getProductImages(long productID){
        List<ImageTable> imageTables = new ArrayList<>();

        try {
            imageTables = productImagesDAO.queryForEq(ProductTable.
                            ColumnNames.PRODUCT_ID, productID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return imageTables;
    }

    public void deleteProductImages(ImageTable image){
        try {
            productImagesDAO.delete(image);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void relase() {
         dbManager.releaseHelper();
    }
}
