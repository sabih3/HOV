package ae.netaq.homesorder_vendor.models;

import java.util.ArrayList;

/**
 * Created by Netaq on 11/28/2017.
 */

public class Orders {

    public ArrayList<Order> orders;

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public class Customer
    {
        public String name;
        public String address;
        public String contactNumber;
        public String email;

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
    }

    public class Product
    {
        public int productID;
        public String productNameAR;
        public String productNameEN;
        public String parentCategoryNameAR;
        public String parentCategoryNameEN;
        public int parentCategoryID;
        public int isOnPromotion;
        public int isFeatured;
        public String comments;
        public String parentCategoryName;

        public int getProductID() {
            return productID;
        }

        public String getProductNameAR() {
            return productNameAR;
        }

        public String getProductNameEN() {
            return productNameEN;
        }

        public String getParentCategoryNameAR() {
            return parentCategoryNameAR;
        }

        public String getParentCategoryNameEN() {
            return parentCategoryNameEN;
        }

        public int getParentCategoryID() {
            return parentCategoryID;
        }

        public int getIsOnPromotion() {
            return isOnPromotion;
        }

        public int getIsFeatured() {
            return isFeatured;
        }

        public String getComments() {
            return comments;
        }

        public String getParentCategoryName() {
            return parentCategoryName;
        }
    }

    public class Order
    {
        public int orderID;
        public int orderStatus;
        public double orderTotal;
        public int payment_mode;
        public String orderDate;
        public String dueDate;
        public String comments;
        public Customer customer;
        public ArrayList<Product> products;

        public int getOrderID() {
            return orderID;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public double getOrderTotal() {
            return orderTotal;
        }

        public int getPayment_mode() {
            return payment_mode;
        }

        public String getOrderDate() {
            return orderDate;
        }

        public String getDueDate() {
            return dueDate;
        }

        public String getComments() {
            return comments;
        }

        public Customer getCustomer() {
            return customer;
        }

        public ArrayList<Product> getProducts() {
            return products;
        }
    }

}
