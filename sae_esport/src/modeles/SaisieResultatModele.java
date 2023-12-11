package modeles;

import java.sql.Date;

import DAOs.MatchDAO;
import classes.DBConnection;
import classes.Match;
import modeles.TournoiModele;

public class SaisieResultatModele {
	
	private Match match;
	private TournoiModele tournoi;
	
	public SaisieResultatModele(Match match, TournoiModele tournoi) {
		this.match = match;
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
