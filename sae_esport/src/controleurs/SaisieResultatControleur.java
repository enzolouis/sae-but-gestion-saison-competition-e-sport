package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.swing.JButton;
import classes.ClotureDatePassee;
import classes.Match;
import modeles.SaisieResultatModele;
import modeles.TournoiModele;
import style.CustomJButton;
import vues.SaisieResultatVue;
import DAOs.MatchDAO;
import DAOs.TournoiDAO;

public class SaisieResultatControleur implements ActionListener {

	private SaisieResultatModele modele;
	private SaisieResultatVue vue;
	public Timer timer;
	private FINALESTATE stateTournoi;

	public enum FINALESTATE {
		NOT_FINALE, IS_FINALE, FINALE_OVER
	}

	/**
	 * Mise en place de la vue, pour permettre les réactions avec Action Listener
	 * 
	 * @param Valeur retourné
	 * @param les    paramètres de tournoi à manipuler dans cette vue
	 */
	public SaisieResultatControleur(SaisieResultatVue vue, TournoiModele tournoi) {

		this.stateTournoi = FINALESTATE.NOT_FINALE;
		this.vue = vue;
		this.modele = new SaisieResultatModele();

		Date dt = tournoi.getDateFin();

		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, 1);
		dt = c.getTime();

		timer = new Timer();
		timer.schedule(new ClotureDatePassee(this), dt);

		if (modele.isFinaleDemarree()) {
			this.stateTournoi = FINALESTATE.IS_FINALE;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {

			JButton bouton = (JButton) e.getSource();

			switch (bouton.getActionCommand()) {
				case "quitter":
					this.vue.dispose();
					this.vue.closeCurrentWindow();
					break;
				case "ouvrirFinale":
					ouvrirFinale();
					break;
				case "fermerTournoi":
					fermerTournoi();
					break;
				default:
					choisirVainqueur(bouton);
			}
		}
	}

	private void choisirVainqueur(JButton bouton) {
		modele.choixVainqueurMatch(bouton);

		this.vue.RefreshMatch((CustomJButton) bouton);
		if (stateTournoi == FINALESTATE.IS_FINALE) {
			stateTournoi = FINALESTATE.FINALE_OVER;
		}
	}

	private void fermerTournoi() {
		if (stateTournoi == FINALESTATE.FINALE_OVER) {
			modele.finirTournoi();
		} else {
			modele.annulerTournoi();
		}
		this.vue.disableButtons();
		timer.cancel();
	}

	private void ouvrirFinale() {

		if (modele.canOpenFinale()) {

			Match finale = modele.createFinale();
			MatchDAO.getInstance().add(finale);
			this.vue.OpenButtonFinal(finale.getIDMatch(),
					finale.getEquipes().get(0), finale.getEquipes().get(1));
			this.stateTournoi = FINALESTATE.IS_FINALE;

			TournoiDAO.getInstance().update(this.modele.getTournoi());

		}

	}

	/**
	 * Retourne le résultat attendus du modèle
	 */
	public SaisieResultatModele getModele() {
		return this.modele;
	}
}
