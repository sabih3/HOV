package ae.netaq.homesorder_vendor.activities.country_area_selection;

import android.content.Context;

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
