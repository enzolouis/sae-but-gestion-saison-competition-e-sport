package sqlrequests;
import java.sql.Connection;
import java.sql.DriverManager;
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
             
    }

}