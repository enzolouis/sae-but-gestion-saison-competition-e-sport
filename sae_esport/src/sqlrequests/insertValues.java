package sqlrequests;

import DAOs.ArbitreDAO;
import classes.Arbitre;
import classes.Nationalite;

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
        
    }
}