package modeles;

import classes.*;
import DAOs.AdministrateurDAO;
import DAOs.TournoiDAO;

public class IdentificationModele {

	public enum Utilisateur {
		ADMIN, ARBITRE
	}

	private Utilisateur utilisateur = null;

	public IdentificationModele() {
	}

	/**
	 * renvoie si un couple de login et de mdp sont valides et set le type de droits
	 * d'utilisateur
	 * 
	 * @param login
	 * @param mot   de passe
	 */
	public boolean checkLogins(String login, String motDePasse) throws Exception {

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

	/**
	 * renvoie le type d'utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

}
