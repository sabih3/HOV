package ae.netaq.homesorder_vendor.activities.country_area_selection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.AreaSelectionAdapter;
import ae.netaq.homesorder_vendor.db.data_manager.CountryDataManager;
import ae.netaq.homesorder_vendor.db.data_manager.UserDataManager;
import ae.netaq.homesorder_vendor.event_bus.UAEAreasSelectedEvent;
import ae.netaq.homesorder_vendor.models.Country;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AreaSelectionUAE extends AppCompatActivity implements AreaSelectionView {

    @BindView(R.id.area_list)
    ExpandableListView areaList;

    @BindView(R.id.btn_proceed)
    Button proceedButton;

    private AreaSelectionPresenter presenter;
    private AreaSelectionAdapter areaListAdapter;

    //private ArrayList<Country> selectedRegions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_selection);

        ButterKnife.bind(this);

        presenter = new AreaSelectionPresenter(this,this);

        proceedButton.setOnClickListener(new ProceedButtonListener());

//        selectedRegions = (ArrayList<Country>) getIntent().
//                          getSerializableExtra(NavigationController.KEY_SELECTED_REGION);

        //presenter.getRegionalData();

        Country uaeRegion = CountryDataManager.getUAERegion(this);

        loadRegionDataInList(uaeRegion);

    }

    @Override
    public void onRegionalDataFetched(Country country) {

        loadRegionDataInList(country);
    }

    private void loadRegionDataInList(Country country) {
        areaListAdapter = new AreaSelectionAdapter(this, country);
        areaList.setAdapter(areaListAdapter);
    }

    private class ProceedButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            Country country = areaListAdapter.getSelectedAreas();

            List<Country.State> selectedStates = new ArrayList<>();
            ArrayList<Country.State.Area> userSelectedAreas;

            for(Country.State eachState :country.getStates()){
                userSelectedAreas = new ArrayList<>();
                List<Country.State.Area> areas = eachState.getAreas();

                for(Country.State.Area eachArea: areas){

                    if(eachArea.isSelected()){

                        userSelectedAreas.add(eachArea);

                    }
                }

                eachState.setSelectedAreas(userSelectedAreas);

                if(eachState.getSelectedAreas().size()>0){
                    selectedStates.add(eachState);
                }
            }

            EventBus.getDefault().post(new UAEAreasSelectedEvent(country,selectedStates));

            AreaSelectionUAE.this.finish();

        }
    }
}
