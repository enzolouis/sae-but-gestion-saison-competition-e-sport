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
	public void beforeTests() {
		
		try {
			
			System.setProperty("derby.system.home", dirProjetJava + "/BDDSAEEsport");
			this.connection = DriverManager.getConnection("jdbc:derby:BDDSAEEsport;create=true");
			this.matchDAO = new MatchDAO();
			connection.setAutoCommit(false);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@After
	public void afterTests() {
		
		try {
			
			connection.setAutoCommit(true);
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	/// Olivier RODRIGUEZ
	/// Test de la récupération d'un Arbitre lorsqu'il n'y a pas cet arbitre
	public void testGetByIdArbitreNonExistant() {
	    
		try {
			
			Optional<Match> match = matchDAO.getById(-1);
		    assertEquals(Optional.empty(), match);
		    connection.rollback();
		    
		} catch(Exception e) {
	        e.printStackTrace();
	    }
		
	}
	
	
	@Test
	/// Olivier RODRIGUEZ
	/// Test de la récupération d'un administrateur
	public void testGetByIdMatch() {
	    
		try {
			
			Match match = new Match(0, false);
			matchDAO.add(match);
			Optional<Match> optional = matchDAO.getById(match.getIDMatch());
		    assertEquals(optional.get(), match);
		    connection.rollback();
		    
		} catch(Exception e) {
	        e.printStackTrace();
	    }
		
	}
	
	
	@Test
	//Test de la récupération des administrateur lorsqu'il y a des administrateurs
	public void testGetAllMatch() {
		    
		try {
			
			Match match1 = new Match(0, false);
			Match match2 = new Match(0, false);	
			matchDAO.add(match1);
			matchDAO.add(match2);
			List<Match> listMatch = matchDAO.getAll();
		    int index =  listMatch.size();
		    assertEquals(listMatch.get(index-2), match1);
		    assertEquals(listMatch.get(index-1), match2);
		    connection.rollback();
			    
		} catch(Exception e) {
		    e.printStackTrace();
		}
		
	}
	
	@Test
	/// Olivier RODRIGUEZ
	/// Test de l'ajout d'administrateur 
	public void testAddMatch() throws SQLException {

	    try{

	    	Match match = new Match(-1, true); 
	    	matchDAO.add(match);
	    	assertEquals(match, matchDAO.getById(match.getIDMatch()).get());
	    	connection.rollback();
	        
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    
	}
	
	@Test
	/// Olivier RODRIGUEZ
	/// Test de la mise à jour d'un administrateur
	public void testUpdateMatch() throws SQLException {
		
	    try{
	    	
	    	Match match = new Match(-1, true);
	        matchDAO.add(match);
	        match.setIdMatch(3);
	        matchDAO.update(match);
	        assertEquals(match, matchDAO.getById(match.getIDMatch()).get());
	        connection.rollback();
	        
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    
	}
	
	@Test
	/// Olivier RODRIGUEZ
	/// Test de supression d'un administrateur
	public void testDeleteMatch() throws SQLException { 
		
		try{
			
			Match match = new Match(-1, true); 
	        matchDAO.add(match);
	        int size = matchDAO.getAll().size();
	        matchDAO.delete(match);           
	        assertEquals(size -1, matchDAO.getAll().size());
	        assertEquals(Optional.empty(), matchDAO.getById(match.getIDMatch()));
	        connection.rollback();
	        
	    }catch(Exception e){
	        e.printStackTrace();
	    }

	}

}
