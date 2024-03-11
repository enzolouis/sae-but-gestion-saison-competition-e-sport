package controleurs;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DAOs.EquipeDAO;
import DAOs.TournoiDAO;
import classes.Arbitre;
import classes.Disposition;
import classes.Equipe;
import classes.EtatTournoi;
import classes.Joueur;
import modeles.TournoiModele;
import style.Palette;
import vues.ListeTournoisVue;

public class ListeTournoisControleur implements ActionListener, ListSelectionListener, MouseListener {

	private ListeTournoisVue vue;
	

	/**
	 * Effectue la construction de la vue
	 * @param vue de la page, permettant l'activation de ActionEvent
	 * */
	public ListeTournoisControleur(ListeTournoisVue vue) {
		this.vue = vue;
		
	}
	
	public void setUpTableModel() {
		this.vue.tableModel.setRowCount(0);
		
		try {
			for (TournoiModele t : TournoiDAO.getInstance().getAll()) {
				this.vue.tableModel.addRow(new Object[] {t.getIDTournoi(), t.getNomTournoi(),
						t.getDateDebut(), t.getDateFin(), t.getNotoriete(), t.getEtatTournoi()});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		if (e.getSource() instanceof JButton) {
			JButton bouton = (JButton) e.getSource();
			
			switch (bouton.getText()) {
			case "Fermer":
				this.vue.closeCurrentWindow();
				break;
			case "Ouvrir":
				int idTournoi = (int) vue.tableTournois.getValueAt(vue.tableTournois.getSelectedRow(), 0);
				try {
					TournoiModele tournoi = TournoiDAO.getInstance().getById(idTournoi).get();
					this.vue.erreurOuverture.setForeground(new Color(235, 77, 75));
					if (!TournoiModele.isAucunTournoiOuvert()) {
						this.vue.panelErreur.setBackground(Palette.REDERRORBACKGROUND);
						this.vue.panelErreur.setBorder(new LineBorder(Palette.REDERRORBORDER, 1));
						this.vue.erreurOuverture.setText("Un tournoi est déjà ouvert ");
					}
					else if (!tournoi.isDateCouranteDansCreneauTournoi()) {
						this.vue.panelErreur.setBackground(Palette.REDERRORBACKGROUND);
						this.vue.panelErreur.setBorder(new LineBorder(Palette.REDERRORBORDER, 1));
						this.vue.erreurOuverture.setText("Nous ne sommes pas dans la période du tournoi !");
					} else if (!tournoi.isTournoiMinimum4EquipeDisposee()) {
						this.vue.panelErreur.setBackground(Palette.REDERRORBACKGROUND);
						this.vue.panelErreur.setBorder(new LineBorder(Palette.REDERRORBORDER, 1));
						this.vue.erreurOuverture.setText("Pas assez d'équipe disposées !");
					} else {
						// reverification de tout
						
						tournoi.ouvrirTournoi();
						this.setUpTableModel();
						this.vue.boutonOuverture.setEnabled(false);
						this.vue.panelErreur.setBackground(Palette.GREENLIGHTER);
						this.vue.panelErreur.setBorder(new LineBorder(Palette.GREEN, 1));
						this.vue.erreurOuverture.setText("Le tournoi a été ouvert !");
						this.vue.erreurOuverture.setForeground(Palette.GREEN);
						
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
			case "<html><p style='text-align:center'>Rendre<br>indisposée</p></html>":
				bouton.setBackground(Palette.REDERRORBACKGROUND);
				bouton.setText("<html><p style='text-align:center'>Rendre<br>disposée</p></html>");
				Equipe eq = (Equipe) vue.listeEquipes.getSelectedValue();
				eq.setDisposition(Disposition.NON_DISPOSEE);
				try {
					EquipeDAO.getInstance().update(eq);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
			case "<html><p style='text-align:center'>Rendre<br>disposée</p></html>":
				bouton.setBackground(Palette.GREEN);
				bouton.setText("<html><p style='text-align:center'>Rendre<br>indisposée</p></html>");
				Equipe eq1 = (Equipe) vue.listeEquipes.getSelectedValue();
				eq1.setDisposition(Disposition.DISPOSEE);
				
				try {
					EquipeDAO.getInstance().update(eq1);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
			}
		} else if (e.getSource() instanceof JToggleButton) {
			JToggleButton toggle = (JToggleButton) e.getSource();
			if (toggle.isSelected()) {
				toggle.setIcon(Palette.OEIL_VISIBLE_ICON);
				this.vue.mdp.setEchoChar((char) 0);
			} else {
				toggle.setIcon(Palette.OEIL_INVISIBLE_ICON);
				this.vue.mdp.setEchoChar('•');
			}
		}
		
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		 if (e.getSource() instanceof JList) {
			@SuppressWarnings("unchecked")
			JList<Equipe> l = (JList<Equipe>) e.getSource();
			Equipe e1 = (Equipe) l.getSelectedValue();
			if (e1 != null) {
				vue.titreEquipe.setText(e1.getNom());
				
				vue.joueursModel.clear();
				for (Joueur j : e1.getListeJoueurs()) {
					vue.joueursModel.addElement(j);
				}
				
				vue.joueurs.setModel(vue.joueursModel);
				
				vue.boutonRendreDisposee.setVisible(true);
				
				if (e1.getDisposition().equals(Disposition.DISPOSEE)) {
					vue.boutonRendreDisposee.setText("<html><p style='text-align:center'>Rendre<br>indisposée</p></html>");
					vue.boutonRendreDisposee.setBackground(Palette.GREEN);
				} else {
					vue.boutonRendreDisposee.setText("<html><p style='text-align:center'>Rendre<br>disposée</p></html>");
					vue.boutonRendreDisposee.setBackground(Palette.REDERRORBACKGROUND);
				}
			}
			
			
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		int idTournoi;
		try {
		// récupération de l'identifiant
		String strSource = e.getSource().toString();
		int start = strSource.indexOf("{")+1, stop  = strSource.length()-1;
		int iSelectedIndex = Integer.parseInt(strSource.substring(start, stop));
		idTournoi = (int) vue.tableTournois.getValueAt(iSelectedIndex, 0);
		} catch (Exception e1) {
			return;
		}
		
		//remise à blank des fields
		vue.titreEquipe.setText(" ");
		vue.joueursModel.clear();
		vue.disposition.setText(" ");
		vue.boutonRendreDisposee.setVisible(false);
		vue.boutonOuverture.setEnabled(true);
		vue.erreurOuverture.setText(" ");
		
		try {
			TournoiModele tournoi = TournoiDAO.getInstance().getById(idTournoi).get();
			vue.labelTitre.setText("Tournoi " + tournoi.getNomTournoi() + " ("+tournoi.getNotoriete().toString()+")");
			vue.login.setText(tournoi.getLogin());
			vue.mdp.setText(tournoi.getMotDePasse());
			vue.dateDebut.setDate(tournoi.getDateDebut());
			vue.dateFin.setDate(tournoi.getDateFin());
			if (tournoi.getEtatTournoi().equals(EtatTournoi.FERME)) {
				vue.boutonOuverture.setEnabled(true);
			} else {
				vue.boutonOuverture.setEnabled(false);
			}
			
			vue.listeEquipesModel.clear();
			
			for (Equipe eq : tournoi.getParticipants().keySet()) {
				vue.listeEquipesModel.addElement(eq);
			}
			
			for (Equipe eq : tournoi.getParticipantsIndisposees()) {
				vue.listeEquipesModel.addElement(eq);
			}
			
			vue.listeEquipes.setModel(vue.listeEquipesModel);
			
			vue.listeArbitresModel.clear();
			for (Arbitre a : tournoi.getArbitres()) {
				vue.listeArbitresModel.addElement(a);
			}
			
			vue.listeArbitres.setModel(vue.listeArbitresModel);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}	
	}
	
}
