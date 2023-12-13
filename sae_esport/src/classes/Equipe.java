package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Equipe {
	
	private int idEquipe;
	private String nom;
	private Nationalite nationalite;
	private List<Joueur> joueurs;
	private boolean dispose;
	private int rangSaisonPrecedante;
	private int pointsSaison;
	
	
	
	/**Constructeur, SANS une liste de joueurs déja donné
	*	@param l'ID de l'Equipe
	*	@param la Nationalite de l'Equipe
	*	@param la Disposition d'une Equipe, en boolean
	*	@param le Rang de l'Equipe sur leurs Saison précédente
	*	@param leurs valeur de Points sur la Saison courante
	*/
	public Equipe(int id, 
					String nom, 
					Nationalite nat,
					boolean dispose, 
					int rangSaisonPrecedante, 
					int pointsSaison) {
		this.idEquipe = id;
		this.nom = nom;
		this.nationalite = nat;
		this.dispose = dispose;
		this.pointsSaison = pointsSaison;
		this.rangSaisonPrecedante =rangSaisonPrecedante;
		this.joueurs = new ArrayList<>();
		
	}
	
	/**Constructeur, AVEC une liste de joueurs déja donné
	*	@param l'ID de l'Equipe
	*	@param la Nationalite de l'Equipe
	*	@param la Disposition d'une Equipe, en boolean
	*	@param une Liste de Joueurs attribué à l'Equipe
	*	@param le Rang de l'Equipe sur leurs Saison précédente
	*	@param leurs valeur de Points sur la Saison courante
	*/
	public Equipe(int id, 
					String nom, 
					Nationalite nat, 
					List<Joueur> joueurs,
					boolean dispose, 
					int rangSaisonPrecedante, 
					int pointsSaison) {
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

	//Retourne le nom l'Equipe
	public String getNom() {
		return nom;
	}
	/**
	 * Change le nom de l'Equipe
	 * 	@param le nom de l'Equipe à remplacer
	 * */
	public void setNom(String nom) {
		this.nom = nom;
	}
		
	//Retourne la Nationalite de l'Equipe
	public Nationalite getNationalite() {
		return nationalite;
	}
	/**
	 * Change la Nationalite de l'Equipe
	 * 	@param la valeur Nationalite avec lequel remplacer l'Equipe
	 * */
	public void setNationalite(Nationalite nationalite) {
		this.nationalite = nationalite;
	}
	
	public boolean getDisposition() {
		return this.dispose;
	}
	public void setDisposition(boolean disposition) {
		this.dispose = disposition;
	}
	
	//Retourne la liste de joueurs
	public List<Joueur> getListeJoueurs(){
		return this.joueurs;
	}
	public void AjouterJoueurs(Joueur joueur){
		this.joueurs.add(joueur);
	}
	
	public int getPointsSaison(){
		return this.pointsSaison;
	}
	//Fixe une valeur de points donné, peut être utilisé pour la correction de scores
	public void setPointsSaison(int pointsSaison){
		this.pointsSaison = pointsSaison;
	}
	//Ajout de points à l'equipe, différencier avec le set
	public void ajoutDePoints(int points){
		this.pointsSaison += points;
	}
	
	//Retourne le Rang de la Saison précendente de l'Equipe
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
	
	//Retourne l'ID de l'Equipe
	public boolean getDispose() {
		return this.dispose;
	}

	/**Change l'ID de l'Equipe
	 * 	@param valeur d'ID avec lequel remplacer
	 * */
	public void setDispose(boolean d) {
		this.dispose = d;
	}

	@Override
	public int hashCode() {
		return Objects.hash(joueurs, nom);
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
		return Objects.equals(joueurs, other.joueurs) && Objects.equals(nom, other.nom);
	}

	@Override
	public String toString() {
		String s = "Equipe N°"+idEquipe+": "+nom+" ("+nationalite+")\n";
		for (Joueur j : this.joueurs) {
			s += "Joueur ["+j.getIdJoueur()+"]: "+j.getPseudo()+"\n";
		}
		s += "Rang précédent: "+rangSaisonPrecedante+" Points de la saison:"+pointsSaison+"\n";
		if (dispose) {
			s+= "Cette équipe est disposée";
		} else {
			s+= "Cette équipe est actuellement indisposée";
		}
		return s;
	}

}
