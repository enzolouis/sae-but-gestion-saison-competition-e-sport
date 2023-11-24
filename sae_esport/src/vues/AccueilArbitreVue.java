package vues;

import java.awt.EventQueue;

import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import controleurs.AccueilArbitreControleur;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.SwingConstants;
import java.awt.Dimension;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JSeparator;

public class AccueilArbitreVue extends JFrame {

    private JPanel contentPane;
    private AccueilArbitreControleur controleur;
    private Connection dbConnection;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	String dirProjetJava = System.getProperty("user.dir");
            		System.setProperty("derby.system.home", dirProjetJava+"/BDDSAEEsport");
            		DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
        			String urlConnexion = "jdbc:derby:BDDSAEEsport;create=true";
        			Connection dbConnection = DriverManager.getConnection(urlConnexion);
        			AccueilArbitreVue frame = new AccueilArbitreVue(dbConnection);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public AccueilArbitreVue(Connection dbConnection) throws Exception {
    	
    	this.dbConnection = dbConnection;
    	this.controleur = new AccueilArbitreControleur(this, dbConnection);
    	
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
        
        JButton btnCrerUnTournoi = new JButton("Accès aux tournois");
        btnCrerUnTournoi.setForeground(Color.WHITE);
        btnCrerUnTournoi.setBorder(new RoundBtn(5));
        btnCrerUnTournoi.setBackground(new Color(102, 173, 221));
        panelUtilisateur.add(btnCrerUnTournoi);
        
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
        
        JLabel titreFenetre = new JLabel("Page d'arbitrage");
        titreFenetre.setHorizontalAlignment(SwingConstants.CENTER);
        titreFenetre.setForeground(new Color(102, 173, 221));
        titreFenetre.setFont(new Font("Tahoma", Font.BOLD, 25));
        titreFenetre.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelTitre.add(titreFenetre);
    }
}
