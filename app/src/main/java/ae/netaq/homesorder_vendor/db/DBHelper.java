package ae.netaq.homesorder_vendor.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import ae.netaq.homesorder_vendor.db.data_manager.tables.CustomerTable;
import ae.netaq.homesorder_vendor.db.data_manager.tables.ImageTable;
import ae.netaq.homesorder_vendor.db.data_manager.tables.OrderTable;
import ae.netaq.homesorder_vendor.db.data_manager.tables.OrderedProductsTable;
import ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable;
import ae.netaq.homesorder_vendor.db.data_manager.tables.ShippingInfoTable;
import ae.netaq.homesorder_vendor.models.Customer;
import ae.netaq.homesorder_vendor.models.Order;
import ae.netaq.homesorder_vendor.models.OrderedProducts;
import ae.netaq.homesorder_vendor.models.Product;
import ae.netaq.homesorder_vendor.models.ShippingInfo;

/**
 * Created by Sabih Ahmed on 03-Dec-17.
 * sabih.ahmed@netaq.ae
 *
 */

public class DBHelper extends OrmLiteSqliteOpenHelper{

    private static String TAG = DBHelper.class.getSimpleName();
    private static String DATABASE_NAME = "vendor-db";
    private static int DATABASE_VERSION = 1;



    private static final Class<?> [] TABLES = {
            OrderTable.class,
            CustomerTable.class,
            ProductTable.class,
            OrderedProductsTable.class,
            ShippingInfoTable.class,
            ImageTable.class
            /**OrderedProducts.class**/
    };

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        for(int i=0;i<=TABLES.length-1;i++){
            try {
                TableUtils.createTable(connectionSource,TABLES[i]);
            } catch (SQLException e) {
                Log.e(TAG,"Error in Table create with table name:"+TABLES[i].getSimpleName());
            }
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {

        if(newVersion > oldVersion){
            for(int i=0;i<=TABLES.length-1;i++){
                try {
                    TableUtils.dropTable(connectionSource,TABLES[i],false);
                } catch (SQLException e) {
                    Log.e(TAG,"Error in Table Drop with table name:"+TABLES[0].getSimpleName());
                }
            }

            for(int i=0;i<=TABLES.length-1;i++){
                try {
                    TableUtils.createTable(connectionSource,TABLES[i]);
                } catch (SQLException e) {
                    Log.e(TAG,"Error in Table create with table name:"+TABLES[i].getSimpleName());
                }
            }



        }
    }

    public void clearDBData() {
        try {
            TableUtils.dropTable(connectionSource,ProductTable.class,true);
        } catch (SQLException e) {
            Log.e(TAG,"Error in clearing Products records");
        }
        try {
            TableUtils.dropTable(connectionSource,ImageTable.class,true);
        } catch (SQLException e) {
            Log.e(TAG,"Error in clearing Images records");
        }
        try {
            TableUtils.dropTable(connectionSource,OrderTable.class,true);
        } catch (SQLException e) {
            Log.e(TAG,"Error in clearing Orders records");
        }
    }
}
