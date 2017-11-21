package ae.netaq.homesorder_vendor.dagger;

import android.content.Context;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Netaq on 11/20/2017.
 */

@Module(includes = ContextModule.class)
public class NetworkModule {

    @Provides
    @AppScope
    public File file(Context context){
        return new File(context.getCacheDir(), "okhttp.cache");
    }

    @Provides
    @AppScope
    public Cache cache(File cacheFile){
        return new Cache(cacheFile, 10*1000*1000);
    }

    @Provides
    @AppScope
    public HttpLoggingInterceptor httpLoggingInterceptor(){
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
    }

    @Provides
    @AppScope
    public OkHttpClient okHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, Cache cache){
        return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).cache(cache).build();
    }
}
