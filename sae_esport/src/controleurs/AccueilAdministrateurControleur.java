package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import style.CustomJFrame;

import vues.AccueilAdministrateurVue;
import vues.ChoixArbitreVue;
import vues.ConsultationSaisonVue;
import vues.CreationTournoiVue;
import vues.IdentificationVue;
import vues.ListeTournoisVue;

public class AccueilAdministrateurControleur implements ActionListener {
	
	private AccueilAdministrateurVue vue;
	
	/*
	 * Effectue la construction de la vue
	 * @param vue de la page, permettant l'activation de ActionEvent
	 * */
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
				
				this.vue.closeCurrentWindow();
				break;
				
			case "Se déconnecter":
				
				this.vue.closeCurrentWindow();
				frame = new IdentificationVue();
				frame.setVisible(true);
				break;
				
			case ("Créer un tournoi"):
				
				this.vue.closeCurrentWindow();
				frame = new CreationTournoiVue();
				frame.setVisible(true);
				break;
				
			case ("Statistiques de la saison"):
				
				this.vue.closeCurrentWindow();
				frame = new ConsultationSaisonVue();
				frame.setVisible(true);
				break;
				
			case ("Liste des tournois"):
				
				this.vue.closeCurrentWindow();
				frame = new ListeTournoisVue();
				frame.setVisible(true);
				break;
				
			case ("Gérer les arbitres"):
				
				this.vue.closeCurrentWindow();
				frame = new ChoixArbitreVue();
				frame.setVisible(true);
				break;
				
			default:
				break;
			}
	
		}
	}

}
