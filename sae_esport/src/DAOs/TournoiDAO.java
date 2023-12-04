package DAOs;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import classes.DBConnection;
import classes.Equipe;
import classes.Tournoi;

public class TournoiDAO {
		
	//Renvois l'ensemble des arbitres
	public List<Tournoi> getAll() throws Exception {
		ArrayList<Tournoi> tournois = new ArrayList<>();
		String reqSelectTournoi = "SELECT * FROM tournoi";
		PreparedStatement st = DBConnection.getInstance().prepareStatement(reqSelectTournoi);
		ResultSet rs = st.executeQuery();
		String reqSelectParticipants = "SELECT idEquipe FROM Participer WHERE idTournoi = ?";
		PreparedStatement stParticipants = DBConnection.getInstance().prepareStatement(reqSelectParticipants);
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
		Statement st = DBConnection.getInstance().createStatement();
		for (Integer i : id) {
			ResultSet rs = st.executeQuery("SELECT * FROM tournoi WHERE idTournoi="+i);
			if (rs.next()) {
				Tournoi t = new Tournoi(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), classes.Notoriete.valueOf(rs.getString(5)), classes.EtatTournoi.valueOf(rs.getString(6)));
				PreparedStatement stParticipants = DBConnection.getInstance().prepareStatement("SELECT idEquipe FROM Participer WHERE idTournoi = ?");
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
		
		PreparedStatement st = DBConnection.getInstance().prepareStatement("SELECT NEXT VALUE FOR seqIdTournoi FROM admin");
		ResultSet rs = st.executeQuery();
		int id = 0;
		if (rs.next()) {
			id = rs.getInt(1);
		}
		value.setIDTournoi(id);
		st = DBConnection.getInstance().prepareStatement("INSERT INTO tournoi VALUES (?, ?, ?, ?, ?, ?, ?)");
		st.setInt(1, id); 
		st.setString(2, value.getNomTournoi());
		st.setDate(3, value.getDateDebut()); 
		st.setDate(4, value.getDateFin());
		st.setString(5, value.getNotoriete().toString()); 
		st.setObject(6, null);
		st.setString(7, value.getEtat_Tournoi().toString());
		int rowcount = st.executeUpdate();
		return rowcount > 0;
	}
	
	//update un arbitre donné
	public boolean update(Tournoi value) throws Exception {
		Statement st = DBConnection.getInstance().createStatement();
		int rowcount = st.executeUpdate("UPDATE tournoi SET ");
		return rowcount > 0;
	}
	
	//retire un arbitre donné
	public boolean delete(Tournoi value) throws Exception {
		Statement st = DBConnection.getInstance().createStatement();
		int rowcount = st.executeUpdate("DELETE FROM tournoi WHERE idTournoi=?");
		return rowcount > 0;
	}
	
	public Optional<Tournoi> getTournoiOuvert() throws Exception {
		Statement st = DBConnection.getInstance().createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM tournoi WHERE ouvert='OUVERT'");
		if (rs.next()) {
			return Optional.of(new Tournoi(rs.getInt(1), "", rs.getString(2), rs.getString(3), 
					classes.Notoriete.valueOf(rs.getString(3)), classes.EtatTournoi.valueOf(rs.getString(4))));
		}
		return Optional.empty();
	}

}
