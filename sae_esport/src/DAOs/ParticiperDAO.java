package DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import classes.DBConnection;
import classes.Match;
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
	
	//retourne une participation en fonction des tournois, et les resultats correspondant specifique
			public Optional<Participer> getByIdTournoi(Integer... id) throws Exception {
				Statement st = DBConnection.getInstance().createStatement();
				for (Integer i : id) {
					ResultSet rs = st.executeQuery("SELECT p.idEquipe, p.idTournoi, p.resultat FROM participer p WHERE idTournoi="+i);
					if (rs.next()) {					//	
						return Optional.of(new Participer(rs.getInt(1),rs.getInt(2),rs.getInt(3)));
					}
				}
				return Optional.empty();
			}
			
			//retourne une participation en fonction des equipes, et les resultats correspondant specifique
			public Optional<Participer> getByIdEquipe(Integer... id) throws Exception {
				Statement st = DBConnection.getInstance().createStatement();
				for (Integer i : id) {
					ResultSet rs = st.executeQuery("SELECT p.idEquipe, p.idTournoi, p.resultat FROM participer p WHERE idEquipe="+i);
					if (rs.next()) {					//	
						return Optional.of(new Participer(rs.getInt(1),rs.getInt(2),rs.getInt(3)));
					}
				}
				return Optional.empty();
			}
	
	//Renvois l'ensemble des arbitres
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

		PreparedStatement st = DBConnection.getInstance().prepareStatement("SELECT NEXT VALUE FOR idTournoi FROM participer");
		ResultSet rs = st.executeQuery();
		int id = 0;
		if (rs.next()) {
			id = rs.getInt(1);
		}
		value.setIdEquipe(id);
		st = DBConnection.getInstance().prepareStatement("INSERT INTO participer VALUES (?,?,?)");
		st.setInt(1, id); 
		st.setInt(2, value.getIdTournoi()); 
		st.setInt(3, value.getResultat());
		
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
		st.setInt(1, value.getIdTournoi());
		int rowcount = st.executeUpdate();
		return rowcount > 0;
	}
}
