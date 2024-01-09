package modeles;

import classes.*;
import DAOs.AdministrateurDAO;
import DAOs.TournoiDAO;

public class IdentificationModele {
	
	public enum Utilisateur {
		ADMIN,ARBITRE
	}
	
	private Utilisateur utilisateur = null;
	
	public IdentificationModele() throws Exception {
		
	}
	
	public boolean checkLogins(String login, String motDePasse) throws Exception {
		//if (this.tournoiOuvert.getLogin() == login && this.tournoiOuvert.getMotDePasse() == motDePasse) {
		//	this.utilisateur = Utilisateur.ARBITRE;
		//	    JOptionPane.showMessageDialog(jFrametest, "Arbitre login");
		//}
		
		if (TournoiDAO.getInstance().getTournoiOuvert().isPresent()) {
			if (TournoiDAO.getInstance().getTournoiOuvert().get().getLogin().equals(login) && 
					TournoiDAO.getInstance().getTournoiOuvert().get().getMotDePasse().equals(motDePasse)) {
				this.utilisateur = Utilisateur.ARBITRE;
				return true;
			}
		}
		
		for (Administrateur a : AdministrateurDAO.getInstance().getAll()) {
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
