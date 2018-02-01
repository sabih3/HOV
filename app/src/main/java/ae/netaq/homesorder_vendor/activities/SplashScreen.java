package ae.netaq.homesorder_vendor.activities;

import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.transitionseverywhere.TransitionManager;

import java.util.ArrayList;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.db.data_manager.CountryDataManager;
import ae.netaq.homesorder_vendor.db.data_manager.OrderDataManager;
import ae.netaq.homesorder_vendor.db.data_manager.UserDataManager;
import ae.netaq.homesorder_vendor.models.Country;
import ae.netaq.homesorder_vendor.models.Order;
import ae.netaq.homesorder_vendor.network.services.OrderService;
import ae.netaq.homesorder_vendor.utils.Common;
import ae.netaq.homesorder_vendor.utils.DevicePreferences;
import ae.netaq.homesorder_vendor.utils.NavigationController;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreen extends AppCompatActivity implements Animation.AnimationListener, View.OnClickListener{

    @BindView(R.id.logo_image_view)
    ImageView logo;

    @BindView(R.id.language_spinner_next_layout)
    RelativeLayout selectLanguageLayout;

    @BindView(R.id.transitions_container)
    LinearLayout transitionsContainer;

    @BindView(R.id.splash_start_btn)
    FloatingActionButton startBtn;

    @BindView(R.id.splash_language_spinner)
    Spinner languageSpinner;

    private Animation fadeInAnimation;

    Handler handler = new Handler();

    ArrayList<String> languageList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);

        fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_anim);

        fadeInAnimation.setAnimationListener(this);

        startBtn.setOnClickListener(this);

        logo.startAnimation(fadeInAnimation);

        initViews();

    }

    private void initViews() {
        languageList = new ArrayList<>();

        languageList.add(getString(R.string.choose_language));

        languageList.add(getString(R.string.lang_name_eng));

        languageList.add(getString(R.string.lang_name_arabic));

        ArrayAdapter<String> languageAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, languageList);

        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        languageSpinner.setAdapter(languageAdapter);

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0){
                    if(i == 2){
                        Common.setAppLocaleToArabic(SplashScreen.this,true);
                        DevicePreferences.setArabicLocale(true);
                        DevicePreferences.saveLang("ar");
                    }else{
                        Common.setAppLocaleToArabic(SplashScreen.this,false);
                        DevicePreferences.setArabicLocale(false);
                        DevicePreferences.saveLang("en");
                    }
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            startBtn.show();
                        }
                    }, 300);

                }else{
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            startBtn.hide();
                        }
                    }, 300);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.splash_start_btn){
            NavigationController.startActivitySignIn(this);
            finish();
        }

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        //if language has not been selected in past, indication of first time install
        if(DevicePreferences.getLang().equals("")) {
            if (animation == fadeInAnimation) {
                TransitionManager.beginDelayedTransition(transitionsContainer);
                selectLanguageLayout.setVisibility(View.VISIBLE);

                handler.postDelayed(new Runnable() {
                    public void run() {
                        startBtn.hide();
                    }
                }, 100);
            }
        }else{
            if(DevicePreferences.getUserInfo() == null){
                NavigationController.startActivitySignIn(SplashScreen.this);
                finish();
            }else{
                NavigationController.showMainActivity(SplashScreen.this);
                finish();

            }
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
