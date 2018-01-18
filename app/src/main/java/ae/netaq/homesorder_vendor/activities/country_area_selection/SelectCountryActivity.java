package ae.netaq.homesorder_vendor.activities.country_area_selection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.event_bus.KSAAreaSelectionEvent;
import ae.netaq.homesorder_vendor.event_bus.UAEAreasSelectedEvent;
import ae.netaq.homesorder_vendor.models.Country;
import ae.netaq.homesorder_vendor.utils.DevicePreferences;
import ae.netaq.homesorder_vendor.utils.NavigationController;
import ae.netaq.homesorder_vendor.utils.UIUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.refactor.library.SmoothCheckBox;

public class SelectCountryActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.layout_uae)
    LinearLayout layoutUAE;

    @BindView(R.id.layout_saudi)
    LinearLayout layoutSaudi;

    @BindView(R.id.uae_map_image)
    ImageView uaeMapImageView;

    @BindView(R.id.selection_result_layout)
    LinearLayout selectionResultLayout;

    @BindView(R.id.uae_selection_result_layout)
    LinearLayout uaeSelectionResultLayout;

    @BindView(R.id.saudi_selection_result_layout)
    LinearLayout saudiSelectionResultLayout;

    @BindView(R.id.tap_to_select_layout)
    LinearLayout tapToSelectLayout;

    @BindView(R.id.saudi_map_image)
    ImageView saudiMapImageView;

    @BindView(R.id.uae_check)
    SmoothCheckBox uaeCheckBox;

    @BindView(R.id.saudi_check)
    SmoothCheckBox saudiCheckBox;

    @BindView(R.id.select_country_proceed)
    Button btnProceed;

    @BindView(R.id.remove_uae)
    TextView removeUAE;

    @BindView(R.id.remove_ksa)
    TextView removeKSA;

    @BindView(R.id.txt_uae_area_setup)
    TextView uaeAreaSetup;

    @BindView(R.id.txt_ksa_area_setup)
    TextView ksaAreaSetup;


    boolean uaeActivated, saudiActivated = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_country);
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);

        layoutUAE.setOnClickListener(this);
        layoutSaudi.setOnClickListener(this);

        uaeAreaSetup.setOnClickListener(new UAEAreaSelectionListener());
        ksaAreaSetup.setOnClickListener(new KSAAreaSelectionListener());

        removeUAE.setOnClickListener(new RemoveUAEListener());
        removeKSA.setOnClickListener(new RemoveKSAListener());
        setupToolbar();

        initViews();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Subscribe
    public void onUAEAreaSelected(UAEAreasSelectedEvent uaeAreasSelection){

        List<Country.State> uaeSelectedStates = uaeAreasSelection.getSelectedStates();

        if(uaeSelectedStates.size()>0){
            uaeAreaSetup.setTextColor(ContextCompat.getColor(this,R.color.shittyDarkGreen));
            uaeAreaSetup.setText("View / Edit Selected Areas");
        }else{
            uaeAreaSetup.setTextColor(ContextCompat.getColor(this,R.color.black));
            uaeAreaSetup.setText("No Areas Selected- Tap To Select");
        }


    }

    @Subscribe
    public void onKSAAreaSelected(KSAAreaSelectionEvent ksaAreaSelectionEvent){
        List<Country.State> ksaSelectedStates = ksaAreaSelectionEvent.getSelectedStates();
        if(ksaSelectedStates.size()>0){
            ksaAreaSetup.setTextColor(ContextCompat.getColor(this,R.color.shittyDarkGreen));
            ksaAreaSetup.setText("View / Edit Selected Areas");
        }else{
            ksaAreaSetup.setTextColor(ContextCompat.getColor(this,R.color.black));
            ksaAreaSetup.setText("No Areas Selected- Tap To Select");
        }
    }

    private void setupToolbar() {

        toolbar.setTitle(R.string.select_country);
        setSupportActionBar(toolbar);

        if(DevicePreferences.isLocaleSetToArabic()){
            toolbar.setNavigationIcon(R.drawable.ic_prev_ar);
        }else{
            toolbar.setNavigationIcon(R.drawable.ic_prev);
        }
    }

    private void initViews() {

        btnProceed.setOnClickListener(new ProceedButtonListener());


    }

    private void setUaeSelection(boolean check){
        if(check) {
            layoutUAE.setBackground(getResources().getDrawable(R.drawable.round_rect_shape_shitty_green));
            uaeMapImageView.setImageResource(R.drawable.ic_uae_map_black);
            uaeCheckBox.setChecked(true, true);
            setUaeResultLayout(View.VISIBLE);
            uaeActivated = true;

        }else{
            layoutUAE.setBackground(getResources().getDrawable(R.drawable.round_rect_shape));
            uaeMapImageView.setImageResource(R.drawable.ic_uae_map_grey);
            uaeCheckBox.setChecked(false,false);
            setUaeResultLayout(View.GONE);
            uaeActivated = false;
        }

    }

    private void setSaudiSelection(boolean check){
        if(check){
            layoutSaudi.setBackground(getResources().getDrawable(R.drawable.round_rect_shape_shitty_green));
            saudiMapImageView.setImageResource(R.drawable.ic_saudi_map_black);
            saudiCheckBox.setChecked(true,true);
            setSaudiResultLayout(View.VISIBLE);
            saudiActivated = true;
        }else{
            layoutSaudi.setBackground(getResources().getDrawable(R.drawable.round_rect_shape));
            saudiMapImageView.setImageResource(R.drawable.ic_saudi_map_grey);
            saudiCheckBox.setChecked(false,false);
            setSaudiResultLayout(View.GONE);
            saudiActivated = false;
        }

    }

    private void setUaeResultLayout(int visibility){
        //tapToSelectLayout.setVisibility(visibility);
        //selectionResultLayout.setVisibility(visibility);
        uaeSelectionResultLayout.setVisibility(visibility);
    }

    private void setSaudiResultLayout(int visibility){
        //tapToSelectLayout.setVisibility(visibility);
        //selectionResultLayout.setVisibility(visibility);
        saudiSelectionResultLayout.setVisibility(visibility);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.select_country_proceed){


        }else if(view.getId() == R.id.layout_uae){
            setUaeSelection(true);
        }else if(view.getId() == R.id.layout_saudi){
            setSaudiSelection(true);
        }
    }

    private class ProceedButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

