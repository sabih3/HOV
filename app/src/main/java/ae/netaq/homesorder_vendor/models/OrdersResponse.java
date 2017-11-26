package ae.netaq.homesorder_vendor.models;

import java.util.ArrayList;

/**
 * Created by Netaq on 11/23/2017.
 */

public class OrdersResponse {

    public int code;
    public String msg;
    public int page_no;
    public ArrayList<Order> orders;

    public OrdersResponse() {
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public int getPage_no() {
        return page_no;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public class Attribute
    {
        public int attr_id;
        public String spicy;
        public String vegetarian;
    }

    public class Product
    {
        public int product_id;
        public String product_name;
        public String product_category;
        public int product_prmoted;
        public String product_featured;
        public ArrayList<Attribute> attributes;

        public int getProduct_id() {
            return product_id;
        }

        public String getProduct_name() {
            return product_name;
        }

        public String getProduct_category() {
            return product_category;
        }

        public int getProduct_prmoted() {
            return product_prmoted;
        }

        public String getProduct_featured() {
            return product_featured;
        }

        public ArrayList<Attribute> getAttributes() {
            return attributes;
        }
    }

    public class Order
    {
        public int order_id;
        public String order_date;
        public String order_time;
        public String delivery_date;
        public String delivery_time;
        public String customer_name;
        public String customer_address;
        public String customer_comments;
        public ArrayList<Product> products;

        public int getOrder_id() {
            return order_id;
        }

        public String getOrder_date() {
            return order_date;
        }

        public String getOrder_time() {
            return order_time;
        }

        public String getDelivery_date() {
            return delivery_date;
        }

        public String getDelivery_time() {
            return delivery_time;
        }

        public String getCustomer_name() {
            return customer_name;
        }

        public String getCustomer_address() {
            return customer_address;
        }

        public String getCustomer_comments() {
            return customer_comments;
        }

        public ArrayList<Product> getProducts() {
            return products;
        }
    }

    public static ArrayList<OrdersResponse> getOrdersList(){

        ArrayList<OrdersResponse> ordersResponses = new ArrayList<>();
        ordersResponses.add(new OrdersResponse());
        ordersResponses.add(new OrdersResponse());
        ordersResponses.add(new OrdersResponse());
        ordersResponses.add(new OrdersResponse());
        ordersResponses.add(new OrdersResponse());
        ordersResponses.add(new OrdersResponse());
        ordersResponses.add(new OrdersResponse());
        return ordersResponses;

    }

}
