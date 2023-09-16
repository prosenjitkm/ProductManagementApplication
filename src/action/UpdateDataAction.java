package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.ProductManagementDAO;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import pojo.Product;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Log4j2
public class UpdateDataAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private Product product = new Product();

    public String execute() {
        try {
            Product fetchedProduct = ProductManagementDAO.getProductById(product.getProductId());
            if (fetchedProduct != null) {
                product.setProductId(fetchedProduct.getProductId());
                product.setProductName(fetchedProduct.getProductName());
                product.setProductCategory(fetchedProduct.getProductCategory());
                product.setProductPrice(fetchedProduct.getProductPrice());
                log.info("Product fetched successfully.");
                return SUCCESS;
            } else {
            	log.error("Product with the given ID not found.");
                return ERROR;
            }
        } catch (Exception e) {
        	log.error("Exception occurred: ", e);
            return ERROR;
        }
    }
}