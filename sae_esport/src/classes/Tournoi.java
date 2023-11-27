package classes;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.sql.Date;

public class Tournoi {
	
	private String nomTournoi;
	private String dateDebut;
	private String dateFin;
	private String login;
	private String mdp;
	private Notoriete notoriete;
	private EtatTournoi etat;
	private int idTournoi;
	private List<Match> matches;
	private Map<Equipe, Integer> participants;
	private Optional<Equipe> vainqueur;

	public Tournoi(int idTournoi, String nomTournoi, String dateDebut, String dateFin, Notoriete notoriete, EtatTournoi etat) {
		this.idTournoi = idTournoi;
		this.nomTournoi = nomTournoi;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
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
	public Date getDateDebut() throws ParseException {
		return getDate(this.dateDebut);
	}
	
	//Donne la date de fin du tournoi
	public Date getDateFin() throws ParseException {
		return getDate(this.dateFin);
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
	
	//
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
		System.out.println(this.mdp);
	}
	
	private char generateLetter() {
		Random r = new Random();
		return (char)(r.nextInt(26) + 'a');
	}
	
	private static Date getDate(String date) throws ParseException {
		return new Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime());
	}
	
	
	
}
