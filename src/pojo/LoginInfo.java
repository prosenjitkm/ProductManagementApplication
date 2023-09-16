package pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents the login information of a user.
 * This POJO contains attributes related to user's login credentials.
 */
@Getter // Lombok annotation to generate getter methods for all fields
@Setter // Lombok annotation to generate setter methods for all fields
@AllArgsConstructor // Lombok annotation to generate a constructor with all arguments
@ToString // Lombok annotation to generate a toString method
public class LoginInfo {
	
	/** 
	 * Represents the username of a user.
	 * This can be an email, unique username or any other form of identification.
	 */
	private String userName;
	
	/** 
	 * Represents the password of a user.
	 * It is advisable to store a hashed version of the password for security reasons.
	 */
	private String password;
}
