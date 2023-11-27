package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import modeles.CreationTournoiModele;
import vues.CreationTournoiVue;

public class CreationTournoiControleur implements ActionListener {
	private CreationTournoiVue vue;
	private CreationTournoiModele modele;
	private Connection dbConnection;
	
	// remplacer identificationvue par CreationTournoiVue
	public CreationTournoiControleur(CreationTournoiVue vue, Connection dbConnection) {
		this.vue = vue;
		this.modele = new CreationTournoiModele(dbConnection);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
