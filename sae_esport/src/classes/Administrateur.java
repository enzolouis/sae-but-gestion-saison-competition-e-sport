package classes;

import java.util.Objects;

public class Administrateur {
	
	private int idAdministrateur;
	private String nom;
	private String login;
	private String motDePasse;
	
	public Administrateur(int id, String nom, String login, String mdp) {
		this.idAdministrateur = id;
		this.nom = nom;
		this.login = login;
		this.motDePasse = mdp;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public int getIdAdministrateur() {
		return this.idAdministrateur;
	}
	
	public void setIdAdministrateur(int id) {
		this.idAdministrateur = id;
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