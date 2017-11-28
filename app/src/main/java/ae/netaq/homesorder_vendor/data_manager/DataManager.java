package ae.netaq.homesorder_vendor.data_manager;

import android.content.Context;

import com.google.gson.Gson;

import ae.netaq.homesorder_vendor.models.Orders;
import ae.netaq.homesorder_vendor.utils.JSONUtils;

/**
 * Created by Netaq on 11/28/2017.
 */

public class DataManager {

    private static String FILENAME_NEW_ORDERS = "new_orders";
    private static String FILENAME_PROCESSING_ORDERS = "processing_orders";
    private static String FILENAME_READY_ORDERS = "ready_orders";
    private static String FILENAME_DISPATCHED_ORDERS = "dispatched_orders";


    public static Orders getNewOrdersList(Context context) {
        String jsonString = JSONUtils.loadJSONFromAsset(context, FILENAME_NEW_ORDERS);

        Gson gson = new Gson();
        Orders orders = gson.fromJson(jsonString, Orders.class);

        return orders;
    }

    public static Orders getProcessingOrdersList(Context context) {
        String jsonString = JSONUtils.loadJSONFromAsset(context, FILENAME_PROCESSING_ORDERS);

        Gson gson = new Gson();
        Orders orders = gson.fromJson(jsonString, Orders.class);

        return orders;
    }

    public static Orders getReadyOrdersList(Context context) {
        String jsonString = JSONUtils.loadJSONFromAsset(context, FILENAME_READY_ORDERS);

        Gson gson = new Gson();
        Orders orders = gson.fromJson(jsonString, Orders.class);

        return orders;
    }

    public static Orders getDispatchedOrdersList(Context context) {
        String jsonString = JSONUtils.loadJSONFromAsset(context, FILENAME_DISPATCHED_ORDERS);

        Gson gson = new Gson();
        Orders orders = gson.fromJson(jsonString, Orders.class);

        return orders;
    }
}
