package sqlrequests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateMatch {
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
			String reqSeqArbitre = "CREATE SEQUENCE seqIdMatch START WITH 1 INCREMENT BY 1";
			PreparedStatement stSeqJoueur = dbConnection.prepareStatement(reqSeqArbitre);
			stSeqJoueur.executeUpdate();
			System.out.println("Séquence arbitre créée");
			
			//création de la table arbitre
			String reqCreateJoueur = "CREATE TABLE match ("
					+ "idMatch INT PRIMARY KEY NOT NULL,"
					+ "finale LOGICAL"
					+ "idEquipe INT NOT NULL"
					+ "FIREIGN KEY(IdEquipe) REFERENCES Equipe(idEquipe)";
			PreparedStatement stCreateJoueur= dbConnection.prepareStatement(reqCreateJoueur);
			stCreateJoueur.executeUpdate();
			System.out.println("Table joueur créée");
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
