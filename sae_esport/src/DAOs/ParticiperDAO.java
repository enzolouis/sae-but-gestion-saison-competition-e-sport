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
	
	public static synchronized ParticiperDAO getInstance() {
		if (instance == null) {
			instance = new ParticiperDAO();
		}
		return instance;
	}
	
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
	
	//retourne la liste des participations d'un tournoi
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
			
	//retourne la liste des participations d'une équipe
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
	
	//renvoie l'ensemble des participations
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
	
	//ajoute un arbitre à la liste
	//peu importe l'id que vous mettrez à l'arbitre, il sera changé
	public boolean add(Participer value) throws Exception {

		PreparedStatement st = DBConnection.getInstance().prepareStatement("INSERT INTO participer VALUES (?,?,?)");
		st.setInt(2, value.getIdTournoi()); 
		st.setInt(3, value.getIdEquipe()); 
		st.setInt(1, value.getResultat());
		
		int rowcount = st.executeUpdate();
		return rowcount > 0;
		
	}
	
	//update un arbitre donné
	public boolean update(Participer value) throws Exception {
		
		PreparedStatement st = DBConnection.getInstance().prepareStatement("UPDATE participer SET resultat=? WHERE idTournoi=? AND idEquipe=?");
		 
		st.setInt(1, value.getResultat()); 
		st.setInt(2, value.getIdTournoi());
		st.setInt(3, value.getIdEquipe());
		
		int rowcount = st.executeUpdate();
		return rowcount > 0;
	}
	
	//retire un arbitre donné
	public boolean delete(Participer value) throws Exception {
		
		PreparedStatement st = DBConnection.getInstance().prepareStatement("DELETE FROM participer WHERE idEquipe=? AND idTournoi=?");
		st.setInt(1, value.getIdEquipe());
		st.setInt(2, value.getIdTournoi());
		int rowcount = st.executeUpdate();
		return rowcount > 0;
	}
}
