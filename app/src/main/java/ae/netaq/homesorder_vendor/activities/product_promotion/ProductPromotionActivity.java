package ae.netaq.homesorder_vendor.activities.product_promotion;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.db.data_manager.tables.ProductTable;
import ae.netaq.homesorder_vendor.utils.DevicePreferences;
import ae.netaq.homesorder_vendor.utils.NavigationController;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductPromotionActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.start_date_text_view)
    TextView startDateTv;

    @BindView(R.id.end_date_text_view)
    TextView endDateTv;

    @BindView(R.id.original_price_edit_text)
    EditText originalPriceEt;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    Calendar calendar = Calendar.getInstance();

    private ProductTable product;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_promotion);
        ButterKnife.bind(this);

        setToolbar();

        product = (ProductTable) getIntent().getSerializableExtra(NavigationController.KEY_PRODUCT);

        startDateTv.setOnClickListener(this);

        endDateTv.setOnClickListener(this);

        initViews();

    }

    private void initViews() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        startDateTv.setText(simpleDateFormat.format(calendar.getTime()));

        originalPriceEt.setText(product.getProductPrice().toString());
    }

    private void setToolbar() {
        toolbar.setTitle(getString(R.string.promotion));
        setSupportActionBar(toolbar);

        if(DevicePreferences.isLocaleSetToArabic()){
            toolbar.setNavigationIcon(R.drawable.ic_prev_ar);
        }else{
            toolbar.setNavigationIcon(R.drawable.ic_prev);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    private void showFromDatePicker() {
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        String date = dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
                        startDateTv.setText(date);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setTitle("From");
        dpd.setMinDate(calendar);
        dpd.vibrate(false);
        dpd.setVersion(DatePickerDialog.Version.VERSION_1);
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    private void showToDatePicker() {
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        String date = dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
                        endDateTv.setText(date);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setTitle("To");
        dpd.setMinDate(calendar);
        dpd.vibrate(false);
        dpd.setVersion(DatePickerDialog.Version.VERSION_1);
        dpd.show(getFragmentManager(), "Datepickerdialog");

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.start_date_text_view){
            showFromDatePicker();
        }else if(view.getId() == R.id.end_date_text_view){
            showToDatePicker();
        }
    }
}
