package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.filechooser.FileNameExtensionFilter;

import DAOs.ArbitreDAO;
import DAOs.EquipeDAO;
import DAOs.TournoiDAO;
import classes.Arbitre;
import classes.Equipe;
import modeles.TournoiModele;
import vues.IdentificationVue;
import vues.ListeTournoisVue;

public class ListeTournoisControleur implements ActionListener, MouseListener {
	private TournoiModele modele;
	private ListeTournoisVue vue;
	private boolean toggleActivated;
	
	public ListeTournoisControleur(ListeTournoisVue vue) {
		this.modele = new TournoiModele();
		this.vue = vue;
		this.toggleActivated = false;
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
			case "Ouverture":
				System.out.println("Ouvrir tournoi");
				break;
			default:
				System.out.println("Autre tournoi");
				break;
			}
		} else if (e.getSource() instanceof JToggleButton) {
			JToggleButton toggle = (JToggleButton) e.getSource();
			if (toggle.isSelected()) {
				toggle.setIcon(ListeTournoisVue.OEIL_VISIBLE_ICON);
				
			} else {
				toggle.setIcon(ListeTournoisVue.OEIL_INVISIBLE_ICON);
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
		if (e.getSource() instanceof JTable) {
			JTable t = (JTable) e.getSource();
			int idTournoi = (int) t.getValueAt(t.getSelectedRow(), 0);

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
	
}
