package ae.netaq.homesorder_vendor.fragments.add_new_product.add_product_information;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.mobsandgeeks.saripaar.annotation.Pattern;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.List;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.utils.Common;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 11/29/2017.
 */

public class AddProductInformationFragment extends Fragment implements Validator.ValidationListener{

    private static final int DEFAULT_VALUE_ORDER_LIMIT = 1;
    private static final int DEFAULT_VALUE_HANDLING_TIME = 1;
    @NotEmpty(messageResId  = R.string.product_name_required_error)
    @Order(1)
    @BindView(R.id.add_product_information_product_name)
    EditText productName;

    @BindView(R.id.add_product_information_product_name_arabic)
    EditText productNameAr;

    @NotEmpty(sequence = 1, messageResId  = R.string.product_price_required_error)
    @Pattern(sequence = 2, regex = "[0-9]+(\\.[0-9][0-9]?)?", messageResId = R.string.valid_price_error)
    @Order(2)
    @BindView(R.id.add_product_information_product_price)
    EditText productPrice;

    @NotEmpty(messageResId  = R.string.product_description_required_error)
    @Order(3)
    @BindView(R.id.add_product_information_product_description)
    EditText productDescription;

    @BindView(R.id.add_product_information_product_description_arabic)
    EditText productDescriptionAr;

    @BindView(R.id.add_product_information_product_size)
    EditText productSize;

    @BindView(R.id.add_product_information_product_color)
    EditText productColor;

    @BindView(R.id.add_product_information_product_name_layout)
    TextInputLayout productNameLayout;

    @BindView(R.id.add_product_information_product_price_layout)
    TextInputLayout productPriceLayout;

    @BindView(R.id.add_product_information_product_description_layout)
    TextInputLayout productDescriptionLayout;

    @BindView(R.id.add_product_information_product_size_layout)
    TextInputLayout productSizeLayout;

    @BindView(R.id.add_product_information_product_color_layout)
    TextInputLayout productColorLayout;

    @BindView(R.id.picker_order_limit)
    NumberPicker picker_order_limit;

    @BindView(R.id.picker_handling_time)
    NumberPicker picker_handling_time;

    private AddProductInformationView mCallback;

    private Validator validator;

    private int orderLimit = DEFAULT_VALUE_ORDER_LIMIT;
    private int handlingTime = DEFAULT_VALUE_HANDLING_TIME ;

    public AddProductInformationFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (AddProductInformationView) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    " must implement AddProductInformationView");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_information_fragment, container, false);
        ButterKnife.bind(this, view);

        initViews();

        Common.changeViewWithLocale(getContext(),view);

        validator = new Validator(this);
        validator.setValidationListener(this);
        validator.setValidationMode(Validator.Mode.BURST);

        return view;

    }

    private void initViews() {

        productDescription.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        productDescription.setRawInputType(InputType.TYPE_CLASS_TEXT);

        productDescriptionAr.setImeOptions(EditorInfo.IME_ACTION_DONE);
        productDescriptionAr.setRawInputType(InputType.TYPE_CLASS_TEXT);

        picker_order_limit.setOnValueChangedListener(new OrderLimitListener());
        picker_handling_time.setOnValueChangedListener(new HandlingTimeListener());

        //setFoodDataInForm();
        //setFashionDataInForm();

    }

    private void setFoodDataInForm() {
        productName.setText("Baqlawa8");
        productNameAr.setText("بقلاوا");
        productPrice.setText("30.75");
        productSize.setText("250 Gms,500 Gms");
        productColor.setText("");
        picker_order_limit.setValue(5);
        picker_handling_time.setValue(1);
        productDescription.setText("This is a description of the food product");
        productDescriptionAr.setText("سشس  شسشس شسحخهثضخ مثتش خثضخحث ثمناضا");
    }
    private void setFashionDataInForm() {
        productName.setText("Kandura");
        productNameAr.setText("كندورا");
        productPrice.setText("500");
        productSize.setText("S,M,L,XL");
        productColor.setText("White,Brown,Navy blue");
        picker_order_limit.setValue(2);
        picker_handling_time.setValue(5);
        productDescription.setText("This is a description of the fashion product");
        productDescriptionAr.setText("سشس  شسشس شسحخهثضخ مثتش خثضخحث ثمناضا");
    }

    public void validate(){
        removeErrors();
        validator.validate();
    }

    public void setSelectedMainCategory(int mainCategory){if(mainCategory == 0){
            productColorLayout.setVisibility(View.GONE);
        }else{
            productColorLayout.setVisibility(View.VISIBLE);
            productColor.clearFocus();
        }
    }

    private void removeErrors() {
        productNameLayout.setError(null);
        productPriceLayout.setError(null);
        productDescriptionLayout.setError(null);
    }

    @Override
    public void onValidationSucceeded() {
        String productNameAR = productNameAr.getText().toString();
        String productNameEN = productName.getText().toString();
        Double price = Double.parseDouble(productPrice.getText().toString());
        String size = productSize.getText().toString();
        String color = productColor.getText().toString();

        String descEN = productDescription.getText().toString();
        String descAR = productDescriptionAr.getText().toString();

        //Will be Called in AddNewProductActivity.onProductInformationAdded
        mCallback.onProductInformationAdded(productNameEN,
                                            productNameAR,
                                            price,
                                            descEN,
                                            descAR,
                                            size,
                                            color,
                                            orderLimit,
                                            handlingTime);
    }

    @Override
    public void onDetach() {
        mCallback = null;
        super.onDetach();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for(int i = 0 ; i<errors.size() ; i++) {
            if (errors.get(i).getView().getId() == R.id.add_product_information_product_name) {
                productName.requestFocus();
                productNameLayout.setError(errors.get(i).getCollatedErrorMessage(getActivity()));
            } else if (errors.get(i).getView().getId() == R.id.add_product_information_product_description) {
                productDescription.requestFocus();
                productDescriptionLayout.setError(errors.get(i).getCollatedErrorMessage(getActivity()));
            } else if (errors.get(i).getView().getId() == R.id.add_product_information_product_price) {
                productPrice.requestFocus();
                productPriceLayout.setError(errors.get(i).getFailedRules().get(0).getMessage(getActivity()));
            }
        }
    }

    private class OrderLimitListener implements NumberPicker.OnValueChangeListener {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            AddProductInformationFragment.this.orderLimit = newVal;
        }
    }

    private class HandlingTimeListener implements NumberPicker.OnValueChangeListener {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            AddProductInformationFragment.this.handlingTime = newVal;
        }
    }
}