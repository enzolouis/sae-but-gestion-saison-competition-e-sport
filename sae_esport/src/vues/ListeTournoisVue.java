package vues;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import style.*;
import java.awt.GridLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import DAOs.TournoiDAO;
import classes.Equipe;
import modeles.TournoiModele;

import java.awt.Dimension;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JList;

public class ListeTournoisVue extends CustomJFrame {
	private JTable tableTournois;
	
	 public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	        			ListeTournoisVue frame = new ListeTournoisVue();
	                    frame.setVisible(true);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }
	
	public ListeTournoisVue() {
		setSize(new Dimension(600, 600));
		setResizable(true);
		
		CustomJPanel panel = new CustomJPanel();
		getContentPane().add(panel);
		
		CustomJPanel panelTitre = new CustomJPanel();
		CustomJLabel titre = new CustomJLabel("Liste des tournois", 20);
		panelTitre.add(titre);
		
		CustomJPanel panelTournois = new CustomJPanel();
		panel.add(panelTitre, BorderLayout.NORTH);	
		panel.add(panelTournois, BorderLayout.CENTER);	
		panelTournois.setLayout(new GridLayout(2, 1, 0, 0));
		
		CustomJPanel panelListe = new CustomJPanel();
		panelListe.setOpaque(false);
		panelListe.setBorder(new EmptyBorder(15, 15, 15, 15));
		panelTournois.add(panelListe);
		panelListe.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JScrollPane scrollListe = new JScrollPane();
		scrollListe.setForeground(new Color(255, 255, 255));
		scrollListe.setPreferredSize(new Dimension(500, 180));
		scrollListe.setSize(new Dimension(100, 100));
		JTable tableTournois = new JTable();
		tableTournois.setOpaque(false);
		tableTournois.setForeground(new Color(44, 47, 51));
		tableTournois.setSelectionBackground(new Color(198, 224, 242));
		tableTournois.setGridColor(new Color(44, 47, 51));
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("ID"); tableModel.addColumn("Titre"); 
		tableModel.addColumn("Date de début"); tableModel.addColumn("Date de fin");
		tableModel.addColumn("Notoriété"); tableModel.addColumn("Etat");
		try {
			for (TournoiModele t : TournoiDAO.getInstance().getAll()) {
				tableModel.addRow(new Object[] {t.getIDTournoi(), t.getNomTournoi(),
						t.getDateDebut(), t.getDateFin(), t.getNotoriete(), t.getEtatTournoi()});
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tableTournois.setModel(tableModel);
		tableTournois.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableTournois.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableTournois.getTableHeader().setBackground(new Color(102,172,221));
		tableTournois.getTableHeader().setForeground(Color.WHITE);
		scrollListe.setViewportView(tableTournois);
		panelListe.add(scrollListe);
		
		CustomJPanel panelInfos = new CustomJPanel();
		panelTournois.add(panelInfos);
		panelInfos.setLayout(new GridLayout(0, 2, 0, 0));
		
		CustomJPanel panelEquipeArbitre = new CustomJPanel();
		panelEquipeArbitre.setBorder(new EmptyBorder(5, 5, 5, 5));
		CustomJPanel panelAttributs = new CustomJPanel();
		panelInfos.add(panelAttributs);
		panelAttributs.setLayout(new GridLayout(3, 0, 0, 0));
		panelInfos.add(panelEquipeArbitre);
		panelEquipeArbitre.setLayout(new GridLayout(0, 2, 0, 0));
		
		CustomJPanel panelEquipe = new CustomJPanel();
		panelEquipe.setBorder(new EmptyBorder(5, 5, 5, 5));
		JScrollPane scrollEquipes = new JScrollPane();
		panelEquipe.add(scrollEquipes);
		JList<Equipe> listeEquipes = new JList<Equipe>();
		scrollEquipes.add(listeEquipes);
		
		panelEquipeArbitre.add(panelEquipe);
		
		JScrollPane scrollArbitres = new JScrollPane();
		
		
	}
	
	

}
