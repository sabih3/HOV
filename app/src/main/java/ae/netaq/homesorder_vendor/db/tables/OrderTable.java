package ae.netaq.homesorder_vendor.db.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

import static ae.netaq.homesorder_vendor.db.tables.OrderTable.ColumnNames.COLUMN_CUSTOMER_ADDRESS;
import static ae.netaq.homesorder_vendor.db.tables.OrderTable.ColumnNames.COLUMN_CUSTOMER_EMAIL;
import static ae.netaq.homesorder_vendor.db.tables.OrderTable.ColumnNames.COLUMN_CUSTOMER_ID;
import static ae.netaq.homesorder_vendor.db.tables.OrderTable.ColumnNames.COLUMN_CUSTOMER_NAME;
import static ae.netaq.homesorder_vendor.db.tables.OrderTable.ColumnNames.COLUMN_CUSTOMER_PHONE;
import static ae.netaq.homesorder_vendor.db.tables.OrderTable.ColumnNames.COLUMN_SHIPPING_NOTES;


/**
 * Created by Sabih Ahmed on 05-Dec-17.
 */

@DatabaseTable(tableName = OrderTable.ColumnNames.TABLE_NAME)

public class OrderTable {

    @DatabaseField(generatedId = true,columnName = ColumnNames.ID)
    public long id;

    @DatabaseField(columnName = ColumnNames.ORDER_ID)
    public long orderID;

    @DatabaseField(columnName = ColumnNames.ORDER_STATUS)
    public String orderStatus;

    @DatabaseField(columnName = ColumnNames.ORDER_AMOUNT)
    public double orderTotal;

    @DatabaseField(columnName = ColumnNames.PAYMENT_MODE)
    public String payment_mode;

    @DatabaseField(columnName = ColumnNames.ORDER_DATE)
    public String orderDate;

    @DatabaseField(columnName = ColumnNames.DUE_DATE)
    public String dueDate;

    @DatabaseField(columnName = ColumnNames.COMMENTS)
    public String comments;

    @DatabaseField(columnName = COLUMN_CUSTOMER_ID)
    public String customerID;

    @DatabaseField(columnName = COLUMN_CUSTOMER_NAME)
    public String name;

    @DatabaseField(columnName = COLUMN_CUSTOMER_EMAIL)
    public String email;

    @DatabaseField(columnName = COLUMN_CUSTOMER_ADDRESS)
    public String address;

    @DatabaseField(columnName = COLUMN_CUSTOMER_PHONE)
    public String phone;

    @DatabaseField(columnName = COLUMN_SHIPPING_NOTES)
    public String shippingNotes;

    @DatabaseField(columnName = "base_shipping_amount")
    public long baseShippingAmount;

    public List<OrderedProducts> items;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShippingNotes() {
        return shippingNotes;
    }

    public void setShippingNotes(String shippingNotes) {
        this.shippingNotes = shippingNotes;
    }

    public long getBaseShippingAmount() {
        return baseShippingAmount;
    }

    public List<OrderedProducts> getItems() {
        return items;
    }

    public void setBaseShippingAmount(long baseShippingAmount) {
        this.baseShippingAmount = baseShippingAmount;
    }

    public void setItems(List<OrderedProducts> items) {
        this.items = items;
    }

    public interface ColumnNames{
        String TABLE_NAME = "orders";
        String ID = "id";
        String ORDER_ID = "order_id";
        String ORDER_STATUS = "status";
        String ORDER_AMOUNT = "amount";
        String PAYMENT_MODE = "payment_mode";
        String ORDER_DATE = "order_date";
        String DUE_DATE = "due_date";
        String COMMENTS = "comments";
        String COLUMN_CUSTOMER_ID = "customer_id";
        String COLUMN_CUSTOMER_NAME="customer_name";
        String COLUMN_CUSTOMER_EMAIL = "customer_email";
        String COLUMN_CUSTOMER_ADDRESS = "customer_address";
        String COLUMN_CUSTOMER_PHONE="customer_phone";
        String COLUMN_SHIPPING_NOTES = "shipping_notes";


    }
}
