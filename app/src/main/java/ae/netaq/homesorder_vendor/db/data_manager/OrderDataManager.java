package ae.netaq.homesorder_vendor.db.data_manager;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ae.netaq.homesorder_vendor.db.DBManager;
import ae.netaq.homesorder_vendor.db.data_manager.tables.OrderTable;
import ae.netaq.homesorder_vendor.models.Customer;
import ae.netaq.homesorder_vendor.models.Order;

/**
 * Created by Netaq on 11/28/2017.
 */

public class OrderDataManager {

    public static final int STATUS_NEW = 0;
    public static final int STATUS_PROCESSING = 1;
    public static final int STATUS_READY = 2;
    public static final int STATUS_DISPATCHED = 3;


    private static String FILENAME_NEW_ORDERS = "new_orders";
    private static String FILENAME_PROCESSING_ORDERS = "processing_orders";
    private static String FILENAME_READY_ORDERS = "ready_orders";
    private static String FILENAME_DISPATCHED_ORDERS = "dispatched_orders";


    public static List<OrderTable> getNewOrdersList(Context context) throws SQLException {
        List<OrderTable> persistedOrders = new ArrayList<>();

        persistedOrders = getOrdersForStatus(STATUS_NEW);

        return persistedOrders;
    }

    public static List<OrderTable> getProcessingOrdersList(Context context) throws SQLException {
        List<OrderTable> underProcessOrders = getOrdersForStatus(STATUS_PROCESSING);

        return underProcessOrders;
    }

    private static List<OrderTable> getOrdersForStatus(int status) throws SQLException {
        List<OrderTable> persistedOrders = new ArrayList<>();

        persistedOrders = getOrderDao().queryForEq(OrderTable.ColumnNames.ORDER_STATUS, status);


        return persistedOrders;
    }

    public static List<OrderTable> getReadyOrdersList(Context context) throws SQLException {
        List<OrderTable> readyOrders = getOrdersForStatus(STATUS_READY);

        return readyOrders;
    }

    public static List<OrderTable> getDispatchedOrdersList(Context context) throws SQLException {
        List<OrderTable> dispatchedList = getOrdersForStatus(STATUS_DISPATCHED);

        return dispatchedList;
    }


    public static Dao<OrderTable,Integer> getOrderDao(){
        Dao<OrderTable, Integer> orderDao = null;
        try {
            orderDao = DBManager.getInstance().getDbHelper().getDao(OrderTable.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderDao;
    }

    /**To persist order in database
     *
     * @param order
     * @throws SQLException
     */
    public static void createOrder(Order order) {

        Customer customer = order.getCustomer();

        OrderTable orderToPersist = new OrderTable();
        orderToPersist.setOrderID(order.getOrderID());
        orderToPersist.setOrderStatus(order.getOrderStatus());
        orderToPersist.setOrderTotal(order.getOrderTotal());
        orderToPersist.setPayment_mode(order.getPayment_mode());
        orderToPersist.setOrderDate(order.getOrderDate());
        orderToPersist.setDueDate(order.getDueDate());
        orderToPersist.setComments(order.getComments());
        orderToPersist.setCustomerID(order.getCustomer().getCustomerID());
        orderToPersist.setName(order.getCustomer().getName());
        orderToPersist.setEmail(order.getCustomer().getEmail());
        orderToPersist.setAddress(order.getCustomer().getAddress());
        orderToPersist.setPhone(order.getCustomer().getPhone());
        orderToPersist.setShippingNotes(order.getCustomer().getShippingNotes());

        try {
            OrderDataManager.getOrderDao().createOrUpdate(orderToPersist);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<OrderTable> getAllOrders(){
        List<OrderTable> orders = null;
        try {
            orders = getOrderDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public static void persistAllOrders(ArrayList<Order> orders, OrderPersistenceListener orderPersistenceListener) {
        for(Order order:orders){

            if(existsInDB(order)){
                continue;
            }else{
                createOrder(order);
            }


        }

        orderPersistenceListener.onOrdersPersisted();

    }

    private static boolean existsInDB(Order order) {

        try {
            List<OrderTable> orderTableList = getOrderDao().queryForEq(OrderTable.ColumnNames.ORDER_ID, order.getOrderID());
            if(!orderTableList.isEmpty()){
                OrderTable orderTable = orderTableList.get(0);
                if(order.getOrderID()==order.getOrderID()){
                    return true;
                }
            }else{
                return false;
            }
        } catch (SQLException e) {
            //Todo:Handle Exception
            e.printStackTrace();
        }
        return false;
    }

    public static void updateOrder(long orderID,int status) throws SQLException {
        List<OrderTable> orderList = getOrderDao().queryForEq(OrderTable.ColumnNames.ORDER_ID, orderID);

        if(!orderList.isEmpty()){

            OrderTable order = orderList.get(0);
            order.setOrderStatus(status);
            getOrderDao().update(order);
        }

    }

    public interface OrderPersistenceListener{

        void onOrdersPersisted();
    }
}
