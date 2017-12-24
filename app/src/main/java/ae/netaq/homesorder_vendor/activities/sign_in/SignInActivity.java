package ae.netaq.homesorder_vendor.activities.sign_in;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.db.data_manager.OrderDataManager;
import ae.netaq.homesorder_vendor.models.Order;
import ae.netaq.homesorder_vendor.models.User;
import ae.netaq.homesorder_vendor.network.services.OrderService;
import ae.netaq.homesorder_vendor.utils.DevicePreferences;
import ae.netaq.homesorder_vendor.utils.NavigationController;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 12/17/2017.
 */


public class SignInActivity extends AppCompatActivity implements View.OnClickListener,SignInView{

    @BindView(R.id.sign_in_register_now)
    LinearLayout registerLayout;

    @BindView(R.id.sign_in_btn)
    Button addProductBtn;

    @BindView(R.id.sign_in_username)
    EditText et_userName;

    @BindView(R.id.sign_in_password)
    EditText et_password;

    private SignInPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        addProductBtn.setOnClickListener(this);

        registerLayout.setOnClickListener(this);

        presenter = new SignInPresenter(this,this);
    }

    @Override
    public void onClick(View view) {


        if(view.getId() == R.id.sign_in_btn){

            //After Validation,
            presenter.requestLogin(et_userName.getText().toString().trim(),et_password.getText().toString().trim());

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
    }

    //SignInPresenter.requestLogin
    @Override
    public void onNetworkFailure() {
        //TODO (3): Handle UI for exception
    }

    //SignInPresenter.requestLogin
    @Override
    public void onUnDefinedException(String localizedError) {
        //TODO (4): Handle UI for exception
    }
}
