package ae.netaq.homesorder_vendor.db;

import android.content.Context;

/**
 * Created by sabih on 03-Dec-17.
 */

public class DBManager {

    private static DBManager instance;
    private static DBHelper dbHelper;

    private DBManager(Context context) {


        dbHelper = new DBHelper(context);

        dbHelper.getWritableDatabase();

    }

    public static void init(Context context){

        if(instance == null){
            instance = new DBManager(context);

        }

    }

    public static DBManager getInstance(){

        return instance;
    }


    public DBHelper getDbHelper(){

        return dbHelper;
    }


}
