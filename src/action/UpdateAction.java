package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.ProductManagementDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import pojo.Product;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Log4j2
public class UpdateAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    
    private Product product = new Product();

    public String execute() {
        log.info("Entering execute() method of UpdateAction");
        String statusCode = "";

        int recordUpdated = ProductManagementDAO.updateProduct(product);

        if (recordUpdated == 1) {
        	log.info("Product record successfully updated.");
            statusCode = SUCCESS;
        } else {
        	log.warn("Failed to update product record.");
            statusCode = ERROR;
        }

        log.info("Exiting execute() method of UpdateAction with status: " + statusCode);
        return statusCode;
    }
}
