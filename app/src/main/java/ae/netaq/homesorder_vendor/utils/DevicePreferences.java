package ae.netaq.homesorder_vendor.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import ae.netaq.homesorder_vendor.constants.Constants;
import ae.netaq.homesorder_vendor.models.User;

/**
 * Created by Netaq on 9/20/2017.
 */

public class DevicePreferences {

    private static final String LANGUAGE_PREF_KEY = "lang";
    private static final String KEY_ARABIC_LOCALE = "key_locale";
    private static final String KEY_USER_INFO = "user_info";
    private static final String KEY_CACHED_PWD = "key_pwd";


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

    public void saveUserInfo(User user){
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString(KEY_USER_INFO, json);
        editor.commit();
    }

    public User getUserInfo(){
        Gson gson = new Gson();
        String json = prefs.getString(KEY_USER_INFO, "");
        User obj = gson.fromJson(json, User.class);
        return obj;
    }

    public void setPasswordInCache(String password) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_CACHED_PWD,password);
        editor.commit();
    }

    public String getCachedPassword(){
        String cachedPwd = "";
        cachedPwd = prefs.getString(KEY_CACHED_PWD,"");
        return cachedPwd;
    }
}
