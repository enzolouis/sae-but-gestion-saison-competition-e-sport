package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import classes.DBConnection;
import classes.Participer;
import classes.Participer;

public class ParticipationDAO {

	
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
		
		PreparedStatement st = DBConnection.getInstance().prepareStatement("UPDATE participer SET idEquipe=?, resultat=? WHERE idTournoi=?");
		 
		st.setInt(1, value.getIdEquipe()); 
		st.setInt(2, value.getResultat());
		
		st.setInt(3, value.getIdTournoi());
		
		int rowcount = st.executeUpdate();
		return rowcount > 0;
		
	}
	
	//retire un arbitre donné
	public boolean delete(Participer value) throws Exception {
		
		PreparedStatement st = DBConnection.getInstance().prepareStatement("DELETE FROM equipe WHERE idEquipe=?");
		st.setInt(1, value.getIdEquipe());
		int rowcount = st.executeUpdate();
		return rowcount > 0;
	}
}
