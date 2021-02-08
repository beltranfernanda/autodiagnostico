package co.edu.autodiagnostico.persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	
	public static Connection conn;
	static {
//		ResourceBundle databaseProps = ResourceBundle.getBundle("propertiesDB");
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/autodiagnostico?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root","mafe1997");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
		    System.out.println("SQLException: " + e.getMessage());
		}
	}
	

	/**
	 * Private constructor using Singleton pattern
	 */
	private ConnectionDB () {
		
	}
	
	/**
	 * Obtain conn object
	 * @author maria.velandia
	 * @return conn
	 */
	public static Connection getConn() {
		return conn;
	}
	
	
	

}
