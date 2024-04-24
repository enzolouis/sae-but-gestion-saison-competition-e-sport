package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import DAOs.TournoiDAO;
import modeles.TournoiModele;
import style.CustomJFrame;
import vues.AccueilArbitreVue;
import vues.ConsultationTournoiVue;
import vues.IdentificationVue;
import vues.SaisieResultatVue;

public class AccueilArbitreControleur implements ActionListener {

	private AccueilArbitreVue vue;

	/**
	 * Effectue la construction de la vue
	 * 
	 * @param vue de la page, permettant l'activation de ActionEvent
	 */
	public AccueilArbitreControleur(AccueilArbitreVue vue) {
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton bouton = (JButton) e.getSource();

			switch (bouton.getActionCommand()) {
				case "quitter":
					this.vue.closeCurrentWindow();
					break;

				case "deconnecter":
					deconnecter();
					break;

				case "accesTournoi":
					ouvrirAccesTournoi();
					break;

				case "statsTournoi":
					ouvrirStatistiquesTournoi();
					break;

				default:
					break;
			}
		}
	}

	private void ouvrirStatistiquesTournoi() {
		CustomJFrame frame;
		this.vue.closeCurrentWindow();
		TournoiModele t = null;
		try {
			t = TournoiDAO.getInstance().getTournoiOuvert().get();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		frame = new ConsultationTournoiVue(t);
		frame.setVisible(true);
	}

	private void ouvrirAccesTournoi() {
		CustomJFrame frame;
		this.vue.closeCurrentWindow();
		try {
			frame = new SaisieResultatVue();
			frame.setVisible(true);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void deconnecter() {
		CustomJFrame frame;
		this.vue.closeCurrentWindow();
		frame = new IdentificationVue();
		frame.setVisible(true);
	}
}
