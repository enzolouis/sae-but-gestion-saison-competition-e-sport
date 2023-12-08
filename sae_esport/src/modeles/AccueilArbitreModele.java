package modeles;

import DAOs.ArbitreDAO;
import classes.Arbitre;
import classes.Nationalite;

public class AccueilArbitreModele {
	
	private ArbitreDAO ArbitreDAO;
	private Arbitre arbitre;
	
	public AccueilArbitreModele(int idarbitre, String nom, String prenom, Nationalite nationalite) {
		this.arbitre.setIdArbitre(idarbitre);
		this.arbitre.setNom(nom);
		this.arbitre.setPrenom(prenom);
		this.arbitre.setNationalite(nationalite);
		
		this.ArbitreDAO = new ArbitreDAO();
	}
	
	public void assignerMatch() {
		
	}
}
