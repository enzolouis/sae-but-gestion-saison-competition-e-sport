package DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import classes.Arbitre;
import classes.DBConnection;
import classes.Equipe;
import classes.Match;
import classes.Participer;
import modeles.TournoiModele;

public class TournoiDAO {

	private static TournoiDAO instance;

	public TournoiDAO() {
	}

	public static synchronized TournoiDAO getInstance() {
		if (instance == null) {
			instance = new TournoiDAO();
		}
		return instance;
	}

	/**
	 * renvoie l'ensemble des tournois
	 * 
	 * @return la liste des tournois dans la bdd
	 */
	public List<TournoiModele> getAll() {

		ArrayList<TournoiModele> tournois = new ArrayList<>();

		try {

			// séléction des tournois, des participants, des arbitres et des matchs
			PreparedStatement st = DBConnection.getInstance().prepareStatement("SELECT * FROM tournoi");

			PreparedStatement stParticipants = DBConnection.getInstance()
					.prepareStatement("SELECT idEquipe, resultat FROM Participer WHERE idTournoi = ?");

			PreparedStatement stArbitres = DBConnection.getInstance()
					.prepareStatement("SELECT idArbitre FROM Gerer WHERE idTournoi = ?");

			PreparedStatement stMatchs = DBConnection.getInstance()
					.prepareStatement("SELECT idMatch FROM MatchT Where idTournoi = ?");

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

				// création du tournoi
				TournoiModele t = new TournoiModele(rs.getInt(1), rs.getString(2),
						outputFormat.format(inputFormat.parse(rs.getString(3))),
						outputFormat.format(inputFormat.parse(rs.getString(4))),
						rs.getString(7), rs.getString(8), classes.Notoriete.valueOf(rs.getString(5)),
						classes.EtatTournoi.valueOf(rs.getString(6)));

				// récupération des équipes
				stParticipants.setInt(1, rs.getInt(1));
				ResultSet rsParticipants = stParticipants.executeQuery();
				while (rsParticipants.next()) {
					t.ajouterEquipe(EquipeDAO.getInstance().getById(rsParticipants.getInt(1)).get(),
							rsParticipants.getInt(2));
				}

				// récupération des arbitres
				stArbitres.setInt(1, rs.getInt(1));
				ResultSet rsArbitres = stArbitres.executeQuery();
				while (rsArbitres.next()) {
					t.ajouterArbitre(ArbitreDAO.getInstance().getById(rsArbitres.getInt(1)).get());
				}

				// récupération des matchs
				stMatchs.setInt(1, rs.getInt(1));
				ResultSet rsMatchs = stMatchs.executeQuery();
				while (rsMatchs.next()) {
					t.ajouterMatch(MatchDAO.getInstance().getById(rsMatchs.getInt(1)).get());
				}

				tournois.add(t);

			}

		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
		return tournois;

	}

	/**
	 * renvoie le tournoi du premier id reconnu
	 * 
	 * @param identifiant(s) du tournoi
	 * @return l'optional englobant le tournoi séléctionné
	 */
	public Optional<TournoiModele> getById(Integer... id) {

		try {

			PreparedStatement st = DBConnection.getInstance()
					.prepareStatement("SELECT * FROM tournoi WHERE idTournoi=?");

			for (Integer i : id) {

				st.setInt(1, i);
				ResultSet rs = st.executeQuery();

				if (rs.next()) {

					// création du tournoi
					SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

					TournoiModele t = new TournoiModele(rs.getInt(1), rs.getString(2),
							outputFormat.format(inputFormat.parse(rs.getString(3))),
							outputFormat.format(inputFormat.parse(rs.getString(4))),
							rs.getString(7), rs.getString(8), classes.Notoriete.valueOf(rs.getString(5)),
							classes.EtatTournoi.valueOf(rs.getString(6)));

					// récupération des participants
					PreparedStatement stParticipants = DBConnection.getInstance()
							.prepareStatement("SELECT idEquipe, resultat FROM Participer WHERE idTournoi = ?");
					stParticipants.setInt(1, rs.getInt(1));
					ResultSet rsParticipants = stParticipants.executeQuery();
					while (rsParticipants.next()) {
						t.ajouterEquipe(EquipeDAO.getInstance().getById(rsParticipants.getInt(1)).get(),
								rsParticipants.getInt(2));
					}

					// récupération des arbitres
					PreparedStatement stArbitres = DBConnection.getInstance()
							.prepareStatement("SELECT idArbitre FROM Gerer WHERE idTournoi = ?");
					stArbitres.setInt(1, rs.getInt(1));
					ResultSet rsArbitres = stArbitres.executeQuery();
					while (rsArbitres.next()) {
						t.ajouterArbitre(ArbitreDAO.getInstance().getById(rsArbitres.getInt(1)).get());
					}

					// récupération des matchs
					PreparedStatement stMatchs = DBConnection.getInstance()
							.prepareStatement("SELECT idMatch FROM MatchT Where idTournoi = ?");
					stMatchs.setInt(1, rs.getInt(1));
					ResultSet rsMatchs = stMatchs.executeQuery();
					while (rsMatchs.next()) {
						t.ajouterMatch(MatchDAO.getInstance().getById(rsMatchs.getInt(1)).get());
					}

					return Optional.of(t);
				}
			}

		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}

		return Optional.empty();
	}

