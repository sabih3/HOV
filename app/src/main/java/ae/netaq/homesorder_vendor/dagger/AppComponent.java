package ae.netaq.homesorder_vendor.dagger;

import com.squareup.picasso.Picasso;

import ae.netaq.homesorder_vendor.network.HomesOrderServices;
import dagger.Component;

/**
 * Created by Netaq on 11/20/2017.
 */

@AppScope
@Component(modules = {ServicesModule.class,  PicassoModule.class})
public interface AppComponent {

    Picasso getPicasso();

    HomesOrderServices getHomesOrderServices();

}
