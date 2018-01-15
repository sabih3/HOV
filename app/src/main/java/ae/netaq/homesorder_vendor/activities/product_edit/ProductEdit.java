package ae.netaq.homesorder_vendor.activities.product_edit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.widget.EditText;

import com.shawnlin.numberpicker.NumberPicker;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable;
import ae.netaq.homesorder_vendor.utils.NavigationController;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductEdit extends AppCompatActivity {

    @BindView(R.id.et_prod_title) EditText etProductTitle;
    @BindView(R.id.et_price) EditText etProductPrice;
    @BindView(R.id.et_size)EditText etProductSize;
    @BindView(R.id.et_color)EditText etColor;
    @BindView(R.id.et_desc_en)EditText editDescEN;
    @BindView(R.id.et_desc_ar)EditText editDescAR;

    @BindView(R.id.edit_spinner_group)AppCompatSpinner targetGroupSpinner;
    @BindView(R.id.edit_order_limit)NumberPicker orderLimitPicker;

    @BindView(R.id.edit_handling_time)NumberPicker handlingTimePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_edit);
        ButterKnife.bind(this);


        ProductTable product = (ProductTable) getIntent().getSerializableExtra(NavigationController.KEY_PRODUCT);

        etProductTitle.setText(product.getProductNameEN());

    }
}
