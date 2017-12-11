package ae.netaq.homesorder_vendor.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.db.data_manager.OrderDataManager;
import ae.netaq.homesorder_vendor.models.Order;
import ae.netaq.homesorder_vendor.network.OrderBAL;
import ae.netaq.homesorder_vendor.utils.NavigationController;

public class SplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        OrderBAL.getAllOrders(this,new OrderBAL.OrderFetchListener() {
            @Override
            public void onOrdersFetched(ArrayList<Order> orders) {

                OrderDataManager.persistAllOrders(orders, new OrderDataManager.OrderPersistenceListener() {
                    @Override
                    public void onOrdersPersisted() {

                        NavigationController.showMainActivity(SplashScreen.this);

                    }
                });
            }

        });
    }
}
