package tests;

import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DAOs.AdministrateurDAO;
import classes.Administrateur;
import classes.DBConnection;

public class TestAdminDAO {
	
	@Before
	public void beforeTests() throws Exception{
		
			DBConnection.getInstance().setAutoCommit(false);

	}
	
	@After
	public void afterTests() throws Exception{
	
			DBConnection.getInstance().setAutoCommit(true);		
	
	}
	
	@Test
	/// Ryan GAUNAND
	/// Test de la récupération d'un administrateur lorsqu'il n'y a pas cet administrateur
	public void testGetByIdAdministrateurNonExistant()throws Exception {
		
			Optional<Administrateur> admin = AdministrateurDAO.getInstance().getById(-1);
		    assertEquals(Optional.empty(), admin);
		   	DBConnection.getInstance().rollback();

	}
	
	@Test
	/// Ryan GAUNAND
	/// Test de la récupération d'un administrateur
	public void testGetByIdAdministrateur()throws Exception {

			Administrateur admin = new Administrateur(0, "Chocola Meilleure", "chocola", "vieillerefdeshojo");
			AdministrateurDAO.getInstance().add(admin);
			Optional<Administrateur> optional = AdministrateurDAO.getInstance().getById(admin.getIdAdministrateur());
		    assertEquals(optional.get(), admin);
		    DBConnection.getInstance().rollback();

	}
	
	
	@Test
	//Test de la récupération des administrateur lorsqu'il y a des administrateurs
	public void testGetAllAdministrateur() throws Exception{
		
			Administrateur admin1 = new Administrateur(0, "Chocola Meilleure", "chocola", "vieillerefdeshojo");
			Administrateur admin2 = new Administrateur(0, "Roméo Wen", "juliette", "motdepasse");	
			AdministrateurDAO.getInstance().add(admin1);
			AdministrateurDAO.getInstance().add(admin2);
			List<Administrateur> listAdministrateur = AdministrateurDAO.getInstance().getAll();
		    int index =  listAdministrateur.size();
		    assertEquals(listAdministrateur.get(index-2), admin1);
		    assertEquals(listAdministrateur.get(index-1), admin2);
		    DBConnection.getInstance().rollback();
		    
	}
	
	@Test
	/// Ryan GAUNAND
	/// Test de l'ajout d'administrateur 
	public void testAddAdministrateur() throws Exception {
 
			Administrateur admin = new Administrateur(-1, "Ryan Add", "r.gaunand", "monMotDePasseSecurise"); 
	    	AdministrateurDAO.getInstance().add(admin);
	    	assertEquals(admin, AdministrateurDAO.getInstance().getById(admin.getIdAdministrateur()).get());
	    	DBConnection.getInstance().rollback();
   
	}
	
	@Test
	/// Ryan GAUNAND
	/// Test de la mise à jour d'un administrateur
	public void testUpdateAdministrateur() throws Exception {
			Administrateur admin = new Administrateur(-1, "Ryan Update", "r.gaunand", "monMotDePasseSecurise");
	        AdministrateurDAO.getInstance().add(admin);
	        String newName = "Nayr";
	        admin.setNom(newName);
	        AdministrateurDAO.getInstance().update(admin);
	        assertEquals(admin, AdministrateurDAO.getInstance().getById(admin.getIdAdministrateur()).get());
	        DBConnection.getInstance().rollback();

	}
	
	@Test
	/// Ryan GAUNAND
	/// Test de supression d'un administrateur
	public void testDeleteAdministrateur() throws Exception { 
			Administrateur admin = new Administrateur(-1, "Ryan Delete", "r.gaunand", "monMotDePasseSecurise"); 
	        AdministrateurDAO.getInstance().add(admin);
	        int size = AdministrateurDAO.getInstance().getAll().size();
	        AdministrateurDAO.getInstance().delete(admin);           
	        assertEquals(size -1, AdministrateurDAO.getInstance().getAll().size());
	        assertEquals(Optional.empty(), AdministrateurDAO.getInstance().getById(admin.getIdAdministrateur()));
	        DBConnection.getInstance().rollback();

	}
}
