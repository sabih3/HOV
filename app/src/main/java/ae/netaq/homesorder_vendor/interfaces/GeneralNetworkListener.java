package ae.netaq.homesorder_vendor.interfaces;

/**
 * Created by Netaq on 11/28/2017.
 *
 * This interface is also extended by other interface.
 * It's respective abstract method is overridden by the BAL listeners in order to handle no internet exception.
 */

public interface GeneralNetworkListener {

    void onNetworkFailure();

}
