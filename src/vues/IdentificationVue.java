package vues;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import DAOs.TournoiDAO;
import controleurs.IdentificationControleur;
import style.CustomJButton;
import style.CustomJFrame;
import style.CustomJLabel;
import style.CustomJPanel;
import style.CustomJPasswordField;
import style.CustomJSeparator;
import style.CustomJTextField;
import style.Palette;

public class IdentificationVue extends CustomJFrame {
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

    public IdentificationVue() {
        super(new Dimension(700, 350), "Identification");
        
        pack();

        // a garder : permet une premiere instance a la base de donnees et eviter de
        // faire l'instance au moment du clique
        // sur "Se connecter" (lag)
        try {
            TournoiDAO.getInstance().getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.controleur = new IdentificationControleur(this);
        
        
        contentPane = super.getContentPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));


        CustomJPanel pageImage = new CustomJPanel();
        CustomJLabel d = new CustomJLabel("", 0);
        d.setIcon(Palette.LOGINIMAGE);
        pageImage.add(d);


        CustomJPanel pageContent = new CustomJPanel();
        pageContent.setLayout(new BorderLayout());
        pageContent.setBorder(new EmptyBorder(10, 20, 0, 20));

        contentPane.add(pageImage, BorderLayout.CENTER);
        contentPane.add(pageContent, BorderLayout.EAST);
        
        // Panel Top : Title
        CustomJPanel panelTop = new CustomJPanel();
        pageContent.add(panelTop, BorderLayout.NORTH);

        CustomJLabel titleTop = new CustomJLabel("Login with your username", 25);
        titleTop.setForeground(Palette.PRIMARY_FOREGROUND);
        titleTop.setFont(Palette.FONT.deriveFont(Font.PLAIN, 16));
        titleTop.setBorder(new EmptyBorder(15, 0, 15, 0));
        panelTop.add(titleTop);

        CustomJSeparator separatorTop = new CustomJSeparator();
        panelTop.add(separatorTop, BorderLayout.SOUTH);

        // Middle Panel : Login
        CustomJPanel panelMiddleLogin = new CustomJPanel(new EmptyBorder(0, 0, 0, 0), new GridLayout(1, 0, 0, 0));
        pageContent.add(panelMiddleLogin, BorderLayout.CENTER);

        // Utilisateur
        CustomJLabel labelUtilisateur = new CustomJLabel("Username", 3);

        labelUtilisateur.setFont(labelUtilisateur.getFont().deriveFont(Font.BOLD, 9f));
        labelUtilisateur.setForeground(Palette.TIERTIARY_FOREGROUND);
        labelUtilisateur.setHorizontalAlignment(SwingConstants.LEFT);
        CustomJPanel panelUtilisateur = new CustomJPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
        panelUtilisateur.setBorder(new EmptyBorder(0, 0, 10, 0));        

        CustomJPanel panelTextFieldUtilisateur = new CustomJPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
        panelTextFieldUtilisateur.setBorderRadius(10);
        panelTextFieldUtilisateur.setBorderColor(Palette.QUATERNARY_FOREGROUND);

        CustomJPanel test = new CustomJPanel();
        panelUtilisateur.add(test);
        test.setLayout(new BorderLayout(3, 3));

        test.add(labelUtilisateur, BorderLayout.NORTH);
        test.add(panelTextFieldUtilisateur);

        textFieldUtilisateur = new CustomJTextField();
        // fix this issue : if we don't set border color, it doesn't appear (will be fixed when refactor Customs)
        textFieldUtilisateur.setBorderColor(Palette.PRIMARY_BACKGROUND);
        textFieldUtilisateur.setBorderRadius(0); // si on laisse par defaut ca se dessine sur le placeholder
        textFieldUtilisateur.addActionListener(controleur);

        textFieldUtilisateur.setPlaceholder("tintin123");
        textFieldUtilisateur.setColumns(10);
        textFieldUtilisateur.setBorder(new EmptyBorder(5, 5, 5, 5));
        textFieldUtilisateur.setFont(Palette.FONT);

        panelTextFieldUtilisateur.add(textFieldUtilisateur);

        CustomJButton btnCompte = new CustomJButton("", 25, (EmptyBorder) null);
        btnCompte.setEnabled(false);
        btnCompte.setBackground(Palette.PRIMARY_BACKGROUND);
        btnCompte.setBorder(null);
        btnCompte.setIcon(Palette.ACCOUNT_LOGO);
        panelTextFieldUtilisateur.add(btnCompte);

