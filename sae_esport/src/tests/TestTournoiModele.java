package tests;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DAOs.TournoiDAO;
import classes.Arbitre;
import classes.DBConnection;
import classes.Disposition;
import classes.Equipe;
import classes.EtatTournoi;
import classes.Nationalite;
import classes.Notoriete;
import modeles.TournoiModele;


public class TestTournoiModele {
	
	private TournoiModele t;
	
	@Before
	public void setUp() throws SQLException {
		DBConnection.getInstance().setAutoCommit(false);
	}
	
	@After
	public void tearDown() throws SQLException {
		DBConnection.getInstance().rollback();
		DBConnection.getInstance().setAutoCommit(true);
	}

	@Test
	public void testTournoiBasic() {
		t = new TournoiModele(	
				1,
				"Champers", 
				"29/09/1988", 
				"30/12/1988", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		
		assertEquals(t.getNomTournoi(), "Champers");
		//assertEquals(t.getDateFin(), "30/12/1988");
		//assertEquals(t.getDateDebut(), "29/09/1988");
		assertEquals(t.getNotoriete(), Notoriete.REGIONAL);		
		assertEquals(t.getEtatTournoi(), EtatTournoi.FERME);
		assertEquals(t.getIDTournoi(),1);
		assertEquals(t.getParticipants(),new HashMap<>());
	}
	
	@Test
	public void testTournoiChangementNom() {
		t = new TournoiModele(		
				1,
				"Champers", 
				"29/09/1988", 
				"30/12/1988", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		
		assertEquals(t.getNomTournoi(), "Champers");
		
		t.setNomTournoi("Champiouf");
		
		assertEquals(t.getNomTournoi(), "Champiouf");
		assertNotEquals(t.getNomTournoi(), "Champers");
	}
	
	@Test
	public void testTournoiChangementDateDebut() {
		t = new TournoiModele(		
				1,
				"Champers", 
				"29/09/1988", 
				"30/12/1988", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		
		//assertEquals(t.getDateDebut(), "29/09/1988");
		
		//t.setDateDebut("12/02/1988");
		
		//assertEquals(t.getDateDebut(), "12/02/1988");
		//assertNotEquals(t.getDateDebut(), "29/09/1988");

	}
	
	@Test
	public void testTournoiChangementDateFin() {
		t = new TournoiModele(		
				1,
				"Champers", 
				"29/09/1988", 
				"30/12/1988", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		
		//assertEquals(t.getDateFin(), "30/12/1988");
		
		//t.setDateFin("22/10/1989");
		
		//assertEquals(t.getDateFin(), "22/10/1989");
		//assertNotEquals(t.getDateFin(), "30/12/1988");
	}
	
	@Test
	public void testTournoiChangementNotoriete() {
		t = new TournoiModele(		
				1,
				"Champers", 
				"29/09/1988", 
				"30/12/1988", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
	
		assertEquals(t.getNotoriete(), Notoriete.REGIONAL);		

		t.setNotoriete(Notoriete.INTERNATIONAL_CLASSE);
		
		assertEquals(t.getNotoriete(), Notoriete.INTERNATIONAL_CLASSE);		
		assertNotEquals(t.getNotoriete(), Notoriete.REGIONAL);		
	}
	
	@Test
	public void testTournoiChangementEtatTournoi() {
		t = new TournoiModele(		
				1,
				"Champers", 
				"29/09/1988", 
				"30/12/1988", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		
		assertEquals(t.getEtatTournoi(), EtatTournoi.FERME);

		t.setEtatTournoi(EtatTournoi.OUVERT);
		
		assertEquals(t.getEtatTournoi(), EtatTournoi.OUVERT);
		assertNotEquals(t.getEtatTournoi(), EtatTournoi.FERME);
	}
	
	@Test
	public void testTournoiChangementID() {
		t = new TournoiModele(
				1,
				"Champers", 
				"29/09/1988", 
				"30/12/1988", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
	
		assertEquals(t.getIDTournoi(),1);

		t.setIDTournoi(3);
		
		assertEquals(t.getIDTournoi(),3);
		assertNotEquals(t.getIDTournoi(),1);
	}
	
	@Test
	public void testTournoiCreerMatch() {
		t = new TournoiModele(
				1,
				"Champers", 
				"29/09/1988", 
				"30/12/1988", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		
		t.nouveauMatch(13, false);
		
		assertEquals(t.getMatchs().get(0).getIDMatch(), 13);
		assertEquals(t.getMatchs().get(0).IsItFinale(), false);
	}
	
	@Test
	public void testTournoiAjouterEquipe() {
		t = new TournoiModele(
				1,
				"Champers", 
				"29/09/1988", 
				"30/12/1988", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);

	Equipe e = new Equipe(1,"rofl",Nationalite.AD,Disposition.NON_DIPOSEE,14,12);
	t.ajouterEquipe(e,0);
	t.majPointsEquipe(e, 121);
	assertTrue(t.getParticipants().containsKey(e));
	
	}
	
	@Test
	public void testDateFinSupADateDebutAvecDateFinSup() throws ParseException {
		t = new TournoiModele(
				1,
				"Champers", 
				"29/12/1988", 
				"30/12/1988", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		
		assertTrue(t.isDateFinSupADateDebut());
	}
	
	@Test
	public void testDateFinSupADateDebutAvecDateFinInf() throws ParseException {
		t = new TournoiModele(
				1,
				"Champers", 
				"30/12/1988", 
				"29/12/1988", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		
		assertFalse(t.isDateFinSupADateDebut());
	}
	
	@Test
	public void testDateFinSupADateDebutAvecDateFinEgal() throws ParseException {
		t = new TournoiModele(
				1,
				"Champers", 
				"30/12/1988", 
				"30/12/1988", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		
		assertFalse(t.isDateFinSupADateDebut());
	}
	
	@Test
	public void testDateFinDateDebutDifferenceInfA30JoursAvecDateDifferenceMoinsDe30Jours() throws ParseException {
		t = new TournoiModele(
				1,
				"Champers", 
				"20/12/1988", 
				"30/12/1988", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		
		assertTrue(t.isDateFinSupADateDebut());
	}
	
	@Test
	public void testDateFinDateDebutDifferenceInfA30JoursAvecDateDifferencePlusDe30Jours() throws ParseException {
		t = new TournoiModele(
				1,
				"Champers", 
				"20/11/1988", 
				"30/12/1988", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		
		assertTrue(t.isDateFinSupADateDebut());
	}
	
	@Test
	public void testNonDupeSansDupe() throws Exception {
		
		t = new TournoiModele(
				1,
				"Nouveau tournoi tout nouveau", 
				"25/12/1988", 
				"30/12/1988", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		
		assertTrue(t.isNonDupe());
	}
	
	@Test
	public void testNonDupeAvecDupe() throws Exception {
		
		t = new TournoiModele(
				2,
				"gnegne", 
				"20/12/1888", 
				"31/12/1888", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		
		TournoiDAO.getInstance().add(t);
		
		TournoiModele t2 = new TournoiModele(
				2,
				"gnegne", 
				"20/12/1888", 
				"31/12/1888", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		
		
		assertFalse(t2.isNonDupe());
		
		// rollback
		DBConnection.getInstance().rollback();
	}
	
	@Test
	public void testTournoiNonSuperposeSansSuperposition() throws Exception {
		t = new TournoiModele(
				2,
				"testTournoiNonSuperpose1", 
				"20/11/1888", 
				"31/11/1888", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		
		TournoiDAO.getInstance().add(t);
		
		
		TournoiModele t2 = new TournoiModele(
				2,
				"testTournoiNonSuperpose2", 
				"25/12/1888", 
				"27/12/1888", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		assertTrue(t2.isTournoiNonSuperpose());
		
		DBConnection.getInstance().rollback();
	}
	
	@Test
	public void testTournoiNonSuperposeSuperpositionDateDebutDansCreneau() throws Exception {
		t = new TournoiModele(
				2,
				"testTournoiNonSuperpose1", 
				"20/11/1888", 
				"31/11/1888", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		
		TournoiDAO.getInstance().add(t);
		
		
		TournoiModele t2 = new TournoiModele(
				2,
				"testTournoiNonSuperpose2", 
				"25/11/1888", 
				"15/12/1888", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		assertFalse(t2.isTournoiNonSuperpose());
		
		DBConnection.getInstance().rollback();
	}
	
	@Test
	public void testTournoiNonSuperposeSuperpositionDateFinDansCreneau() throws Exception {
		t = new TournoiModele(
				2,
				"testTournoiNonSuperpose1", 
				"20/11/1888", 
				"31/11/1888", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		
		TournoiDAO.getInstance().add(t);
		
		
		TournoiModele t2 = new TournoiModele(
				2,
				"testTournoiNonSuperpose2", 
				"01/10/1888", 
				"25/11/1888", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		assertFalse(t2.isTournoiNonSuperpose());
		
		DBConnection.getInstance().rollback();
	}
	
	@Test
	public void testTournoiNonSuperposeSuperpositionCreneauEnglobantCreneau() throws Exception {
		t = new TournoiModele(
				2,
				"testTournoiNonSuperpose1", 
				"20/11/1888", 
				"31/11/1888", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		
		TournoiDAO.getInstance().add(t);
		
		
		TournoiModele t2 = new TournoiModele(
				2,
				"testTournoiNonSuperpose2", 
				"15/10/1888", 
				"15/12/1888", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		assertFalse(t2.isTournoiNonSuperpose());
		DBConnection.getInstance().rollback();
	}
	
	@Test
	public void testTournoiNonSuperposeSuperpositionDateDebutEtDateFinDansCreneau() throws Exception {
		t = new TournoiModele(
				2,
				"testTournoiNonSuperpose1", 
				"20/11/1888", 
				"31/11/1888", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		
		TournoiDAO.getInstance().add(t);
		
		
		TournoiModele t2 = new TournoiModele(
				2,
				"testTournoiNonSuperpose2", 
				"23/11/1888", 
				"27/11/1888", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		assertFalse(t2.isTournoiNonSuperpose());
		
		DBConnection.getInstance().rollback();
	}
	
	
	
	@Test
	public void testTournoiMinimum4EquipeDisposeeAvecAuMoins4EquipesDisposees() {
		TournoiModele t = new TournoiModele();
		t.ajouterEquipe(new Equipe(1, "e1", Nationalite.FR, Disposition.DISPOSEE, 1000, 100),0);
		t.ajouterEquipe(new Equipe(2, "e2", Nationalite.FR, Disposition.DISPOSEE, 1000, 100),0);
		t.ajouterEquipe(new Equipe(3, "e3", Nationalite.FR, Disposition.DISPOSEE, 1000, 100),0);
		t.ajouterEquipe(new Equipe(4, "e4", Nationalite.FR, Disposition.DISPOSEE, 1000, 100),0);
		
		TournoiModele t2 = new TournoiModele();
		t2.ajouterEquipe(new Equipe(1, "e1", Nationalite.FR, Disposition.DISPOSEE, 1000, 100),0);
		t2.ajouterEquipe(new Equipe(2, "e2", Nationalite.FR, Disposition.DISPOSEE, 1000, 100),0);
		t2.ajouterEquipe(new Equipe(3, "e3", Nationalite.FR, Disposition.DISPOSEE, 1000, 100),0);
		t2.ajouterEquipe(new Equipe(4, "e4", Nationalite.FR, Disposition.DISPOSEE, 1000, 100),0);
		t2.ajouterEquipe(new Equipe(4, "e4", Nationalite.FR, Disposition.DISPOSEE, 1000, 100),0);
		
		assertTrue(t.isTournoiMinimum4EquipeDisposee());
		assertTrue(t2.isTournoiMinimum4EquipeDisposee());
	}
	
	@Test
	public void testTournoiMinimum4EquipeDisposeeAvecMoinsDe4EquipesDisposees() {
		TournoiModele t = new TournoiModele();
		t.ajouterEquipe(new Equipe(1, "e1", Nationalite.FR, Disposition.DISPOSEE, 1000, 100),0);
		t.ajouterEquipe(new Equipe(2, "e2", Nationalite.FR, Disposition.DISPOSEE, 1000, 100),0);
		t.ajouterEquipe(new Equipe(3, "e3", Nationalite.FR, Disposition.DISPOSEE, 1000, 100),0);
		t.ajouterEquipe(new Equipe(4, "e4", Nationalite.FR, Disposition.NON_DIPOSEE, 1000, 100),0);
		
		assertFalse(t.isTournoiMinimum4EquipeDisposee());
	}
	
	@Test
	public void testTournoiMinimum1ArbitreAvecAuMoins1Arbitre() {
		TournoiModele t = new TournoiModele();
		t.ajouterArbitre(new Arbitre(1, "Alfred", "Moukhamedov", Nationalite.RU));		
		
		TournoiModele t2 = new TournoiModele();
		t2.ajouterArbitre(new Arbitre(1, "Alfred", "Moukhamedov", Nationalite.RU));
		t2.ajouterArbitre(new Arbitre(2, "Dimitri", "Moukhamedov", Nationalite.RU));

		assertTrue(t.isTournoiMinimum1Arbitre());
		assertTrue(t2.isTournoiMinimum1Arbitre());
	}
	
	@Test
	public void testTournoiMinimum1ArbitreAvecMoinsDe1Arbitre() {
		TournoiModele t = new TournoiModele();

		assertFalse(t.isTournoiMinimum1Arbitre());
	}
	@Test
	public void testDateCouranteDansCreneauTournoiAvecDateCouranteDansCreneau() throws ParseException {
		Date currentDate = Date.valueOf(LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT).toLocalDate());
        
        Calendar cCurrentDate = Calendar.getInstance();
        cCurrentDate.setTime(currentDate);
        
        // cas : on ouvre le tournoi pile au milieu du créneau
        Calendar cDebut = (Calendar) cCurrentDate.clone();
        cDebut.add(Calendar.DAY_OF_MONTH, -5);
        
        Calendar cFin = (Calendar) cCurrentDate.clone();
        cFin.add(Calendar.DAY_OF_MONTH, 5);
        
        TournoiModele t = new TournoiModele();
        t.setDateDebut(new SimpleDateFormat("dd/MM/yyyy").format(cDebut.getTime()));
		t.setDateFin(new SimpleDateFormat("dd/MM/yyyy").format(cFin.getTime()));
        
        // cas : on ouvre le tournoi le premier jour du tournoi
        
        Calendar cDebut2 = (Calendar) cCurrentDate.clone();
        
        Calendar cFin2 = (Calendar) cCurrentDate.clone();
        cFin2.add(Calendar.DAY_OF_MONTH, 5);
        
        TournoiModele t2 = new TournoiModele();
        t2.setDateDebut(new SimpleDateFormat("dd/MM/yyyy").format(cDebut2.getTime()));
		t2.setDateFin(new SimpleDateFormat("dd/MM/yyyy").format(cFin2.getTime()));
        
        // cas : on ouvre le tournoi le premier jour du tournoi
		
		Calendar cDebut3 = (Calendar) cCurrentDate.clone();
		cDebut3.add(Calendar.DAY_OF_MONTH, -5);
		
        Calendar cFin3 = (Calendar) cCurrentDate.clone();
        
        TournoiModele t3 = new TournoiModele();
        t3.setDateDebut(new SimpleDateFormat("dd/MM/yyyy").format(cDebut3.getTime()));
		t3.setDateFin(new SimpleDateFormat("dd/MM/yyyy").format(cFin3.getTime()));
        
		// cas : on ouvre le tournoi le premier et à la fois le dernier jour
		// impossible selon le test testDateFinSupADateDebutAvecDateFinEgal
		// un tournoi ne peut pas avoir la même date de début que de fin
		
		assertTrue(t.isDateCouranteDansCreneauTournoi());
		assertTrue(t2.isDateCouranteDansCreneauTournoi());
		assertTrue(t3.isDateCouranteDansCreneauTournoi());
	}
	
	@Test
	public void testDateCouranteDansCreneauTournoiAvecDateCouranteAvantCreneau() throws ParseException {
		Date currentDate = Date.valueOf(LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT).toLocalDate());
        
        Calendar cCurrentDate = Calendar.getInstance();
        cCurrentDate.setTime(currentDate);
        
        // cas : on ouvre le tournoi pile au milieu du créneau
        Calendar cDebut = (Calendar) cCurrentDate.clone();
        cDebut.add(Calendar.DAY_OF_MONTH, 1); // un jour après aujourd'hui
        
        Calendar cFin = (Calendar) cCurrentDate.clone();
        cFin.add(Calendar.DAY_OF_MONTH, 5); // arbitraire
        
        TournoiModele t = new TournoiModele();
        t.setDateDebut(new SimpleDateFormat("dd/MM/yyyy").format(cDebut.getTime()));
		t.setDateFin(new SimpleDateFormat("dd/MM/yyyy").format(cFin.getTime()));
        
		assertFalse(t.isDateCouranteDansCreneauTournoi());
	}
	
	@Test
	public void testDateCouranteDansCreneauTournoiAvecDateCouranteApresCreneau() throws ParseException {
		Date currentDate = Date.valueOf(LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT).toLocalDate());
        
        Calendar cCurrentDate = Calendar.getInstance();
        cCurrentDate.setTime(currentDate);
        
        // cas : on ouvre le tournoi pile au milieu du créneau
        Calendar cDebut = (Calendar) cCurrentDate.clone();
        cDebut.add(Calendar.DAY_OF_MONTH, -5); // arbitraire
        
        Calendar cFin = (Calendar) cCurrentDate.clone();
        cFin.add(Calendar.DAY_OF_MONTH, -1); // un jour avant aujourd'hui
        
        TournoiModele t = new TournoiModele();
        t.setDateDebut(new SimpleDateFormat("dd/MM/yyyy").format(cDebut.getTime()));
		t.setDateFin(new SimpleDateFormat("dd/MM/yyyy").format(cFin.getTime()));
        
		assertFalse(t.isDateCouranteDansCreneauTournoi());
	}
	
	@Test
	public void testAucunTournoiOuvertAvecAucunTournoiOuvert() throws Exception {
		
		TournoiDAO.getInstance().add(new TournoiModele(
				2,
				"testAucunToOuvertAucToOu", 
				"20/11/1888", 
				"31/11/1888", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME));
		
		TournoiDAO.getInstance().add(new TournoiModele(
				2,
				"testAucunToOuvertAucToOu2", 
				"20/11/1888", 
				"31/11/1888", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME));
		
		TournoiDAO.getInstance().add(new TournoiModele(
				2,
				"testAucunToOuvertAucToOu3", 
				"20/11/1888", 
				"31/11/1888", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME));
		
		assertTrue(TournoiModele.isAucunTournoiOuvert());
		
		DBConnection.getInstance().rollback();
	}
	
	@Test
	public void testAucunTournoiOuvertAvecMinimum1TournoiOuvert() throws Exception {
		TournoiModele t = new TournoiModele(
				2,
				"testAucunToOuvertMin1ToOu", 
				"20/11/1888", 
				"31/11/1888", 
				Notoriete.REGIONAL,
				EtatTournoi.OUVERT);
	
		TournoiDAO.getInstance().add(t);
		
		assertFalse(TournoiModele.isAucunTournoiOuvert());
	
		DBConnection.getInstance().rollback();
	
		TournoiModele t2 = new TournoiModele(
				2,
				"testAucunToOuvertMin1ToOu2", 
				"20/11/1888", 
				"31/11/1888", 
				Notoriete.REGIONAL,
				EtatTournoi.OUVERT);
		
		TournoiModele t3 = new TournoiModele(
				2,
				"testAucunToOuvertMin1ToOu3", 
				"20/11/1888", 
				"31/11/1888", 
				Notoriete.REGIONAL,
				EtatTournoi.OUVERT);
		
		TournoiDAO.getInstance().add(t2);
		TournoiDAO.getInstance().add(t3);
		
		assertFalse(TournoiModele.isAucunTournoiOuvert());
	
		DBConnection.getInstance().rollback();
	}
	
	@Test
	public void testSupprimerEquipeIndisposees() throws Exception {
		TournoiModele t = new TournoiModele(
				2,
				"testAucunToOuvertMin1ToOu", 
				"20/11/1888", 
				"31/11/1888", 
				Notoriete.REGIONAL,
				EtatTournoi.OUVERT);
		
		t.ajouterEquipe(new Equipe(1, "e1", Nationalite.FR, Disposition.DISPOSEE, 1000, 100), 0);
		t.ajouterEquipe(new Equipe(2, "e2", Nationalite.FR, Disposition.DISPOSEE, 1000, 100), 0);
		t.ajouterEquipe(new Equipe(5, "e5", Nationalite.FR, Disposition.NON_DIPOSEE, 1000, 100), 0);
		
		t.supprimerEquipeIndisposees();
		
		List<String> lstNomEquipe = t.getParticipants().keySet().stream().map(e -> e.getNom()).collect(Collectors.toList());
		assertTrue(lstNomEquipe.contains("e1"));
		assertFalse(lstNomEquipe.contains("e5"));
		
	}
	
	@Test
	public void testTournoiOuvrable() throws ParseException, Exception {
		Date currentDate = Date.valueOf(LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT).toLocalDate());
        
        Calendar cCurrentDate = Calendar.getInstance();
        cCurrentDate.setTime(currentDate);
        
        // cas : on ouvre le tournoi pile au milieu du créneau
        Calendar cDebut = (Calendar) cCurrentDate.clone();
        cDebut.add(Calendar.DAY_OF_MONTH, -5); // arbitraire
        
        Calendar cFin = (Calendar) cCurrentDate.clone();
        cFin.add(Calendar.DAY_OF_MONTH, 5);
        
		TournoiModele t = new TournoiModele(
				10,
				"testAucunToOuvertMin1ToOu", 
				new SimpleDateFormat("dd/MM/yyyy").format(cDebut.getTime()), 
				new SimpleDateFormat("dd/MM/yyyy").format(cFin.getTime()), 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		
		
		// configuration parfaite d'un tournoi pouvant être lancé
		// (>= 4 equipe, >= 1 equipe, date dans creneau, aucun tournoi deja ouvert)
		t.ajouterEquipe(new Equipe(1, "e1", Nationalite.FR, Disposition.DISPOSEE, 1000, 100), 0);
		t.ajouterEquipe(new Equipe(2, "e2", Nationalite.FR, Disposition.DISPOSEE, 1000, 100), 0);
		t.ajouterEquipe(new Equipe(3, "e3", Nationalite.FR, Disposition.DISPOSEE, 1000, 100), 0);
		t.ajouterEquipe(new Equipe(4, "e4", Nationalite.FR, Disposition.DISPOSEE, 1000, 100), 0);
		
		t.ajouterArbitre(new Arbitre(1, "Albert", "Camus", Nationalite.FR));
		
		assertTrue(t.isTournoiOuvrable());
	}
	
	@Test
	public void testOuvrirTournoi() throws ParseException, Exception {
		Date currentDate = Date.valueOf(LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT).toLocalDate());
        
        Calendar cCurrentDate = Calendar.getInstance();
        cCurrentDate.setTime(currentDate);
        
        // cas : on ouvre le tournoi pile au milieu du créneau
        Calendar cDebut = (Calendar) cCurrentDate.clone();
        cDebut.add(Calendar.DAY_OF_MONTH, -5); // arbitraire
        
        Calendar cFin = (Calendar) cCurrentDate.clone();
        cFin.add(Calendar.DAY_OF_MONTH, 5);
        
		TournoiModele t = new TournoiModele(
				10,
				"testAucunToOuvertMin1ToOu", 
				new SimpleDateFormat("dd/MM/yyyy").format(cDebut.getTime()), 
				new SimpleDateFormat("dd/MM/yyyy").format(cFin.getTime()), 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		
		
		// configuration parfaite d'un tournoi pouvant être lancé
		// (>= 4 equipe, >= 1 equipe, date dans creneau, aucun tournoi deja ouvert)
		t.ajouterEquipe(new Equipe(1, "e1", Nationalite.FR, Disposition.DISPOSEE, 1000, 100), 0);
		t.ajouterEquipe(new Equipe(2, "e2", Nationalite.FR, Disposition.DISPOSEE, 1000, 100), 0);
		t.ajouterEquipe(new Equipe(3, "e3", Nationalite.FR, Disposition.DISPOSEE, 1000, 100), 0);
		t.ajouterEquipe(new Equipe(4, "e4", Nationalite.FR, Disposition.DISPOSEE, 1000, 100), 0);
		
		t.ajouterArbitre(new Arbitre(1, "Albert", "Camus", Nationalite.FR));
		
		//assertTrue(t.isTournoiOuvrable());
		TournoiDAO.getInstance().add(t);
		
		t.ouvrirTournoi();
		assertEquals(t.getEtatTournoi(), EtatTournoi.OUVERT);
		
		DBConnection.getInstance().rollback();
	}
	
}
