package DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import classes.Arbitre;
import classes.DBConnection;
import classes.Equipe;
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
	
	//Renvois l'ensemble des arbitres
	public List<TournoiModele> getAll() throws Exception {
		
		ArrayList<TournoiModele> tournois = new ArrayList<>();
		String reqSelectTournoi = "SELECT * FROM tournoi";
		PreparedStatement st = DBConnection.getInstance().prepareStatement(reqSelectTournoi);
		ResultSet rs = st.executeQuery();
		
		String reqSelectParticipants = "SELECT idEquipe, resultat FROM Participer WHERE idTournoi = ?";
		PreparedStatement stParticipants = DBConnection.getInstance().prepareStatement(reqSelectParticipants);
		
		PreparedStatement stArbitres = DBConnection.getInstance().prepareStatement("SELECT idArbitre FROM Gerer WHERE idTournoi = ?");
		
		while (rs.next()) {
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
		    SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
			TournoiModele t = new TournoiModele(rs.getInt(1), rs.getString(2), outputFormat.format(inputFormat.parse(rs.getString(3))), outputFormat.format(inputFormat.parse(rs.getString(4))), rs.getString(5), rs.getString(6), classes.Notoriete.valueOf(rs.getString(7)), classes.EtatTournoi.valueOf(rs.getString(8)));
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
			
			tournois.add(t);
		}
		return tournois;
	}
	
	//retourne un Arbitre specifique
	public Optional<TournoiModele> getById(Integer... id) throws Exception {
		Statement st = DBConnection.getInstance().createStatement();
		for (Integer i : id) {
			ResultSet rs = st.executeQuery("SELECT * FROM tournoi WHERE idTournoi="+i);
			if (rs.next()) {
				SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
			    SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
				TournoiModele t = new TournoiModele(rs.getInt(1), rs.getString(2), outputFormat.format(inputFormat.parse(rs.getString(3))), outputFormat.format(inputFormat.parse(rs.getString(4))), rs.getString(5), rs.getString(6), classes.Notoriete.valueOf(rs.getString(7)), classes.EtatTournoi.valueOf(rs.getString(8)));
				
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
				
				return Optional.of(t);
			}
		}
		return Optional.empty();
	}
	
	//ajoute un arbitre à la liste
	public boolean add(TournoiModele value) throws Exception {
		
		PreparedStatement st = DBConnection.getInstance().prepareStatement("SELECT NEXT VALUE FOR seqIdTournoi FROM dual");
		ResultSet rs = st.executeQuery();
		int id = 0;
		if (rs.next()) {
			id = rs.getInt(1);
		}
		System.out.println(id);
		value.setIDTournoi(id);
		
		st = DBConnection.getInstance().prepareStatement("INSERT INTO tournoi (idTournoi, nom, dateDebut, dateFin, notoriete, ouvert, login, mdp) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		st.setInt(1, id); 
		st.setString(2, value.getNomTournoi());
		st.setDate(3, value.getDateDebut()); 
		st.setDate(4, value.getDateFin());
		st.setString(5, value.getLogin());
		st.setString(6, value.getMotDePasse());
		st.setString(7, value.getNotoriete().toString());
		st.setString(8, value.getEtatTournoi().toString());
		int rowcount = st.executeUpdate();
		return rowcount > 0;
	}
	
	//update un arbitre donné
	public boolean update(TournoiModele value) throws Exception {
		Statement st = DBConnection.getInstance().createStatement();
		int rowcount = st.executeUpdate("UPDATE tournoi SET ");
		return rowcount > 0;
	}
	
	//retire un arbitre donné
	public boolean delete(TournoiModele value) throws Exception {
		PreparedStatement st = DBConnection.getInstance().prepareStatement("DELETE FROM tournoi WHERE idTournoi=?");
		st.setInt(1, value.getIDTournoi());
		//Statement st = DBConnection.getInstance().createStatement();
		int rowcount = st.executeUpdate();
		return rowcount > 0;
	}
	
	public Optional<TournoiModele> getTournoiOuvert() throws Exception {
		Statement st = DBConnection.getInstance().createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM tournoi WHERE ouvert='OUVERT'");
		if (rs.next()) {
			return Optional.of(new TournoiModele(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), classes.Notoriete.valueOf(rs.getString(7)), classes.EtatTournoi.valueOf(rs.getString(8))));
		}
		return Optional.empty();
	}
	
	public boolean addArbitre(TournoiModele t, Arbitre a) throws Exception {

		PreparedStatement stAddGerer = DBConnection.getInstance().prepareStatement("INSERT INTO gerer VALUES (?,?)");
		stAddGerer.setInt(1, t.getIDTournoi()); stAddGerer.setInt(2, a.getIdArbitre());
		int rowcount = stAddGerer.executeUpdate();
		return rowcount > 0;
		
	}
	
	public boolean deleteArbitre(TournoiModele t, Arbitre a) throws Exception {
		
		PreparedStatement stDelGerer = DBConnection.getInstance().prepareStatement("DELETE FROM gerer WHERE idTournoi=? AND idArbitre = ?");
		stDelGerer.setInt(1, t.getIDTournoi()); stDelGerer.setInt(2, a.getIdArbitre());
		int rowcount = stDelGerer.executeUpdate();
		return rowcount > 0;
		
	}
	
	public boolean addEquipe (TournoiModele t, Equipe e) throws Exception {
		PreparedStatement stAddParticiper = DBConnection.getInstance().prepareStatement("INSERT INTO participer VALUES(0,?,?)");
		stAddParticiper.setInt(1,t.getIDTournoi()); stAddParticiper.setInt(2, e.getIdEquipe());
		int rowcount = stAddParticiper.executeUpdate();
		return rowcount > 0;
	}
	
	public boolean deleteEquipe (TournoiModele t, Equipe e) throws Exception {
		
		PreparedStatement stDelEquipe = DBConnection.getInstance().prepareStatement("DELETE FROM participer WHERE idTournoi=? AND idEquipe = ?");
		stDelEquipe.setInt(1, t.getIDTournoi()); stDelEquipe.setInt(2, e.getIdEquipe());
		int rowcount = stDelEquipe.executeUpdate();
		return rowcount > 0;
		
	}

}