	/**
	 * ajoute un tournoi à la bdd
	 * 
	 * @param tournoi à supprimer
	 * @return booléen indiquant si le tournoi a été ajouté ou non
	 */
	public boolean add(TournoiModele value) {

		int rowcount = 0;

		try {

			PreparedStatement st = DBConnection.getInstance()
					.prepareStatement("SELECT NEXT VALUE FOR seqIdTournoi FROM dual");
			ResultSet rs = st.executeQuery();
			int id = 0;
			if (rs.next()) {
				id = rs.getInt(1);
			}
			value.setIDTournoi(id);

			for (Match m : value.getMatchs()) {
				m.setIdTournoi(id);
			}

			st = DBConnection.getInstance().prepareStatement(
					"INSERT INTO tournoi (idTournoi, nom, dateDebut, dateFin, notoriete, ouvert, login, mdp) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			st.setInt(1, id);
			st.setString(2, value.getNomTournoi());
			st.setDate(3, value.getDateDebut());
			st.setDate(4, value.getDateFin());
			st.setString(5, value.getNotoriete().toString());
			st.setString(6, value.getEtatTournoi().toString());
			st.setString(7, value.getLogin());
			st.setString(8, value.getMotDePasse());
			rowcount = st.executeUpdate();

			for (Match m : value.getMatchs()) {
				MatchDAO.getInstance().add(m);
			}

			for (Equipe e : value.getParticipants().keySet()) {
				ParticiperDAO.getInstance().add(new Participer(value.getParticipants().get(e), id, e.getIdEquipe()));
			}

			for (Arbitre a : value.getArbitres()) {
				st = DBConnection.getInstance().prepareStatement("INSERT INTO gerer VALUES(?,?)");
				st.setInt(1, value.getIDTournoi());
				st.setInt(2, a.getIdArbitre());
				st.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowcount > 0;
	}

	/**
	 * mettre à jour un tournoi
	 * 
	 * @param tournoi à maj
	 * @return booléen indiquant si la maj du tournoi a été effectuée
	 */
	public boolean update(TournoiModele value) {

		// Construction de la requête UPDATE
		String query = "UPDATE tournoi SET "
				+ "nom = ?, "
				+ "dateDebut = ?, "
				+ "dateFin = ?, "
				+ "notoriete = ?, "
				+ "ouvert = ?, "
				+ "login = ?, "
				+ "mdp = ?, "
				+ "idVainqueur = ? "
				+ "WHERE idTournoi = ?";

		int rowcount = 0;

		try {

			// maj du tournoi
			PreparedStatement preparedStatement = DBConnection.getInstance().prepareStatement(query);

			preparedStatement.setString(1, value.getNomTournoi());
			preparedStatement.setDate(2, value.getDateDebut());
			preparedStatement.setDate(3, value.getDateFin());
			preparedStatement.setString(4, value.getNotoriete().toString());
			preparedStatement.setString(5, value.getEtatTournoi().toString());
			preparedStatement.setString(6, value.getLogin());
			preparedStatement.setString(7, value.getMotDePasse());
			try {
				preparedStatement.setInt(8, value.getVainqueur().get().getIdEquipe());
			} catch (NoSuchElementException e) {
				preparedStatement.setObject(8, null);
			}
			preparedStatement.setInt(9, value.getIDTournoi());

			// maj des matchs du tournoi
			for (Match m : value.getMatchs()) {
				MatchDAO.getInstance().update(m);
			}

			// maj des equipes du tournoi
			for (Equipe e : value.getParticipants().keySet()) {
				if (value.getParticipants().containsKey(e)) {
					ParticiperDAO.getInstance().update(
							new Participer(value.getParticipants().get(e), value.getIDTournoi(), e.getIdEquipe()));
				}
			}

			// maj des arbitres
			List<Arbitre> arbitres = new ArrayList<Arbitre>();
			arbitres.addAll(value.getArbitres());

			PreparedStatement stArbitres = DBConnection.getInstance()
					.prepareStatement("SELECT idArbitre FROM Gerer WHERE idTournoi = ?");
			stArbitres.setInt(1, value.getIDTournoi());
			ResultSet rsArbitres = stArbitres.executeQuery();

			while (rsArbitres.next()) {
				arbitres.remove(ArbitreDAO.getInstance().getById(rsArbitres.getInt(1)).get());
			}

			stArbitres = DBConnection.getInstance()
					.prepareStatement("DELETE FROM Gerer WHERE idTournoi = ? AND idArbitre = ?");
			stArbitres.setInt(1, value.getIDTournoi());

			for (Arbitre a : arbitres) {
				stArbitres.setInt(2, a.getIdArbitre());
				stArbitres.executeUpdate();
			}

			rowcount = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowcount > 0;

	}

	/**
	 * supprime de la bdd un tournoi donné
	 * 
	 * @param tournoi à supp
	 * @return booléen indiquant si le tournoi été effectivement supprimé
	 */
	public boolean delete(TournoiModele value) {

		int rowcount = 0;

		try {
			PreparedStatement st = DBConnection.getInstance().prepareStatement("DELETE FROM tournoi WHERE idTournoi=?");
			st.setInt(1, value.getIDTournoi());
			rowcount = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowcount > 0;
	}

	/**
	 * renvoie le tournoi actuellement ouvert (ou un empty si aucun tournoi n'est
	 * ouvert)
	 * 
	 * @return optional englobant le potentiel tournoi ouvert
	 */
	public Optional<TournoiModele> getTournoiOuvert() {
		Statement st;
		try {
			st = DBConnection.getInstance().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM tournoi WHERE ouvert='OUVERT'");
			if (rs.next()) {
				return this.getById(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	/**
	 * ajoute un arbitre à un tournoi et renvoie si l'ajout s'est fait
	 * 
	 * @param tournoi où ajouter l'arbitre
	 * @param arbitre à ajouter
	 * @return booléen indiquant si l'ajout de l'arbitre au tournoi a été effectuée
	 */
	public boolean addArbitre(TournoiModele t, Arbitre a) {

		int rowcount = 0;

		try {
			PreparedStatement stAddGerer = DBConnection.getInstance()
					.prepareStatement("INSERT INTO gerer VALUES (?,?)");
			stAddGerer.setInt(1, t.getIDTournoi());
			stAddGerer.setInt(2, a.getIdArbitre());
			rowcount = stAddGerer.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowcount > 0;

	}

	/**
	 * retire un arbitre d'un tournoi et renvoie si la suppression s'est faite
	 * 
	 * @param tournoi où supprimer l'arbitre
	 * @param arbitre à supprimer
	 * @return un booléen indiquant si la suppression de l'arbitre au tournoi a été
	 *         effectuée
	 */
	public boolean deleteArbitre(TournoiModele t, Arbitre a) {

		int rowcount = 0;

		try {
			PreparedStatement stDelGerer = DBConnection.getInstance()
					.prepareStatement("DELETE FROM gerer WHERE idTournoi=? AND idArbitre = ?");
			stDelGerer.setInt(2, a.getIdArbitre());
			rowcount = stDelGerer.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowcount > 0;

	}

	/**
	 * ajoute une équipe au tournoi et renvoie si l'ajout s'est fait
	 * 
	 * @param tournoi où ajouter l'équipe
	 * @param équipe  à ajouter
	 * @return un booléen indiquant si l'ajout de l'équipe dans le tournoi a été
	 *         effectuée
	 */
	public boolean addEquipe(TournoiModele t, Equipe e) {

		Participer p = new Participer(0, t.getIDTournoi(), e.getIdEquipe());
		return ParticiperDAO.getInstance().add(p);

	}

	/**
	 * supprimer une équipe au tournoi et renvoie si la suppressio s'est faite
	 * 
	 * @param tournoi où supprimer l'équipe
	 * @param équipe  à supprimer
	 * @return un booléen indiquant si la suppression de l'équipe dans le tournoi a
	 *         bien eu lieu
	 */
	public boolean deleteEquipe(TournoiModele t, Equipe e) {

		Optional<Participer> p = ParticiperDAO.getInstance().getByIdTournoiIdEquipe(e.getIdEquipe(), t.getIDTournoi());
		if (p.isPresent()) {
			return ParticiperDAO.getInstance().delete(p.get());
		}
		return false;

	}

}
