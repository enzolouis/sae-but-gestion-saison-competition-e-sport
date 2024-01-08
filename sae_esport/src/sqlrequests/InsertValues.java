package sqlrequests;

import java.util.Iterator;

import DAOs.AdministrateurDAO;
import DAOs.ArbitreDAO;
import DAOs.EquipeDAO;
import DAOs.JoueurDAO;
import DAOs.MatchDAO;
import DAOs.ParticiperDAO;
import DAOs.TournoiDAO;
import classes.Administrateur;
import classes.Arbitre;
import classes.Disposition;
import classes.Equipe;
import classes.EtatTournoi;
import classes.Joueur;
import classes.Match;
import classes.Nationalite;
import classes.Notoriete;
import classes.Participer;
import modeles.TournoiModele;

public class InsertValues {

    public static void main(String[] args) throws Exception {
    	InsertValues.Insert();
    }
   
    public static void BigTournoiExemple() throws Exception {
    	TournoiModele t = new TournoiModele(66, "Big tournoi", "20/12/2023", "20/01/2023", 
				Notoriete.INTERNATIONAL, EtatTournoi.FERME);
    	TournoiDAO.getInstance().add(t);
    	System.out.print(t.getIDTournoi());
    	
    	// 4 équipes
		Equipe e = new Equipe(0, "Maxence Maury-Balliteam", Nationalite.FR, Disposition.DISPOSEE, 3, 1000);EquipeDAO.getInstance().add(e);
		Equipe e2 = new Equipe(1, "Ibrateam Zoubairov", Nationalite.FR, Disposition.DISPOSEE, 1, 2300);EquipeDAO.getInstance().add(e2);
		Equipe e3 = new Equipe(2, "Equipema Rasoanaivo", Nationalite.FR, Disposition.DISPOSEE, 2, 2001);EquipeDAO.getInstance().add(e3);
		Equipe e4 = new Equipe(3, "Stuarteam Barthe", Nationalite.FR, Disposition.DISPOSEE, 4, 18);EquipeDAO.getInstance().add(e4);
		Equipe e5 = new Equipe(0, "Peymar", Nationalite.FR, Disposition.DISPOSEE, 3, 1000);EquipeDAO.getInstance().add(e5);
		Equipe e6 = new Equipe(1, "Plkk", Nationalite.FR, Disposition.DISPOSEE, 1, 2300);EquipeDAO.getInstance().add(e6);
		Equipe e7 = new Equipe(2, "PhaineL", Nationalite.FR, Disposition.DISPOSEE, 2, 2001);EquipeDAO.getInstance().add(e7);
		Equipe e8 = new Equipe(3, "Zombie Tsunami", Nationalite.FR, Disposition.DISPOSEE, 4, 18);EquipeDAO.getInstance().add(e8);
		
		Joueur je11 = new Joueur(0, "Enzo", e.getIdEquipe());JoueurDAO.getInstance().add(je11);
		Joueur je12 = new Joueur(1, "Emile", e.getIdEquipe());JoueurDAO.getInstance().add(je12);
		Joueur je13 = new Joueur(2, "Emma", e.getIdEquipe());JoueurDAO.getInstance().add(je13);
		Joueur je14 = new Joueur(3, "Lea", e.getIdEquipe());JoueurDAO.getInstance().add(je14);
		Joueur je15 = new Joueur(4, "Leo", e.getIdEquipe());JoueurDAO.getInstance().add(je15);
		
		Joueur je21 = new Joueur(5, "Enzo", e2.getIdEquipe());JoueurDAO.getInstance().add(je21);
		Joueur je22 = new Joueur(6, "Emile", e2.getIdEquipe());JoueurDAO.getInstance().add(je22);
		Joueur je23 = new Joueur(7, "Emma", e2.getIdEquipe());JoueurDAO.getInstance().add(je23);
		Joueur je24 = new Joueur(8, "Lea", e2.getIdEquipe());JoueurDAO.getInstance().add(je24);
		Joueur je25 = new Joueur(9, "Leo", e2.getIdEquipe());JoueurDAO.getInstance().add(je25);
		
		Joueur je31 = new Joueur(10, "Enzo", e3.getIdEquipe());JoueurDAO.getInstance().add(je31);
		Joueur je32 = new Joueur(11, "Emile", e3.getIdEquipe());JoueurDAO.getInstance().add(je32);
		Joueur je33 = new Joueur(12, "Emma", e3.getIdEquipe());JoueurDAO.getInstance().add(je33);
		Joueur je34 = new Joueur(13, "Lea", e3.getIdEquipe());JoueurDAO.getInstance().add(je34);
		Joueur je35 = new Joueur(14, "Leo", e3.getIdEquipe());JoueurDAO.getInstance().add(je35);
		
		Joueur j1 = new Joueur(15, "Enzo", e4.getIdEquipe());JoueurDAO.getInstance().add(j1);
		Joueur j2 = new Joueur(16, "Emile", e4.getIdEquipe());JoueurDAO.getInstance().add(j2);
		Joueur j3 = new Joueur(17, "Emma", e4.getIdEquipe());JoueurDAO.getInstance().add(j3);
		Joueur j4 = new Joueur(18, "Lea", e4.getIdEquipe());JoueurDAO.getInstance().add(j4);
		Joueur j5 = new Joueur(19, "Leo", e4.getIdEquipe());JoueurDAO.getInstance().add(j5);
		
		TournoiDAO.getInstance().addEquipe(t, e);
		TournoiDAO.getInstance().addEquipe(t, e2);
		TournoiDAO.getInstance().addEquipe(t, e3);
		TournoiDAO.getInstance().addEquipe(t, e4);
		TournoiDAO.getInstance().addEquipe(t, e5);
		TournoiDAO.getInstance().addEquipe(t, e6);
		TournoiDAO.getInstance().addEquipe(t, e7);
		TournoiDAO.getInstance().addEquipe(t, e8);
		
		
		// 1 arbitre
		Arbitre a = new Arbitre(0, "Josman", "José", Nationalite.FR);
		ArbitreDAO.getInstance().add(a);
		TournoiDAO.getInstance().addArbitre(t, a);
		
		ParticiperDAO.getInstance().update(new Participer(e.getIdEquipe(), t.getIDTournoi(), 1));
		ParticiperDAO.getInstance().update(new Participer(e2.getIdEquipe(), t.getIDTournoi(), 4));
		ParticiperDAO.getInstance().update(new Participer(e3.getIdEquipe(), t.getIDTournoi(), 2));
		ParticiperDAO.getInstance().update(new Participer(e4.getIdEquipe(), t.getIDTournoi(), 3));
		ParticiperDAO.getInstance().update(new Participer(e5.getIdEquipe(), t.getIDTournoi(), 8));
		ParticiperDAO.getInstance().update(new Participer(e6.getIdEquipe(), t.getIDTournoi(), 6));
		ParticiperDAO.getInstance().update(new Participer(e7.getIdEquipe(), t.getIDTournoi(), 7));
		ParticiperDAO.getInstance().update(new Participer(e8.getIdEquipe(), t.getIDTournoi(), 5));
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
	    	
	    	// le laisser en premier
	    	InsertValues.BigTournoiExemple();
	    	
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