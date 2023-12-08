package sqlrequests;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import classes.DBConnection;

public class CreateParticipation {

	public static void main(String[] args) {

		try {
			//création de la séquence de l'id tournoi				
			String reqSeqParticiper = "CREATE OR REPLACE SEQUENCE seqParticipation START WITH 1 INCREMENT BY 1";
			PreparedStatement stSeqParticiper = DBConnection.getInstance().prepareStatement(reqSeqParticiper);
			stSeqParticiper.executeUpdate();
			System.out.println("Séquence tournoi créée");
			
			//creation de la table participation
			String reqCreateParticipation = "CREATE OR REPLACE TABLE participation"
					+ "(resultat INT,"
					+ "idTournoi INT,"
					+ "idEquipe INT,"
					+ "PRIMARY KEY (idTournoi, idEquipe),"
					+ "FOREIGN KEY (idTournoi) REFERENCES Tournoi(idTournoi),"
					+ "FOREIGN KEY (idEquipe) REFERENCES Equipe(idEquipe))";
			
			PreparedStatement stCreateParticipation = DBConnection.getInstance().prepareStatement(reqCreateParticipation);
			stCreateParticipation.executeUpdate();
			System.out.println("Table participation créée");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
