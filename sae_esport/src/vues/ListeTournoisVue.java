package vues;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import style.*;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import java.awt.Dimension;
import javax.swing.border.EmptyBorder;

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
		panelListe.setBorder(new EmptyBorder(15, 15, 15, 15));
		panelTournois.add(panelListe);
		
		JTable colonnesTournoi = new JTable();
		colonnesTournoi.setSize(new Dimension(20, 20));
		colonnesTournoi.setBounds(15, 15, 496, 35);
		DefaultTableModel colonnesModele = new DefaultTableModel();
		Object [] colonnesNom = {"Identifiant", "Nom", "Date de début", "Date de fin", "Notoriété", "Etat"};
		colonnesModele.addRow(colonnesNom);
		panelListe.setLayout(null);
		colonnesTournoi.setRowHeight(28);
		colonnesTournoi.setModel(colonnesModele);
		panelListe.add(colonnesTournoi);
		
		JTable tableTournoi = new JTable();
		tableTournoi.setBounds(15, 61, 496, 163);
		DefaultTableModel tableModele = new DefaultTableModel();
		tableTournoi.setModel(tableModele);
		panelListe.add(tableTournoi);
		
		CustomJPanel panelInfos = new CustomJPanel();
		panelTournois.add(panelInfos);
		panelInfos.setLayout(new GridLayout(0, 2, 0, 0));
		
		CustomJPanel panelEquipeArbitre = new CustomJPanel();
		CustomJPanel panelAttributs = new CustomJPanel();
		panelInfos.add(panelEquipeArbitre);
		panelInfos.add(panelAttributs);
		
	}
	
	

}
