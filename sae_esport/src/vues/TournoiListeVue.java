package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import style.CustomJButton;
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
    	super(new Dimension(850, 600), "Listes des tournois");
    	
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
        JPanel panelMiddle = new CustomJPanel(new EmptyBorder(10, 0, 0, 0));
        contentPanel.add(panelMiddle, FlowLayout.CENTER);
        
        CustomJPanel panelMiddleHeaderCustomJPanel = new CustomJPanel(new EmptyBorder(10, 10, 10, 10));
        panelMiddle.add(panelMiddleHeaderCustomJPanel);
        
        CustomJLabel titleTopTest = new CustomJLabel("Listes des tournois", 25);
        panelMiddleHeaderCustomJPanel.add(titleTopTest);
        
        CustomJPanel panelRoundList = new CustomJPanel(new EmptyBorder(10, 10, 10, 10), new GridLayout(1, 0, 10, 10)); 
        CustomJScrollPane scrollpanelRoundList = new CustomJScrollPane(panelRoundList);
        scrollpanelRoundList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollpanelRoundList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panelMiddle.add(scrollpanelRoundList);
        
        GridBagLayout gbl_matchCustomJPanel = new GridBagLayout();
        gbl_matchCustomJPanel.rowHeights = new int[] {30, 60};
        gbl_matchCustomJPanel.columnWidths = new int[] {200};
        gbl_matchCustomJPanel.columnWeights = new double[]{0.0};
        gbl_matchCustomJPanel.rowWeights = new double[]{0.0, 0.0};
        
        GridBagConstraints gbc_titleMatchTop = new GridBagConstraints();
        gbc_titleMatchTop.fill = GridBagConstraints.CENTER;
        gbc_titleMatchTop.gridx = 0;
        gbc_titleMatchTop.gridy = 0;
        
        GridBagConstraints gbc_roundListEquipe = new GridBagConstraints();
        gbc_roundListEquipe.fill = GridBagConstraints.CENTER;
        gbc_roundListEquipe.gridx = 0;
        gbc_roundListEquipe.gridy = 1;
        
        for (int i = 1; i <= 10; i++) {
        	// Panel Match : Titre + Panel Liste Equipe
        	CustomJPanel MatchCustomJPanel = new CustomJPanel(new EmptyBorder(10, 10, 10, 10), new GridLayout(2, 1, 10, 10));
        	MatchCustomJPanel.setLayout(gbl_matchCustomJPanel);
        	panelRoundList.add(MatchCustomJPanel);
        	
        	// Numero Round
            CustomJLabel titleMatchTop = new CustomJLabel("Round n°" + i, 25);
            MatchCustomJPanel.add(titleMatchTop, gbc_titleMatchTop);
            
            // Panel Equipe Liste
            CustomJPanel MatchEquipeListJPanel = new CustomJPanel(new EmptyBorder(10, 10, 10, 10), new GridLayout(0, 2, 10, 10));
            MatchCustomJPanel.add(MatchEquipeListJPanel, gbc_roundListEquipe);
        	
        	for (int j = 1; j <= 8; j++) {
            	CustomJButton button = new CustomJButton("Équipe n°" + j, 15);
            	//button.addActionListener(this.controleur);
            	MatchEquipeListJPanel.add(button);
    		}
		}
        
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
