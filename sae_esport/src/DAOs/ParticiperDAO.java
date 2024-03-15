package DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import classes.DBConnection;
import classes.Participer;

public class ParticiperDAO {
	
	private static ParticiperDAO instance;
	
	public ParticiperDAO() {
		super();
	}
	
	public static synchronized ParticiperDAO getInstance() {
		if (instance == null) {
			instance = new ParticiperDAO();
		}
		return instance;
	}
	
	/**
	 * récupère la participation d'une équipe à un tournoi
	 * @param id de l'équipe
	 * @param id du tournoi
	 * @return l'optional englobant la participation
	 * */
	public Optional<Participer> getByIdTournoiIdEquipe (int idEquipe, int idTournoi) {

		try {
			
			PreparedStatement st = DBConnection.getInstance().prepareStatement
					("SELECT * FROM participer WHERE idTournoi=? AND idEquipe=?");
			st.setInt(1, idTournoi); st.setInt(2, idEquipe);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return Optional.of(new Participer(rs.getInt(1), idTournoi, idEquipe));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Optional.empty();
		
	}
	
	/**
	 * renvoie la liste des participants du tournoi du premier id reconnu
	 * @param identifiant(s) du tournoi
	 * @return la liste des participations du tournoi
	 * */
	public List<Participer> getByIdTournoi(Integer... id) {
		
		ArrayList<Participer> participations = new ArrayList<>();
		
		try {
			
			PreparedStatement st = DBConnection.getInstance()
					.prepareStatement("SELECT * FROM participer WHERE idTournoi=?");
		
			for (Integer i : id) {
				
				st.setInt(1, i);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {	
					participations.add(new Participer(rs.getInt(1),rs.getInt(2),rs.getInt(3)));
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return participations;
	}
			
	/**
	 * renvoie la liste des participations de l'équipe du premier id reconnu
	 * @param identifiant(s) de l'équipe
	 * @return la liste des participants
	 * */
	public List<Participer> getByIdEquipe(Integer... id) {
		
		ArrayList<Participer> participations = new ArrayList<>();
		
		try {
			
			PreparedStatement st = DBConnection.getInstance()
					.prepareStatement("SELECT * FROM participer WHERE idEquipe=?");
		
			for (Integer i : id) {
				st.setInt(1, i); ResultSet rs = st.executeQuery();
				while (rs.next()) {					//	
					participations.add(new Participer(rs.getInt(1),rs.getInt(2),rs.getInt(3)));
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return participations;
		
	}
	
	/**
	 * renvoie toutes les participations
	 * @return la liste des participations dans la bdd
	 * */
	public List<Participer> getAll() {
		
		ArrayList<Participer> participations = new ArrayList<Participer>();

		try {
			
			PreparedStatement st = DBConnection.getInstance()
					.prepareStatement("SELECT * FROM participer");
			ResultSet rs = st.executeQuery();
		
			while (rs.next()) {
				participations.add(new Participer(rs.getInt(1),rs.getInt(2),rs.getInt(3)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return participations;
	}
	
	/**
	 * ajoute une participation à la bdd
	 * @param participation à ajouter
	 * @return un booléen indiquant si l'ajout s'est effectué 
	 * */
	public boolean add(Participer value) {

		int rowcount = 0;
		
		try {
			
			PreparedStatement st = DBConnection.getInstance()
					.prepareStatement("INSERT INTO participer VALUES (?,?,?)");
			st.setInt(2, value.getIdTournoi()); 
			st.setInt(3, value.getIdEquipe()); 
			st.setInt(1, value.getResultat());
			rowcount = st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowcount > 0;
		
	}
	
	/**
	 * met à jour une participation
	 * @param participation à update
	 * @return un booléen indiquant si la modif s'est effectuée 
	 * */
	public boolean update(Participer value) {
		
		int rowcount = 0; 
		
		try {
			
			PreparedStatement st = DBConnection.getInstance()
					.prepareStatement("UPDATE participer SET resultat=? WHERE idTournoi=? AND idEquipe=?");
			
			st.setInt(1, value.getResultat()); 
			st.setInt(2, value.getIdTournoi());
			st.setInt(3, value.getIdEquipe());
			rowcount = st.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowcount > 0;
		
	}
	
	/**
	 * supprime une participation dans la bdd
	 * @param participation à supprimer
	 * @return un booléen indiquant si la supp s'est effectuée
	 * */
	public boolean delete(Participer value) {
		
		int rowcount = 0;
		
		try {
			
			PreparedStatement st = DBConnection.getInstance()
					.prepareStatement("DELETE FROM participer WHERE idEquipe=? AND idTournoi=?");
			st.setInt(1, value.getIdEquipe());
			st.setInt(2, value.getIdTournoi());
			rowcount = st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowcount > 0;
	}
}
