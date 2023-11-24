package controleurs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;

import modeles.AccueilAdministrateurModele;
import vues.AccueilAdministrateurVue;

public class AccueilAdministrateurControleur implements ActionListener {
	
	private AccueilAdministrateurVue vue;
	private AccueilAdministrateurModele modele;
	private Connection dbConnection;
	
	public AccueilAdministrateurControleur(AccueilAdministrateurVue vue, Connection dbConnection) throws Exception {
		this.vue = vue;
		this.dbConnection = dbConnection;
		this.modele = new AccueilAdministrateurModele(dbConnection);
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
