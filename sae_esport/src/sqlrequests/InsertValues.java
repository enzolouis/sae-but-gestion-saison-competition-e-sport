package sqlrequests;

import DAOs.AdministrateurDAO;
import DAOs.ArbitreDAO;
import DAOs.EquipeDAO;
import DAOs.JoueurDAO;
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
   
    public static void BigTournoiExemple() {
    	
    	try {
    	
	    	TournoiModele t = new TournoiModele(66, "Big tournoi", "20/12/2023", "20/01/2025", Notoriete.INTERNATIONAL, EtatTournoi.FERME);
	    	
			Match m1 = new Match(1, t.getIDTournoi(), false);
			Match m2 = new Match(2, t.getIDTournoi(), false); 
			Match m3 = new Match(3, t.getIDTournoi(), false); 
	    	
	    	// 4 équipes
			Equipe e = new Equipe(0, "Maxence Maury-Balliteam", Nationalite.FR, Disposition.DISPOSEE, 3, 1000); EquipeDAO.getInstance().add(e);
			Equipe e2 = new Equipe(1, "Ibrateam Zoubairov", Nationalite.FR, Disposition.DISPOSEE, 1, 1000); EquipeDAO.getInstance().add(e2);
			Equipe e3 = new Equipe(2, "Equipema Rasoanaivo", Nationalite.FR, Disposition.DISPOSEE, 2, 2001); EquipeDAO.getInstance().add(e3);
			Equipe e4 = new Equipe(3, "Stuarteam Barthe", Nationalite.FR, Disposition.DISPOSEE, 4, 18); EquipeDAO.getInstance().add(e4);
			
			m1.AddEquipe(e);
			m1.AddEquipe(e2);
			m2.AddEquipe(e);
			m2.AddEquipe(e3);
			m3.AddEquipe(e);
			m3.AddEquipe(e4);
			
	    	t.ajouterMatch(m1);
	    	t.ajouterMatch(m2);
	    	t.ajouterMatch(m3);
			
	    	TournoiDAO.getInstance().add(t);
	
			Joueur je11 = new Joueur(0, "Marie", e.getIdEquipe());JoueurDAO.getInstance().add(je11);
			Joueur je12 = new Joueur(1, "Milo", e.getIdEquipe());JoueurDAO.getInstance().add(je12);
			Joueur je13 = new Joueur(2, "Mouna", e.getIdEquipe());JoueurDAO.getInstance().add(je13);
			Joueur je14 = new Joueur(3, "Maxime", e.getIdEquipe());JoueurDAO.getInstance().add(je14);
			Joueur je15 = new Joueur(4, "Meredith", e.getIdEquipe());JoueurDAO.getInstance().add(je15);
			
			Joueur je21 = new Joueur(5, "Ilona", e2.getIdEquipe());JoueurDAO.getInstance().add(je21);
			Joueur je22 = new Joueur(6, "Ingrid", e2.getIdEquipe());JoueurDAO.getInstance().add(je22);
			Joueur je23 = new Joueur(7, "Isaac", e2.getIdEquipe());JoueurDAO.getInstance().add(je23);
			Joueur je24 = new Joueur(8, "Icare", e2.getIdEquipe());JoueurDAO.getInstance().add(je24);
			Joueur je25 = new Joueur(9, "Ines", e2.getIdEquipe());JoueurDAO.getInstance().add(je25);
			
			Joueur je31 = new Joueur(10, "Enzo", e3.getIdEquipe());JoueurDAO.getInstance().add(je31);
			Joueur je32 = new Joueur(11, "Emile", e3.getIdEquipe());JoueurDAO.getInstance().add(je32);
			Joueur je33 = new Joueur(12, "Emma", e3.getIdEquipe());JoueurDAO.getInstance().add(je33);
			Joueur je34 = new Joueur(13, "Lea", e3.getIdEquipe());JoueurDAO.getInstance().add(je34);
			Joueur je35 = new Joueur(14, "Leo", e3.getIdEquipe());JoueurDAO.getInstance().add(je35);
			
			Joueur j1 = new Joueur(15, "Suzie", e4.getIdEquipe());JoueurDAO.getInstance().add(j1);
			Joueur j2 = new Joueur(16, "Salem", e4.getIdEquipe());JoueurDAO.getInstance().add(j2);
			Joueur j3 = new Joueur(17, "Sophie", e4.getIdEquipe());JoueurDAO.getInstance().add(j3);
			Joueur j4 = new Joueur(18, "Samy", e4.getIdEquipe());JoueurDAO.getInstance().add(j4);
			Joueur j5 = new Joueur(19, "Soul", e4.getIdEquipe());JoueurDAO.getInstance().add(j5);

			TournoiDAO.getInstance().addEquipe(t, e);
			TournoiDAO.getInstance().addEquipe(t, e2);
			TournoiDAO.getInstance().addEquipe(t, e3);
			TournoiDAO.getInstance().addEquipe(t, e4);
			
			// 1 arbitre
			Arbitre a = new Arbitre(0, "Josman", "José", Nationalite.FR); ArbitreDAO.getInstance().add(a);
			TournoiDAO.getInstance().addArbitre(t, a);
			
			ParticiperDAO.getInstance().update(new Participer(e.getIdEquipe(), t.getIDTournoi(), 1));
			ParticiperDAO.getInstance().update(new Participer(e2.getIdEquipe(), t.getIDTournoi(), 4));
			ParticiperDAO.getInstance().update(new Participer(e3.getIdEquipe(), t.getIDTournoi(), 2));
			ParticiperDAO.getInstance().update(new Participer(e4.getIdEquipe(), t.getIDTournoi(), 3));
			
			System.out.println("✔ Insertion du tournoi ("+ t.getIDTournoi() +") Big Tournoi réussie.");
			
    	} catch (Exception e) {
    		
    		System.out.println("❌ Insertion du tournoi Big Tournoi échouée. ⚠ " + e);
    		
    	}

    }
    
    public static void InsererArbitres() {
    	
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
			
			System.out.println("❌ Insertion de tous les arbitres échouée. ⚠ " + e);
			
		}
    	
    }
    
    public static void insererAdmins() {
    	
    	try {
    		
			AdministrateurDAO.getInstance().add(new Administrateur(0, "Bernard", "beber", "1234"));
			AdministrateurDAO.getInstance().add(new Administrateur(1, "Alice", "alice123", "MotDePasse123"));
			
			System.out.println("✔ Insertion de tous les administrateurs réussie.");
			
		} catch (Exception e) {
			
			System.out.println("❌ Insertion de tous les administrateurs échouée. ⚠ " + e);
			
		}
    	
    }
    
    public static void Insert() {
    	
    	InsertValues.BigTournoiExemple();
    	InsertValues.InsererArbitres();
    	InsertValues.insererAdmins();
    	
    }
}