package DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import classes.DBConnection;
import classes.Joueur;
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
	
	//Renvois l'ensemble des matchs
	public List<Match> getAll() throws Exception {
		String reqSelectJoueur = "SELECT * FROM matchT";
		PreparedStatement st = DBConnection.getInstance().prepareStatement(reqSelectJoueur);
		ResultSet rs = st.executeQuery();
		ArrayList<Match> match = new ArrayList<Match>();
		while (rs.next()) {
			Match m = new Match(rs.getInt(1),rs.getInt(4),rs.getBoolean(2));
			Integer idVainqueur = rs.getInt(3);
			if (idVainqueur != 0) {
				m.setVainqueur(idVainqueur);
			}
			PreparedStatement stEquipes = DBConnection.getInstance()
					.prepareStatement("SELECT idEquipe1, idEquipe2 FROM disputer WHERE idMatch = ?");
			stEquipes.setInt(1, m.getIDMatch());
			ResultSet rsEquipes = stEquipes.executeQuery();
			if (rsEquipes.next()) {
				m.AddEquipe(EquipeDAO.getInstance().getById(rsEquipes.getInt(1)).get());
				m.AddEquipe(EquipeDAO.getInstance().getById(rsEquipes.getInt(2)).get());
				match.add(m);
			}
		}
		return match;
	}
	
	//retourne un match specifique
		public Optional<Match> getById(Integer... id) throws Exception {
			Statement st = DBConnection.getInstance().createStatement();
			for (Integer i : id) {
				ResultSet rs = st.executeQuery("SELECT * FROM matchT WHERE idMatch="+i);
				if (rs.next()) {
					Match m = new Match(rs.getInt(1),rs.getInt(4),rs.getBoolean(2));
					Integer idVainqueur = rs.getInt(3);
					if (idVainqueur != 0) {
						m.setVainqueur(idVainqueur);
					}
					PreparedStatement stEquipes = DBConnection.getInstance()
							.prepareStatement("SELECT idEquipe1, idEquipe2 FROM disputer WHERE idMatch = ?");
					stEquipes.setInt(1, m.getIDMatch());
					ResultSet rsEquipes = stEquipes.executeQuery();
					if (rsEquipes.next()) {
						m.AddEquipe(EquipeDAO.getInstance().getById(rsEquipes.getInt(1)).get());
						m.AddEquipe(EquipeDAO.getInstance().getById(rsEquipes.getInt(2)).get());
					}
					return Optional.of(m);
				}
			}
			return Optional.empty();
		}
	
	//ajoute un arbitre à la liste
			//peu importe l'id que vous mettrez à l'arbitre, il sera changé
			public boolean add(Match value) throws Exception {

				PreparedStatement st = DBConnection.getInstance().prepareStatement("SELECT NEXT VALUE FOR SeqIdMatch FROM dual");
				ResultSet rs = st.executeQuery();
				int id = 0;
				if (rs.next()) {
					id = rs.getInt(1);
				}
				value.setIdMatch(id);
				
				st = DBConnection.getInstance().prepareStatement("INSERT INTO matchT VALUES (?,?,?,?)");
				st.setInt(1, id); 
				st.setBoolean(2, value.IsItFinale());
				if (value.getVainqueur() == 0) {
					st.setNull(3, Types.INTEGER);
				} else {
					st.setInt(3, value.getVainqueur());
				}
				st.setInt(4, value.getIdTournoi());
				int rowcountMatch = st.executeUpdate();
				
				int rowcountDisputer = 1;
				
				if (!value.getEquipes().isEmpty()) {
					st = DBConnection.getInstance().prepareStatement("INSERT INTO disputer VALUEs(?,?,?)");
					st.setInt(1, id); st.setInt(2, value.getEquipes().get(0).getIdEquipe()); 
					st.setInt(3, value.getEquipes().get(1).getIdEquipe()); 
					rowcountDisputer = st.executeUpdate();
				} 
				
				return rowcountMatch > 0 && rowcountDisputer > 0;
				
			}
			
			//update un arbitre donné
			public boolean update(Match value) throws Exception {
				
				PreparedStatement st = DBConnection.getInstance()
						.prepareStatement("UPDATE matchT SET finale=?, idVainqueur=? WHERE idMatch=?");
				st.setBoolean(1, value.IsItFinale()); st.setInt(2, value.getVainqueur()); st.setInt(3, value.getIDMatch()); 
				int rowcount = st.executeUpdate();
				return rowcount > 0;
				
			}
			
			//retire un arbitre donné
			public boolean delete(Match value) throws Exception {
				PreparedStatement st = DBConnection.getInstance().prepareStatement("DELETE FROM disputer WHERE idMatch=?");
				st.setInt(1, value.getIDMatch());
				st.executeUpdate();
				st = DBConnection.getInstance().prepareStatement("DELETE FROM matchT WHERE idMatch=?");
				st.setInt(1, value.getIDMatch());
				int rowcount = st.executeUpdate();
				return rowcount > 0;
			}
}
