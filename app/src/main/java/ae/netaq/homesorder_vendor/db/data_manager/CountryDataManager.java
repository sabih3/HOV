package ae.netaq.homesorder_vendor.db.data_manager;

import android.content.Context;

import com.google.gson.Gson;

import ae.netaq.homesorder_vendor.models.Country;
import ae.netaq.homesorder_vendor.utils.JSONUtils;

import static ae.netaq.homesorder_vendor.db.data_manager.CountryDataManager.CountryCodes.*;

/**
 * Created by sabih on 03-Jan-18.
 */

public class CountryDataManager {
    public static Country getUAERegion(Context context) {
        String areas_uae = JSONUtils.loadJSONFromAsset(context, "areas_uae.json");

        Gson gson = new Gson();
        Country UAE = gson.fromJson(areas_uae, Country.class);


        return UAE;
    }

    public static Country getKSARegion(Context context){
        String areas_ksa = JSONUtils.loadJSONFromAsset(context, "areas_sa.json");

        Gson gson = new Gson();
        Country KSA = gson.fromJson(areas_ksa, Country.class);

        return KSA;

    }


    public static class CountryCodes{
        public static String UAE = "AE";
        public static String KSA = "KSA";

    }
}
