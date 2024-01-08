package DAOs;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import classes.DBConnection;
import classes.EtatTournoi;
import classes.Notoriete;
import classes.Participer;
import modeles.TournoiModele;

public class testParticiperDAO {
	
	@Before
	public void beforeTests() throws Exception{
			DBConnection.getInstance().setAutoCommit(false);
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
	    Participer participer = new Participer(1,1,15);
		ParticiperDAO.getInstance().add(participer);
		List<Participer> participations = ParticiperDAO.getInstance().getByIdTournoi(participer.getIdTournoi());
		assertTrue(participations.contains(participer));
		DBConnection.getInstance().rollback();
	}
	
	@Test
	public void testGetByIdEquipe() throws Exception{
	    Participer participer = new Participer(1,1,15);
		ParticiperDAO.getInstance().add(participer);
		List<Participer> participations = ParticiperDAO.getInstance().getByIdEquipe(participer.getIdEquipe());
		assertTrue(participations.contains(participer));
		DBConnection.getInstance().rollback();
	}
	
	
	@Test
	//Test de la récupération de toutes les participations
	public void testGetAllParticiper() throws Exception{
		    Participer participer1 = new Participer(1,1,15);
			Participer participer2 = new Participer(1,2,15);
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
		
		Participer participer = new Participer(1,1,15);
		ParticiperDAO.getInstance().add(participer);
	    assertEquals(participer, 
	    		ParticiperDAO.getInstance().
	    		getByIdTournoiIdEquipe(participer.getIdEquipe(), participer.getIdTournoi()));
	    DBConnection.getInstance().rollback();
	    	
	}
	
	@Test
	/// Test de la mise à jour d'une participation
	public void testUpdate() throws Exception {
		Participer participer = new Participer(1,1,15);
	    ParticiperDAO.getInstance().add(participer);
	    participer.setResultat(20);
	    ParticiperDAO.getInstance().update(participer);
	    assertEquals(participer,ParticiperDAO.getInstance()
	    		.getByIdTournoiIdEquipe(participer.getIdEquipe(), participer.getIdTournoi()));
	    DBConnection.getInstance().rollback();
	}
	
	@Test
	/// Test de supression d'une participation
	public void testDelete() throws Exception { 
		Participer participer = new Participer(1,1,15);
	    ParticiperDAO.getInstance().add(participer);
	    int size = ParticiperDAO.getInstance().getAll().size();
	    ParticiperDAO.getInstance().delete(participer);           
	    assertEquals(size -1, ParticiperDAO.getInstance().getAll().size());
	    assertEquals(Optional.empty(), ParticiperDAO.getInstance().
	    		getByIdTournoiIdEquipe(participer.getIdEquipe(), participer.getIdTournoi()));
	    DBConnection.getInstance().rollback();
	}

}
