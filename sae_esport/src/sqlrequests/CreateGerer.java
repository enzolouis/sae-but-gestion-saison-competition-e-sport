package sqlrequests;

import java.sql.SQLException;
import java.sql.Statement;

import classes.DBConnection;

public class CreateGerer {
	
	public static void main(String[] args) {
		CreateGerer.Drop();
		CreateGerer.Create();
	}
	
	public static void Drop() {
		try {
		    // Suppression de la table gerer
		    Statement stSupprGerer = DBConnection.getInstance().createStatement();
		    stSupprGerer.executeUpdate("DROP TABLE gerer");
		    System.out.println("✔ Suppression de la table gerer réussie.");
		} catch (SQLException e) {
		    System.out.println("❌ Suppression de la table gerer échouée.");
		}

		try {
		    // Suppression de la séquence seqGerer
		    Statement stSupprSeqGerer = DBConnection.getInstance().createStatement();
		    stSupprSeqGerer.executeUpdate("DROP SEQUENCE seqGerer RESTRICT");
		    System.out.println("✔ Suppression de la séquence seqGerer réussie.");
		} catch (SQLException e) {
		    System.out.println("❌ Suppression de la séquence seqGerer échouée.");
		}
	}
	
	public static void Create() {
		try {
		    // Création de la séquence de l'identifiant d'équipe
		    Statement stSeqGerer = DBConnection.getInstance().createStatement();
		    stSeqGerer.executeUpdate("CREATE SEQUENCE seqGerer START WITH 1 INCREMENT BY 1");
		    System.out.println("✔ Création de la séquence seqGerer réussie.");
		} catch (SQLException e) {
		    System.out.println("❌ Création de la séquence seqGerer échouée.");
		}

		try {
		    // Création de la table gerer
		    Statement stCreateGerer = DBConnection.getInstance().createStatement();
		    stCreateGerer.executeUpdate("CREATE TABLE Gerer("
		            + "idTournoi INT,"
		            + "idArbitre INT,"
		            + "PRIMARY KEY(idTournoi, idArbitre),"
		            + "FOREIGN KEY(idTournoi) REFERENCES Tournoi(idTournoi),"
		            + "FOREIGN KEY(idArbitre) REFERENCES Arbitre(idArbitre))"
		    );
		    System.out.println("✔ Création de la table gerer réussie.");
		} catch (SQLException e) {
		    System.out.println("❌ Création de la table gerer échouée.");
		}
		
	}
	
}
