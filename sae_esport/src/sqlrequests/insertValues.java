package sqlrequests;

import DAOs.ArbitreDAO;
import DAOs.TournoiDAO;
import classes.Arbitre;
import classes.EtatTournoi;
import classes.Nationalite;
import classes.Notoriete;
import modeles.TournoiModele;

public class insertValues {

    public static void main(String[] args) throws Exception {
    	
    	//insertion des arbitres
	    ArbitreDAO.getInstance().add(new Arbitre(0, "Dominique", "We", Nationalite.FR));
	    ArbitreDAO.getInstance().add(new Arbitre(0, "Etoiles", "Rayou", Nationalite.DZ));
	    ArbitreDAO.getInstance().add(new Arbitre(0, "Marc", "Evans", Nationalite.JP));
	    ArbitreDAO.getInstance().add(new Arbitre(0, "Arbitre", "qui arbitre", Nationalite.BR));
    	ArbitreDAO.getInstance().add(new Arbitre(0, "Patrick", "Magnaud", Nationalite.IR));
    	ArbitreDAO.getInstance().add(new Arbitre(0, "Chat", "Zelie", Nationalite.RU));
    	ArbitreDAO.getInstance().add(new Arbitre(0, "Toxic", "Avenger", Nationalite.FR));
    	ArbitreDAO.getInstance().add(new Arbitre(0, "Bonnard", "Rik", Nationalite.IS));
        
    	TournoiDAO.getInstance().add(
    			new TournoiModele(0, "Tournoi nom 1", "12/12/2023", "12/01/2024", 
    					Notoriete.INTERNATIONAL, EtatTournoi.OUVERT));
    	TournoiDAO.getInstance().add(
    			new TournoiModele(0, "Tournoi nom 2", "12/12/2024", "12/01/2025", 
    					Notoriete.INTERNATIONAL, EtatTournoi.OUVERT));
    	TournoiDAO.getInstance().add(
    			new TournoiModele(0, "Tournoi nom 3", "12/12/2025", "12/01/2026", 
    					Notoriete.INTERNATIONAL, EtatTournoi.OUVERT));
    	TournoiDAO.getInstance().add(
    			new TournoiModele(0, "Tournoi nom 4", "12/12/2026", "12/01/2027", 
    					Notoriete.INTERNATIONAL, EtatTournoi.OUVERT));
    }
}