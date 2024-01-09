package controleurs;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import DAOs.ArbitreDAO;
import DAOs.EquipeDAO;
import DAOs.TournoiDAO;
import classes.Arbitre;
import classes.Disposition;
import classes.Equipe;
import classes.Joueur;
import modeles.TournoiModele;
import vues.IdentificationVue;
import vues.ListeTournoisVue;

public class ListeTournoisControleur implements ActionListener, ListSelectionListener, MouseListener {
	private TournoiModele modele;
	private ListeTournoisVue vue;
	
	public ListeTournoisControleur(ListeTournoisVue vue) {
		this.modele = new TournoiModele();
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		if (e.getSource() instanceof JButton) {
			JButton bouton = (JButton) e.getSource();
			
			switch (bouton.getText()) {
			case "Fermer":
				this.vue.setVisible(false);
				this.vue.dispose();
				break;
			case "Ouvrir":
				int idTournoi = (int) vue.tableTournois.getValueAt(vue.tableTournois.getSelectedRow(), 0);
				
				System.out.println(idTournoi);
				try {
					TournoiModele tournoi = TournoiDAO.getInstance().getById(idTournoi).get();
					this.vue.erreurOuverture.setForeground(new Color(235, 77, 75));
					if (!TournoiModele.isAucunTournoiOuvert()) {
						this.vue.erreurOuverture.setText("Un tournoi est déjà ouvert ");
					}
					else if (!tournoi.isDateCouranteDansCreneauTournoi()) {
						this.vue.erreurOuverture.setText("Nous ne sommes pas dans la période du tournoi !");
					} else if (!tournoi.isTournoiMinimum4EquipeDisposee()) {
						this.vue.erreurOuverture.setText("Il n'y a pas assez d'équipe disposées pour commencer le tournoi !");
					} else {
						// reverification de tout (voir si on supprime ça dans tournoi)
						tournoi.ouvrirTournoi();
						this.vue.erreurOuverture.setText("Le tournoi a été ouvert !");
						this.vue.erreurOuverture.setForeground(new Color(106, 176, 76));
						
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
			default:
				break;
			}
		} else if (e.getSource() instanceof JToggleButton) {
			JToggleButton toggle = (JToggleButton) e.getSource();
			if (toggle.isSelected()) {
				toggle.setIcon(ListeTournoisVue.OEIL_VISIBLE_ICON);
				this.vue.mdp.setEchoChar((char) 0);
			} else {
				toggle.setIcon(ListeTournoisVue.OEIL_INVISIBLE_ICON);
				this.vue.mdp.setEchoChar('•');
			}
		}
		
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		 if (e.getSource() instanceof JList) {
			JList l = (JList) e.getSource();
			Equipe e1 = (Equipe) l.getSelectedValue();
			vue.titreEquipe.setText(e1.getNom());
			List<String> joueurs = new ArrayList<>();
			
			vue.joueursModel.clear();
			for (Joueur j : e1.getListeJoueurs()) {
				vue.joueursModel.addElement(j);
			}
			
			vue.joueurs.setModel(vue.joueursModel);
			
			vue.disposition.setText(e1.getDispose().toString());
			if (e1.getDispose().equals(Disposition.DISPOSEE)) {
				vue.disposition.setForeground(new Color(106, 176, 76));
			} else {
				vue.disposition.setForeground(new Color(235, 77, 75));
			}
			
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		// récupération de l'identifiant
		String strSource = e.getSource().toString();
		int start = strSource.indexOf("{")+1, stop  = strSource.length()-1;
		int iSelectedIndex = Integer.parseInt(strSource.substring(start, stop));
		int idTournoi = (int) vue.tableTournois.getValueAt(iSelectedIndex, 0);
		
		//remise à blank des fields
		vue.titreEquipe.setText(" ");
		vue.joueursModel.clear();
		vue.disposition.setText(" ");
		vue.boutonOuverture.setEnabled(true);
		vue.erreurOuverture.setText(" ");
		
		try {
			TournoiModele tournoi = TournoiDAO.getInstance().getById(idTournoi).get();
			vue.labelTitre.setText("Tournoi N°" + tournoi.getIDTournoi() + " ("+tournoi.getNotoriete().toString()+")");
			vue.login.setText(tournoi.getLogin());
			vue.mdp.setText(tournoi.getMotDePasse());
			vue.dateDebut.setDate(tournoi.getDateDebut());
			vue.dateFin.setDate(tournoi.getDateFin());
			
			vue.listeEquipesModel.clear();
			
			for (Equipe eq : tournoi.getEquipes().keySet()) {
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
