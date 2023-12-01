package classes;

public class Participer {
	private int IDEquipe;
	private int IDTournoi;
	private int resultat;
	
	public Participer(int equipe, int tournoi, int resultat) {
		this.IDEquipe = equipe;
		this.IDTournoi= tournoi;
		this.resultat = resultat;
	}
	
	public int getIdEquipe() {
		return this.IDEquipe;
	}
	public void setIdEquipe(int equipe) {
		this.IDEquipe = equipe;
	}
	
	public int getIdTournoi() {
		return this.IDTournoi;
	}
	public void setIdTournoi(int tournoi) {
		this.IDTournoi = tournoi;
	}
	
	public int getResultat() {
		return this.resultat;
	}
	public void setResultat(int resultat) {
		this.resultat = resultat;
	}
}
