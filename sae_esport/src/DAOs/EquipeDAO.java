package DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import classes.DBConnection;
import classes.Equipe;
import classes.Joueur;
import classes.Nationalite;
import sqlrequests.CreateDual;

public class EquipeDAO {
	
	private static EquipeDAO instance;
	
	public EquipeDAO() {
		super();
	}
	
	public static synchronized EquipeDAO getInstance() {
		if (instance == null) {
			instance = new EquipeDAO();
		}
		return instance;
	}
	
	//Renvois l'ensemble des equipes
	public List<Equipe> getAll() throws Exception {
		String reqSelectEquipe = "SELECT * FROM equipe";
		PreparedStatement st = DBConnection.getInstance().prepareStatement(reqSelectEquipe);
		ResultSet rs = st.executeQuery();
		
		ArrayList<Equipe> equipes = new ArrayList<Equipe>();
		while (rs.next()) {
			PreparedStatement reqSelectJoueursequipe = DBConnection.getInstance().prepareStatement("SELECT * FROM joueur WHERE idEquipe = ?");
			reqSelectJoueursequipe.setInt(1, rs.getInt(1));
			ResultSet res = reqSelectJoueursequipe.executeQuery();
			List<Joueur> joueurs = new ArrayList<Joueur>();
			while (res.next()) {
				joueurs.add(new Joueur(res.getInt(1),res.getString(2),res.getInt(3)));
			}
			equipes.add(new Equipe(rs.getInt(1),rs.getString(2),classes.Nationalite.valueOf(rs.getString(3)),joueurs,rs.getBoolean(4),rs.getInt(5),rs.getInt(6)));
		}
		return equipes;
	}
	
	//ajoute un equipe à la liste
	//peu importe l'id que vous mettrez à l'equipe, il sera changé
	public boolean add(Equipe value) throws Exception {

		PreparedStatement st = DBConnection.getInstance().prepareStatement("SELECT NEXT VALUE FOR seqIdEquipe FROM dual");
		ResultSet rs = st.executeQuery();
		int id = 0;
		if (rs.next()) {
			id = rs.getInt(1);
		}
		System.out.println(id);
		value.setIdEquipe(id);
		st = DBConnection.getInstance().prepareStatement("INSERT INTO equipe VALUES (?,?,?,?,?,?)");
		st.setInt(1, id); 
		st.setString(2, value.getNom()); 
		st.setString(3, value.getNationalite().toString());
		st.setBoolean(4, value.getDisposition()); 
		st.setInt(5, value.getRangSaisonPrecedante()); 
		st.setInt(6, value.getPointsSaison());
		List<Joueur> lj = value.getListeJoueurs();
		JoueurDAO j = new JoueurDAO();
		for (int i=0;i<lj.size();i++) {
			j.add(lj.get(i));
		}
		
		return st.executeUpdate() > 0;
		
	}
	
	//ajoute des equipes d'un tableau css
			public List<Equipe > importEquipes(File file) throws Exception{
				
				FileReader fr;
				BufferedReader bfr;
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
				List<Equipe> equipesTournoi = new ArrayList<Equipe>();
				List<Joueur> listJoueurs = JoueurDAO.getInstance().getAll();
				
				for (String[] s : data) {
					
					Nationalite nat = Nationalite.valueOf(s[1]);
					
					List<Joueur> joueursEquipe = new ArrayList<Joueur>();
					
					Equipe e = new Equipe(0,s[0],nat,joueursEquipe,true,Integer.parseInt(s[2]),Integer.parseInt(s[3]));
					equipesTournoi.add(e);
					
					boolean nouvelleEquipe = true;
					for (Equipe eq : listEquipes) {
						if (e.getNom() == eq.getNom()) {
							nouvelleEquipe = false;
						}
					}
					
					if (nouvelleEquipe) add(e); int id = e.getIdEquipe();
					
					for (int i=4;i<=8;i++) {
						
						Joueur j = new Joueur(0,s[i],id);
						boolean nouveauJoueur = true;
						int idJ = 0;
						for (Joueur jo : listJoueurs) {
							if (jo.getPseudo() == j.getPseudo()) {
								nouveauJoueur = false;
								idJ = jo.getIdJoueur();
							}
						}
						
						if (nouveauJoueur) {
							JoueurDAO.getInstance().add(j);
							joueursEquipe.add(j);
						} else {
							j.setIDJoueur(idJ);
						}	
						
						e.AjouterJoueurs(j);
						
					}
					
				}
				
				return equipesTournoi;
			}
	
	//update un equipe donné
	public boolean update(Equipe value) throws Exception {
		
		PreparedStatement st = DBConnection.getInstance().prepareStatement("UPDATE equipe SET nom=?, nationalite=?, dispose=?, rangsaisonprecedante=?, pointssaison=? WHERE idequipe=?");
		st.setString(1, value.getNom()); 
		st.setObject(2, (Object) value.getNationalite()); 
		st.setBoolean(3, value.getDisposition()); 
		st.setInt(4, value.getRangSaisonPrecedante()); 
		st.setInt(5, value.getPointsSaison());
		st.setInt(6, value.getIdEquipe());
		List<Joueur> lj = value.getListeJoueurs();
		JoueurDAO j = new JoueurDAO();
		for (int i=0;i<lj.size();i++) {
			j.update(lj.get(i));
		}
		
		int rowcount = st.executeUpdate();
		return rowcount > 0;
		
	}
	
	//retire un equipe donné
	public boolean delete(Equipe value) throws Exception {
		
		PreparedStatement st = DBConnection.getInstance().prepareStatement("DELETE FROM equipe WHERE idEquipe=?");
		st.setInt(1, value.getIdEquipe());
		int rowcount = st.executeUpdate();
		return rowcount > 0;
	}
}
