package sqlrequests;

import java.sql.SQLException;
import java.sql.Statement;

import classes.DBConnection;

public class CreateEquipe {
	
	public static void main(String[] args) {
		CreateEquipe.Drop();
		CreateEquipe.Create();
	}
	
	public static void Drop() {
		try {
		    // Suppression de la table equipe
		    Statement stSupprEquipe = DBConnection.getInstance().createStatement();
		    stSupprEquipe.executeUpdate("DROP TABLE equipe");
		    System.out.println("✔ Suppression de la table equipe réussie.");
		} catch (SQLException e) {
		    System.out.println("❌ Suppression de la table equipe échouée.");
		}
	
		try {
		    // Suppression de la séquence seqIdEquipe
		    Statement stSupprSeqIdEquipe = DBConnection.getInstance().createStatement();
		    stSupprSeqIdEquipe.executeUpdate("DROP SEQUENCE seqIdEquipe RESTRICT");
		    System.out.println("✔ Suppression de la séquence seqIdEquipe réussie.");
		} catch (SQLException e) {
		    System.out.println("❌ Suppression de la séquence seqIdEquipe échouée.");
		}
	}
	
	public static void Create() {	
		try {
		    // Création de la séquence de l'identifiant d'équipe
		    Statement stSeqEquipe = DBConnection.getInstance().createStatement();
		    stSeqEquipe.executeUpdate("CREATE SEQUENCE seqIdEquipe START WITH 1 INCREMENT BY 1");
		    System.out.println("✔ Création de la séquence seqIdEquipe réussie.");
		} catch (SQLException e) {
		    System.out.println("❌ Création de la séquence seqIdEquipe échouée.");
		}
	
		try {
		    // Création de la table equipe
		    Statement stCreateEquipe = DBConnection.getInstance().createStatement();
		    stCreateEquipe.executeUpdate("CREATE TABLE equipe("
		            + "idEquipe INTEGER PRIMARY KEY NOT NULL,"
		            + "nom VARCHAR(50),"
		            + "nationalite VARCHAR(50),"
		            + "rangSaisonPrecedente INTEGER,"
		            + "pointsSaison INTEGER,"
		            + "disposee VARCHAR(10))");
		    System.out.println("✔ Création de la table equipe réussie.");
		} catch (SQLException e) {
		    System.out.println("❌ Création de la table equipe échouée.");
		}
		
	}

}
