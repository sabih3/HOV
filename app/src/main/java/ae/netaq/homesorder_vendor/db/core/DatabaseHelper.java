package ae.netaq.homesorder_vendor.db.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import ae.netaq.homesorder_vendor.db.tables.ImageTable;
import ae.netaq.homesorder_vendor.db.tables.OrderTable;
import ae.netaq.homesorder_vendor.db.tables.OrderedProducts;
import ae.netaq.homesorder_vendor.db.tables.ProductTable;

/**
 * Created by Sabih Ahmed on 03-Dec-2017.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper{


    private static final String databaseName = "vendor-db";
    private static final int databaseVersion = 1;

    private Dao<OrderTable,Integer> orderDAO = null;
    private Dao<ProductTable,Integer> productDAO = null;
    private Dao<ImageTable,Integer> imagesDAO = null;
    private Dao<OrderedProducts, Integer> orderdProductDAO = null;

    private Class<?>[] TABLES = {
            OrderTable.class,
            ProductTable.class,
            ImageTable.class,
            OrderedProducts.class
    };


    public DatabaseHelper(Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        for(Class<?> daoClass: TABLES){
            try {
                TableUtils.createTable(connectionSource,daoClass);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){

            for(Class<?> daoClass: TABLES){
                try {
                    TableUtils.dropTable(connectionSource,daoClass,false);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            for(Class<?> daoClass: TABLES){
                try {
                    TableUtils.createTable(connectionSource,daoClass);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public Dao<OrderTable,Integer> getOrderDAO() throws SQLException {
        if(orderDAO == null){

            orderDAO = DaoManager.createDao(connectionSource,OrderTable.class);
        }

        return orderDAO;
    }

    public Dao<ProductTable,Integer> getProductDAO() throws SQLException {
        if(productDAO == null){
            productDAO = DaoManager.createDao(connectionSource,ProductTable.class);
        }

        return productDAO;
    }

    public Dao<ImageTable,Integer> getProductImages() throws SQLException {
        if(imagesDAO == null){
            imagesDAO = DaoManager.createDao(connectionSource,ImageTable.class);
        }

        return imagesDAO;
    }

    public Dao<OrderedProducts, Integer> getOrderedProductsDAO() throws SQLException{
        if(orderdProductDAO == null){
            orderdProductDAO = DaoManager.createDao(connectionSource,OrderedProducts.class);
        }
        return orderdProductDAO;
    }
}
