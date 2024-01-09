package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Match {
	private int idMatch;
	private boolean finale;
	private List<Equipe> equipes;
	private int idVainqueur;
	private int idTournoi;
	
	public Match(int idMatch, int idTournoi, boolean finale) {
		this.finale = finale;
		this.idMatch = idMatch;
		this.idTournoi = idTournoi;
		this.equipes = new ArrayList<>();
		this.idVainqueur = 0;
	}
	
	public int getIdTournoi() {
		return idTournoi;
	}

	public void setIdTournoi(int idTournoi) {
		this.idTournoi = idTournoi;
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

	@Override
	public int hashCode() {
		return Objects.hash(equipes, finale, idMatch, idVainqueur);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Match other = (Match) obj;
		return (equipes.containsAll(other.equipes)) && (other.equipes.containsAll(equipes)) && 
				(finale == other.finale) && (idMatch == other.idMatch)
				&& idVainqueur == other.idVainqueur;
	}
}
