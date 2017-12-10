package ae.netaq.homesorder_vendor.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ae.netaq.homesorder_vendor.AppController;
import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.SliderPagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Deena on 29/11/2017.
 */

public class ProductDetailActivity extends AppCompatActivity{

    @BindView(R.id.slider_pager)
    ViewPager sliderPager;

    @BindView(R.id.slider_indicator)
    CircleIndicator circleIndicator;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Picasso picasso;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);

        toolbar.setTitle(R.string.product_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        picasso = AppController.get(this).getPicasso();

        setupProductImageSlider();
    }

    private void setupProductImageSlider() {
        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.fashion);
        images.add(R.drawable.food);

        sliderPager.setAdapter(new SliderPagerAdapter(ProductDetailActivity.this, images,null, picasso));
        circleIndicator.setViewPager(sliderPager);
    }
}
