package classes;

import java.util.ArrayList;
import java.util.List;

public class Tournoi {
	public enum EtatTournoi {
		OUVERT,FERME
	}
	public enum Notoriete{
		LOCAL,
		REGIONAL,
		NATIONAL,
		INTERNATIONAL,
		INTERNATIONAL_CLASSE
	}
	
	private String nomTournoi;
	private String StringDebut;		//format : "dd/MM/YYYY"
	private String StringFin;
	private Notoriete notoriete;
	private EtatTournoi etat;
	private String login;
	private String motDePasse;
	private int idTournoi;
	private List<Match> matches;
	private List<Equipe> equipes;

	public Tournoi(String nomTournoi, String StringDebut,String StringFin,
					Notoriete notoriete, EtatTournoi etat,
					String motDePasse, int idTournoi, List<Equipe> equipes) {
		this.idTournoi = idTournoi;
		this.nomTournoi = nomTournoi;
		this.StringDebut =StringDebut;
		this.StringFin = StringFin;
		this.notoriete = notoriete;
		this.etat = etat;
		this.motDePasse = motDePasse;
		this.matches= new ArrayList<>();
		
		this.equipes = new ArrayList<>();
		for(Equipe e : equipes) {
			this.equipes.add(e);
		}
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
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getMotDePasse() {
		return this.motDePasse;
	}
	public void setTournoiMDP(String MDP) {
		this.motDePasse = MDP;
	}
	
	public String getDateDebut() {
		return this.StringDebut;
	}
	public void setDateDebut(String dateDebut) {
		this.StringDebut = dateDebut;
	}
	
	public String getDateFin() {
		return this.StringFin;
	}
	public void setDateFin(String dateFin) {
		this.StringDebut = dateFin;
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
