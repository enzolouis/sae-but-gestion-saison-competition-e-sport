package classes;

import java.util.ArrayList;
import java.util.List;

public class Equipe {
	
	private int idEquipe;
	private String nom;
	private Nationalite nationalite;
	private List<Joueur> joueurs;
	private boolean dispose;
	private int rangSaisonPrecedante;
	private int pointsSaison;
	
	
	public Equipe(int id, String nom, Nationalite nat, List<Joueur> joueurs,boolean dispose, int rangSaisonPrecedante, int pointsSaison) {
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

	public String getNom() {
		return this.nom;
	}
	public Nationalite getNationalite() {
		return this.nationalite;
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
	
	public int getRangSaisonPrecedante(){
		return this.rangSaisonPrecedante;
	}

	public int getIdEquipe() {
		return this.idEquipe;
	}

}
