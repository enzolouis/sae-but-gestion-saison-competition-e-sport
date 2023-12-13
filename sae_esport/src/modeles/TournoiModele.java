package modeles;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import DAOs.ParticiperDAO;
import DAOs.TournoiDAO;
import classes.Arbitre;
import classes.Equipe;
import classes.EtatTournoi;
import classes.Match;
import classes.Nationalite;
import classes.Notoriete;
import classes.Participer;

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
	private List<Equipe> participantsIndisposees;
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
		this.participantsIndisposees = new ArrayList<>();
		this.vainqueur = Optional.empty();
		this.arbitres = new ArrayList<>();
		this.mdp = mdp;
		this.login = login;
	}
	
	public TournoiModele() {		
		this.matches= new ArrayList<>();
		this.participants = new HashMap<>();
		this.participantsIndisposees = new ArrayList<>();
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
		this.participantsIndisposees = new ArrayList<>();
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
		if (equipe.getDispose()) {
			this.participants.put(equipe, 0);
		} else {
			this.participantsIndisposees.add(equipe);
		}
		
	}
	
	public void supprimerEquipe(Equipe equipe) {
		this.participants.remove(equipe);
		this.participantsIndisposees.add(equipe);
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
	
	public List<Arbitre> getArbitres() {
		return this.arbitres;
	}
	
	public Map<Equipe,Integer> getEquipes() {
		return this.participants;
	}
	
	//Génère un Login pour le Tournoi,
	//l'ensemble est basé sur le NomTournoi + idTournoi, en UpperCase + 3 lettres générés au hasard
	private void generateLogin() {
		this.login = this.nomTournoi.substring(0, 2).toUpperCase() + this.idTournoi + this.generateLetter()+ this.generateLetter() + this.generateLetter();
		//System.out.println(this.login);
	}
	
	//Génère un Mot de Passe pour le Tournoi
	private void generateMdp() {
		this.mdp = "";
		for (int i = 0; i<12; i++) {
			this.mdp += this.generateLetter();
		}
		//System.out.println(this.mdp);
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
		// System.out.println(getDateFin().compareTo(getDateDebut()) == 1);
		return getDateFin().after(getDateDebut());
	}
	
	public boolean isDateFinDateDebutDifferenceInfA30Jours() throws ParseException {
		// 30 jours = 2592000000 milisecond
		
		return getDateFin().getTime() - getDateDebut().getTime() < 2592000000L;
	}
	
	public boolean isTournoiNonSuperpose() throws Exception {
		for (TournoiModele t : TournoiDAO.getInstance().getAll()) {
			// si (la date de debut est plus grande ou egal à la date de début d'un autre tournoi 
			//     ET la date début est plus petite que la date de fin de l'autre tournoi) (*** ou egal ??)
			// OU (la date de fin est plus grande que la date de debut d'un autre tournoi
			//    (ET la date de fin eest plus petit ou egal a la date de fin d'un autre tournoi)
			// OU (la date de debut d'un tournoi est plus grande ou egal a la date de debut
			//    (ET la date de debut d'un tournoi est plus petit que la date de fin
			
			// before et after existe...
			
			if ((getDateDebut().compareTo(t.getDateDebut()) >= 0 && getDateDebut().compareTo(t.getDateFin()) < 0) 
		            || (getDateFin().compareTo(t.getDateDebut()) > 0 && getDateFin().compareTo(t.getDateFin()) <= 0) 
		           || (t.getDateDebut().compareTo(getDateDebut()) >= 0 && t.getDateDebut().compareTo(getDateFin()) < 0)) {
		            return false;
			}
		}
		
		return true;
	}
	
	public String getDateString(java.util.Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH)+"/"+(Integer.valueOf(c.get(Calendar.MONTH))+1)+"/"+c.get(Calendar.YEAR);
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
	
	public boolean isTournoiMinimum4EquipeDisposee() {		
		return participants.keySet().stream().filter(e -> e.getDisposition()).count() >= 4;
	}
	
	public boolean isTournoiMinimum1Arbitre() {
		return arbitres.size() >= 1;
	}
	
	public boolean isDateCouranteDansCreneauTournoi() throws ParseException {
		LocalDate currentDate = LocalDate.now();

        Date sqlDate = Date.valueOf(LocalDateTime.of(currentDate, LocalTime.MIDNIGHT).toLocalDate());
        
		return sqlDate.compareTo(getDateDebut()) != -1 && getDateFin().compareTo(sqlDate) != -1;
	}
	
	public static boolean isAucunTournoiOuvert() throws Exception {
		for (TournoiModele t : TournoiDAO.getInstance().getAll()) {
			if (t.getEtatTournoi() == EtatTournoi.OUVERT) {
				return false;
			}
		}
		
		return true;
	}
	
	public void supprimerEquipeIndisposees() throws Exception {
		for (Equipe e : this.participantsIndisposees) {
			ParticiperDAO.getInstance().delete(new Participer(e.getIdEquipe(), this.getIDTournoi(), 0)); // 0 : arbitraire
		}
	}
	
	public boolean isTournoiOuvrable() throws ParseException, Exception {
		return this.isTournoiMinimum4EquipeDisposee() 
				&& this.isTournoiMinimum1Arbitre() 
				&& TournoiModele.isAucunTournoiOuvert() 
				&& this.isDateCouranteDansCreneauTournoi();
	}
	
	public void ouvrirTournoi() throws Exception {
		if (this.isTournoiOuvrable()) {
			this.supprimerEquipeIndisposees();
			this.setEtatTournoi(EtatTournoi.OUVERT);
			TournoiDAO.getInstance().update(this);
		}
	}

}
