package ae.netaq.homesorder_vendor.fragments.add_new_product.add_product_images;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ae.netaq.homesorder_vendor.AppController;
import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.adapters.add_new_product_images.AddImagesRecyclerAdapter;
import ae.netaq.homesorder_vendor.utils.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Netaq on 11/29/2017.
 */

public class AddProductImagesFragment extends Fragment implements View.OnClickListener{

    @BindView(R.id.add_photo_layout)
    LinearLayout addPhoto;

    @BindView(R.id.add_photo_images_recycler)
    RecyclerView addPhotoImagesRecycler;

    private static final int SELECT_PICTURE = 100;

    private AddProductImagesView mCallback;

    private Picasso picasso;

    private ArrayList<Uri> imagesUri;

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
        addPhoto.setOnClickListener(this);
        picasso = AppController.get(getActivity()).getPicasso();
        imagesUri = new ArrayList<>();

        initViews();

        return view;

    }

    private void initViews() {
        addPhotoImagesRecycler.setHasFixedSize(true);
        addPhotoImagesRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false));
    }

    // The following code is responsible of selecting multiple images from the storage.
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (data != null) {
                ClipData clipData = data.getClipData();
                if (clipData != null) {
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        Uri uri = item.getUri();
                        imagesUri.add(uri);
                    }
                }
                else{
                    Uri uri = data.getData();
                    imagesUri.add(uri);
                }
                initiateImagesRecycler(imagesUri);
            }
        }
    }

    private void initiateImagesRecycler(ArrayList<Uri> imagesUri) {
        AddImagesRecyclerAdapter addImagesRecyclerAdapter = new AddImagesRecyclerAdapter(getActivity(), imagesUri, picasso);
        addPhotoImagesRecycler.setAdapter(addImagesRecyclerAdapter);
    }

    /* Choose an image from Gallery */
    public void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.select_pictures)), SELECT_PICTURE);
    }

    public void validate(){
        if(imagesUri.size() > 0){
            mCallback.onAddImagesCompleted(imagesUri);
        }else{
            Utils.showToast(getActivity(), getString(R.string.add_images_error));
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.add_photo_layout){
         openImageChooser();
        }
    }

    @Override
    public void onDetach() {
        mCallback = null;
        super.onDetach();
    }
}
