package modeles;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import DAOs.MatchDAO;
import DAOs.ParticiperDAO;
import DAOs.TournoiDAO;
import classes.Arbitre;
import classes.Disposition;
import classes.Equipe;
import classes.EtatTournoi;
import classes.Match;
import classes.Notoriete;
import classes.Participer;

public class TournoiModele {
		
	private int idTournoi;				//id de tournoi
	private String nomTournoi;			//nom de tournoi
	private String dateDebut;			//date de début de tournoi
	private String dateFin;				//date de fin de tournoi
	private String login;				//login d'arbitres
	private String mdp;					//mot de passe d'arbitres
	private Notoriete notoriete;		//notoriété du tournoi
	private EtatTournoi etat;			//état du tournoi
	private List<Match> matches;		//liste des matchs du tournoi
	private Match finale;				//finale du tournoi
	private Optional<Equipe> vainqueur; //vainqueur du tournoi
	private List<Arbitre> arbitres;		//liste de tous les arbitres du tournoi
	
	//dictionnaire des participants du tournoi avec l'équipe
	//en clé et les points de l'équipe en valeur
	private Map<Equipe, Integer> participants;
	//liste contenant les équipes indisposées
	private List<Equipe> participantsIndisposees;

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
	
	/**
	 * Constructeur d'un tournoi vide
	 * */
	public TournoiModele() {
		this.matches= new ArrayList<>();
		this.participants = new HashMap<>();
		this.participantsIndisposees = new ArrayList<>();
		this.vainqueur = Optional.empty();
		this.arbitres = new ArrayList<>();
	}
	
	/**
	 * Constructeur de la classe Tournoi générant les logins
	 * 	@param id du tournoi
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
		this.participantsIndisposees = new ArrayList<>();
		this.vainqueur = Optional.empty();
		this.arbitres = new ArrayList<>();
		this.generateLogin();
		this.generateMdp();
	}
	
	/**
	 * renvoie l'id du tournoi
	 * */
	public int getIDTournoi() {
		return this.idTournoi;
		
	/**
	 * set l'id du tournoi
	 * @param nouvel identifiant
	 * */
	}
	public void setIDTournoi(int id) {
		this.idTournoi = id;
	}

	/**
	 * renvoie le nom du tournoi
	 * */
	public String getNomTournoi() {
		return this.nomTournoi;
	}
	
	/**
	 * set le nom du tournoi
	 * @param nouveau nom
	 * */
	public void setNomTournoi(String nom) {
		this.nomTournoi = nom;
	}
	
	/**
	 * renvoie la date de début
	 * */
	public Date getDateDebut() throws ParseException {
		return getDate(this.dateDebut);
	}
	
	/**
	 * modifie la date de début
	 * @param nouvelle date de début
	 * */
	public void setDateDebut(String dateD) {
		this.dateDebut = dateD;
	}
	
	/**
	 * renvoie la date de fin
	 * */
	public Date getDateFin() throws ParseException {
		return getDate(this.dateFin);
	}
	
	/**
	 * renvoie la date de fin du tournoi
	 * @param nouvelle date de fin
	 * */
	public void setDateFin(String dateF) {
		this.dateFin = dateF;
	}
	
	/**
	 * renvoie la notoriété du tournoi
	 * */
	public Notoriete getNotoriete() {
		return this.notoriete;
	}
	
	/**
	 * modifie la notoriété du tournoi
	 * @param nouvelle notoriété
	 * */
	public void setNotoriete(Notoriete not) {
		this.notoriete = not;
	}
	
	/**
	 * renvoie l'état du tournoi
	 * */
	public EtatTournoi getEtatTournoi() {
		return this.etat;
	}
	
	/**
	 * modifie l'état du tournoi
	 * @param nouvel état
	 * */
	public void setEtatTournoi(EtatTournoi etat) {
		this.etat = etat;
	}
	
	/**
	 * renvoie la liste des matchs
	 * */
	public List<Match> getMatchs(){
		return this.matches;
	}
	
	/**
	 * renvoie la liste des arbitres
	 * */
	public List<Arbitre> getArbitres() {
		return this.arbitres;
	}
	
	@Override
	public String toString() {
		return getIDTournoi()+": "+getNomTournoi()+" ("+getNotoriete()+")\n";
	}
	
	/**Créé un nouveau match pour le tournoi, 
	* 	@param la valeur de l'ID du match créée
	* 	@param le booléan précisant s'il sagit d'une finale, ou non
	* */
	public void nouveauMatch(int idMatch, boolean finale) {
		if (finale) {
			this.finale = new Match(idMatch, this.idTournoi, finale);
		} else {
			this.matches.add(new Match(idMatch, this.idTournoi, finale));
		}
	}
	
