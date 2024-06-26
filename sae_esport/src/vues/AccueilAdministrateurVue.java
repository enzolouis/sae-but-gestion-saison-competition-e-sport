package vues;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.border.EmptyBorder;

import controleurs.AccueilAdministrateurControleur;
import style.CustomJButton;
import style.CustomJFrame;
import style.CustomJLabel;
import style.CustomJPanel;
import style.CustomJSeparator;
import style.Palette;

public class AccueilAdministrateurVue extends CustomJFrame {

    private CustomJPanel contentPane;
    private AccueilAdministrateurControleur controleur;
  
    
    public AccueilAdministrateurVue() {
    	
    	super(new Dimension(400, 300), "Accueil Administrateur");
    	
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	
    	pack();
    	
    	this.controleur = new AccueilAdministrateurControleur(this);

        contentPane = super.getContentPanel();
        
        // Top Panel : Titre
        CustomJPanel panelTitre = new CustomJPanel();
        contentPane.add(panelTitre, BorderLayout.NORTH);
        
        CustomJLabel titreFenetre = new CustomJLabel("PAGE D'ADMINISTRATION", 25);
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
        
        CustomJButton btnCreerUnTournoi = new CustomJButton("Créer un tournoi", 10);
        btnCreerUnTournoi.setActionCommand("creerTournoi");
        btnCreerUnTournoi.setBackground(Palette.BLUE);
        btnCreerUnTournoi.setForeground(Palette.WHITE);
        btnCreerUnTournoi.addActionListener(controleur);
        gbc_panelMiddle.gridx = 0;
        gbc_panelMiddle.gridy = 0;
        panelMiddle.add(btnCreerUnTournoi, gbc_panelMiddle);
        
        CustomJButton btnStatistiquesDeLa = new CustomJButton("Statistiques de la saison", 10);
        btnStatistiquesDeLa.setActionCommand("statsSaison");
        btnStatistiquesDeLa.setBackground(Palette.BLUE);
        btnStatistiquesDeLa.setForeground(Palette.WHITE);
        btnStatistiquesDeLa.addActionListener(controleur);
        gbc_panelMiddle.gridx = 0;
        gbc_panelMiddle.gridy = 1;
        panelMiddle.add(btnStatistiquesDeLa, gbc_panelMiddle.clone());
        
        CustomJButton btnListeTournois = new CustomJButton("Liste des tournois", 10);
        btnListeTournois.setActionCommand("listeTournois");
        btnListeTournois.setBackground(Palette.BLUE);
        btnListeTournois.setForeground(Palette.WHITE);
        btnListeTournois.addActionListener(controleur);
        gbc_panelMiddle.gridx = 0;
        gbc_panelMiddle.gridy = 2;
        panelMiddle.add(btnListeTournois, gbc_panelMiddle.clone());
        
        CustomJButton btnArbitres = new CustomJButton("Gérer les arbitres", 10);
        btnArbitres.setActionCommand("gererArbitres");
        btnArbitres.setBackground(Palette.BLUE);
        btnArbitres.setForeground(Palette.WHITE);
        btnArbitres.addActionListener(controleur);
        gbc_panelMiddle.gridx = 0;
        gbc_panelMiddle.gridy = 3;
        panelMiddle.add(btnArbitres, gbc_panelMiddle.clone());
        // Bottom Panel : Quitter & Divers
        CustomJPanel panelQuitterSeconnecter = new CustomJPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        contentPane.add(panelQuitterSeconnecter, BorderLayout.SOUTH);
        
        CustomJButton btnDeconnexion = new CustomJButton("Se déconnecter", 10);
        btnDeconnexion.setActionCommand("deconnecter");
        btnDeconnexion.setBackground(Palette.REDQUIT);
        btnDeconnexion.setForeground(Palette.WHITE);
        btnDeconnexion.addActionListener(this.controleur);
        panelQuitterSeconnecter.add(btnDeconnexion, BorderLayout.CENTER); 
        
        CustomJButton btnQuit = new CustomJButton("Quitter", 10);
        btnQuit.setActionCommand("quitter");
     // Définir la taille préférée en utilisant la largeur souhaitée et la hauteur actuelle
        btnQuit.setPreferredSize(new Dimension(btnDeconnexion.getPreferredSize().width, btnQuit.getPreferredSize().height));
        
        btnQuit.setBackground(Palette.REDQUIT);
        btnQuit.setForeground(Palette.WHITE);
        btnQuit.addActionListener(this.controleur);
        panelQuitterSeconnecter.add(btnQuit, BorderLayout.CENTER);
    }
}
