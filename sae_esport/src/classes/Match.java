package classes;

import java.util.ArrayList;
import java.util.List;

public class Match {
	private int idMatch;
	private boolean finale;
	private List<Equipe> equipes;
	private int idVainqueur;
	
	public Match(int idMatch,boolean finale) {
		this.finale = finale;
		this.idMatch = idMatch;
		this.equipes = new ArrayList<>();
	}
	
	//Retourne l'ID du match
	public int getIDMatch() {
		return this.idMatch;
	}
	/**Change l'ID du Match
	 * 	@param valeur de remplacement de l'ID du Match
	 * */
	public void setIdMatch(int id) {
		this.idMatch = id;
	}
	
	//Retourne le boolean exprimant si le match est une finale ou non
	public boolean IsItFinale() {
		return this.finale;
	}
	
	/**Ajoute une Equipe au match
	 * 	@param l'Equipe à ajouter au match
	 * */
	public void AddEquipe(Equipe equipe) {
		this.equipes.add(equipe);
	}
	
	//Retourne les Equipes participant au Match
	public List<Equipe> getEquipes(){
		return this.equipes;
	}
	
	/**Remplace une des Equipes du Match
	 * 	@param Equipe deja en place, qui est à remplacer
	 * 	@param Equipe qui va remplacer la 1e valeur
	 * */
	public void remplacerEquipe(Equipe equipeARemplacer, Equipe EquipeAMettre) {
		for (Equipe i : this.equipes) {
			int y = 0; 
			if(i.equals(equipeARemplacer)) {
				this.equipes.remove(y);
				this.equipes.add(EquipeAMettre);
			}
			y++;
		}
	}
	
	public void setVainqueur(int idVainqueur) {
		this.idVainqueur = idVainqueur;
	}
	
	public int getVainqueur() {
		return idVainqueur;
	}
}
