package DAOs;

import static org.junit.Assert.assertEquals;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import classes.Administrateur;

public class testDAOAdministrateur {
	
	String dirProjetJava = System.getProperty("user.dir");
	Connection connection;
	AdministrateurDAO adminDAO;
	
	@Before
	public void beforeTests() throws Exception{
		
			System.setProperty("derby.system.home", dirProjetJava + "/BDDSAEEsport");
			this.connection = DriverManager.getConnection("jdbc:derby:BDDSAEEsport;create=true");
			this.adminDAO = new AdministrateurDAO();
			connection.setAutoCommit(false);

	}
	
	@After
	public void afterTests() throws Exception{
	
			connection.setAutoCommit(true);
			connection.close();
			
		
	}
	
	@Test
	/// Ryan GAUNAND
	/// Test de la récupération d'un administrateur lorsqu'il n'y a pas cet administrateur
	public void testGetByIdAdministrateurNonExistant()throws Exception {
			Optional<Administrateur> admin = adminDAO.getById(-1);
		    assertEquals(Optional.empty(), admin);
		    connection.rollback();

	}
	
	@Test
	/// Ryan GAUNAND
	/// Test de la récupération d'un administrateur
	public void testGetByIdAdministrateur()throws Exception {

			Administrateur admin = new Administrateur(0, "Chocola Meilleure", "chocola", "vieillerefdeshojo");
			adminDAO.add(admin);
			Optional<Administrateur> optional = adminDAO.getById(admin.getIdAdministrateur());
		    assertEquals(optional.get(), admin);
		    connection.rollback();

	}
	
	
	@Test
	//Test de la récupération des administrateur lorsqu'il y a des administrateurs
	public void testGetAllAdministrateur() throws Exception{
		
			Administrateur admin1 = new Administrateur(0, "Chocola Meilleure", "chocola", "vieillerefdeshojo");
			Administrateur admin2 = new Administrateur(0, "Roméo Wen", "juliette", "motdepasse");	
			adminDAO.add(admin1);
			adminDAO.add(admin2);
			List<Administrateur> listAdministrateur = adminDAO.getAll();
		    int index =  listAdministrateur.size();
		    assertEquals(listAdministrateur.get(index-2), admin1);
		    assertEquals(listAdministrateur.get(index-1), admin2);
		    connection.rollback();
	}
	
	@Test
	/// Ryan GAUNAND
	/// Test de l'ajout d'administrateur 
	public void testAddAdministrateur() throws Exception {
 
			Administrateur admin = new Administrateur(-1, "Ryan Add", "r.gaunand", "monMotDePasseSecurise"); 
	    	adminDAO.add(admin);
	    	assertEquals(admin, adminDAO.getById(admin.getIdAdministrateur()).get());
	    	connection.rollback();
   
	}
	
	@Test
	/// Ryan GAUNAND
	/// Test de la mise à jour d'un administrateur
	public void testUpdateAdministrateur() throws Exception {
			Administrateur admin = new Administrateur(-1, "Ryan Update", "r.gaunand", "monMotDePasseSecurise");
	        adminDAO.add(admin);
	        String newName = "Nayr";
	        admin.setNom(newName);
	        adminDAO.update(admin);
	        assertEquals(admin, adminDAO.getById(admin.getIdAdministrateur()).get());
	        connection.rollback();

	}
	
	@Test
	/// Ryan GAUNAND
	/// Test de supression d'un administrateur
	public void testDeleteAdministrateur() throws Exception { 
			Administrateur admin = new Administrateur(-1, "Ryan Delete", "r.gaunand", "monMotDePasseSecurise"); 
	        adminDAO.add(admin);
	        int size = adminDAO.getAll().size();
	        adminDAO.delete(admin);           
	        assertEquals(size -1, adminDAO.getAll().size());
	        assertEquals(Optional.empty(), adminDAO.getById(admin.getIdAdministrateur()));
	        connection.rollback();

	}
}
