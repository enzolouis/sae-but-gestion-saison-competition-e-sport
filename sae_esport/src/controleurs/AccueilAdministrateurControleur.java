package controleurs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import style.CustomJFrame;
import vues.AccueilAdministrateurVue;
import vues.ConsultationSaisonVue;
import vues.CreationTournoiVue;
import vues.ListeTournoisVue;

public class AccueilAdministrateurControleur implements ActionListener {
	
	private AccueilAdministrateurVue vue;
	
	public AccueilAdministrateurControleur(AccueilAdministrateurVue vue) {
		this.vue = vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton bouton = (JButton) e.getSource();
			CustomJFrame frame;
			switch (bouton.getText()) {
			case ("Quitter"):
				this.vue.setVisible(false);
				this.vue.dispose();
				break;
			case ("Créer un tournoi"):
				frame = new CreationTournoiVue();
				frame.setVisible(true);
				break;
			case ("Statistiques de la saison"):
				frame = new ConsultationSaisonVue();
				frame.setVisible(true);
				break;
			case ("Liste des tournois"):
				frame = new ListeTournoisVue();
				frame.setVisible(true);
			default:
				break;
			}
		}
	}

}
