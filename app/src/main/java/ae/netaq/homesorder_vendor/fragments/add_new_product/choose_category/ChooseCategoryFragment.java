package ae.netaq.homesorder_vendor.fragments.add_new_product.choose_category;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.models.ProductCategories;
import ae.netaq.homesorder_vendor.models.ProductGroups;
import ae.netaq.homesorder_vendor.utils.ProductCategoriesManager;
import ae.netaq.homesorder_vendor.utils.Common;
import ae.netaq.homesorder_vendor.utils.ProductGroupsManager;
import ae.netaq.homesorder_vendor.utils.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.refactor.library.SmoothCheckBox;

/**
 * Created by Netaq on 11/29/2017.
 */

public class ChooseCategoryFragment extends Fragment implements View.OnClickListener{

    @BindView(R.id.food_layout)
    RelativeLayout foodLayout;

    @BindView(R.id.fashion_layout)
    RelativeLayout fashionLayout;

    @BindView(R.id.food_checkbox)
    SmoothCheckBox foodCheckBox;

    @BindView(R.id.fashion_checkbox)
    SmoothCheckBox fashionCheckbox;

    @BindView(R.id.food_image_view)
    ImageView foodImageView;

    @BindView(R.id.fashion_image_view)
    ImageView fashionImageView;

    @BindView(R.id.categories_spinner_food)
    Spinner categoriesSpinnerFood;

    @BindView(R.id.categories_spinner_fashion)
    Spinner categoriesSpinnerFashion;

    @BindView(R.id.group_spinner_fashion)
    Spinner groupSpinnerFashion;

    @BindView(R.id.fashion_spinner_layout)
    LinearLayout fashionSpinnerLayout;

    @BindView(R.id.food_spinner_layout)
    LinearLayout foodSpinnerLayout;

    private ChooseCategoryView mCallback;

    private ProductCategories.Category category;

    private ProductGroups.Group group;

    public ChooseCategoryFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (ChooseCategoryView) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement ChooseCategoryView");
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.choose_category_fragment, container, false);
        ButterKnife.bind(this, view);

        foodLayout.setOnClickListener(this);
        fashionLayout.setOnClickListener(this);

        Common.changeViewWithLocale(getContext(),view);

        initViews();

        setUpSpinners();

        return view;

    }

    private void setUpSpinners() {

        categoriesSpinnerFashion.setEnabled(false);

        categoriesSpinnerFood.setAdapter(ProductCategoriesManager.getProductCategoriesAdapter(getActivity(),
                ProductCategoriesManager.FILENAME_FOOD_CATEGORIES));

        groupSpinnerFashion.setAdapter(ProductGroupsManager.getProductGroupsAdapter(getActivity(),
                ProductGroupsManager.FILENAME_PRODUCT_GROUPS));

        categoriesSpinnerFood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0){
                    category = ProductCategoriesManager.getProductCategories(getContext(),
                            ProductCategoriesManager.FILENAME_FOOD_CATEGORIES).getCategories().get(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        categoriesSpinnerFashion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0){
                    if(groupSpinnerFashion.getSelectedItemPosition() == 1){
                        category = ProductCategoriesManager.getProductCategories(getContext(),
                                ProductCategoriesManager.FILENAME_FASHION_CATEGORIES_MEN).getCategories().get(i);
                    }else if(groupSpinnerFashion.getSelectedItemPosition() == 2){
                        category = ProductCategoriesManager.getProductCategories(getContext(),
                                ProductCategoriesManager.FILENAME_FASHION_CATEGORIES_WOMEN).getCategories().get(i);
                    }else if(groupSpinnerFashion.getSelectedItemPosition() == 3){
                        category = ProductCategoriesManager.getProductCategories(getContext(),
                                ProductCategoriesManager.FILENAME_FASHION_CATEGORIES_KIDS).getCategories().get(i);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        groupSpinnerFashion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0){
                    categoriesSpinnerFashion.setEnabled(true);
                    if(i == 1){
                        categoriesSpinnerFashion.setAdapter(ProductCategoriesManager.
                                getProductCategoriesAdapter(getActivity(),
                                        ProductCategoriesManager.FILENAME_FASHION_CATEGORIES_MEN));
                        group = ProductGroupsManager.getProductGroups(getActivity(),
                                ProductGroupsManager.FILENAME_PRODUCT_GROUPS).getGroups().get(1);
                    }else if(i == 2){
                        categoriesSpinnerFashion.setAdapter(ProductCategoriesManager.getProductCategoriesAdapter(getActivity(),
                                ProductCategoriesManager.FILENAME_FASHION_CATEGORIES_WOMEN));
                        group = ProductGroupsManager.getProductGroups(getActivity(),
                                ProductGroupsManager.FILENAME_PRODUCT_GROUPS).getGroups().get(2);
                    }else if(i == 3){
                        categoriesSpinnerFashion.setAdapter(ProductCategoriesManager.getProductCategoriesAdapter(getActivity(),
                                ProductCategoriesManager.FILENAME_FASHION_CATEGORIES_KIDS));
                        group = ProductGroupsManager.getProductGroups(getActivity(),
                                ProductGroupsManager.FILENAME_PRODUCT_GROUPS).getGroups().get(3);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void initViews() {

        foodCheckBox.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox smoothCheckBox, boolean b) {
                if(b){
                    foodImageView.setColorFilter(Color.GRAY, PorterDuff.Mode.LIGHTEN);
                    fashionCheckbox.setChecked(false,false);
                    fashionSpinnerLayout.setVisibility(View.GONE);
                    foodSpinnerLayout.setVisibility(View.VISIBLE);
                }else{
                    foodImageView.setColorFilter(Color.TRANSPARENT);
                    foodCheckBox.clearAnimation();
                }
            }
        });

        fashionCheckbox.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox smoothCheckBox, boolean b) {
                if(b){
                    fashionImageView.setColorFilter(Color.GRAY, PorterDuff.Mode.LIGHTEN);
                    foodCheckBox.setChecked(false,false);
                    fashionSpinnerLayout.setVisibility(View.VISIBLE);
                    foodSpinnerLayout.setVisibility(View.GONE);
                }else{
                    fashionImageView.setColorFilter(Color.TRANSPARENT);
                }
            }
        });

    }

    public void validate(){
        if(foodCheckBox.isChecked()){
            if(categoriesSpinnerFood.getSelectedItemPosition() != 0){
                mCallback.onCategoryChosen(0,category,null);
            }else{
                Utils.showToast(getActivity(), getString(R.string.choose_subcategory_error));
            }
        }else if(fashionCheckbox.isChecked()){
            if(categoriesSpinnerFashion.getSelectedItemPosition() != 0 &&
                    groupSpinnerFashion.getSelectedItemPosition()!= 0){
                mCallback.onCategoryChosen(1,category,group);
            }else{
                Utils.showToast(getActivity(), getString(R.string.choose_group_category_error));
            }
        }else{
            Utils.showToast(getActivity(), getString(R.string.choose_main_category_error));
        }
    }

    @Override
    public void onDetach() {
        mCallback = null;
        super.onDetach();
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.food_layout){
            if(foodCheckBox.isChecked()){
                foodCheckBox.setChecked(false, true);
            }else{
                foodCheckBox.setChecked(true, true);
            }
        }else if(v.getId() == R.id.fashion_layout){
            if(fashionCheckbox.isChecked()){
                fashionCheckbox.setChecked(false, true);
            }else{
                fashionCheckbox.setChecked(true, true);
            }
        }

    }
}