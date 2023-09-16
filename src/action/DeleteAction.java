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
public class DeleteAction extends ActionSupport{
	
    private static final long serialVersionUID = 1L;

    private Product product;
	
	public String execute() {
		
        log.info("Entering execute() method of DeleteAction");
        String statusCode = "";
        
        // Use the product instance variable instead of creating a new empty Product
        int recordDeleted = ProductManagementDAO.deleteProduct(product);
        
        
        if (recordDeleted == 1) {
        	log.info("Product record successfully deleted.");
            statusCode = SUCCESS;
        } else {
        	log.warn("Failed to delete product record.");
            statusCode = ERROR;
        }

        log.info("Exiting execute() method of DeleteAction with status: " + statusCode);
        return statusCode;
	}
}
