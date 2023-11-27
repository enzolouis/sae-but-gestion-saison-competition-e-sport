package classes;

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

	//Retourne le nom l'Administrateur
	public String getNom() {
		return nom;
	}

	/**
	 * Change le nom de l'Administrateur
	 * 	@param le nom de l'Administrateur à remplacer
	 * */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	//Retourne le Login de l'Administrateur
	public String getLogin() {
		return login;
	}
	/**
	 * Change le Login de l'Administrateur
	 * 	@param le Login de l'Administrateur à remplacer
	 * */
	public void setLogin(String login) {
		this.login = login;
	}

	//Retourne le Mot de Passe de l'Administrateur
	public String getMotDePasse() {
		return motDePasse;
	}
	/**
	 * Change le Mot de passe de l'Administrateur
	 * 	@param le Mot de Passe de l'Administrateur à remplacer
	 * */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	//Retourne l'ID de l'Administrateur
	public int getIdAdministrateur() {
		return this.idAdministrateur;
	}
	/**
	 * Change l'ID de l'Administrateur
	 * 	@param la valeur de l'ID de l'Administrateur à remplacer
	 * */
	public void setIdAdministrateur(int id) {
		this.idAdministrateur = id;
	}

}