package controleurs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;

import modeles.ChoixArbitreModele;
import vues.ChoixArbitreVue;

public class ChoixArbitreControleur implements ActionListener {

	private ChoixArbitreVue vue;
	private ChoixArbitreModele modele;
	
	public ChoixArbitreControleur(ChoixArbitreVue vue) throws Exception {
		this.modele = new ChoixArbitreModele();
		this.vue = vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bouton = (JButton) e.getSource();
		if (bouton.getText().equals("Quitter")) {
			this.vue.setVisible(false);
			this.vue.dispose();
		} else if (bouton.getText().equals("sdqz")) {
			
		}
	}
	
}
