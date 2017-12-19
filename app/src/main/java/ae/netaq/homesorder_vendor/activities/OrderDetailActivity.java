package ae.netaq.homesorder_vendor.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.order_detail_products_listing.OrderDetailProductItemRecyclerAdapter;
import ae.netaq.homesorder_vendor.utils.DevicePreferences;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Deena on 29/11/2017.
 */

public class OrderDetailActivity extends AppCompatActivity {

    @BindView(R.id.order_detail_product_listing_recycler)
    RecyclerView productsRecycler;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        ButterKnife.bind(this);

        setToolBar();

        initViews();
    }

    private void setToolBar() {
        toolbar.setTitle(R.string.order_detail);
        setSupportActionBar(toolbar);

        if(DevicePreferences.isLocaleSetToArabic()){
            toolbar.setNavigationIcon(R.drawable.ic_prev_ar);
        }else{
            toolbar.setNavigationIcon(R.drawable.ic_prev);
        }
    }

    private void initViews() {

        OrderDetailProductItemRecyclerAdapter mAdapter = new OrderDetailProductItemRecyclerAdapter(this);
        productsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        productsRecycler.setAdapter(mAdapter);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
