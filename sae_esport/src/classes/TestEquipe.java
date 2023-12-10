package classes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestEquipe {
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
		List<Joueur> equipes = new ArrayList<Joueur>();
		
		equipes.add(new Joueur(1,"dorr",1));
		
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
	
	@Test
	public void testEquipeChangementDisposition() {
		e = new Equipe(1,
					"rofl",
					Nationalite.AD,
					new ArrayList<Joueur>(),
					false,
					14,
					12);
	
		assertEquals(e.getDisposition(), false);
		
		e.setDisposition(true);;
	
		assertEquals(e.getDisposition(), true);
		assertNotEquals(e.getDisposition(), false);

	}
	
	@Test
	public void testEquipeChangementPointsSaison() {
		e = new Equipe(1,
					"rofl",
					Nationalite.AD,
					false,
					14,
					12);
		
		assertEquals(e.getPointsSaison(),12);
		
		e.setPointsSaison(33);
		
		assertEquals(e.getPointsSaison(),33);
		assertNotEquals(e.getPointsSaison(),12);
	}
	
	
	@Test
	public void testEquipeAjoutDePoints() {
		e = new Equipe(1,
					"rofl",
					Nationalite.AD,
					false,
					14,
					12);
		
		
		assertEquals(e.getPointsSaison(),12);
		
		e.ajoutDePoints(3);
		
		assertEquals(e.getPointsSaison(),15);
		assertNotEquals(e.getPointsSaison(),12);
	}
	
	@Test
	public void testEquipeAjoutDeJoueur() {
		e = new Equipe(1,"rofl",Nationalite.AD,false,14,12);
		Joueur j = new Joueur(1,"dorr",1);
		e.AjouterJoueurs(j);
		assertEquals(e.getListeJoueurs().get(0),j);
	}

}
