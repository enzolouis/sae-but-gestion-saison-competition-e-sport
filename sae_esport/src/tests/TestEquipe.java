package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import classes.Disposition;
import classes.Equipe;
import classes.Joueur;
import classes.Nationalite;

public class TestEquipe {
	private Equipe e;

	@Test
	public void testEquipeSansJoueurs() {
		e = new Equipe(1, "rofl", Nationalite.AD, Disposition.NON_DISPOSEE, 14, 12);

		assertEquals(e.getIdEquipe(), 1);
		assertEquals(e.getDisposition(), Disposition.NON_DISPOSEE);
		assertEquals(e.getNom(), "rofl");
		assertEquals(e.getNationalite(), Nationalite.AD);
		assertEquals(e.getRangSaisonPrecedante(), 14);
		assertEquals(e.getPointsSaison(), 12);
	}

	@Test
	public void testEquipeAVECJoueurs() {
		List<Joueur> equipes = new ArrayList<Joueur>();

		equipes.add(new Joueur(1, "dorr", 1));

		e = new Equipe(1,
				"rofl",
				Nationalite.AD,
				new ArrayList<Joueur>(),
				Disposition.NON_DISPOSEE,
				14,
				12);

		assertEquals(e.getIdEquipe(), 1);
		assertEquals(e.getDisposition(), Disposition.NON_DISPOSEE);
		assertEquals(e.getNom(), "rofl");
		assertEquals(e.getNationalite(), Nationalite.AD);
		assertEquals(e.getRangSaisonPrecedante(), 14);
		assertEquals(e.getListeJoueurs(), new ArrayList<Joueur>());
		assertEquals(e.getPointsSaison(), 12);
	}

	@Test
	public void testEquipeChangementDisposition() {
		e = new Equipe(1,
				"rofl",
				Nationalite.AD,
				new ArrayList<Joueur>(),
				Disposition.NON_DISPOSEE,
				14,
				12);

		assertEquals(e.getDisposition(), Disposition.NON_DISPOSEE);

		e.setDisposition(Disposition.DISPOSEE);
		;

		assertEquals(e.getDisposition(), Disposition.DISPOSEE);
		assertNotEquals(e.getDisposition(), Disposition.NON_DISPOSEE);

	}

	@Test
	public void testEquipeChangementPointsSaison() {
		e = new Equipe(1,
				"rofl",
				Nationalite.AD,
				Disposition.NON_DISPOSEE,
				14,
				12);

		assertEquals(e.getPointsSaison(), 12);

		e.setPointsSaison(33);

		assertEquals(e.getPointsSaison(), 33);
		assertNotEquals(e.getPointsSaison(), 12);
	}

	@Test
	public void testEquipeAjoutDePoints() {
		e = new Equipe(1,
				"rofl",
				Nationalite.AD,
				Disposition.NON_DISPOSEE,
				14,
				12);

		assertEquals(e.getPointsSaison(), 12);

		e.ajoutDePoints(3);

		assertEquals(e.getPointsSaison(), 15);
		assertNotEquals(e.getPointsSaison(), 12);
	}

	@Test
	public void testEquipeAjoutDeJoueur() {
		e = new Equipe(1, "rofl", Nationalite.AD, Disposition.NON_DISPOSEE, 14, 12);
		Joueur j = new Joueur(1, "dorr", 1);
		e.ajouterJoueur(j);
		assertEquals(e.getListeJoueurs().get(0), j);
	}

}
