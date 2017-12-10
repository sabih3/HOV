package ae.netaq.homesorder_vendor.fragments.add_new_product.add_product_information;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
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
import com.mobsandgeeks.saripaar.annotation.Digits;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import java.util.List;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.fragments.add_new_product.add_product_images.AddProductImagesView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 11/29/2017.
 */

public class AddProductInformationFragment extends Fragment implements Validator.ValidationListener{

    @NotEmpty(messageResId  = R.string.product_name_required_error)
    @Order(1)
    @BindView(R.id.add_product_information_product_name)
    EditText productName;

    @NotEmpty(sequence = 1, messageResId  = R.string.product_price_required_error)
    @Pattern(sequence = 2, regex = "[0-9]+(\\.[0-9][0-9]?)?", messageResId = R.string.valid_price_error)
    @Order(2)
    @BindView(R.id.add_product_information_product_price)
    EditText productPrice;

    @NotEmpty(messageResId  = R.string.product_description_required_error)
    @Order(3)
    @BindView(R.id.add_product_information_product_description)
    EditText productDescription;

    @BindView(R.id.add_product_information_product_name_layout)
    TextInputLayout productNameLayout;

    @BindView(R.id.add_product_information_product_price_layout)
    TextInputLayout productPriceLayout;

    @BindView(R.id.add_product_information_product_description_layout)
    TextInputLayout productDescriptionLayout;

    private AddProductInformationView mCallback;

    private Validator validator;

    public AddProductInformationFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (AddProductInformationView) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement AddProductInformationView");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_information_fragment, container, false);
        ButterKnife.bind(this, view);

        productDescription.setImeOptions(EditorInfo.IME_ACTION_DONE);
        productDescription.setRawInputType(InputType.TYPE_CLASS_TEXT);

        validator = new Validator(this);
        validator.setValidationListener(this);
        validator.setValidationMode(Validator.Mode.BURST);

        return view;

    }

    public void validate(){
        removeErrors();
        validator.validate();
    }

    private void removeErrors() {
        productNameLayout.setError(null);
        productPriceLayout.setError(null);
        productDescriptionLayout.setError(null);
    }

    @Override
    public void onValidationSucceeded() {
        mCallback.onProductInformationAdded(productName.getText().toString(),Float.parseFloat(productPrice.getText().toString()),productDescription.getText().toString());
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
                productPriceLayout.setError(errors.get(i).getCollatedErrorMessage(getActivity()));
            }
        }
    }
}