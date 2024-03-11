package DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
	 * */
	public List<TournoiModele> getAll() throws Exception {
		
		ArrayList<TournoiModele> tournois = new ArrayList<>();
		String reqSelectTournoi = "SELECT * FROM tournoi";
		PreparedStatement st = DBConnection.getInstance().prepareStatement(reqSelectTournoi);
		ResultSet rs = st.executeQuery();
		
		String reqSelectParticipants = "SELECT idEquipe, resultat FROM Participer WHERE idTournoi = ?";
		PreparedStatement stParticipants = DBConnection.getInstance().prepareStatement(reqSelectParticipants);
		
		PreparedStatement stArbitres = DBConnection.getInstance().prepareStatement("SELECT idArbitre FROM Gerer WHERE idTournoi = ?");
		
		PreparedStatement stMatchs = DBConnection.getInstance().prepareStatement("SELECT idMatch FROM MatchT Where idTournoi = ?");
		
		while (rs.next()) {			
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
		    SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
			TournoiModele t = new TournoiModele(rs.getInt(1), rs.getString(2), outputFormat.format(inputFormat.parse(rs.getString(3))), outputFormat.format(inputFormat.parse(rs.getString(4))), rs.getString(7), rs.getString(8), classes.Notoriete.valueOf(rs.getString(5)), classes.EtatTournoi.valueOf(rs.getString(6)));
			stParticipants.setInt(1, rs.getInt(1));
			ResultSet rsParticipants = stParticipants.executeQuery();
			while (rsParticipants.next()) {
				t.ajouterEquipe(EquipeDAO.getInstance().getById(rsParticipants.getInt(1)).get(),rsParticipants.getInt(2));
			}
			stArbitres.setInt(1, rs.getInt(1));
			ResultSet rsArbitres = stArbitres.executeQuery();
			while (rsArbitres.next()) {
				t.ajouterArbitre(ArbitreDAO.getInstance().getById(rsArbitres.getInt(1)).get());
			}
			stMatchs.setInt(1, rs.getInt(1));
			ResultSet rsMatchs = stMatchs.executeQuery();
			while (rsMatchs.next()) {
				t.ajouterMatch(MatchDAO.getInstance().getById(rsMatchs.getInt(1)).get());
			}
			
			tournois.add(t);
		}
		return tournois;
	}
	
	/**
	 * renvoie le tournoi du premier id reconnu
	 * @param identifiant(s) du tournoi
	 * */
	public Optional<TournoiModele> getById(Integer... id) throws Exception {
		Statement st = DBConnection.getInstance().createStatement();
		for (Integer i : id) {
			ResultSet rs = st.executeQuery("SELECT * FROM tournoi WHERE idTournoi="+i);
			if (rs.next()) {
				SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
			    SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
				TournoiModele t = new TournoiModele(rs.getInt(1), rs.getString(2), outputFormat.format(inputFormat.parse(rs.getString(3))), outputFormat.format(inputFormat.parse(rs.getString(4))), rs.getString(7), rs.getString(8), classes.Notoriete.valueOf(rs.getString(5)), classes.EtatTournoi.valueOf(rs.getString(6)));
				
				PreparedStatement stParticipants = DBConnection.getInstance().prepareStatement("SELECT idEquipe, resultat FROM Participer WHERE idTournoi = ?");
				stParticipants.setInt(1, rs.getInt(1));
				ResultSet rsParticipants = stParticipants.executeQuery();
				while (rsParticipants.next()) {
					t.ajouterEquipe(EquipeDAO.getInstance().getById(rsParticipants.getInt(1)).get(),rsParticipants.getInt(2));
				}
				
				PreparedStatement stArbitres = DBConnection.getInstance().prepareStatement("SELECT idArbitre FROM Gerer WHERE idTournoi = ?");
				stArbitres.setInt(1, rs.getInt(1));
				ResultSet rsArbitres = stArbitres.executeQuery();
				while (rsArbitres.next()) {
					t.ajouterArbitre(ArbitreDAO.getInstance().getById(rsArbitres.getInt(1)).get());
				}
				
				PreparedStatement stMatchs = DBConnection.getInstance().prepareStatement("SELECT idMatch FROM MatchT Where idTournoi = ?");
				stMatchs.setInt(1, rs.getInt(1));
				ResultSet rsMatchs = stMatchs.executeQuery();
				while (rsMatchs.next()) {
					t.ajouterMatch(MatchDAO.getInstance().getById(rsMatchs.getInt(1)).get());
				}
				
				return Optional.of(t);
			}
		}
		return Optional.empty();
	}
	
	/**
	 * ajoute un tournoi à la bdd
	 * @param identifiant(s) de l'arbitre
	 * */
	public boolean add(TournoiModele value) throws Exception {
		
		PreparedStatement st = DBConnection.getInstance().prepareStatement("SELECT NEXT VALUE FOR seqIdTournoi FROM dual");
		ResultSet rs = st.executeQuery();
		int id = 0;
		if (rs.next()) {
			id = rs.getInt(1);
		}

		value.setIDTournoi(id);
		
		for (Match m : value.getMatchs()) {
			m.setIdTournoi(id);
		}
		
		st = DBConnection.getInstance().prepareStatement("INSERT INTO tournoi (idTournoi, nom, dateDebut, dateFin, notoriete, ouvert, login, mdp) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		st.setInt(1, id); 
		st.setString(2, value.getNomTournoi());
		st.setDate(3, value.getDateDebut()); 
		st.setDate(4, value.getDateFin());
		st.setString(5, value.getNotoriete().toString());
		st.setString(6, value.getEtatTournoi().toString());
		st.setString(7, value.getLogin());
		st.setString(8, value.getMotDePasse());
		int rowcount = st.executeUpdate();
		
		for (Match m : value.getMatchs()) {
			MatchDAO.getInstance().add(m);
		}
		
		for (Equipe e : value.getParticipants().keySet()) {
			ParticiperDAO.getInstance().add(new Participer(value.getParticipants().get(e), id, e.getIdEquipe()));
		}
		
		for (Arbitre a : value.getArbitres()) {
			st = DBConnection.getInstance().prepareStatement("INSERT INTO gerer VALUES(?,?)");
			st.setInt(1, value.getIDTournoi()); st.setInt(2, a.getIdArbitre());
			st.executeUpdate();
		}
		
		return rowcount > 0;
	}
	
	/**
	 * mettre à jour un tournoi
	 * @param tournoi à maj
	 * */
	public boolean update(TournoiModele value) throws Exception {
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
	    
	    PreparedStatement preparedStatement = DBConnection.getInstance().prepareStatement(query);

        // Paramétrage des valeurs dans la requête
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
        
        for (Match m : value.getMatchs()) {
			MatchDAO.getInstance().update(m);
		}
		
		for (Equipe e : value.getParticipants().keySet()) {
			if (value.getParticipants().containsKey(e)) {
				ParticiperDAO.getInstance().update(new Participer(value.getParticipants().get(e), value.getIDTournoi(), e.getIdEquipe()));	
			}
		}
		
		List<Arbitre> arbitres = new ArrayList<Arbitre>();
		arbitres.addAll(value.getArbitres());
		
		PreparedStatement stArbitres = DBConnection.getInstance().prepareStatement("SELECT idArbitre FROM Gerer WHERE idTournoi = ?");
		stArbitres.setInt(1, value.getIDTournoi());
		ResultSet rsArbitres = stArbitres.executeQuery();
		
		while (rsArbitres.next()) {
			arbitres.remove(ArbitreDAO.getInstance().getById(rsArbitres.getInt(1)).get());
		}
		
		stArbitres = DBConnection.getInstance().prepareStatement("DELETE FROM Gerer WHERE idTournoi = ? AND idArbitre = ?");
		stArbitres.setInt(1, value.getIDTournoi());
		
		for (Arbitre a : arbitres) {
			stArbitres.setInt(2, a.getIdArbitre());
			stArbitres.executeUpdate();
		}

        int rowcount = preparedStatement.executeUpdate();
        return rowcount > 0;
	}
	
	/**
	 * supprime de la bdd un tournoi donné
	 * @param tournoi à supp
	 * */
	public boolean delete(TournoiModele value) throws Exception {
		PreparedStatement st = DBConnection.getInstance().prepareStatement("DELETE FROM tournoi WHERE idTournoi=?");
		st.setInt(1, value.getIDTournoi());
		//Statement st = DBConnection.getInstance().createStatement();
		int rowcount = st.executeUpdate();
		return rowcount > 0;
	}
	
	/**
	 * renvoie le tournoi actuellement ouvert (ou un empty si aucun tournoi n'est ouvert)
	 * */
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
	 * @param tournoi où ajouter l'arbitre
	 * @param arbitre à ajouter
	 * */
	public boolean addArbitre(TournoiModele t, Arbitre a) throws Exception {

		PreparedStatement stAddGerer = DBConnection.getInstance().prepareStatement("INSERT INTO gerer VALUES (?,?)");
		stAddGerer.setInt(1, t.getIDTournoi()); stAddGerer.setInt(2, a.getIdArbitre());
		int rowcount = stAddGerer.executeUpdate();
		return rowcount > 0;
		
	}
	
	/**
	 * retire un arbitre d'un tournoi et renvoie si la suppression s'est faite
	 * @param tournoi où supprimer l'arbitre
	 * @param arbitre à supprimer
	 * */
	public boolean deleteArbitre(TournoiModele t, Arbitre a) throws Exception {
		
		PreparedStatement stDelGerer = DBConnection.getInstance().prepareStatement("DELETE FROM gerer WHERE idTournoi=? AND idArbitre = ?");
		stDelGerer.setInt(1, t.getIDTournoi()); stDelGerer.setInt(2, a.getIdArbitre());
		int rowcount = stDelGerer.executeUpdate();
		return rowcount > 0;
		
	}
	
	/**
	 * ajoute une équipe au tournoi et renvoie si l'ajout s'est fait
	 * @param tournoi où ajouter l'équipe
	 * @param équipe à ajouter
	 * */
	public boolean addEquipe (TournoiModele t, Equipe e) throws Exception {
		
		Participer p = new Participer(0, t.getIDTournoi(), e.getIdEquipe());
		return ParticiperDAO.getInstance().add(p);

	}
	
	/**
	 * supprimer une équipe au tournoi et renvoie si la suppressio s'est faite
	 * @param tournoi où supprimer l'équipe
	 * @param équipe à supprimer
	 * */
	public boolean deleteEquipe (TournoiModele t, Equipe e) throws Exception {
		
		Optional<Participer> p = ParticiperDAO.getInstance().getByIdTournoiIdEquipe(e.getIdEquipe(), t.getIDTournoi());
		if (p.isPresent()) {
			return ParticiperDAO.getInstance().delete(p.get());
		}
		return false;
		
	}

}
