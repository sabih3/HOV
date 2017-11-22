package ae.netaq.homesorder_vendor.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.activities.MainActivity;
import ae.netaq.homesorder_vendor.adapters.FragmentViewPager;
import ae.netaq.homesorder_vendor.fragments.PagerFragment;
import ae.netaq.homesorder_vendor.fragments.orders.dispatched_tab.DispatchedOrdersFragment;
import ae.netaq.homesorder_vendor.fragments.orders.new_tab.NewOrdersFragment;
import ae.netaq.homesorder_vendor.fragments.orders.processing_tab.ProcessingOrdersFragment;
import ae.netaq.homesorder_vendor.fragments.orders.ready_tab.ReadyOrdersFragment;

/**
 * Created by Netaq on 11/21/2017.
 */

public class NavigationController {

    public static PagerAdapter getOrdersPagerAdapter(Context context, FragmentManager supportFragmentManager) {

        FragmentViewPager viewPagerAdapter = null;
        if (context instanceof MainActivity) {

            ArrayList<PagerFragment> fragmentList = new ArrayList<>();

            Fragment newOrdersFragment = new NewOrdersFragment();
            Fragment processingOrdersFragment = new ProcessingOrdersFragment();
            Fragment readyOrdersFragment = new ReadyOrdersFragment();
            Fragment dispatchedOrdersFragment = new DispatchedOrdersFragment();

            PagerFragment newOrdersPagerFragment = new PagerFragment(newOrdersFragment, context.getString(R.string.new_tab_title));
            PagerFragment processingOrdersPagerFragment = new PagerFragment(processingOrdersFragment, context.getString(R.string.processing_tab_title));
            PagerFragment readyOrdersPagerFragment = new PagerFragment(readyOrdersFragment, context.getString(R.string.ready_tab_title));
            PagerFragment dispatchedOrdersPagerFragment = new PagerFragment(dispatchedOrdersFragment, context.getString(R.string.dispatched_tab_title));

            fragmentList.add(newOrdersPagerFragment);
            fragmentList.add(processingOrdersPagerFragment);
            fragmentList.add(readyOrdersPagerFragment);
            fragmentList.add(dispatchedOrdersPagerFragment);

            viewPagerAdapter = new FragmentViewPager(supportFragmentManager, fragmentList);

        }
        return viewPagerAdapter;
    }


}
