package classes;

import java.util.Objects;

public class Arbitre {
	
	private int idArbitre;
	private String nom;
	private String prenom;
	private Nationalite nationalite;
	
	/**
	 * Constructeur de la classe Arbitre
	 * 	@param ID de l'Arbitre
	 * 	@param Nom de l'Arbitre
	 * 	@param Mot de passe de l'Arbitre
	 * */
	public Arbitre(int id, String nom, String prenom, Nationalite nat) {
		this.idArbitre = id;
		this.nom = nom;
		this.prenom = prenom;
		this.nationalite = nat;
	}

	//Retourne le nom l'Arbitre
	public String getNom() {
		return nom;
	}
	/**
	 * Change le nom de l'Arbitre
	 * 	@param le nom de l'Arbitre à remplacer
	 * */
	public void setNom(String nom) {
		this.nom = nom;
	}

	//Retourne le Prenom l'Arbitre
	public String getPrenom() {
		return prenom;
	}
	/**
	 * Change le Prenom de l'Arbitre
	 * 	@param le Prenom de l'Arbitre à remplacer
	 * */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	//Retourne la Nationalite de l'Arbitre
	public Nationalite getNationalite() {
		return nationalite;
	}
	/**
	 * Change la Nationalite de l'Arbitre
	 * 	@param la valeur Nationalite avec lequel remplacer l'Arbitre
	 * */
	public void setNationalite(Nationalite nationalite) {
		this.nationalite = nationalite;
	}

	//Retourne l'ID de l'Arbitre
	public int getIdArbitre() {
		return this.idArbitre;
	}
	/**
	 * Change l'ID de l'Arbitre
	 * 	@param la valeur de l'ID de l'Arbitre à remplacer
	 * */
	public void setIdArbitre(int id) {
		this.idArbitre = id;
	}
	
	@Override
	public String toString() {
		return this.nom.toUpperCase()+" "+this.prenom+" ("+this.nationalite+")";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idArbitre, nationalite, nom, prenom);
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
