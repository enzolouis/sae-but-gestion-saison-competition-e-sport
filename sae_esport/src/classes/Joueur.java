package classes;

public class Joueur {
	
	private int idJoueur;
	private String pseudo;
	
	/**
	 * Constructeur de la classe Joueur
	 * 	@param l'ID du Joueur 
	 *	@param du Pseudo du Joueur
	 * */
	public Joueur(int id, String pseudo) {
		this.idJoueur = id;
		this.pseudo = pseudo;
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

}