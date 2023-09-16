package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dbutil.DBUtil;
import pojo.LoginInfo;
//Import Log4j classes.
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class LoginDAO {

    // Create the logger for this class.
    private static final Logger logger = LogManager.getLogger(LoginDAO.class);


    /**
     * This method validates if a user with the given details exists in the database.
     * 
     * @param userDetails Contains the username and password to validate.
     * @return Returns true if the user is valid; otherwise returns false.
     */
    public static boolean isUserValid(LoginInfo userDetails) {
        boolean validStatus = false;  // Status flag to indicate if the user is valid or not.
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
        	
        	logger.info("Attempting to validate user.");
        	
            // Get the database connection.
            connection = DBUtil.getConnection();
            
            // Prepare the SQL query to fetch user details.
            String sql = "SELECT * FROM login_info WHERE user_name = ? AND password = ?";
            preparedStatement = connection.prepareStatement(sql);
            
            // Set the username and password parameters.
            preparedStatement.setString(1, userDetails.getUserName());
            preparedStatement.setString(2, userDetails.getPassword());
            
            // Execute the query.
            resultSet = preparedStatement.executeQuery();
            
            // If resultSet has at least one row, then the user is valid.
            while(resultSet.next()) {
                validStatus = true;
            }
            
            logger.info("User validation complete.");

        } catch (Exception e) {
            // Log any exceptions that occur during the process.
            logger.error("An error occurred during user validation.", e);
            
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                DBUtil.closeConnection(connection);
            } catch (Exception e) {
                logger.error("An error occurred while closing resources.", e);
            }

            // Close connection.
            DBUtil.closeConnection(connection);
        }
        
        return validStatus;  // Return the validation status.
    }
}
