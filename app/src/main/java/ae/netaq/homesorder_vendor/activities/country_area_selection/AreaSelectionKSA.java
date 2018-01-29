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
import ae.netaq.homesorder_vendor.db.data_manager.UserDataManager;
import ae.netaq.homesorder_vendor.event_bus.KSAAreaSelectionEvent;
import ae.netaq.homesorder_vendor.models.Country;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AreaSelectionKSA extends AppCompatActivity {

    @BindView(R.id.area_list_ksa)
    ExpandableListView areaList;

    @BindView(R.id.btn_proceed)
    Button proceedButton;

    private AreaSelectionPresenter presenter;
    private AreaSelectionAdapter areaListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.area_selection_saudia);

        ButterKnife.bind(this);
        Country ksaRegion = UserDataManager.getKSARegion();

        loadRegionDataInList(ksaRegion);

        proceedButton.setOnClickListener(new ProceedButtonListener());
    }

    private void loadRegionDataInList(Country country) {
        areaListAdapter = new AreaSelectionAdapter(this, country);
        areaList.setAdapter(areaListAdapter);
    }

    private class ProceedButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Country country = areaListAdapter.getSelectedAreas();
            UserDataManager.persistKSARegion(country);

            List<Country.State> selectedStates = new ArrayList<>();
            ArrayList<Country.State.Area> userSelectedAreas;

            for(Country.State eachState :country.getStates()) {
                userSelectedAreas = new ArrayList<>();
                List<Country.State.Area> areas = eachState.getAreas();

                for (Country.State.Area eachArea : areas) {

                    if (eachArea.isSelected()) {

                        userSelectedAreas.add(eachArea);

                    }
                }

                eachState.setSelectedAreas(userSelectedAreas);

                if (eachState.getSelectedAreas().size() > 0) {
                    selectedStates.add(eachState);
                }
            }

            EventBus.getDefault().post(new KSAAreaSelectionEvent(country,selectedStates));
            AreaSelectionKSA.this.finish();
        }
    }
}
