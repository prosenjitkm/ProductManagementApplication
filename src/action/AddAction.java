package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.ProductManagementDAO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pojo.Product;

@Getter
@Setter
@ToString
public class AddAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    // Log4j2 logger
    private static final Logger logger = LogManager.getLogger(AddAction.class);

    // Product details
    private String productId;
    private String productName;
    private String productCategory;
    private Integer productPrice;

    /**
     * Executes the action to add a product.
     *
     * @return The result of the action. Returns "success" if the product is added successfully, otherwise returns "error".
     */
    public String execute() {
        try {
            // Create a new Product object from the provided fields
            Product product = createProduct();

            // Log the product details
            logProductDetails(product);

            // Add the product and evaluate the result
            if (addProduct(product)) {
                return SUCCESS;
            } else {
                addActionError("Product couldn't be added. Please try again.");
                return ERROR;
            }

        } catch (Exception e) {
            // Log the exception and set an error message
            logger.error("An exception occurred while adding the product: ", e);
            addActionError("There was an error while adding the product. Please try again.");
            return ERROR;
        }
    }

    private Product createProduct() {
        return new Product(productId, productName, productCategory, productPrice);
    }

    private void logProductDetails(Product product) {
        logger.info("Adding product: {}", product);
    }

    private boolean addProduct(Product product) throws Exception {
        int recordAdded = ProductManagementDAO.addProduct(product);

        if (recordAdded == 1) {
            logger.info("Product successfully added");
            return true;
        } else {
            logger.error("Product could not be added");
            return false;
        }
    }
}
