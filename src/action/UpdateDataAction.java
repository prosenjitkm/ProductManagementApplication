package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.ProductManagementDAO;
import lombok.*;
import pojo.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateDataAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LogManager.getLogger(UpdateDataAction.class);

    private String productId;
    private String productName;
    private String productCategory;
    private Integer productPrice;

    public String execute() {
        try {
            Product product = ProductManagementDAO.getProductById(productId);
            if (product != null) {
                productId = product.getProductId();
                productName = product.getProductName();
                productCategory = product.getProductCategory();
                productPrice = product.getProductPrice();
                logger.info("Product fetched successfully.");
                return SUCCESS;
            } else {
                logger.error("Product with the given ID not found.");
                return ERROR;
            }
        } catch (Exception e) {
            logger.error("Exception occurred: ", e);
            return ERROR;
        }
    }
}