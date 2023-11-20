package vues;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import controleurs.IdentificationControleur;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.SwingConstants;
import java.awt.Dimension;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JSeparator;

public class IdentificationVue extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldMotDePasse;
    private JTextField textFieldUtilisateur;
    private IdentificationControleur controleur;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    IdentificationVue frame = new IdentificationVue();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * @throws Exception 
     */
    public IdentificationVue() throws Exception {
    	
    	this.controleur = new IdentificationControleur(this);
    	
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
        
        JLabel labelNomUtilisateur = new JLabel("Nom d'utilisateur :");
        labelNomUtilisateur.setForeground(new Color(255, 255, 255));
        labelNomUtilisateur.setPreferredSize(new Dimension(110, 30));
        panelUtilisateur.add(labelNomUtilisateur);
        
        textFieldUtilisateur = new JTextField();
        textFieldUtilisateur.setBorder(new EmptyBorder(5, 5, 5, 5));
        textFieldUtilisateur.setForeground(new Color(255, 255, 255));
        textFieldUtilisateur.setBackground(new Color(29, 88, 129));
        panelUtilisateur.add(textFieldUtilisateur);
        textFieldUtilisateur.setColumns(10);
        
        JPanel panelMotDePasse = new JPanel();
        panelMotDePasse.setBackground(new Color(44, 47, 51));
        panelUtilisateurMotDePasse.add(panelMotDePasse);
        
        JLabel labelMotDePasse = new JLabel("Mot de passe :");
        labelMotDePasse.setForeground(new Color(255, 255, 255));
        labelMotDePasse.setPreferredSize(new Dimension(110, 30));
        panelMotDePasse.add(labelMotDePasse);
        
        textFieldMotDePasse = new JTextField();
        textFieldMotDePasse.setBorder(new EmptyBorder(5, 5, 5, 5));
        textFieldMotDePasse.setBackground(new Color(29, 88, 129));
        textFieldMotDePasse.setForeground(new Color(255, 255, 255));
        panelMotDePasse.add(textFieldMotDePasse);
        textFieldMotDePasse.setColumns(10);
        
        JPanel panelQuitterSeconnecter = new JPanel();
        panelQuitterSeconnecter.setBackground(new Color(44, 47, 51));
        panelQuitterSeconnecter.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.add(panelQuitterSeconnecter, BorderLayout.SOUTH);
        panelQuitterSeconnecter.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JButton btnQuit = new JButton("Quitter");
        btnQuit.setBackground(new Color(231, 76, 60));
        btnQuit.setForeground(new Color(255, 255, 255));
        btnQuit.setBorder(new RoundBtn(5));
        btnQuit.addActionListener(this.controleur);
        panelQuitterSeconnecter.add(btnQuit);
        
        JButton btnLogin = new JButton("Se connecter");
        btnLogin.setForeground(new Color(255, 255, 255));
        btnLogin.setBackground(new Color(46, 204, 113));
        btnLogin.setBorder(new RoundBtn(5));
        btnLogin.addActionListener(controleur);
        panelQuitterSeconnecter.add(btnLogin);
        
        JPanel panelTitre = new JPanel();
        panelTitre.setBackground(new Color(44, 47, 51));
        contentPane.add(panelTitre, BorderLayout.NORTH);
        panelTitre.setLayout(new BorderLayout(0, 0));
        
        JSeparator separatorTitre = new JSeparator();
        panelTitre.add(separatorTitre, BorderLayout.SOUTH);
        
        JLabel titreFenetre = new JLabel("CONNEXION");
        titreFenetre.setHorizontalAlignment(SwingConstants.CENTER);
        titreFenetre.setForeground(new Color(41, 128, 185));
        titreFenetre.setFont(new Font("Tahoma", Font.BOLD, 25));
        titreFenetre.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelTitre.add(titreFenetre);
    }
    
    class RoundBtn implements Border 
    {
        private int r;
        RoundBtn(int r) {
            this.r = r;
        }
        public Insets getBorderInsets(Component c) {
            return new Insets(this.r+1, this.r+1, this.r+2, this.r);
        }
        public boolean isBorderOpaque() {
            return true;
        }
        public void paintBorder(Component c, Graphics g, int x, int y, 
        int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, r, r);
        }
    }
    
    public String getUtilisateurContent() {
    	return this.textFieldUtilisateur.getText();
    }
    
    public String getMotDePasseContent() {
    	return this.textFieldMotDePasse.getText();
    }
    
}
