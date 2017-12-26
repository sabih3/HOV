package ae.netaq.homesorder_vendor.activities.update_profile;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Pattern;
import com.squareup.picasso.Picasso;

import java.util.List;

import ae.netaq.homesorder_vendor.AppController;
import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.constants.Regex;
import ae.netaq.homesorder_vendor.models.User;
import ae.netaq.homesorder_vendor.utils.DevicePreferences;
import ae.netaq.homesorder_vendor.utils.NavigationController;
import ae.netaq.homesorder_vendor.utils.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Netaq on 12/20/2017.
 */

public class UpdateProfileActivity extends AppCompatActivity implements View.OnClickListener, Validator.ValidationListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.add_photo_image_view)
    CircleImageView logoImageView;

    @BindView(R.id.edit_photo_image_view)
    CircleImageView editPhotoImageView;

    @BindView(R.id.update_profile_email)
    EditText profileUpdateEmail;

    @BindView(R.id.update_profile_old_password_layout)
    TextInputLayout profileUpdateOldPasswordLayout;

    @NotEmpty(messageResId = R.string.field_required)
    @BindView(R.id.update_profile_old_password)
    EditText profileUpdateOldPassword;

    @BindView(R.id.update_profile_new_password_layout)
    TextInputLayout profileUpdateNewPasswordLayout;

    @Password(scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS, min = 8, messageResId = R.string.invalid_password_error)
    @BindView(R.id.update_profile_new_password)
    EditText profileUpdateNewPassword;

    @BindView(R.id.update_profile_confirm_new_password_layout)
    TextInputLayout profileUpdateConfirmNewPasswordLayout;

    @ConfirmPassword(messageResId = R.string.passwords_match_error)
    @BindView(R.id.update_profile_confirm_new_password)
    EditText profileUpdateConfirmNewPassword;

    @BindView(R.id.update_profile_person_name_layout)
    TextInputLayout updateProfilePersonNameLayout;

    @NotEmpty(messageResId = R.string.field_required)
    @BindView(R.id.update_profile_person_name)
    EditText updateProfilePersonName;

    @BindView(R.id.update_profile_phone_layout)
    TextInputLayout profileUpdatePhoneLayout;

    @Pattern(regex = Regex.phoneRegex, messageResId = R.string.valid_phone_number_error)
    @NotEmpty(messageResId = R.string.field_required)
    @BindView(R.id.update_profile_phone)
    EditText profileUpdatePhone;

    @BindView(R.id.update_profile_shop_name)
    EditText profileUpdateVendorName;

    @BindView(R.id.update_profile_btn)
    Button profileUpdateBtn;

    private Validator validator;

    private String imagePath = "";

    private static final int SELECT_PICTURE = 100;

    private Picasso picasso;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        ButterKnife.bind(this);

        toolbar.setTitle(R.string.update_profile);
        setSupportActionBar(toolbar);

        validator = new Validator(this);
        validator.setValidationListener(this);

        profileUpdateBtn.setOnClickListener(this);

        editPhotoImageView.setOnClickListener(this);

        picasso = AppController.get(this).getPicasso();

        initViews();

        setTextWatchers();

    }

    private void setTextWatchers() {

        profileUpdatePhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!Utils.isValidPhone(profileUpdatePhone.getText().toString())){
                    profileUpdatePhoneLayout.setError(getString(R.string.valid_phone_number_error));
                }else{
                    profileUpdatePhoneLayout.setError(null);
                }
            }
        });
    }

    private void initViews() {
        profileUpdateEmail.setText(DevicePreferences.getUserInfo().getUserEmail());
        profileUpdateVendorName.setText(DevicePreferences.getUserInfo().getVendorName());
        updateProfilePersonName.setText(DevicePreferences.getUserInfo().getPersonName());
        profileUpdatePhone.setText(DevicePreferences.getUserInfo().getUserPhone());

        profileUpdateEmail.setEnabled(false);
        profileUpdateEmail.setFocusable(false);
        profileUpdateVendorName.setEnabled(false);
        profileUpdateVendorName.setFocusable(false);

        profileUpdateEmail.setTextColor(getResources().getColor(R.color.lightGrey));
        profileUpdateVendorName.setTextColor(getResources().getColor(R.color.lightGrey));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        picasso.load("file://"+DevicePreferences.getUserInfo().getProfileImagePath()).into(logoImageView);
    }

    private void selectProfilePicture(){
        int permissionGrant = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);

        if(permissionGrant == PackageManager.PERMISSION_GRANTED){
            openImageChooser();
        }else{
            //ask permisssion
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    NavigationController.REQUEST_PERMISSION_STORAGE);
        }
    }

    public void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.select_pictures)), SELECT_PICTURE);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.update_profile_btn){
            if(profileUpdateNewPassword.getText().length() == 0){
                validator.removeRules(profileUpdateNewPassword);
                validator.removeRules(profileUpdateConfirmNewPassword);
            }else{
                validator = new Validator(this);
                validator.setValidationListener(this);
            }
            removeErrors();
            validator.validate();
        }else if(view.getId() == R.id.edit_photo_image_view){
            selectProfilePicture();
        }

    }

    private void removeErrors() {
        profileUpdatePhoneLayout.setError(null);
        updateProfilePersonNameLayout.setError(null);
        profileUpdateOldPasswordLayout.setError(null);
        profileUpdateNewPasswordLayout.setError(null);
        profileUpdateConfirmNewPasswordLayout.setError(null);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (data != null) {
                //For Single image
                imagePath = Utils.getPathBasedOnSDK(this,data.getData());
                picasso.load("file://"+imagePath).resize(200, 200).centerCrop().into(logoImageView);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if(requestCode == NavigationController.REQUEST_PERMISSION_STORAGE){
            openImageChooser();
        }
    }

    @Override
    public void onValidationSucceeded() {

        if(profileUpdateOldPassword.getText().toString().equals(DevicePreferences.getUserInfo().getUserPassword())){
            User.getInstance().setUserEmail(profileUpdateEmail.getText().toString());
            User.getInstance().setPersonName(updateProfilePersonName.getText().toString());
            User.getInstance().setUserPhone(profileUpdatePhone.getText().toString());
            User.getInstance().setVendorName(profileUpdateVendorName.getText().toString());
            User.getInstance().setUserPassword(profileUpdateOldPassword.getText().toString());
            if(imagePath!=""){
                User.getInstance().setProfileImagePath(imagePath);
            }else{
                User.getInstance().setProfileImagePath(DevicePreferences.getUserInfo().getProfileImagePath());
            }
            DevicePreferences.saveUserInfo(User.getInstance());
            Utils.showToast(this,"Profile Updated Successfully");
        }else{
            profileUpdateOldPasswordLayout.setError(getString(R.string.wrong_password_error));
        }

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for(int i = 0 ; i<errors.size() ; i++) {
            if (errors.get(i).getView().getId() == R.id.update_profile_phone) {
                profileUpdatePhone.requestFocus();
                profileUpdatePhoneLayout.setError(errors.get(i).getFailedRules().get(0).getMessage(this));
            } else if (errors.get(i).getView().getId() == R.id.update_profile_person_name) {
                updateProfilePersonName.requestFocus();
                updateProfilePersonNameLayout.setError(errors.get(i).getFailedRules().get(0).getMessage(this));
            } else if (errors.get(i).getView().getId() == R.id.update_profile_old_password) {
                profileUpdateOldPassword.requestFocus();
                profileUpdateOldPasswordLayout.setError(errors.get(i).getFailedRules().get(0).getMessage(this));
            }else if (errors.get(i).getView().getId() == R.id.update_profile_new_password) {
                profileUpdateNewPassword.requestFocus();
                profileUpdateNewPasswordLayout.setError(errors.get(i).getFailedRules().get(0).getMessage(this));
            }else if (errors.get(i).getView().getId() == R.id.update_profile_confirm_new_password) {
                profileUpdateConfirmNewPassword.requestFocus();
                profileUpdateConfirmNewPasswordLayout.setError(errors.get(i).getFailedRules().get(0).getMessage(this));
            }
        }
    }
}
