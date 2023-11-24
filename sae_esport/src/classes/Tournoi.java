package classes;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class Tournoi {
	
	private String nomTournoi;
	private String DateDebut;		//format : "dd/MM/YYYY"
	private String DateFin;
	private String login;
	private String mdp;
	private Notoriete notoriete;
	private EtatTournoi etat;
	private int idTournoi;
	private List<Match> matches;
	private Map<Equipe, Integer> participants;
	private Optional<Equipe> vainqueur;

	public Tournoi(int idTournoi, String nomTournoi, String string,
			String string2, Notoriete notoriete, EtatTournoi etat) {
		
		this.idTournoi = idTournoi;
		this.nomTournoi = nomTournoi;
		this.DateDebut = string;
		this.DateFin = string2;
		this.notoriete = notoriete;
		this.etat = etat;
		this.matches= new ArrayList<>();
		this.participants = new HashMap<>();
		this.vainqueur = Optional.empty();
		this.generateLogin();
		this.generateMdp();
		
	}
	
	//Donne l'id du tournoi
	public int getIDTournoi() {
		return this.idTournoi;
	}
	//Change l'id du tournoi
	public void setIDTournoi(int id) {
		this.idTournoi = id;
	}
	
	//Donne le nom du tournoi
	public String getNomTournoi() {
		return this.nomTournoi;
	}
	//Change le nom du tournoi
	public void setNomTournoi(String nom) {
		this.nomTournoi = nom;
	}
	
	//Donne la date de début du tournoi
	public String getDateDebut() {
		return this.DateDebut;
	}
	//Change la date de début du tournoi
	public void setDateDebut(String dateDebut) {
		this.DateDebut = dateDebut;
	}
	
	//Donne la date de fin du tournoi
	public String getDateFin() {
		return this.DateFin;
	}
	//Change la date de fin du tournoi
	public void setDateFin(String dateFin) {
		this.DateFin = dateFin;
	}
	
	//Donne la Notoriete du tournoi
	public Notoriete getNotoriete() {
		return this.notoriete;
	}
	
	//Change la Notoriete du tournoi
	public void setNotoriete(Notoriete not) {
		this.notoriete = not;
	}
	
	//Donne l'etat du tournoi
	public EtatTournoi getEtat_Tournoi() {
		return this.etat;
	}
	//DChange l'etat du tournoi
	public void setEtat_Tournoi(EtatTournoi etat) {
		this.etat = etat;
	}
	
	//Donne la liste des match du tournoi
	public List<Match> getMatchs(){
		return this.matches;
	}
	//Créé un nouveau tournoi, 
	//avec id et booléen donné
	public void NouveauMatch(int idMatch, boolean finale) {
		this.matches.add(new Match(idMatch, finale));
	}
	
	
	
	//Donne la map de participant
	public Map<Equipe, Integer> getParticipants(){
		return this.participants;
	}
	
	//Ajoute une Equipe donné dans la liste des participans
	public void ajouterEquipe(Equipe equipe) {
		this.participants.put(equipe, 0);
	}
	
	public void majPointsEquipe(Equipe equipeARemplacer, int points) {
		this.participants.put(equipeARemplacer, points);
	}

	public String getLogin() {
		return this.login;	}

	public String getMotDePasse() {
		return this.mdp;
	}

	public Optional<Equipe> getVainqueur() {
		return this.vainqueur;
	}
	
	private void generateLogin() {
		this.login = this.nomTournoi.substring(0, 2).toUpperCase() + this.idTournoi + this.generateLetter()+ this.generateLetter() + this.generateLetter();
		System.out.println(this.login);
	}
	
	private void generateMdp() {
		this.mdp = "";
		for (int i = 0; i<12; i++) {
			this.mdp += this.generateLetter();
		}
	}
	
	private char generateLetter() {
		Random r = new Random();
		return (char)(r.nextInt(26) + 'a');
	}
	
}
