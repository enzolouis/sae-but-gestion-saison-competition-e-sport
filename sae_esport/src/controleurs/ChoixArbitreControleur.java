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
	private Connection dbConnection;
	
	public ChoixArbitreControleur(ChoixArbitreVue vue, Connection dbConnection) throws Exception {
		this.vue = vue;
		this.dbConnection = dbConnection;
		this.modele = new ChoixArbitreModele(dbConnection);
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
