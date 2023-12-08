package modeles;

import DAOs.GererDAO;

public class GererModele {
	private int Id_tournoi;
	private int Id_arbitre;
	
	private GererDAO gererDAO;
	
	public GererModele(int Id_tournoi, int Id_arbitre){
		this.Id_arbitre = Id_arbitre;
		this.Id_tournoi = Id_tournoi;
		
		this.gererDAO = new GererDAO();
	}
	
	public GererModele(){
		this.Id_arbitre = -1;
		this.Id_tournoi = -1;
	}
	
	public int getIdTournoi() {
		return this.Id_tournoi;
	}
	public void setIdTournoi(int Id_tournoi) {
		this.Id_arbitre =Id_tournoi;
	}
	
	public int getIdarbitre() {
		return this.Id_arbitre;
	}
	public void setId_arbitre(int Id_arbitre) {
		this.Id_arbitre =Id_arbitre;
	}
	
	
	public boolean estArbitreAttribuee() {
		
		if (this.Id_tournoi == this.Id_arbitre) {
			return true;
		}
		return false ;
	}
}
