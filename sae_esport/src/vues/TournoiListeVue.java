package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;

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
    	super(new Dimension(600, 900), "Listes des tournois");
    	
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
        contentPanel.add(panelMiddle);
        
        CustomJPanel panelRoundList = new CustomJPanel(new EmptyBorder(10, 10, 10, 10), new GridLayout(0, 2, 10, 10)); 
        CustomJScrollPane scrollpanelRoundList = new CustomJScrollPane(panelRoundList);
        scrollpanelRoundList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollpanelRoundList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panelMiddle.add(scrollpanelRoundList);
        
        for (int i = 1; i <= 10; i++) {
        	// Panel Match : Titre + Panel Liste Equipe
        	CustomJPanel MatchCustomJPanel = new CustomJPanel(new EmptyBorder(10, 10, 10, 10), new GridLayout(0, 1, 10, 10));
        	panelRoundList.add(MatchCustomJPanel, BorderLayout.WEST);
        	
        	// Numero Round
            CustomJLabel titleMatchTop = new CustomJLabel("Round n°" + i, 25);
            panelRoundList.add(titleMatchTop, BorderLayout.NORTH);
            
            // Panel Equipe Liste
            CustomJPanel MatchEquipeListJPanel = new CustomJPanel(new EmptyBorder(10, 10, 10, 10), new GridLayout(0, 2, 10, 10));
            panelRoundList.add(MatchEquipeListJPanel, BorderLayout.SOUTH);
        	
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
