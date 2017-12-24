package ae.netaq.homesorder_vendor.activities.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
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
import ae.netaq.homesorder_vendor.utils.UIUtils;
import ae.netaq.homesorder_vendor.utils.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Netaq on 12/17/2017.
 */

public class RegisterActivity extends AppCompatActivity implements
        View.OnClickListener,
        Validator.ValidationListener,RegisterView{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.add_photo_image_view)
    CircleImageView logoImageView;

    @BindView(R.id.edit_photo_image_view)
    CircleImageView editPhotoImageView;

    @BindView(R.id.register_email_layout)
    TextInputLayout registerEmailLayout;

    @Pattern(regex = Regex.emailRegex, messageResId = R.string.valid_email_address_error, caseSensitive = false)
    @NotEmpty(messageResId = R.string.field_required)
    @BindView(R.id.register_email)
    EditText registerEmail;

    @BindView(R.id.register_password_layout)
    TextInputLayout registerPasswordLayout;

    @NotEmpty(messageResId = R.string.field_required)
    @Password(scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS, min = 8, messageResId = R.string.invalid_password_error)
    @BindView(R.id.register_password)
    EditText registerPassword;

    @BindView(R.id.register_retype_password_layout)
    TextInputLayout registerRetypePasswordLayout;

    @NotEmpty(messageResId = R.string.field_required)
    @ConfirmPassword(messageResId = R.string.passwords_match_error)
    @BindView(R.id.register_retype_password)
    EditText registerRetypePassword;

    @BindView(R.id.register_person_name_layout)
    TextInputLayout registerPersonNameLayout;

    @NotEmpty(messageResId = R.string.field_required)
    @BindView(R.id.register_person_name)
    EditText registerPersonName;

    @BindView(R.id.register_phone_layout)
    TextInputLayout registerPhoneLayout;

    @Pattern(regex = Regex.phoneRegex, messageResId = R.string.valid_phone_number_error)
    @NotEmpty(messageResId = R.string.field_required)
    @BindView(R.id.register_phone)
    EditText registerPhone;

    @BindView(R.id.register_shop_name_layout)
    TextInputLayout registerVendorNameLayout;

    @NotEmpty(messageResId = R.string.field_required)
    @BindView(R.id.register_shop_name)
    EditText registerVendorName;

    @BindView(R.id.register_btn)
    Button registerBtn;

    private Validator validator;

    private static final int SELECT_PICTURE = 100;

    private Picasso picasso;

    private Uri logoImageUri = null;

    private RegisterPresenter presenter;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        toolbar.setTitle(R.string.register);
        setSupportActionBar(toolbar);

        validator = new Validator(this);
        validator.setValidationListener(this);

        editPhotoImageView.setOnClickListener(this);

        registerBtn.setOnClickListener(this);

        picasso = AppController.get(this).getPicasso();

        presenter = new RegisterPresenter(this,this);

        progressDialog = new ProgressDialog(this, R.style.ProgressDialogTheme);

        initViews();
    }

    private void initViews() {

        setTextWatchers();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setTextWatchers() {
        registerEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!Utils.isValidEmail(registerEmail.getText().toString())){
                    registerEmailLayout.setError(getString(R.string.valid_email_address_error));
                }else{
                    registerEmailLayout.setError(null);
                }
            }
        });

        registerPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(registerPassword.getText().length()>0){
                    registerPasswordLayout.setError(null);
                }else{
                    registerPasswordLayout.setError(getString(R.string.field_required));
                }
            }
        });
        registerPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!Utils.isValidPhone(registerPhone.getText().toString())){
                    registerPhoneLayout.setError(getString(R.string.valid_phone_number_error));
                }else{
                    registerPhoneLayout.setError(null);
                }
            }
        });
        registerRetypePassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(registerRetypePassword.getText().toString().equals(registerPassword.getText().toString())){
                    registerRetypePasswordLayout.setError(null);
                }else{
                    registerRetypePasswordLayout.setError(getString(R.string.passwords_match_error));
                }
            }
        });
        registerVendorName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(registerVendorName.getText().length()>0){
                    registerVendorNameLayout.setError(null);
                }else{
                    registerVendorNameLayout.setError(getString(R.string.field_required));
                }
            }
        });
        registerPersonName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(registerPersonName.getText().length()>0){
                    registerPersonNameLayout.setError(null);
                }else{
                    registerPersonNameLayout.setError(getString(R.string.field_required));
                }
            }
        });
    }

    private void removeErrors() {
        registerEmailLayout.setError(null);
        registerPasswordLayout.setError(null);
        registerRetypePasswordLayout.setError(null);
        registerPersonNameLayout.setError(null);
        registerPhoneLayout.setError(null);
        registerVendorNameLayout.setError(null);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.edit_photo_image_view){
            openImageChooser();
        }else if(view.getId() == R.id.register_btn){
            removeErrors();
            validator.setValidationMode(Validator.Mode.BURST);
            validator.validate();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (data != null) {
                //For Single image
                logoImageUri = data.getData();
                picasso.load(logoImageUri).resize(200, 200).centerCrop().into(logoImageView);
            }
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
    public void onValidationSucceeded() {
        User.getInstance().setUserEmail(registerEmail.getText().toString());
        User.getInstance().setUserPassword(registerPassword.getText().toString());
        User.getInstance().setPersonName(registerPersonName.getText().toString());
        User.getInstance().setUserPhone(registerPhone.getText().toString());
        User.getInstance().setVendorName(registerVendorName.getText().toString());
        User.getInstance().setLogoUri(logoImageUri);


        //request network to register user
        presenter.registerUser();
        UIUtils.showProgressDialog(this, "Registering! Please Wait...", progressDialog);

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for(int i = 0 ; i<errors.size() ; i++) {
            if (errors.get(i).getView().getId() == R.id.register_email) {
                registerEmail.requestFocus();
                registerEmailLayout.setError(errors.get(i).getFailedRules().get(0).getMessage(this));
            } else if (errors.get(i).getView().getId() == R.id.register_password) {
                registerPassword.requestFocus();
                registerPasswordLayout.setError(errors.get(i).getFailedRules().get(0).getMessage(this));
            } else if (errors.get(i).getView().getId() == R.id.register_retype_password) {
                registerRetypePassword.requestFocus();
                registerRetypePasswordLayout.setError(errors.get(i).getFailedRules().get(0).getMessage(this));
            }else if (errors.get(i).getView().getId() == R.id.register_person_name) {
                registerPersonName.requestFocus();
                registerPersonNameLayout.setError(errors.get(i).getCollatedErrorMessage(this));
            }else if (errors.get(i).getView().getId() == R.id.register_phone) {
                registerPhone.requestFocus();
                registerPhoneLayout.setError(errors.get(i).getFailedRules().get(0).getMessage(this));
            } else if (errors.get(i).getView().getId() == R.id.register_shop_name) {
                registerVendorName.requestFocus();
                registerVendorNameLayout.setError(errors.get(i).getCollatedErrorMessage(this));
            }
        }
    }

    /** =====================Call backs related to Network Requests ============================
     * @param token**/

    //RegisterPresenter.registerUser
    @Override
    public void onRegistrationSuccess(String token) {
        User.getInstance().setUserToken(token);
        DevicePreferences.saveUserInfo(User.getInstance());

        UIUtils.hideProgressDialog(progressDialog);
        Utils.showToast(this, "USer Registered Successfully");
        NavigationController.showMainActivity(RegisterActivity.this);
        RegisterActivity.this.finish();

    }

    //RegisterPresenter.registerUser
    @Override
    public void onEmailTaken(String localizedError) {
        //TODO (1) : Handle Email Taken on view
        UIUtils.hideProgressDialog(progressDialog);
        UIUtils.showMessageDialog(this, localizedError,
                "Take me to login",
                "Cancel", new UIUtils.DialogButtonListener() {
            @Override
            public void onPositiveButtonClicked() {
                NavigationController.startActivitySignIn(RegisterActivity.this);
                finish();
            }

            @Override
            public void onNegativeButtonClicked() {

            }
        });
    }

    //RegisterPresenter.registerUser
    @Override
    public void onVendorNameTaken(String localizedError) {
       //TODO (2) : Handle Vendor Name Taken on view
        UIUtils.hideProgressDialog(progressDialog);
        UIUtils.showMessageDialog(this, localizedError,
                "Ok",
                "Cancel", new UIUtils.DialogButtonListener() {
                    @Override
                    public void onPositiveButtonClicked() {
                        registerVendorName.requestFocus();
                    }

                    @Override
                    public void onNegativeButtonClicked() {

                    }
                });
    }



    //RegisterPresenter.registerUser
    @Override
    public void onNetworkFailure() {
        //TODO (3) : Handle Network Failure
        UIUtils.hideProgressDialog(progressDialog);
        UIUtils.showMessageDialog(this, "Unable to connect with the internet",
                "Ok",
                "", new UIUtils.DialogButtonListener() {
                    @Override
                    public void onPositiveButtonClicked() {
                    }

                    @Override
                    public void onNegativeButtonClicked() {

                    }
                });
    }

    //RegisterPresenter.registerUser
    @Override
    public void onUnDefinedException(String localizedError) {
        //TODO (4) : Handle Undefined Exception on view
        UIUtils.hideProgressDialog(progressDialog);
        UIUtils.showMessageDialog(this, localizedError,
                "Ok",
                "Cancel", new UIUtils.DialogButtonListener() {
                    @Override
                    public void onPositiveButtonClicked() {
                        registerVendorName.requestFocus();
                    }

                    @Override
                    public void onNegativeButtonClicked() {

                    }
                });
    }

    /** =========================Network Call backs block end========= =========================**/
}

