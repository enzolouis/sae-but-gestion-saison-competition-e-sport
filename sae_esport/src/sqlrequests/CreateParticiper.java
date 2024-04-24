package sqlrequests;

import java.sql.SQLException;
import java.sql.Statement;

import classes.DBConnection;

public class CreateParticiper {

    public static void main(String[] args) {
        CreateParticiper.Drop();
        CreateParticiper.Create();
    }

    public static void Drop() {
        try {
            // Suppression de la table participation
            Statement stSupprParticipation = DBConnection.getInstance().createStatement();
            stSupprParticipation.executeUpdate("DROP TABLE participer");
            System.out.println("✔ Suppression de la table participer réussie.");
        } catch (SQLException e) {
            System.out.println("❌ Suppression de la table participer échouée.");
        }

        try {
            // Suppression de la séquence seqParticipation
            Statement stSupprSeqParticipation = DBConnection.getInstance().createStatement();
            stSupprSeqParticipation.executeUpdate("DROP SEQUENCE seqParticiper RESTRICT");
            System.out.println("✔ Suppression de la séquence seqParticiper réussie.");
        } catch (SQLException e) {
            System.out.println("❌ Suppression de la séquence seqParticiper échouée.");
        }
    }

    public static void Create() {
        try {
            // Création de la séquence de l'id tournoi
            Statement stSeqParticipation = DBConnection.getInstance().createStatement();
            stSeqParticipation.executeUpdate("CREATE SEQUENCE seqParticiper START WITH 1 INCREMENT BY 1");
            System.out.println("✔ Création de la séquence seqParticiper réussie.");
        } catch (SQLException e) {
            System.out.println("❌ Création de la séquence seqParticiper échouée.");
        }

        try {
            // Création de la table participation
            Statement stCreateParticipation = DBConnection.getInstance().createStatement();
            stCreateParticipation.executeUpdate("CREATE TABLE participer"
                    + "(resultat INT,"
                    + "idTournoi INT,"
                    + "idEquipe INT,"
                    + "PRIMARY KEY (idTournoi, idEquipe),"
                    + "FOREIGN KEY (idTournoi) REFERENCES Tournoi(idTournoi),"
                    + "FOREIGN KEY (idEquipe) REFERENCES Equipe(idEquipe))");
            System.out.println("✔ Création de la table participer réussie.");
        } catch (SQLException e) {
            System.out.println("❌ Création de la table participer échouée.");
        }

    }

}
