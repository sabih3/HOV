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
}
