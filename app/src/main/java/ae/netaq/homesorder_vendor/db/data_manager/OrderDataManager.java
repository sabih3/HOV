package ae.netaq.homesorder_vendor.db.data_manager;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ae.netaq.homesorder_vendor.db.DBManager;
import ae.netaq.homesorder_vendor.db.data_manager.tables.OrderTable;
import ae.netaq.homesorder_vendor.db.data_manager.tables.OrderedProducts;
import ae.netaq.homesorder_vendor.models.Customer;
import ae.netaq.homesorder_vendor.models.Order;
import ae.netaq.homesorder_vendor.network.model.ResponseOrderList;
import okhttp3.Response;

/**
 * Created by Netaq on 11/28/2017.
 */

public class OrderDataManager {

    public static final String STATUS_NEW = "pending";
    public static final String STATUS_PROCESSING = "processing";
    public static final String STATUS_READY = "ready";
    public static final String STATUS_DISPATCHED = "complete";


    public static List<OrderTable> getNewOrdersList(Context context) throws SQLException {
        List<OrderTable> persistedOrders = new ArrayList<>();

        persistedOrders = getOrdersForStatus(STATUS_NEW);

        return persistedOrders;
    }

    public static List<OrderTable> getProcessingOrdersList(Context context) throws SQLException {
        List<OrderTable> underProcessOrders = getOrdersForStatus(STATUS_PROCESSING);

        return underProcessOrders;
    }

    private static List<OrderTable> getOrdersForStatus(String status) throws SQLException {
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
    public static void createOrder(ResponseOrderList remoteOrder) {

        //Customer customer = order.getCustomer();

        List<ResponseOrderList.Item> items = remoteOrder.getItems();
        OrderTable orderToPersist = new OrderTable();
        orderToPersist.setOrderID(remoteOrder.getOrderID());
        orderToPersist.setOrderStatus(remoteOrder.getOrderStatus());
        orderToPersist.setOrderTotal(remoteOrder.getOrderTotal());
        orderToPersist.setPayment_mode(remoteOrder.getPayment_mode());
        orderToPersist.setOrderDate(remoteOrder.getOrderDate());
        orderToPersist.setDueDate(remoteOrder.getDueDate());
        orderToPersist.setComments(remoteOrder.getComments());
        orderToPersist.setCustomerID(remoteOrder.getCustomer().get(0).getCustomerID());
        orderToPersist.setName(remoteOrder.getCustomer().get(0).getName());
        orderToPersist.setEmail(remoteOrder.getCustomer().get(0).getEmail());
        orderToPersist.setAddress(remoteOrder.getCustomer().get(0).getAddress());
        orderToPersist.setPhone(remoteOrder.getCustomer().get(0).getPhone());
        orderToPersist.setShippingNotes(remoteOrder.getCustomer().get(0).getShippingNotes());

        try {
            getOrderDao().create(orderToPersist);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        OrderedProducts orderedProducts = new OrderedProducts();

        for(ResponseOrderList.Item item: items){
            orderedProducts.setOrderID(remoteOrder.getOrderID());
            orderedProducts.setProductID(item.getProductID());
            orderedProducts.setNameEN(item.getProductNameEN());
            orderedProducts.setNameAR(item.getProductNameAR());
            orderedProducts.setPrice(item.getPrice());

//            orderedProducts.setMainCategoryEN(item.getMainCategorynameEN().get(0));
//            orderedProducts.setMainCategoryAR(item.getMainCategorynameAR().get(0));
//            orderedProducts.setTargetGroupEN(item.getTargetedGroupNameEN().get(0));
//            orderedProducts.setTargetGroupAR(item.getTargetedGroupNameAR().get(0));
//            orderedProducts.setSubCategoryEN(item.getSubCategoryNameEn().get(0));
//            orderedProducts.setSubCategoryAR(item.getSubCategoryNameAR().get(0));
            orderedProducts.setPreviewImage(item.getMedia().get(0));
            orderedProducts.setDesiredQuantity(item.getQty_ordered());
            orderedProducts.setDesiredSize(item.getOptions().get(0).getSize());
            orderedProducts.setDesiredColor(item.getOptions().get(1).getColor());
            orderedProducts.setDesiredWeight(item.getOptions().get(2).getWeight());
            orderedProducts.setItemComments(item.getComments());

            try {
                Dao<OrderedProducts, Integer> orderedProductDAO = DBManager.getInstance().
                                                        getDbHelper().getDao(OrderedProducts.class);

                orderedProductDAO.create(orderedProducts);

            } catch (SQLException e) {
                e.printStackTrace();
            }
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

    public static void persistAllOrders(List<ResponseOrderList> orders,
                                        OrderPersistenceListener persistenceListener) {
        for(ResponseOrderList order:orders){

            if(existsInDB(order)){
                continue;
            }else{
                createOrder(order);
            }


        }

        persistenceListener.onOrdersPersisted();
    }

    private static boolean existsInDB(ResponseOrderList remoteOrder) {

        try {
            List<OrderTable> orderTableList = getOrderDao().queryForEq(OrderTable.ColumnNames.ORDER_ID,
                             remoteOrder.getOrderID());
            if(!orderTableList.isEmpty()){
                OrderTable orderTable = orderTableList.get(0);
                if(remoteOrder.getOrderID()==remoteOrder.getOrderID()){
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

    public static void updateOrder(long orderID, String status) throws SQLException {
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
