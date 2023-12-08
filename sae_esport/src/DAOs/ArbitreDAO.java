package DAOs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import classes.Arbitre;
import classes.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ArbitreDAO {
	
	private static ArbitreDAO instance;
	
	public ArbitreDAO() {
		super();
	}
	
	public static synchronized ArbitreDAO getInstance() {
		if (instance == null) {
			instance = new ArbitreDAO();
		}
		return instance;
	}
	
	//Renvois l'ensemble des arbitres
	public List<Arbitre> getAll() throws Exception {
		String reqSelectArbitre = "SELECT * FROM arbitre";
		PreparedStatement st = DBConnection.getInstance().prepareStatement(reqSelectArbitre);
		ResultSet rs = st.executeQuery();
		ArrayList<Arbitre> arbitres = new ArrayList<Arbitre>();
		while (rs.next()) {
			arbitres.add(new Arbitre(rs.getInt(1),rs.getString(2),rs.getString(3),classes.Nationalite.valueOf(rs.getString(4))));
		}
		return arbitres;
	}
	
	//retourne un Arbitre specifique
	public Optional<Arbitre> getById(Integer... id) throws Exception {
		Statement st = DBConnection.getInstance().createStatement();
		for (Integer i : id) {
			ResultSet rs = st.executeQuery("SELECT * FROM arbitre WHERE idArbitre="+i);
			if (rs.next()) {
				return Optional.of(new Arbitre(rs.getInt(1),rs.getString(2),rs.getString(3),classes.Nationalite.valueOf(rs.getString(4))));
			}
		}
		return Optional.empty();
	}
	
	//ajoute un arbitre à la liste
	//peu importe l'id que vous mettrez à l'arbitre, il sera changé
	public boolean add(Arbitre value) throws Exception {

		PreparedStatement st = DBConnection.getInstance().prepareStatement("SELECT NEXT VALUE FOR seqIdArbitre FROM arbitre");
		ResultSet rs = st.executeQuery();
		int id = 0;
		if (rs.next()) {
			id = rs.getInt(1);
		}
		value.setIdArbitre(id);
		st = DBConnection.getInstance().prepareStatement("INSERT INTO arbitre VALUES (?,?,?,?)");
		st.setInt(1, id); st.setString(2, value.getNom()); 
		st.setString(3, value.getPrenom()); st.setString(4, value.getNationalite().toString());
		int rowcount = st.executeUpdate();
		return rowcount > 0;
		
	}
	
	//update un arbitre donné
	public boolean update(Arbitre value) throws Exception {
		
		PreparedStatement st = DBConnection.getInstance().prepareStatement("UPDATE arbitre SET nom=?, prenom=?, nationalite=? WHERE idArbitre=?");
		st.setString(1, value.getNom()); st.setString(2, value.getPrenom());
		st.setString(3, value.getNationalite().toString()); st.setInt(4, value.getIdArbitre());
		int rowcount = st.executeUpdate();
		return rowcount > 0;
		
	}
	
	//retire un arbitre donné
	public boolean delete(Arbitre value) throws Exception {
		
		PreparedStatement st = DBConnection.getInstance().prepareStatement("DELETE FROM arbitre WHERE idArbitre=?");
		st.setInt(1, value.getIdArbitre());
		int rowcount = st.executeUpdate();
		return rowcount > 0;
	}

}
