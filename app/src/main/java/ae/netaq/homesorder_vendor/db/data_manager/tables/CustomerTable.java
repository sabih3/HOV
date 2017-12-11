package ae.netaq.homesorder_vendor.db.data_manager.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import static ae.netaq.homesorder_vendor.db.data_manager.tables.CustomerTable.ColumnNames.COLUMN_CUSTOMER_ID;
import static ae.netaq.homesorder_vendor.db.data_manager.tables.CustomerTable.ColumnNames.COLUMN_CUSTOMER_NAME;
import static ae.netaq.homesorder_vendor.db.data_manager.tables.CustomerTable.ColumnNames.COLUMN_EMAIL;
import static ae.netaq.homesorder_vendor.db.data_manager.tables.CustomerTable.ColumnNames.COLUMN_ID;
import static ae.netaq.homesorder_vendor.db.data_manager.tables.CustomerTable.ColumnNames.TABLE_NAME;

/**
 * Created by sabih on 05-Dec-17.
 */
@DatabaseTable(tableName = TABLE_NAME)
public class CustomerTable {
    @DatabaseField(generatedId = true,columnName = COLUMN_ID)
    public long id;

    @DatabaseField(unique = true,columnName =COLUMN_CUSTOMER_ID )
    public long customerID;

    @DatabaseField(columnName = COLUMN_CUSTOMER_NAME)
    public String name;

    @DatabaseField(columnName = COLUMN_EMAIL)
    public String email;



    public interface ColumnNames{
        String TABLE_NAME = "customers";
        String COLUMN_CUSTOMER_ID = "customer_id";
        String COLUMN_ID = "id";
        String COLUMN_CUSTOMER_NAME ="customer_name";
        String COLUMN_ADDRESS = "address";
        String COLUMN_CONTACT = "contact_number";
        String COLUMN_EMAIL = "email";
    }
}
