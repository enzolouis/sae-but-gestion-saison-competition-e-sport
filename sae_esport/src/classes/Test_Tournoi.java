package classes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import classes.Tournoi.EtatTournoi;
import classes.Tournoi.Notoriete;

public class Test_Tournoi {
	
	private Tournoi t;

	@Test
	public void testTournoiBasic() {
		t = new Tournoi(		"Champers", 
								"29/09/1988", 
								"30/12/1988", 
								Notoriete.REGIONAL,
								EtatTournoi.FERME,
								"MotDePasse",
								1,
								new ArrayList<>());
		
		assertEquals(t.getNomTournoi(), "Champers");
		assertEquals(t.getDateFin(), "30/12/1988");
		assertEquals(t.getDateDebut(), "29/09/1988");
		assertEquals(t.getNotoriete(), Notoriete.REGIONAL);		
		assertEquals(t.getEtat_Tournoi(), EtatTournoi.FERME);
		assertEquals(t.getMotDePasse(),"MotDePasse" );
		assertEquals(t.getIDTournoi(),1);
	}
	
	@Test
	public void testTournoiChangementNom() {
		t = new Tournoi(		
				"Champers", 
				"29/09/1988", 
				"30/12/1988", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME,
				"MotDePasse",
				1,
				new ArrayList<>());
		
		assertEquals(t.getNomTournoi(), "Champers");
		
		t.setNomTournoi("Champiouf");
		
		assertEquals(t.getNomTournoi(), "Champiouf");
		assertNotEquals(t.getNomTournoi(), "Champers");
	}
	
	@Test
	public void testTournoiChangementDateDebut() {
		t = new Tournoi(		
				"Champers", 
				"29/09/1988", 
				"30/12/1988", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME,
				"MotDePasse",
				1,
				new ArrayList<>());
		
		assertEquals(t.getDateDebut(), "29/09/1988");
		
		t.setDateDebut("12/02/1988");
		
		assertEquals(t.getDateDebut(), "12/02/1988");
		assertNotEquals(t.getDateDebut(), "29/09/1988");

	}
	
	@Test
	public void testTournoiChangementDateFin() {
		t = new Tournoi(		
				"Champers", 
				"29/09/1988", 
				"30/12/1988", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME,
				"MotDePasse",
				1,
				new ArrayList<>());
		
		assertEquals(t.getDateFin(), "30/12/1988");
		
		t.setDateDebut("22/10/1989");
		
		assertEquals(t.getDateFin(), "22/10/1989");
		assertNotEquals(t.getDateFin(), "30/12/1988");
	}
	
	@Test
	public void testTournoiChangementNotoriete() {
		t = new Tournoi(		
				"Champers", 
				"29/09/1988", 
				"30/12/1988", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME,
				"MotDePasse",
				1,
				new ArrayList<>());
	
		assertEquals(t.getNotoriete(), Notoriete.REGIONAL);		

		t.setNotoriete(Notoriete.INTERNATIONAL_CLASSE);
		
		assertEquals(t.getNotoriete(), Notoriete.INTERNATIONAL_CLASSE);		
		assertNotEquals(t.getNotoriete(), Notoriete.REGIONAL);		
	}
	
	@Test
	public void testTournoiChangementEtatTournoi() {
		t = new Tournoi(		
				"Champers", 
				"29/09/1988", 
				"30/12/1988", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME,
				"MotDePasse",
				1,
				new ArrayList<>());
		
		assertEquals(t.getEtat_Tournoi(), EtatTournoi.FERME);

		t.setEtat_Tournoi(EtatTournoi.OUVERT);
		
		assertEquals(t.getEtat_Tournoi(), EtatTournoi.OUVERT);
		assertNotEquals(t.getEtat_Tournoi(), EtatTournoi.FERME);
	}
	
	@Test
	public void testTournoiChangementMotDePasse() {
		t = new Tournoi(		
				"Champers", 
				"29/09/1988", 
				"30/12/1988", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME,
				"MotDePasse",
				1,
				new ArrayList<>());
	
		assertEquals(t.getMotDePasse(),"MotDePasse" );

		t.setTournoiMDP("NotPass");
		
		assertEquals(t.getMotDePasse(),"NotPass" );
		assertNotEquals(t.getMotDePasse(),"MotDePasse" );
	}
	
	@Test
	public void testTournoiChangementID() {
		t = new Tournoi(		
				"Champers", 
				"29/09/1988", 
				"30/12/1988", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME,
				"MotDePasse",
				1,
				new ArrayList<>());
	
		assertEquals(t.getIDTournoi(),1);

		t.setIDTournoi(3);
		
		assertEquals(t.getIDTournoi(),3);
		assertNotEquals(t.getIDTournoi(),1);
	}
}
