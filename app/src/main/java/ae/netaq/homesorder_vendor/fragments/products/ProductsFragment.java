package ae.netaq.homesorder_vendor.fragments.products;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.utils.Common;
import ae.netaq.homesorder_vendor.utils.NavigationController;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 11/21/2017.
 *
 * This fragment is used by the main activity in order to show the list of products and promotions.
 * Once the products menu item is selected from the navigation drawer the main_container in the main activity is replaced with this fragment.
 * This fragment features two tabs (Products and Promotions)
 */

public class ProductsFragment extends Fragment {

    @BindView(R.id.products_tab_layout)
    TabLayout tabs;

    @BindView(R.id.products_view_pager)
    ViewPager pager;

    private View view;

    public ProductsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.products_fragment, container, false);
        ButterKnife.bind(this, view);
        initViews();
        Common.changeViewWithLocale(getContext(),pager);
        return view;

    }

    private void initViews() {
        pager.setAdapter(NavigationController.getProductsPagerAdapter(this.getContext(),getChildFragmentManager()));
        tabs.setupWithViewPager(pager);
    }
}
