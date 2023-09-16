package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.LoginDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import pojo.LoginInfo;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Log4j2
public class LoginAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private LoginInfo loginInfo;

	/**
	 * Executes the login action.
	 * It validates the credentials with the LoginDAO, and 
	 * returns a corresponding result based on the validation outcome.
	 * 
	 * @return SUCCESS if the credentials are valid, otherwise ERROR.
	 */
	public String execute() {
		try {
			// Validate the credentials using the DAO.
			if (LoginDAO.isUserValid(loginInfo)) {
				log.info("User authenticated successfully.");
				return SUCCESS;
			} else {
				// If credentials are invalid, set an error message.
				addActionError("Invalid username or password.");
				log.warn("Invalid username or password entered.");
				return INPUT;
			}
		}
		catch (Exception e) {
			// If any exception occurs, log it and set an error message.
			log.error("An exception occurred during the authentication process: ", e);
			addActionError("An unexpected error occurred. Please try again later.");
			return INPUT;
		}
	}
	
	/**
	 * Validates the provided fields for correctness.
	 * It checks if the username and password are non-empty.
	 * Any validation errors are added to the action.
	 */
	@Override
	public void validate() {
	    if (loginInfo == null) {
	        addFieldError("loginInfo", "Login information is missing.");
	        return; // Return to avoid potential NullPointerExceptions in subsequent checks.
	    }
	    if (loginInfo.getUserName() == null || loginInfo.getUserName().trim().isEmpty()) {
	        addFieldError("userName", "Username cannot be empty.");
	    }

	    if (loginInfo.getPassword() == null || loginInfo.getPassword().trim().isEmpty()) {
	        addFieldError("password", "Password cannot be empty.");
	    }
	}
}
