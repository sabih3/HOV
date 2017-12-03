package ae.netaq.homesorder_vendor.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

import static ae.netaq.homesorder_vendor.models.Customer.ColumnNames.COLUMN_ADDRESS;
import static ae.netaq.homesorder_vendor.models.Customer.ColumnNames.COLUMN_CONTACT;
import static ae.netaq.homesorder_vendor.models.Customer.ColumnNames.COLUMN_EMAIL;
import static ae.netaq.homesorder_vendor.models.Customer.ColumnNames.TABLE_NAME;
import static ae.netaq.homesorder_vendor.models.Customer.ColumnNames.COLUMN_ID;
import static ae.netaq.homesorder_vendor.models.Customer.ColumnNames.COLUMN_CUSTOMER_NAME;

/**
 * Created by Sabih Ahmed on 03-Dec-17.
 * sabih.ahmed@netaq.ae
 */

@DatabaseTable(tableName = TABLE_NAME)
public class Customer implements Serializable{

    @DatabaseField(id = true,unique = true, columnName =COLUMN_ID )
    public long customerID;

    @DatabaseField(columnName = COLUMN_CUSTOMER_NAME)
    public String name;

    @DatabaseField(columnName = COLUMN_ADDRESS)
    public String address;

    @DatabaseField(columnName = COLUMN_CONTACT)
    public String contactNumber;

    @DatabaseField(columnName = COLUMN_EMAIL)
    public String email;

    public long getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;

    }

    public interface ColumnNames{
        String TABLE_NAME = "customers";
        String COLUMN_ID = "id";
        String COLUMN_CUSTOMER_NAME ="customer_name";
        String COLUMN_ADDRESS = "address";
        String COLUMN_CONTACT = "contact_number";
        String COLUMN_EMAIL = "email";
    }
}
