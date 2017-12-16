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
    private static int DATABASE_VERSION = 3;



    private static final Class<?> [] TABLES = {
            OrderTable.class,
            CustomerTable.class,
            ProductTable.class,
            OrderedProductsTable.class,
            ShippingInfoTable.class,
            ImageTable.class
    };

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource,TABLES[0]);
        } catch (SQLException e) {
            Log.e(TAG,"Error in Table create with table name:"+TABLES[0].getSimpleName());
        }

        try {
            TableUtils.createTable(connectionSource,TABLES[1]);
        } catch (SQLException e) {
            Log.e(TAG,"Error in Table create with table name:"+TABLES[1].getSimpleName());
        }

        try {
            TableUtils.createTable(connectionSource,TABLES[2]);
        } catch (SQLException e) {
            Log.e(TAG,"Error in Table create with table name:"+TABLES[2].getSimpleName());
        }

        try {
            TableUtils.createTable(connectionSource,TABLES[3]);
        } catch (SQLException e) {
            Log.e(TAG,"Error in Table create with table name:"+TABLES[3].getSimpleName());
        }

        try {
            TableUtils.createTable(connectionSource,TABLES[4]);
        } catch (SQLException e) {
            Log.e(TAG,"Error in Table create with table name:"+TABLES[4].getSimpleName());
        }

        try {
            TableUtils.createTable(connectionSource,TABLES[5]);
        } catch (SQLException e) {
            Log.e(TAG,"Error in Table create with table name:"+TABLES[5].getSimpleName());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {

        if(newVersion > oldVersion){
            try {
                TableUtils.dropTable(connectionSource,TABLES[0],false);
            } catch (SQLException e) {
                Log.e(TAG,"Error in Table Drop with table name:"+TABLES[0].getSimpleName());
            }

            try {
                TableUtils.dropTable(connectionSource,TABLES[1],false);
            } catch (SQLException e) {
                Log.e(TAG,"Error in Table Drop with table name:"+TABLES[1].getSimpleName());
            }

            try {
                TableUtils.dropTable(connectionSource,TABLES[2],false);
            } catch (SQLException e) {
                Log.e(TAG,"Error in Table Drop with table name:"+TABLES[2].getSimpleName());
            }

            try {
                TableUtils.dropTable(connectionSource,TABLES[3],false);
            } catch (SQLException e) {
                Log.e(TAG,"Error in Table Drop with table name:"+TABLES[3].getSimpleName());
            }

            try {
                TableUtils.dropTable(connectionSource,TABLES[4],false);
            } catch (SQLException e) {
                Log.e(TAG,"Error in Table Drop with table name:"+TABLES[4].getSimpleName());
            }

            try {
                TableUtils.dropTable(connectionSource,TABLES[5],false);
            } catch (SQLException e) {
                Log.e(TAG,"Error in Table Drop with table name:"+TABLES[5].getSimpleName());
            }

            try {
                TableUtils.createTable(connectionSource,TABLES[0]);
            } catch (SQLException e) {
                Log.e(TAG,"Error in Table Upgrade with table name:"+TABLES[0].getSimpleName());
            }

            try {
                TableUtils.createTable(connectionSource,TABLES[1]);
            } catch (SQLException e) {
                Log.e(TAG,"Error in Table create with table name:"+TABLES[1].getSimpleName());
            }

            try {
                TableUtils.createTable(connectionSource,TABLES[2]);
            } catch (SQLException e) {
                Log.e(TAG,"Error in Table create with table name:"+TABLES[2].getSimpleName());
            }

            try {
                TableUtils.createTable(connectionSource,TABLES[3]);
            } catch (SQLException e) {
                Log.e(TAG,"Error in Table create with table name:"+TABLES[3].getSimpleName());
            }

            try {
                TableUtils.createTable(connectionSource,TABLES[4]);
            } catch (SQLException e) {
                Log.e(TAG,"Error in Table create with table name:"+TABLES[4].getSimpleName());
            }

            try {
                TableUtils.createTable(connectionSource,TABLES[5]);
            } catch (SQLException e) {
                Log.e(TAG,"Error in Table create with table name:"+TABLES[5].getSimpleName());
            }
        }
    }
}
