package tests;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DAOs.EquipeDAO;
import DAOs.TournoiDAO;
import classes.DBConnection;
import classes.Disposition;
import classes.Equipe;
import classes.EtatTournoi;
import classes.Match;
import classes.Nationalite;
import classes.Notoriete;
import modeles.TournoiModele;

public class TestTournoiDAO {
	
	@Before
	public void beforeTests()throws Exception {
			DBConnection.getInstance().setAutoCommit(false);
	}
	
	@After
	public void afterTests() throws Exception{
		DBConnection.getInstance().setAutoCommit(true);
	}
	
	@Test
	/// Olivier RODRIGUEZ
	/// Test de la récupération d'un Arbitre lorsqu'il n'y a pas cet arbitre
	public void testGetByIdTournoiNonExistant() throws Exception{
	    Optional<TournoiModele> tournoi = TournoiDAO.getInstance().getById(-1);
		assertEquals(Optional.empty(), tournoi);
		DBConnection.getInstance().rollback();
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
		TournoiDAO.getInstance().add(tournoi);
		Optional<TournoiModele> optional = TournoiDAO.getInstance().getById(tournoi.getIDTournoi());
		assertEquals(optional.get(), tournoi);
		DBConnection.getInstance().rollback();
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
			
			TournoiDAO.getInstance().add(tournoi1);
			TournoiDAO.getInstance().add(tournoi2);
			List<TournoiModele> listMatch = TournoiDAO.getInstance().getAll();
		    int index =  listMatch.size();
		    assertEquals(listMatch.get(index-2), tournoi1);
		    assertEquals(listMatch.get(index-1), tournoi2);
		    DBConnection.getInstance().rollback();

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
	    	
	    	TournoiDAO.getInstance().add(tournoi1);
	    	assertEquals(tournoi1, TournoiDAO.getInstance().getById(tournoi1.getIDTournoi()).get());
	    	DBConnection.getInstance().rollback();
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
	    	
	    	TournoiDAO.getInstance().add(tournoi1);
	    	tournoi1.setNomTournoi("prout");
	        TournoiDAO.getInstance().update(tournoi1);
	        assertEquals(tournoi1, TournoiDAO.getInstance().getById(tournoi1.getIDTournoi()).get());
	        DBConnection.getInstance().rollback();
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
	    	
	        TournoiDAO.getInstance().add(tournoi1);
	        int size = TournoiDAO.getInstance().getAll().size();
	        TournoiDAO.getInstance().delete(tournoi1);
	        assertEquals(size -1, TournoiDAO.getInstance().getAll().size());
	        assertEquals(Optional.empty(), TournoiDAO.getInstance().getById(tournoi1.getIDTournoi()));
	        DBConnection.getInstance().rollback();
	}
	
	@Test
	public void testRecupererMatch() throws Exception {
		TournoiModele tournoi = new TournoiModele(
				1,
				"Champers", 
				"25/12/1988", 
				"30/12/1988", 
				Notoriete.REGIONAL,
				EtatTournoi.FERME);
        Match match = new Match(0,1,false);
        Equipe e = new Equipe(0, "Maxence Maury-Balliteam", Nationalite.FR, Disposition.DISPOSEE, 3, 1000);EquipeDAO.getInstance().add(e);
		Equipe e2 = new Equipe(1, "Ibrateam Zoubairov", Nationalite.FR, Disposition.DISPOSEE, 1, 2300);EquipeDAO.getInstance().add(e2);
		match.addEquipe(e); match.addEquipe(e2);
        tournoi.ajouterMatch(match);
        TournoiDAO.getInstance().add(tournoi);
        TournoiModele tournoistocke = TournoiDAO.getInstance().getById(tournoi.getIDTournoi()).get();
        assertTrue(tournoistocke.getMatchs().contains(match));
        
	}

}
