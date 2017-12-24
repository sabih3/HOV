package ae.netaq.homesorder_vendor.activities.sign_in;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.ArrayList;
import java.util.List;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.db.data_manager.OrderDataManager;
import ae.netaq.homesorder_vendor.models.Order;
import ae.netaq.homesorder_vendor.models.User;
import ae.netaq.homesorder_vendor.network.services.OrderService;
import ae.netaq.homesorder_vendor.utils.DevicePreferences;
import ae.netaq.homesorder_vendor.utils.NavigationController;
import ae.netaq.homesorder_vendor.utils.UIUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 12/17/2017.
 */


public class SignInActivity extends AppCompatActivity implements View.OnClickListener,Validator.ValidationListener,SignInView{

    @BindView(R.id.sign_in_register_now)
    LinearLayout registerLayout;

    @BindView(R.id.sign_in_btn)
    Button addProductBtn;

    @BindView(R.id.sign_in_username_layout)
    TextInputLayout et_username_layout;
    @NotEmpty
    @BindView(R.id.sign_in_username)
    EditText et_userName;

    @BindView(R.id.sign_in_password_layout)
    TextInputLayout et_password_layout;
    @NotEmpty
    @BindView(R.id.sign_in_password)
    EditText et_password;

    private Validator validator;

    private SignInPresenter presenter;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        validator = new Validator(this);
        validator.setValidationMode(Validator.Mode.BURST);

        validator.setValidationListener(this);

        addProductBtn.setOnClickListener(this);

        registerLayout.setOnClickListener(this);

        presenter = new SignInPresenter(this,this);

        progressDialog = new ProgressDialog(this, R.style.ProgressDialogTheme);

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.sign_in_btn){
            removeErrors();
            validator.validate();
        }else if(view.getId() == R.id.sign_in_register_now){
            NavigationController.startActivityRegister(SignInActivity.this);
        }

    }


    //SignInPresenter.requestLogin
    @Override
    public void onLoggedIn(String token) {

        User.getInstance().setUserToken(token);
        DevicePreferences.getInstance().saveUserInfo(User.getInstance());

        OrderService.getAllOrders(this,new OrderService.OrderFetchListener() {
            @Override
            public void onOrdersFetched(ArrayList<Order> orders) {

                OrderDataManager.persistAllOrders(orders, new OrderDataManager.OrderPersistenceListener() {
                    @Override
                    public void onOrdersPersisted() {

                        NavigationController.showMainActivity(SignInActivity.this);
                        finish();

                    }
                });
            }

        });
    }

    //SignInPresenter.requestLogin
    @Override
    public void onLoginFailure(String exception) {
        //TODO (2): Handle UI for exception
        UIUtils.hideProgressDialog(progressDialog);
        UIUtils.showMessageDialog(this, exception,
                "Ok",
                "Cancel", new UIUtils.DialogButtonListener() {
                    @Override
                    public void onPositiveButtonClicked() {
                    }

                    @Override
                    public void onNegativeButtonClicked() {

                    }
                });
    }

    //SignInPresenter.requestLogin
    @Override
    public void onNetworkFailure() {
        //TODO (3): Handle UI for exception
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

    //SignInPresenter.requestLogin
    @Override
    public void onUnDefinedException(String localizedError) {
        //TODO (4): Handle UI for exception
        UIUtils.hideProgressDialog(progressDialog);
        UIUtils.showMessageDialog(this, localizedError,
                "Ok",
                "Cancel", new UIUtils.DialogButtonListener() {
                    @Override
                    public void onPositiveButtonClicked() {
                    }

                    @Override
                    public void onNegativeButtonClicked() {

                    }
                });
    }

    @Override
    public void onValidationSucceeded() {
        UIUtils.showProgressDialog(this, "Signing In! Please Wait...", progressDialog);
        presenter.requestLogin(et_userName.getText().toString().trim(),et_password.getText().toString().trim());
    }

    private void removeErrors() {
        et_username_layout.setError(null);
        et_password_layout.setError(null);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for(int i = 0 ; i<errors.size() ; i++) {
            if (errors.get(i).getView().getId() == R.id.sign_in_username) {
                et_userName.requestFocus();
                et_username_layout.setError(errors.get(i).getCollatedErrorMessage(this));
            } else if (errors.get(i).getView().getId() == R.id.sign_in_password) {
                et_password.requestFocus();
                et_password_layout.setError(errors.get(i).getCollatedErrorMessage(this));
            }
        }
    }
}
