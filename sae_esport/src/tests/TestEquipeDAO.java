package tests;

import static org.junit.Assert.*;
import java.util.List;
import java.util.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import DAOs.EquipeDAO;
import classes.DBConnection;
import classes.Disposition;
import classes.Equipe;
import classes.Nationalite;

public class TestEquipeDAO {
	
	@Before
	public void beforeTests() throws Exception{
		DBConnection.getInstance().setAutoCommit(false);
	}
	
	@After
	public void afterTests() throws Exception{
		DBConnection.getInstance().setAutoCommit(true);
	}
	
	@Test
	/// Olivier RODRIGUEZ
	/// Test de la récupération d'un Arbitre lorsqu'il n'y a pas cet arbitre
	public void testGetByIdMatchNonExistant() throws Exception{
	    Optional<Equipe> equipe = EquipeDAO.getInstance().getById(-1);
		    assertEquals(Optional.empty(), equipe);
		    DBConnection.getInstance().rollback();
	}
	
	
	@Test
	/// Olivier RODRIGUEZ
	/// Test de la récupération d'un administrateur
	public void testGetByIdMatch() throws Exception{
	    Equipe equipe = new Equipe(1,"rofl",Nationalite.AD,Disposition.NON_DISPOSEE,14,12);
			EquipeDAO.getInstance().add(equipe);
			Optional<Equipe> optional = EquipeDAO.getInstance().getById(equipe.getIdEquipe());
		    assertEquals(optional.get(), equipe);
		    DBConnection.getInstance().rollback();
	}
	
	
	@Test
	//Test de la récupération des Equipes lorsqu'il y a des administrateurs
	public void testGetAllMatch() throws Exception{
		    Equipe equipe1 = new Equipe(1,"rofl",Nationalite.AD,Disposition.NON_DISPOSEE,14,12);
			Equipe equipe2 = new Equipe(1,"rofl",Nationalite.AD,Disposition.NON_DISPOSEE,14,12);

			EquipeDAO.getInstance().add(equipe1);
			EquipeDAO.getInstance().add(equipe2);
			List<Equipe> listMatch = EquipeDAO.getInstance().getAll();
		    int index =  listMatch.size();
		    assertEquals(listMatch.get(index-2), equipe1);
		    assertEquals(listMatch.get(index-1), equipe2);
		    DBConnection.getInstance().rollback();
	}
	
	@Test
	/// Olivier RODRIGUEZ
	/// Test de l'ajout d'Equipe 
	public void testAddEquipe() throws Exception {
		Equipe equipe1 = new Equipe(1,"rofl",Nationalite.AD,Disposition.NON_DISPOSEE,14,12);
	    	EquipeDAO.getInstance().add(equipe1);
	    	assertEquals(equipe1, EquipeDAO.getInstance().getById(equipe1.getIdEquipe()).get());
	    	DBConnection.getInstance().rollback();
	}
	
	@Test
	/// Olivier RODRIGUEZ
	/// Test de la mise à jour d'un administrateur
	public void testUpdateMatch() throws Exception {
		Equipe equipe1 = new Equipe(1,"rofl",Nationalite.AD,Disposition.NON_DISPOSEE,14,12);
	        EquipeDAO.getInstance().add(equipe1);
	        equipe1.setDisposition(Disposition.DISPOSEE);
	        EquipeDAO.getInstance().update(equipe1);
	        assertEquals(equipe1, EquipeDAO.getInstance().getById(equipe1.getIdEquipe()).get());
	        DBConnection.getInstance().rollback();
	}
	
	@Test
	/// Olivier RODRIGUEZ
	/// Test de supression d'un administrateur
	public void testDeleteMatch() throws Exception { 
		Equipe equipe1 = new Equipe(1,"rofl",Nationalite.AD,Disposition.NON_DISPOSEE,14,12);
	        EquipeDAO.getInstance().add(equipe1);
	        int size = EquipeDAO.getInstance().getAll().size();
	        EquipeDAO.getInstance().delete(equipe1);           
	        assertEquals(size -1, EquipeDAO.getInstance().getAll().size());
	        assertEquals(Optional.empty(), EquipeDAO.getInstance().getById(equipe1.getIdEquipe()));
	        DBConnection.getInstance().rollback();
	}
	
}
