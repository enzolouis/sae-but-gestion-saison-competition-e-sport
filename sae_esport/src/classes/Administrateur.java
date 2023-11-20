package classes;

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

}