package tests;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DAOs.ArbitreDAO;
import classes.Arbitre;
import classes.DBConnection;
import classes.Nationalite;

public class TestArbitreDAO {

	@Before
	public void beforeTests() throws Exception {

		DBConnection.getInstance().setAutoCommit(false);

	}

	@After
	public void afterTests() throws Exception {

		DBConnection.getInstance().setAutoCommit(true);

	}

	@Test
	/// Ryan GAUNAND
	/// Test de la récupération d'un Arbitre lorsqu'il n'y a pas cet arbitre
	public void testGetByIdArbitreNonExistant() throws Exception {
		Optional<Arbitre> arbitre = ArbitreDAO.getInstance().getById(-1);
		assertEquals(Optional.empty(), arbitre);
		DBConnection.getInstance().rollback();
	}

	@Test
	/// Ryan GAUNAND
	/// Test de la récupération d'un arbitre
	public void testGetByIdArbitre() throws Exception {
		Arbitre arbitre = new Arbitre(0, "Vanilla", "Mieux", Nationalite.JP);
		ArbitreDAO.getInstance().add(arbitre);
		Optional<Arbitre> optional = ArbitreDAO.getInstance().getById(arbitre.getIdArbitre());
		assertEquals(optional.get(), arbitre);
		DBConnection.getInstance().rollback();
	}

	@Test
	// Test de la récupération des arbitres
	public void testGetAllArbitre() throws Exception {
		Arbitre admin1 = new Arbitre(0, "Vanilla", "Mieux", Nationalite.JP);
		Arbitre admin2 = new Arbitre(0, "Pierre", "TempêteDeNeige", Nationalite.FR);
		ArbitreDAO.getInstance().add(admin1);
		ArbitreDAO.getInstance().add(admin2);
		List<Arbitre> listArbitre = ArbitreDAO.getInstance().getAll();
		int index = listArbitre.size();
		assertEquals(listArbitre.get(index - 2), admin1);
		assertEquals(listArbitre.get(index - 1), admin2);
		DBConnection.getInstance().rollback();
	}

	@Test
	/// Ryan GAUNAND
	/// Test de l'ajout d'Arbitre
	public void testAddArbitre() throws Exception {
		Arbitre admin = new Arbitre(0, "Vanilla", "Mieux", Nationalite.JP);
		ArbitreDAO.getInstance().add(admin);
		assertEquals(admin, ArbitreDAO.getInstance().getById(admin.getIdArbitre()).get());
		DBConnection.getInstance().rollback();
	}

	@Test
	/// Ryan GAUNAND
	/// Test de la mise à jour d'un Arbitre
	public void testUpdateArbitre() throws Exception {
		Arbitre admin = new Arbitre(0, "Vanilla", "Mieux", Nationalite.JP);
		ArbitreDAO.getInstance().add(admin);
		String newName = "Menthe";
		admin.setNom(newName);
		ArbitreDAO.getInstance().update(admin);
		assertEquals(admin, ArbitreDAO.getInstance().getById(admin.getIdArbitre()).get());
		DBConnection.getInstance().rollback();
	}

	@Test
	/// Ryan GAUNAND
	/// Test de supression d'un Arbitre
	public void testDeleteArbitre() throws Exception {
		Arbitre admin = new Arbitre(0, "Vanilla", "Mieux", Nationalite.JP);
		ArbitreDAO.getInstance().add(admin);
		int size = ArbitreDAO.getInstance().getAll().size();
		ArbitreDAO.getInstance().delete(admin);
		assertEquals(size - 1, ArbitreDAO.getInstance().getAll().size());
		assertEquals(Optional.empty(), ArbitreDAO.getInstance().getById(admin.getIdArbitre()));
		DBConnection.getInstance().rollback();
	}
}
