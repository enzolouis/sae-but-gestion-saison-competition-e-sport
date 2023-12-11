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

import classes.Joueur;

public class testJoueurDAO {

	String dirProjetJava = System.getProperty("user.dir");
	Connection connection;
	JoueurDAO Joueur;
	
	@Before
	public void beforeTests() {
		
		try {
			
			System.setProperty("derby.system.home", dirProjetJava + "/BDDSAEEsport");
			this.connection = DriverManager.getConnection("jdbc:derby:BDDSAEEsport;create=true");
			this.Joueur = new JoueurDAO();
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
			
			Optional<Joueur> joueur = Joueur.getById(-1);
		    assertEquals(Optional.empty(), joueur);
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
			
			Joueur joueur = new Joueur(1,"dorr",1);
			Joueur.add(joueur);
			Optional<Joueur> optional = Joueur.getById(joueur.getIdJoueur());
		    assertEquals(optional.get(), joueur);
		    connection.rollback();
		    
		} catch(Exception e) {
	        e.printStackTrace();
	    }
		
	}
	
	
	@Test
	//Test de la récupération des administrateur lorsqu'il y a des administrateurs
	public void testGetAllMatch() {
		    
		try {
			
			Joueur joueur1 = new Joueur(1,"dorr",1);
			Joueur joueur2 = new Joueur(1,"dorr",1);
			this.Joueur.add(joueur1);
			this.Joueur.add(joueur2);
			List<Joueur> listMatch = Joueur.getAll();
		    int index =  listMatch.size();
		    assertEquals(listMatch.get(index-2), joueur1);
		    assertEquals(listMatch.get(index-1), joueur2);
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

			Joueur joueur1 = new Joueur(1,"dorr",1);
	    	Joueur.add(joueur1);
	    	assertEquals(joueur1, Joueur.getById(joueur1.getIdJoueur()).get());
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
	    	
	    	Joueur joueur1 = new Joueur(1,"dorr",1);
	        Joueur.add(joueur1);
	        joueur1.setIDJoueur(3);
	        Joueur.update(joueur1);
	        assertEquals(joueur1, Joueur.getById(joueur1.getIdJoueur()).get());
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
			
			Joueur joueur1 = new Joueur(1,"dorr",1); 
	        Joueur.add(joueur1);
	        int size = Joueur.getAll().size();
	        Joueur.delete(joueur1);           
	        assertEquals(size -1, Joueur.getAll().size());
	        assertEquals(Optional.empty(), Joueur.getById(joueur1.getIdJoueur()));
	        connection.rollback();
	        
	    }catch(Exception e){
	        e.printStackTrace();
	    }

	}

}
