package ae.netaq.homesorder_vendor.activities;

import android.content.Context;

import java.util.ArrayList;

import ae.netaq.homesorder_vendor.db.data_manager.UserDataManager;
import ae.netaq.homesorder_vendor.models.Country;

/**
 * Created by sabih on 30-Dec-17.
 */

public class AreaSelectionPresenter {

    private AreaSelectionView areaSelectionView;
    private Context context;



    public AreaSelectionPresenter(Context context, AreaSelectionView viewListener) {
        this.areaSelectionView = viewListener;
        this.context = context;
    }

    public void getRegionalData() {

        //Country country = CountryDataManager.getUAERegion(context);

//        ArrayList<Country> persistedRegions = UserDataManager.getUAERegion();
//
//        Country country = persistedRegions.get(0);
//
//        areaSelectionView.onRegionalDataFetched(country);



    }
}
