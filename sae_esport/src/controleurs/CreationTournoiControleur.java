package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;

import classes.Arbitre;
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
		if (e.getSource() instanceof JButton) {
			JButton bouton = (JButton) e.getSource();
			switch (bouton.getText()) {
			case ("Ajouter"):
				this.vue.modeleList.addElement((Arbitre) this.vue.comboBoxArbitre.getSelectedItem());
				break;
			case ("Vider"):
				this.vue.modeleList.removeAllElements();
				break;
			case ("Supprimer"):
				for (Arbitre a : this.vue.listArbitres.getSelectedValuesList()) {
					this.vue.modeleList.removeElement(a);
				}
				break;
			case ("Importer"):
				if (!this.vue.textFieldEquipesFile.getText().isEmpty()) {
					//code d'ajout de l'importation des équipes
				}
				break;
			case ("Quitter"):
				break;
			case ("Valider"):
				break;
			}
		}
	}
	
	public boolean checkAllFields() {
		return this.vue.textFieldEquipesFile.getText().isEmpty() ||
				this.vue.textFieldNom.getText().isEmpty();
	}
	
}
