package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static DBConnection instance;
	private Connection con;
	
	private DBConnection() {
		String dirProjetJava = System.getProperty("user.dir");
		System.setProperty("derby.system.home", dirProjetJava+"/BDDSAEEsport");
		
		try {
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String urlConnexion = "jdbc:derby:BDDSAEEsport;create=true";
		
		try {
			this.con = DriverManager.getConnection(urlConnexion);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static synchronized DBConnection getInstance()
	{
		if (instance == null) {
			instance = new DBConnection();
		}
		
		return instance;
	}

	
	
}