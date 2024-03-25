package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import DAOs.TournoiDAO;
import DAOs.EquipeDAO;
import classes.Arbitre;
import classes.EtatTournoi;
import classes.Notoriete;
import classes.Equipe;
import modeles.TournoiModele;
import style.Palette;
import vues.CreationTournoiVue;

public class CreationTournoiControleur implements ActionListener {
	
	private CreationTournoiVue vue;	//vue de la création de tournoi
	private TournoiModele modele;	//modèle de gestion des tournois 
	private List<Equipe> data;		//liste des équipes importées dans le tournoi
	
	public CreationTournoiControleur(CreationTournoiVue vue) {
		this.vue = vue;
		this.modele = new TournoiModele();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() instanceof JButton) {
			
			JButton bouton = (JButton) e.getSource();
			
			switch (bouton.getActionCommand()) {
				case "quitter":
					this.vue.closeCurrentWindow();
					break;
				case "ajouter":
					ajouterArbitreSelectionne();
					break;
				case "vider":
					viderListeArbitres();
					break;
				case "supprimer":
					supprimerArbitreSelectionne();
					break;
				case "importer":
					importFichierCSV();
					break;
				case "valider":
					validerCreationTournoi();
					break;
				}
		}
	}

	
	/**
	 * vérifie si un des fields est vide
	 * */
	public boolean checkForEmptyField() {

		return (this.vue.textFieldNom.getText().equals("") || this.vue.dateChooserDebut.getDate() == null 
				|| this.vue.dateChooserFin.getDate() == null || this.vue.textFieldEquipesFile.getText().equals("")
				|| this.vue.comboBoxArbitre.getItemCount() == 0);
	}
	
	/**
	 * renvoie la liste complète des informations pour un tournoi
	 * @param le tournoi à consulter
	 * */
	public String infosTournoi(TournoiModele t) {
		String infos = t.getIDTournoi()+": "+t.getNomTournoi()+"("+t.getNotoriete()+")\n";
		infos += "Du "+t.getDateDebut()+" au "+t.getDateFin()+"\n";
		infos+="Liste des arbitres:\n";
		for (Arbitre a : t.getArbitres()) {
			infos+=a.toString();
			infos+="\n";
		}
		infos+="Liste des équipes:\n";
		for (Equipe e : t.getParticipants().keySet()) {
			infos+=e.toString();
			infos+="\n";
		}
		return infos;
	}
	
}
