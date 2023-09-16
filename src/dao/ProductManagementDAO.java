package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbutil.DBUtil;
import lombok.extern.log4j.Log4j2;
import pojo.Product;

@Log4j2
public class ProductManagementDAO {
    
    public static Product getProductById(String productId) {
    	
        log.info("Attempting to get product by ID: {}", productId);
        Product product = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM product WHERE prod_id = ?";
        
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productId);
            resultSet = preparedStatement.executeQuery();
            
            // As we are querying by primary key, at most one product will be returned
            if (resultSet.next()) {
                product = new Product(
                    resultSet.getString("PROD_ID"),
                    resultSet.getString("PROD_NAME"),
                    resultSet.getString("PROD_CATEGORY"),
                    resultSet.getInt("PROD_PRICE")
                );
                log.info("Product found: {}", product);
            } else {
            	log.warn("No product found with ID: {}", productId);
            }
            
        } catch (SQLException e) {
        	log.error("Database operation failed: ", e);
        } catch (NullPointerException e) {
        	log.error("Null value encountered: ", e);
        } catch (Exception e) {
        	log.error("An unexpected error occurred: ", e);
        } finally {
            closeResources(connection, preparedStatement, resultSet);
        }
        
        return product;
    }
    
    public static int deleteProduct (Product product) {
    	
    	log.info("Attempting to delete product: {}", product);
    	
    	int status = 0;
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	
    	String sql = "DELETE FROM product WHERE PROD_ID = ?";
    	
    	try {
    		connection = DBUtil.getConnection();
    		preparedStatement = connection.prepareStatement(sql);
    		preparedStatement.setString(1, product.getProductId());
    		status = preparedStatement.executeUpdate();
    		
            if(status == 1) {
            	log.info("Product deleted successfully.");
            } else {
            	log.warn("No product found with ID: {}", product.getProductId());
            }
	    } catch (SQLException e) {
	    	log.error("Database operation failed: ", e);
	    } catch (NullPointerException e) {
	    	log.error("Null value encountered: ", e);
	    } catch (Exception e) {
	    	log.error("An unexpected error occurred: ", e);
	    } finally {
	        // Ensure resources are closed
	        closeResources(connection, preparedStatement, null);
	    }
    	
    	return status;
    }
    
    public static int updateProduct(Product product) {

    	log.info("Attempting to update product: {}", product);
        
        int status = 0; // Indicates the number of updated rows
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        // Reorder the query to match setProductToPreparedStatement
        String sql = "UPDATE product SET prod_name = ?, prod_category = ?, prod_price = ? WHERE prod_id = ?";
        
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            // Use helper method to set product data into PreparedStatement
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductCategory());
            preparedStatement.setInt(3, product.getProductPrice());
            preparedStatement.setString(4, product.getProductId());

            // Execute update
            status = preparedStatement.executeUpdate();
            
            if(status == 1) {
            	log.info("Product updated successfully.");
            } else {
            	log.warn("No product found with ID: {}", product.getProductId());
            }
            
        } catch (SQLException e) {
        	log.error("Database operation failed: ", e);
        } catch (NullPointerException e) {
        	log.error("Null value encountered: ", e);
        } catch (Exception e) {
        	log.error("An unexpected error occurred: ", e);
        } finally {
            // Ensure resources are closed
            closeResources(connection, preparedStatement, null);
        }
        
        return status;
    }

    
    public static int addProduct(Product product) {
    	
    	log.info("Attempting to add product: " + product);
    	int status = 0;
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	String sql = "INSERT INTO product VALUES(?,?,?,?)";
	  
    	try {
    		connection = DBUtil.getConnection();
    		preparedStatement = connection.prepareStatement(sql);
    		
    		log.info("Setting product details to PreparedStatement.");
            
            preparedStatement.setString(1, product.getProductId());
            log.debug("Set product ID to: {}", product.getProductId());
            
            preparedStatement.setString(2, product.getProductName());
            log.debug("Set product name to: {}", product.getProductName());
            
            preparedStatement.setString(3, product.getProductCategory());
            log.debug("Set product category to: {}", product.getProductCategory());
            
            preparedStatement.setInt(4, product.getProductPrice());
            log.debug("Set product price to: {}", product.getProductPrice());
            
    		status = preparedStatement.executeUpdate();
		  
    		log.info("Product added successfully with status code: " + status);}
    	
    	catch (SQLException e){
    		log.error("Database operation failed: ", e); }
    	catch (NullPointerException e){
    		log.error("Null value encountered: ", e);}
    	catch (Exception e){
    		log.error("An unexpected error occurred: ", e); }
    	
    	finally {
    		closeResources(connection, preparedStatement, null); }
    	
    	return status;
    }
	 

    public static List<Product> getAllProducts() {
    	log.info("Fetching all products.");

        List<Product> productList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            String sql = "SELECT * FROM product";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product(
                    resultSet.getString("PROD_ID"),
                    resultSet.getString("PROD_NAME"),
                    resultSet.getString("PROD_CATEGORY"),
                    resultSet.getInt("PROD_PRICE"));
                
                productList.add(product);
            }
            
            log.info("Fetched all products successfully.");

        } catch (SQLException e) {
        	log.error("Database operation failed: ", e);
        } catch (Exception e) {
        	log.error("An unexpected error occurred: ", e);
        } finally {
            closeResources(connection, preparedStatement, resultSet);
        }

        return productList;
    }

    private static void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();

            log.info("Database resources closed successfully.");

        } catch (Exception e) {
        	log.error("Failed to close resources: ", e);
        }
    }
}
