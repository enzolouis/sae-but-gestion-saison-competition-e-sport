package DAOs;

public class Arbitre {
	
	private int idArbitre;
	private String nom;
	private String prenom;
	private Nationalite nationalite;
	
	public Arbitre(int id, String nom, String prenom, Nationalite nat) {
		this.idArbitre = id;
		this.nom = nom;
		this.prenom = prenom;
		this.nationalite = nat;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Nationalite getNationalite() {
		return nationalite;
	}

	public void setNationalite(Nationalite nationalite) {
		this.nationalite = nationalite;
	}

	public int getIdArbitre() {
		return this.idArbitre;
	}

}
