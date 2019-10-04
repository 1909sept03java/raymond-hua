package com.revature.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	public static Connection getConnection() throws SQLException, IOException {
		Connection c = null;
		try {
			// read in contents of a properties file - NEVER want to hardcode credentials
			Properties prop = new Properties();
			// InputStream in = new FileInputStream("connection.properties");
			prop.load(ConnectionUtil.class.getClassLoader().getResourceAsStream("connection.properties"));
			// need to provide: url to the database, username, password
			Class.forName("oracle.jdbc.driver.OracleDriver");
			c = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"),
					prop.getProperty("password"));
		} catch (ClassNotFoundException ex) {
			System.out.println("Unable to load driver class!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return c;
	}
}