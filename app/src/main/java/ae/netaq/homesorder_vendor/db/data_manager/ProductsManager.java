package ae.netaq.homesorder_vendor.db.data_manager;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import ae.netaq.homesorder_vendor.db.data.ProductsRepository;
import ae.netaq.homesorder_vendor.db.tables.ImageTable;
import ae.netaq.homesorder_vendor.db.tables.ProductTable;
import ae.netaq.homesorder_vendor.network.model.ResponseAddProduct;

/**
 * Created by Sabih Ahmed on 04-Dec-17.
 */

public class ProductsManager {

    public static List<ProductTable> getAllProducts(Context context) throws SQLException {
        ProductsRepository productsRepository = new ProductsRepository(context);
        List<ProductTable> persistedproducts = productsRepository.getAllProducts();

        productsRepository.relase();

        return persistedproducts;
    }

    public static void insertRemoteProduct(Context context,ResponseAddProduct.Product product)
                                           throws Exception {
        ProductsRepository productsRepository = new ProductsRepository(context);

        ProductTable productToPersist = new ProductTable();
        productToPersist.setProductID(product.getProductID());
        productToPersist.setParentCategoryID(Integer.valueOf(product.getMainCategoryID().get(0)));

        productToPersist.setParentCategoryNameEN(product.getMainCategorynameEN().get(0));
        productToPersist.setParentCategoryNameAR(product.getMainCategorynameAR().get(0));

        int targetGroupID = -1;
        try {
            targetGroupID = Integer.valueOf(product.getTargetGroupID().get(0));
        }catch (Exception exc){

        }

        productToPersist.setTargetGroup(targetGroupID);

        int subCategoryID = -1;
        try {
            productToPersist.setSubCategoryID(Integer.valueOf(product.getSubCategoryID().get(0)));
        }catch (Exception exc){

        }

        productToPersist.setSubCategoryID(subCategoryID);

        String subCategoryAR="";
        try {
            productToPersist.setSubCategoryNameAR(product.getSubCategoryNameAR().get(0));
        }catch (Exception exc){

        }

        productToPersist.setSubCategoryNameAR(subCategoryAR);

        String subCategoryEN = "";

        try {
            productToPersist.setSubCategoryNameEN(product.getSubCategoryNameEn().get(0));
        }catch (Exception exc){

        }

        productToPersist.setSubCategoryNameEN(subCategoryEN);

        productToPersist.setProductNameEN(product.getProductNameEN());
        productToPersist.setProductNameAR(product.getProductNameAR());
        productToPersist.setPerDayOrderLimit(Integer.valueOf(product.getPerDayOrderLimit()));
        productToPersist.setHandlingTime(Integer.valueOf(product.getHandlingTime()));
        productToPersist.setProductPrice(Double.valueOf(product.getPrice()));
        productToPersist.setDescriptionAR(product.getDescriptionAR());
        productToPersist.setDescriptionEN(product.getDescriptionEN());

        String productColor = "";

        try {
            productColor = product.getColor().get(0);
        }catch (Exception exc){
            productColor = "";
        }

        productToPersist.setColor(productColor);

        String size = "";

        try {
            size = product.getSize().get(0);
        }catch (Exception exc){
            size = "";
        }

        productToPersist.setSize(size);

        productsRepository.createProduct(productToPersist);

        //Local URI Array, to be replaced with absolute URLs of backend
        List<String > productImagePaths = product.getMedia();
        for(String  url: productImagePaths){
            ImageTable productImage = new ImageTable();
            productImage.setProductID(product.getProductID());
            productImage.setImage(null);
            productImage.setImageURI(url);

            productsRepository.insertProductImages(productImage);
        }

        productsRepository.relase();
    }

    public static void updateExistingProduct(Context context,ResponseAddProduct.Product product){
        ProductsRepository productsRepository = new ProductsRepository(context);

        try {

            ProductTable productToUpdate = productsRepository.getProductById(product.getProductID());

            productToUpdate.setParentCategoryID(Integer.valueOf(product.getMainCategoryID().get(0)));

            productToUpdate.setParentCategoryNameEN(product.getMainCategorynameEN().get(0));
            productToUpdate.setParentCategoryNameAR(product.getMainCategorynameAR().get(0));

            int targetGroupID = -1;
            try {

                targetGroupID = Integer.valueOf(product.getTargetGroupID().get(0));

            }catch (Exception exc){

            }

            productToUpdate.setTargetGroup(targetGroupID);

            productToUpdate.setSubCategoryID(Integer.valueOf(product.getSubCategoryID().get(0)));
            productToUpdate.setSubCategoryNameAR(product.getSubCategoryNameAR().get(0));
            productToUpdate.setSubCategoryNameEN(product.getSubCategoryNameEn().get(0));
            productToUpdate.setProductNameEN(product.getProductNameEN());
            productToUpdate.setProductNameAR(product.getProductNameAR());
            productToUpdate.setPerDayOrderLimit(Integer.valueOf(product.getPerDayOrderLimit()));
            productToUpdate.setHandlingTime(Integer.valueOf(product.getHandlingTime()));
            productToUpdate.setProductPrice(Double.valueOf(product.getPrice()));
            productToUpdate.setDescriptionAR(product.getDescriptionAR());
            productToUpdate.setDescriptionEN(product.getDescriptionEN());

            String productColor = "";

            try {
                productColor = product.getColor().get(0);
            }catch (Exception exc){
                productColor = "";
            }

            productToUpdate.setColor(productColor);

            String size = "";

            try {
                size = product.getSize().get(0);
            }catch (Exception exc){
                size = "";
            }
            productToUpdate.setSize(size);

            productsRepository.update(productToUpdate);

            List<ImageTable> imageTables = productsRepository.getProductImages(product.getProductID());

            if(!imageTables.isEmpty()){
                for(ImageTable previousImage: imageTables){
                    productsRepository.deleteProductImages(previousImage);
                }
            }

            //Images with Absolute paths
            List<String > productImagePaths = product.getMedia();
            for(String  url: productImagePaths){
                ImageTable productImage = new ImageTable();
                productImage.setProductID(product.getProductID());
                productImage.setImage(null);
                productImage.setImageURI(url);

                productsRepository.insertProductImages(productImage);

            }


        } catch (SQLException e) {

        }


        productsRepository.relase();
    }
}
