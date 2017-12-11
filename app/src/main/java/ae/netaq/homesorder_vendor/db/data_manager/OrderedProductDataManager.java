package ae.netaq.homesorder_vendor.db.data_manager;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import ae.netaq.homesorder_vendor.db.DBManager;
import ae.netaq.homesorder_vendor.models.OrderedProducts;

/**
 * Created by sabih on 05-Dec-17.
 */

public class OrderedProductDataManager {


    public static Dao<OrderedProducts, Integer> getOrderedProductDao(){
        Dao<OrderedProducts, Integer> orderedProductDAO = null;
        try {
            orderedProductDAO = DBManager.getInstance().getDbHelper().getDao(OrderedProducts.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderedProductDAO;
    }


    public static void insertOrderedProducts(){

    }


}
