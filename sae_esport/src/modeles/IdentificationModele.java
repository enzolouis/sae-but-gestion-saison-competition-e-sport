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

public class IdentificationModele {
	
	enum Utilisateur {
		ADMIN,ARBITRE
	}
	
	private List<Administrateur> admins;
	private AdministrateurDAO adminDAO;
	private Connection dbConnection;
	private Tournoi tournoiOuvert;
	private Utilisateur utilisateur = null;
	
	public IdentificationModele() throws Exception {
		
		//création de la connexion
		DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
		String urlConnexion = "jdbc:derby:BDDSAEEsport;create=true";
		this.dbConnection = DriverManager.getConnection(urlConnexion);
		this.adminDAO = new AdministrateurDAO(dbConnection);
		//initalisation des variables
		//recuperer le tournoi ouvert à l'aide du DAO (créer un DAO tournoi)
		//recuperer les logins d'arbitre, ajouter les check logins
		this.admins = adminDAO.getAll();
		
	}
	
	public boolean checkLogins(String login, String motDePasse) {
		JFrame jFrametest = new JFrame();
		if (this.tournoiOuvert.getLogin() == login && this.tournoiOuvert.getMotDePasse() == motDePasse) {
			this.utilisateur = Utilisateur.ARBITRE;
			    JOptionPane.showMessageDialog(jFrametest, "Arbitre login");
		}
		for (Administrateur a : admins) {
			if (a.getLogin() == login && a.getMotDePasse() == motDePasse) {
				this.utilisateur = Utilisateur.ADMIN;
				JOptionPane.showMessageDialog(jFrametest, "Admin login");
				return true;
			}
		}
		JOptionPane.showMessageDialog(jFrametest, "Aucun login");
		return false;
	}
	
	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

}
