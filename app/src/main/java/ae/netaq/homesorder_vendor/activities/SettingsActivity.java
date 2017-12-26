package ae.netaq.homesorder_vendor.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.event_bus.LanguageChangeEvent;
import ae.netaq.homesorder_vendor.utils.Common;
import ae.netaq.homesorder_vendor.utils.DevicePreferences;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 12/11/2017.
 */

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_settings)
    Toolbar toolbar;

    @BindView(R.id.language_layout)
    RelativeLayout languageLayout;

    @BindView(R.id.selected_language)
    TextView selectedLanguage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        setToolbar();

        initViews();
    }

    private void setToolbar() {
        toolbar.setTitle(R.string.settings);
        setSupportActionBar(toolbar);

        if(DevicePreferences.isLocaleSetToArabic()){
            toolbar.setNavigationIcon(R.drawable.ic_prev_ar);
        }else{
            toolbar.setNavigationIcon(R.drawable.ic_prev);
        }
    }

    private void initViews() {

        String language = DevicePreferences.isLocaleSetToArabic() ?
                        getString(R.string.lang_name_eng) : getString(R.string.lang_name_arabic);


        selectedLanguage.setText(language);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        languageLayout.setOnClickListener(new LanguageSelectionListener());


    }

    private class LanguageSelectionListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(DevicePreferences.getInstance().isLocaleSetToArabic()){
                Common.setAppLocaleToArabic(SettingsActivity.this,false);
                DevicePreferences.setArabicLocale(false);
                EventBus.getDefault().post(new LanguageChangeEvent());
            }else{
                Common.setAppLocaleToArabic(SettingsActivity.this,true);
                DevicePreferences.setArabicLocale(true);
                EventBus.getDefault().post(new LanguageChangeEvent());
            }

            recreate();
        }
    }
}
