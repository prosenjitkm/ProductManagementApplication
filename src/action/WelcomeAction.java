package action;

import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import dao.ProductManagementDAO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import pojo.Product;

/**
 * This class represents the action taken when the user lands on the Welcome page.
 * It interacts with the ProductManagementDAO to retrieve a list of products,
 * and prepares them to be displayed on the Welcome page.
 * 
 * The class uses Lombok annotations for getter and setter boilerplate code.
 */
@Getter
@Setter
@Log4j2
public class WelcomeAction extends ActionSupport {
    
    private static final long serialVersionUID = 1L;
    
    // List to store the products that will be displayed on the Welcome page.
    private List<Product> products;
    
    /**
     * Executes the welcome action.
     * Retrieves the list of products from the database and prepares them 
     * to be displayed on the Welcome page.
     * 
     * @return SUCCESS if the products are retrieved successfully, otherwise ERROR.
     */
    public String execute() {
        try {
            log.info("Executing welcome action");
            initializeProducts();
        } catch(Exception e) {
            // Log the error and return the ERROR status.
        	log.error("An exception occurred while executing the welcome action: ", e);
            return ERROR;
        }
        return SUCCESS;
    }
    
    /**
     * Fetches the list of all products from the database using the ProductManagementDAO.
     * The retrieved products are stored in the products list.
     */
    public void initializeProducts() {
    	log.info("Initializing product list");
        products = ProductManagementDAO.getAllProducts();
    }
}
