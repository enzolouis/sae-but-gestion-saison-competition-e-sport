package DAOs;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import classes.Arbitre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdministrateurDAO {

private Connection dbConnection;
	
	//Constructeur DAO, et mise en place de la connexion
	public AdministrateurDAO(Connection c) {
		this.dbConnection = c;
	}
	
	//Renvoie l'ensemble des administrateurs
	public List<classes.Administrateur> getAll() throws Exception {
		String reqSelectAdministrateur = "SELECT * FROM admin";
		PreparedStatement st = this.dbConnection.prepareStatement(reqSelectAdministrateur);
		ResultSet rs = st.executeQuery();
		ArrayList<classes.Administrateur> administrateur = new ArrayList<classes.Administrateur>();
		while (rs.next()) {
			administrateur.add(new classes.Administrateur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
		}
		return administrateur;
	}
	
	//retourne un Administrateur specifique
	public Optional<classes.Administrateur> getById(Integer... id) throws Exception {
		PreparedStatement st = this.dbConnection.prepareStatement("SELECT * FROM admin WHERE idAdmin=?");
		for (Integer i : id) {
			st.setInt(1, i);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return Optional.of(new classes.Administrateur(i,rs.getString(2),rs.getString(3),rs.getString(4)));
			}
		}
		return Optional.empty();
	}
	
	//ajoute un administrateur à la liste
	public boolean add(classes.Administrateur value) throws Exception {
		
		PreparedStatement st = this.dbConnection.prepareStatement("SELECT NEXT VALUE FOR seqIdAdmin FROM dual");
		ResultSet rs = st.executeQuery();
		int id = 0;
		if (rs.next()) {
			System.out.println(rs.getInt(1));
		}
		return false;
		//value.setIdAdministrateur(id);
		//st = this.dbConnection.prepareStatement("INSERT INTO admin VALUES (?, ?, ?, ?)");
		//st.setInt(1, id); st.setString(2, value.getNom());
		//st.setString(3, value.getLogin()); st.setString(4, value.getMotDePasse());
		//int rowcount = st.executeUpdate();
		//return rowcount > 0;
			
	}
	
	//update un administrateur donné
	public boolean update(classes.Administrateur value) throws Exception {
		
		PreparedStatement st = this.dbConnection.prepareStatement("UPDATE admin SET nom=?, login=?, mdp=?, WHERE idSujet=?");
		st.setString(1, value.getNom()); st.setString(2, value.getLogin());
		st.setString(3, value.getMotDePasse()); st.setInt(4, value.getIdAdministrateur());
		int rowcount = st.executeUpdate();
		return rowcount > 0;
		
	}
	
	//retire un administrateur donné
	public boolean delete(classes.Administrateur value) throws Exception {
		
		PreparedStatement st = this.dbConnection.prepareStatement("DELETE FROM admin WHERE idAdmin=?");
		st.setInt(1, value.getIdAdministrateur());
		int rowcount = st.executeUpdate();
		return rowcount > 0;
	}
	
}