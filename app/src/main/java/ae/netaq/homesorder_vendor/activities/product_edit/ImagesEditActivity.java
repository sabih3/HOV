package ae.netaq.homesorder_vendor.activities.product_edit;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.InputStream;
import java.util.List;

import ae.netaq.homesorder_vendor.AppController;
import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.edit_product_images.EditImagesRecyclerAdapter;
import ae.netaq.homesorder_vendor.db.tables.ImageTable;
import ae.netaq.homesorder_vendor.db.tables.ProductTable;
import ae.netaq.homesorder_vendor.utils.DevicePreferences;
import ae.netaq.homesorder_vendor.utils.NavigationController;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ImagesEditActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.edit_photo_images_recycler)
    RecyclerView imagesRecycler;

    @BindView(R.id.edit_product_images_btn)
    Button btnUpdateImages;

    private ProductTable product;

    private List<ImageTable> imagesArray;

    private static final int SELECT_PICTURE = 100;

    private EditImagesRecyclerAdapter editImagesRecyclerAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_images);
        ButterKnife.bind(this);

        btnUpdateImages.setOnClickListener(this);

        product = (ProductTable) getIntent().getSerializableExtra(NavigationController.KEY_PRODUCT);

        imagesArray = product.getImagesArray();

        setToolbar();

        initViews();

        setupImagesRecycler(imagesArray);

    }

    private void setToolbar() {
        toolbar.setTitle(R.string.edit_product);
        setSupportActionBar(toolbar);

        if(DevicePreferences.isLocaleSetToArabic()){
            toolbar.setNavigationIcon(R.drawable.ic_prev_ar);
        }else{
            toolbar.setNavigationIcon(R.drawable.ic_prev);
        }
    }

    private void initViews() {
        imagesRecycler.setHasFixedSize(true);
        imagesRecycler.setLayoutManager(new GridLayoutManager(this, 3,
                LinearLayoutManager.VERTICAL, false));
    }

    private void setupImagesRecycler(List<ImageTable> imagesArray) {
        editImagesRecyclerAdapter = new EditImagesRecyclerAdapter(this,imagesArray, AppController.get(this).getPicasso());
        imagesRecycler.setAdapter(editImagesRecyclerAdapter);

    }

    // The following code is responsible of selecting multiple images from the storage.
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        InputStream inputStream = null;
        if (resultCode == RESULT_OK) {
            if (data != null) {
                ClipData clipData = data.getClipData();
                //For multiple images
                if (clipData != null) {
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        Uri uri = item.getUri();
                        ImageTable imageTable = new ImageTable();
                        imageTable.setProductID(product.getProductID());
                        imageTable.setImage(null);
                        imageTable.setImageURI(uri.toString());
                        imagesArray.add(imageTable);

                    }
                }

                else{
                    //For Single image
                    Uri uri = data.getData();
                    ImageTable imageTable = new ImageTable();
                    imageTable.setProductID(product.getProductID());
                    imageTable.setImage(null);
                    imageTable.setImageURI(uri.toString());
                    imagesArray.add(imageTable);


                }
                editImagesRecyclerAdapter.notifyDataSetChanged();
            }
        }
    }

    public void openImageChooser() {
        int permissionGrant = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if(permissionGrant == PackageManager.PERMISSION_GRANTED){
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);

            startActivityForResult(Intent.createChooser(intent, getString(R.string.select_pictures)), SELECT_PICTURE);
        }else{
            //ask permisssion
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    NavigationController.REQUEST_PERMISSION_STORAGE);
        }

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.edit_product_images_btn){
            //product.setImagesArray(imagesArray);
            //NavigationController.showProductEdit(ImagesEditActivity.this,product);
            Intent intent = new Intent();
            intent.putExtra(NavigationController.KEY_PRODUCT, product);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_images, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_images: {
                openImageChooser();
                break;
            }
        }
        return false;
    }

}
