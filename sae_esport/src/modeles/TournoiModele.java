package modeles;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import DAOs.TournoiDAO;
import classes.Equipe;
import classes.EtatTournoi;
import classes.Match;
import classes.Notoriete;

public class TournoiModele {
	
	private TournoiDAO tournoiDAO;
	//Représente le nom ou le titre du Tournoi
	private String nomTournoi;
	
	//Représente les dates d'ouvertures et de fermeture du Tournoi
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

	/**
	 * Constructeur de la classe Tournoi
	 * 	@param ID du tournoi
	 * 	@param nom du tournoi
	 * 	@param date de début du tournoi, date de son ouverture
	 * 	@param date de fin, date de sa fermeture
	 * 	@param notoriété du Tournoi
	 * 	@param Etat d'ouverture ou fermeture du Tournoi
	 * */
	public TournoiModele(int idTournoi, String nomTournoi, String dateDebut, String dateFin, Notoriete notoriete, EtatTournoi etat) {
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
		
		this.tournoiDAO = new TournoiDAO();
	}
	
	public TournoiModele() {		
		this.matches= new ArrayList<>();
		this.participants = new HashMap<>();
		this.vainqueur = Optional.empty();
		
		this.generateLogin();
		this.generateMdp();
		
		this.tournoiDAO = new TournoiDAO();
	}
	
	/**Donne l'id du tournoi
	 * */
	public int getIDTournoi() {
		return this.idTournoi;
	}
	/**Change l'id du tournoi
		@param id à remplacer 
	*/
	public void setIDTournoi(int id) {
		this.idTournoi = id;
	}
	
	/**
	 * Donne le nom du tournoi
	 * 
	 * */
	public String getNomTournoi() {
		return this.nomTournoi;
	}
	/**Change le nom du tournoi
	 * @param nom à remplacer
	 * */
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
	
	/**Change la Notoriete du tournoi
	 * 	@param la valeur de notoriété avec lequel changer
	 * */
	public void setNotoriete(Notoriete not) {
		this.notoriete = not;
	}
	
	//Donne l'etat du tournoi
	public EtatTournoi getEtatTournoi() {
		return this.etat;
	}
	/**Change l'etat du tournoi
	 * 	@param la valeur de l'état du tournoi avec lequel changer
	 * */
	public void setEtatTournoi(EtatTournoi etat) {
		this.etat = etat;
	}
	
	//Donne la liste des match du tournoi
	public List<Match> getMatchs(){
		return this.matches;
	}
	
	/**Créé un nouveau match pour le tournoi, 
	* 	@param la valeur de l'ID du match créée
	* 	@param le booléan précisant s'il sagit d'une finale, ou non
	 * */
	public void nouveauMatch(int idMatch, boolean finale) {
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
	
	//Attribue les points à une Equipe
	public void majPointsEquipe(Equipe equipeARemplacer, int points) {
		this.participants.put(equipeARemplacer, points);
	}

	//Donne le Login du Tournoi
	public String getLogin() {
		return this.login;	}
	
	//Donne le Mot de Passe du Tournoi
	public String getMotDePasse() {
		return this.mdp;
	}

	//Donne l'Equipe vainqueur du Tournoi, 
	//retourne un false si aucuns vainqueur n'est encore attribué
	public Optional<Equipe> getVainqueur() {
		return this.vainqueur;
	}
	
	//Génère un Login pour le Tournoi,
	//l'ensemble est basé sur le NomTournoi + idTournoi, en UpperCase + 3 lettres générés au hasard
	private void generateLogin() {
		this.login = this.nomTournoi.substring(0, 2).toUpperCase() + this.idTournoi + this.generateLetter()+ this.generateLetter() + this.generateLetter();
		System.out.println(this.login);
	}
	
	//Génère un Mot de Passe pour le Tournoi
	private void generateMdp() {
		this.mdp = "";
		for (int i = 0; i<12; i++) {
			this.mdp += this.generateLetter();
		}
		System.out.println(this.mdp);
	}
	
	//Renvois une lettre au hasard 
	//Utilisé pour la génération de Mot de passe et ID du Tournoi
	private char generateLetter() {
		Random r = new Random();
		return (char)(r.nextInt(26) + 'a');
	}
	
	private static Date getDate(String date) throws ParseException {
		return new Date(new SimpleDateFormat("dd/mm/yyyy").parse(date).getTime());
	}
	
	
	// a tester
	public boolean isNonDupe() throws Exception {
		// El torn "torn" n'est pas damns a base den dons damns cite function
		for (TournoiModele t : tournoiDAO.getAll()) {
			if (t.getNomTournoi().equals(getNomTournoi())) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean isDateFinSupADateDebut() throws ParseException {
		return getDateFin().compareTo(getDateDebut()) == 1;
	}
	
	public boolean isDateFinDateDebutDifferenceInfA30Jours() throws ParseException {
		// 30 jours = 2592000000 milisecond
		
		return getDateFin().getTime() - getDateDebut().getTime() < 2592000000L;
	}
	
	public boolean isTournoiNonSuperpose() throws Exception {
		return true;
	}

}