//            if(uaeActivated && saudiActivated){
//                //Todo: Mark this to start both activities from here
//                Log.d("","");
//            }else{
//                if(!uaeActivated && !saudiActivated){
//                    return;
//                }else{
//                    if(uaeActivated){
//                        NavigationController.showAreaSelectionUAE(SelectCountryActivity.this);
//                    }
//
//                    if(saudiActivated){
//                        NavigationController.showAreaSelectionKSA(SelectCountryActivity.this);
//                    }
//                }
//            }


        }
    }

    private class UAEAreaSelectionListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            NavigationController.showAreaSelectionUAE(SelectCountryActivity.this);
        }
    }

    private class KSAAreaSelectionListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            NavigationController.showAreaSelectionKSA(SelectCountryActivity.this);
        }
    }

    private class RemoveUAEListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            UIUtils.showMessageDialog(SelectCountryActivity.this, "Do you really want to remove UAE from your coverage?",
                    "No", "Yes", new UIUtils.DialogButtonListener() {
                        @Override
                        public void onPositiveButtonClicked() {

                        }

                        @Override
                        public void onNegativeButtonClicked() {
                            setUaeSelection(false);
                        }
                    }
            );

        }
    }

    private class RemoveKSAListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            UIUtils.showMessageDialog(SelectCountryActivity.this, "Do you really want to remove Saudia Arabia from your coverage?",
                    "No", "Yes", new UIUtils.DialogButtonListener() {
                        @Override
                        public void onPositiveButtonClicked() {

                        }

                        @Override
                        public void onNegativeButtonClicked() {
                            setSaudiSelection(false);
                        }
                    }
            );
        }
    }
}