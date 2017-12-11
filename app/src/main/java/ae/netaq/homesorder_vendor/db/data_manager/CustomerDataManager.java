package ae.netaq.homesorder_vendor.db.data_manager;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import ae.netaq.homesorder_vendor.db.DBManager;
import ae.netaq.homesorder_vendor.db.data_manager.tables.CustomerTable;
import ae.netaq.homesorder_vendor.models.Customer;

/**
 * Created by sabih on 04-Dec-17.
 */

public class CustomerDataManager {

    public static Dao<CustomerTable,Integer> getCustomerDao(){
        Dao<CustomerTable, Integer> customerDAO = null;

        try {
            customerDAO = DBManager.getInstance().getDbHelper().getDao(CustomerTable.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerDAO;
    }
}
