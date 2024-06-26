package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static Connection instance;
	
	/*
	 * Initie la connexion à la base de données
	 * */
	private DBConnection() {
		
		String dirProjetJava = System.getProperty("user.dir");
		System.setProperty("derby.system.home", dirProjetJava+"/BDDSAEEsport");
		String urlConnexion = "jdbc:derby:BDDSAEEsport;create=true";
		
		try {
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			DBConnection.instance = DriverManager.getConnection(urlConnexion);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public static synchronized Connection getInstance()
	{
		if (instance == null) {
			new DBConnection();
		}
		
		return instance;
	}

	
	
}