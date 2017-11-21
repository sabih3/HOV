package ae.netaq.homesorder_vendor.dagger;

import ae.netaq.homesorder_vendor.network.HomesOrderServices;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Netaq on 11/20/2017.
 */

@Module(includes = NetworkModule.class)
public class ServicesModule {

    @Provides
    @AppScope
    public HomesOrderServices servicesInterface(Retrofit retrofit){

        return retrofit.create(HomesOrderServices.class);
    }

    @Provides
    @AppScope
    public Retrofit retrofit(OkHttpClient okHttpClient){

        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).baseUrl("https://api.github.com/").build();

    }

}
