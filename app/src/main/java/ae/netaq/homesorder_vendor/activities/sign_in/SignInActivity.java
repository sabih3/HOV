package ae.netaq.homesorder_vendor.activities.sign_in;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.db.data_manager.OrderDataManager;
import ae.netaq.homesorder_vendor.models.Order;
import ae.netaq.homesorder_vendor.network.services.OrderService;
import ae.netaq.homesorder_vendor.utils.NavigationController;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 12/17/2017.
 */


public class SignInActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.sign_in_register_now)
    LinearLayout registerLayout;

    @BindView(R.id.sign_in_btn)
    Button addProductBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        addProductBtn.setOnClickListener(this);

        registerLayout.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.sign_in_btn){
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
        }else if(view.getId() == R.id.sign_in_register_now){
            NavigationController.startActivityRegister(SignInActivity.this);
            finish();
        }

    }
}
