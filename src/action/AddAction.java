package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.ProductManagementDAO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import pojo.Product;

@Getter
@Setter
@ToString
@Log4j2
public class AddAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    
    private Product product;

    public String execute() {
        try {
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
            log.error("An exception occurred while adding the product: ", e);
            addActionError("There was an error while adding the product. Please try again.");
            return ERROR;
        }
    }

    private void logProductDetails(Product product) {
    	log.info("Adding product: {}", product);
    }

    private boolean addProduct(Product product) throws Exception {
        int recordAdded = ProductManagementDAO.addProduct(product);

        if (recordAdded == 1) {
        	log.info("Product successfully added");
            return true;
        } else {
        	log.error("Product could not be added");
            return false;
        }
    }
}
