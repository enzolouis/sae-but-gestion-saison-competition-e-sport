package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import DAOs.ArbitreDAO;
import DAOs.TournoiDAO;
import classes.Arbitre;
import modeles.TournoiModele;
import style.CustomJButton;
import style.CustomJComboBox;
import style.CustomJFrame;
import style.CustomJLabel;
import style.CustomJPanel;
import style.CustomJScrollPane;
import style.CustomJSeparator;

public class TournoiListeVue extends CustomJFrame {
	private CustomJPanel contentPanel;
    //private TournoiListeControleur controleur;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	TournoiListeVue frame = new TournoiListeVue();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public TournoiListeVue() throws Exception {
    	super(new Dimension(975, 500), "Listes des tournois");
    	
    	pack();
    	
    	//this.controleur = new TournoiListeControleur(this);
    	contentPanel = this.getContentPanel();
        
        // Panel Top : Title
    	CustomJPanel panelTop = new CustomJPanel();
        contentPanel.add(panelTop, BorderLayout.NORTH);
        
        CustomJLabel titleTop = new CustomJLabel("Listes des tournois", 25);
        panelTop.add(titleTop);
        
        CustomJSeparator separatorTop = new CustomJSeparator();
        panelTop.add(separatorTop, BorderLayout.SOUTH);
        
        // Panel middle : Action et Listes
        GridBagLayout gbl_panelMiddle = new GridBagLayout();
        gbl_panelMiddle.rowHeights = new int[] {50, 200, 50};
        gbl_panelMiddle.columnWidths = new int[] {0};
        gbl_panelMiddle.columnWeights = new double[]{1.0};
        gbl_panelMiddle.rowWeights = new double[]{0.0, 1.0, 1.0};
        JPanel panelMiddle = new CustomJPanel(new EmptyBorder(0, 0, 10, 10));
        panelMiddle.setLayout(gbl_panelMiddle);
        contentPanel.add(panelMiddle);
        
        CustomJPanel panelMiddleHeaderCustomJPanel = new CustomJPanel(new EmptyBorder(0, 0, 0, 0));
        GridBagConstraints gbc_panelMiddleHeaderCustomJPanel = new GridBagConstraints();
        gbc_panelMiddleHeaderCustomJPanel.anchor = GridBagConstraints.BASELINE_LEADING;
        panelMiddle.add(panelMiddleHeaderCustomJPanel, gbc_panelMiddleHeaderCustomJPanel);
        
