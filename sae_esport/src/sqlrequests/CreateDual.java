package sqlrequests;

import java.sql.SQLException;
import java.sql.Statement;

import classes.DBConnection;

public class CreateDual {

	public static void main(String[] args) {
		Drop();
		Create();
		try {
			Statement stInsertDual = DBConnection.getInstance().createStatement();
			stInsertDual.executeUpdate("INSERT INTO DUAL VALUES ('c')");
			System.out.println("✔ Insertion de la valeur unique de DUAL réussie.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("❌ Insertion de la valeur unique de DUAL échouée.");
		}
		
	}
	
	public static void Create() {
		
        try {
            // Création de la séquence de l'identifiant admin
            Statement stTableDual = DBConnection.getInstance().createStatement();
            stTableDual.executeUpdate("CREATE TABLE DUAL ("
            		+ "variable VARCHAR(1))");
            System.out.println("✔ Création de la table DUAL réussie.");
        } catch (SQLException e) {
            System.out.println("❌  Création de la table DUAL échouée.");
        }
        
	}
	
	public static void Drop() {
		
		try {
            // Suppression de la table admin
            Statement stSupprDual = DBConnection.getInstance().createStatement();
            stSupprDual.executeUpdate("DROP TABLE DUAL");
            System.out.println("✔ Suppression de la table DUAL réussie.");
        } catch (SQLException e) {
            System.out.println("❌ Suppression de la table DUAL échouée. (=> raison possible : première initialisation de la base de données, relancez et plus d'erreur)");
        }
		
	}

}
