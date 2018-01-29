package ae.netaq.homesorder_vendor.activities.delivery_setup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.utils.DevicePreferences;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DeliverySetupActvity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_setup);
        ButterKnife.bind(this);

        setToolbar();
    }

    private void setToolbar() {
        toolbar.setTitle("Delivery Setup");
        setSupportActionBar(toolbar);

        if(DevicePreferences.isLocaleSetToArabic()){
            toolbar.setNavigationIcon(R.drawable.ic_prev_ar);
        }else{
            toolbar.setNavigationIcon(R.drawable.ic_prev);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
