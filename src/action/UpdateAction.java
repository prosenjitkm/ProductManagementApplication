package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.ProductManagementDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pojo.Product;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    // Initialize Log4j2 logger
    private static final Logger logger = LogManager.getLogger(UpdateAction.class);

    private String productId;
    private String productName;
    private String productCategory;
    private Integer productPrice;

    public String execute() {
        logger.info("Entering execute() method of UpdateAction");
        String statusCode = "";
        Product product = new Product(productId, productName, productCategory, productPrice);
        int recordUpdated = ProductManagementDAO.updateProduct(product);

        if (recordUpdated == 1) {
            logger.info("Product record successfully updated.");
            statusCode = SUCCESS;
        } else {
            logger.warn("Failed to update product record.");
            statusCode = ERROR;
        }

        logger.info("Exiting execute() method of UpdateAction with status: " + statusCode);
        return statusCode;
    }
}
