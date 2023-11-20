package modeles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import classes.*;
import DAOs.ArbitreDAO;

public class IdentificationModele {
	
	enum Utilisateur {
		ADMIN,ARBITRE
	}
	
	private List<Administrateur> admins;
	private AdminDAO adminDAO;
	private Connection dbConnection;
	private Tournoi tournoiOuvert;
	private Utilisateur utilisateur = null;
	
	public IdentificationModele() throws Exception {
		
		//création de la connexion
		DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
		String urlConnexion = "jdbc:derby:BDDSAEEsport;create=true";
		this.dbConnection = DriverManager.getConnection(urlConnexion);
		
		//initalisation des variables
		//recuperer le tournoi ouvert à l'aide du DAO (créer un DAO tournoi)
		//recuperer les logins d'arbitre, ajouter les check logins
		//recuperer la liste des administrateurs, ajouter les check logins
		
	}
	
	public boolean checkLogins(String login, String motDePasse) {
		if (this.tournoiOuvert.getLogin() == login && this.tournoiOuvert.getMotDePasse() == motDePasse) {
			this.utilisateur = Utilisateur.ARBITRE;
		}
		for (Administrateur a : admins) {
			if (a.getLogin() == login && a.getMotDePasse() == motDePasse) {
				this.utilisateur = Utilisateur.ADMIN;
				return true;
			}
		}
		return false;
	}
	
	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

}
