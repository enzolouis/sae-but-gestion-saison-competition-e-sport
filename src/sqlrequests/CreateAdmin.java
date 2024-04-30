package sqlrequests;

import java.sql.SQLException;
import java.sql.Statement;

import classes.DBConnection;

public class CreateAdmin {

    public static void main(String[] args) {
        CreateAdmin.Drop();
        CreateAdmin.Create();
    }

    /**
     * Supprime la table admin, ainsi que sa séquence
     */
    public static void Drop() {
        try {
            // Suppression de la table admin
            Statement stSupprAdmin = DBConnection.getInstance().createStatement();
            stSupprAdmin.executeUpdate("DROP TABLE admin");
            System.out.println("✔ Suppression de la table ADMIN réussie.");
        } catch (SQLException e) {
            System.out.println(
                    "❌ Suppression de la table ADMIN échouée. (=> raison possible : première initialisation de la base de données, relancez et plus d'erreur)");
        }

        try {
            // Suppression de la séquence seqIdAdmin
            Statement stSupprSeqIdAdmin = DBConnection.getInstance().createStatement();
            stSupprSeqIdAdmin.executeUpdate("DROP SEQUENCE seqIdAdmin RESTRICT");
            System.out.println("✔ Suppression de la séquence seqIdAdmin réussie.");
        } catch (SQLException e) {
            System.out.println("❌ Suppression de la séquence seqIdAdmin échouée.");
        }
    }

    /**
     * Créée la table admin, ainsi que sa séquence
     */
    public static void Create() {
        try {
            // Création de la séquence de l'identifiant admin
            Statement stSeqAdmin = DBConnection.getInstance().createStatement();
            stSeqAdmin.executeUpdate("CREATE SEQUENCE seqIdAdmin START WITH 1 INCREMENT BY 1");
            System.out.println("✔ Création de la séquence seqIdAdmin réussie.");
        } catch (SQLException e) {
            System.out.println("❌ Création de la séquence seqIdAdmin échouée.");
        }

        try {
            // Création de la table admin
            Statement stCreateAdmin = DBConnection.getInstance().createStatement();
            stCreateAdmin.executeUpdate("CREATE TABLE admin("
                    + "idAdmin INT PRIMARY KEY NOT NULL,"
                    + "nom VARCHAR(50),"
                    + "login VARCHAR(50),"
                    + "motDePasse VARCHAR(50))");
            System.out.println("✔ Création de la table admin réussie.");
        } catch (SQLException e) {
            System.out.println("❌ Création de la table admin échouée.");
        }
    }
}