	/**
	 * ajoute un match au tournoi
	 * @param nouveau match
	 * */
	public void ajouterMatch(Match m) {
		this.matches.add(m);
	}
	
	/**
	 * renvoie le dictionnaire des équipes participant avec leurs points
	 * */
	public Map<Equipe, Integer> getParticipants(){
		return this.participants;
	}
	
	/**
	 * ajoute une équipe au tournoi
	 * */
	public void ajouterEquipe(Equipe equipe, int resultat) {
		if (equipe.getDisposition().equals(Disposition.DISPOSEE)) {
			this.participants.put(equipe, resultat);
		} else {
			this.participantsIndisposees.add(equipe);
		}
		
	}
	
	/**
	 * rend une équipe indiposée
	 * @param équipe à indisposer
	 * */
	public void rendreIndisposé(Equipe equipe) {
		this.participants.remove(equipe);
		this.participantsIndisposees.add(equipe);
	}
	
	/**
	 * ajoute un arbitre
	 * @param arbitre à ajouter
	 * */
	public void ajouterArbitre(Arbitre arbitre) {
		this.arbitres.add(arbitre);
	}
	
	/**
	 * met à jour les points d'une équipe inscrite au tournoi
	 * @param equipe dont les points sont à supprimer
	 * @param points à mettre
	 * */
	public void majPointsEquipe(Equipe equipeARemplacer, int points) {
		this.participants.put(equipeARemplacer, points);
	}

	/**
	 * renvoie le login d'arbitre du tournoi
	 * */
	public String getLogin() {
		return this.login;	}
	
	/**
	 * renvoie le mot de passe d'arbitre du tournoi
	 * */
	public String getMotDePasse() {
		return this.mdp;
	}

	/**
	 * renvoie le vainqueur du tournoi, sera empty si aucun joueur n'est déterminé
	 * */
	public Optional<Equipe> getVainqueur() {
		return this.vainqueur;
	}

	
	/**
	 * génère le login d'arbitre du torunoi
	 * */
	private void generateLogin() {
		this.login = this.nomTournoi.substring(0, 2) + this.idTournoi + this.generateLetter()+ this.generateLetter() + this.generateLetter();
		this.login = this.login.toUpperCase();
	}
	
	/**
	 * génère le mot de passe du tournois
	 * */
	private void generateMdp() {
		
		String motdepasse = "";
		for (int i = 0; i<7; i++) {
			motdepasse += this.generateLetter();
		}
		Random r = new Random();
		motdepasse += r.nextInt(100);
		List<String> letters = Arrays.asList(motdepasse);
		Collections.shuffle(letters);
		this.mdp = "";
		for (String letter : letters) {
			this.mdp+=letter;
		}
		
	}
	
	/**
	 * renvoie une lettre au hasard
	 * */
	private char generateLetter() {
		Random r = new Random();
		return (char)(r.nextInt(26) + 'a');
	}
	
