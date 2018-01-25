package ae.netaq.homesorder_vendor.network.model;


import java.util.List;

/**
 * Created by sabih on 25-Jan-18.
 */

public class ResponseOrderList {

    public int orderID;
    public String orderStatus;
    public int orderTotal;
    public String payment_mode;
    public String orderDate;
    public String comments;
    public List<Customer> customer;
    public String currencycode;
    public List<Item> items;
    public String dueDate;
    public int amount_ordered;
    public int base_shipping_amount;
    public int base_amount_ordered;
    public int order_id;

    public int getOrderID() {
        return orderID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public int getOrderTotal() {
        return orderTotal;
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getComments() {
        return comments;
    }

    public List<Customer> getCustomer() {
        return customer;
    }

    public String getCurrencycode() {
        return currencycode;
    }

    public List<Item> getItems() {
        return items;
    }

    public String getDueDate() {
        return dueDate;
    }

    public int getAmount_ordered() {
        return amount_ordered;
    }

    public int getBase_shipping_amount() {
        return base_shipping_amount;
    }

    public int getBase_amount_ordered() {
        return base_amount_ordered;
    }

    public int getOrder_id() {
        return order_id;
    }

    public class Item {
        public int productID;
        public String productNameEN;
        public String productNameAR;
        public int price;
        public String perDayOrderLimit;
        public String handlingTime;
        public String descriptionEN;
        public String descriptionAR;
        public List<String> mainCategorynameEN;
        public List<String> mainCategorynameAR;
        public List<String> mainCategoryID;
        public List<String> targetedGroupNameEN;
        public List<String> targetedGroupNameAR;
        public List<String> targetGroupID;
        public List<String> subCategoryNameEn;
        public List<String> subCategoryNameAR;
        public List<String> subCategoryID;
        public List<String> media;
        public String created_at;
        public String updated_at;
        public int qty_ordered;
        public List<Option> options;
        public String comments;
        public int offerPrice;

        public int getProductID() {
            return productID;
        }

        public String getProductNameEN() {
            return productNameEN;
        }

        public String getProductNameAR() {
            return productNameAR;
        }

        public int getPrice() {
            return price;
        }

        public String getPerDayOrderLimit() {
            return perDayOrderLimit;
        }

        public String getHandlingTime() {
            return handlingTime;
        }

        public String getDescriptionEN() {
            return descriptionEN;
        }

        public String getDescriptionAR() {
            return descriptionAR;
        }

        public List<String> getMainCategorynameEN() {
            return mainCategorynameEN;
        }

        public List<String> getMainCategorynameAR() {
            return mainCategorynameAR;
        }

        public List<String> getMainCategoryID() {
            return mainCategoryID;
        }

        public List<String> getTargetedGroupNameEN() {
            return targetedGroupNameEN;
        }

        public List<String> getTargetedGroupNameAR() {
            return targetedGroupNameAR;
        }

        public List<String> getTargetGroupID() {
            return targetGroupID;
        }

        public List<String> getSubCategoryNameEn() {
            return subCategoryNameEn;
        }

        public List<String> getSubCategoryNameAR() {
            return subCategoryNameAR;
        }

        public List<String> getSubCategoryID() {
            return subCategoryID;
        }

        public List<String> getMedia() {
            return media;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public int getQty_ordered() {
            return qty_ordered;
        }

        public List<Option> getOptions() {
            return options;
        }

        public String getComments() {
            return comments;
        }

        public int getOfferPrice() {
            return offerPrice;
        }
    }


    public class Option {
        public String size;
        public String color;
        public String weight;

        public String getSize() {
            return size;
        }

        public String getColor() {
            return color;
        }

        public String getWeight() {
            return weight;
        }
    }

    public class Customer {
        public String type;
        public String customerID;
        public String name;
        public String email;
        public String address;
        public String phone;
        public String shippingNotes;

        public String getType() {
            return type;
        }

        public String getCustomerID() {
            return customerID;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getAddress() {
            return address;
        }

        public String getPhone() {
            return phone;
        }

        public String getShippingNotes() {
            return shippingNotes;
        }
    }

}
