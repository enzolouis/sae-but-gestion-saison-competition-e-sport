package modeles;

import java.sql.Connection;

public class ChoixArbitreModele {
	private Connection dbConnection;
	
	public ChoixArbitreModele(Connection dbConnection) throws Exception {
		this.dbConnection = dbConnection;
	}
}
