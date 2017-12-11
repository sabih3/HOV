package ae.netaq.homesorder_vendor.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.utils.DevicePreferences;
import ae.netaq.homesorder_vendor.utils.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 12/11/2017.
 */

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.language_spinner)
    Spinner languageSpinner;

    List<String> languagesArray;

    private static boolean configChanges = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        setLocal();

        setToolbar();

        initViews();
    }

    private void setToolbar() {
        toolbar.setTitle(R.string.settings);
        if(DevicePreferences.getLang().equals("ar"))
        toolbar.setNavigationIcon(R.drawable.ic_prev_ar);
        else
        toolbar.setNavigationIcon(R.drawable.ic_prev);
        setSupportActionBar(toolbar);
    }

    private void setLocal() {
        Utils.configureLocal(this);
        if(configChanges){
            configChanges = false;
            recreate();
        }
    }

    private void initViews() {
        languagesArray = Arrays.asList(getResources().getStringArray(R.array.languages_array));

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, languagesArray);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(dataAdapter);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if(DevicePreferences.getLang().equals("en")){
            languageSpinner.setSelection(0);
        }else if(DevicePreferences.getLang().equals("ar")){
            languageSpinner.setSelection(1);
        }

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0 && !DevicePreferences.getLang().equals("en")) {
                    DevicePreferences.saveLang("en");
                    configChanges = true;
                    MainActivity.configChanges = true;
                    setLocal();
                }else if(i == 1 && !DevicePreferences.getLang().equals("ar")){
                    DevicePreferences.saveLang("ar");
                    configChanges = true;
                    MainActivity.configChanges = true;
                    setLocal();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
    }

}