        CustomJComboBox<TournoiModele> comboBoxTournoi = new CustomJComboBox<TournoiModele>();
        comboBoxTournoi.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		try {
			for (TournoiModele a : TournoiDAO.getInstance().getAll()) {
				comboBoxTournoi.addItem(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        panelMiddleHeaderCustomJPanel.add(comboBoxTournoi);
        
        GridBagConstraints gbc_panelRoundList = new GridBagConstraints();
        gbc_panelRoundList.fill = GridBagConstraints.BOTH;
        gbc_panelRoundList.gridx = 0;
        gbc_panelRoundList.gridy = 1;
        CustomJPanel panelRoundList = new CustomJPanel(new EmptyBorder(10, 10, 10, 10), new GridLayout(1, 0, 10, 10)); 
        CustomJScrollPane scrollpanelRoundList = new CustomJScrollPane(panelRoundList);
        scrollpanelRoundList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollpanelRoundList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panelMiddle.add(scrollpanelRoundList, gbc_panelRoundList);
        
        GridBagLayout gbl_matchCustomJPanel = new GridBagLayout();
        gbl_matchCustomJPanel.rowHeights = new int[] {30, 0};
        gbl_matchCustomJPanel.columnWidths = new int[] {0};
        gbl_matchCustomJPanel.columnWeights = new double[]{1.0};
        gbl_matchCustomJPanel.rowWeights = new double[]{0.0, 0.0};
        
        GridBagConstraints gbc_titleMatchTop = new GridBagConstraints();
        gbc_titleMatchTop.fill = GridBagConstraints.BOTH;
        gbc_titleMatchTop.gridx = 0;
        gbc_titleMatchTop.gridy = 0;
        
        GridBagConstraints gbc_roundListEquipe = new GridBagConstraints();
        gbc_roundListEquipe.fill = GridBagConstraints.BOTH;
        gbc_roundListEquipe.gridx = 0;
        gbc_roundListEquipe.gridy = 1;
        
        for (int i = 1; i <= 10; i++) {
        	// Panel Match : Titre + Panel Liste Equipe
        	CustomJPanel MatchCustomJPanel = new CustomJPanel(new EmptyBorder(0, 0, 0, 0), new GridLayout(2, 1, 0, 0));
        	MatchCustomJPanel.setLayout(gbl_matchCustomJPanel);
        	panelRoundList.add(MatchCustomJPanel);
        	
        	// Numero Round
            CustomJLabel titleMatchTop = new CustomJLabel("Round n°" + i, 25);
            MatchCustomJPanel.add(titleMatchTop, gbc_titleMatchTop);
            
            // Panel Equipe Liste
            CustomJPanel MatchEquipeListJPanel = new CustomJPanel(new EmptyBorder(10, 10, 10, 10), new GridLayout(0, 2, 5, 5));
            MatchCustomJPanel.add(MatchEquipeListJPanel, gbc_roundListEquipe);
        	
        	for (int j = 1; j <= 8; j++) {
            	CustomJButton button = new CustomJButton("Équipe n°" + j, 15);
            	//button.addActionListener(this.controleur);
            	MatchEquipeListJPanel.add(button);
    		}
		}
        
        // Panel middle bottom : Action tournoi
        GridBagConstraints gbc_panelMiddleBottom = new GridBagConstraints();
        gbc_panelMiddleBottom.anchor = GridBagConstraints.SOUTHWEST;

        gbc_panelMiddleBottom.gridx = 0;
        gbc_panelMiddleBottom.gridy = 2;
        GridBagLayout gbl_panelMiddleBottom = new GridBagLayout();
        gbl_panelMiddleBottom.rowHeights = new int[] {0};
        gbl_panelMiddleBottom.columnWidths = new int[] {0, 700, 100};
        gbl_panelMiddleBottom.columnWeights = new double[]{0.0};
        gbl_panelMiddleBottom.rowWeights = new double[]{0.0};
        CustomJPanel panelMiddleBottom = new CustomJPanel(new EmptyBorder(10, 10, 10, 10), new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelMiddleBottom.setLayout(gbl_panelMiddleBottom);
        panelMiddle.add(panelMiddleBottom, gbc_panelMiddleBottom);
        
    	CustomJButton buttonModify = new CustomJButton("Modifier", 15);
        GridBagConstraints gbc_buttonModify = new GridBagConstraints();
        gbc_buttonModify.fill = GridBagConstraints.VERTICAL;
        gbc_buttonModify.gridx = 0;
        gbc_buttonModify.gridy = 0;
    	//button.addActionListener(this.controleur);
    	panelMiddleBottom.add(buttonModify, gbc_buttonModify);
    	
    	CustomJButton buttonOpen = new CustomJButton("Ouvrir", 15);
        GridBagConstraints gbc_buttonOpen = new GridBagConstraints();
        gbc_buttonOpen.fill = GridBagConstraints.CENTER;
        gbc_buttonOpen.gridx = 1;
        gbc_buttonOpen.gridy = 0;
        gbc_buttonOpen.anchor = GridBagConstraints.EAST;
    	//button.addActionListener(this.controleur);
    	panelMiddleBottom.add(buttonOpen, gbc_buttonOpen);
    	
    	CustomJButton buttonClose = new CustomJButton("Fermer", 15);
        GridBagConstraints gbc_buttonClose = new GridBagConstraints();
        gbc_buttonClose.anchor = GridBagConstraints.EAST;
        gbc_buttonClose.gridx = 2;
        gbc_buttonClose.gridy = 0;
    	//button.addActionListener(this.controleur);
    	panelMiddleBottom.add(buttonClose, gbc_buttonClose);
        
        // Panel bottom
        CustomJPanel panelBottom = new CustomJPanel(new EmptyBorder(10, 10, 0, 10), new FlowLayout(FlowLayout.CENTER, 5, 5));
        contentPanel.add(panelBottom, BorderLayout.SOUTH);
        
        //Bouton quitter
        CustomJButton btnQuit = new CustomJButton("Quitter", 10);
        btnQuit.setBackground(new Color(231, 76, 60));
        btnQuit.setForeground(new Color(255, 255, 255));
        //btnQuit.addActionListener(this.controleur);
        panelBottom.add(btnQuit);
    }
}
