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
		    Assert.assertEquals(3, listAdministrateur.size());
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
		Administrateur admin = new Administrateur(10, "Ryan", "r.gaunand", "monMotDePasseSecurise");  
		
	    try{
	    	connection.setAutoCommit(false);
	    	
	    	adminDAO.add(admin);
	    	
	        Assert.assertNotNull(adminDAO);
	        Assert.assertNotNull(adminDAO.getById(10).get()); 
	        Assert.assertEquals(2, adminDAO.getAll().size()); 
	 
	        Administrateur admin1 = adminDAO.getById(admin.getIdAdministrateur()).get();
	        Assert.assertEquals(admin1.getIdAdministrateur(), admin.getIdAdministrateur());
	        Assert.assertEquals(admin1.getNom(), admin.getNom());
	        Assert.assertEquals(admin1.getLogin(), admin.getLogin());
	        Assert.assertEquals(admin1.getMotDePasse(), admin.getMotDePasse());
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
	/// Test de la mise à jour d'un administrateur en éditant l'entièreté des données de celui-ci (Id, Nom, Login et MDP)
	public void testUpdateAdministrateur() throws SQLException {
	    System.setProperty("derby.system.home", dirProjetJava + "/BDDSAEEsport");
		DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
		Connection connection = DriverManager.getConnection("jdbc:derby:BDDSAEEsport;create=true");
		AdministrateurDAO adminDAO = new AdministrateurDAO(connection);
		Administrateur admin = new Administrateur(11, "Ryan", "r.gaunand", "monMotDePasseSecurise"); 
		
	    try{
	        connection.setAutoCommit(false);
	        
	        adminDAO.add(admin);
	 
	        Integer newId = 12;
	        String newName = "Nayr";
	        String newLogin = "nayr.gaunand";
	        String newPassword = "monNouveauMotDePasseSecurise";
	        
	        admin.setIdAdministrateur(newId);
	        admin.setNom(newName);
	        admin.setLogin(newLogin );
	        admin.setMotDePasse(newPassword);
	        adminDAO.update(admin);
	 
	        Administrateur admin1 = adminDAO.getById(admin.getIdAdministrateur()).get();
	        Assert.assertEquals((Integer) newId, (Integer) admin1.getIdAdministrateur());
	        Assert.assertEquals(newName, admin1.getNom());
	        Assert.assertEquals(newLogin, admin1.getLogin());
	        Assert.assertEquals(newPassword, admin1.getMotDePasse());
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
	/// Test de supression d'un administrateur
	public void testDeleteAdministrateur() throws SQLException {
	    System.setProperty("derby.system.home", dirProjetJava + "/BDDSAEEsport");
		DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
		Connection connection = DriverManager.getConnection("jdbc:derby:BDDSAEEsport;create=true");
		AdministrateurDAO adminDAO = new AdministrateurDAO(connection);
		Administrateur admin = new Administrateur(2, "Ryan", "r.gaunand", "monMotDePasseSecurise"); 
		
		try{
	        connection.setAutoCommit(false);
	        
	        boolean rowChanged = adminDAO.add(admin);
	        List<Administrateur> listAdministrateur = adminDAO.getAll();
	        Assert.assertEquals(4, listAdministrateur.size());
	        
	        adminDAO.delete(admin);           
	        Assert.assertEquals(3, adminDAO.getAll().size());
	        
	        Administrateur admin1 = adminDAO.getById(admin.getIdAdministrateur()).get();
	        Assert.assertNull(admin1);
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
