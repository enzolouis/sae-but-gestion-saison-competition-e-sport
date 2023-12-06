package vues;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleurs.AccueilAdministrateurControleur;
import style.CustomBorder;
import style.CustomJFrame;
import style.CustomJLabel;
import style.CustomJPanel;
import style.CustomJSeparator;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Dimension;

import java.awt.Color;
import javax.swing.JSeparator;

public class AccueilAdministrateurVue extends CustomJFrame {

    private CustomJPanel contentPane;
    private AccueilAdministrateurControleur controleur;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
        		AccueilAdministrateurVue frame = new AccueilAdministrateurVue();
                frame.setVisible(true);
            }
        });
    }
    
    public AccueilAdministrateurVue() {
    	super(new int[] {450, 300});
    	
    	this.controleur = new AccueilAdministrateurControleur(this);

        contentPane = this.getContentPanel();
        setContentPane(contentPane);
        
        CustomJPanel panelUtilisateurMotDePasse = new CustomJPanel(new int[] {10, 10, 10, 10});
        panelUtilisateurMotDePasse.setLayout(new GridLayout(2, 0, 0, 0));
        contentPane.add(panelUtilisateurMotDePasse, BorderLayout.CENTER);
        
        CustomJPanel panelUtilisateur = new CustomJPanel();
        panelUtilisateur.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelUtilisateurMotDePasse.add(panelUtilisateur);
        
        JButton btnCreerUnTournoi = new JButton("Créer un tournoi");
        btnCreerUnTournoi.addActionListener(controleur);
        btnCreerUnTournoi.setForeground(Color.WHITE);
        btnCreerUnTournoi.setBorder(new CustomBorder(5));
        btnCreerUnTournoi.setBackground(new Color(102, 173, 221));
        panelUtilisateur.add(btnCreerUnTournoi);
        
        CustomJPanel panelMotDePasse = new CustomJPanel();
        panelMotDePasse.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelUtilisateurMotDePasse.add(panelMotDePasse);
        
        JButton btnStatistiquesDeLa = new JButton("Statistiques de la saison");
        btnStatistiquesDeLa.setForeground(Color.WHITE);
        btnStatistiquesDeLa.setBorder(new CustomBorder(5));
        btnStatistiquesDeLa.setBackground(new Color(102, 173, 221));
        panelMotDePasse.add(btnStatistiquesDeLa);
        
        CustomJPanel panelQuitterSeconnecter = new CustomJPanel(new int[] {10, 10, 0, 10});
        panelQuitterSeconnecter.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        contentPane.add(panelQuitterSeconnecter, BorderLayout.SOUTH);
        
        JButton btnQuit = new JButton("Quitter");
        btnQuit.setBackground(new Color(231, 76, 60));
        btnQuit.setForeground(new Color(255, 255, 255));
        btnQuit.setBorder(new CustomBorder(5));
        btnQuit.addActionListener(this.controleur);
        panelQuitterSeconnecter.add(btnQuit);
        
        CustomJPanel panelTitre = new CustomJPanel();
        contentPane.add(panelTitre, BorderLayout.NORTH);
        
        CustomJSeparator separatorTitre = new CustomJSeparator();
        panelTitre.add(separatorTitre, BorderLayout.SOUTH);
        
        CustomJLabel titreFenetre = new CustomJLabel("Page d'administration", 25, new int[] {10, 10, 10, 10});
        panelTitre.add(titreFenetre);
    }
}
