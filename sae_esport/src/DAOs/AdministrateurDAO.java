package DAOs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import classes.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import classes.Administrateur;

public class AdministrateurDAO {

	private static AdministrateurDAO instance;

	private AdministrateurDAO() {
	}

	public static synchronized AdministrateurDAO getInstance() {
		if (instance == null) {
			instance = new AdministrateurDAO();
		}
		return instance;
	}

	/**
	 * renvoie l'ensemble des administrateurs
	 * 
	 * @return la liste des administrateurs
	 */
	public List<Administrateur> getAll() {

		ArrayList<Administrateur> administrateurs = new ArrayList<Administrateur>();

		try {

			PreparedStatement st = DBConnection.getInstance().prepareStatement("SELECT * FROM admin");
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				administrateurs.add(new Administrateur(
						rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return administrateurs;

	}

	/**
	 * renvoie l'administrateur du premier id reconnu
	 * 
	 * @param identifiant(s) de l'admin
	 * @return l'optional englobant le possible administrateur
	 */
	public Optional<Administrateur> getById(Integer... id) {

		try {

			PreparedStatement st = DBConnection.getInstance().prepareStatement("SELECT * FROM admin WHERE idAdmin=?");

			for (Integer i : id) {
				st.setInt(1, i);
				ResultSet rs = st.executeQuery();
				if (rs.next()) {
					return Optional
							.of(new classes.Administrateur(i, rs.getString(2), rs.getString(3), rs.getString(4)));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Optional.empty();
	}

	/**
	 * ajoute un administrateur à la liste
	 * 
	 * @param admin à ajouter
	 * @return un booléen indiquant si l'administrateur a été inséré
	 */
	public boolean add(Administrateur value) {

		int rowcount = 0;

		try {

			PreparedStatement st = DBConnection.getInstance()
					.prepareStatement("SELECT NEXT VALUE FOR seqIdAdmin FROM admin");
			ResultSet rs = st.executeQuery();
			int id = 0;

			if (rs.next()) {
				id = rs.getInt(1);
			}

			value.setIdAdministrateur(id);

			st = DBConnection.getInstance().prepareStatement("INSERT INTO admin VALUES (?, ?, ?, ?)");
			st.setInt(1, id);
			st.setString(2, value.getNom());
			st.setString(3, value.getLogin());
			st.setString(4, value.getMotDePasse());
			rowcount = st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowcount > 0;

	}

	/**
	 * met à jour dans la bdd l'admin donné
	 * 
	 * @param admin à mettre à jour
	 * @return un booléen indiquant si la modif a été mise à jour
	 */
	public boolean update(Administrateur value) {

		int rowcount = 0;

		try {

			PreparedStatement st = DBConnection.getInstance()
					.prepareStatement("UPDATE admin SET nom=?, login=?, motDePasse=? WHERE idAdmin=?");
			st.setString(1, value.getNom());
			st.setString(2, value.getLogin());
			st.setString(3, value.getMotDePasse());
			st.setInt(4, value.getIdAdministrateur());
			rowcount = st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowcount > 0;

	}

	/**
	 * supprime de la bdd un administrateur donné
	 * 
	 * @param l'administrateur à supprimer
	 * @return un booléen indiquant si la suppression a été effectuée
	 */
	public boolean delete(Administrateur value) {

		int rowcount = 0;

		try {

			PreparedStatement st = DBConnection.getInstance().prepareStatement("DELETE FROM admin WHERE idAdmin=?");
			st.setInt(1, value.getIdAdministrateur());
			rowcount = st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowcount > 0;
	}

}