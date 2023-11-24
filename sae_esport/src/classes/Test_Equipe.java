package classes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class Test_Equipe {
	private Equipe e;

	@Test
	public void testEquipeSansJoueurs() {
		e = new Equipe(1,"rofl",Nationalite.AD,false,14,12);
		
		assertEquals(e.getIdEquipe(),1);
		assertEquals(e.getDisposition(), false);
		assertEquals(e.getNom(),"rofl");
		assertEquals(e.getNationalite(), Nationalite.AD);
		assertEquals(e.getRangSaisonPrecedante(),14);
		assertEquals(e.getPointsSaison(),12);
	}
	
	@Test
	public void testEquipeAVECJoueurs() {
		e = new Equipe(1,
					"rofl",
					Nationalite.AD,
					new ArrayList<Joueur>(),
					false,
					14,
					12);
		
		assertEquals(e.getIdEquipe(),1);
		assertEquals(e.getDisposition(), false);
		assertEquals(e.getNom(),"rofl");
		assertEquals(e.getNationalite(), Nationalite.AD);
		assertEquals(e.getRangSaisonPrecedante(),14);
		assertEquals(e.getListeJoueurs(),new ArrayList<Joueur>());
		assertEquals(e.getPointsSaison(),12);
	}

}
