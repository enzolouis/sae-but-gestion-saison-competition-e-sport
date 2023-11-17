package DAOs;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ArbitreDAO {
	
	private Connection dbConnection;
	
	public ArbitreDAO(Connection c) {
		this.dbConnection = c;
	}
	
	public List<Arbitre> getAll() throws Exception {
		String reqSelectArbitre = "SELECT * FROM arbitre";
		PreparedStatement st = this.dbConnection.prepareStatement(reqSelectArbitre);
		ResultSet rs = st.executeQuery();
		ArrayList<Arbitre> arbitres = new ArrayList<Arbitre>();
		while (rs.next()) {
			arbitres.add(new Arbitre(rs.getInt(1),rs.getString(2),rs.getString(3),Nationalite.valueOf(rs.getString(4))));
		}
		return arbitres;
	}
	
	public Optional<Arbitre> getById(Integer... id) throws Exception {
		Statement st = this.dbConnection.createStatement();
		for (Integer i : id) {
			ResultSet rs = st.executeQuery("SELECT * FROM arbitre WHERE idArbitre="+i);
			if (rs.next()) {
				return Optional.of(new Arbitre(rs.getInt(1),rs.getString(2),rs.getString(3),Nationalite.valueOf(rs.getString(4))));
			}
		}
		return Optional.empty();
	}
	
	public boolean add(Arbitre value) throws Exception {

		Statement st = this.dbConnection.createStatement();
		int rowcount = st.executeUpdate("INSERT INTO arbitre VALUES ("+value.getIdArbitre()+", '"+value.getNom()+"', '"+value.getPrenom()+"', '"+value.getNationalite()+"')");
		return rowcount > 0;
		
	}
	
	public boolean update(Arbitre value) throws Exception {
		
		Statement st = this.dbConnection.createStatement();
		int rowcount = st.executeUpdate("UPDATE arbitre SET nom='"+value.getNom()+"', prenom='"+value.getPrenom()+", nationalite='"+value.getNationalite()+"' WHERE idSujet="+value.getIdArbitre());
		return rowcount > 0;
		
	}
	
	public boolean delete(Arbitre value) throws Exception {
		
		Statement st = this.dbConnection.createStatement();
		int rowcount = st.executeUpdate("DELETE FROM arbitre WHERE idArbitre="+value.getIdArbitre());
		return rowcount > 0;
	}

}
