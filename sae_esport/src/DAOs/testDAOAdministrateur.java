package DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

import classes.Administrateur;

public class testDAOAdministrateur {
    String dirProjetJava = System.getProperty("user.dir");

	@Test
	/// Ryan GAUNAND
	/// Test de la récupération complète des administrateurs
	public void testGetAllAdministrateur() throws SQLException {
	    System.setProperty("derby.system.home", dirProjetJava + "/BDDSAEEsport");
		DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
		Connection connection = DriverManager.getConnection("jdbc:derby:BDDSAEEsport;create=true");
		AdministrateurDAO adminDAO = new AdministrateurDAO(connection);
		
		try {
			connection.setAutoCommit(false);
			
			List<Administrateur> listAdministrateur = adminDAO.getAll();
		    Assert.assertNotNull(listAdministrateur);
		    Assert.assertEquals(0, listAdministrateur.size());
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
	/// Test de l'ajout d'administrateur 
	public void testAddAdministrateur() throws SQLException {
	    System.setProperty("derby.system.home", dirProjetJava + "/BDDSAEEsport");
		DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
		Connection connection = DriverManager.getConnection("jdbc:derby:BDDSAEEsport;create=true");
		AdministrateurDAO adminDAO = new AdministrateurDAO(connection);
		Administrateur admin = new Administrateur(-1, "Ryan Add", "r.gaunand", "monMotDePasseSecurise");  
		
	    try{
	    	connection.setAutoCommit(false);
	    	
	    	adminDAO.add(admin);
	    	
	        Assert.assertNotNull(adminDAO);
	        Assert.assertNotNull(adminDAO.getById(admin.getIdAdministrateur()));
	        Assert.assertEquals(1, adminDAO.getAll().size()); 
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
	/// Test de la mise à jour d'un administrateur en éditant l'entièreté des données de celui-ci (Nom, Login et MDP)
	public void testUpdateAdministrateur() throws SQLException {
	    System.setProperty("derby.system.home", dirProjetJava + "/BDDSAEEsport");
		DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
		Connection connection = DriverManager.getConnection("jdbc:derby:BDDSAEEsport;create=true");
		AdministrateurDAO adminDAO = new AdministrateurDAO(connection);
		Administrateur admin = new Administrateur(-1, "Ryan Update", "r.gaunand", "monMotDePasseSecurise"); 
		
	    try{
	        adminDAO.add(admin);
	        
	        String newName = "Nayr";
	        String newLogin = "nayr.gaunand";
	        String newPassword = "monNouveauMotDePasseSecurise";
	        
	        admin.setNom(newName);
	        admin.setLogin(newLogin);
	        admin.setMotDePasse(newPassword);
	        adminDAO.update(admin);
	 
	        Assert.assertEquals(newName, admin.getNom());
	        Assert.assertEquals(newLogin, admin.getLogin());
	        Assert.assertEquals(newPassword, admin.getMotDePasse());
	    }catch(Exception e){
	        e.printStackTrace();
	    }finally{
	        try{
		        adminDAO.delete(admin);
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	    }
	    
	    return;
	}
	
	@Test
	/// Ryan GAUNAND
	/// Test de supression d'un administrateur
	public void testDeleteAdministrateur() throws SQLException {
	    System.setProperty("derby.system.home", dirProjetJava + "/BDDSAEEsport");
		DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
		Connection connection = DriverManager.getConnection("jdbc:derby:BDDSAEEsport;create=true");
		AdministrateurDAO adminDAO = new AdministrateurDAO(connection);
		Administrateur admin = new Administrateur(-1, "Ryan Delete", "r.gaunand", "monMotDePasseSecurise"); 
		
		try{
	        connection.setAutoCommit(false);
	        
	        adminDAO.add(admin);
	        Assert.assertEquals(1, adminDAO.getAll().size());
	        
	        adminDAO.delete(admin);           
	        Assert.assertEquals(0, adminDAO.getAll().size());
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
