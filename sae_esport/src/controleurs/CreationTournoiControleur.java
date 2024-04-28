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
    private CreationTournoiVue vue;
    private TournoiModele modele;
    private List<Equipe> data;

    /**
     * Effectue la construction de la vue
     * 
     * @param vue de la page, permettant l'activation de ActionEvent
     */
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

    private void validerCreationTournoi() {
        String nom = this.vue.textFieldNom.getText();

        if (checkForEmptyField()) {
            vue.messageCreation.setText("Un des champs nécessaires n'a pas été rempli.");
        } else if (nom.length() < 3) {
            vue.messageCreation.setText("Le nom de votre tournoi doit contenir au moins 3 caractères.");
        } else {
            String dateDebut = this.modele.getDateString(this.vue.dateChooserDebut.getDate());
            String dateFin = this.modele.getDateString(this.vue.dateChooserFin.getDate());
            Notoriete not = (Notoriete) this.vue.comboBoxNotoriete.getSelectedItem();
            TournoiModele t = new TournoiModele(0, nom, dateDebut, dateFin, not, EtatTournoi.FERME);

            if (!t.isTournoiValide()) {
                vue.messageCreation.setText(
                        "<html> Les dates sont invalides. Vérifiez qu'un tournoi <br> n'existe pas sur ce créneau.");
            } else {
                TournoiDAO.getInstance().add(t);
                for (int i = 0; i < this.vue.listArbitres.getModel().getSize(); i++) {
                    TournoiDAO.getInstance().addArbitre(t, this.vue.listArbitres.getModel().getElementAt(i));
                    t.ajouterArbitre(this.vue.listArbitres.getModel().getElementAt(i));
                }
                for (Equipe eq : data) {
                    TournoiDAO.getInstance().addEquipe(t, eq);
                    t.ajouterEquipe(eq, 0);
                }

                disableAllFields(t);
            }
        }
    }

    private void importFichierCSV() {
        JFileChooser fc = new JFileChooser();
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(new FileNameExtensionFilter("CSV Documents", "csv"));
        int chose = fc.showOpenDialog(this.vue);

        if (chose == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            this.vue.textFieldEquipesFile.setText(file.getName());
            data = EquipeDAO.getInstance().importEquipes(file);
        }

    }

    private void supprimerArbitreSelectionne() {
        for (Arbitre a : this.vue.listArbitres.getSelectedValuesList()) {
            this.vue.comboBoxArbitre.addItem(a);
            this.vue.modeleList.removeElement(a);
        }
    }

    private void viderListeArbitres() {
        try {
            for (Object a : this.vue.modeleList.toArray()) {
                this.vue.comboBoxArbitre.addItem((Arbitre) a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        this.vue.modeleList.removeAllElements();
    }

    private void ajouterArbitreSelectionne() {
        Arbitre a = (Arbitre) this.vue.comboBoxArbitre.getSelectedItem();
		if (!this.vue.modeleList.contains(a)) {
			this.vue.modeleList.addElement(a);
			this.vue.comboBoxArbitre.removeItem(a);
		}
    }

    /**
     * vérifie si un des fields est vide
     */
    public boolean checkForEmptyField() {
        boolean nomVide = this.vue.textFieldNom.getText().equals("");
        boolean dateDebutVide = this.vue.dateChooserDebut.getDate() == null;
        boolean dateFinVide = this.vue.dateChooserFin == null;
        boolean equipesVide = this.vue.textFieldEquipesFile.getText().equals("");
        boolean arbitresVide = this.vue.modeleList.isEmpty();

        return nomVide || dateDebutVide || dateFinVide || equipesVide || arbitresVide;
    }

    public void disableAllFields(TournoiModele t) {
        vue.messageCreation.setForeground(Palette.SUCCESS_FOREGROUND);
        vue.messageCreation.setText("Le tournoi N°" + t.getIDTournoi() + " a été créé.");
        vue.btnAddArbitre.setEnabled(false);
        vue.btnImportEquipes.setEnabled(false);
        vue.textFieldEquipesFile.setEnabled(false);
        vue.textFieldNom.setEnabled(false);
        vue.btnSupprimerArbitre.setEnabled(false);
        vue.btnValider.setEnabled(false);
        vue.btnViderArbitres.setEnabled(false);
        vue.dateChooserDebut.setEnabled(false);
        vue.dateChooserFin.setEnabled(false);
        vue.comboBoxArbitre.setEnabled(false);
        vue.comboBoxNotoriete.setEnabled(false);
    }

}
