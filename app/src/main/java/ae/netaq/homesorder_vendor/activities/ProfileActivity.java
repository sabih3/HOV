package ae.netaq.homesorder_vendor.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.utils.DevicePreferences;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Netaq on 12/19/2017.
 */

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_profile_view)
    Toolbar toolbar;

    @BindView(R.id.profile_view_person_name)
    TextView personName;

    @BindView(R.id.profile_view_vendor_email)
    TextView vendorEmail;

    @BindView(R.id.profile_view_vendor_phone)
    TextView vendorPhone;

    @BindView(R.id.profile_view_vendor_name)
    TextView vendorName;

    @BindView(R.id.profile_view_vendor_profile_image)
    CircleImageView vendorProfileImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        ButterKnife.bind(this);

        setToolbar();

        initViews();
    }

    private void initViews() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        personName.setText(DevicePreferences.getUserInfo().getPersonName());
        vendorEmail.setText(DevicePreferences.getUserInfo().getUserEmail());
        vendorPhone.setText(DevicePreferences.getUserInfo().getUserPhone());
        vendorName.setText(DevicePreferences.getUserInfo().getVendorName());

        if(DevicePreferences.getUserInfo().getProfileImagePath()!=""){
            Picasso.with(this).load("file://"+DevicePreferences.getUserInfo().getProfileImagePath()).into(vendorProfileImg);
        }

    }

    private void setToolbar() {
        toolbar.setTitle(R.string.profile);
        setSupportActionBar(toolbar);

        if(DevicePreferences.isLocaleSetToArabic()){
            toolbar.setNavigationIcon(R.drawable.ic_prev_ar);
        }else{
            toolbar.setNavigationIcon(R.drawable.ic_prev);
        }
    }
}
