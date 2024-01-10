package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import classes.Participer;

public class TestParticiper {

	Participer participer;
	
	@Test
	public void testCreationParticiper() {
		participer = new Participer(5,1,1);
		
		assertEquals(participer.getIdEquipe(),1);
		assertEquals(participer.getIdTournoi(),1);
		assertEquals(participer.getResultat(),5);
	}
	
	@Test
	public void testChangementIdEquipe() {
		participer = new Participer(1,1,5);
		
		participer.setIdEquipe(3);
		
		assertEquals(participer.getIdEquipe(),3);
		assertNotEquals(participer.getIdEquipe(),1);
	}
	
	@Test
	public void testChangementIdTournoi() {
		participer = new Participer(1,1,5);
		
		participer.setIdTournoi(3);
		
		assertEquals(participer.getIdTournoi(),3);
		assertNotEquals(participer.getIdTournoi(),1);
	}
	
	@Test
	public void testChangementResultat() {
		participer = new Participer(1,1,5);
		
		participer.setResultat(10);
		
		assertEquals(participer.getResultat(),10);
		assertNotEquals(participer.getResultat(),5);
	}

}
