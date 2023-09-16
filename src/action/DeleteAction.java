package action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.opensymphony.xwork2.ActionSupport;
import dao.ProductManagementDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pojo.Product;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 
@ToString
public class DeleteAction extends ActionSupport{
	
    private static final long serialVersionUID = 1L;

    // Initialize Log4j2 logger
    private static final Logger logger = LogManager.getLogger(DeleteAction.class);
    
    private String productId;
    private String productName;
    private String productCategory;
    private Integer productPrice;
	
	public String execute() {
		
        logger.info("Entering execute() method of DeleteAction");
        String statusCode = "";
        Product product = new Product(productId, productName, productCategory, productPrice);
        int recordDeleted = ProductManagementDAO.deleteProduct(product);
        
        
        if (recordDeleted == 1) {
            logger.info("Product record successfully deleted.");
            statusCode = SUCCESS;
        } else {
            logger.warn("Failed to delete product record.");
            statusCode = ERROR;
        }

        logger.info("Exiting execute() method of DeleteAction with status: " + statusCode);
        return statusCode;
	}
}
