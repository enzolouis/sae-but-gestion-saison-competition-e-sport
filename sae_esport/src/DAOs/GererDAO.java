package DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import classes.DBConnection;
import classes.Gerer;

public class GererDAO {
	
private static GererDAO instance;
	
	private GererDAO() {
		
	}
	
	public static synchronized GererDAO getInstance() {
		if (instance == null) {
			instance = new GererDAO();
		}
		return instance;
	}
	
	//Renvois l'ensemble des arbitres
	public List<Gerer> getAll() throws Exception {
		String reqSelectParticipation = "SELECT * FROM gerer";
		PreparedStatement st = DBConnection.getInstance().prepareStatement(reqSelectParticipation);
		ResultSet rs = st.executeQuery();
		ArrayList<Gerer> participations = new ArrayList<Gerer>();
		while (rs.next()) {
			participations.add(new Gerer(rs.getInt(1),rs.getInt(2)));
		}
		return participations;
	}
	
	//ajoute un arbitre à la liste
	//peu importe l'id que vous mettrez à l'arbitre, il sera changé
	public boolean add(Gerer value) throws Exception {

		PreparedStatement st = DBConnection.getInstance().prepareStatement("SELECT NEXT VALUE FOR idTournoi FROM gerer");
		ResultSet rs = st.executeQuery();
		int id = 0;
		if (rs.next()) {
			id = rs.getInt(1);
		}
		value.setIdTournoi(id);
		st = DBConnection.getInstance().prepareStatement("INSERT INTO participer VALUES (?,?)");
		st.setInt(1, id); 
		st.setInt(2, value.getIdarbitre());
		
		int rowcount = st.executeUpdate();
		return rowcount > 0;
		
	}
	
	//update un arbitre donné
	public boolean update(Gerer value) throws Exception {
		
		PreparedStatement st = DBConnection.getInstance().prepareStatement("UPDATE gerer SET idEquipe=? WHERE idTournoi=?");
		 
		st.setInt(1, value.getIdarbitre()); 
		st.setInt(2, value.getIdTournoi());
		
		int rowcount = st.executeUpdate();
		return rowcount > 0;
		
	}
	
	//retire un arbitre donné
	public boolean delete(Gerer value) throws Exception {
		
		PreparedStatement st = DBConnection.getInstance().prepareStatement("DELETE FROM gerer WHERE idTournoi=?");
		st.setInt(1, value.getIdTournoi());
		int rowcount = st.executeUpdate();
		return rowcount > 0;
	}
}
