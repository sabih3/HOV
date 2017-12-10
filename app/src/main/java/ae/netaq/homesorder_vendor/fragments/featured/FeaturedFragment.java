package ae.netaq.homesorder_vendor.fragments.featured;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.featured.FeaturedOrdersRecyclerAdapter;
import ae.netaq.homesorder_vendor.adapters.featured.viewholder.FeaturedOrdersRecyclerViewHolder;

/**
 * Created by Netaq on 11/21/2017.
 *
 * This fragment is used by the main activity in order to show the list of featured products.
 * Once the featured menu item is selected from the navigation drawer the main_container in the main activity is replaced with this fragment.
 */

public class FeaturedFragment extends Fragment {

    private View view;
    RecyclerView recyclerViewFeatured;
    public FeaturedFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.featured_fragment, container, false);
        initViews();
         return view;

    }

    private void initViews() {
        recyclerViewFeatured =view.findViewById(R.id.rv_featured_fragment);
        recyclerViewFeatured.setLayoutManager(new GridLayoutManager(getActivity(),2, LinearLayoutManager.VERTICAL, false));
        recyclerViewFeatured.setAdapter(new FeaturedOrdersRecyclerAdapter());

    }

}
