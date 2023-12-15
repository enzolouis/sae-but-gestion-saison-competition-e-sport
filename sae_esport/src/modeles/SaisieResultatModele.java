package modeles;

import java.sql.Date;

import DAOs.MatchDAO;
import classes.DBConnection;
import classes.Match;
import modeles.TournoiModele;

public class SaisieResultatModele {
	
	private TournoiModele tournoi;
	
	public SaisieResultatModele(TournoiModele tournoi) {
		this.tournoi = tournoi;
	}
	
	public boolean IsFinaleDemarree() {
		for (Match i : tournoi.getMatchs()) {
			if (i.IsItFinale()) {
				return true;
			}
		}
		return false;
	}
	
	
}
