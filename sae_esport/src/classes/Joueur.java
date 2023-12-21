package classes;

import java.util.Objects;

public class Joueur {
	
	private int idJoueur;
	private String pseudo;
	private int idEquipe;
	
	/**
	 * Constructeur de la classe Joueur
	 * 	@param l'ID du Joueur 
	 *	@param du Pseudo du Joueur
	 * */
	public Joueur(int id, String pseudo, int idEquipe) {
		this.idJoueur = id;
		this.idEquipe = idEquipe;
		this.pseudo = pseudo;
	}

	public int getIdEquipe() {
		return idEquipe;
	}

	public void setIdEquipe(int idEquipe) {
		this.idEquipe = idEquipe;
	}

	//Retourne le Pseudo du Joueur
	public String getPseudo() {
		return pseudo;
	}
	/**Change me Pseudo du Joueur
	 * 	@param le Pseudo avec lequel remplacer
	 * */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	//Retourne l'ID du Joueur
	public int getIdJoueur() {
		return this.idJoueur;
	}
	/**Change l'ID du Joueur
	 * 	@param l'ID du Joueur avec lequel remplacer
	 * */
	public void setIDJoueur(int id) {
		this.idJoueur = id;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idEquipe, idJoueur, pseudo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Joueur other = (Joueur) obj;
		return idEquipe == other.idEquipe && idJoueur == other.idJoueur && Objects.equals(pseudo, other.pseudo);
	}
	
	@Override
	public String toString() {
		return idJoueur+": "+pseudo+" ("+idEquipe+")";
	}

}