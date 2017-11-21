package ae.netaq.homesorder_vendor.dagger;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Netaq on 11/20/2017.
 */

@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @AppScope
    public Context context(){
        return context;
    }
}
