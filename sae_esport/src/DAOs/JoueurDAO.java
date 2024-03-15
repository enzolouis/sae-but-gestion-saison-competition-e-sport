package DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import classes.DBConnection;
import classes.Joueur;

public class JoueurDAO {
	
	private static JoueurDAO instance;
	
	public JoueurDAO() {
		super();
	}
	
	public static synchronized JoueurDAO getInstance() {
		if (instance == null) {
			instance = new JoueurDAO();
		}
		return instance;
	}
	
	/**
	 * renvoie l'ensemble des joueurs dans la BDD
	 * @return la liste des joueurs
	 * */
	public List<Joueur> getAll() {
		
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		
		try {
			
			PreparedStatement st = DBConnection.getInstance().prepareStatement("SELECT * FROM joueur");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				joueurs.add(new Joueur(rs.getInt(1),rs.getString(2),rs.getInt(3)));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return joueurs;
		
	}
	
	/**
	 * renvoie le joueur du premier id reconnu
	 * @param identifiant(s) du joueur
	 * @return optional englobant le possible joueur existant
	 * */
	public Optional<Joueur> getById(Integer... id) {
		
		try {
			PreparedStatement st = DBConnection.getInstance()
					.prepareStatement("SELECT * FROM joueur WHERE idJoueur=?");
		for (Integer i : id) {
			st.setInt(1, i);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return Optional.of(new Joueur(rs.getInt(1),rs.getString(2),rs.getInt(3)));
			}
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Optional.empty();
		
	}

	/**
	 * ajoute un joueur à la bdd
	 * @param joueur à ajouter
	 * @return booléen indiquant si le joueur a été ajouté ou non
	 * */
	public boolean add(Joueur value) {
		
		int rowcount = 0;

		try {
			
			//changement de l'id du joueur selon son id dans la bdd
			PreparedStatement st = DBConnection.getInstance().prepareStatement("SELECT NEXT VALUE FOR seQIDJoueur FROM DUAL");
			ResultSet rs = st.executeQuery();
			int id = 0;
			if (rs.next()) {
				id = rs.getInt(1);
			}
			value.setIDJoueur(id);
			
			//insérer le joueur dans la bdd
			st = DBConnection.getInstance().prepareStatement("INSERT INTO joueur VALUES (?,?, ?)");
			st.setInt(1, id); st.setString(2, value.getPseudo()); st.setInt(3, value.getIdEquipe());
			rowcount = st.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowcount > 0;
			
	}
		
	/**
	 * met à j dans la bdd un joueur
	 * @param joueur à mettre à jour
	 * @return le booléen indiquant si la maj s'est bien effectué
	 * */
	public boolean update(Joueur value) {
		
		int rowcount = 0;

		try {
			
			PreparedStatement st = DBConnection.getInstance()
					.prepareStatement("UPDATE joueur SET pseudo=?, idEquipe=? WHERE idJoueur=?");
			st.setString(1, value.getPseudo()); st.setInt(2, value.getIdEquipe()); 
			st.setInt(3, value.getIdJoueur());
			rowcount = st.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowcount > 0;
			
	}
		
	/**
	 * supprimer le joueur de la bdd
	 * @param joueur à supprimer
	 * @return le booléen indiquant si la suppression s'est bien effectuée
	 * */
	public boolean delete(Joueur value) {
		
		int rowcount = 0;
			
		try {
			PreparedStatement st = DBConnection.getInstance()
					.prepareStatement("DELETE FROM joueur WHERE idJoueur=?");
		
			st.setInt(1, value.getIdJoueur());
			rowcount = st.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowcount > 0;

	}
}
