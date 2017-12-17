package ae.netaq.homesorder_vendor.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.transitionseverywhere.TransitionManager;

import java.util.ArrayList;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.db.data_manager.OrderDataManager;
import ae.netaq.homesorder_vendor.models.Order;
import ae.netaq.homesorder_vendor.network.OrderBAL;
import ae.netaq.homesorder_vendor.utils.NavigationController;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 12/14/2017.
 */

public class SignInActivity extends AppCompatActivity implements Animation.AnimationListener, View.OnClickListener{

    @BindView(R.id.logo_image_view)
    ImageView logo;

    @BindView(R.id.sign_in_fields_layout)
    LinearLayout inputFieldsLayout;

    @BindView(R.id.transitions_container)
    LinearLayout transitionsContainer;

    @BindView(R.id.sign_in_register_now)
    LinearLayout registerLayout;

    @BindView(R.id.sign_in_btn)
    Button addProductBtn;

    private Animation fadeInAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_anim);

        fadeInAnimation.setAnimationListener(this);

        addProductBtn.setOnClickListener(this);

        registerLayout.setOnClickListener(this);

        logo.startAnimation(fadeInAnimation);

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if(animation == fadeInAnimation){
            TransitionManager.beginDelayedTransition(transitionsContainer);
            inputFieldsLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.sign_in_btn){
            OrderBAL.getAllOrders(this,new OrderBAL.OrderFetchListener() {
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
        }

    }
}
