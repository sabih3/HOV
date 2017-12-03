package ae.netaq.homesorder_vendor.fragments.add_new_product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ae.netaq.homesorder_vendor.R;

/**
 * Created by Netaq on 11/29/2017.
 */

public class ProductPreviewFragment extends Fragment {

    private View view;

    public ProductPreviewFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.featured_fragment, container, false);
        return view;

    }
}