        // Mot de passe
        CustomJPanel panelMotDePasse = new CustomJPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
        //
        CustomJPanel panelTextFieldMotDePasse = new CustomJPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
        panelTextFieldMotDePasse.setBorder(new EmptyBorder(10, 10, 10, 10));
        //panelTextFieldMotDePasse.setLayout(new BoxLayout(panelTextFieldMotDePasse, BoxLayout.X_AXIS)); // Utilisation d'un BoxLayout horizontal
        panelTextFieldMotDePasse.setBorder(null);
        panelTextFieldMotDePasse.setBorderRadius(10);
        panelTextFieldMotDePasse.setBorderColor(Palette.QUATERNARY_FOREGROUND);
        //panelMotDePasse.add(panelTextFieldMotDePasse);

        CustomJLabel labelMotDePasse = new CustomJLabel("Password", 3);

        labelMotDePasse.setFont(labelUtilisateur.getFont().deriveFont(Font.BOLD, 9f));
        labelMotDePasse.setForeground(Palette.TIERTIARY_FOREGROUND);
        labelMotDePasse.setHorizontalAlignment(SwingConstants.LEFT);

        
        CustomJPanel test2 = new CustomJPanel();
        panelMotDePasse.add(test2);
        test2.setLayout(new BorderLayout(2, 2));

        test2.add(labelMotDePasse, BorderLayout.NORTH);
        test2.add(panelTextFieldMotDePasse);

        textFieldMotDePasse = new CustomJPasswordField();
        textFieldMotDePasse.setPlaceholder("E1234$xNzaq");
        textFieldMotDePasse.setBorderColor(Palette.PRIMARY_BACKGROUND);
        textFieldMotDePasse.setBorderRadius(0); // si on laisse par defaut ca se dessine sur le placeholder
        textFieldMotDePasse.addActionListener(controleur);
        textFieldMotDePasse.setColumns(10);
        textFieldMotDePasse.setBorder(new EmptyBorder(5, 5, 5, 5));
        textFieldMotDePasse.setFont(Palette.FONT);
        panelTextFieldMotDePasse.add(textFieldMotDePasse);

        CustomJButton btnMotDePasseVisibilite = new CustomJButton("", 25, (EmptyBorder) null);
        btnMotDePasseVisibilite.addActionListener(controleur);
        btnMotDePasseVisibilite.setBackground(Palette.PRIMARY_BACKGROUND);
        btnMotDePasseVisibilite.setBorder(null);
        btnMotDePasseVisibilite.setIcon(Palette.OEIL_INVISIBLE_ICON);
        panelTextFieldMotDePasse.add(btnMotDePasseVisibilite);

        JPanel panelUtilisateurEtMotDePasse = new CustomJPanel();
        panelUtilisateurEtMotDePasse.setLayout(new BoxLayout(panelUtilisateurEtMotDePasse, BoxLayout.Y_AXIS));
        panelUtilisateurEtMotDePasse.setBorder(new EmptyBorder(20, 0, 10, 0));
        panelMiddleLogin.add(panelUtilisateurEtMotDePasse);
        panelUtilisateurEtMotDePasse.add(panelUtilisateur);
        panelUtilisateurEtMotDePasse.add(panelMotDePasse);

        CustomJPanel panelButtonsMessages = new CustomJPanel(new GridLayout(2, 0, 0, 0));

        // Bottom Panel : Quitter + Login
        CustomJPanel panelQuitterSeconnecter = new CustomJPanel(new EmptyBorder(10, 10, 0, 10),
                new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelButtonsMessages.add(panelQuitterSeconnecter, BorderLayout.SOUTH);

        CustomJButton btnQuit = new CustomJButton("Quitter", 35);
        btnQuit.setMargin(new Insets(7, 10, 7, 10));
        btnQuit.setActionCommand("quitter");
        btnQuit.setBackground(Palette.TIERTIARY_BACKGROUND);
        btnQuit.addActionListener(this.controleur);
        panelQuitterSeconnecter.add(btnQuit);

        CustomJButton btnLogin = new CustomJButton("Se connecter", 35);
        btnLogin.setIcon(Palette.CONNECT);
        btnLogin.setMargin(new Insets(7, 10, 7, 10));
        btnLogin.setActionCommand("connecter");
        btnLogin.addActionListener(controleur);
        panelQuitterSeconnecter.add(btnLogin);

        // Définir la taille préférée en utilisant la largeur souhaitée et la hauteur actuelle
        btnQuit.setPreferredSize(new Dimension(btnLogin.getPreferredSize().width, btnQuit.getPreferredSize().height));

        panelErreur = new CustomJPanel(new GridLayout(1,0,0,0));
        erreurOuverture = new CustomJLabel("", 3);
        erreurOuverture.setText(" ");
        erreurOuverture.setForeground(Palette.ERROR_FOREGROUND);
        erreurOuverture.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelErreur.add(erreurOuverture);
        
        panelButtonsMessages.add(panelErreur);

        pageContent.add(panelButtonsMessages, BorderLayout.SOUTH);

    }
}
