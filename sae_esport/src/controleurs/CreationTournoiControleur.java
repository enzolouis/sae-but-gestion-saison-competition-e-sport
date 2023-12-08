package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
<<<<<<< Updated upstream
=======
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
>>>>>>> Stashed changes

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import DAOs.TournoiDAO;
import classes.Arbitre;
import classes.DBConnection;
import classes.EtatTournoi;
import classes.Notoriete;
<<<<<<< Updated upstream
import classes.Tournoi;
=======
import modeles.CreationTournoiModele;
>>>>>>> Stashed changes
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
				if (this.vue.textFieldNom.getText().equals("") || this.vue.dateChooserDebut.getDate() == null 
				|| this.vue.dateChooserFin.getDate() == null) {
					JFrame jFrame = new JFrame();
					JOptionPane.showMessageDialog(jFrame, "Un des champs nécessaires n'a pas été rempli");
				} else {
					String nom = this.vue.textFieldNom.getText();
					String  dateDebut = this.modele.getDateString(this.vue.dateChooserDebut.getDate());
					String dateFin =   this.modele.getDateString(this.vue.dateChooserFin.getDate());
					Notoriete not = (Notoriete) this.vue.comboBoxNotoriete.getSelectedItem();
					TournoiModele t = new TournoiModele(0, nom, dateDebut, dateFin, not, EtatTournoi.FERME);
					if (!t.isTournoiValide()) {
						JFrame jFrame = new JFrame();
						JOptionPane.showMessageDialog(jFrame, "Le tournoi est invalide, vérifiez que les dates"
								+" ne soient pas invalides \n (date de fin antérieur à la date de début ou durée de "+
								"tournoi supérieure à 30 jours) \n ou qu'un tournoi n'existe pas sur ce créneau.");
					} else {
						try {
							TournoiDAO.getInstance().add(t);
							System.out.println(TournoiDAO.getInstance().getById(t.getIDTournoi()).get());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							}
						JFrame jFrame = new JFrame();
						JOptionPane.showMessageDialog(jFrame, "Le tournoi N°"+t.getIDTournoi()+" "+t.getNomTournoi()+" a été ajouté"
								+ " à la base de données\n Login: "+t.getLogin()+"\n Mot de passe: "+t.getMotDePasse());
						this.vue.dispose();
					}
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
