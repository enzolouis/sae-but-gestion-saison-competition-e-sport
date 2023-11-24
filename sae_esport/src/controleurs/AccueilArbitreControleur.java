package controleurs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import modeles.AccueilArbitreModele;
import vues.AccueilArbitreVue;

public class AccueilArbitreControleur implements ActionListener {
	
	private AccueilArbitreVue vue;
	private AccueilArbitreModele modele;
	private Connection dbConnection;
	
	public AccueilArbitreControleur(AccueilArbitreVue vue, Connection dbConnection) throws Exception {
		this.vue = vue;
		this.dbConnection = dbConnection;
		this.modele = new AccueilArbitreModele(dbConnection);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("controleur page administrateur");
	}

}
