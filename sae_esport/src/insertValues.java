import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import DAOs.AdministrateurDAO;
import DAOs.TournoiDAO;
import classes.Administrateur;
import classes.EtatTournoi;
import classes.Notoriete;
import classes.Tournoi;

public class insertValues {

    public static void main(String[] args) throws Exception {

        String dirProjetJava = System.getProperty("user.dir");
        System.setProperty("derby.system.home", dirProjetJava+"/BDDSAEEsport");
        DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
        String urlConnexion = "jdbc:derby:BDDSAEEsport;create=true";
        Connection dbConnection = DriverManager.getConnection(urlConnexion);
        
        AdministrateurDAO adminDAO = new AdministrateurDAO(dbConnection);
        List<Administrateur> admins = adminDAO.getAll();
        for (Administrateur a : admins) {
            System.out.println(a.getIdAdministrateur()+" "+a.getLogin()+" "+a.getMotDePasse());
        }
        
        TournoiDAO tournoiDAO = new TournoiDAO(dbConnection);
        tournoiDAO.add(new Tournoi(1, "Nom de tournoi", "2023-10-26 00.00.000000",  "2023-10-30 00.00.000000", Notoriete.LOCAL, EtatTournoi.OUVERT));
    }

}