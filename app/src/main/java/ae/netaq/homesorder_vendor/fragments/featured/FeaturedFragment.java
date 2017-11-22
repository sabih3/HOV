package ae.netaq.homesorder_vendor.fragments.featured;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ae.netaq.homesorder_vendor.R;

/**
 * Created by Netaq on 11/21/2017.
 *
 * This fragment is used by the main activity in order to show the list of featured products.
 * Once the featured menu item is selected from the navigation drawer the main_container in the main activity is replaced with this fragment.
 */

public class FeaturedFragment extends Fragment {

    private View view;

    public FeaturedFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.featured_fragments, container, false);
        return view;

    }
}
