package tests;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DAOs.EquipeDAO;
import DAOs.ParticiperDAO;
import classes.DBConnection;
import classes.Disposition;
import classes.Equipe;
import classes.Nationalite;
import classes.Participer;

public class TestParticiperDAO {
	
	private Equipe equipeTest;
	private Equipe equipeTest2;
	
	@Before
	public void beforeTests() throws Exception{
			DBConnection.getInstance().setAutoCommit(false);
			equipeTest = new Equipe(0, "nom", Nationalite.FR, Disposition.DISPOSEE, 0, 0);
			equipeTest2 = new Equipe(0, "nom2", Nationalite.FR, Disposition.DISPOSEE, 0, 0);
			EquipeDAO.getInstance().add(equipeTest); EquipeDAO.getInstance().add(equipeTest2);
	}
	
	@After
	public void afterTests() throws Exception{
		DBConnection.getInstance().setAutoCommit(true);
	}
	
	@Test
	/// Test de la récupération des participations d'un tournoi non existant 
	public void testGetByIdTournoiNonExistant() throws Exception{
	    List<Participer> participations = ParticiperDAO.getInstance().getByIdTournoi(-1);
	    assertTrue(participations.isEmpty());
	}
	
	@Test
	/// Test de la récupération des participations d'une équipe non existante
	public void testGetByIdEquipeNonExistant() throws Exception{
		List<Participer> participations = ParticiperDAO.getInstance().getByIdEquipe(-1);
	    assertTrue(participations.isEmpty());
	}
	
	@Test
	/// Test de la récupération de participations par tournoi
	public void testGetByIdTournoi() throws Exception{
	    Participer participer = new Participer(15,1,equipeTest.getIdEquipe());
		ParticiperDAO.getInstance().add(participer);
		List<Participer> participations = ParticiperDAO.getInstance().getByIdTournoi(participer.getIdTournoi());
		assertTrue(participations.contains(participer));
		DBConnection.getInstance().rollback();
	}
	
	@Test
	public void testGetByIdEquipe() throws Exception{
	    Participer participer = new Participer(15,1,equipeTest.getIdEquipe());
		ParticiperDAO.getInstance().add(participer);
		List<Participer> participations = ParticiperDAO.getInstance().getByIdEquipe(participer.getIdEquipe());
		assertTrue(participations.contains(participer));
		DBConnection.getInstance().rollback();
	}
	
	@Test
	//Test de la récupération de toutes les participations
	public void testGetAllParticiper() throws Exception{
		    Participer participer1 = new Participer(15,1,equipeTest.getIdEquipe());
			Participer participer2 = new Participer(15,1,equipeTest2.getIdEquipe());
			ParticiperDAO.getInstance().add(participer1);
			ParticiperDAO.getInstance().add(participer2);
			List<Participer> participations = ParticiperDAO.getInstance().getAll();
		    assertTrue(participations.contains(participer1));
		    assertTrue(participations.contains(participer2));
		    DBConnection.getInstance().rollback();
	}
	
	@Test
	/// Test de l'ajout d'une participation
	public void testAddViaTournoi() throws Exception {
		
		Participer participer = new Participer(15,1,equipeTest.getIdEquipe());
		ParticiperDAO.getInstance().add(participer);
	    assertEquals(participer, ParticiperDAO.getInstance().
	    		getByIdTournoiIdEquipe(participer.getIdEquipe(), participer.getIdTournoi()).get());
	    DBConnection.getInstance().rollback();
	    	
	}
	
	@Test
	/// Test de la mise à jour d'une participation
	public void testUpdate() throws Exception {
		Participer participer = new Participer(15,1,equipeTest.getIdEquipe());
	    ParticiperDAO.getInstance().add(participer);
	    participer.setResultat(20);
	    ParticiperDAO.getInstance().update(participer);
	    assertEquals(participer,ParticiperDAO.getInstance()
	    		.getByIdTournoiIdEquipe(participer.getIdEquipe(), participer.getIdTournoi()).get());
	    DBConnection.getInstance().rollback();
	}
	
	@Test
	/// Test de supression d'une participation
	public void testDelete() throws Exception { 
		Participer participer = new Participer(15,1,equipeTest.getIdEquipe());
	    ParticiperDAO.getInstance().add(participer);
	    int size = ParticiperDAO.getInstance().getAll().size();
	    ParticiperDAO.getInstance().delete(participer);           
	    assertEquals(size -1, ParticiperDAO.getInstance().getAll().size());
	    assertEquals(Optional.empty(), ParticiperDAO.getInstance().
	    		getByIdTournoiIdEquipe(participer.getIdEquipe(), participer.getIdTournoi()));
	    DBConnection.getInstance().rollback();
	}

}
