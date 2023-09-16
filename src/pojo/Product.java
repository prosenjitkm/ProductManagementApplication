package pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Import Log4j classes.
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents a product entity.
 * This POJO contains attributes related to a specific product.
 */
@Getter          // Lombok annotation to generate getter methods for all fields
@Setter          // Lombok annotation to generate setter methods for all fields
@NoArgsConstructor  // Lombok annotation to generate a no-argument constructor
@ToString       // Lombok annotation to generate a toString method
public class Product {
	
    // Create the logger for this class.
    private static final Logger logger = LogManager.getLogger(Product.class);

    // Your fields go here...
	
    /** 
     * Represents the unique identifier for the product.
     * Typically used for database operations and to distinguish products.
     */
    private String productId;

    /** 
     * Represents the name of the product.
     * This is typically displayed to end-users.
     */
    private String productName;

    /** 
     * Represents the category to which the product belongs.
     * Helps in categorizing products for better organization and search.
     */
    private String productCategory;

    /** 
     * Represents the price of the product.
     * This value can be displayed to end-users and used in transactional operations.
     */
    private Integer productPrice;
    
    // Use logging in constructor to trace object creation.
    public Product(String productId, String productName, String productCategory, Integer productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
        logger.info("Product object created: " + this.toString());
    }
}
