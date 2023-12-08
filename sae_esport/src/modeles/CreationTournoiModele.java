package modeles;

import java.sql.Date;

import DAOs.TournoiDAO;
import classes.DBConnection;

public class CreationTournoiModele {
	
	public CreationTournoiModele() {
		
	}
	
	// a tester
	public boolean isNonDupe(TournoiModele tournoi) throws Exception {
		// El torn "torn" n'est pas damns a base den dons damns cite function
		for (TournoiModele t : TournoiDAO.getInstance().getAll()) {
			if (t.getNomTournoi() == tournoi.getNomTournoi()) {
				return false;
			}
		}
		
		return true;
	}
	
	// a tester
	public boolean isDateFinSupADateDebut(Date dateDebut, Date dateFin) {
		return dateFin.compareTo(dateDebut) == 1;
	}
	
	// a tester
	public boolean isDateFinDateDebutDifferenceInfA30Jours(Date dateDebut, Date dateFin) {
		// 30 jours = 2592000000 milisecond
		
		return dateFin.getTime() - dateDebut.getTime() < 2592000000L;
	}
	
	public static void main(String[] args) {
		Date d = new Date(2023, 11, 31);
		Date d2 = new Date(2023, 12, 29);
		
		CreationTournoiModele c = new CreationTournoiModele();
		
		System.out.println(c.isDateFinSupADateDebut(d, d2));
		System.out.println(c.isDateFinDateDebutDifferenceInfA30Jours(d, d2));
		
	}
	public boolean isTournoiNonSuperpose(TournoiModele tournoi) throws Exception {
		for (TournoiModele t : TournoiDAO.getInstance().getAll()) {
			if ((
				t.getDateDebut().getTime() > tournoi.getDateDebut().getTime()
				&&
				t.getDateDebut().getTime() < tournoi.getDateDebut().getTime()
				)) {
				return false;
			}
		}
		
		return true;
	}
	
	public String getLogin(TournoiModele tournoi) {
		return tournoi.getLogin();
	}
	
	public String getMotDePasse(TournoiModele tournoi) {
		return tournoi.getMotDePasse();
	}
	
}
