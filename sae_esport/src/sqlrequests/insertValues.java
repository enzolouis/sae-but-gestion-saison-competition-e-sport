package sqlrequests;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import DAOs.AdministrateurDAO;
import DAOs.TournoiDAO;
import classes.Administrateur;
import classes.EtatTournoi;
import classes.Notoriete;
import classes.Tournoi;

public class insertValues {

    public static void main(String[] args) throws Exception {
    	
    	//connexion à la base de données

        String dirProjetJava = System.getProperty("user.dir");
        System.setProperty("derby.system.home", dirProjetJava+"/BDDSAEEsport");
        DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
        String urlConnexion = "jdbc:derby:BDDSAEEsport;create=true";
        Connection dbConnection = DriverManager.getConnection(urlConnexion);
        
        //ajout d'administrateur
        AdministrateurDAO adminDAO = new AdministrateurDAO(dbConnection);
        adminDAO.add(new Administrateur(0, "admin1", "admin1", "motdepasse"));
        System.out.print("Admin OK");
        
        TournoiDAO tournoiDAO = new TournoiDAO(dbConnection);
        tournoiDAO.add(new Tournoi(0, "premierTournoi", "2023-12-16", "2023-12-24", Notoriete.LOCAL, EtatTournoi.OUVERT));
        System.out.print("Tournoi OK");
    }
}