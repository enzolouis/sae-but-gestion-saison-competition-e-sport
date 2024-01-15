package classes;

import java.util.Objects;

public class Administrateur {
	
	private int idAdministrateur;
	private String nom;
	private String login;
	private String motDePasse;
	
	/**
	 * Constructeur de la classe Administrateur
	 * 	@param ID de l'Administrateur
	 * 	@param Nom de l'Administrateur
	 * 	@param Mot de passe de l'Administrateur
	 * */
	public Administrateur(int id, String nom, String login, String mdp) {
		this.idAdministrateur = id;
		this.nom = nom;
		this.login = login;
		this.motDePasse = mdp;
	}

	/**
	 * Renvoie le nom de l'administrateur
	 * */
	public String getNom() {
		return nom;
	}
	
	/**
	 * setter de l'identifiant de l'administrateur
	 * 	@param ID de l'Administrateur
	 * */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Renvoie le login de l'administrateur
	 * */
	public String getLogin() {
		return login;
	}

	/**
	 * Renvoie le mot de passe de l'administrateur
	 * */
	public String getMotDePasse() {
		return motDePasse;
	}

	/**
	 * Renvoie l'identifiant de l'administrateur
	 * */
	public int getIdAdministrateur() {
		return this.idAdministrateur;
	}
	
	/**
	 * setter de l'identifiant de l'administrateur
	 * 	@param ID de l'Administrateur
	 * */
	public void setIdAdministrateur(int id) {
		this.idAdministrateur = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idAdministrateur, login, motDePasse, nom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Administrateur other = (Administrateur) obj;
		return idAdministrateur == other.idAdministrateur && Objects.equals(login, other.login)
				&& Objects.equals(motDePasse, other.motDePasse) && Objects.equals(nom, other.nom);
	}
	
}