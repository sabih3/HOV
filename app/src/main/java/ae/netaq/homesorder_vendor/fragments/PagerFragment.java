package ae.netaq.homesorder_vendor.fragments;

import android.support.v4.app.Fragment;

/**
 * Created by Netaq on 11/22/2017.
 */

public class PagerFragment {

    private Fragment mFragment;
    private String fragmentTitle;

    public PagerFragment(Fragment mFragment, String fragmentTitle) {
        this.mFragment = mFragment;
        this.fragmentTitle = fragmentTitle;
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public String getFragmentTitle() {
        return fragmentTitle;
    }

}

