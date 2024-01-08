package controleurs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import style.CustomJFrame;
import vues.AccueilArbitreVue;
import vues.ConsultationSaisonVue;
import vues.ConsultationTournoi;
import vues.CreationTournoiVue;

public class AccueilArbitreControleur implements ActionListener {
	
	private AccueilArbitreVue vue;
	
	public AccueilArbitreControleur(AccueilArbitreVue vue) {
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
			case ("Accès au tournoi"):
				break;
			case ("Statistiques du tournoi"):
				break;
			default:
				break;
			}
		}
	}

}
