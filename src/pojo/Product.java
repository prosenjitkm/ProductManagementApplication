package pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

/**
 * Represents a product entity.
 * This POJO contains attributes related to a specific product.
 */
@Getter          // Lombok annotation to generate getter methods for all fields
@Setter          // Lombok annotation to generate setter methods for all fields
@NoArgsConstructor  // Lombok annotation to generate a no-argument constructor
@ToString       // Lombok annotation to generate a toString method
@Log4j2
public class Product {

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
    private int productPrice;  // Using primitive int, but choose based on your requirements
    
    public Product(String productId, String productName, String productCategory, int productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
        log.info("Product object created: " + this);
    }
}
