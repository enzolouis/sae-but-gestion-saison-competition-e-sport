package DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import classes.DBConnection;
import classes.Match;

public class MatchDAO {
	
	private static MatchDAO instance;
	
	public MatchDAO() {
		super();
	}
	
	public static synchronized MatchDAO getInstance() {
		if (instance == null) {
			instance = new MatchDAO();
		}
		return instance;
	}
	
	//Renvois l'ensemble des joueurs
	public List<Match> getAll() throws Exception {
		String reqSelectJoueur = "SELECT * FROM match";
		PreparedStatement st = DBConnection.getInstance().prepareStatement(reqSelectJoueur);
		ResultSet rs = st.executeQuery();
		ArrayList<Match> match = new ArrayList<Match>();
		while (rs.next()) {
			match.add(new Match(rs.getInt(1),rs.getBoolean(2)));
		}
		return match;
	}
	
	//ajoute un arbitre à la liste
			//peu importe l'id que vous mettrez à l'arbitre, il sera changé
			public boolean add(Match value) throws Exception {

				PreparedStatement st = DBConnection.getInstance().prepareStatement("SELECT NEXT VALUE FOR setIdMatch FROM match");
				ResultSet rs = st.executeQuery();
				int id = 0;
				if (rs.next()) {
					id = rs.getInt(1);
				}
				value.setIdMatch(id);
				st = DBConnection.getInstance().prepareStatement("INSERT INTO joueur VALUES (?,?)");
				st.setInt(1, id); 
				st.setBoolean(2, value.IsItFinale()); 
				
				int rowcount = st.executeUpdate();
				return rowcount > 0;
				
			}
			
			//update un arbitre donné
			public boolean update(Match value) throws Exception {
				
				PreparedStatement st = DBConnection.getInstance().prepareStatement("UPDATE match SET finale=? WHERE idMatch=?");
				st.setBoolean(1, value.IsItFinale()); st.setInt(2, value.getIDMatch());
				int rowcount = st.executeUpdate();
				return rowcount > 0;
				
			}
			
			//retire un arbitre donné
			public boolean delete(Match value) throws Exception {
				
				PreparedStatement st = DBConnection.getInstance().prepareStatement("DELETE FROM match WHERE idMatch=?");
				st.setInt(1, value.getIDMatch());
				int rowcount = st.executeUpdate();
				return rowcount > 0;
			}
}
