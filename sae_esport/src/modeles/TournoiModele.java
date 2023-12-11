package modeles;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import DAOs.TournoiDAO;
import classes.Arbitre;
import classes.Equipe;
import classes.EtatTournoi;
import classes.Match;
import classes.Nationalite;
import classes.Notoriete;

public class TournoiModele {
		
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
	private List<Arbitre> arbitres;

	/**
	 * Constructeur de la classe Tournoi
	 * 	@param ID du tournoi
	 * 	@param nom du tournoi
	 * 	@param date de début du tournoi, date de son ouverture
	 * 	@param date de fin, date de sa fermeture
	 * 	@param notoriété du Tournoi
	 * 	@param Etat d'ouverture ou fermeture du Tournoi
	 * */
	public TournoiModele(int idTournoi, String nomTournoi, String dateDebut, String dateFin, String login, String mdp, Notoriete notoriete, EtatTournoi etat) {
		this.idTournoi = idTournoi;
		this.nomTournoi = nomTournoi;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.notoriete = notoriete;
		this.etat = etat;
		this.matches= new ArrayList<>();
		this.participants = new HashMap<>();
		this.vainqueur = Optional.empty();
		this.arbitres = new ArrayList<>();
		this.mdp = mdp;
		this.login = login;
	}
	
	public TournoiModele() {		
		this.matches= new ArrayList<>();
		this.participants = new HashMap<>();
		this.vainqueur = Optional.empty();
		this.arbitres = new ArrayList<>();
	}
	
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
		this.arbitres = new ArrayList<>();
		this.generateLogin();
		this.generateMdp();
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
	
	public void setDateDebut(String dateD) {
		this.dateDebut = dateD;
	}
	
	public void setDateFin(String dateF) {
		this.dateFin = dateF;
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
	
	@Override
	public String toString() {
		return this.idTournoi+": "+this.nomTournoi;
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
	
	public void ajouterArbitre(Arbitre arbitre) {
		this.arbitres.add(arbitre);
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
		return new Date(new SimpleDateFormat("dd/MM/yyyy").parse(date).getTime());
	}
	
	
	// a tester
	public boolean isNonDupe() throws Exception {
		// El torn "torn" n'est pas damns a base den dons damns cite function
		for (TournoiModele t : TournoiDAO.getInstance().getAll()) {
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
		for (TournoiModele t : TournoiDAO.getInstance().getAll()) {
			if ((getDateDebut().compareTo(t.getDateDebut()) >= 0 && getDateDebut().compareTo(t.getDateFin()) < 0) 
		            && (getDateFin().compareTo(t.getDateDebut()) > 0 && getDateFin().compareTo(t.getDateFin()) <= 0) 
		           && (t.getDateDebut().compareTo(getDateDebut()) >= 0 && t.getDateDebut().compareTo(getDateFin()) < 0)) {
		            return false;
			}
		}
		
		return true;
		
	}
	
	public String getDateString(java.util.Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH)+"/"+c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR);
	}
	
	public boolean isTournoiValide() {
		try {
			return this.isDateFinDateDebutDifferenceInfA30Jours() && this.isDateFinSupADateDebut()
					&& this.isNonDupe() && this.isTournoiNonSuperpose();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	// a tester
	public boolean isTournoiMinimum4EquipeDisposee() {		
		return participants.keySet().stream().filter(e -> e.getDispose()).count() >= 4;
	}
	
	public boolean isTournoiMinimum1Arbitre() {
		return arbitres.size() >= 1;
	}
	
	public boolean isDateCouranteDansCreneauTournoi() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		
		Calendar calendar2 = Calendar.getInstance();
		//System.out.println(calendar2.get(Calendar.DAY_OF_MONTH));
		//System.out.println(calendar2.get(Calendar.MONTH));
		//System.out.println(calendar2.get(Calendar.YEAR));
		
		System.out.println(currentDate);
		System.out.println(getDateDebut());
		System.out.println(getDateFin());
		
		System.out.println(currentDate.compareTo(getDateDebut()));
		System.out.println(getDateFin().compareTo(currentDate));
		
		return currentDate.compareTo(getDateDebut()) != -1 && getDateFin().compareTo(currentDate) != -1;
	}
	
	public static void main(String[] args) throws ParseException {
		TournoiModele t = new TournoiModele();
		t.setDateDebut("20/12/2020");
		t.setDateFin("25/12/2023");
		t.ajouterEquipe(new Equipe(1, "e1", Nationalite.FR, true, 1000, 100));
		t.ajouterEquipe(new Equipe(2, "e2", Nationalite.FR, true, 1000, 100));
		t.ajouterEquipe(new Equipe(3, "e3", Nationalite.FR, true, 1000, 100));
		t.ajouterEquipe(new Equipe(4, "e4", Nationalite.FR, true, 1000, 100));
		t.ajouterEquipe(new Equipe(5, "e5", Nationalite.FR, false, 1000, 100));
		
		System.out.println(t.isTournoiMinimum4EquipeDisposee());
		
		System.out.println(t.isTournoiMinimum1Arbitre());
		t.ajouterArbitre(new Arbitre(1, "Alfred", "Moukhamedov", Nationalite.RU));
		System.out.println(t.isTournoiMinimum1Arbitre());
		t.ajouterArbitre(new Arbitre(2, "En", "Moukhamedov", Nationalite.RU));
		System.out.println(t.isTournoiMinimum1Arbitre());
		
		System.out.println(t.isDateCouranteDansCreneauTournoi());
	}
	
	public boolean isTournoiOuvrable() {
		return true;
	}

}
