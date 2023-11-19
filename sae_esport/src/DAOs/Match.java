package DAOs;

public class Match {
	private int idMatch;
	private boolean finale;
	
	public Match(int idMatch,boolean finale) {
		this.finale = finale;
		this.idMatch = idMatch;
	}
	
	public int getIDMatch() {
		return this.idMatch;
	}
	public boolean IsItFinale() {
		return this.finale;
	}
}
