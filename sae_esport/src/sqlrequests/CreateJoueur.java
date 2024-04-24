package sqlrequests;

import java.sql.SQLException;
import java.sql.Statement;

import classes.DBConnection;

public class CreateJoueur {

    public static void main(String[] args) {
        CreateJoueur.Drop();
        CreateJoueur.Create();
    }

    public static void Drop() {
        try {
            // Suppression de la table joueur
            Statement stSupprJoueur = DBConnection.getInstance().createStatement();
            stSupprJoueur.executeUpdate("DROP TABLE joueur");
            System.out.println("✔ Suppression de la table joueur réussie.");
        } catch (SQLException e) {
            System.out.println("❌ Suppression de la table joueur échouée.");
        }

        try {
            // Suppression de la séquence seqIdJoueur
            Statement stSupprSeqIdJoueur = DBConnection.getInstance().createStatement();
            stSupprSeqIdJoueur.executeUpdate("DROP SEQUENCE seqIdJoueur RESTRICT");
            System.out.println("✔ Suppression de la séquence seqIdJoueur réussie.");
        } catch (SQLException e) {
            System.out.println("❌ Suppression de la séquence seqIdJoueur échouée.");
        }
    }

    public static void Create() {
        try {
            // Création de la séquence de l'identifiant joueur
            Statement stSeqJoueur = DBConnection.getInstance().createStatement();
            stSeqJoueur.executeUpdate("CREATE SEQUENCE seqIdJoueur START WITH 1 INCREMENT BY 1");
            System.out.println("✔ Création de la séquence seqIdJoueur réussie.");
        } catch (SQLException e) {
            System.out.println("❌ Création de la séquence seqIdJoueur échouée.");
        }

        try {
            // Création de la table joueur
            Statement stCreateJoueur = DBConnection.getInstance().createStatement();
            stCreateJoueur.executeUpdate("CREATE TABLE joueur ("
                    + "idJoueur INT PRIMARY KEY NOT NULL,"
                    + "pseudo VARCHAR(50),"
                    + "idEquipe INT NOT NULL,"
                    + "FOREIGN KEY (idEquipe) REFERENCES Equipe(idEquipe))");
            System.out.println("✔ Création de la table joueur réussie.");
        } catch (SQLException e) {
            System.out.println("❌ Création de la table joueur échouée.");
        }

    }
}
