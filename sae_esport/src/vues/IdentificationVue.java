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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controleurs.IdentificationControleur;
import style.CustomJTextField;
import style.CustomBorder;

public class IdentificationVue extends JFrame {

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
        labelNomUtilisateur.setForeground(new Color(102, 173, 221));
        labelNomUtilisateur.setPreferredSize(new Dimension(110, 30));
        panelUtilisateur.add(labelNomUtilisateur);
        
        JPanel panelTextFieldNomUtilisateur = new JPanel();
        panelTextFieldNomUtilisateur.setBorder(null);
        panelTextFieldNomUtilisateur.setBackground(new Color(29, 88, 129));
        panelUtilisateur.add(panelTextFieldNomUtilisateur);
        
        textFieldUtilisateur = new CustomJTextField();
        textFieldUtilisateur.addActionListener(controleur);
        textFieldUtilisateur.setPlaceholder("login");
        textFieldUtilisateur.setForeground(Color.WHITE);
        textFieldUtilisateur.setColumns(13);
        textFieldUtilisateur.setBorder(null);
        textFieldUtilisateur.setBackground(new Color(29, 88, 129));
        panelTextFieldNomUtilisateur.add(textFieldUtilisateur);
        
        JPanel panelMotDePasse = new JPanel();
        panelMotDePasse.setBackground(new Color(44, 47, 51));
        panelUtilisateurMotDePasse.add(panelMotDePasse);
        
        JLabel labelMotDePasse = new JLabel("Mot de passe :");
        labelMotDePasse.setForeground(new Color(102, 173, 221));
        labelMotDePasse.setPreferredSize(new Dimension(110, 30));
        panelMotDePasse.add(labelMotDePasse);
        
        JPanel panelTextFieldMotDePasse = new JPanel();
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
        
        JButton btnMotDePasseVisibilite = new JButton("");
        btnMotDePasseVisibilite.addActionListener(controleur);
        btnMotDePasseVisibilite.setBackground(new Color(29, 88, 129));
        btnMotDePasseVisibilite.setBorder(null);
        btnMotDePasseVisibilite.setIcon(OEIL_INVISIBLE_ICON);
        panelTextFieldMotDePasse.add(btnMotDePasseVisibilite);
        
        JPanel panelQuitterSeconnecter = new JPanel();
        panelQuitterSeconnecter.setBackground(new Color(44, 47, 51));
        panelQuitterSeconnecter.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.add(panelQuitterSeconnecter, BorderLayout.SOUTH);
        panelQuitterSeconnecter.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JButton btnQuit = new JButton("Quitter");
        btnQuit.setBackground(new Color(231, 76, 60));
        btnQuit.setForeground(new Color(255, 255, 255));
        btnQuit.setBorder(new CustomBorder(5));
        btnQuit.addActionListener(this.controleur);
        panelQuitterSeconnecter.add(btnQuit);
        
        JButton btnLogin = new JButton("Se connecter");
        btnLogin.setForeground(new Color(255, 255, 255));
        btnLogin.setBackground(new Color(46, 204, 113));
        btnLogin.setBorder(new CustomBorder(5));
        btnLogin.addActionListener(controleur);
        panelQuitterSeconnecter.add(btnLogin);
        
        JPanel panelTitre = new JPanel();
        panelTitre.setBackground(new Color(44, 47, 51));
        contentPane.add(panelTitre, BorderLayout.NORTH);
        panelTitre.setLayout(new BorderLayout(0, 0));
        
        JSeparator separatorTitre = new JSeparator();
        separatorTitre.setBackground(new Color(102, 173, 221));
        separatorTitre.setForeground(new Color(102, 173, 221));
        panelTitre.add(separatorTitre, BorderLayout.SOUTH);
        
        JLabel titreFenetre = new JLabel("CONNEXION");
        titreFenetre.setHorizontalAlignment(SwingConstants.CENTER);
        titreFenetre.setForeground(new Color(102, 173, 221));
        titreFenetre.setFont(new Font("Tahoma", Font.BOLD, 25));
        titreFenetre.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelTitre.add(titreFenetre);
    }
}
