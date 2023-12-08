package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controleurs.IdentificationControleur;
import style.CustomJButton;
import style.CustomJFrame;
import style.CustomJLabel;
import style.CustomJPanel;
import style.CustomJSeparator;
import style.CustomJTextField;

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
    	
    	this.controleur = new IdentificationControleur(this);
    	contentPane = super.getContentPanel();
        
    	CustomJPanel panelUtilisateurMotDePasse = new CustomJPanel(new EmptyBorder(10, 10, 10, 10), new GridLayout(2, 0, 0, 0));
        contentPane.add(panelUtilisateurMotDePasse, BorderLayout.CENTER);
        
        CustomJPanel panelUtilisateur = new CustomJPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelUtilisateurMotDePasse.add(panelUtilisateur);
        
        CustomJLabel labelNomUtilisateur = new CustomJLabel("Nom d'utilisateur :", 10);
        labelNomUtilisateur.setPreferredSize(new Dimension(110, 30));
        panelUtilisateur.add(labelNomUtilisateur);
        
        CustomJPanel panelTextFieldNomUtilisateur = new CustomJPanel((EmptyBorder) null);
        panelTextFieldNomUtilisateur.setBackground(new Color(29, 88, 129));
        panelUtilisateur.add(panelTextFieldNomUtilisateur);
        
        textFieldUtilisateur = new CustomJTextField("Login", 13, (EmptyBorder) null);
        textFieldUtilisateur.addActionListener(controleur);
        panelTextFieldNomUtilisateur.add(textFieldUtilisateur);
        
        CustomJPanel panelMotDePasse = new CustomJPanel();
        panelUtilisateurMotDePasse.add(panelMotDePasse);
        
        CustomJLabel labelMotDePasse = new CustomJLabel("Mot de passe :", 10);
        labelMotDePasse.setPreferredSize(new Dimension(110, 30));
        panelMotDePasse.add(labelMotDePasse);
        
        CustomJPanel panelTextFieldMotDePasse = new CustomJPanel();
        FlowLayout fl_panelTextFieldMotDePasse = (FlowLayout) panelTextFieldMotDePasse.getLayout();
        fl_panelTextFieldMotDePasse.setVgap(0);
        fl_panelTextFieldMotDePasse.setHgap(0);
        panelTextFieldMotDePasse.setBackground(new Color(29, 88, 129));
        panelMotDePasse.add(panelTextFieldMotDePasse);
        
        textFieldMotDePasse = new JPasswordField();
        textFieldMotDePasse.addActionListener(controleur);
        textFieldMotDePasse.setForeground(Color.WHITE);
        textFieldMotDePasse.setColumns(10);
        textFieldMotDePasse.setBorder(new EmptyBorder(5, 5, 5, 5));
        textFieldMotDePasse.setBackground(new Color(29, 88, 129));
        panelTextFieldMotDePasse.add(textFieldMotDePasse);
        
        JButton btnMotDePasseVisibilite = new CustomJButton("", 5, (EmptyBorder) null);
        btnMotDePasseVisibilite.addActionListener(controleur);
        btnMotDePasseVisibilite.setBackground(new Color(29, 88, 129));
        btnMotDePasseVisibilite.setIcon(OEIL_INVISIBLE_ICON);
        panelTextFieldMotDePasse.add(btnMotDePasseVisibilite);
        
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
        
        CustomJPanel panelTitre = new CustomJPanel();
        contentPane.add(panelTitre, BorderLayout.NORTH);

        
        CustomJSeparator separatorTitre = new CustomJSeparator();
        panelTitre.add(separatorTitre, BorderLayout.SOUTH);
        
        CustomJLabel titreFenetre = new CustomJLabel("CONNEXION", 10, new EmptyBorder(10, 10, 10, 10));
        titreFenetre.setHorizontalAlignment(SwingConstants.CENTER);
        panelTitre.add(titreFenetre);
    }
}
