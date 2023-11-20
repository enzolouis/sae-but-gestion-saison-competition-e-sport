package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tournoi {
	
	private String nomTournoi;
	private String DateDebut;		//format : "dd/MM/YYYY"
	private String DateFin;
	private Notoriete notoriete;
	private EtatTournoi etat;
	private String login;
	private String motDePasse;
	private int idTournoi;
	private List<Match> matches;
	private Map<Equipe, Integer> participants;

	public Tournoi(int idTournoi, String nomTournoi, String DateDebut,
			String DateFin, Notoriete notoriete, EtatTournoi etat) {
		
		this.idTournoi = idTournoi;
		this.nomTournoi = nomTournoi;
		this.DateDebut = DateDebut;
		this.DateFin = DateFin;
		this.notoriete = notoriete;
		this.etat = etat;
		this.matches= new ArrayList<>();
		this.participants = new HashMap<>();

	}
	
	public int getIDTournoi() {
		return this.idTournoi;
	}
	public void setIDTournoi(int id) {
		this.idTournoi = id;
	}
	
	public String getNomTournoi() {
		return this.nomTournoi;
	}
	public void setNomTournoi(String nom) {
		this.nomTournoi = nom;
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public String getMotDePasse() {
		return this.motDePasse;
	}
	
	public String getDateDebut() {
		return this.DateDebut;
	}
	public void setDateDebut(String dateDebut) {
		this.DateDebut = dateDebut;
	}
	
	public String getDateFin() {
		return this.DateFin;
	}
	public void setDateFin(String dateFin) {
		this.DateFin = dateFin;
	}
	
	public Notoriete getNotoriete() {
		return this.notoriete;
	}
	
	public void setNotoriete(Notoriete not) {
		this.notoriete = not;
	}
	
	public EtatTournoi getEtat_Tournoi() {
		return this.etat;
	}
	public void setEtat_Tournoi(EtatTournoi etat) {
		this.etat = etat;
	}
	
	public void NouveauMatch(int idMatch, boolean finale) {
		this.matches.add(new Match(idMatch, finale));
	}
	
	public Map<Equipe, Integer> getParticipants(){
		return this.participants;
	}
	
	public void ajouterEquipe(Equipe equipe) {
		this.participants.put(equipe, 0);
	}
	
	public void majPointsEquipe(Equipe equipeARemplacer, int points) {
		this.participants.put(equipeARemplacer, points);
	}
	
}
