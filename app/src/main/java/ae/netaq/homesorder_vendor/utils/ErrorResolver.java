package ae.netaq.homesorder_vendor.utils;

import android.content.Context;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.network.core.ErrorUtils;
import ae.netaq.homesorder_vendor.network.model.APIError;
import retrofit2.Response;

/**
 * Created by sabih on 24-Dec-17.
 */

public class ErrorResolver {


    public static String getLocalizedError(Context mContext,
                                           Response<?> response) {

        APIError apiError = ErrorUtils.parseError(response);

        String interpretedException = interpretError(mContext, apiError);

        return interpretedException;
    }

    private static String interpretError(Context mContext, APIError apiError) {

        int code = apiError.getCode();
        String interpretedError ="";

        switch (code){

            case 1001:
                interpretedError = mContext.getString(R.string.error_msg_1001);
            break;

            case 1002:
                interpretedError = mContext.getString(R.string.error_msg_1002);
            break;

            default:
                interpretedError = mContext.getString(R.string.error_default);
            break;
        }

        return interpretedError;
    }
}
