package DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import classes.Arbitre;
import classes.Nationalite;

public class testDAOArbitre {
    String dirProjetJava = System.getProperty("user.dir");

	@Test
	/// Ryan GAUNAND
	/// Test de la récupération complète des arbitres
	public void testGetAllArbitre() throws SQLException {
	    System.setProperty("derby.system.home", dirProjetJava + "/BDDSAEEsport");
		DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
		Connection connection = DriverManager.getConnection("jdbc:derby:BDDSAEEsport;create=true");
		ArbitreDAO arbitreDAO = new ArbitreDAO(connection);
		
		try {
			connection.setAutoCommit(false);
			
			List<Arbitre> listArbitre = arbitreDAO.getAll();
		    Assert.assertNotNull(listArbitre);
		    Assert.assertEquals(0, listArbitre.size());
		}catch(Exception e){
	        e.printStackTrace();
	    }finally{
	        try{
	            connection.rollback();
	            connection.setAutoCommit(true);
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	    }

	    return;
	}
	
	@Test
	/// Ryan GAUNAND
	/// Test de l'ajout d'arbitre 
	public void testAddArbitre() throws SQLException {
	    System.setProperty("derby.system.home", dirProjetJava + "/BDDSAEEsport");
		DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
		Connection connection = DriverManager.getConnection("jdbc:derby:BDDSAEEsport;create=true");
		ArbitreDAO arbitreDAO = new ArbitreDAO(connection);
		Arbitre arbitre = new Arbitre(-1, "Ryan Arbitre", "Add", Nationalite.FR);  
		
	    try{
	    	connection.setAutoCommit(false);
	    	
	    	arbitreDAO.add(arbitre);
	    	
	        Assert.assertNotNull(arbitreDAO);
	        Assert.assertNotNull(arbitreDAO.getById(arbitre.getIdArbitre()));
	        Assert.assertEquals(1, arbitreDAO.getAll().size()); 
	    }catch(Exception e){
	        e.printStackTrace();
	    }finally{
	        try{
	        	connection.rollback();
	        	connection.setAutoCommit(true);
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	    }
	    
		return;
	}
	
	@Test
	/// Ryan GAUNAND
	/// Test de la mise à jour d'un arbitre en éditant l'entièreté des données de celui-ci (Nom, Prenom et Nationalité)
	public void testUpdateArbitre() throws SQLException {
	    System.setProperty("derby.system.home", dirProjetJava + "/BDDSAEEsport");
		DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
		Connection connection = DriverManager.getConnection("jdbc:derby:BDDSAEEsport;create=true");
		ArbitreDAO arbitreDAO = new ArbitreDAO(connection);
		Arbitre arbitre = new Arbitre(-1, "Ryan Arbitre", "Update", Nationalite.FR); 
		
	    try{
	        arbitreDAO.add(arbitre);
	        
	        String newName = "Update 2";
	        String newFirstname = "Nayr Arbitre";
	        Nationalite newNationality = Nationalite.ES;
	        
	        arbitre.setNom(newName);
	        arbitre.setPrenom(newFirstname);
	        arbitre.setNationalite(newNationality);
	        arbitreDAO.update(arbitre);
	 
	        Assert.assertEquals(newName, arbitre.getNom());
	        Assert.assertEquals(newFirstname, arbitre.getPrenom());
	        Assert.assertEquals(newNationality, arbitre.getNationalite());
	    }catch(Exception e){
	        e.printStackTrace();
	    }finally{
	        try{
		        arbitreDAO.delete(arbitre);
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	    }
	    
	    return;
	}
	
	@Test
	/// Ryan GAUNAND
	/// Test de supression d'un arbitre
	public void testDeleteArbitre() throws SQLException {
	    System.setProperty("derby.system.home", dirProjetJava + "/BDDSAEEsport");
		DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
		Connection connection = DriverManager.getConnection("jdbc:derby:BDDSAEEsport;create=true");
		ArbitreDAO arbitreDAO = new ArbitreDAO(connection);
		Arbitre arbitre = new Arbitre(-1, "Ryan Arbitre", "Delete", Nationalite.FR); 
		
		try{
	        connection.setAutoCommit(false);
	        
	        arbitreDAO.add(arbitre);
	        Assert.assertEquals(1, arbitreDAO.getAll().size());
	        
	        arbitreDAO.delete(arbitre);           
	        Assert.assertEquals(0, arbitreDAO.getAll().size());
	    }catch(Exception e){
	        e.printStackTrace();
	    }finally{
	        try{
	            connection.rollback();
	            connection.setAutoCommit(true);
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	    }
		
		return;
	}
}
