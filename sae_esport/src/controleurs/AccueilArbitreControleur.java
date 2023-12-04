package controleurs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;

import modeles.AccueilArbitreModele;
import vues.AccueilArbitreVue;

public class AccueilArbitreControleur implements ActionListener {
	
	private AccueilArbitreVue vue;
	private AccueilArbitreModele modele;
	
	public AccueilArbitreControleur(AccueilArbitreVue vue) {
		this.vue = vue;
		this.modele = new AccueilArbitreModele();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bouton = (JButton) e.getSource();
		if (bouton.getText().equals("Quitter")) {
			this.vue.setVisible(false);
			this.vue.dispose();
		}
	}

}
