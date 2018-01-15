package ae.netaq.homesorder_vendor.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.db.data_manager.tables.ImageTable;
import ae.netaq.homesorder_vendor.utils.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 12/7/2017.
 */

public class SliderPagerAdapter extends PagerAdapter {

    @BindView(R.id.image)
    ImageView imageView;

    private List<ImageTable> images;
    private ArrayList<Uri> imagesUri;
    private LayoutInflater inflater;
    private Picasso picasso;
    private Context context;

    public SliderPagerAdapter(Context context,
                              List<ImageTable> images,
                              ArrayList<Uri> imagesUri, Picasso picasso) {
        this.context = context;
        this.images = images;
        this.imagesUri = imagesUri;
        this.picasso = picasso;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        if(images!=null) {
            return images.size();
        }else if(imagesUri!=null){
            return imagesUri.size();
        }else{
            return 0;
        }
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View myImageLayout = inflater.inflate(R.layout.image_slide, view, false);
        ButterKnife.bind(this, myImageLayout);

        if(images !=null){
            String imageURL = images.get(position).getImageURI();
            //String imagePath = Utils.getPathBasedOnSDK(context,imageURI);
            // load from detail
            picasso.load(imageURL).resize(500, 400).centerCrop().into(imageView);
        }
        if(imagesUri != null){

            //load from preview
            picasso.load(imagesUri.get(position)).resize(500, 400).centerCrop().into(imageView);

        }


        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}