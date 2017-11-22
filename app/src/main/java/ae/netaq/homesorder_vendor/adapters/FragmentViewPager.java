package ae.netaq.homesorder_vendor.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;

import ae.netaq.homesorder_vendor.fragments.PagerFragment;

/**
 * Created by Netaq on 11/22/2017.
 */

public class FragmentViewPager extends FragmentPagerAdapter {

    private final ArrayList<PagerFragment> mDataset;

    public FragmentViewPager(FragmentManager fm, ArrayList<PagerFragment> fragmentList) {
        super(fm);
        this.mDataset = fragmentList;

    }

    @Override
    public Fragment getItem(int position) {
        return mDataset.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return mDataset.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return mDataset.get(position).getFragmentTitle();

    }
}