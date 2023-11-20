package DAOs;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
		String reqSelectAdministrateur = "SELECT * FROM administrateur";
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
		Statement st = this.dbConnection.createStatement();
		for (Integer i : id) {
			ResultSet rs = st.executeQuery("SELECT * FROM administrateur WHERE idadministrateur="+i);
			if (rs.next()) {
				return Optional.of(new classes.Administrateur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
			}
		}
		return Optional.empty();
	}
	
	//ajoute un administrateur à la liste
	public boolean add(classes.Administrateur value) throws Exception {

		Statement st = this.dbConnection.createStatement();
		int rowcount = st.executeUpdate("INSERT INTO administrateur VALUES ("+value.getIdAdministrateur()+", '"+value.getNom()+"', '"+value.getLogin()+"', '"+value.getmotDePasse()+"',)");
		return rowcount > 0;
		
	}
	
	//update un administrateur donné
	public boolean update(classes.Administrateur value) throws Exception {
		
		Statement st = this.dbConnection.createStatement();
		int rowcount = st.executeUpdate("UPDATE administrateur SET nom='"+value.getNom()+"', login='"+value.getLogin()+"', mdp='"+value.getmotDePasse()+", WHERE idSujet="+value.getIdAdministrateur());
		return rowcount > 0;
		
	}
	
	//retire un administrateur donné
	public boolean delete(classes.Administrateur value) throws Exception {
		
		Statement st = this.dbConnection.createStatement();
		int rowcount = st.executeUpdate("DELETE FROM administrateur WHERE idAdministrateur="+value.getIdAdministrateur());
		return rowcount > 0;
	}
	
}