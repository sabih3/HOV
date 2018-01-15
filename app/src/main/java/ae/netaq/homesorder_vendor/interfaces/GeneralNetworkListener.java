package ae.netaq.homesorder_vendor.interfaces;

/**
 * Created by Netaq on 11/28/2017.
 *
 * Base Network Listener
 *
 */

public interface GeneralNetworkListener {

    void onNetworkFailure();
    void onSessionTimesOut();

}
