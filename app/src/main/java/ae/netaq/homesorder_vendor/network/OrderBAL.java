package ae.netaq.homesorder_vendor.network;

import android.content.Context;

import com.google.gson.Gson;

import java.util.ArrayList;

import ae.netaq.homesorder_vendor.db.data_manager.OrderDataManager;
import ae.netaq.homesorder_vendor.models.Order;
import ae.netaq.homesorder_vendor.models.Orders;
import ae.netaq.homesorder_vendor.utils.JSONUtils;
import ae.netaq.homesorder_vendor.utils.NavigationController;

/**
 * Created by sabih on 06-Dec-17.
 */

public class OrderBAL {

    private static String FILENAME_NEW_ORDERS = "new_orders";

    public static void getAllOrders(Context context, final OrderFetchListener orderFetchListener) {

        String jsonString = JSONUtils.loadJSONFromAsset(context, FILENAME_NEW_ORDERS);

        Gson gson = new Gson();
        Orders orders = gson.fromJson(jsonString, Orders.class);

        orderFetchListener.onOrdersFetched(orders.getOrders());

    }





    public interface OrderFetchListener{

        void onOrdersFetched(ArrayList<Order> orders);

    }
}
