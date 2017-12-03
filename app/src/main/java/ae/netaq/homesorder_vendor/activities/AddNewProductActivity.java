package ae.netaq.homesorder_vendor.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.badoualy.stepperindicator.StepperIndicator;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.utils.NavigationController;
import ae.netaq.homesorder_vendor.utils.NonSwipeableViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 11/29/2017.
 */

public class AddNewProductActivity extends AppCompatActivity {

    @BindView(R.id.add_product_pager)
    NonSwipeableViewPager pager;

    @BindView(R.id.add_product_indicator)
    StepperIndicator indicator;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.add_product_bottom_btn)
    Button nextBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);
        ButterKnife.bind(this);

        toolbar.setTitle(R.string.add_product);
        setSupportActionBar(toolbar);

        initViews();
    }

    private void initViews() {
        pager.setAdapter(NavigationController.getAddNewProductPagerAdapter(getSupportFragmentManager()));
        indicator.setViewPager(pager);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(pager.getCurrentItem()+1, true);

                switch (pager.getCurrentItem()) {
                    case 1:
                        nextBtn.setText(getString(R.string.add_images));
                        break;
                    case 2:
                        nextBtn.setText(getString(R.string.preview));
                        break;
                    case 3:
                        nextBtn.setText(getString(R.string.add_product));
                        break;
                    default:
                        nextBtn.setText(getString(R.string.add_details));
                }
            }
        });

    }
}
