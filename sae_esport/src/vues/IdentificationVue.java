package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import style.CustomJPasswordField;
import style.CustomJSeparator;
import style.CustomJTextField;
import java.awt.Rectangle;
import javax.swing.border.LineBorder;

public class IdentificationVue extends CustomJFrame {

	public static final ImageIcon OEIL_INVISIBLE_ICON = new ImageIcon(new ImageIcon(IdentificationVue.class.getClassLoader().getResource
			("oeilMotDePasseInvisible.png")).getImage().getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH));
	
	public static final ImageIcon OEIL_VISIBLE_ICON = new ImageIcon(new ImageIcon(IdentificationVue.class.getClassLoader().getResource
			("oeilMotDePasseVisible.png")).getImage().getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH));
	
	public static final ImageIcon LOGO = new ImageIcon(new ImageIcon(IdentificationVue.class.getClassLoader().getResource
			("logo_app.png")).getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH));
	
	
    private JPanel contentPane;
    private IdentificationControleur controleur;
    
    private CustomJTextField textFieldUtilisateur;
    private CustomJPasswordField textFieldMotDePasse;
    public CustomJLabel erreurOuverture;
    public CustomJPanel panelErreur;
    
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
    	super(new Dimension(300, 450), "Identification");
    	
    	ImageIcon icon = new ImageIcon("src\\logo_app.png");
    	this.setIconImage(icon.getImage());
    	
    	pack();
    	
    	this.controleur = new IdentificationControleur(this);
    	contentPane = super.getContentPanel();
        
        // Panel Top : Title
    	CustomJPanel panelTop = new CustomJPanel();
        contentPane.add(panelTop, BorderLayout.NORTH);
        
        CustomJLabel titleTop = new CustomJLabel("IDENTIFICATION", 25);
        titleTop.setFont(Palette.customFont.deriveFont(Font.BOLD, 20));
        panelTop.add(titleTop);
        
        CustomJSeparator separatorTop = new CustomJSeparator();
        panelTop.add(separatorTop, BorderLayout.SOUTH);
    	
        // Middle Panel : Login
    	CustomJPanel panelMiddleLogin = new CustomJPanel(new EmptyBorder(10, 10, 10, 10), new GridLayout(2, 0, 0, 0));
    	GridLayout gridLayout = (GridLayout) panelMiddleLogin.getLayout();
        contentPane.add(panelMiddleLogin, BorderLayout.CENTER);
        
        JPanel panelImageApp = new CustomJPanel();
        
        panelMiddleLogin.add(panelImageApp);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBorder(new EmptyBorder(10, 0, 0, 0));
        
        lblNewLabel.setPreferredSize(new Dimension(120, 120));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setIcon(LOGO);
        panelImageApp.add(lblNewLabel, BorderLayout.NORTH);
        
        panelErreur = new CustomJPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        erreurOuverture = new CustomJLabel("",10);
		erreurOuverture.setText(" ");
		erreurOuverture.setForeground(Palette.REDERRORFOREGROUND);
		erreurOuverture.setBorder(new EmptyBorder(3, 3, 3, 3));
		erreurOuverture.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelErreur.add(erreurOuverture);
		
        
        // Utilisateur
        CustomJPanel panelUtilisateur = new CustomJPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        
        JPanel panelTextFieldUtilisateur = new CustomJPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelTextFieldUtilisateur.setBackground(Palette.BLUE);
        panelTextFieldUtilisateur.setBorder(null);
        panelUtilisateur.add(panelTextFieldUtilisateur);
        
        // a fix
        //textFieldUtilisateur = new CustomJTextField(10, (EmptyBorder) null, "Login");
        //panelTextFieldUtilisateur.add(textFieldUtilisateur);
        
        
        
        textFieldUtilisateur = new CustomJTextField();
        textFieldUtilisateur.setBorderColor(Palette.BLUE);
        textFieldUtilisateur.setBorderRadius(0); // si on laisse par defaut ca se dessine sur le placeholder
        textFieldUtilisateur.setPlaceholder("Login");
        textFieldUtilisateur.addActionListener(controleur);
        textFieldUtilisateur.setForeground(Color.WHITE);
        textFieldUtilisateur.setColumns(13);
        textFieldUtilisateur.setBorder(null);
        textFieldUtilisateur.setBackground(Palette.BLUE);
        textFieldUtilisateur.setFont(Palette.customTextFont);
        panelTextFieldUtilisateur.add(textFieldUtilisateur);
        
        // Mot de passe
        CustomJPanel panelMotDePasse = new CustomJPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        // 
        JPanel panelTextFieldMotDePasse = new CustomJPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelTextFieldMotDePasse.setBorder(null);
        panelTextFieldMotDePasse.setBackground(Palette.BLUE);
        panelMotDePasse.add(panelTextFieldMotDePasse);

        textFieldMotDePasse = new CustomJPasswordField();
        textFieldMotDePasse.setPlaceholder("Password");
        textFieldMotDePasse.setBorderColor(Palette.BLUE);
        textFieldMotDePasse.setBorderRadius(0); // si on laisse par defaut ca se dessine sur le placeholder
        textFieldMotDePasse.addActionListener(controleur);
        textFieldMotDePasse.setForeground(Color.WHITE);
        textFieldMotDePasse.setColumns(10);
        textFieldMotDePasse.setBorder(new EmptyBorder(5, 5, 5, 5));
        textFieldMotDePasse.setBackground(Palette.BLUE);
        textFieldMotDePasse.setFont(Palette.customTextFont);
        panelTextFieldMotDePasse.add(textFieldMotDePasse);
        
        
        JButton btnMotDePasseVisibilite = new CustomJButton("", 25, (EmptyBorder) null);
        btnMotDePasseVisibilite.addActionListener(controleur);
        btnMotDePasseVisibilite.setBackground(Palette.BLUE);
        btnMotDePasseVisibilite.setBorder(null);
        btnMotDePasseVisibilite.setIcon(OEIL_INVISIBLE_ICON);
        panelTextFieldMotDePasse.add(btnMotDePasseVisibilite);
        
        JPanel panelUtilisateurEtMotDePasse = new CustomJPanel();
        panelUtilisateurEtMotDePasse.setBorder(new EmptyBorder(20, 0, 10, 0));
        panelMiddleLogin.add(panelUtilisateurEtMotDePasse);
        panelUtilisateurEtMotDePasse.add(panelErreur);
        panelUtilisateurEtMotDePasse.add(panelUtilisateur);
        panelUtilisateurEtMotDePasse.add(panelMotDePasse);
        panelUtilisateurEtMotDePasse.setLayout(new GridLayout(3, 0, 0, 0));
        
        
        
        // Bottom Panel : Quitter + Login
        CustomJPanel panelQuitterSeconnecter = new CustomJPanel(new EmptyBorder(10, 10, 10, 10), new FlowLayout(FlowLayout.CENTER, 5, 5));
        contentPane.add(panelQuitterSeconnecter, BorderLayout.SOUTH);
        
        CustomJButton btnQuit = new CustomJButton("Quitter", 5);
        btnQuit.setBackground(Palette.REDQUIT);
        btnQuit.addActionListener(this.controleur);
        panelQuitterSeconnecter.add(btnQuit);
        
        CustomJButton btnLogin = new CustomJButton("Se connecter", 5);
        btnLogin.setBackground(Palette.BLUE);
        btnLogin.addActionListener(controleur);
        panelQuitterSeconnecter.add(btnLogin);
    }
}
