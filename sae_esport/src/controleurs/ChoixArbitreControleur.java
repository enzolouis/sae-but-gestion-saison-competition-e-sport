package controleurs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;

import vues.ChoixArbitreVue;

public class ChoixArbitreControleur implements ActionListener {

	private ChoixArbitreVue vue;
	
	public ChoixArbitreControleur(ChoixArbitreVue vue) throws Exception {
		this.vue = vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bouton = (JButton) e.getSource();
		if (bouton.getText().equals("Quitter")) {
			this.vue.closeCurrentWindow();
		}
	}
	
}
