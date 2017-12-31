package ae.netaq.homesorder_vendor.activities;

import android.content.Context;

import com.google.gson.Gson;

import ae.netaq.homesorder_vendor.models.Areas;
import ae.netaq.homesorder_vendor.utils.JSONUtils;

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
        String areas_uae = JSONUtils.loadJSONFromAsset(context, "areas_uae.json");

        Gson gson = new Gson();
        Areas areas = gson.fromJson(areas_uae, Areas.class);



        areaSelectionView.onRegionalDataFetched(areas);



    }
}
