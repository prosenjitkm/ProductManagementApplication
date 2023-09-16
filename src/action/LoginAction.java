package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.LoginDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pojo.LoginInfo;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	// Initialize Log4j2 logger
	private static final Logger logger = LogManager.getLogger(LoginAction.class);
	
	// Represents the entered username for login.
	private String userName;
	
	// Represents the entered password for login.
	private String password;

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
			if (LoginDAO.isUserValid(new LoginInfo(userName, password))) {
				logger.info("User authenticated successfully.");
				return SUCCESS;
			} else {
				// If credentials are invalid, set an error message.
				addActionError("Invalid username or password.");
				logger.warn("Invalid username or password entered.");
				return INPUT;
			}
		}
		catch (Exception e) {
			// If any exception occurs, log it and set an error message.
			logger.error("An exception occurred during the authentication process: ", e);
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
		if (userName == null || userName.trim().isEmpty()) {
			addFieldError("userName", "Username cannot be empty.");
		}

		if (password == null || password.trim().isEmpty()) {
			addFieldError("password", "Password cannot be empty.");
		}
	}
}
