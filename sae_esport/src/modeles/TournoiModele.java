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
import java.util.Objects;
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
		
	//identifiant et titre du tournoi
	private int idTournoi;
	private String nomTournoi;
	//dates d'ouvertures et de fermeture du Tournoi
	private String dateDebut;
	private String dateFin;
	//login d'arbitre
	private String login;
	private String mdp;
	//notoriete du tournoi
	private Notoriete notoriete;
	//etat du tournoi
	private EtatTournoi etat;
	//liste des matchs sur tournoi 
	private List<Match> matches;
	private Match finale;
	//dictionnaire des participants du tournoi avec l'équipe
	//en clé et les points de l'équipe en valeur
	private Map<Equipe, Integer> participants;
	//liste contenant les équipes indisposées
	private List<Equipe> participantsIndisposees;
	//optional contenant le vainqueur
	private Optional<Equipe> vainqueur;
	//liste contenant tous les arbitres assignés au tournoi
	private List<Arbitre> arbitres;

	/**
	 * Constructeur de la classe Tournoi
	 * 	@param id du tournoi
	 * 	@param nom du tournoi
	 * 	@param date de début du tournoi, date de son ouverture
	 * 	@param date de fin, date de sa fermeture
	 *  @param login utilisé par les arbitres 
	 *  @param mdp utilisé par les aribtres 
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
	
	//getters et setters de l'identifiant du tournoi
	public int getIDTournoi() {
		return this.idTournoi;
	}
	public void setIDTournoi(int id) {
		this.idTournoi = id;
	}

	//getters et setter du nom du tournoi
	public String getNomTournoi() {
		return this.nomTournoi;
	}
	public void setNomTournoi(String nom) {
		this.nomTournoi = nom;
	}
	
	//getters et setters de la date de début du tournoi 
	public Date getDateDebut() throws ParseException {
			return getDate(this.dateDebut);
	}
	public void setDateDebut(String dateD) {
		this.dateDebut = dateD;
	}
	
	//getters et setters de la date de fin du tournoi
	public Date getDateFin() throws ParseException {
		return getDate(this.dateFin);
	}
	public void setDateFin(String dateF) {
		this.dateFin = dateF;
	}
	
	//getters et setters de la notoriété du tournoi
	public Notoriete getNotoriete() {
		return this.notoriete;
	}
	public void setNotoriete(Notoriete not) {
		this.notoriete = not;
	}
	
	//getters et setters de l'état du tournoi 
	public EtatTournoi getEtatTournoi() {
		return this.etat;
	}
	public void setEtatTournoi(EtatTournoi etat) {
		this.etat = etat;
	}
	
	//getters et setters de la liste des matchs
	public List<Match> getMatchs(){
		return this.matches;
	}
	
	public Map<Equipe,Integer> getEquipes() {
		return this.participants;
	}
	
	public List<Arbitre> getArbitres() {
		return this.arbitres;
	}
	
	@Override
	public String toString() {
		String infos = getIDTournoi()+": "+getNomTournoi()+"("+getNotoriete()+")\n";
		try {
			infos += "Du "+getDateDebut()+" au "+getDateFin()+"\n";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		infos+="Liste des arbitres:\n";
		for (Arbitre a : getArbitres()) {
			infos+=a.toString();
			infos+="\n";
		}
		infos+="Liste des équipes:\n";
		for (Equipe e : getEquipes().keySet()) {
			infos+=e.toString();
			infos+="\n";
		}
		return infos;
	}
	
	/**Créé un nouveau match pour le tournoi, 
	* 	@param la valeur de l'ID du match créée
	* 	@param le booléan précisant s'il sagit d'une finale, ou non
	* */
	public void nouveauMatch(int idMatch, boolean finale) {
		if (finale) {
			this.finale = new Match(idMatch, finale);
		} else {
			this.matches.add(new Match(idMatch, finale));
		}
	}
	
	//Donne la map de participant
	public Map<Equipe, Integer> getParticipants(){
		return this.participants;
	}
	
	//Ajoute une Equipe donné dans la liste des participans
	public void ajouterEquipe(Equipe equipe, int resultat) {
		if (equipe.getDispose()) {
			this.participants.put(equipe, resultat);
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
			// /* = peut se positionner n'importe ou
			// 						19/01							  30/01
			//   					  |									|
			
			// Cas 1           			     |----------------------------------|
			// Cas 2  	|-----------------------------|
			// Cas 3  	|-------------------------------------------------------|
			// Cas 4		   					|------------|
			// Cas 4 déclaré optionnel :
			// || (getDateDebut().compareTo(t.getDateDebut()) >= 0 && getDateFin().compareTo(t.getDateFin()) <= 0)
			// quand un des deux premier cas est rempli ET que la pré-condition dateDébut < dateFin est rempli
			// , le cas 4 est forcément rempli		
			
			// pré-condition : date début < date fin
			
			
			if ((getDateDebut().compareTo(t.getDateDebut()) >= 0 && getDateDebut().compareTo(t.getDateFin()) <= 0) 
	                || (getDateFin().compareTo(t.getDateDebut()) >= 0 && getDateFin().compareTo(t.getDateFin()) <= 0) 
	                || (getDateDebut().compareTo(t.getDateDebut()) <= 0 && getDateFin().compareTo(t.getDateDebut()) >= 0)) {
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

	@Override
	public int hashCode() {
		return Objects.hash(idTournoi, nomTournoi, participants);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TournoiModele other = (TournoiModele) obj;
		return idTournoi == other.idTournoi && Objects.equals(nomTournoi, other.nomTournoi)
				&& Objects.equals(participants, other.participants);
	}
	
	

}
