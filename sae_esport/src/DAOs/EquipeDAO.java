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
	
	//Renvois l'ensemble des equipes
	public List<Equipe> getAll() throws Exception {
		String reqSelectEquipe = "SELECT * FROM equipe";
		PreparedStatement st = DBConnection.getInstance().prepareStatement(reqSelectEquipe);
		ResultSet rs = st.executeQuery();
		
		ArrayList<Equipe> equipes = new ArrayList<Equipe>();
		while (rs.next()) {
			String reqSelectJoueursequipe = "SELECT * FROM joueur WHERE rs.getInt(1) = Joueur.idEquipe";
			PreparedStatement sta = DBConnection.getInstance().prepareStatement(reqSelectJoueursequipe);
			ResultSet res = sta.executeQuery();
			List<Joueur> joueurs = new ArrayList<Joueur>();
			while (res.next()) {
				joueurs.add(new Joueur(rs.getInt(1),rs.getString(2)));
			}
			equipes.add(new Equipe(rs.getInt(1),rs.getString(2),classes.Nationalite.valueOf(rs.getString(3)),joueurs,rs.getBoolean(4),rs.getInt(5),rs.getInt(6)));
		}
		return equipes;
	}
	
	//ajoute un equipe à la liste
	//peu importe l'id que vous mettrez à l'equipe, il sera changé
	public boolean add(Equipe value) throws Exception {

		PreparedStatement st = DBConnection.getInstance().prepareStatement("SELECT NEXT VALUE FOR idEquipe FROM equipe");
		ResultSet rs = st.executeQuery();
		int id = 0;
		if (rs.next()) {
			id = rs.getInt(1);
		}
		value.setIdEquipe(id);
		st = DBConnection.getInstance().prepareStatement("INSERT INTO equipe VALUES (?,?,?,?,?,?)");
		st.setInt(1, id); 
		st.setString(2, value.getNom()); 
		st.setObject(3, (Object) value.getNationalite());
		st.setBoolean(4, value.getDisposition()); 
		st.setInt(5, value.getRangSaisonPrecedante()); 
		st.setInt(6, value.getPointsSaison());
		List<Joueur> lj = value.getListeJoueurs();
		JoueurDAO j = new JoueurDAO();
		for (int i=0;i<lj.size();i++) {
			j.add(lj.get(i));
		}
		
		int rowcount = st.executeUpdate();
		return rowcount > 0;
		
	}
	
	//ajoute des equipes d'un tableau css
			public List<String[] > importEquipes(String FILEPATH) throws Exception{
				File file;
				FileReader fr;
				BufferedReader bfr;
				
				file = new File(FILEPATH);
				fr = new FileReader(file);
				bfr= new BufferedReader(fr);
				
				
				List<String[] > data = new ArrayList<String[] >();

				String nextLine = null;
				while ((nextLine = bfr.readLine()) != null) {
				    String s = nextLine;
				    data.add(s.split(","));
				}
				bfr.close();
				
				List<Equipe> le= getAll();
				
				for (String[] s : data) {
					Nationalite n = Nationalite.valueOf(s[1]);
					Equipe e = new Equipe(0,s[0],n,true,Integer.parseInt(s[2]),Integer.parseInt(s[3]));
					boolean t = true;
					for (Equipe eq : le) {
						if (e.getNom() == eq.getNom()) {
							t = false;
						}
					}
					if (t) {
						add(e);
					}
				}
				return data;
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
