package dbutil;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class for managing database connections.
 * This class provides methods to establish a connection to the database and close it.
 */
public class DBUtil {
	
    // Initialize Log4j2 logger
	private static final Logger logger = LogManager.getLogger(DBUtil.class);

	// The path to the database configuration file.
	private static final String DB_CONFIG_FILE = "/dbconfig.properties";
	
	/**
	 * Establishes and returns a connection to the database.
	 * 
	 * @return A Connection object connected to the database.
	 */
	public static Connection getConnection()
	{
		Connection connection = null;
		
		// Try-with-resources to auto-close the InputStream, ensuring resource management.
		try (InputStream inputStream = DBUtil.class.getResourceAsStream(DB_CONFIG_FILE)){
			
			// Load the database connection properties from the configuration file.
            Properties properties = new Properties();
            properties.load(inputStream);

            // Retrieve properties (DB credentials and JDBC details).
            String driver = properties.getProperty("jdbc.driver");
            String url = properties.getProperty("jdbc.url");
            String username = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");

            // Load and register the JDBC driver.
            Class.forName(driver);

            // Establish and return the connection to the database using the retrieved properties.
            connection = DriverManager.getConnection(url, username, password);
            logger.info("Database connection established successfully.");
		
		}catch (Exception e) {
			// Log any exception that arises during the connection establishment.
			logger.error("An exception occurred while establishing the database connection: ", e);
		}
		
		return connection;
	}
	
	/**
	 * Closes the provided database connection.
	 * 
	 * @param connection The Connection object to be closed.
	 */
	public static void closeConnection (Connection connection)
	{
		try {
			// Check if the connection is non-null and still open.
			if (connection != null && !connection.isClosed()) {
				// Close the connection.
				connection.close();
				logger.info("Database connection closed successfully.");
			}
		} catch (Exception e) {
			// Log any exception that arises during the connection closure.
			logger.error("An exception occurred while closing the database connection: ", e);
		}
	}
}
