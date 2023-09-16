package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbutil.DBUtil;
import pojo.Product;

// Import the Log4j classes
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductManagementDAO {
	
	// Initialize a logger for this class
    private static final Logger logger = LogManager.getLogger(ProductManagementDAO.class);
    
    public static Product getProductById(String productId) {
    	
        logger.info("Attempting to get product by ID: {}", productId);
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
                logger.info("Product found: {}", product);
            } else {
                logger.warn("No product found with ID: {}", productId);
            }
            
        } catch (SQLException e) {
            logger.error("Database operation failed: ", e);
        } catch (NullPointerException e) {
            logger.error("Null value encountered: ", e);
        } catch (Exception e) {
            logger.error("An unexpected error occurred: ", e);
        } finally {
            closeResources(connection, preparedStatement, resultSet);
        }
        
        return product;
    }
    
    public static int deleteProduct (Product product) {
    	
    	logger.info("Attempting to delete product: {}", product);
    	
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
                logger.info("Product deleted successfully.");
            } else {
                logger.warn("No product found with ID: {}", product.getProductId());
            }
	    } catch (SQLException e) {
	        logger.error("Database operation failed: ", e);
	    } catch (NullPointerException e) {
	        logger.error("Null value encountered: ", e);
	    } catch (Exception e) {
	        logger.error("An unexpected error occurred: ", e);
	    } finally {
	        // Ensure resources are closed
	        closeResources(connection, preparedStatement, null);
	    }
    	
    	return status;
    }
    
    public static int updateProduct(Product product) {

        logger.info("Attempting to update product: {}", product);
        
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
                logger.info("Product updated successfully.");
            } else {
                logger.warn("No product found with ID: {}", product.getProductId());
            }
            
        } catch (SQLException e) {
            logger.error("Database operation failed: ", e);
        } catch (NullPointerException e) {
            logger.error("Null value encountered: ", e);
        } catch (Exception e) {
            logger.error("An unexpected error occurred: ", e);
        } finally {
            // Ensure resources are closed
            closeResources(connection, preparedStatement, null);
        }
        
        return status;
    }

    
    public static int addProduct(Product product) {
    	
    	logger.info("Attempting to add product: " + product);
    	int status = 0;
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	String sql = "INSERT INTO product VALUES(?,?,?,?)";
	  
    	try {
    		connection = DBUtil.getConnection();
    		preparedStatement = connection.prepareStatement(sql);
    		
            logger.info("Setting product details to PreparedStatement.");
            
            preparedStatement.setString(1, product.getProductId());
            logger.debug("Set product ID to: {}", product.getProductId());
            
            preparedStatement.setString(2, product.getProductName());
            logger.debug("Set product name to: {}", product.getProductName());
            
            preparedStatement.setString(3, product.getProductCategory());
            logger.debug("Set product category to: {}", product.getProductCategory());
            
            preparedStatement.setInt(4, product.getProductPrice());
            logger.debug("Set product price to: {}", product.getProductPrice());
            
    		status = preparedStatement.executeUpdate();
		  
    		logger.info("Product added successfully with status code: " + status);}
    	
    	catch (SQLException e){
    			logger.error("Database operation failed: ", e); }
    	catch (NullPointerException e){
    		logger.error("Null value encountered: ", e);}
    	catch (Exception e){
    		logger.error("An unexpected error occurred: ", e); }
    	
    	finally {
    		closeResources(connection, preparedStatement, null); }
    	
    	return status;
    }
	 

    public static List<Product> getAllProducts() {
        logger.info("Fetching all products.");

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
            
            logger.info("Fetched all products successfully.");

        } catch (SQLException e) {
            logger.error("Database operation failed: ", e);
        } catch (Exception e) {
            logger.error("An unexpected error occurred: ", e);
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

            logger.info("Database resources closed successfully.");

        } catch (Exception e) {
            logger.error("Failed to close resources: ", e);
        }
    }
}
