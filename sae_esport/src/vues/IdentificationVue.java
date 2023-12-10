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
import javax.swing.JTextField;
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
    
    private JTextField textFieldUtilisateur;
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
        labelNomUtilisateur.setHorizontalAlignment(SwingConstants.LEFT);
        labelNomUtilisateur.setPreferredSize(new Dimension(110, 30));
        panelUtilisateur.add(labelNomUtilisateur);
        
        JPanel panelTextFieldUtilisateur = new CustomJPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelTextFieldUtilisateur.setBackground(new Color(29, 88, 129));
        panelTextFieldUtilisateur.setBorder(null);
        panelUtilisateur.add(panelTextFieldUtilisateur);
        
        // a fix
        //textFieldUtilisateur = new CustomJTextField(10, (EmptyBorder) null, "Login");
        //panelTextFieldUtilisateur.add(textFieldUtilisateur);
        
        textFieldUtilisateur = new JTextField();
        textFieldUtilisateur.addActionListener(controleur);
        //textFieldUtilisateur.setPlaceholder("login");
        textFieldUtilisateur.setForeground(Color.WHITE);
        textFieldUtilisateur.setColumns(13);
        textFieldUtilisateur.setBorder(null);
        textFieldUtilisateur.setBackground(new Color(29, 88, 129));
        panelTextFieldUtilisateur.add(textFieldUtilisateur);
        
        // Mot de passe
        CustomJPanel panelMotDePasse = new CustomJPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelMiddleLogin.add(panelMotDePasse);
        
        CustomJLabel labelMotDePasse = new CustomJLabel("Mot de passe :", 12);
        labelMotDePasse.setHorizontalAlignment(SwingConstants.LEFT);
        labelMotDePasse.setPreferredSize(new Dimension(110, 30));
        panelMotDePasse.add(labelMotDePasse);
        
        
        // 
        JPanel panelTextFieldMotDePasse = new CustomJPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelTextFieldMotDePasse.setBorder(null);
        panelTextFieldMotDePasse.setBackground(new Color(29, 88, 129));
        panelMotDePasse.add(panelTextFieldMotDePasse);

        textFieldMotDePasse = new JPasswordField();
        textFieldMotDePasse.addActionListener(controleur);
        textFieldMotDePasse.setForeground(Color.WHITE);
        textFieldMotDePasse.setColumns(10);
        textFieldMotDePasse.setBorder(new EmptyBorder(5, 5, 5, 5));
        textFieldMotDePasse.setBackground(new Color(29, 88, 129));
        panelTextFieldMotDePasse.add(textFieldMotDePasse);
        
        
        JButton btnMotDePasseVisibilite = new CustomJButton("", 25, (EmptyBorder) null);
        btnMotDePasseVisibilite.addActionListener(controleur);
        btnMotDePasseVisibilite.setBackground(new Color(29, 88, 129));
        btnMotDePasseVisibilite.setBorder(null);
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
