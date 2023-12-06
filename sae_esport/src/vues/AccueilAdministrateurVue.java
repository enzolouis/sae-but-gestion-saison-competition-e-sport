package vues;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleurs.AccueilAdministrateurControleur;
import style.RoundBtn;

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

public class AccueilAdministrateurVue extends JFrame {

    private JPanel contentPane;
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
    	
    	this.controleur = new AccueilAdministrateurControleur(this);
    	
    	setMinimumSize(new Dimension(450, 300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(44, 47, 51));
        contentPane.setBorder(new EmptyBorder(30, 30, 30, 30));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel panelUtilisateurMotDePasse = new JPanel();
        panelUtilisateurMotDePasse.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelUtilisateurMotDePasse.setBackground(new Color(44, 47, 51));
        contentPane.add(panelUtilisateurMotDePasse, BorderLayout.CENTER);
        panelUtilisateurMotDePasse.setLayout(new GridLayout(2, 0, 0, 0));
        
        JPanel panelUtilisateur = new JPanel();
        panelUtilisateur.setBackground(new Color(44, 47, 51));
        panelUtilisateurMotDePasse.add(panelUtilisateur);
        panelUtilisateur.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JButton btnCreerUnTournoi = new JButton("Créer un tournoi");
        btnCreerUnTournoi.addActionListener(controleur);
        btnCreerUnTournoi.setForeground(Color.WHITE);
        btnCreerUnTournoi.setBorder(new RoundBtn(5));
        btnCreerUnTournoi.setBackground(new Color(102, 173, 221));
        panelUtilisateur.add(btnCreerUnTournoi);
        
        JPanel panelMotDePasse = new JPanel();
        panelMotDePasse.setBackground(new Color(44, 47, 51));
        panelUtilisateurMotDePasse.add(panelMotDePasse);
        
        JButton btnStatistiquesDeLa = new JButton("Statistiques de la saison");
        btnStatistiquesDeLa.setForeground(Color.WHITE);
        btnStatistiquesDeLa.setBorder(new RoundBtn(5));
        btnStatistiquesDeLa.setBackground(new Color(102, 173, 221));
        panelMotDePasse.add(btnStatistiquesDeLa);
        
        JPanel panelQuitterSeconnecter = new JPanel();
        panelQuitterSeconnecter.setBackground(new Color(44, 47, 51));
        panelQuitterSeconnecter.setBorder(new EmptyBorder(10, 10, 0, 10));
        contentPane.add(panelQuitterSeconnecter, BorderLayout.SOUTH);
        panelQuitterSeconnecter.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JButton btnQuit = new JButton("Quitter");
        btnQuit.setBackground(new Color(231, 76, 60));
        btnQuit.setForeground(new Color(255, 255, 255));
        btnQuit.setBorder(new RoundBtn(5));
        btnQuit.addActionListener(this.controleur);
        panelQuitterSeconnecter.add(btnQuit);
        
        JPanel panelTitre = new JPanel();
        panelTitre.setBackground(new Color(44, 47, 51));
        contentPane.add(panelTitre, BorderLayout.NORTH);
        panelTitre.setLayout(new BorderLayout(0, 0));
        
        JSeparator separatorTitre = new JSeparator();
        separatorTitre.setBackground(new Color(102, 173, 221));
        separatorTitre.setForeground(new Color(102, 173, 221));
        panelTitre.add(separatorTitre, BorderLayout.SOUTH);
        
        JLabel titreFenetre = new JLabel("Page d'administration");
        titreFenetre.setHorizontalAlignment(SwingConstants.CENTER);
        titreFenetre.setForeground(new Color(102, 173, 221));
        titreFenetre.setFont(new Font("Tahoma", Font.BOLD, 25));
        titreFenetre.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelTitre.add(titreFenetre);
    }
}
