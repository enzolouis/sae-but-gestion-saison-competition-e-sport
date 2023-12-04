package classes;

public class Gerer {
	private int Id_tournoi;
	private int Id_arbitre;
	
	public Gerer(int Id_tournoi, int Id_arbitre){
		this.Id_arbitre = Id_arbitre;
		this.Id_tournoi = Id_tournoi;
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
}
