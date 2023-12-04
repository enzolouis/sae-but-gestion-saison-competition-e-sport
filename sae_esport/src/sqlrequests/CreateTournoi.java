package sqlrequests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTournoi {

	public static void main(String[] args) {
				
		String dirProjetJava = System.getProperty("user.dir");
		System.setProperty("derby.system.home", dirProjetJava+"/BDDSAEEsport");
				
		try {
			
			//connexion
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			String urlConnexion = "jdbc:derby:BDDSAEEsport;create=true";
			Connection dbConnection = DriverManager.getConnection(urlConnexion);
			System.out.println("Connexion ok");
			
			//création de la séquence de l'id tournoi				
			String reqSeqTournoi = "CREATE OR REPLACE SEQUENCE seqIdTournoi START WITH 1 INCREMENT BY 1";
			PreparedStatement stSeqTournoi = dbConnection.prepareStatement(reqSeqTournoi);
			stSeqTournoi.executeUpdate();
			System.out.println("Séquence tournoi créée");
			
			//création de la table tournoi
			String reqCreateTournoi = "CREATE OR REPLACE TABLE tournoi ("
					+ "idTournoi INT PRIMARY KEY NOT NULL,"
					+ "nom VARCHAR(30),"
					+ "dateDebut DATE,"
					+ "dateFin DATE,"
					+ "notoriete VARCHAR(30),"
					+ "idVainqueur INT,"
					+ "ouvert VARCHAR(30))";
			PreparedStatement stCreateTournoi = dbConnection.prepareStatement(reqCreateTournoi);
			stCreateTournoi.executeUpdate();
			System.out.println("Table tournoi créée");
					
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
