package controleurs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;

import modeles.AccueilAdministrateurModele;
import vues.AccueilAdministrateurVue;
import vues.CreationTournoiVue;

public class AccueilAdministrateurControleur implements ActionListener {
	
	private AccueilAdministrateurVue vue;
	private AccueilAdministrateurModele modele;
	
	public AccueilAdministrateurControleur(AccueilAdministrateurVue vue) {
		this.vue = vue;
		this.modele = new AccueilAdministrateurModele();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton bouton = (JButton) e.getSource();
			switch (bouton.getText()) {
			case ("Quitter"):
				this.vue.setVisible(false);
				this.vue.dispose();
				break;
			case ("Créer un tournoi"):
				CreationTournoiVue frame = new CreationTournoiVue();
				frame.setVisible(true);
			default:
				break;
			}
		}
	}

}
