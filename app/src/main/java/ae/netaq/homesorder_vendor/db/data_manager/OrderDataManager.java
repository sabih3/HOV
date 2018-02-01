package ae.netaq.homesorder_vendor.db.data_manager;

import android.content.Context;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ae.netaq.homesorder_vendor.db.data.OrderRepository;
import ae.netaq.homesorder_vendor.db.tables.OrderTable;
import ae.netaq.homesorder_vendor.db.tables.OrderedProducts;
import ae.netaq.homesorder_vendor.network.model.ResponseOrderList;

/**
 * Created by Netaq on 11/28/2017.
 */

public class OrderDataManager {

    public static final String STATUS_NEW = "pending";
    public static final String STATUS_PROCESSING = "processing";
    public static final String STATUS_READY = "ready";
    public static final String STATUS_DISPATCHED = "complete";


    public static List<OrderTable> getNewOrdersList(Context context) throws SQLException {
        OrderRepository orderRepository = new OrderRepository(context);
        List<OrderTable> persistedOrders = new ArrayList<>();
        persistedOrders = orderRepository.getOrdersForStatus(STATUS_NEW);
        orderRepository.release();

        return persistedOrders;
    }

    public static List<OrderTable> getProcessingOrdersList(Context context) throws SQLException {
        OrderRepository orderRepository = new OrderRepository(context);
        List<OrderTable> underProcessOrders = new ArrayList<OrderTable>();
         //underProcessOrders = getOrdersForStatus(STATUS_PROCESSING);
        underProcessOrders = orderRepository.getOrdersForStatus(STATUS_PROCESSING);
        orderRepository.release();

        return underProcessOrders;
    }


    public static List<OrderTable> getReadyOrdersList(Context context) throws SQLException {
        OrderRepository orderRepository = new OrderRepository(context);
        List<OrderTable> readyOrders = new ArrayList<>();

        readyOrders = orderRepository.getOrdersForStatus(STATUS_READY);
        orderRepository.release();
        return readyOrders;
    }

    public static List<OrderTable> getDispatchedOrdersList(Context context) throws SQLException {
        OrderRepository orderRepository = new OrderRepository(context);
        List<OrderTable> dispatchedList = new ArrayList<>();
        dispatchedList = orderRepository.getOrdersForStatus(STATUS_DISPATCHED);

        orderRepository.release();

        return dispatchedList;
    }

    /**To persist order in database
     *
     * @param order
     * @throws SQLException
     */
    public static void createOrder(Context context,ResponseOrderList remoteOrder) {
        OrderRepository orderRepository = new OrderRepository(context);

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
            orderRepository.createOrder(orderToPersist);

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
                orderRepository.createOrderedProducts(orderedProducts);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        orderRepository.release();
    }

    public static List<OrderTable> getAllOrders(Context context) throws SQLException {
        List<OrderTable> allPersistedOrders = new ArrayList<>();

        OrderRepository orderRepository = new OrderRepository(context);
        allPersistedOrders = orderRepository.getAllOrders();

        orderRepository.release();

        return allPersistedOrders;
    }

    public static void persistAllOrders(Context context,List<ResponseOrderList> orders,
                                        OrderPersistenceListener persistenceListener) {

        for(ResponseOrderList order:orders){

            if(existsInDB(context,order)){
                try {
                    updateOrder(context,order.getOrderID(),order.getOrderStatus());

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else{

                createOrder(context,order);
            }


        }

        persistenceListener.onOrdersPersisted();
    }

    private static boolean existsInDB(Context context,ResponseOrderList remoteOrder) {
        OrderRepository orderRepository = new OrderRepository(context);

        boolean exists ;

        try {
            exists = orderRepository.checkIfOrderExists(remoteOrder.getOrderID());
        } catch (SQLException e) {
            // In case of exception, returning true, so that the order get updated,
            //rather created new.
            exists = true;

        }
        orderRepository.release();

        return exists;
    }

    /**Update order by status
     *
     * @param orderID
     * @param status
     * @throws SQLException
     */
    public static void updateOrder(Context context,long orderID, String status) throws SQLException {
        OrderRepository orderRepository = new OrderRepository(context);
        orderRepository.updateOrder(orderID,status);

        orderRepository.release();
    }



    public interface OrderPersistenceListener{

        void onOrdersPersisted();
    }
}
