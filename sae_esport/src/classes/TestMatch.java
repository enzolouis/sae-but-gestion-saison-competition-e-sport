package classes;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestMatch {

	Match match;

	@Test
	public void testCreateTournoi() {
		match = new Match(1,1,false);
		
		assertEquals(match.getIDMatch(), 1);
		assertEquals(match.IsItFinale(), false);
	}
	
	@Test
	public void testChangementIdMatch() {
		match = new Match(1,1,false);
		
		match.setIdMatch(3);
		
		assertEquals(match.getIDMatch(), 3);
		assertNotEquals(match.getIDMatch(),1);
	}
	
	@Test
	public void testAjoutEquipe() {
		match = new Match(1,1,false);
		Match comparator = new Match(1,1,false);
		
		match.AddEquipe(new Equipe(1,"rofl",Nationalite.AD,Disposition.NON_DIPOSEE,14,12));
		comparator.AddEquipe(new Equipe(1,"rofl",Nationalite.AD,Disposition.NON_DIPOSEE,14,12));

		
		assertEquals(match.getEquipes(), comparator.getEquipes());
	}
	
	
	@Test
	public void testRemplacerEquipe() {
		match = new Match(1,1,false);
		Match comparator = new Match(1,1,false);
		
		match.AddEquipe(new Equipe(1,"rofl",Nationalite.AD,Disposition.NON_DIPOSEE,14,12));
		comparator.AddEquipe(new Equipe(1,"flering",Nationalite.AD,Disposition.NON_DIPOSEE,14,12));
		
		match.remplacerEquipe(new Equipe(1,"rofl",Nationalite.AD,Disposition.NON_DIPOSEE,14,12), 
							new Equipe(1,"flering",Nationalite.AD,Disposition.NON_DIPOSEE,14,12));

		
		assertEquals(match.getEquipes(), comparator.getEquipes());
	}
	
	@Test
	public void testAjoutVainqueur() {
		match = new Match(1,1,false);
		
		match.setVainqueur(3);
		
		assertEquals(match.getVainqueur(),3);
	}

}
