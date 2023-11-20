package classes;

import java.util.ArrayList;
import java.util.List;

public class Match {
	private int idMatch;
	private boolean finale;
	private List<Equipe> equipes;
	
	public Match(int idMatch,boolean finale) {
		this.finale = finale;
		this.idMatch = idMatch;
		this.equipes = new ArrayList<>();
	}
	
	public int getIDMatch() {
		return this.idMatch;
	}
	public boolean IsItFinale() {
		return this.finale;
	}
	
	public void AddEquipe(Equipe equipe) {
		this.equipes.add(equipe);
	}
	public List<Equipe> getEquipes(){
		return this.equipes;
	}
	public void remplacerEquipe(Equipe equipeARemplacer, Equipe EquipeAMettre) {
		for (Equipe i : this.equipes) {
			if(i == equipeARemplacer) {
				i = EquipeAMettre;
			}
		}
	}
}
