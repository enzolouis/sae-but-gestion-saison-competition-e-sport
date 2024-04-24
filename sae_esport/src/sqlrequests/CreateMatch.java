package sqlrequests;

import java.sql.SQLException;
import java.sql.Statement;

import classes.DBConnection;

public class CreateMatch {

	public static void main(String[] args) {
		CreateMatch.Drop();
		CreateMatch.Create();
	}

	public static void Drop() {
		try {
			// Suppression de la table match
			Statement stSupprMatch = DBConnection.getInstance().createStatement();
			stSupprMatch.executeUpdate("DROP TABLE matchT");
			System.out.println("✔ Suppression de la table match réussie.");
		} catch (SQLException e) {
			System.out.println("❌ Suppression de la table match échouée.");
		}

		try {
			// Suppression de la séquence seqIdMatch
			Statement stSupprSeqIdMatch = DBConnection.getInstance().createStatement();
			stSupprSeqIdMatch.executeUpdate("DROP SEQUENCE seqIdMatch RESTRICT");
			System.out.println("✔ Suppression de la séquence seqIdMatch réussie.");
		} catch (SQLException e) {
			System.out.println("❌ Suppression de la séquence seqIdMatch échouée.");
		}
	}

	public static void Create() {
		try {
			// Création de la séquence de l'identifiant match
			Statement stSeqMatch = DBConnection.getInstance().createStatement();
			stSeqMatch.executeUpdate("CREATE SEQUENCE seqIdMatch START WITH 1 INCREMENT BY 1");
			System.out.println("✔ Création de la séquence seqIdMatch réussie.");
		} catch (SQLException e) {
			System.out.println("❌ Création de la séquence seqIdMatch échouée.");
		}

		try {
			// Création de la table match
			Statement stCreateMatch = DBConnection.getInstance().createStatement();
			stCreateMatch.executeUpdate("CREATE TABLE matchT ("
					+ "idMatch INT PRIMARY KEY NOT NULL,"
					+ "finale BOOLEAN,"
					+ "idVainqueur INT,"
					+ "idTournoi INT,"
					+ "FOREIGN KEY (idTournoi) REFERENCES Tournoi(idTournoi),"
					+ "FOREIGN KEY(idVainqueur) REFERENCES Equipe(idEquipe))");
			System.out.println("✔ Création de la table match réussie.");
		} catch (SQLException e) {
			System.out.println("❌ Création de la table match échouée.");
		}

	}
}