	/**
	 * renvoie une date depuis une chaîne de caractères
	 * @param date en chaîne de caractère
	 * */
	private static Date getDate(String date) {
		try {
			return new Date(new SimpleDateFormat("dd/MM/yyyy").parse(date).getTime());
		} catch (ParseException e) {
			try {
				return new Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * renvoie un booléen indiquant s'il n'y a pas déjà un tournoi
	 * avec le même nom que le tournoi actuel dans la bdd
	 * */
	public boolean isNonDupe() throws Exception {
		for (TournoiModele t : TournoiDAO.getInstance().getAll()) {
			if (t.getNomTournoi().equals(getNomTournoi())) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * renvoie un bool indiquant de la dateFin > dateDebut
	 * */
	public boolean isDateFinSupADateDebut() throws ParseException {
		return getDateFin().after(getDateDebut());
	}
	
	/**
	 * renvoie un bool indiquant que la durée du tournoi est inf à 30j
	 * */
	public boolean isDateFinDateDebutDifferenceInfA30Jours() throws ParseException {
		// 30 jours = 2592000000 milisecond
		return getDateFin().getTime() - getDateDebut().getTime() < 2592000000L;
	}
	
	/**
	 * renvoie un bool indiquant qu'aucun tournoi n'existe sur le même créneau que celui ci
	 * */
	public boolean isTournoiNonSuperpose() throws Exception {
		for (TournoiModele t : TournoiDAO.getInstance().getAll()) {
			
			if ((getDateDebut().compareTo(t.getDateDebut()) >= 0 && getDateDebut().compareTo(t.getDateFin()) <= 0) 
	                || (getDateFin().compareTo(t.getDateDebut()) >= 0 && getDateFin().compareTo(t.getDateFin()) <= 0) 
	                || (getDateDebut().compareTo(t.getDateDebut()) <= 0 && getDateFin().compareTo(t.getDateDebut()) >= 0)) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * renvoie le string de la date
	 * */
	public String getDateString(java.util.Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH)+"/"+(Integer.valueOf(c.get(Calendar.MONTH))+1)+"/"+c.get(Calendar.YEAR);
	}
	
	/**
	 * renvoie si un tournoi respecte toutes les restrictions de validité
	 * */
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
	
	/**
	 * renvoie si le tournoi a au min 4 équipes dispoées
	 * */
	public boolean isTournoiMinimum4EquipeDisposee() {		
		return participants.keySet()
				.stream()
				.filter(e -> e.getDisposition().equals(Disposition.DISPOSEE)).count() >= 4;
	}
	
	/**
	 * renvoie si le tournoi a au min 1 arbitre
	 * */
	public boolean isTournoiMinimum1Arbitre() {
		return arbitres.size() >= 1;
	}
	
	/**
	 * renvoie si nous sommes aujourd'hui dans le créneau du tournoi
	 * */
	public boolean isDateCouranteDansCreneauTournoi() throws ParseException {
		LocalDate currentDate = LocalDate.now();

        Date sqlDate = Date.valueOf(LocalDateTime.of(currentDate, LocalTime.MIDNIGHT).toLocalDate());
        
		return sqlDate.compareTo(getDateDebut()) != -1 && getDateFin().compareTo(sqlDate) != -1;
	}
	
	/**
	 * renvoie si un tournoi est ouvert
	 * */
	public static boolean isAucunTournoiOuvert() throws Exception {
		for (TournoiModele t : TournoiDAO.getInstance().getAll()) {
			if (t.getEtatTournoi() == EtatTournoi.OUVERT) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * supprime la participation des équipes indiposées de la bdd
	 * */
	public void supprimerEquipeIndisposees() throws Exception {
		for (Equipe e : this.participantsIndisposees) {
			ParticiperDAO.getInstance().delete(new Participer(e.getIdEquipe(), this.getIDTournoi(), 0)); // 0 : arbitraire
		}
	}
	
	/**
	 * renvoie si le tournoi est ouvrable
	 * */
	public boolean isTournoiOuvrable() throws ParseException, Exception {
		return this.isTournoiMinimum4EquipeDisposee() 
				&& this.isTournoiMinimum1Arbitre() 
				&& TournoiModele.isAucunTournoiOuvert() 
				&& this.isDateCouranteDansCreneauTournoi();
	}
	
	/**
	 * ouvre un tournoi
	 * */
	public void ouvrirTournoi() throws Exception {
		
		if (this.isTournoiOuvrable()) {
			
			this.supprimerEquipeIndisposees();
			this.setEtatTournoi(EtatTournoi.OUVERT);
			
			ArrayList<Equipe> equipes = new ArrayList<>();
			equipes.addAll(participants.keySet());
			ArrayList<Equipe> equipesAttribuees = new ArrayList<>();
			for (Equipe e : equipes) {
				if (!equipesAttribuees.contains(e)) {
					equipesAttribuees.add(e);
					for (Equipe e2 : equipes) {
						if (!equipesAttribuees.contains(e2)) {
							Match m = new Match(0, this.idTournoi, false);
							m.AddEquipe(e); m.AddEquipe(e2);
							this.ajouterMatch(m); 
							MatchDAO.getInstance().add(m);
						}
					}
				}
			}
			
			System.out.println(this.idTournoi);
			TournoiDAO.getInstance().update(this);
			
			for (Match m : this.getMatchs()) {
				System.out.println(m.getIdTournoi());
			}
			
		}
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(arbitres, dateDebut, dateFin, etat, finale, idTournoi, login, matches, mdp, nomTournoi,
				notoriete, participants, participantsIndisposees, vainqueur);
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
		return Objects.equals(arbitres, other.arbitres) && Objects.equals(dateDebut, other.dateDebut)
				&& Objects.equals(dateFin, other.dateFin) && etat == other.etat && Objects.equals(finale, other.finale)
				&& idTournoi == other.idTournoi && Objects.equals(login, other.login)
				&& Objects.equals(matches, other.matches) && Objects.equals(mdp, other.mdp)
				&& Objects.equals(nomTournoi, other.nomTournoi) && notoriete == other.notoriete
				&& Objects.equals(participants, other.participants)
				&& Objects.equals(participantsIndisposees, other.participantsIndisposees)
				&& Objects.equals(vainqueur, other.vainqueur);
	}

	
	

}
