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

import classes.Equipe;
import classes.Nationalite;
import classes.Equipe;

public class testEquipeDAO {

	String dirProjetJava = System.getProperty("user.dir");
	Connection connection;
	EquipeDAO equipeDAO;
	
	@Before
	public void beforeTests() {
		
		try {
			
			System.setProperty("derby.system.home", dirProjetJava + "/BDDSAEEsport");
			this.connection = DriverManager.getConnection("jdbc:derby:BDDSAEEsport;create=true");
			this.equipeDAO = new EquipeDAO();
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
	public void testGetByIdMatchNonExistant() {
	    
		try {
			
			Optional<Equipe> equipe = equipeDAO.getById(-1);
		    assertEquals(Optional.empty(), equipe);
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
			
			Equipe equipe = new Equipe(1,"rofl",Nationalite.AD,false,14,12);
			equipeDAO.add(equipe);
			Optional<Equipe> optional = equipeDAO.getById(equipe.getIdEquipe());
		    assertEquals(optional.get(), equipe);
		    connection.rollback();
		    
		} catch(Exception e) {
	        e.printStackTrace();
	    }
		
	}
	
	
	@Test
	//Test de la récupération des Equipes lorsqu'il y a des administrateurs
	public void testGetAllMatch() {
		    
		try {
			
			Equipe equipe1 = new Equipe(1,"rofl",Nationalite.AD,false,14,12);
			Equipe equipe2 = new Equipe(1,"rofl",Nationalite.AD,false,14,12);

			this.equipeDAO.add(equipe1);
			this.equipeDAO.add(equipe2);
			List<Equipe> listMatch = equipeDAO.getAll();
		    int index =  listMatch.size();
		    assertEquals(listMatch.get(index-2), equipe1);
		    assertEquals(listMatch.get(index-1), equipe2);
		    connection.rollback();
			    
		} catch(Exception e) {
		    e.printStackTrace();
		}
		
	}
	
	@Test
	/// Olivier RODRIGUEZ
	/// Test de l'ajout d'Equipe 
	public void testAddEquipe() throws SQLException {

	    try{

			Equipe equipe1 = new Equipe(1,"rofl",Nationalite.AD,false,14,12);
	    	equipeDAO.add(equipe1);
	    	assertEquals(equipe1, equipeDAO.getById(equipe1.getIdEquipe()).get());
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
	    	
			Equipe equipe1 = new Equipe(1,"rofl",Nationalite.AD,false,14,12);
	        equipeDAO.add(equipe1);
	        equipe1.setIdEquipe(3);
	        equipeDAO.update(equipe1);
	        assertEquals(equipe1, equipeDAO.getById(equipe1.getIdEquipe()).get());
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
			
			Equipe equipe1 = new Equipe(1,"rofl",Nationalite.AD,false,14,12);
	        equipeDAO.add(equipe1);
	        int size = equipeDAO.getAll().size();
	        equipeDAO.delete(equipe1);           
	        assertEquals(size -1, equipeDAO.getAll().size());
	        assertEquals(Optional.empty(), equipeDAO.getById(equipe1.getIdEquipe()));
	        connection.rollback();
	        
	    }catch(Exception e){
	        e.printStackTrace();
	    }

	}
}
