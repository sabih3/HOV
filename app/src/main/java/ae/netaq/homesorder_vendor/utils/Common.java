package ae.netaq.homesorder_vendor.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.Locale;

/**
 * Created by sabih on 11-Dec-17.
 */

public class Common {

    public static void setAppLocaleToArabic(Context context, boolean setToArabic) {
        Resources resources = context.getResources();
        Locale locale = new Locale("en");
        Configuration configuration = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();

        if(setToArabic){
            locale = new Locale("ar");

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        }
        else{
            configuration.locale=locale;
        }

        context.getApplicationContext().getResources().updateConfiguration(configuration,
                context.getResources().getDisplayMetrics());


    }

    public static boolean isAPPLocaleArabic(Context context){
        boolean isAppLocaleArabic = false;

        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();

        if(resources.getConfiguration().locale.getLanguage().equals("ar")){

            isAppLocaleArabic = true;
        }

        return isAppLocaleArabic;

    }

    /**This method changes the view to RTL if arabic locale is chosen by user
     *
     * @param context
     * @param rootView
     * @Example changeViewWithLocale(context, rootContainerView)
     */
    public static void changeViewWithLocale(Context context,View rootView) {

        if(Common.isAPPLocaleArabic(context)){
            rootView.setRotationY(180);
        }
    }
}
