package DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class testDAOAdministrateur {
	private AdministrateurDAO adminDAO;
	private Connection connectionDriverManager;
	
	public testDAOAdministrateur() throws SQLException {
		DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
		String urlConnexion = "jdbc:derby:BDDSAEEsport;create=true";
		this.connectionDriverManager = DriverManager.getConnection(urlConnexion);
		this.adminDAO = new AdministrateurDAO(connectionDriverManager);
	}
	
	public boolean testGetAllAdministrateur() throws Exception {
		return this.adminDAO.getAll().size() > 0;
	}
}
