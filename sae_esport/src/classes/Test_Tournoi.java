package classes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;


public class Test_Tournoi {
	
	private Tournoi t;

	@Test
	public void testTournoiBasic() {
		t = new Tournoi(	
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
		assertEquals(t.getEtat_Tournoi(), EtatTournoi.FERME);
		assertEquals(t.getIDTournoi(),1);
		assertEquals(t.getParticipants(),new HashMap<>());
	}
	
	@Test
	public void testTournoiChangementNom() {
		t = new Tournoi(		
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
		t = new Tournoi(		
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
		t = new Tournoi(		
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
		t = new Tournoi(		
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
		t = new Tournoi(		
				1,
				"Champers", 
				"29/09/1988", 
				"30/12/1988", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		
		assertEquals(t.getEtat_Tournoi(), EtatTournoi.FERME);

		t.setEtat_Tournoi(EtatTournoi.OUVERT);
		
		assertEquals(t.getEtat_Tournoi(), EtatTournoi.OUVERT);
		assertNotEquals(t.getEtat_Tournoi(), EtatTournoi.FERME);
	}
	
	@Test
	public void testTournoiChangementID() {
		t = new Tournoi(
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
		t = new Tournoi(
				1,
				"Champers", 
				"29/09/1988", 
				"30/12/1988", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
		
		t.NouveauMatch(13, false);
		
		assertEquals(t.getMatchs().get(0).getIDMatch(), 13);
		assertEquals(t.getMatchs().get(0).IsItFinale(), false);
	}
	
	@Test
	public void testTournoiAjouterEquipe() {
		t = new Tournoi(
				1,
				"Champers", 
				"29/09/1988", 
				"30/12/1988", 
				Notoriete.REGIONAL,
				
				EtatTournoi.FERME);
	
	t.ajouterEquipe(new Equipe(1,"rofl",Nationalite.AD,false,14,12));
	t.majPointsEquipe(new Equipe(1,"rofl",Nationalite.AD,false,14,12), 121);
	
	assertTrue(t.getParticipants().containsKey(new Equipe(1,"rofl",Nationalite.AD,false,14,12)));
	}
	
	
}
