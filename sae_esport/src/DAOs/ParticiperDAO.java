package DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import classes.DBConnection;
import classes.Participer;

public class ParticiperDAO {
	
	private static ParticiperDAO instance;
	
	public ParticiperDAO() {
		super();
	}
	
	/**
	 * Mise en place d'une instance de participation, pour intéragir avec la bdd correspondante
	 * */
	public static synchronized ParticiperDAO getInstance() {
		if (instance == null) {
			instance = new ParticiperDAO();
		}
		return instance;
	}
	
	/**
	 * récupère la participation d'une équipe à un tournoi
	 * @param id de l'équipe
	 * @param id du tournoi
	 * */
	public Optional<Participer> getByIdTournoiIdEquipe (int idEquipe, int idTournoi) throws Exception {
		
		PreparedStatement st = DBConnection.getInstance().prepareStatement
				("SELECT * FROM participer WHERE idTournoi=? AND idEquipe=?");
		st.setInt(1, idTournoi); st.setInt(2, idEquipe);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			return Optional.of(new Participer(rs.getInt(1), idTournoi, idEquipe));
		}
		return Optional.empty();
		
	}
	
	/**
	 * renvoie la liste des participants du tournoi du premier id reconnu
	 * @param identifiant(s) du tournoi
	 * */
	public List<Participer> getByIdTournoi(Integer... id) throws Exception {
		ArrayList<Participer> participations = new ArrayList<>();
		PreparedStatement st = DBConnection.getInstance()
				.prepareStatement("SELECT * FROM participer WHERE idTournoi=?");
		for (Integer i : id) {
			st.setInt(1, i);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {					//	
				participations.add(new Participer(rs.getInt(1),rs.getInt(2),rs.getInt(3)));
			}
		}
		return participations;
	}
			
	/**
	 * renvoie la liste des participations de l'équipe du premier id reconnu
	 * @param identifiant(s) de l'équipe
	 * */
	public List<Participer> getByIdEquipe(Integer... id) throws Exception {
		ArrayList<Participer> participations = new ArrayList<>();
		PreparedStatement st = DBConnection.getInstance()
				.prepareStatement("SELECT * FROM participer WHERE idEquipe=?");
		for (Integer i : id) {
			st.setInt(1, i);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {					//	
				participations.add(new Participer(rs.getInt(1),rs.getInt(2),rs.getInt(3)));
			}
		}
		return participations;
	}
	
	/**
	 * renvoie toutes les participations
	 * */
	public List<Participer> getAll() throws Exception {
		String reqSelectParticipation = "SELECT * FROM participer";
		PreparedStatement st = DBConnection.getInstance().prepareStatement(reqSelectParticipation);
		ResultSet rs = st.executeQuery();
		ArrayList<Participer> participations = new ArrayList<Participer>();
		while (rs.next()) {
			participations.add(new Participer(rs.getInt(1),rs.getInt(2),rs.getInt(3)));
		}
		return participations;
	}
	
	/**
	 * ajoute une participation à la bdd
	 * @param participation à ajouter
	 * */
	public boolean add(Participer value) throws Exception {

		PreparedStatement st = DBConnection.getInstance().prepareStatement("INSERT INTO participer VALUES (?,?,?)");
		st.setInt(2, value.getIdTournoi()); 
		st.setInt(3, value.getIdEquipe()); 
		st.setInt(1, value.getResultat());
		int rowcount = st.executeUpdate();
		return rowcount > 0;
		
	}
	
	/**
	 * met à jour une participation
	 * @param participation à update
	 * */
	public boolean update(Participer value) throws Exception {
		
		PreparedStatement st = DBConnection.getInstance().prepareStatement("UPDATE participer SET resultat=? WHERE idTournoi=? AND idEquipe=?");
		 
		st.setInt(1, value.getResultat()); 
		st.setInt(2, value.getIdTournoi());
		st.setInt(3, value.getIdEquipe());
		
		int rowcount = st.executeUpdate();
		return rowcount > 0;
	}
	
	/**
	 * supprime une participation dans la bdd
	 * @param participation à supprimer
	 * */
	public boolean delete(Participer value) throws Exception {
		
		PreparedStatement st = DBConnection.getInstance().prepareStatement("DELETE FROM participer WHERE idEquipe=? AND idTournoi=?");
		st.setInt(1, value.getIdEquipe());
		st.setInt(2, value.getIdTournoi());
		int rowcount = st.executeUpdate();
		return rowcount > 0;
	}
}
