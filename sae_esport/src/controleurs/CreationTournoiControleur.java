package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import DAOs.TournoiDAO;
import DAOs.EquipeDAO;
import classes.Arbitre;
import classes.EtatTournoi;
import classes.Notoriete;
import classes.Equipe;
import modeles.TournoiModele;
import vues.CreationTournoiVue;

public class CreationTournoiControleur implements ActionListener {
	private CreationTournoiVue vue;
	private TournoiModele modele;
	private List<Equipe> data;
	
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
					try {
						data = EquipeDAO.getInstance().importEquipes(file);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				break;
			case ("Quitter"):
				this.vue.dispose();
				break;
			case ("Valider"):
				if (this.vue.textFieldNom.getText().equals("") || this.vue.dateChooserDebut.getDate() == null 
				|| this.vue.dateChooserFin.getDate() == null || this.vue.textFieldEquipesFile.getText().equals("")
				|| this.vue.comboBoxArbitre.getItemCount() == 0) {
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
							for (int i = 0; i < this.vue.listArbitres.getModel().getSize(); i++) {
								TournoiDAO.getInstance().addArbitre(t, this.vue.listArbitres.getModel().getElementAt(i));
								t.ajouterArbitre(this.vue.listArbitres.getModel().getElementAt(i));
							}
							for (Equipe eq : data) {
								TournoiDAO.getInstance().addEquipe(t, eq);
								t.ajouterEquipe(eq, 0);
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							}
						JFrame jFrame = new JFrame();
						try {
							JOptionPane.showMessageDialog(jFrame, "Le tournoi N°"+t.getIDTournoi()+" "+t.getNomTournoi()+" a été ajouté"
									+ " à la base de données\n Login: "+t.getLogin()+"\n Mot de passe: "+t.getMotDePasse()
									+"\n Du "+t.getDateDebut()+" au "+t.getDateFin());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
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
	
	public String infosTournoi(TournoiModele t) {
		String infos = t.getIDTournoi()+": "+t.getNomTournoi()+"("+t.getNotoriete()+")\n";
		try {
			infos += "Du "+t.getDateDebut()+" au "+t.getDateFin()+"\n";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		infos+="Liste des arbitres:\n";
		for (Arbitre a : t.getArbitres()) {
			infos+=a.toString();
			infos+="\n";
		}
		infos+="Liste des équipes:\n";
		for (Equipe e : t.getEquipes().keySet()) {
			infos+=e.toString();
			infos+="\n";
		}
		return infos;
	}
	
}
