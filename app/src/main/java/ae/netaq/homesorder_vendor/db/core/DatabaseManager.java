package ae.netaq.homesorder_vendor.db.core;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

/**
 * Created by Sabih Ahmed on 03-Dec-Jan-17.
 */

public class DatabaseManager {

    private DatabaseHelper databaseHelper = null;

    public DatabaseHelper getHelper(Context context){

        if(databaseHelper == null){
            databaseHelper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
        }

        return databaseHelper;
    }

    public void releaseHelper(){

        if(databaseHelper != null){
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }
}
