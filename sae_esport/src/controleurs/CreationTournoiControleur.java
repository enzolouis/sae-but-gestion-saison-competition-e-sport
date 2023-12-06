package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import DAOs.TournoiDAO;
import classes.Arbitre;
import classes.EtatTournoi;
import classes.Notoriete;
import classes.Tournoi;
import modeles.CreationTournoiModele;
import modeles.TournoiModele;
import vues.CreationTournoiVue;

public class CreationTournoiControleur implements ActionListener {
	private CreationTournoiVue vue;
	private TournoiModele modele;
	
	// remplacer identificationvue par CreationTournoiVue
	public CreationTournoiControleur(CreationTournoiVue vue) {
		this.vue = vue;
		this.modele = new TournoiModele();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton bouton = (JButton) e.getSource();
			switch (bouton.getText()) {
			case ("Ajouter"):
				Arbitre a = (Arbitre) this.vue.comboBoxArbitre.getSelectedItem();
				if (!this.vue.modeleList.contains(a)) {
					this.vue.modeleList.addElement(a);
				}
				break;
			case ("Vider"):
				this.vue.modeleList.removeAllElements();
				break;
			case ("Supprimer"):
				for (Arbitre arb : this.vue.listArbitres.getSelectedValuesList()) {
					this.vue.modeleList.removeElement(arb);
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
				if (!(this.vue.textFieldNom.getText().equals("") || this.vue.dateChooserDebut.getDate() == null
				|| this.vue.dateChooserFin.getDate() == null)) {
					String nom = this.vue.textFieldNom.getText();
					String  dateDebut = this.modele.dateChooserToString(this.vue.dateChooserDebut.getDate());
					String dateFin =  this.modele.dateChooserToString(this.vue.dateChooserFin.getDate());
					Notoriete not = (Notoriete) this.vue.comboBoxNotoriete.getSelectedItem();
						Tournoi t = new Tournoi(0, nom, dateDebut, dateFin, not, EtatTournoi.FERME);
						try {
							TournoiDAO.getInstance().add(t);
							System.out.println(TournoiDAO.getInstance().getById(t.getIDTournoi()));
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					JFrame jFrame = new JFrame();
					JOptionPane.showMessageDialog(jFrame, "Le tournoi "+t.getNomTournoi()+" a été ajouté"
							+ "à la base de données");
					this.vue.dispose();
				} else {
					JFrame jFrame = new JFrame();
					JOptionPane.showMessageDialog(jFrame, "Un des champs nécessaires n'a pas été rempli");
				}
				break;
			}
		}
	}
	
	public boolean checkAllFields() {
		return this.vue.textFieldEquipesFile.getText().isEmpty() ||
				this.vue.textFieldNom.getText().isEmpty();
	}
	
}
