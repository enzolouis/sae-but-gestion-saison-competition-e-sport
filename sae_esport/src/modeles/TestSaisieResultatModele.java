package modeles;

import classes.Match;
import modeles.SaisieResultatModele;

public class TestSaisieResultatModele {
	
	public static SaisieResultatModele m;
	
	public static void main(String[] args) {
		TournoiModele t = new TournoiModele();
		Match ma = new Match(0, false);
		m = new SaisieResultatModele(t);
		if (m.IsFinaleDemarree()) {
			System.out.print("IsFinaleDemarree - tournoi vide erreur");
		}
		else {
			System.out.print("IsFinaleDemarree - tournoi vide OK");
		}
		t.nouveauMatch(ma.getIDMatch(),ma.IsItFinale());
		t.nouveauMatch(1,false);
		t.nouveauMatch(2, false);
		if (m.IsFinaleDemarree()) {
			System.out.print("IsFinaleDemarree - tournoi non vide sans finale erreur");
		}
		else {
			System.out.print("IsFinaleDemarree - tournoi non vide sans finale OK");
		}
		t.nouveauMatch(3, true);
		if (m.IsFinaleDemarree()) {
			System.out.print("IsFinaleDemarree - tournoi avec finale OK");
		}
		else {
			System.out.print("IsFinaleDemarree - tournoi avec finale erreur");
		}
	}
	
}
