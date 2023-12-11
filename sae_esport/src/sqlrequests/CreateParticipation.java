package sqlrequests;

import java.sql.SQLException;
import java.sql.Statement;

import classes.DBConnection;

public class CreateParticipation {
	
	public static void main(String[] args) {
		CreateParticipation.Drop();
		CreateParticipation.Create();
	}
	
	public static void Drop() {
		try {
		    // Suppression de la table participation
		    Statement stSupprParticipation = DBConnection.getInstance().createStatement();
		    stSupprParticipation.executeUpdate("DROP TABLE participation");
		    System.out.println("✔ Suppression de la table participation réussie.");
		} catch (SQLException e) {
		    System.out.println("❌ Suppression de la table participation échouée.");
		}

		try {
		    // Suppression de la séquence seqParticipation
		    Statement stSupprSeqParticipation = DBConnection.getInstance().createStatement();
		    stSupprSeqParticipation.executeUpdate("DROP SEQUENCE seqParticipation RESTRICT");
		    System.out.println("✔ Suppression de la séquence seqParticipation réussie.");
		} catch (SQLException e) {
		    System.out.println("❌ Suppression de la séquence seqParticipation échouée.");
		}
	}
	
	public static void Create() {
		try {
		    // Création de la séquence de l'id tournoi
		    Statement stSeqParticipation = DBConnection.getInstance().createStatement();
		    stSeqParticipation.executeUpdate("CREATE SEQUENCE seqParticipation START WITH 1 INCREMENT BY 1");
		    System.out.println("✔ Création de la séquence seqParticipation réussie.");
		} catch (SQLException e) {
		    System.out.println("❌ Création de la séquence seqParticipation échouée.");
		}

		try {
		    // Création de la table participation
		    Statement stCreateParticipation = DBConnection.getInstance().createStatement();
		    stCreateParticipation.executeUpdate("CREATE TABLE participation"
		            + "(resultat INT,"
		            + "idTournoi INT,"
		            + "idEquipe INT,"
		            + "PRIMARY KEY (idTournoi, idEquipe),"
		            + "FOREIGN KEY (idTournoi) REFERENCES Tournoi(idTournoi),"
		            + "FOREIGN KEY (idEquipe) REFERENCES Equipe(idEquipe))");
		    System.out.println("✔ Création de la table participation réussie.");
		} catch (SQLException e) {
		    System.out.println("❌ Création de la table participation échouée.");
		}
		

	}

}
