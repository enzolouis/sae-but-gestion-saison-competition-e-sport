package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import classes.Administrateur;

public class TestAdmin {

	private Administrateur admin;

	@Test
	public void testInstanceAdmin() {
		admin = new Administrateur(1, "Jean", "login", "123");

		assertEquals(admin.getIdAdministrateur(), 1);
		assertEquals(admin.getNom(), "Jean");
		assertEquals(admin.getLogin(), "login");
		assertEquals(admin.getMotDePasse(), "123");
	}

	@Test
	public void testChangementNoms() {
		admin = new Administrateur(1, "Jean", "login", "123");

		assertEquals(admin.getNom(), "Jean");

		admin.setNom("Pat");

		assertEquals(admin.getNom(), "Pat");
		assertNotEquals(admin.getNom(), "Jean");
	}

	@Test
	public void testChangementIDAdmin() {
		admin = new Administrateur(1, "Jean", "login", "123");

		assertEquals(admin.getIdAdministrateur(), 1);

		admin.setIdAdministrateur(3);

		assertEquals(admin.getIdAdministrateur(), 3);
		assertNotEquals(admin.getIdAdministrateur(), 1);
	}

}
