package io.ztech.music.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBUtil {
	public static Logger logger = Logger.getLogger(DBUtil.class.getName());
	private Connection connection;

	public void setConnection() {
		try {
			InputStream input = getClass().getResourceAsStream("/io/ztech/music/properties/dbconfig.properties");
			Properties prop = new Properties();
			prop.load(input);
			Class.forName(prop.getProperty("driverClass"));
			this.connection = DriverManager.getConnection(prop.getProperty("connectionString"), prop.getProperty("username"), prop.getProperty("password"));

		} catch(FileNotFoundException e) {
			logger.warning("property file missing");
		} catch(IOException e) {
			logger.warning("couldn't read property file");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.log(Level.WARNING, "Error in making connection");
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Access error");
		} finally {
			
		}
	}

	public Connection getConnection() {
		this.setConnection();
		return this.connection;
	}

	public void closeConnection() {
		if (connection != null) {
			try {
				this.connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.log(Level.WARNING, "error in connection closing");
			}
		}
	}

}
