package ae.netaq.homesorder_vendor.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Netaq on 9/20/2017.
 */

public class DevicePreferences {

    private static final String LANGUAGE_PREF_KEY = "lang";
    private static final String KEY_ARABIC_LOCALE = "key_locale";

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
            editor.putString(LANGUAGE_PREF_KEY, token);
            editor.commit();

        }catch (Exception ex){

        }

    }

    public static String getLang(){

        String token = prefs.getString(LANGUAGE_PREF_KEY, "");
        return token;
    }

    /**Sets Preferred locale
     * pass setToArabic as true when setting preferred locale
     * to arabic, false otherwise
     * @param setToArabic
     */
    public static void setArabicLocale(boolean setToArabic){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(KEY_ARABIC_LOCALE,setToArabic);
        editor.commit();
    }

    /**Gives Preferred App locale
     * true in case of arabic,
     * false otherwise
     * @return
     */
    public static boolean isLocaleSetToArabic(){
        boolean localeSetToArabic;

        localeSetToArabic = prefs.getBoolean(KEY_ARABIC_LOCALE,false);

        return localeSetToArabic;
    }

}
