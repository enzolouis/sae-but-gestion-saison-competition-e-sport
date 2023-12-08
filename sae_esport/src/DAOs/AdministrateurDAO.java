package DAOs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import classes.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdministrateurDAO {
	
	private static AdministrateurDAO instance;
	
	public AdministrateurDAO() {
		super();
	}
	
	public static synchronized AdministrateurDAO getInstance() {
		if (instance == null) {
			instance = new AdministrateurDAO();
		}
		return instance;
	}
		
	//Renvoie l'ensemble des administrateurs
	public List<classes.Administrateur> getAll() throws Exception {
		String reqSelectAdministrateur = "SELECT * FROM admin";
		PreparedStatement st = DBConnection.getInstance().prepareStatement(reqSelectAdministrateur);
		ResultSet rs = st.executeQuery();
		ArrayList<classes.Administrateur> administrateur = new ArrayList<classes.Administrateur>();
		while (rs.next()) {
			administrateur.add(new classes.Administrateur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
		}
		return administrateur;
	}
	
	//retourne un Administrateur specifique
	public Optional<classes.Administrateur> getById(Integer... id) throws Exception {
		PreparedStatement st = DBConnection.getInstance().prepareStatement("SELECT * FROM admin WHERE idAdmin=?");
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
		
		PreparedStatement st = DBConnection.getInstance().prepareStatement("SELECT NEXT VALUE FOR seqIdAdmin FROM admin");
		ResultSet rs = st.executeQuery();
		int id = 0;
		if (rs.next()) {
			id = rs.getInt(1);
		}
		value.setIdAdministrateur(id);
		st = DBConnection.getInstance().prepareStatement("INSERT INTO admin VALUES (?, ?, ?, ?)");
		st.setInt(1, id); st.setString(2, value.getNom());
		st.setString(3, value.getLogin()); st.setString(4, value.getMotDePasse());
		int rowcount = st.executeUpdate();
		return rowcount > 0;
			
	}
	
	//update un administrateur donné
	public boolean update(classes.Administrateur value) throws Exception {
		
		PreparedStatement st = DBConnection.getInstance().prepareStatement("UPDATE admin SET nom=?, login=?, motDePasse=? WHERE idAdmin=?");
		st.setString(1, value.getNom()); st.setString(2, value.getLogin());
		st.setString(3, value.getMotDePasse()); st.setInt(4, value.getIdAdministrateur());
		int rowcount = st.executeUpdate();
		return rowcount > 0;
		
	}
	
	//retire un administrateur donné
	public boolean delete(classes.Administrateur value) throws Exception {
		
		PreparedStatement st = DBConnection.getInstance().prepareStatement("DELETE FROM admin WHERE idAdmin=?");
		st.setInt(1, value.getIdAdministrateur());
		int rowcount = st.executeUpdate();
		return rowcount > 0;
	}
	
}