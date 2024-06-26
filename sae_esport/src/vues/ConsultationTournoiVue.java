package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controleurs.ConsultationTournoiControleur;
import modeles.TournoiModele;
import style.CustomJButton;
import style.CustomJFrame;
import style.CustomJLabel;
import style.CustomJPanel;
import style.CustomJSeparator;
import style.EvenOddRenderer;
import style.Palette;

import javax.swing.JTable;

public class ConsultationTournoiVue extends CustomJFrame {

	private JPanel contentPane;
	private ConsultationTournoiControleur controleur;
	public DefaultTableModel tableModel;

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public ConsultationTournoiVue(TournoiModele tournoiCourant) {
		
		super(new Dimension(750, 500), "Consultation du classement du tournoi N°"+tournoiCourant.getIDTournoi());
		// tournoi en paramètre
		
		controleur = new ConsultationTournoiControleur(this);
		
    	pack();
    	
    	//this.controleur = new IdentificationControleur(this);
    	contentPane = super.getContentPanel();
        
        // Panel Top : Title
    	CustomJPanel panelTop = new CustomJPanel();
        contentPane.add(panelTop, BorderLayout.NORTH);
        
        CustomJLabel titleTop = new CustomJLabel("CLASSEMENT DE "+tournoiCourant.getNomTournoi().toUpperCase(), 25);
        titleTop.setFont(Palette.customFont.deriveFont(Font.BOLD, 20));
        panelTop.add(titleTop);
        
        CustomJSeparator separatorTop = new CustomJSeparator();
        panelTop.add(separatorTop, BorderLayout.SOUTH);
    	
        // Middle Panel : Login
    	CustomJPanel panelMiddleClassement = new CustomJPanel(new EmptyBorder(20, 0, 5, 0), new GridLayout(2, 0, 0, 0));
        contentPane.add(panelMiddleClassement, BorderLayout.CENTER);
        panelMiddleClassement.setLayout(new BorderLayout(0, 0));
        
        
        
		JTable tableClassement = new JTable(){
			public boolean isCellEditable(int row, int column) {                
				return false;
			};};
		tableClassement.setBorder(null);
		tableClassement.setOpaque(false);
        tableClassement.setFont(Palette.customFont.deriveFont(Font.PLAIN, 12));
		tableClassement.setForeground(Palette.WHITE);
		tableClassement.setSelectionBackground(Palette.WHITE);
		tableClassement.setGridColor(Palette.WHITE);
		tableClassement.setRowHeight(34);
		
		
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Top");
		tableModel.addColumn("Equipe");
		tableModel.addColumn("Matchs joués");
		tableModel.addColumn("Points");
		tableModel.addColumn("Victoires");
		tableModel.addColumn("Défaites");
		
		/*
		for (Equipe e : tournoiCourant.classementTournoi()
				.keySet()
				.stream()
				.sorted((e1,e2) -> { if (tournoiCourant.classementTournoi().get(e1) 
											> tournoiCourant.classementTournoi().get(e2)) {
										return 1;
									} else if (tournoiCourant.classementTournoi().get(e1) 
											== tournoiCourant.classementTournoi().get(e2)) {
										return 0;
									} else {
										return -1;
									}
									})
				.collect(Collectors.toList())) {
			
			
			int matchsJoues = 0; int victoires = 0; int defaites = 0;
			
			for (Match m : tournoiCourant.getMatchs()) {
				if (m.getEquipes().contains(e)) {
					if (m.getVainqueur() != 0) {
						matchsJoues++;
						if (m.getVainqueur() == e.getIdEquipe()) {
							victoires++;
						} else {
							defaites++;
						}
					}
					
				}
			}
			
			tableModel.addRow(new Object[] {tournoiCourant.classementTournoi().get(e), e.getNom(), 
					matchsJoues, tournoiCourant.getParticipants().get(e), victoires, defaites});
			
		}	
		*/
		
		this.controleur.setUpTableModel();
		
		tableClassement.setModel(tableModel);
		tableClassement.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableClassement.getColumnModel().getColumn(1).setPreferredWidth(175);
        EvenOddRenderer renderer = new EvenOddRenderer();
		tableClassement.setDefaultRenderer(Object.class, renderer);
		
		tableClassement.getTableHeader().setBackground(Palette.BLACKLIGHTER);
		tableClassement.getTableHeader().setForeground(Color.WHITE);
		tableClassement.getTableHeader().setFont(Palette.customFont.deriveFont(Font.PLAIN, 11));
		panelMiddleClassement.add(tableClassement.getTableHeader(), BorderLayout.NORTH);
		panelMiddleClassement.add(tableClassement, BorderLayout.CENTER);
        
        
        // Bottom Panel : Quitter + Login
        CustomJPanel panelQuitterSeconnecter = new CustomJPanel(new EmptyBorder(10, 10, 10, 10), new FlowLayout(FlowLayout.CENTER, 5, 5));
        contentPane.add(panelQuitterSeconnecter, BorderLayout.SOUTH);
        
        CustomJButton btnQuit = new CustomJButton("Quitter", 5);
        btnQuit.setActionCommand("quitter");
        btnQuit.addActionListener(this.controleur);
        btnQuit.setBackground(new Color(231, 76, 60));
        btnQuit.setForeground(new Color(255, 255, 255));
        //btnQuit.addActionListener(this.controleur);
        panelQuitterSeconnecter.add(btnQuit);
	}
	
	public void closeCurrentWindow() {
		super.closeCurrentWindow();
		AccueilArbitreVue frame = new AccueilArbitreVue();
		frame.setVisible(true);
	}

}
