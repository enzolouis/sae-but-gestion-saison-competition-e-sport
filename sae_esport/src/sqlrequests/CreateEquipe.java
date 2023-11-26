package sqlrequests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateEquipe {
	
public static void main(String[] args) {
		
		String dirProjetJava = System.getProperty("user.dir");
		System.setProperty("derby.system.home", dirProjetJava+"/BDDSAEEsport");
		
		try {
			
			//connexion
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			String urlConnexion = "jdbc:derby:BDDSAEEsport;create=true";
			Connection dbConnection = DriverManager.getConnection(urlConnexion);
			System.out.println("Connexion ok");
			
			//création de la séquence de l'identifiant d'équioe
			String reqSeqEquipe = "CREATE SEQUENCE seqIdEquipe START WITH 1 INCREMENT BY 1";
			PreparedStatement stSeqEquipe = dbConnection.prepareStatement(reqSeqEquipe);
			stSeqEquipe.executeUpdate();
			System.out.println("Séquence equipe créée");
			String reqCreateEquipe = "CREATE TABLE equipe"
					+ "(idEquipe INT PRIMARY KEY NOT NULL,"
					+ "nom VARCHAR(50),"
					+ "nationalite VARCHAR(50),"
					+ "rangSaisonPrecedente INT,"
					+ "pointsSaison INT,"
					+ "disposee VARCHAR(30))";
			PreparedStatement stCreateEquipe = dbConnection.prepareStatement(reqCreateEquipe);
			stCreateEquipe.executeUpdate();
			System.out.println("Table equipe créée");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
