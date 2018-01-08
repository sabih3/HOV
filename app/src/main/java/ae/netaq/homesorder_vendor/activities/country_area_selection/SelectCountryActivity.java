package ae.netaq.homesorder_vendor.activities.country_area_selection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.utils.DevicePreferences;
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

    boolean uaeActivated, saudiActivated = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_country);
        ButterKnife.bind(this);

        layoutUAE.setOnClickListener(this);
        layoutSaudi.setOnClickListener(this);

        setupToolbar();

        initViews();

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
    }

    private void setUaeSelection(boolean check){
        if(check) {
            layoutUAE.setBackground(getResources().getDrawable(R.drawable.round_rect_shape_shitty_green));
            uaeMapImageView.setImageResource(R.drawable.ic_uae_map_black);
            uaeCheckBox.setChecked(true, true);
            setUaeResultLayout();
            uaeActivated = true;
        }else{
            layoutUAE.setBackground(getResources().getDrawable(R.drawable.round_rect_shape));
            uaeMapImageView.setImageResource(R.drawable.ic_uae_map_grey);
            uaeCheckBox.setChecked(false,false);
            uaeActivated = false;
        }
    }

    private void setSaudiSelection(boolean check){
        if(check){
            layoutSaudi.setBackground(getResources().getDrawable(R.drawable.round_rect_shape_shitty_green));
            saudiMapImageView.setImageResource(R.drawable.ic_saudi_map_black);
            saudiCheckBox.setChecked(true,true);
            setSaudiResultLayout();
            saudiActivated = true;
        }else{
            layoutSaudi.setBackground(getResources().getDrawable(R.drawable.round_rect_shape));
            saudiMapImageView.setImageResource(R.drawable.ic_saudi_map_grey);
            saudiCheckBox.setChecked(false,false);
            saudiActivated = false;
        }
    }

    private void setUaeResultLayout(){
        tapToSelectLayout.setVisibility(View.GONE);
        selectionResultLayout.setVisibility(View.VISIBLE);
        uaeSelectionResultLayout.setVisibility(View.VISIBLE);
    }

    private void setSaudiResultLayout(){
        tapToSelectLayout.setVisibility(View.GONE);
        selectionResultLayout.setVisibility(View.VISIBLE);
        saudiSelectionResultLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.select_country_proceed){

        }else if(view.getId() == R.id.layout_uae){
            if(!uaeActivated){
                setUaeSelection(true);
            }else{
                setUaeSelection(false);
            }
        }else if(view.getId() == R.id.layout_saudi){
            if(!saudiActivated){
                setSaudiSelection(true);
            }else{
                setSaudiSelection(false);
            }
        }
    }
}
