package ae.netaq.homesorder_vendor.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import ae.netaq.homesorder_vendor.models.Customer;
import ae.netaq.homesorder_vendor.models.Order;

/**
 * Created by Sabih Ahmed on 03-Dec-17.
 * sabih.ahmed@netaq.ae
 *
 */

public class DBHelper extends OrmLiteSqliteOpenHelper{

    private static String TAG = DBHelper.class.getSimpleName();
    private static String DATABASE_NAME = "HO-vendor-db";
    private static int DATABASE_VERSION = 1;

    private Dao<Order,Integer> orderDAO;
    private Dao<Customer,Integer> customerDAO;

    private static final Class<?> [] TABLES = {
            Order.class,
            Customer.class
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
                TableUtils.createTable(connectionSource,TABLES[0]);
            } catch (SQLException e) {
                Log.e(TAG,"Error in Table Upgrade with table name:"+TABLES[0].getSimpleName());
            }

            try {
                TableUtils.createTable(connectionSource,TABLES[1]);
            } catch (SQLException e) {
                Log.e(TAG,"Error in Table create with table name:"+TABLES[1].getSimpleName());
            }
        }
    }
}
