package modeles;

import java.util.List;
import classes.*;
import DAOs.AdministrateurDAO;

public class IdentificationModele {
	
	public enum Utilisateur {
		ADMIN,ARBITRE
	}
	
	private List<Administrateur> admins;
	private AdministrateurDAO adminDAO;
	private TournoiModele tournoiOuvert;
	private Utilisateur utilisateur = null;
	
	public IdentificationModele() throws Exception {

		this.adminDAO = new AdministrateurDAO();
		this.adminDAO.add(new Administrateur(0, "Admin", "login1", "mdp1"));
		this.adminDAO.add(new Administrateur(0, "Admin", "login2", "mdp2"));
		//initalisation des variables
		this.tournoiOuvert = new TournoiModele(1, "Tournoi test", "20/10/2023", "26/10/2023", Notoriete.INTERNATIONAL, EtatTournoi.OUVERT);

		//recuperer le tournoi ouvert à l'aide du DAO (créer un DAO tournoi)
		//recuperer les logins d'arbitre, ajouter les check logins
		this.admins = adminDAO.getAll();
		
	}
	
	public boolean checkLogins(String login, String motDePasse) {
		//if (this.tournoiOuvert.getLogin() == login && this.tournoiOuvert.getMotDePasse() == motDePasse) {
		//	this.utilisateur = Utilisateur.ARBITRE;
		//	    JOptionPane.showMessageDialog(jFrametest, "Arbitre login");
		//}
		System.out.println(login+" "+motDePasse);
		
		if (this.tournoiOuvert.getLogin().equals(login) && this.tournoiOuvert.getMotDePasse().equals(motDePasse)) {
			this.utilisateur = Utilisateur.ARBITRE;
			return true;
		}
		
		for (Administrateur a : admins) {
			if (a.getLogin().equals(login) && a.getMotDePasse().equals(motDePasse)) {
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
