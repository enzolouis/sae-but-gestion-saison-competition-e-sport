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

import classes.Match;

public class testDAOMatch {

	String dirProjetJava = System.getProperty("user.dir");
	Connection connection;
	MatchDAO matchDAO;
	
	@Before
	public void beforeTests() throws Exception{
		System.setProperty("derby.system.home", dirProjetJava + "/BDDSAEEsport");
			this.connection = DriverManager.getConnection("jdbc:derby:BDDSAEEsport;create=true");
			this.matchDAO = new MatchDAO();
			connection.setAutoCommit(false);
	}
	
	@After
	public void afterTests() throws Exception{
		
			connection.setAutoCommit(true);
			connection.close();
	}
	
	@Test
	/// Olivier RODRIGUEZ
	/// Test de la récupération d'un Arbitre lorsqu'il n'y a pas cet arbitre
	public void testGetByIdMatchNonExistant()throws Exception {
	    Optional<Match> match = matchDAO.getById(-1);
		    assertEquals(Optional.empty(), match);
		    connection.rollback();
	}
	
	
	@Test
	/// Olivier RODRIGUEZ
	/// Test de la récupération d'un administrateur
	public void testGetByIdMatch() throws Exception{
	    	Match match = new Match(0, false);
			matchDAO.add(match);
			Optional<Match> optional = matchDAO.getById(match.getIDMatch());
		    assertEquals(optional.get(), match);
		    connection.rollback();
	}
	
	
	@Test
	//Test de la récupération des administrateur lorsqu'il y a des administrateurs
	public void testGetAllMatch() throws Exception{
		    Match match1 = new Match(0, false);
			Match match2 = new Match(0, false);	
			this.matchDAO.add(match1);
			this.matchDAO.add(match2);
			List<Match> listMatch = matchDAO.getAll();
		    int index =  listMatch.size();
		    assertEquals(listMatch.get(index-2), match1);
		    assertEquals(listMatch.get(index-1), match2);
		    connection.rollback();
	}
	
	@Test
	/// Olivier RODRIGUEZ
	/// Test de l'ajout d'administrateur 
	public void testAddMatch() throws Exception {
		Match match = new Match(-1, true); 
	    	matchDAO.add(match);
	    	assertEquals(match, matchDAO.getById(match.getIDMatch()).get());
	    	connection.rollback();
	}
	
	@Test
	/// Olivier RODRIGUEZ
	/// Test de la mise à jour d'un administrateur
	public void testUpdateMatch() throws Exception {
		Match match = new Match(-1, true);
	        matchDAO.add(match);
	        match.setIdMatch(3);
	        matchDAO.update(match);
	        assertEquals(match, matchDAO.getById(match.getIDMatch()).get());
	        connection.rollback();
	}
	
	@Test
	/// Olivier RODRIGUEZ
	/// Test de supression d'un administrateur
	public void testDeleteMatch() throws Exception { 
		Match match = new Match(-1, true); 
	        matchDAO.add(match);
	        int size = matchDAO.getAll().size();
	        matchDAO.delete(match);           
	        assertEquals(size -1, matchDAO.getAll().size());
	        assertEquals(Optional.empty(), matchDAO.getById(match.getIDMatch()));
	        connection.rollback();
	}

}
