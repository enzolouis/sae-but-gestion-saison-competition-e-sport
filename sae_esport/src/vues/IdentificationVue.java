package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controleurs.ChoixArbitreControleur;
import controleurs.IdentificationControleur;
import style.CustomJButton;
import style.CustomJFrame;
import style.CustomJLabel;
import style.CustomJPanel;
import style.CustomJSeparator;
import style.CustomJTextField;
import java.awt.Rectangle;

public class IdentificationVue extends CustomJFrame {

	public static final ImageIcon OEIL_INVISIBLE_ICON = new ImageIcon(new ImageIcon(IdentificationVue.class.getClassLoader().getResource
			("oeilMotDePasseInvisible.png")).getImage().getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH));
	
	public static final ImageIcon OEIL_VISIBLE_ICON = new ImageIcon(new ImageIcon(IdentificationVue.class.getClassLoader().getResource
			("oeilMotDePasseVisible.png")).getImage().getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH));
	
    private JPanel contentPane;
    private IdentificationControleur controleur;
    private CustomJTextField textFieldUtilisateur;
    private JPasswordField textFieldMotDePasse;
    
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
    
    public String getUtilisateurContenu() {
    	return textFieldUtilisateur.getText();
    }
    
    public String getMotDePasseContenu() {
    	return String.valueOf(textFieldMotDePasse.getPassword());
    }
    
    public JPasswordField getMotDePasse() {
    	return textFieldMotDePasse;
    }
    
    public IdentificationVue() throws Exception {
    	super(new Dimension(450, 300), "Identification");
    	
    	pack();
    	
    	this.controleur = new IdentificationControleur(this);
    	contentPane = super.getContentPanel();
        
        // Panel Top : Title
    	CustomJPanel panelTop = new CustomJPanel();
        contentPane.add(panelTop, BorderLayout.NORTH);
        
        CustomJLabel titleTop = new CustomJLabel("Connexion", 25);
        panelTop.add(titleTop);
        
        CustomJSeparator separatorTop = new CustomJSeparator();
        panelTop.add(separatorTop, BorderLayout.SOUTH);
    	
        // Middle Panel : Login
    	CustomJPanel panelMiddleLogin = new CustomJPanel(new EmptyBorder(10, 10, 10, 10), new GridLayout(2, 0, 0, 0));
        contentPane.add(panelMiddleLogin, BorderLayout.CENTER);
        
        // Utilisateur
        CustomJPanel panelUtilisateur = new CustomJPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelMiddleLogin.add(panelUtilisateur);
        
        CustomJLabel labelNomUtilisateur = new CustomJLabel("Nom d'utilisateur :", 12);
        panelUtilisateur.add(labelNomUtilisateur);
        
        this.textFieldUtilisateur = new CustomJTextField(10, new EmptyBorder(10, 10, 10, 10), "Login");
        textFieldUtilisateur.addActionListener(controleur);
        panelUtilisateur.add(textFieldUtilisateur);
        
        // Mot de passe
        CustomJPanel panelTextFieldMotDePasse = new CustomJPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelMiddleLogin.add(panelTextFieldMotDePasse);
        
        CustomJLabel labelMotDePasse = new CustomJLabel("Mot de passe :", 12);
        panelTextFieldMotDePasse.add(labelMotDePasse);
        
        this.textFieldMotDePasse = new JPasswordField();
        textFieldMotDePasse.setBackground(new Color(29, 88, 129));
        textFieldMotDePasse.setSelectedTextColor(new Color(29, 88, 129));
        textFieldMotDePasse.setPreferredSize(new Dimension(100, 34));
        this.textFieldMotDePasse.setSize(new Dimension(150, 20));
        this.textFieldMotDePasse.setMinimumSize(new Dimension(150, 20));
        this.textFieldMotDePasse.addActionListener(controleur);
        panelTextFieldMotDePasse.add(textFieldMotDePasse);
        
        CustomJButton btnMotDePasseVisibilite = new CustomJButton("", 25, new EmptyBorder(0, 0, 0, 0));
        btnMotDePasseVisibilite.addActionListener(controleur);
        btnMotDePasseVisibilite.setIcon(OEIL_INVISIBLE_ICON);
        panelTextFieldMotDePasse.add(btnMotDePasseVisibilite);
        
        // Bottom Panel : Quitter + Login
        CustomJPanel panelQuitterSeconnecter = new CustomJPanel(new EmptyBorder(10, 10, 10, 10), new FlowLayout(FlowLayout.CENTER, 5, 5));
        contentPane.add(panelQuitterSeconnecter, BorderLayout.SOUTH);
        
        CustomJButton btnQuit = new CustomJButton("Quitter", 5);
        btnQuit.setBackground(new Color(231, 76, 60));
        btnQuit.setForeground(new Color(255, 255, 255));
        btnQuit.addActionListener(this.controleur);
        panelQuitterSeconnecter.add(btnQuit);
        
        CustomJButton btnLogin = new CustomJButton("Se connecter", 5);
        btnLogin.setForeground(new Color(255, 255, 255));
        btnLogin.setBackground(new Color(46, 204, 113));
        btnLogin.addActionListener(controleur);
        panelQuitterSeconnecter.add(btnLogin);
    }
}
