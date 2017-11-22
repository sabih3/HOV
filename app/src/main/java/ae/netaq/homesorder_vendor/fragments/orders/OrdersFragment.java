package ae.netaq.homesorder_vendor.fragments.orders;

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
 * This fragment is used by the main activity in order to show the list of orders.
 * Once the orders menu item is selected from the navigation drawer the main_container in the main activity is replaced with this fragment.
 * On the application launch this fragment is automatically loaded in main_container.
 */

public class OrdersFragment extends Fragment{

    View view;

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
        return view;

    }
}
