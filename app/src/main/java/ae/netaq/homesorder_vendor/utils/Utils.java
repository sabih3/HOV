package ae.netaq.homesorder_vendor.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.widget.Toast;

import java.util.Locale;

import ae.netaq.homesorder_vendor.activities.SettingsActivity;

/**
 * Created by Netaq on 12/6/2017.
 */

public class Utils {

    public static void showToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    //The following code can be used to get the real path from the URI
    /* Get the real path from the URI */
    public static String getPathFromURI(Context context, Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    public static void configureLocal(Activity activity){
        if(DevicePreferences.getLang().equals("")){
            setLocale(activity, new Locale("en"));
        }else if (DevicePreferences.getLang().equals("ar")){
            setLocale(activity, new Locale("ar"));
        }else{
            setLocale(activity, new Locale("en"));
        }
    }

    public static void setLocale(Activity activity, Locale locale){

        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        activity.getBaseContext().getResources().updateConfiguration(config, activity.getBaseContext().getResources().getDisplayMetrics());

    }

}
