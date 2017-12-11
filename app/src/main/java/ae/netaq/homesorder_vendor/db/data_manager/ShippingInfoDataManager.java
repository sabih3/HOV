package ae.netaq.homesorder_vendor.db.data_manager;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import ae.netaq.homesorder_vendor.db.DBManager;
import ae.netaq.homesorder_vendor.models.ShippingInfo;

/**
 * Created by sabih on 05-Dec-17.
 */

public class ShippingInfoDataManager {


    public static Dao<ShippingInfo, Integer> getDao() {
        Dao<ShippingInfo, Integer> shippingDAO = null;
        try {
            shippingDAO = DBManager.getInstance().getDbHelper().getDao(ShippingInfo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shippingDAO;
    }
}
