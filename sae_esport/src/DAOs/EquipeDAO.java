package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import classes.Equipe;
import classes.Equipe;

public class EquipeDAO {
	private Connection dbConnection;
	
	//Constructeur DAO, et mise en place de la connexion
	public EquipeDAO(Connection c) {
		this.dbConnection = c;
	}
	
	//Renvois l'ensemble des arbitres
	public List<Equipe> getAll() throws Exception {
		String reqSelectEquipe = "SELECT * FROM equipe";
		PreparedStatement st = this.dbConnection.prepareStatement(reqSelectEquipe);
		ResultSet rs = st.executeQuery();
		ArrayList<Equipe> arbitres = new ArrayList<Equipe>();
		while (rs.next()) {
			arbitres.add(new Equipe(rs.getInt(1),rs.getString(2),classes.Nationalite.valueOf(rs.getString(3)),rs.getBoolean(4),rs.getInt(5),rs.getInt(6)));
		}
		return arbitres;
	}
	
	//ajoute un arbitre à la liste
	//peu importe l'id que vous mettrez à l'arbitre, il sera changé
	public boolean add(Equipe value) throws Exception {

		PreparedStatement st = this.dbConnection.prepareStatement("SELECT NEXT VALUE FOR idEquipe FROM equipe");
		ResultSet rs = st.executeQuery();
		int id = 0;
		if (rs.next()) {
			id = rs.getInt(1);
		}
		value.setIdEquipe(id);
		st = this.dbConnection.prepareStatement("INSERT INTO arbitre VALUES (?,?,?,?,?,?)");
		st.setInt(1, id); 
		st.setString(2, value.getNom()); 
		st.setObject(3, (Object) value.getNationalite()); 
		st.setBoolean(4, value.getDisposition()); 
		st.setInt(5, value.getRangSaisonPrecedante()); 
		st.setInt(6, value.getPointsSaison());
		
		int rowcount = st.executeUpdate();
		return rowcount > 0;
		
	}
	
	//update un arbitre donné
	public boolean update(Equipe value) throws Exception {
		
		PreparedStatement st = this.dbConnection.prepareStatement("UPDATE arbitre SET nom=?, nationalite=?, dispose=?, rangsaisonprecedante=?, pointssaison=? WHERE idArbitre=?");
		st.setString(1, value.getNom()); 
		st.setObject(2, (Object) value.getNationalite()); 
		st.setBoolean(3, value.getDisposition()); 
		st.setInt(4, value.getRangSaisonPrecedante()); 
		st.setInt(5, value.getPointsSaison());
		
		st.setInt(6, value.getIdEquipe());
		
		int rowcount = st.executeUpdate();
		return rowcount > 0;
		
	}
	
	//retire un arbitre donné
	public boolean delete(Equipe value) throws Exception {
		
		PreparedStatement st = this.dbConnection.prepareStatement("DELETE FROM equipe WHERE idEquipe=?");
		st.setInt(1, value.getIdEquipe());
		int rowcount = st.executeUpdate();
		return rowcount > 0;
	}
}
