package sqlrequests;

import java.sql.SQLException;
import java.sql.Statement;

import classes.DBConnection;

public class CreateArbitre {

    public static void main(String[] args) {
        CreateArbitre.Drop();
        CreateArbitre.Create();
    }

    /**
     * Supprimme la table Arbitre, ainsi que sa séquence
     */
    public static void Drop() {
        try {
            // Suppression de la table arbitre
            Statement stSupprArbitre = DBConnection.getInstance().createStatement();
            stSupprArbitre.executeUpdate("DROP TABLE arbitre");
            System.out.println("✔ Suppression de la table arbitre réussie.");
        } catch (SQLException e) {
            System.out.println("❌ Suppression de la table arbitre échouée.");
        }

        try {
            // Suppression de la séquence seqIdArbitre
            Statement stSupprSeqIdArbitre = DBConnection.getInstance().createStatement();
            stSupprSeqIdArbitre.executeUpdate("DROP SEQUENCE seqIdArbitre RESTRICT");
            System.out.println("✔ Suppression de la séquence seqIdArbitre réussie.");
        } catch (SQLException e) {
            System.out.println("❌ Suppression de la séquence seqIdArbitre échouée.");
        }
    }

    /**
     * Créée la table Arbitre, ainsi que sa séquence
     */
    public static void Create() {
        try {
            // Création de la séquence de l'identifiant arbitre
            Statement stSeqArbitre = DBConnection.getInstance().createStatement();
            stSeqArbitre.executeUpdate("CREATE SEQUENCE seqIdArbitre START WITH 1 INCREMENT BY 1");
            System.out.println("✔ Création de la séquence seqIdArbitre réussie.");
        } catch (SQLException e) {
            System.out.println("❌ Création de la séquence seqIdArbitre échouée.");
        }

        try {
            // Création de la table arbitre
            Statement stCreateArbitre = DBConnection.getInstance().createStatement();
            stCreateArbitre.executeUpdate("CREATE TABLE arbitre("
                    + "idArbitre INT PRIMARY KEY NOT NULL,"
                    + "nom VARCHAR(50),"
                    + "prenom VARCHAR(50),"
                    + "nationalite VARCHAR(50))");
            System.out.println("✔ Création de la table arbitre réussie.");
        } catch (SQLException e) {
            System.out.println("❌ Création de la table arbitre échouée.");
        }

    }

}
