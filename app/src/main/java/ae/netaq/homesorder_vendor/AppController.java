package ae.netaq.homesorder_vendor;

import android.app.Application;
import ae.netaq.homesorder_vendor.db.DBManager;
import ae.netaq.homesorder_vendor.utils.Common;
import ae.netaq.homesorder_vendor.utils.DevicePreferences;

/**
 * Created by Netaq on 11/20/2017.
 */

public class AppController extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        DevicePreferences.getInstance().init(this); // init SharedPrefs

        DBManager.init(this);// init DB

        boolean preferredLocale = DevicePreferences.getInstance().isLocaleSetToArabic();

        Common.setAppLocaleToArabic(this,preferredLocale); // set preferred lcoale

    }

}
