package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DAOs.TournoiDAO;
import classes.Equipe;
import controleurs.IdentificationControleur;
import modeles.TournoiModele;
import style.CustomJButton;
import style.CustomJFrame;
import style.CustomJLabel;
import style.CustomJPanel;
import style.CustomJSeparator;
import javax.swing.JTable;

public class ConsultationTournoi extends CustomJFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TournoiModele t = null;
					try {
						t = TournoiDAO.getInstance().getAll().get(0);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ConsultationTournoi frame = new ConsultationTournoi(t);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConsultationTournoi(TournoiModele tournoiCourant) {
		super(new Dimension(750, 500), "Consultation du classement du tournoi N°"+tournoiCourant.getIDTournoi());
		// tournoi en paramètre
		
		
		
		
		
    	pack();
    	
    	//this.controleur = new IdentificationControleur(this);
    	contentPane = super.getContentPanel();
        
        // Panel Top : Title
    	CustomJPanel panelTop = new CustomJPanel();
        contentPane.add(panelTop, BorderLayout.NORTH);
        
        CustomJLabel titleTop = new CustomJLabel("Classement du tournoi "+tournoiCourant.getNomTournoi(), 25);
        panelTop.add(titleTop);
        
        CustomJSeparator separatorTop = new CustomJSeparator();
        panelTop.add(separatorTop, BorderLayout.SOUTH);
    	
        // Middle Panel : Login
    	CustomJPanel panelMiddleClassement = new CustomJPanel(new EmptyBorder(10, 10, 10, 10), new GridLayout(2, 0, 0, 0));
        contentPane.add(panelMiddleClassement, BorderLayout.CENTER);
        panelMiddleClassement.setLayout(new BorderLayout(0, 0));
        
        
        
        JScrollPane scrollListe = new JScrollPane();
        scrollListe.setOpaque(false);
        scrollListe.setBackground(new Color(44, 47, 51));
		scrollListe.setForeground(new Color(255, 255, 255));
		scrollListe.setPreferredSize(new Dimension(500, 180));
		scrollListe.setSize(new Dimension(100, 100));
		
		JTable tableClassement = new JTable(){
			public boolean isCellEditable(int row, int column) {                
				return false;
			};};
		tableClassement.setOpaque(false);
		
        tableClassement.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		tableClassement.setForeground(new Color(44, 47, 51));
		tableClassement.setSelectionBackground(new Color(198, 224, 242));
		tableClassement.setGridColor(new Color(44, 47, 51));
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("ID"); tableModel.addColumn("Nom"); 
		try {
			for (Equipe e : tournoiCourant.getEquipes().keySet()) {
				tableModel.addRow(new Object[] {e.getIdEquipe(), e.getNom()});
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tableClassement.setModel(tableModel);
		tableClassement.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableClassement.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableClassement.getTableHeader().setBackground(new Color(102,172,221));
		tableClassement.getTableHeader().setForeground(Color.WHITE);
		scrollListe.setViewportView(tableClassement);
		panelMiddleClassement.add(scrollListe, BorderLayout.CENTER);
        
        
        // Bottom Panel : Quitter + Login
        CustomJPanel panelQuitterSeconnecter = new CustomJPanel(new EmptyBorder(10, 10, 10, 10), new FlowLayout(FlowLayout.CENTER, 5, 5));
        contentPane.add(panelQuitterSeconnecter, BorderLayout.SOUTH);
        
        CustomJButton btnQuit = new CustomJButton("Quitter", 5);
        btnQuit.setBackground(new Color(231, 76, 60));
        btnQuit.setForeground(new Color(255, 255, 255));
        //btnQuit.addActionListener(this.controleur);
        panelQuitterSeconnecter.add(btnQuit);
	}

}
