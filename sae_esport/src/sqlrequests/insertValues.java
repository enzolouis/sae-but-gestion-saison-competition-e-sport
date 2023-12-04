package sqlrequests;
import DAOs.AdministrateurDAO;
import DAOs.TournoiDAO;
import classes.Administrateur;
import classes.EtatTournoi;
import classes.Notoriete;
import modeles.TournoiModele;

public class insertValues {

    public static void main(String[] args) throws Exception {
    	
        //ajout d'administrateur
        AdministrateurDAO adminDAO = new AdministrateurDAO();
        adminDAO.add(new Administrateur(0, "admin1", "admin1", "motdepasse"));
        System.out.print("Admin OK");
        
        TournoiDAO tournoiDAO = new TournoiDAO();
        tournoiDAO.add(new TournoiModele(0, "premierTournoi", "2023-12-16", "2023-12-24", Notoriete.LOCAL, EtatTournoi.OUVERT));
        System.out.print("Tournoi OK");
    }
}