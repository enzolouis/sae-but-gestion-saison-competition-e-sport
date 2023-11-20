package classes;

import static org.junit.Assert.*;

import org.junit.*;

public class TEST_Arbitre {
	
	private Arbitre a;

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	//test des instances correcte
	@Test
	public void testArbitreCorrectTraits() {
		a = new Arbitre(1,"Gendre", "Xavier", Nationalite.FR);
		
		assertEquals(a.getNom(), "Gendre");
		assertEquals(a.getIdArbitre(), 1);
		assertEquals(a.getPrenom(),"Xavier");
		assertEquals(a.getNationalite(), Nationalite.FR);
	}
	
	//test des instances incorrecte
	@Test
	public void testArbitreIncorrectTraits() {
		a = new Arbitre(1,"Gendre", "Xavier", Nationalite.FR);
		
		assertNotEquals(a.getNom(), "Gendrerior");
		assertNotEquals(a.getIdArbitre(), 13);
		assertNotEquals(a.getPrenom(),"Xaviera");
		assertNotEquals(a.getNationalite(), Nationalite.GA);

	}
	
	@Test
	public void testArbitreChangementNoms() {
		a = new Arbitre(1,"Gendre", "Xavier", Nationalite.FR);
		
		assertEquals(a.getNom(), "Gendre");
		
		a.setNom("Gendrerior");
		
		assertEquals(a.getNom(), "Gendrerior");
		assertNotEquals(a.getNom(), "Gendre");
	}
	
	@Test
	public void testArbitreChangementPrenoms() {
		a = new Arbitre(1,"Gendre", "Xavier", Nationalite.FR);
		
		assertEquals(a.getPrenom(), "Xavier");
		
		a.setPrenom("Xaviera");
		
		assertEquals(a.getPrenom(), "Xaviera");
		assertNotEquals(a.getPrenom(), "Xavier");
	}
	
	@Test
	public void testArbitreChangementNationalite() {
		a = new Arbitre(1,"Gendre", "Xavier", Nationalite.FR);
		
		assertEquals(a.getNationalite(), Nationalite.FR);
		
		a.setNationalite(Nationalite.GA);
		
		assertEquals(a.getNationalite(), Nationalite.GA);
		assertNotEquals(a.getNationalite(), Nationalite.FR);
	}
	
	
}
