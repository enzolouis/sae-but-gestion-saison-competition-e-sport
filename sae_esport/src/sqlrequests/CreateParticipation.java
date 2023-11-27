package sqlrequests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateParticipation {

	public static void main(String[] args) {

		String dirProjetJava = System.getProperty("user.dir");
		System.setProperty("derby.system.home", dirProjetJava+"/BDDSAEEsport");
		
		try {
			
			//connexion
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			String urlConnexion = "jdbc:derby:BDDSAEEsport;create=true";
			Connection dbConnection = DriverManager.getConnection(urlConnexion);
			System.out.println("Connexion ok");
			
			//creation de la table participation
			String reqCreateParticipation = "CREATE TABLE participation"
					+ "(resultat INT,"
					+ "idTournoi INT,"
					+ "idEquipe INT,"
					+ "PRIMARY KEY (idTournoi, idEquipe),"
					+ "FOREIGN KEY (idTournoi) REFERENCES Tournoi(idTournoi),"
					+ "FOREIGN KEY (idEquipe) REFERENCES Equipe(idEquipe))";
			
			PreparedStatement stCreateParticipation = dbConnection.prepareStatement(reqCreateParticipation);
			stCreateParticipation.executeUpdate();
			System.out.println("Table participation créée");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
