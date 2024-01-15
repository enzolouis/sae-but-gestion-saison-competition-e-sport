package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Equipe {
	
	private int idEquipe;
	private String nom;
	private Nationalite nationalite;
	private List<Joueur> joueurs;
	private Disposition dispose;
	private int rangSaisonPrecedante;
	private int pointsSaison;
	
	
	
	/**Constructeur SANS une liste de joueurs déja donné
	*	@param l'ID de l'Equipe
	*	@param la Nationalite de l'Equipe
	*	@param la Disposition d'une Equipe, en boolean
	*	@param le Rang de l'Equipe sur leurs Saison précédente
	*	@param leurs valeur de Points sur la Saison courante
	*/
	public Equipe(int id, String nom, Nationalite nat,Disposition dispose, 
				int rangSaisonPrecedante, int pointsSaison) {
		this.idEquipe = id;
		this.nom = nom;
		this.nationalite = nat;
		this.dispose = dispose;
		this.pointsSaison = pointsSaison;
		this.rangSaisonPrecedante =rangSaisonPrecedante;
		this.joueurs = new ArrayList<>();
		
	}
	
	/**Constructeur AVEC une liste de joueurs déja donné
	*	@param l'ID de l'Equipe
	*	@param la Nationalite de l'Equipe
	*	@param la Disposition d'une Equipe, en boolean
	*	@param une Liste de Joueurs attribué à l'Equipe
	*	@param le Rang de l'Equipe sur leurs Saison précédente
	*	@param leurs valeur de Points sur la Saison courante
	*/
	public Equipe(int id, String nom, Nationalite nat, List<Joueur> joueurs,
				Disposition dispose, int rangSaisonPrecedante, int pointsSaison) {
		
		this.idEquipe = id;
		this.nom = nom;
		this.nationalite = nat;
		this.dispose = dispose;
		this.pointsSaison = pointsSaison;
		this.rangSaisonPrecedante =rangSaisonPrecedante;
		this.joueurs = new ArrayList<>();
		
		for(Joueur i : joueurs) {
			this.joueurs.add(i);
		}
	}

	/**
	 * Renvoie le nom de l'équipe
	 * */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Change le nom de l'équipe
	 * 	@param le nouveau nom de l'équipe
	 * */
	public void setNom(String nom) {
		this.nom = nom;
	}
		
	/**
	 * Renvoie la nationalité de l'équipe
	 * */
	public Nationalite getNationalite() {
		return nationalite;
	}
	
	/**
	 * Change la nationalité de l'Equipe
	 * 	@param la nouvelle nationalité de l'équipe
	 * */
	public void setNationalite(Nationalite nationalite) {
		this.nationalite = nationalite;
	}
	
	/**
	 * Renvoie la disposition de l'équipe
	 * */
	public Disposition getDisposition() {
		return this.dispose;
	}
	
	/**
	 * Change la disposition de l'équipe
	 * @param la nouvelle disposition de l'équipe
	 * */
	public void setDisposition(Disposition disposition) {
		this.dispose = disposition;
	}
	
	/**
	 * Renvoie la liste des joueurs de l'équipe
	 * */
	public List<Joueur> getListeJoueurs(){
		return this.joueurs;
	}
	
	/**
	 * Ajoute un joueur à l'équipe
	 * @param le nouveau joueur de l'équipe
	 * */
	public void ajouterJoueur(Joueur joueur){
		this.joueurs.add(joueur);
	}
	
	/**
	 * Renvoie les points de l'équipe pour la saison actuelle
	 * */
	public int getPointsSaison(){
		return this.pointsSaison;
	}
	
	/**
	 * Permet de changer les points de l'équipe pour la saison actuelle
	 * */
	public void setPointsSaison(int pointsSaison){
		this.pointsSaison = pointsSaison;
	}
	
	/**
	 * Permet d'ajouter des points à l'équipe pour la saison actuelle
	 * */
	public void ajoutDePoints(int points){
		this.pointsSaison += points;
	}
	
	/**
	 *	renvoie le rang de l'équipe lors du rang de la saison précédente
	 * */
	public int getRangSaisonPrecedante(){
		return this.rangSaisonPrecedante;
	}
	
	//Retourne l'ID de l'Equipe
	public int getIdEquipe() {
		return this.idEquipe;
	}

	/**Change l'ID de l'Equipe
	 * 	@param valeur d'ID avec lequel remplacer
	 * */
	public void setIdEquipe(int id) {
		this.idEquipe = id;
	}

	@Override
	public String toString() {
		return nom+" ("+nationalite+")";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dispose, idEquipe, joueurs, nationalite, nom, pointsSaison, rangSaisonPrecedante);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipe other = (Equipe) obj;
		return dispose == other.dispose && idEquipe == other.idEquipe && joueurs.containsAll(other.joueurs)
				&& other.joueurs.containsAll(joueurs) && nationalite == other.nationalite &&
				nom.equals(other.nom) && pointsSaison == other.pointsSaison
				&& rangSaisonPrecedante == other.rangSaisonPrecedante;
	}

}
