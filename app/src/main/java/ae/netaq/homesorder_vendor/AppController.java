package ae.netaq.homesorder_vendor;

import android.app.Application;
import ae.netaq.homesorder_vendor.db.core.DatabaseHelper;
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


        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.getWritableDatabase();

        boolean preferredLocale = DevicePreferences.getInstance().isLocaleSetToArabic();

        Common.setAppLocaleToArabic(this,preferredLocale); // set preferred lcoale

        /*User cachedUser = DevicePreferences.getInstance().getUserInfo();

        Country uaeRegion = CountryDataManager.getUAERegion(this);
        Country ksaRegion = CountryDataManager.getKSARegion(this);

        UserDataManager.persistUAERegion(uaeRegion);
        UserDataManager.persistKSARegion(ksaRegion);*/


    }

}
