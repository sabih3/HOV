package ae.netaq.homesorder_vendor.fragments.add_new_product.product_preview;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;

import ae.netaq.homesorder_vendor.AppController;
import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.SliderPagerAdapter;
import ae.netaq.homesorder_vendor.models.Product;
import ae.netaq.homesorder_vendor.utils.Common;
import ae.netaq.homesorder_vendor.utils.ProductDetailUtility;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Netaq on 11/29/2017.
 */

public class ProductPreviewFragment extends Fragment {

    @BindView(R.id.slider_pager)
    ViewPager sliderPager;

    @BindView(R.id.slider_indicator)
    CircleIndicator circleIndicator;

    private ProductPreviewView mCallback;

    private Picasso picasso;

    public ProductPreviewFragment() {
    }

    private View view;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (ProductPreviewView) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement ProductPreviewView");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.product_preview_fragment, container, false);

        ButterKnife.bind(this, view);

        picasso = AppController.get(getActivity()).getPicasso();

        Common.changeViewWithLocale(getContext(),view);



        return view;
    }

    public void setupProductImageSlider(ArrayList<Uri> imagesUri) {
        sliderPager.setAdapter(new SliderPagerAdapter(getActivity(), null,imagesUri, picasso));
        circleIndicator.setViewPager(sliderPager);

        ProductDetailUtility detailUtility = new ProductDetailUtility(getContext(),
                view);
        detailUtility.bindValues(Product.getInstance());
    }

    public void validate(){
        mCallback.onAddProductRequested();
    }

    @Override
    public void onDetach() {
        mCallback = null;
        super.onDetach();
    }

}