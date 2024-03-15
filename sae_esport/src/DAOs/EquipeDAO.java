package DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import classes.DBConnection;
import classes.Disposition;
import classes.Equipe;
import classes.Joueur;
import classes.Nationalite;
import modeles.TournoiModele;

public class EquipeDAO {
	
	private static EquipeDAO instance;
	
	private EquipeDAO() {
	}
	
	public static synchronized EquipeDAO getInstance() {
		if (instance == null) {
			instance = new EquipeDAO();
		}
		return instance;
	}
	
	/**
	 * renvoie l'ensemble des équipes
	 * @renvoie la liste des équipes
	 * */
	public List<Equipe> getAll() {
		
		ArrayList<Equipe> equipes = new ArrayList<Equipe>();
		
		try {
			
			PreparedStatement st = DBConnection.getInstance().prepareStatement("SELECT * FROM equipe");
			ResultSet rs = st.executeQuery();

			//pour chaque équipe...
			while (rs.next()) {
				
				//récupération de tous les joueurs
				PreparedStatement reqSelectJoueursequipe = DBConnection.getInstance()
						.prepareStatement("SELECT * FROM joueur WHERE idEquipe = ?");
				reqSelectJoueursequipe.setInt(1, rs.getInt(1));
				ResultSet res = reqSelectJoueursequipe.executeQuery();
				List<Joueur> joueurs = new ArrayList<Joueur>();
				while (res.next()) {
					joueurs.add(new Joueur(res.getInt(1),res.getString(2),res.getInt(3)));
				}
				
				equipes.add(new Equipe(rs.getInt(1),rs.getString(2),classes.Nationalite.valueOf(rs.getString(3)),joueurs,Disposition.valueOf(rs.getString(6)),rs.getInt(4),rs.getInt(5)));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return equipes;
	}
	
	/**
	 * renvoie l'équipe du premier id reconnu
	 * @param identifiant(s) de l'équipe
	 * @return l'optional englobant l'équipe séléctionné
	 * */
	public Optional<Equipe> getById(Integer... id) {

		try {
			
			PreparedStatement st = DBConnection.getInstance()
					.prepareStatement("SELECT * FROM equipe WHERE idEquipe=?");
		
			for (Integer i : id) {
				
				st.setInt(1, i);
				ResultSet rs = st.executeQuery();
				
				if (rs.next()) {
					PreparedStatement reqSelectJoueursequipe = DBConnection.getInstance().prepareStatement("SELECT * FROM joueur WHERE idEquipe = ?");
					reqSelectJoueursequipe.setInt(1, rs.getInt(1));
					ResultSet res = reqSelectJoueursequipe.executeQuery();
					List<Joueur> joueurs = new ArrayList<Joueur>();
					while (res.next()) {
						joueurs.add(new Joueur(res.getInt(1),res.getString(2),res.getInt(3)));
					}
					return Optional.of(new Equipe(rs.getInt(1),rs.getString(2),classes.Nationalite.valueOf(rs.getString(3)),joueurs,Disposition.valueOf(rs.getString(6)),rs.getInt(4),rs.getInt(5)));
				}
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Optional.empty();
	}
	
	/**
	 * ajoute une équipe à la bdd
	 * @param équipe à ajouter
	 * @return renvoie le booléen indiquant si l'ajout a été fait
	 * */
	public boolean add(Equipe value) {
		
		int rowcount = 0;

		try {
			
			PreparedStatement st = DBConnection.getInstance().prepareStatement("SELECT NEXT VALUE FOR seqIdEquipe FROM dual");
			ResultSet rs = st.executeQuery();
			int id = 0;
			if (rs.next()) {
				id = rs.getInt(1);
			}
			value.setIdEquipe(id);
			
			st = DBConnection.getInstance().prepareStatement("INSERT INTO equipe VALUES (?,?,?,?,?,?)");
			st.setInt(1, id); 
			st.setString(2, value.getNom()); 
			st.setString(3, value.getNationalite().toString());
			st.setString(6, value.getDisposition().toString()); 
			st.setInt(4, value.getRangSaisonPrecedante()); 
			st.setInt(5, value.getPointsSaison());
			List<Joueur> lj = value.getListeJoueurs();
			for (int i=0;i<lj.size();i++) {
				JoueurDAO.getInstance().add(lj.get(i));
			}
			
			rowcount = st.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowcount > 0;
		
	}
	
	/**
	 * importe des équipes depuis un fichier CSV
	 * @param fichier CSV
	 * @return la liste des équipes dans le fichier CSV
	 * */
	public List<Equipe > importEquipes(File file) {
				
		FileReader fr;	BufferedReader bfr;
		List<Equipe> equipesTournoi = new ArrayList<Equipe>();
		
		try {
			
			fr = new FileReader(file);
			bfr= new BufferedReader(fr);
				
			List<String[] > data = new ArrayList<String[] >();

			String nextLine = null;
			while ((nextLine = bfr.readLine()) != null) {
				String s = nextLine;
				data.add(s.split(","));
			}
			bfr.close();
				
		List<Equipe> listEquipes = getAll();
		List<Joueur> listJoueurs = JoueurDAO.getInstance().getAll();
				
		for (String[] s : data) {
					
			Nationalite nat = Nationalite.valueOf(s[1]);
			
			List<Joueur> joueursEquipe = new ArrayList<Joueur>();
					
			Equipe e = new Equipe(0,s[0],nat,joueursEquipe,Disposition.DISPOSEE,Integer.parseInt(s[2]),Integer.parseInt(s[3]));
			equipesTournoi.add(e);
			
			boolean nouvelleEquipe = true;
			for (Equipe eq : listEquipes) {
				if (e.getNom().equals(eq.getNom())) {
					nouvelleEquipe = false;
					e.setIdEquipe(eq.getIdEquipe());
				}
			}
					
			if (nouvelleEquipe) {
				add(e);
			}
					
			int id = e.getIdEquipe();
					
			for (int i=4;i<=8;i++) {
				
				Joueur j = new Joueur(0,s[i],id);
				boolean nouveauJoueur = true;
				for (Joueur jo : listJoueurs) {
					if (jo.getPseudo().equals(j.getPseudo())) {
						nouveauJoueur = false;
						j.setIDJoueur(jo.getIdJoueur());
					}
				}
				
				if (nouveauJoueur) {
					JoueurDAO.getInstance().add(j);
					joueursEquipe.add(j);
					e.ajouterJoueur(j);
				}
						
			}
					
		}	
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return equipesTournoi;
		
	}
	
	/**
	 * mettre à jour une équipe
	 * @param équipe à maj
	 * @return booléen indiquant si la mise à jour a été faite 
	 * */
	public boolean update(Equipe value) {
		
		int rowcount = 0;
		
		try {
			
			//maj de l'équipe
			PreparedStatement st = DBConnection.getInstance()
					.prepareStatement("UPDATE equipe SET nom=?, nationalite=?, disposee=?, "
							+ "rangSaisonPrecedente=?, pointssaison=? WHERE idequipe=?");
		
			st.setString(1, value.getNom()); 
			st.setString(2, value.getNationalite().toString()); 
			st.setString(3, value.getDisposition().toString()); 
			st.setInt(4, value.getRangSaisonPrecedante()); 
			st.setInt(5, value.getPointsSaison());
			st.setInt(6, value.getIdEquipe());
			
			//maj des joueurs de l'équipe
			List<Joueur> lj = value.getListeJoueurs();
			for (int i=0;i<lj.size();i++) {
				JoueurDAO.getInstance().update(lj.get(i));
			}
		
			rowcount = st.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowcount > 0;
		
	}
	
	/**
	 * supprimer une équipe de la bdd
	 * @param équipe à supprimer
	 * @return booléen indiquant si la suppression a eu lieu
	 * */
	public boolean delete(Equipe value){
		
		int rowcount = 0;
		
		try {
			
			PreparedStatement st = DBConnection.getInstance()
					.prepareStatement("DELETE FROM equipe WHERE idEquipe=?");
		
			st.setInt(1, value.getIdEquipe());
			rowcount = st.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowcount > 0;
		
	}
	
	/**
	 * inscrire une équipe à un tournoi
	 * @param equipe à inscrire
	 * @param tournoi où l'inscrire
	 * @return booléen indquant si l'inscription a eu lie u
	 * */
	public boolean inscrireEquipe(Equipe equipe, TournoiModele tournoi) {
		
		int rowcount = 0;
		
		try {
			
			PreparedStatement st = DBConnection.getInstance()
					.prepareStatement("INSERT INTO participer VALUES (0,?,?)");
			st.setInt(1, tournoi.getIDTournoi()); st.setInt(2, equipe.getIdEquipe());
			rowcount = st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowcount > 0;
	}
	
	/**
	 * renvoie la liste des tournois d'une équipe
	 * @param l'équipe à récupérer
	 * */
	public List<TournoiModele> getTournoisEquipe(Equipe e) {
		
		List<TournoiModele> tournois = new ArrayList<TournoiModele>();
		
		try {
			
			PreparedStatement st = DBConnection.getInstance()
					.prepareStatement("SELECT * FROM Participer WHERE idEquipe = ?");
			st.setInt(1, e.getIdEquipe());
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				
				if (TournoiDAO.getInstance().getById(rs.getInt(2)).isPresent()) {
					tournois.add(TournoiDAO.getInstance().getById(rs.getInt(2)).get());
				}
				
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		return tournois;
		
	}
	
}
