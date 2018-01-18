package ae.netaq.homesorder_vendor;

import android.app.Activity;
import android.app.Application;

import com.squareup.picasso.Picasso;

import ae.netaq.homesorder_vendor.dagger.AppComponent;
import ae.netaq.homesorder_vendor.dagger.ContextModule;
import ae.netaq.homesorder_vendor.dagger.DaggerAppComponent;
import ae.netaq.homesorder_vendor.db.DBManager;
import ae.netaq.homesorder_vendor.db.data_manager.CountryDataManager;
import ae.netaq.homesorder_vendor.db.data_manager.UserDataManager;
import ae.netaq.homesorder_vendor.models.Country;
import ae.netaq.homesorder_vendor.models.User;
import ae.netaq.homesorder_vendor.network.HomesOrderServices;
import ae.netaq.homesorder_vendor.utils.Common;
import ae.netaq.homesorder_vendor.utils.DevicePreferences;

/**
 * Created by Netaq on 11/20/2017.
 */

public class AppController extends Application{

    public static AppController get(Activity activity){
        return (AppController) activity.getApplication();
    }

    Picasso picasso;

    HomesOrderServices homesOrderServices;

    @Override
    public void onCreate() {
        super.onCreate();

        DevicePreferences.getInstance().init(this); // init SharedPrefs

        AppComponent component = DaggerAppComponent.builder().contextModule(new ContextModule(this)).build();

        homesOrderServices = component.getHomesOrderServices();
        picasso = component.getPicasso();


        DBManager.init(this);// init DB

        boolean preferredLocale = DevicePreferences.getInstance().isLocaleSetToArabic();

        Common.setAppLocaleToArabic(this,preferredLocale); // set preferred lcoale

        User cachedUser = DevicePreferences.getInstance().getUserInfo();

        Country uaeRegion = CountryDataManager.getUAERegion(this);
        Country ksaRegion = CountryDataManager.getKSARegion(this);

        UserDataManager.persistUAERegion(uaeRegion);
        UserDataManager.persistKSARegion(ksaRegion);


    }

    public Picasso getPicasso() {
        return picasso;
    }

    public HomesOrderServices getHomesOrderServices() {
        return homesOrderServices;
    }
}
