package sqlrequests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateArbitre {

	public static void main(String[] args) {

		String dirProjetJava = System.getProperty("user.dir");
		System.setProperty("derby.system.home", dirProjetJava+"/BDDSAEEsport");
		
		try {
			
			//connexion
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			String urlConnexion = "jdbc:derby:BDDSAEEsport;create=true";
			Connection dbConnection = DriverManager.getConnection(urlConnexion);
			System.out.println("Connexion ok");
			
			//création de la séquence de l'identifiant arbitre
			String reqSeqArbitre = "CREATE OR REPLACE SEQUENCE seqIdArbitre START WITH 1 INCREMENT BY 1";
			PreparedStatement stSeqArbitre = dbConnection.prepareStatement(reqSeqArbitre);
			stSeqArbitre.executeUpdate();
			System.out.println("Séquence arbitre créée");
			
			//création de la table arbitre
			String reqCreateArbitre = "CREATE OR REPLACE TABLE arbitre ("
					+ "idArbitre INT PRIMARY KEY NOT NULL,"
					+ "nom VARCHAR(50),"
					+ "prenom VARCHAR(50),"
					+ "nationalite VARCHAR(50))";
			PreparedStatement stCreateArbitre = dbConnection.prepareStatement(reqCreateArbitre);
			stCreateArbitre.executeUpdate();
			System.out.println("Table arbitre créée");
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
