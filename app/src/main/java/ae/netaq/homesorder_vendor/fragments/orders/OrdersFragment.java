package ae.netaq.homesorder_vendor.fragments.orders;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.utils.NavigationController;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 11/21/2017.
 *
 * This fragment is used by the main activity in order to show the list of orders in their respective stages.
 * Once the orders menu item is selected from the navigation drawer the main_container in the main activity is replaced with this fragment.
 * On the application launch this fragment is automatically loaded in main_container.
 * This fragment features four tabs (New, Processing, Ready, Dispatched).
 */

public class OrdersFragment extends Fragment{

    @BindView(R.id.orders_tab_layout)
    TabLayout tabs;

    @BindView(R.id.orders_view_pager)
    ViewPager pager;

    private View view;

    public OrdersFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.orders_fragment, container, false);
        ButterKnife.bind(this, view);
        initViews();
        return view;

    }

    private void initViews() {
        pager.setAdapter(NavigationController.getOrdersPagerAdapter(this.getContext(),getChildFragmentManager()));
        tabs.setupWithViewPager(pager);
    }
}
