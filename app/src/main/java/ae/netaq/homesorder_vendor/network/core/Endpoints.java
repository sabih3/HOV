package ae.netaq.homesorder_vendor.network.core;

/**
 * Created by sabih on 21-Dec-17.
 * sabih.ahmed@netaq.ae
 */

public class Endpoints {

    public static final String BASE_URL = "http://home.kendev.xyz/homeorder/public/api/";

    public static final String USER_REGISTER ="vendor/me/register";

    public static final String USER_LOGIN ="vendor/login";

    public static final String PRODUCT_ADD ="vendor/product/add/{userToken}";


    public static final String PRODUCT_UPDATE = "vendor/product/update/{userToken}";

    public static final String USER_FORGET_PWD = "vendor/forgotpassword";

    public static final String USER_PROFILE_UPDATE = "vendor/me/update/{userToken}";

    public static final String PRODUCT_LIST = "vendor/me/product/{userToken}";

    public static final String ORDER_LIST_NEW = "vendor/me/order/{userToken}/0";

    public static final String ORDER_LIST_PROCESSING = "vendor/me/order/{userToken}/1";

    public static final String ORDER_LIST_READY = "vendor/me/order/{userToken}/2";

    public static final String ORDER_LIST_DISPATCHED ="";

    public static final String ORDER_UPDATE_PROCESSING = "";

    public static final String ORDER_UPDATE_READY ="";

    public static final String ORDER_UPDATE_DISPATCHED = "";


}
