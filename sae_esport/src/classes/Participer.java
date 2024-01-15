package classes;

import java.util.Objects;

public class Participer implements Comparable<Participer> {
	
	private int IDEquipe;
	private int IDTournoi;
	private int resultat;
	
	/**
	 * Constructeur de participation
	 * 	@param la valeur de résultat en points du Tournoi, variant en fonction de la position finale dans le tournoi
	 * 	@param la valeur de l'ID du Tournoi
	 * 	@param la valeur de l'ID de l'Equipe
	 * */
	public Participer(int resultat, int tournoi, int equipe) {
		this.resultat = resultat;
		this.IDTournoi= tournoi;
		this.IDEquipe = equipe;
	}
	
	/**
	 * renvoie l'id de l'équipe
	 * */
	public int getIdEquipe() {
		return this.IDEquipe;
	}
	/**
	 * set l'id de l'Equipe
	 * 	@param le nouvel id d'équipe
	 * */
	public void setIdEquipe(int equipe) {
		this.IDEquipe = equipe;
	}
	
	/**
	 * renvoie l'id du tournoi
	 * */
	public int getIdTournoi() {
		return this.IDTournoi;
	}
	
	/**
	 * set l'ID du Tournoi
	 * @param la valeur de l'ID du Tournoi à remplacer
	 * */
	public void setIdTournoi(int tournoi) {
		this.IDTournoi = tournoi;
	}
	
	/**
	 * renvoie le résultat du tournoi
	 * */
	public int getResultat() {
		return this.resultat;
	}
	
	/**
	 * set le résultat
	 * @param la valeur du Résultat à remplacer
	 * */
	public void setResultat(int resultat) {
		this.resultat = resultat;
	}
	
	@Override
	public String toString() {
		return "Participer(tournoi=" + this.IDTournoi + ", equipe="+this.IDEquipe+", resultat="+this.resultat+")";
	}

	@Override
	public int hashCode() {
		return Objects.hash(IDEquipe, IDTournoi, resultat);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participer other = (Participer) obj;
		return IDEquipe == other.IDEquipe && IDTournoi == other.IDTournoi && resultat == other.resultat;
	}

	@Override
	public int compareTo(Participer p) {
		return this.resultat - p.resultat;
	}
	
	
}
