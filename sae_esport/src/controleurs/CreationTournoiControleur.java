package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import classes.Arbitre;
import modeles.CreationTournoiModele;
import vues.CreationTournoiVue;

public class CreationTournoiControleur implements ActionListener {
	private CreationTournoiVue vue;
	private CreationTournoiModele modele;
	
	// remplacer identificationvue par CreationTournoiVue
	public CreationTournoiControleur(CreationTournoiVue vue) {
		this.vue = vue;
		this.modele = new CreationTournoiModele();
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
				JFileChooser fc = new JFileChooser();
				fc.setAcceptAllFileFilterUsed(false);
				fc.addChoosableFileFilter(new FileNameExtensionFilter("CSV Documents", "csv"));
				int chose = fc.showOpenDialog(this.vue);
				if (chose == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					this.vue.textFieldEquipesFile.setText(file.getName());
				}
				break;
			case ("Quitter"):
				this.vue.dispose();
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
