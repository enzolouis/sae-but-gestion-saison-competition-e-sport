package sqlrequests;

import java.sql.SQLException;
import java.sql.Statement;

import classes.DBConnection;

public class CreateDisputer {

    public static void main(String[] args) {
        Drop();
        Create();
    }

    public static void Drop() {
        try {
            // Suppression de la table match
            Statement stSupprDisp = DBConnection.getInstance().createStatement();
            stSupprDisp.executeUpdate("DROP TABLE disputer");
            System.out.println("✔ Suppression de la table disputer réussie.");
        } catch (SQLException e) {
            System.out.println("❌ Suppression de la table disputer échouée.");
        }
    }

    public static void Create() {
        try {
            // Création de la table match
            Statement stCreateDisputer = DBConnection.getInstance().createStatement();
            stCreateDisputer.executeUpdate("CREATE TABLE disputer ("
                    + "idMatch INT PRIMARY KEY NOT NULL,"
                    + "idEquipe1 INT,"
                    + "idEquipe2 INT,"
                    + "FOREIGN KEY(idMatch) REFERENCES MatchT(idMatch),"
                    + "FOREIGN KEY(IdEquipe1) REFERENCES Equipe(idEquipe),"
                    + "FOREIGN KEY(IdEquipe2) REFERENCES Equipe(idEquipe))");
            System.out.println("✔ Création de la table disputer réussie.");
        } catch (SQLException e) {
            System.out.println("❌ Création de la table disputer échouée.");
        }

    }

}
