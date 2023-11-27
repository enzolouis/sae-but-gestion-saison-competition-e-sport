package DAOs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import classes.Arbitre;
import classes.Arbitre;
import classes.Nationalite;

public class testDAOArbitre {
	
	String dirProjetJava = System.getProperty("user.dir");
	Connection connection;
	ArbitreDAO arbitreDAO;
	
	@Before
	public void beforeTests() {
		
		try {
			
			System.setProperty("derby.system.home", dirProjetJava + "/BDDSAEEsport");
			this.connection = DriverManager.getConnection("jdbc:derby:BDDSAEEsport;create=true");
			this.arbitreDAO = new ArbitreDAO(connection);
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
	/// Ryan GAUNAND
	/// Test de la récupération d'un Arbitre lorsqu'il n'y a pas cet arbitre
	public void testGetByIdArbitreNonExistant() {
	    
		try {
			
			Optional<Arbitre> arbitre = arbitreDAO.getById(-1);
		    assertEquals(Optional.empty(), arbitre);
		    connection.rollback();
		    
		} catch(Exception e) {
	        e.printStackTrace();
	    }
		
	}
	
	@Test
	/// Ryan GAUNAND
	/// Test de la récupération d'un arbitre
	public void testGetByIdArbitre() {
	    
		try {
			
			Arbitre arbitre = new Arbitre(0, "Vanilla", "Mieux", Nationalite.JP);
			arbitreDAO.add(arbitre);
			Optional<Arbitre> optional = arbitreDAO.getById(arbitre.getIdArbitre());
		    assertEquals(optional.get(), arbitre);
		    connection.rollback();
		    
		} catch(Exception e) {
	        e.printStackTrace();
	    }
		
	}
	
	
	@Test
	//Test de la récupération des arbitres
	public void testGetAllArbitre() {
		    
		try {
			
			Arbitre admin1 = new Arbitre(0, "Vanilla", "Mieux", Nationalite.JP);
			Arbitre admin2 = new Arbitre(0, "Pierre", "TempêteDeNeige", Nationalite.FR);	
			arbitreDAO.add(admin1);
			arbitreDAO.add(admin2);
			List<Arbitre> listArbitre = arbitreDAO.getAll();
			int index = listArbitre.size();
		    assertEquals(listArbitre.get(index-2), admin1);
		    assertEquals(listArbitre.get(index-1), admin2);
		    connection.rollback();
			    
		} catch(Exception e) {
		    e.printStackTrace();
		}
		
	}
	
	@Test
	/// Ryan GAUNAND
	/// Test de l'ajout d'Arbitre 
	public void testAddArbitre() throws SQLException {

	    try{

			Arbitre admin = new Arbitre(0, "Vanilla", "Mieux", Nationalite.JP);
	    	arbitreDAO.add(admin);
	    	assertEquals(admin, arbitreDAO.getById(admin.getIdArbitre()).get());
	    	connection.rollback();
	        
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    
	}
	
	@Test
	/// Ryan GAUNAND
	/// Test de la mise à jour d'un Arbitre
	public void testUpdateArbitre() throws SQLException {
		
	    try{
	    	
			Arbitre admin = new Arbitre(0, "Vanilla", "Mieux", Nationalite.JP);
	        arbitreDAO.add(admin);
	        String newName = "Menthe";
	        admin.setNom(newName);
	        arbitreDAO.update(admin);
	        assertEquals(admin, arbitreDAO.getById(admin.getIdArbitre()).get());
	        connection.rollback();
	        
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    
	}
	
	@Test
	/// Ryan GAUNAND
	/// Test de supression d'un Arbitre
	public void testDeleteArbitre() throws SQLException { 
		
		try{
			
			Arbitre admin = new Arbitre(0, "Vanilla", "Mieux", Nationalite.JP);
	        arbitreDAO.add(admin);
	        int size = arbitreDAO.getAll().size();
	        arbitreDAO.delete(admin);           
	        assertEquals(size - 1, arbitreDAO.getAll().size());
	        assertEquals(Optional.empty(), arbitreDAO.getById(admin.getIdArbitre()));
	        connection.rollback();
	        
	    }catch(Exception e){
	        e.printStackTrace();
	    }

	}
}
