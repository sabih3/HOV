package ae.netaq.homesorder_vendor.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.AreaSelectionAdapter;
import ae.netaq.homesorder_vendor.models.Areas;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AreaSelectionScreen extends AppCompatActivity implements AreaSelectionView{

    @BindView(R.id.area_list)
    ExpandableListView areaList;

    private AreaSelectionPresenter presenter;
    private AreaSelectionAdapter areaListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_selection);

        ButterKnife.bind(this);

        presenter = new AreaSelectionPresenter(this,this);

        presenter.getRegionalData();

    }

    @Override
    public void onRegionalDataFetched(Areas areas) {
        loadRegionDataInList(areas);
    }

    private void loadRegionDataInList(Areas areas) {
        areaListAdapter = new AreaSelectionAdapter(this,areas);
        areaList.setAdapter(areaListAdapter);
    }
}
