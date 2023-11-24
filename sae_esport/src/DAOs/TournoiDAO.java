package DAOs;

import java.sql.Date;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
			Tournoi t = new Tournoi(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), classes.Notoriete.valueOf(rs.getString(5)), classes.EtatTournoi.valueOf(rs.getString(6)));
			stParticipants.setInt(1, rs.getInt(1));
			ResultSet rsParticipants = stParticipants.executeQuery();
			while (rsParticipants.next()) {
				t.ajouterEquipe(null);
			}
			tournois.add(t);
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
				Tournoi t = new Tournoi(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), classes.Notoriete.valueOf(rs.getString(5)), classes.EtatTournoi.valueOf(rs.getString(6)));
				PreparedStatement stParticipants = this.dbConnection.prepareStatement("SELECT idEquipe FROM Participer WHERE idTournoi = ?");
				stParticipants.setInt(1, rs.getInt(1));
				ResultSet rsParticipants = stParticipants.executeQuery();
				while (rsParticipants.next()) {
					t.ajouterEquipe(null);
				}
				return Optional.of(t);
			}
		}
		return Optional.empty();
	}
	
	//ajoute un arbitre à la liste
	public boolean add(Tournoi value) throws Exception {
		
		PreparedStatement st = this.dbConnection.prepareStatement("SELECT NEXT VALUE FOR seqIdTournoi FROM admin");
		ResultSet rs = st.executeQuery();
		int id = 0;
		if (rs.next()) {
			id = rs.getInt(1);
		}
		value.setIDTournoi(id);
		st = this.dbConnection.prepareStatement("INSERT INTO tournoi VALUES (?, ?, ?, ?, ?, ?, ?)");
		st.setInt(1, id); st.setDate(2, new Date(Timestamp.valueOf(value.getDateDebut()).getTime())); st.setString(3, value.getDateFin().toString());
		st.setString(4, value.getNotoriete().toString()); st.setInt(5, value.getVainqueur().get().getIdEquipe());
		st.setString(6, value.getEtat_Tournoi().toString());
		st.setString(3, value.getLogin()); st.setString(4, value.getMotDePasse());
		int rowcount = st.executeUpdate();
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
		int rowcount = st.executeUpdate("DELETE FROM tournoi WHERE idTournoi=?");
		return rowcount > 0;
	}
	
	public Optional<Tournoi> getTournoiOuvert() throws Exception {
		Statement st = this.dbConnection.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM tournoi WHERE ouvert=OUVERT");
		if (rs.next()) {
			return Optional.of(new Tournoi(rs.getInt(1), "", rs.getString(2), rs.getString(3), 
					classes.Notoriete.valueOf(rs.getString(3)), classes.EtatTournoi.valueOf(rs.getString(4))));
		}
		return Optional.empty();
	}

}
