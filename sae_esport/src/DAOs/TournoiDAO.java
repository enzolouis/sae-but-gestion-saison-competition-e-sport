package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import classes.Equipe;
import classes.Tournoi;

public class TournoiDAO {
	
private Connection dbConnection;
	
	//Constructeur DAO, et mise en place de la connexion
	public TournoiDAO(Connection c) {
		this.dbConnection = c;
	}
	
	//Renvois l'ensemble des arbitres
	public List<Tournoi> getAll() throws Exception {
		ArrayList<Tournoi> tournois = new ArrayList<>();
		String reqSelectTournoi = "SELECT * FROM tournoi";
		PreparedStatement st = this.dbConnection.prepareStatement(reqSelectTournoi);
		ResultSet rs = st.executeQuery();
		String reqSelectParticipants = "SELECT idEquipe FROM Participer WHERE idTournoi = ?";
		PreparedStatement stParticipants = this.dbConnection.prepareStatement(reqSelectParticipants);
		ArrayList<Equipe> participants = new ArrayList<>();
		while (rs.next()) {
			stParticipants.setInt(1, rs.getInt(1));
			ResultSet rsParticipants = stParticipants.executeQuery();
			while (rsParticipants.next()) {
				participants.add(null);
			}
			tournois.add(null);
			participants.clear();
		}
		return tournois;
	}
	
	//retourne un Arbitre specifique
	public Optional<Tournoi> getById(Integer... id) throws Exception {
		Statement st = this.dbConnection.createStatement();
		for (Integer i : id) {
			ResultSet rs = st.executeQuery("SELECT * FROM tournoi WHERE idTournoi="+i);
			if (rs.next()) {
				return Optional.of(null);
			}
		}
		return Optional.empty();
	}
	
	//ajoute un arbitre à la liste
	public boolean add(Tournoi value) throws Exception {
		Statement st = this.dbConnection.createStatement();
		int rowcount = st.executeUpdate("INSERT INTO tournoi VALUES ()");
		return rowcount > 0;
	}
	
	//update un arbitre donné
	public boolean update(Tournoi value) throws Exception {
		Statement st = this.dbConnection.createStatement();
		int rowcount = st.executeUpdate("UPDATE tournoi SET ");
		return rowcount > 0;
	}
	
	//retire un arbitre donné
	public boolean delete(Tournoi value) throws Exception {
		Statement st = this.dbConnection.createStatement();
		int rowcount = st.executeUpdate("DELETE FROM tournoi WHERE idTournoi=");
		return rowcount > 0;
	}
	
	public Optional<Tournoi> getTournoiOuvert() throws Exception {
		Statement st = this.dbConnection.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM tournoi WHERE ouvert=OUVERT");
		if (rs.next()) {
			return Optional.of(null);
		}
		return Optional.empty();
	}

}
