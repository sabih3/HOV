package ae.netaq.homesorder_vendor;

import android.app.Activity;
import android.app.Application;

import com.squareup.picasso.Picasso;

import ae.netaq.homesorder_vendor.dagger.AppComponent;
import ae.netaq.homesorder_vendor.dagger.ContextModule;
import ae.netaq.homesorder_vendor.dagger.DaggerAppComponent;
import ae.netaq.homesorder_vendor.db.DBManager;
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

    }

    public Picasso getPicasso() {
        return picasso;
    }

    public HomesOrderServices getHomesOrderServices() {
        return homesOrderServices;
    }
}
