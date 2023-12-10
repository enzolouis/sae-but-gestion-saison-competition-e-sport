package classes;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestJoueur {
	private Joueur j;
	
	@Test
	public void testJoueurBasic() {

		j = new Joueur(1,"dorr",1);
		
		assertEquals(j.getPseudo(),"dorr");
		assertEquals(j.getIdJoueur(),1);
		assertEquals(j.getIdEquipe(),1);
	}
	
	@Test
	public void testJoueurChangementPseudo() {

		j = new Joueur(1,"dorr",1);
		
		assertEquals(j.getPseudo(),"dorr");
		
		j.setPseudo("dorreur");
		
		assertEquals(j.getPseudo(),"dorreur");
		assertNotEquals(j.getPseudo(),"dorr");
	}
	
	@Test
	public void testJoueurChangementID() {

		j = new Joueur(1,"dorr",1);
		
		assertEquals(j.getIdJoueur(),1);
		
		j.setIDJoueur(5);
		
		assertEquals(j.getIdJoueur(),5);
		assertNotEquals(j.getIdJoueur(),1);
	}

}
