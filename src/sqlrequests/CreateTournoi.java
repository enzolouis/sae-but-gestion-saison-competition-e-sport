package sqlrequests;

import java.sql.SQLException;
import java.sql.Statement;

import classes.DBConnection;

public class CreateTournoi {

    public static void main(String[] args) {
        CreateTournoi.Drop();
        CreateTournoi.Create();
    }

    public static void Drop() {
        try {
            // Suppression de la table tournoi
            Statement stSupprTournoi = DBConnection.getInstance().createStatement();
            stSupprTournoi.executeUpdate("DROP TABLE tournoi");
            System.out.println("✔ Suppression de la table tournoi réussie.");
        } catch (SQLException e) {
            System.out.println("❌ Suppression de la table tournoi échouée.");
        }

        try {
            // Suppression de la séquence seqIdTournoi
            Statement stSupprSeqIdTournoi = DBConnection.getInstance().createStatement();
            stSupprSeqIdTournoi.executeUpdate("DROP SEQUENCE seqIdTournoi RESTRICT");
            System.out.println("✔ Suppression de la séquence seqIdTournoi réussie.");
        } catch (SQLException e) {
            System.out.println("❌ Suppression de la séquence seqIdTournoi échouée.");
        }
    }

    public static void Create() {
        try {
            // Création de la séquence de l'id tournoi
            Statement stSeqTournoi = DBConnection.getInstance().createStatement();
            stSeqTournoi.executeUpdate("CREATE SEQUENCE seqIdTournoi START WITH 1 INCREMENT BY 1");
            System.out.println("✔ Création de la séquence seqIdTournoi réussie.");
        } catch (SQLException e) {
            System.out.println("❌ Création de la séquence seqIdTournoi échouée.");
        }

        try {
            // Création de la table tournoi
            Statement stCreateTournoi = DBConnection.getInstance().createStatement();
            stCreateTournoi.executeUpdate("CREATE TABLE tournoi ("
                    + "idTournoi INT PRIMARY KEY NOT NULL,"
                    + "nom VARCHAR(30),"
                    + "dateDebut DATE,"
                    + "dateFin DATE,"
                    + "notoriete VARCHAR(30),"
                    + "ouvert VARCHAR(30),"
                    + "login VARCHAR(30),"
                    + "mdp VARCHAR(50),"
                    + "idVainqueur INT,"
                    + "FOREIGN KEY (idVainqueur) REFERENCES Equipe(idEquipe))");
            System.out.println("✔ Création de la table tournoi réussie.");
        } catch (SQLException e) {
            System.out.println("❌ Création de la table tournoi échouée.");
        }

    }

}
