package org.cinematics.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {

	private static Connection connection;
	
	//THESE VALUES HAVE TO BE MODIFIED FOR YOUR DATABASE 
	private static String driverName = "org.postgresql.Driver";
	private static String dbAdress = "jdbc:postgresql://127.0.0.1:5432/postgres";
	private static String userName = "elev";
	private static String password = "";
	
	public static Connection getConnection() {

		if(!isConnectionOpen()) {
			if(openConnection()) {
				return connection;
			}
		}
		return null;
	}
	
	public static boolean closeConnection() {
		try {
			if(connection != null) {
				connection.close();
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}
	
	private static boolean openConnection() {
		try {
            Class.forName(driverName);
        }

        catch (ClassNotFoundException e) {
            System.err.println(e);
            return false;
        }	
		
		try {
			connection = DriverManager.getConnection(dbAdress, userName, password);
			return true;
			
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}
	
	private static boolean isConnectionOpen() {
		if(connection == null) {
			return false;
		}
		try {
			if(connection.isClosed()) {
				return false;
			}
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
		return true;
	}
	
	
}
