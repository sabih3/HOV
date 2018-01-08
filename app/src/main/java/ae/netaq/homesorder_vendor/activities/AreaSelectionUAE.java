package ae.netaq.homesorder_vendor.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.activities.country_area_selection.SelectCountryActivity;
import ae.netaq.homesorder_vendor.adapters.AreaSelectionAdapter;
import ae.netaq.homesorder_vendor.db.data_manager.UserDataManager;
import ae.netaq.homesorder_vendor.models.Country;
import ae.netaq.homesorder_vendor.utils.NavigationController;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AreaSelectionScreen extends AppCompatActivity implements AreaSelectionView{

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

        presenter.getRegionalData();

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

            List<Country.State> selectedStates = areaListAdapter.getSelectedAreas();
            selectedStates.size();

            if(selectedStates.size()==1){
                SelectCountryActivity.selectedRegions.get(0).setSelectedStates(selectedStates);
            }

            AreaSelectionScreen.this.finish();

        }
    }
}
