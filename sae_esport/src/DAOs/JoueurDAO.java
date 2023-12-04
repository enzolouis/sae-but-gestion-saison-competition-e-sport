package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import classes.DBConnection;
import classes.Joueur;
import classes.Joueur;

public class JoueurDAO {
	
	//Renvois l'ensemble des joueurs
	public List<Joueur> getAll() throws Exception {
		String reqSelectJoueur = "SELECT * FROM joueur";
		PreparedStatement st = DBConnection.getInstance().prepareStatement(reqSelectJoueur);
		ResultSet rs = st.executeQuery();
		ArrayList<Joueur> joueur = new ArrayList<Joueur>();
		while (rs.next()) {
			joueur.add(new Joueur(rs.getInt(1),rs.getString(2)));
		}
		return joueur;
	}
	
	//retourne un joueur specifique
	public Optional<Joueur> getById(Integer... id) throws Exception {
		Statement st = DBConnection.getInstance().createStatement();
		for (Integer i : id) {
			ResultSet rs = st.executeQuery("SELECT * FROM joueur WHERE idJoueur="+i);
			if (rs.next()) {
				return Optional.of(new Joueur(rs.getInt(1),rs.getString(2)));
			}
		}
		return Optional.empty();
	}
	
	//ajoute un arbitre à la liste
		//peu importe l'id que vous mettrez à l'arbitre, il sera changé
		public boolean add(Joueur value) throws Exception {

			PreparedStatement st = DBConnection.getInstance().prepareStatement("SELECT NEXT VALUE FOR setIDJoueur FROM joueur");
			ResultSet rs = st.executeQuery();
			int id = 0;
			if (rs.next()) {
				id = rs.getInt(1);
			}
			value.setIDJoueur(id);
			st = DBConnection.getInstance().prepareStatement("INSERT INTO joueur VALUES (?,?)");
			st.setInt(1, id); st.setString(2, value.getPseudo()); 
			int rowcount = st.executeUpdate();
			return rowcount > 0;
			
		}
		
		//update un arbitre donné
		public boolean update(Joueur value) throws Exception {
			
			PreparedStatement st = DBConnection.getInstance().prepareStatement("UPDATE arbitre SET pseudo=? WHERE idArbitre=?");
			st.setString(1, value.getPseudo()); st.setInt(2, value.getIdJoueur());
			int rowcount = st.executeUpdate();
			return rowcount > 0;
			
		}
		
		//retire un arbitre donné
		public boolean delete(Joueur value) throws Exception {
			
			PreparedStatement st = DBConnection.getInstance().prepareStatement("DELETE FROM joueur WHERE idArbitre=?");
			st.setInt(1, value.getIdJoueur());
			int rowcount = st.executeUpdate();
			return rowcount > 0;
		}
}
