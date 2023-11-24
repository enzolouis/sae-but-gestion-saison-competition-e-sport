package classes;

public class Joueur {
	
	private int idJoueur;
	private String pseudo;
	
	public Joueur(int id, String pseudo) {
		this.idJoueur = id;
		this.pseudo = pseudo;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public int getIdJoueur() {
		return this.idJoueur;
	}
	public void setIDJoueur(int id) {
		this.idJoueur = id;
	}

}