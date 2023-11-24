package modeles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import classes.*;
import DAOs.AdministrateurDAO;
import DAOs.ArbitreDAO;
import DAOs.TournoiDAO;

public class IdentificationModele {
	
	public enum Utilisateur {
		ADMIN,ARBITRE
	}
	
	private List<Administrateur> admins;
	private AdministrateurDAO adminDAO;
	private Connection dbConnection;
	private TournoiDAO tournoiDAO;
	private Tournoi tournoiOuvert;
	private Utilisateur utilisateur = null;
	
	public IdentificationModele(Connection dbConnection) throws Exception {
		
		//création de la connexion
		DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
		
		this.dbConnection = dbConnection;
		this.adminDAO = new AdministrateurDAO(dbConnection);
		this.adminDAO.add(new Administrateur(0, "Admin", "login1", "mdp1"));
		this.adminDAO.add(new Administrateur(0, "Admin", "login2", "mdp2"));
		//initalisation des variables
		this.tournoiDAO = new TournoiDAO(dbConnection);
		this.tournoiOuvert = new Tournoi(1, "Tournoi test", "20/10/2023", "26/10/2023", Notoriete.INTERNATIONAL, EtatTournoi.OUVERT);
		
		//recuperer le tournoi ouvert à l'aide du DAO (créer un DAO tournoi)
		//recuperer les logins d'arbitre, ajouter les check logins
		this.admins = adminDAO.getAll();
		
	}
	
	public boolean checkLogins(String login, String motDePasse) {
		//if (this.tournoiOuvert.getLogin() == login && this.tournoiOuvert.getMotDePasse() == motDePasse) {
		//	this.utilisateur = Utilisateur.ARBITRE;
		//	    JOptionPane.showMessageDialog(jFrametest, "Arbitre login");
		//}
		for (Administrateur a : admins) {
			System.out.println(a.getLogin()+" "+a.getMotDePasse());
			System.out.println(login+" "+motDePasse);
			if (a.getLogin().equals(login) && a.getMotDePasse().equals(motDePasse)) {
				this.utilisateur = Utilisateur.ADMIN;
				return true;
			}
			if (this.tournoiOuvert.getLogin().equals(login) && this.tournoiOuvert.getMotDePasse().equals(motDePasse)) {
				return true;
			}
		}
		return false;
	}
	
	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}
	
	

}
