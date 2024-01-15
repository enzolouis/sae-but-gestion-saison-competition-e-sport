package vues;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleurs.AccueilArbitreControleur;
import style.CustomJButton;
import style.CustomJFrame;
import style.CustomJLabel;
import style.CustomJPanel;
import style.CustomJSeparator;
import style.Palette;

public class AccueilArbitreVue extends CustomJFrame {

    private JPanel contentPane;
    private AccueilArbitreControleur controleur;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
        		AccueilArbitreVue frame = new AccueilArbitreVue();
				frame.setVisible(true);
            }
        });
    }
    
    public AccueilArbitreVue() {
    	super(new Dimension(400, 300), "Accueil Arbitre");
    	
    	pack();
    	
    	this.controleur = new AccueilArbitreControleur(this);
    	
        contentPane = this.getContentPanel();
        
        // Top Panel : Titre
        CustomJPanel panelTitre = new CustomJPanel();
        contentPane.add(panelTitre, BorderLayout.NORTH);
        
        CustomJLabel titreFenetre = new CustomJLabel("PAGE D'ARBITRAGE", 25);
        titreFenetre.setFont(Palette.customFont.deriveFont(Font.BOLD, 20));
        titreFenetre.setForeground(Palette.WHITE);
        panelTitre.add(titreFenetre, BorderLayout.CENTER);
        
        CustomJSeparator separatorTitre = new CustomJSeparator();
        panelTitre.add(separatorTitre, BorderLayout.SOUTH);
        
        // Middle Panel : Actions / Sujet
        CustomJPanel panelMiddle = new CustomJPanel(new EmptyBorder(5, 5, 5, 5));
        
        GridBagLayout gbl_panelMiddle = new GridBagLayout(); 
        panelMiddle.setLayout(gbl_panelMiddle);
        contentPane.add(panelMiddle, BorderLayout.CENTER);
        
        GridBagConstraints gbc_panelMiddle = new GridBagConstraints();
        gbc_panelMiddle.weightx = 1.0;
        gbc_panelMiddle.weighty = 1.0;
        
        CustomJButton btnCreerUnTournoi = new CustomJButton("Accès au tournoi", 10);
        btnCreerUnTournoi.setBackground(Palette.BLUE);
        btnCreerUnTournoi.setForeground(Palette.WHITE);
        btnCreerUnTournoi.addActionListener(controleur);
        gbc_panelMiddle.gridx = 0;
        gbc_panelMiddle.gridy = 0;
        panelMiddle.add(btnCreerUnTournoi, gbc_panelMiddle);
        
        CustomJButton btnStatistiquesDeLa = new CustomJButton("Statistiques du tournoi", 10);
        btnStatistiquesDeLa.setBackground(Palette.BLUE);
        btnStatistiquesDeLa.setForeground(Palette.WHITE);
        btnStatistiquesDeLa.addActionListener(controleur);
        gbc_panelMiddle.gridx = 0;
        gbc_panelMiddle.gridy = 1;
        panelMiddle.add(btnStatistiquesDeLa, gbc_panelMiddle.clone());
        
        // Bottom Panel : Quitter & Divers
        CustomJPanel panelQuitterSeconnecter = new CustomJPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        contentPane.add(panelQuitterSeconnecter, BorderLayout.SOUTH);
        
        CustomJButton btnDeconnexion = new CustomJButton("Se déconnecter", 10);
        btnDeconnexion.setBackground(Palette.REDQUIT);
        btnDeconnexion.setForeground(Palette.WHITE);
        btnDeconnexion.addActionListener(this.controleur);
        panelQuitterSeconnecter.add(btnDeconnexion, BorderLayout.CENTER); 
        
        CustomJButton btnQuit = new CustomJButton("Quitter", 10);
        btnQuit.setBackground(Palette.REDQUIT);
        btnQuit.setForeground(Palette.WHITE);
        btnQuit.addActionListener(this.controleur);
        panelQuitterSeconnecter.add(btnQuit, BorderLayout.CENTER); 
    }
}
