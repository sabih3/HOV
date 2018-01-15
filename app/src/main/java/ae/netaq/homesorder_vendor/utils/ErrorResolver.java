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
                                          int code) {

//        APIError apiError = ErrorUtils.parseError(response);
//        int code = apiError.getCode();
        String interpretedException = interpretError(mContext, code);

        return interpretedException;
    }

    private static String interpretError(Context mContext, int code) {


        String interpretedError ="";

        switch (code){
            case 1000:
                interpretedError = "The product already exists, Please try be giving a different " +
                        "name";
            break;

            case 1001:
                interpretedError = mContext.getString(R.string.error_msg_1001);
            break;

            case 1002:
                interpretedError = mContext.getString(R.string.error_msg_1002);
            break;

            case 2001:
                interpretedError = mContext.getString(R.string.error_msg_2001);
            break;

            case 4000:
                interpretedError = "";
            break;

            case 6000:
                interpretedError = "The product name already exist please choose another name";
            break;

            case 6001:
                interpretedError = "Please choose below size values only";
            break;

            case 6002:
                interpretedError = "Please choose below colors only";
            break;

            default:
                interpretedError = mContext.getString(R.string.error_default);
            break;
        }

        return interpretedError;
    }
}
