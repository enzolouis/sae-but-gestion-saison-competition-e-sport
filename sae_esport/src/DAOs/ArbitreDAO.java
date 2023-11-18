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
	
	//Constructeur DAO, et mise en place de la connexion
	public ArbitreDAO(Connection c) {
		this.dbConnection = c;
	}
	
	//Renvois l'ensemble des arbitres
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
	
	//ajoute un arbitre à la liste
	public boolean add(Arbitre value) throws Exception {

		Statement st = this.dbConnection.createStatement();
		int rowcount = st.executeUpdate("INSERT INTO arbitre VALUES ("+value.getIdArbitre()+", '"+value.getNom()+"', '"+value.getPrenom()+"', '"+value.getNationalite()+"')");
		return rowcount > 0;
		
	}
	
	//update un arbitre donné
	public boolean update(Arbitre value) throws Exception {
		
		Statement st = this.dbConnection.createStatement();
		int rowcount = st.executeUpdate("UPDATE arbitre SET nom='"+value.getNom()+"', prenom='"+value.getPrenom()+", nationalite='"+value.getNationalite()+"' WHERE idSujet="+value.getIdArbitre());
		return rowcount > 0;
		
	}
	
	//retire un arbitre donné
	public boolean delete(Arbitre value) throws Exception {
		
		Statement st = this.dbConnection.createStatement();
		int rowcount = st.executeUpdate("DELETE FROM arbitre WHERE idArbitre="+value.getIdArbitre());
		return rowcount > 0;
	}

}
