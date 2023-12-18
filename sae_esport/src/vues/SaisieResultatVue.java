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
import javax.swing.SwingConstants;

public class SaisieResultatVue extends CustomJFrame {
	private CustomJPanel contentPanel;
    //private TournoiListeControleur controleur;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	SaisieResultatVue frame = new SaisieResultatVue();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public SaisieResultatVue() throws Exception {
    	super(new Dimension(975, 600), "Résultat des matchs");
    	
    	pack();
    	
    	//this.controleur = new TournoiListeControleur(this);
    	contentPanel = this.getContentPanel();
        
        // Panel Top : Title
    	CustomJPanel panelTop = new CustomJPanel();
        contentPanel.add(panelTop, BorderLayout.NORTH);
        
        CustomJLabel titleTop = new CustomJLabel("Résultat des matchs", 25);
        panelTop.add(titleTop);
        
        CustomJSeparator separatorTop = new CustomJSeparator();
        panelTop.add(separatorTop, BorderLayout.SOUTH);
        
        // Panel middle : Action et Listes
        GridBagLayout gbl_panelMiddle = new GridBagLayout();
        gbl_panelMiddle.rowHeights = new int[] {50, 0, 50};
        gbl_panelMiddle.columnWidths = new int[] {0};
        gbl_panelMiddle.columnWeights = new double[]{1.0};
        gbl_panelMiddle.rowWeights = new double[]{0.0, 1.0, 0.0};
        JPanel panelMiddle = new CustomJPanel(new EmptyBorder(0, 10, 0, 10));
        panelMiddle.setLayout(gbl_panelMiddle);
        contentPanel.add(panelMiddle);
        
        // Panel Middle Finale
        CustomJPanel panelMiddleFinal = new CustomJPanel(new EmptyBorder(10, 0, 10, 0), new GridLayout(0, 1, 10, 10));
        GridBagConstraints gbc_panelMiddleFinal = new GridBagConstraints();
        gbc_panelMiddleFinal.anchor = GridBagConstraints.NORTH;
        gbc_panelMiddleFinal.fill = GridBagConstraints.HORIZONTAL;
        gbc_panelMiddleFinal.gridx = 0;
        gbc_panelMiddleFinal.gridy = 0;
        panelMiddle.add(panelMiddleFinal, gbc_panelMiddleFinal);
        
        CustomJLabel middleLabeFinale = new CustomJLabel("Finale :", 25);
        middleLabeFinale.setHorizontalAlignment(SwingConstants.LEFT);
        panelMiddleFinal.add(middleLabeFinale);
        
        CustomJPanel panelMiddleFinalEquipe = new CustomJPanel(new EmptyBorder(0, 0, 0, 0), new GridLayout(1, 2, 10, 10));
        panelMiddleFinal.add(panelMiddleFinalEquipe);
        CustomJButton button1Finale = new CustomJButton("Équipe n°1", 15);
    	//button.addActionListener(this.controleur);
        panelMiddleFinalEquipe.add(button1Finale);
    	
       	CustomJButton button2Finale = new CustomJButton("Équipe n°2", 15);
    	//button.addActionListener(this.controleur);
       	panelMiddleFinalEquipe.add(button2Finale);
        
       	// Panel Middle Match
       	CustomJPanel panelMiddleMatch = new CustomJPanel(new EmptyBorder(10, 0, 10, 0));
        GridBagLayout gbl_panelMiddleMatch = new GridBagLayout();
        gbl_panelMiddleMatch.rowHeights = new int[] {30, 0};
        gbl_panelMiddleMatch.columnWidths = new int[] {0};
        gbl_panelMiddleMatch.columnWeights = new double[]{1.0};
        gbl_panelMiddleMatch.rowWeights = new double[]{0.0, 1.0};
        panelMiddleMatch.setLayout(gbl_panelMiddleMatch);
        GridBagConstraints gbc_panelMiddleHeaderCustomJPanel = new GridBagConstraints();
        gbc_panelMiddleHeaderCustomJPanel.weighty = 1.0;
        gbc_panelMiddleHeaderCustomJPanel.fill = GridBagConstraints.BOTH;
        gbc_panelMiddleHeaderCustomJPanel.gridx = 0;
        gbc_panelMiddleHeaderCustomJPanel.gridy = 1;
        panelMiddle.add(panelMiddleMatch, gbc_panelMiddleHeaderCustomJPanel);
        
        CustomJLabel middleLabelMatch = new CustomJLabel("Matchs :", 25);
        GridBagConstraints gbc_middleLabelMatch = new GridBagConstraints();
        gbc_middleLabelMatch.anchor = GridBagConstraints.NORTHWEST;
        gbc_middleLabelMatch.gridx = 0;
        gbc_middleLabelMatch.gridy = 0;
        panelMiddleMatch.add(middleLabelMatch, gbc_middleLabelMatch);
        
        CustomJPanel panelRoundList = new CustomJPanel(new EmptyBorder(10, 10, 10, 10), new GridLayout(1, 0, 10, 10)); 
        CustomJScrollPane scrollpanelRoundList = new CustomJScrollPane(panelRoundList);
        scrollpanelRoundList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollpanelRoundList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        GridBagConstraints gbc_panelRoundList = new GridBagConstraints();
        gbc_panelRoundList.fill = GridBagConstraints.BOTH;
        gbc_panelRoundList.gridx = 0;
        gbc_panelRoundList.gridy = 1;
        panelMiddleMatch.add(scrollpanelRoundList, gbc_panelRoundList);
        
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
        	
        	for (int j = 1; j <= 4; j++) {
            	CustomJButton button1 = new CustomJButton("Équipe n°1", 15);
            	//button.addActionListener(this.controleur);
            	MatchEquipeListJPanel.add(button1);
            	
               	CustomJButton button2 = new CustomJButton("Équipe n°2", 15);
            	//button.addActionListener(this.controleur);
            	MatchEquipeListJPanel.add(button2);
    		}
		}
        
        // Panel middle bottom : Action tournoi
        CustomJPanel panelMiddleBottom = new CustomJPanel(new EmptyBorder(10, 10, 10, 10), new FlowLayout(FlowLayout.CENTER, 5, 5));
        GridBagConstraints gbc_panelMiddleBottom = new GridBagConstraints();
        gbc_panelMiddleBottom.anchor = GridBagConstraints.SOUTHEAST;
        gbc_panelMiddleBottom.gridx = 0;
        gbc_panelMiddleBottom.gridy = 2;
        panelMiddle.add(panelMiddleBottom, gbc_panelMiddleBottom);
    	
    	CustomJButton buttonOpen = new CustomJButton("Ouvrir la finale", 15);
        GridBagConstraints gbc_buttonOpen = new GridBagConstraints();
        gbc_buttonOpen.fill = GridBagConstraints.CENTER;
        gbc_buttonOpen.gridx = 0;
        gbc_buttonOpen.gridy = 0;
        gbc_buttonOpen.anchor = GridBagConstraints.EAST;
    	//button.addActionListener(this.controleur);
    	panelMiddleBottom.add(buttonOpen, gbc_buttonOpen);
    	
    	CustomJButton buttonClose = new CustomJButton("Fermer le tournoi", 15);
        GridBagConstraints gbc_buttonClose = new GridBagConstraints();
        gbc_buttonClose.anchor = GridBagConstraints.EAST;
        gbc_buttonClose.gridx = 1;
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