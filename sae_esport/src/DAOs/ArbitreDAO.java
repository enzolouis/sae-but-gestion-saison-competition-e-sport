package DAOs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import classes.Arbitre;
import classes.DBConnection;
import modeles.TournoiModele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ArbitreDAO {
	
	private static ArbitreDAO instance;
	
	private ArbitreDAO() {
	}
	
	/**
	 * Mise en place d'une instance d'arbitres, pour intéragir avec la bdd
	 * */
	public static synchronized ArbitreDAO getInstance() {
		if (instance == null) {
			instance = new ArbitreDAO();
		}
		return instance;
	}
	
	/**
	 * renvoie l'ensemble des arbitres
	 * */
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
	
	/**
	 * renvoie l'arbitre du premier id reconnu
	 * @param identifiant(s) de l'arbitre
	 * */
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
	
	/**
	 * ajoute un arbitre à la bdd
	 * @param arbitre à ajouter
	 * */
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
	
	/**
	 * met à jour l'arbitre dans la bdd
	 * @param abitre à mettre à j
	 * */
	public boolean update(Arbitre value) throws Exception {
		
		PreparedStatement st = DBConnection.getInstance().prepareStatement("UPDATE arbitre SET nom=?, prenom=?, nationalite=? WHERE idArbitre=?");
		st.setString(1, value.getNom()); st.setString(2, value.getPrenom());
		st.setString(3, value.getNationalite().toString()); st.setInt(4, value.getIdArbitre());
		int rowcount = st.executeUpdate();
		return rowcount > 0;
		
	}
	
	/**
	 * supprime l'arbitre de la bdd
	 * @param arbitre à supp
	 * */
	public boolean delete(Arbitre value) throws Exception {
		
		PreparedStatement st = DBConnection.getInstance().prepareStatement("DELETE FROM gerer WHERE idArbitre=?");
		st.setInt(1, value.getIdArbitre());
		st.executeUpdate();
		st = DBConnection.getInstance().prepareStatement("DELETE FROM arbitre WHERE idArbitre=?");
		st.setInt(1, value.getIdArbitre());
		int rowcount = st.executeUpdate();
		return rowcount > 0;
	}
	
	/**
	 * renvoie la liste des marchitres arbitrés par l'arbitre
	 * @param identifiant(s) de l'arbitre
	 * */
	public List<TournoiModele> getTournoisOfArbitre(Arbitre value) throws Exception {
		ArrayList<TournoiModele> tournois = new ArrayList<>();
		String reqSelectArbitre = "SELECT idTournoi FROM gerer WHERE idArbitre = ?";
		PreparedStatement st = DBConnection.getInstance().prepareStatement(reqSelectArbitre);
		st.setInt(1, value.getIdArbitre());
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			tournois.add(TournoiDAO.getInstance().getById(rs.getInt(1)).get());
			}
		return tournois;
	}
	
}
