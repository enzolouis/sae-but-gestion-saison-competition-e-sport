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
import classes.Joueur;
import classes.Notoriete;
import modeles.TournoiModele;

public class testTournoiDAO {
	String dirProjetJava = System.getProperty("user.dir");

	Connection connection;
	TournoiDAO tournoi;
	
	@Before
	public void beforeTests()throws Exception {
		System.setProperty("derby.system.home", dirProjetJava + "/BDDSAEEsport");
			this.connection = DriverManager.getConnection("jdbc:derby:BDDSAEEsport;create=true");
			this.tournoi = new TournoiDAO();
			connection.setAutoCommit(false);
	}
	
	@After
	public void afterTests() throws Exception{
		connection.setAutoCommit(true);
			connection.close();
	}
	
	@Test
	/// Olivier RODRIGUEZ
	/// Test de la récupération d'un Arbitre lorsqu'il n'y a pas cet arbitre
	public void testGetByIdTournoiNonExistant() throws Exception{
	    Optional<TournoiModele> tournoi = this.tournoi.getById(-1);
		    assertEquals(Optional.empty(), tournoi);
		    connection.rollback();
	}
	
	
	@Test
	/// Olivier RODRIGUEZ
	/// Test de la récupération d'un administrateur
	public void testGetByIdTournoi() throws Exception{
	    TournoiModele tournoi = new TournoiModele(
					1,
					"Champers", 
					"25/12/1988", 
					"30/12/1988", 
					Notoriete.REGIONAL,
					EtatTournoi.FERME);
			this.tournoi.add(tournoi);
			Optional<TournoiModele> optional = this.tournoi.getById(tournoi.getIDTournoi());
		    assertEquals(optional.get(), tournoi);
		    connection.rollback();
	}
	
	
	@Test
	//Test de la récupération des administrateur lorsqu'il y a des administrateurs
	public void testGetAllTournoi() throws Exception{
		    TournoiModele tournoi1 = new TournoiModele(
					1,
					"Champers", 
					"25/12/1988", 
					"30/12/1988", 
					Notoriete.REGIONAL,
					EtatTournoi.FERME);
			TournoiModele tournoi2 = new TournoiModele(
					1,
					"Champers", 
					"25/12/1988", 
					"30/12/1988", 
					Notoriete.REGIONAL,
					EtatTournoi.FERME);
			
			this.tournoi.add(tournoi1);
			this.tournoi.add(tournoi2);
			List<TournoiModele> listMatch = tournoi.getAll();
		    int index =  listMatch.size();
		    assertEquals(listMatch.get(index-2), tournoi1);
		    assertEquals(listMatch.get(index-1), tournoi2);
		    connection.rollback();
	}
	
	@Test
	/// Olivier RODRIGUEZ
	/// Test de l'ajout d'administrateur 
	public void testAddTournoi() throws Exception {
		TournoiModele tournoi1 = new TournoiModele(
					1,
					"Champers", 
					"25/12/1988", 
					"30/12/1988", 
					Notoriete.REGIONAL,
					EtatTournoi.FERME);
	    	
	    	this.tournoi.add(tournoi1);
	    	assertEquals(tournoi1, tournoi.getById(tournoi1.getIDTournoi()).get());
	    	connection.rollback();
	}
	
	@Test
	/// Olivier RODRIGUEZ
	/// Test de la mise à jour d'un administrateur
	public void testUpdateTournoi() throws Exception {
		TournoiModele tournoi1 = new TournoiModele(
					1,
					"Champers", 
					"25/12/1988", 
					"30/12/1988", 
					Notoriete.REGIONAL,
					EtatTournoi.FERME);	
	    	
	    	tournoi.add(tournoi1);
	    	tournoi1.setIDTournoi(3);
	        tournoi.update(tournoi1);
	        assertEquals(tournoi1, tournoi.getById(tournoi1.getIDTournoi()).get());
	        connection.rollback();
	}
	
	@Test
	/// Olivier RODRIGUEZ
	/// Test de supression d'un administrateur
	public void testDeleteTournoi() throws Exception { 
		TournoiModele tournoi1 = new TournoiModele(
					1,
					"Champers", 
					"25/12/1988", 
					"30/12/1988", 
					Notoriete.REGIONAL,
					EtatTournoi.FERME);
	    	
	        tournoi.add(tournoi1);
	        int size = tournoi.getAll().size();
	        tournoi.delete(tournoi1);           
	        assertEquals(size -1, tournoi.getAll().size());
	        assertEquals(Optional.empty(), tournoi.getById(tournoi1.getIDTournoi()));
	        connection.rollback();
	}

}
