package ae.netaq.homesorder_vendor.activities.update_profile;

import ae.netaq.homesorder_vendor.activities.GeneralView;

/**
 * Created by Netaq on 12/20/2017.
 */

public interface UpdateProfileActivityView extends GeneralView{
    void onProfileUpdated();
    void onProfileUpdateFailure(String errorMessage);
}
