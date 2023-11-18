package DAOs;

public class Joueur {
	
	private int idJoueur;
	private String pseudo;
	
	public Joueur(int id, String pseudo) {
		this.idJoueur = id;
		this.pseudo = pseudo;
	}

	public String getpseudo() {
		return pseudo;
	}

	public void setpseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPrepseudo() {
		return prepseudo;
	}

	public int getIdJoueur() {
		return this.idJoueur;
	}

}