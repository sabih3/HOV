package ae.netaq.homesorder_vendor.db.data;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ae.netaq.homesorder_vendor.db.core.DatabaseHelper;
import ae.netaq.homesorder_vendor.db.core.DatabaseManager;
import ae.netaq.homesorder_vendor.db.tables.OrderTable;
import ae.netaq.homesorder_vendor.db.tables.OrderedProducts;


/**
 * Created by Sabih Ahmed on 05-Dec-17.
 */

public class OrderRepository {

    private DatabaseHelper dbHelper = null;
    private DatabaseManager dbManager =null;
    private Dao<OrderTable,Integer> orderDAO = null;
    private Dao<OrderedProducts,Integer> orderedProductsDAO = null;

    public OrderRepository(Context context) {

        dbManager = new DatabaseManager();
        dbHelper = dbManager.getHelper(context);
        try {
            orderDAO = dbHelper.getOrderDAO();
            orderedProductsDAO = dbHelper.getOrderedProductsDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createOrder(OrderTable Order) throws SQLException {

        orderDAO.create(Order);


    }

    public void updateOrder(long orderID,String status) throws SQLException {
        List<OrderTable> orderList = new ArrayList<>();
        try {
            orderList = orderDAO.queryForEq(OrderTable.ColumnNames.ORDER_ID, orderID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(!orderList.isEmpty()){

            OrderTable order = orderList.get(0);
            order.setOrderStatus(status);
            orderDAO.update(order);
        }
    }

    public List<OrderTable> getOrdersForStatus(String status) throws SQLException {
        List<OrderTable> persistedOrders = new ArrayList<>();

        persistedOrders = orderDAO.queryForEq(OrderTable.ColumnNames.ORDER_STATUS, status);


        return persistedOrders;
    }

    public List<OrderTable> getAllOrders() throws SQLException {
        List<OrderTable> orders = new ArrayList<>();

        orders = orderDAO.queryForAll();

        return orders;
    }
    public boolean checkIfOrderExists(Long orderID) throws SQLException {

        List<OrderTable> orderTableList = orderDAO.queryForEq(OrderTable.ColumnNames.ORDER_ID,
                orderID);
        if(!orderTableList.isEmpty()){
            OrderTable orderTable = orderTableList.get(0);
            if(orderID == orderTable.getOrderID()){
                return true;
            }
            }else{
                return false;
            }

    return false;
    }

    public void createOrderedProducts(OrderedProducts orderedProducts) throws SQLException {
        orderedProductsDAO.create(orderedProducts);
    }

    public void release(){
        dbManager.releaseHelper();
    }

}
