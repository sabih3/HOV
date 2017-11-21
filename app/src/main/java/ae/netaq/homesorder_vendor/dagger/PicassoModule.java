package ae.netaq.homesorder_vendor.dagger;

import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by Netaq on 11/20/2017.
 */

@Module(includes = {ContextModule.class, NetworkModule.class})
public class PicassoModule {

    @Provides
    @AppScope
    public Picasso picasso(Context context, OkHttp3Downloader okHttp3Downloader){
        return new Picasso.Builder(context).downloader(okHttp3Downloader).build();
    }

    @Provides
    @AppScope
    public OkHttp3Downloader okHttp3Downloader(OkHttpClient okHttpClient){
       return new OkHttp3Downloader(okHttpClient);
    }
}
