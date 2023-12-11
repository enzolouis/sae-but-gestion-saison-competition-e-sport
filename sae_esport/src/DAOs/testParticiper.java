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

import classes.EtatTournoi;
import classes.Notoriete;
import classes.Participer;
import modeles.TournoiModele;

public class testParticiper {
	String dirProjetJava = System.getProperty("user.dir");

	Connection connection;
	ParticiperDAO participer;
	
	@Before
	public void beforeTests() {
		
		try {
			
			System.setProperty("derby.system.home", dirProjetJava + "/BDDSAEEsport");
			this.connection = DriverManager.getConnection("jdbc:derby:BDDSAEEsport;create=true");
			this.participer = new ParticiperDAO();
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
	public void testGetByIdTournoiNonExistant() {
	    
		try {
			
			Optional<Participer> participer = this.participer.getByIdTournoi(-1);
		    assertEquals(Optional.empty(), participer);
		    connection.rollback();
		    
		} catch(Exception e) {
	        e.printStackTrace();
	    }
		
	}
	@Test
	/// Olivier RODRIGUEZ
	/// Test de la récupération d'un Arbitre lorsqu'il n'y a pas cet arbitre
	public void testGetByIdEquipeNonExistant() {
	    
		try {
			
			Optional<Participer> participer = this.participer.getByIdEquipe(-1);
		    assertEquals(Optional.empty(), participer);
		    connection.rollback();
		    
		} catch(Exception e) {
	        e.printStackTrace();
	    }
		
	}
	
	
	@Test
	/// Olivier RODRIGUEZ
	/// Test de la récupération d'un administrateur
	public void testGetByIdTournoi() {
	    
		try {
			
			Participer participer = new Participer(
					1,
					1,
					15);
			
			this.participer.add(participer);
			Optional<Participer> optional = this.participer.getByIdTournoi(participer.getIdTournoi());
		    assertEquals(optional.get(), participer);
		    connection.rollback();
		    
		} catch(Exception e) {
	        e.printStackTrace();
	    }
		
	}
	public void testGetByIdEquipe() {
	    
		try {
			
			Participer participer = new Participer(
					1,
					1,
					15);
			
			this.participer.add(participer);
			Optional<Participer> optional = this.participer.getByIdEquipe(participer.getIdTournoi());
		    assertEquals(optional.get(), participer);
		    connection.rollback();
		    
		} catch(Exception e) {
	        e.printStackTrace();
	    }
		
	}
	
	
	@Test
	//Test de la récupération des administrateur lorsqu'il y a des administrateurs
	public void testGetAllMatch() {
		    
		try {
			
			Participer participer1 = new Participer(
					1,
					1,
					15);
			Participer participer2 = new Participer(
					1,
					1,
					15);
			
			this.participer.add(participer1);
			this.participer.add(participer2);
			List<Participer> listMatch = participer.getAll();
		    int index =  listMatch.size();
		    assertEquals(listMatch.get(index-2), participer1);
		    assertEquals(listMatch.get(index-1), participer2);
		    connection.rollback();
			    
		} catch(Exception e) {
		    e.printStackTrace();
		}
		
	}
	
	@Test
	/// Olivier RODRIGUEZ
	/// Test de l'ajout d'administrateur 
	public void testAddViaTournoi() throws SQLException {

	    try{

	    	Participer participer1 = new Participer(
					1,
					1,
					15);
	    	
	    	this.participer.add(participer1);
	    	assertEquals(participer1, participer.getByIdTournoi(participer1.getIdTournoi()).get());
	    	connection.rollback();
	        
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    
	}
	public void testAddViaEquipe() throws SQLException {

	    try{

	    	Participer participer1 = new Participer(
					1,
					1,
					15);
	    	
	    	this.participer.add(participer1);
	    	assertEquals(participer1, participer.getByIdEquipe(participer1.getIdEquipe()).get());
	    	connection.rollback();
	        
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    
	}
	
	@Test
	/// Olivier RODRIGUEZ
	/// Test de la mise à jour d'un administrateur
	public void testUpdateTournoi() throws SQLException {
		
	    try{
	    	
	    	Participer participer1 = new Participer(
					1,
					1,
					15);
	    	
	    	participer.add(participer1);
	    	participer1.setIdTournoi(3);
	        participer.update(participer1);
	        assertEquals(participer1, participer.getByIdTournoi(participer1.getIdTournoi()).get());
	        connection.rollback();
	        
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    
	}
	public void testUpdateEquipe() throws SQLException {
		
	    try{
	    	
	    	Participer participer1 = new Participer(
					1,
					1,
					15);
	    	
	    	participer.add(participer1);
	    	participer1.setIdEquipe(3);
	        participer.update(participer1);
	        assertEquals(participer1, participer.getByIdEquipe(participer1.getIdEquipe()).get());
	        connection.rollback();
	        
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    
	}
	
	@Test
	/// Olivier RODRIGUEZ
	/// Test de supression d'un administrateur
	public void testDeleteTournoi() throws SQLException { 
		
		try{
			
			Participer participer1 = new Participer(
					1,
					1,
					15);
	    	
	        participer.add(participer1);
	        int size = participer.getAll().size();
	        participer.delete(participer1);           
	        assertEquals(size -1, participer.getAll().size());
	        assertEquals(Optional.empty(), participer.getByIdTournoi(participer1.getIdTournoi()));
	        connection.rollback();
	        
	    }catch(Exception e){
	        e.printStackTrace();
	    }

	}
	public void testDeleteEquipe() throws SQLException { 
		
		try{
			
			Participer participer1 = new Participer(
					1,
					1,
					15);
	    	
	        participer.add(participer1);
	        int size = participer.getAll().size();
	        participer.delete(participer1);           
	        assertEquals(size -1, participer.getAll().size());
	        assertEquals(Optional.empty(), participer.getByIdEquipe(participer1.getIdEquipe()));
	        connection.rollback();
	        
	    }catch(Exception e){
	        e.printStackTrace();
	    }

	}

}
