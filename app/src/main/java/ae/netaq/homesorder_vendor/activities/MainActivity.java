package ae.netaq.homesorder_vendor.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import ae.netaq.homesorder_vendor.AppController;
import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.db.data_manager.UserDataManager;
import ae.netaq.homesorder_vendor.event_bus.LanguageChangeEvent;
import ae.netaq.homesorder_vendor.fragments.featured.FeaturedFragment;
import ae.netaq.homesorder_vendor.fragments.orders.OrdersFragment;
import ae.netaq.homesorder_vendor.fragments.products.ProductsFragment;
import ae.netaq.homesorder_vendor.models.User;
import ae.netaq.homesorder_vendor.utils.DevicePreferences;
import ae.netaq.homesorder_vendor.utils.NavigationController;
import ae.netaq.homesorder_vendor.utils.UIUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements
             NavigationView.OnNavigationItemSelectedListener,
             View.OnClickListener{

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.sign_out_btn)
    Button signOutBtn;

    @BindView(R.id.add_product_fab)
    FloatingActionButton addProductFab;

    private CircleImageView profilePhoto;

    private ImageView settingsBtn;

    private RelativeLayout updateProfileBtn;

    private int navItemIndex = -1;

    public static boolean configChanges = false;

    private Fragment fragment = null;

    private Class fragmentClass = null;

    private static boolean firstTimeLaunch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        AppController.get(this).getHomesOrderServices();

        EventBus.getDefault().register(this);

        firstTimeLaunch = true;

        signOutBtn.setOnClickListener(this);

        addProductFab.setOnClickListener(this);

        settingsBtn = navigationView.getHeaderView(0).findViewById(R.id.settings_icon);

        updateProfileBtn = navigationView.getHeaderView(0).findViewById(R.id.update_profile_layout);

        profilePhoto = navigationView.getHeaderView(0).findViewById(R.id.profile_image);

        settingsBtn.setOnClickListener(this);

        updateProfileBtn.setOnClickListener(this);

        //Setting up the toolbar.
        setUpToolBar();

        //configuring the Navigation Drawer.
        configureNavigationDrawer();

        setProfilePhoto();

        //By default when the home activity is loaded select the orders fragment to fill the container.
        if(firstTimeLaunch){
            selectDrawerItem(navigationView.getMenu().getItem(0));
            initFragment();
            firstTimeLaunch = false;
        }
    }

    private void setProfilePhoto() {
        CircleImageView profilePhoto = navigationView.getHeaderView(0).
                                       findViewById(R.id.profile_image);

        if(DevicePreferences.getInstance().getUserInfo()!=null){
            try {
                Picasso.with(this).load(DevicePreferences.getInstance().getUserInfo().getLogoURL())
                        .placeholder(R.drawable.ic_person_white_24px).into(profilePhoto);
            }catch (Exception exc){

            }

        }

        TextView tvPersonName = navigationView.getHeaderView(0).findViewById(R.id.drawer_tv_person_name);
        tvPersonName.setText(DevicePreferences.getInstance().getUserInfo().getPersonName());
    }

    private void setUpToolBar() {
        toolbar.setTitle(R.string.orders);
        setSupportActionBar(toolbar);
    }

    private void configureNavigationDrawer() {

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer,
                toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we don't want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
                initFragment();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we don't want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.addDrawerListener(actionBarDrawerToggle);

        //Calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    private void initFragment() {
        if(fragmentClass != null){
            // Try to initialize the selected fragment
            try {
                fragment = (Fragment) fragmentClass.newInstance();

                // Insert the fragment by replacing any existing fragment
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit();

                //Show the add product floating button when products is selected from navigation menu.
                if(navItemIndex == 1){
                    addProductFab.setVisibility(View.VISIBLE);
                }else{
                    addProductFab.setVisibility(View.GONE);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        selectDrawerItem(item);
        return true;
    }

    public void selectDrawerItem(MenuItem item){

        int position = 0;
            //Check to see which item was being clicked and perform appropriate action
            switch (item.getItemId()) {
                case R.id.nav_orders_item:
                    fragmentClass = OrdersFragment.class;
                    position = 0;
                    break;
                case R.id.nav_products_item:
                    fragmentClass = ProductsFragment.class;
                    position = 1;
                    break;
                case R.id.nav_featured_item:
                    fragmentClass = FeaturedFragment.class;
                    position = 2;
                    break;
                case R.id.nav_profile_item:
                    NavigationController.startActivityProfile(MainActivity.this);
                    position = 3;
                    break;

                case R.id.covergae_setup:
                    NavigationController.showCountrySelectActivity(MainActivity.this);
                    position = 4;
                default:
                    fragmentClass = OrdersFragment.class;
            }
            //Check that if the profile item is selected or not, if yes then do nothing.
            if(position!=3){
                if(navItemIndex == position){
                    fragmentClass = null;
                    drawer.closeDrawers();
                }
                navItemIndex = position;
                // Highlight the selected item has been done by NavigationView
                item.setChecked(true);
                // Set action bar title
                toolbar.setTitle(item.getTitle());
                // Close the navigation drawer
                drawer.closeDrawers();
            }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.sign_out_btn){
            drawer.closeDrawers();

            UIUtils.showMessageDialog(this,
                    getString(R.string.confirmation_log_out),
                    getString(R.string.no),
                    getString(R.string.yes),
                    new UIUtils.DialogButtonListener() {
                @Override
                public void onPositiveButtonClicked() {
                    //dialog gets dismissed
                    NavigationController.showCountrySelectActivity(MainActivity.this);
                }

                @Override
                public void onNegativeButtonClicked() {
                    //log out
                    UserDataManager.clearUserData();
                    MainActivity.this.finish();
                    NavigationController.startActivitySignIn(MainActivity.this);

                }
            });

        }else if(v.getId() == R.id.add_product_fab){
            NavigationController.startActivityAddNewProduct(MainActivity.this);
        }else if(v.getId() == R.id.settings_icon){
            NavigationController.startActivitySettings(MainActivity.this);
        }else if(v.getId() == R.id.update_profile_layout){
            NavigationController.showUpdateProfileActivity(MainActivity.this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(configChanges){
            recreate();
            configChanges = false;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLanguageChanged(LanguageChangeEvent languageChangeEvent){
        configChanges = true;
    }
}
