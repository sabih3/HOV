package ae.netaq.homesorder_vendor.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import ae.netaq.homesorder_vendor.AppController;
import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.fragments.featured.FeaturedFragment;
import ae.netaq.homesorder_vendor.fragments.orders.OrdersFragment;
import ae.netaq.homesorder_vendor.fragments.products.ProductsFragment;
import ae.netaq.homesorder_vendor.utils.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{

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

    private int navItemIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        AppController.get(this).getHomesOrderServices();

        signOutBtn.setOnClickListener(this);

        //Setting up the toolbar.
        setUpToolBar();

        //configuring the Navigation Drawer.
        configureNavigationDrawer();

        //By default when the home activity is loaded select the orders fragment to fill the container.
        selectDrawerItem(navigationView.getMenu().getItem(0));
    }

    private void setUpToolBar() {
        toolbar.setTitle(R.string.orders);
        toolbar.setCollapsible(true);
        setSupportActionBar(toolbar);
    }

    private void configureNavigationDrawer() {

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we don't want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        selectDrawerItem(item);
        return true;
    }

    public void selectDrawerItem(MenuItem item){

        Fragment fragment = null;
        Class fragmentClass;
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
            default:
                fragmentClass = OrdersFragment.class;
                position = 0;
        }

        if(navItemIndex == position){
            drawer.closeDrawers();
        }else {
            // Try to initialize the selected fragment
            try {
                fragment = (Fragment) fragmentClass.newInstance();

                // Insert the fragment by replacing any existing fragment
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit();

                navItemIndex = position;

                if(navItemIndex == 1){
                    addProductFab.setVisibility(View.VISIBLE);
                }else{
                    addProductFab.setVisibility(View.GONE);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Highlight the selected item has been done by NavigationView
        item.setChecked(true);
        // Set action bar title
        toolbar.setTitle(item.getTitle());
        // Close the navigation drawer
        drawer.closeDrawers();

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.sign_out_btn){
            drawer.closeDrawers();
        }
    }
}
