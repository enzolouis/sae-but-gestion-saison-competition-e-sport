package sqlrequests;

import DAOs.AdministrateurDAO;
import DAOs.ArbitreDAO;
import DAOs.EquipeDAO;
import DAOs.JoueurDAO;
import DAOs.TournoiDAO;
import classes.Administrateur;
import classes.Arbitre;
import classes.Disposition;
import classes.Equipe;
import classes.EtatTournoi;
import classes.Joueur;
import classes.Nationalite;
import classes.Notoriete;
import modeles.TournoiModele;

public class InsertValues {

    public static void main(String[] args) throws Exception {
    	InsertValues.Insert();
    }
    
    public static void Insert() {
	    try {
			ArbitreDAO.getInstance().add(new Arbitre(0, "Dominique", "We", Nationalite.FR));
			ArbitreDAO.getInstance().add(new Arbitre(1, "Etoiles", "Rayou", Nationalite.DZ));
		    ArbitreDAO.getInstance().add(new Arbitre(2, "Marc", "Evans", Nationalite.JP));
		    ArbitreDAO.getInstance().add(new Arbitre(3, "Arbitre", "qui arbitre", Nationalite.BR));
	    	ArbitreDAO.getInstance().add(new Arbitre(4, "Patrick", "Magnaud", Nationalite.IR));
	    	ArbitreDAO.getInstance().add(new Arbitre(5, "Chat", "Zelie", Nationalite.RU));
	    	ArbitreDAO.getInstance().add(new Arbitre(6, "Toxic", "Avenger", Nationalite.FR));
	    	ArbitreDAO.getInstance().add(new Arbitre(7, "Bonnard", "Rik", Nationalite.IS));
	    	System.out.println("✔ Insertion de tous les arbitres réussie.");
		} catch (Exception e) {
			System.out.println("❌ Insertion de tous les arbitres échouée.");
		}
	    
	    try {
	    	AdministrateurDAO.getInstance().add(new Administrateur(0, "Bernard", "beber", "1234"));
	    	AdministrateurDAO.getInstance().add(new Administrateur(1, "Alice", "alice123", "MotDePasse123"));
	    	AdministrateurDAO.getInstance().add(new Administrateur(2, "Bob", "bob456", "Securite456"));
	    	AdministrateurDAO.getInstance().add(new Administrateur(3, "Charlie", "charlie789", "MdpCharlie789"));
	    	AdministrateurDAO.getInstance().add(new Administrateur(4, "David", "david_42", "DavidMotDePasse"));
	    	AdministrateurDAO.getInstance().add(new Administrateur(5, "Eva", "eva007", "MotDePasseEva007"));
	    	AdministrateurDAO.getInstance().add(new Administrateur(6, "Frank", "frankenstein", "Frankenstein123"));
	    	AdministrateurDAO.getInstance().add(new Administrateur(7, "Grace", "gracieux", "GraceMotDePasse"));
	    	AdministrateurDAO.getInstance().add(new Administrateur(8, "Hank", "hankster", "HankMdp123"));
	    	AdministrateurDAO.getInstance().add(new Administrateur(9, "Ivy", "ivy@123", "IvySecurite456"));
	    	AdministrateurDAO.getInstance().add(new Administrateur(10, "Jack", "jackpot", "JackpotMotDePasse"));
	    	System.out.println("✔ Insertion de tous les administrateurs réussie.");
		} catch (Exception e) {
			System.out.println("❌ Insertion de tous les arbitres échouée.");
		}
	    
	    try {
	    	
			TournoiModele t = new TournoiModele(5, "Tournoi nom 6", "14/11/2023", "30/11/2023", 
					Notoriete.INTERNATIONAL, EtatTournoi.FERME);
			Equipe e = new Equipe(0, "e1", Nationalite.FR, Disposition.DISPOSEE, 2, 10);
			
			
			
			Equipe e2 = new Equipe(1, "e2", Nationalite.FR, Disposition.DISPOSEE, 2, 10);
			Equipe e3 = new Equipe(2, "e3", Nationalite.FR, Disposition.DISPOSEE, 2, 10);
			Equipe e4 = new Equipe(3, "e4", Nationalite.FR, Disposition.DISPOSEE, 2, 10);
			TournoiDAO.getInstance().add(t);
			EquipeDAO.getInstance().add(e);
			Joueur j1 = new Joueur(0, "Enzo", e.getIdEquipe());
			Joueur j2 = new Joueur(1, "Emile", e.getIdEquipe());
			Joueur j3 = new Joueur(2, "Emma", e.getIdEquipe());
			Joueur j4 = new Joueur(3, "Lea", e.getIdEquipe());
			Joueur j5 = new Joueur(4, "Leo", e.getIdEquipe());
			JoueurDAO.getInstance().add(j1);
			JoueurDAO.getInstance().add(j2);
			JoueurDAO.getInstance().add(j3);
			JoueurDAO.getInstance().add(j4);
			JoueurDAO.getInstance().add(j5);
			
			EquipeDAO.getInstance().add(e2);
			EquipeDAO.getInstance().add(e3);
			EquipeDAO.getInstance().add(e4);
			TournoiDAO.getInstance().addEquipe(t, e);
			TournoiDAO.getInstance().addEquipe(t, e2);
			TournoiDAO.getInstance().addEquipe(t, e3);
			TournoiDAO.getInstance().addEquipe(t, e4);
			Arbitre a = new Arbitre(0, "Josm", "ad", Nationalite.FR);
			ArbitreDAO.getInstance().add(a);
			TournoiDAO.getInstance().addArbitre(t, a);
			
			TournoiDAO.getInstance().add(new TournoiModele(0, "Tournoi nom 1", "12/12/3023", "12/01/3024", 
					Notoriete.INTERNATIONAL, EtatTournoi.FERME));
			TournoiDAO.getInstance().add(new TournoiModele(1, "Tournoi nom 2", "12/12/3024", "12/01/3025", 
								Notoriete.NATIONAL, EtatTournoi.FERME));
			TournoiDAO.getInstance().add(new TournoiModele(2, "Tournoi nom 3", "12/12/3025", "12/01/3026", 
								Notoriete.REGIONAL, EtatTournoi.FERME));
			TournoiDAO.getInstance().add(new TournoiModele(3, "Tournoi nom 4", "12/12/3026", "12/01/3027", 
								Notoriete.INTERNATIONAL, EtatTournoi.FERME));
			TournoiDAO.getInstance().add(new TournoiModele(4, "Tournoi nom 5", "12/12/3036", "12/01/3037", 
								Notoriete.INTERNATIONAL, EtatTournoi.FERME));
			
			System.out.println("✔ Insertion de tous les tournois réussie.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("❌ Insertion de tous les arbitres échouée.");
		}    	
    }
}