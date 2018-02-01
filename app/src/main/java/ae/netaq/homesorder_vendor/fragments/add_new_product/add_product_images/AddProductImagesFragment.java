package ae.netaq.homesorder_vendor.fragments.add_new_product.add_product_images;

import android.Manifest;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.InputStream;
import java.util.ArrayList;

import ae.netaq.homesorder_vendor.AppController;
import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.add_new_product_images.AddImagesRecyclerAdapter;
import ae.netaq.homesorder_vendor.utils.Common;
import ae.netaq.homesorder_vendor.event_bus.StoragePermissionGrantedEvent;
import ae.netaq.homesorder_vendor.utils.NavigationController;
import ae.netaq.homesorder_vendor.utils.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Netaq on 11/29/2017.
 */

public class AddProductImagesFragment extends Fragment{

    @BindView(R.id.add_photo_layout)
    LinearLayout addPhoto;

    @BindView(R.id.add_photo_images_recycler)
    RecyclerView addPhotoImagesRecycler;

    private static final int SELECT_PICTURE = 100;

    private AddProductImagesView mCallback;


    private ArrayList<Uri> imagesUri;
    private ArrayList<byte[]> bytesArray = new ArrayList<>();
    public AddProductImagesFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (AddProductImagesView) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement AddProductImagesView");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_images_fragment, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);

        imagesUri = new ArrayList<>();

        Common.changeViewWithLocale(getContext(),view);

        initViews();

        return view;

    }

    private void initViews() {
        addPhoto.setOnClickListener(new AddPhotoListener());
        addPhotoImagesRecycler.setHasFixedSize(true);
        addPhotoImagesRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 3,
                LinearLayoutManager.VERTICAL, false));
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
                        imagesUri.add(uri);

                    }
                }

                else{
                    //For Single image
                    Uri uri = data.getData();
                    imagesUri.add(uri);

                }

                initiateImagesRecycler(imagesUri);
            }
        }
    }

    private void initiateImagesRecycler(ArrayList<Uri> imagesUri) {
        AddImagesRecyclerAdapter addImagesRecyclerAdapter = new AddImagesRecyclerAdapter(getActivity(),
                                                            imagesUri);
        addPhotoImagesRecycler.setAdapter(addImagesRecyclerAdapter);
    }

    /* Choose an image from Gallery */
    public void openImageChooser() {
        int permissionGrant = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE);

        if(permissionGrant == PackageManager.PERMISSION_GRANTED){
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);

            startActivityForResult(Intent.createChooser(intent, getString(R.string.select_pictures)), SELECT_PICTURE);
        }else{
            //ask permisssion
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                              NavigationController.REQUEST_PERMISSION_STORAGE);
        }

    }

    //AddNewProductActivity.
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPermissionGranted(StoragePermissionGrantedEvent permissionGranted){
        openImageChooser();
    }
    public void validate(){
        if(imagesUri.size() > 0){
            mCallback.onAddImagesCompleted(imagesUri,bytesArray);
        }else{
            Utils.showToast(getActivity(), getString(R.string.add_images_error));
        }
    }

    @Override
    public void onDetach() {
        mCallback = null;
        super.onDetach();
    }

    private class AddPhotoListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            openImageChooser();
        }
    }
}
