package modeles;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.HashMap;

import org.junit.Test;

import DAOs.TournoiDAO;
import classes.Arbitre;
import classes.DBConnection;
import classes.Equipe;
import classes.EtatTournoi;
import classes.Nationalite;
import classes.Notoriete;


public class TestTournoiModele {
	
	private TournoiModele t;

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

	t.ajouterEquipe(new Equipe(1,"rofl",Nationalite.AD,false,14,12),0);
	t.majPointsEquipe(new Equipe(1,"rofl",Nationalite.AD,false,14,12), 121);
	
	assertTrue(t.getParticipants().containsKey(new Equipe(1,"rofl",Nationalite.AD,false,14,121)));
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
				"Champers", 
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
	public void testTournoiNonSuperpose() throws Exception {
		
		t = new TournoiModele(
				2,
				"gneg", 
				"20/11/1888", 
				"31/11/1888", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		
		TournoiDAO.getInstance().add(t);
		
		
		TournoiModele t2 = new TournoiModele(
				2,
				"gnegne", 
				"25/12/1888", 
				"27/12/1888", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		
		System.out.println("-> "+ t2.isTournoiNonSuperpose());
		//assertTrue(t2.isTournoiNonSuperpose());
		
		DBConnection.getInstance().rollback();
		// rollback
		//TournoiDAO.getInstance().delete(t);
	}
	
	@Test
	public void testTournoiMinimum4EquipeDisposeeAvecAuMoins4EquipesDisposees() {
		TournoiModele t = new TournoiModele();
		t.ajouterEquipe(new Equipe(1, "e1", Nationalite.FR, true, 1000, 100),0);
		t.ajouterEquipe(new Equipe(2, "e2", Nationalite.FR, true, 1000, 100),0);
		t.ajouterEquipe(new Equipe(3, "e3", Nationalite.FR, true, 1000, 100),0);
		t.ajouterEquipe(new Equipe(4, "e4", Nationalite.FR, true, 1000, 100),0);
		
		TournoiModele t2 = new TournoiModele();
		t2.ajouterEquipe(new Equipe(1, "e1", Nationalite.FR, true, 1000, 100),0);
		t2.ajouterEquipe(new Equipe(2, "e2", Nationalite.FR, true, 1000, 100),0);
		t2.ajouterEquipe(new Equipe(3, "e3", Nationalite.FR, true, 1000, 100),0);
		t2.ajouterEquipe(new Equipe(4, "e4", Nationalite.FR, true, 1000, 100),0);
		t2.ajouterEquipe(new Equipe(4, "e4", Nationalite.FR, true, 1000, 100),0);
		
		assertTrue(t.isTournoiMinimum4EquipeDisposee());
		assertTrue(t2.isTournoiMinimum4EquipeDisposee());
	}
	
	@Test
	public void testTournoiMinimum4EquipeDisposeeAvecMoinsDe4EquipesDisposees() {
		TournoiModele t = new TournoiModele();
		t.ajouterEquipe(new Equipe(1, "e1", Nationalite.FR, true, 1000, 100),0);
		t.ajouterEquipe(new Equipe(2, "e2", Nationalite.FR, true, 1000, 100),0);
		t.ajouterEquipe(new Equipe(3, "e3", Nationalite.FR, true, 1000, 100),0);
		t.ajouterEquipe(new Equipe(4, "e4", Nationalite.FR, false, 1000, 100),0);
		
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
	
	
	
	
}
