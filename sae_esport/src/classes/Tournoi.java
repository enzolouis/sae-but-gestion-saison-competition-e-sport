package classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tournoi {
	private enum Etat_Tournoi {
		OUVERT,FERME
	}
	private enum Notoriete{
		LOCAL,
		REGIONAL,
		NATIONAL,
		INTERNATIONAL,
		INTERNATIONAL_CLASSE
	}
	
	private String nomTournoi;
	private Date dateDebut;
	private Date dateFin;
	private Notoriete notoriete;
	private Etat_Tournoi etat;
	private String motDePasse;
	private int idTournoi;
	private List<Match> matches;
	private List<Equipe> equipes;
	
	public Tournoi(String nomTournoi, Date dateDebut,Date DateFin,
					Notoriete notoriete, Etat_Tournoi etat,
					String motDePasse, int idTournoi, List<Equipe> equipes) {
		this.nomTournoi = nomTournoi;
		this.dateDebut =dateDebut;
		this.dateFin = DateFin;
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
	
	public String getTournoiMDP() {
		return this.motDePasse;
	}
	public void setTournoiMDP(String MDP) {
		this.motDePasse = MDP;
	}
	
	public Date getDateDebut() {
		return this.dateDebut;
	}
	public void setDateDebut(Date Datedebut) {
		this.dateDebut = Datedebut;
	}
	
	public Date getDateFin() {
		return this.dateFin;
	}
	public void setDateFin(Date DateFin) {
		this.dateDebut = DateFin;
	}
	
	public Notoriete getNotoriete() {
		return this.notoriete;
	}
	public void setNotoriete(Notoriete not) {
		this.notoriete = not;
	}
	
	public Etat_Tournoi getEtat_Tournoi() {
		return this.etat;
	}
	public void setEtat_Tournoi(Etat_Tournoi etat) {
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
