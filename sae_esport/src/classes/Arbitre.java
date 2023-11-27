package classes;

import java.util.Objects;

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

	public void setIdArbitre(int id) {
		this.idArbitre = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arbitre other = (Arbitre) obj;
		return idArbitre == other.idArbitre && nationalite == other.nationalite && Objects.equals(nom, other.nom)
				&& Objects.equals(prenom, other.prenom);
	}
	
	
	
}
