package DAOs;

public class Administrateur {
	
	private int idAdministrateur;
	private String nom;
	private String mot_de_passe;
	
	public Administrateur(int id, String nom, String mot_de_passe) {
		this.idAdministrateur = id;
		this.nom = nom;
		this.mot_de_passe = mot_de_passe;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getmot_de_passe() {
		return mot_de_passe;
	}

	public void setmot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}

	public int getIdAdministrateur() {
		return this.idAdministrateur;
	}

}