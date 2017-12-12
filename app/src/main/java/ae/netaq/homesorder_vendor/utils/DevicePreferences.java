package ae.netaq.homesorder_vendor.utils;

import android.content.Context;
import android.content.SharedPreferences;

import ae.netaq.homesorder_vendor.constants.Constants;

/**
 * Created by Netaq on 9/20/2017.
 */

public class DevicePreferences {

    private static DevicePreferences instance;
    private static SharedPreferences prefs;

    private Context mContext;

    private DevicePreferences() {

    }

    public static DevicePreferences getInstance(){

        if(instance == null ){
            instance = new DevicePreferences();
        }
        return instance;

    }

    public void init(Context context){

        this.mContext = context;

        prefs = mContext.getSharedPreferences("prefs", Context.MODE_PRIVATE);
    }

    public static void saveLang(String token) {

        SharedPreferences.Editor editor;

        try{
            editor = prefs.edit();
            editor.putString(Constants.LANGUAGE_PREF_KEY, token);
            editor.commit();

        }catch (Exception ex){

        }

    }

    public static String getLang(){

        String token = prefs.getString(Constants.LANGUAGE_PREF_KEY, "");
        return token;
    }

}
