package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controleurs.AccueilAdministrateurControleur;
import style.CustomJButton;
import style.CustomJFrame;
import style.CustomJLabel;
import style.CustomJPanel;
import style.CustomJSeparator;
import style.Palette;

public class AccueilAdministrateurVue extends CustomJFrame {

    private CustomJPanel contentPane;
    private AccueilAdministrateurControleur controleur;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AccueilAdministrateurVue frame = new AccueilAdministrateurVue();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AccueilAdministrateurVue() {
        super(new Dimension(400, 450), "Accueil Administrateur");

        pack();

        this.controleur = new AccueilAdministrateurControleur(this);

        contentPane = super.getContentPanel();

        // Top Panel : Titre
        CustomJPanel panelTitre = new CustomJPanel();
        contentPane.add(panelTitre, BorderLayout.NORTH);

        CustomJLabel titreFenetre = new CustomJLabel("Administration", 25);
        titreFenetre.setBorder(new EmptyBorder(15, 0, 15, 0));
        titreFenetre.setFont(Palette.FONT.deriveFont(Font.PLAIN, 16));
        titreFenetre.setForeground(Palette.PRIMARY_FOREGROUND);
        panelTitre.add(titreFenetre, BorderLayout.CENTER);

        CustomJSeparator separatorTitre = new CustomJSeparator();
        panelTitre.add(separatorTitre, BorderLayout.SOUTH);

        // Middle Panel : Actions / Sujet
        CustomJPanel panelMiddle = new CustomJPanel(new EmptyBorder(5, 5, 5, 5));

        panelMiddle.setLayout(new GridLayout(2, 2, 10, 10));
        contentPane.add(panelMiddle, BorderLayout.CENTER);

        CustomJButton btnCreerUnTournoi = new CustomJButton("", 35);
        btnCreerUnTournoi.setIcon(Palette.STATS);
        btnCreerUnTournoi.setBackground(new Color(240, 240, 240));
        btnCreerUnTournoi.setBorderColor(Palette.QUATERNARY_FOREGROUND);
        btnCreerUnTournoi.setActionCommand("creerTournoi");
        btnCreerUnTournoi.addActionListener(controleur);
        panelMiddle.add(btnCreerUnTournoi);

        CustomJButton btnStatistiquesDeLa = new CustomJButton("", 35);
        btnStatistiquesDeLa.setIcon(Palette.STATS);
        btnStatistiquesDeLa.setBackground(new Color(240, 240, 240));
        btnStatistiquesDeLa.setBorderColor(Palette.QUATERNARY_FOREGROUND);
        btnStatistiquesDeLa.setActionCommand("statsSaison");
        btnStatistiquesDeLa.addActionListener(controleur);
        panelMiddle.add(btnStatistiquesDeLa);

        CustomJButton btnListeTournois = new CustomJButton("", 35);
        btnListeTournois.setIcon(Palette.STATS);
        btnListeTournois.setBackground(new Color(240, 240, 240));
        btnListeTournois.setBorderColor(Palette.QUATERNARY_FOREGROUND);
        btnListeTournois.setActionCommand("listeTournois");
        btnListeTournois.addActionListener(controleur);
        panelMiddle.add(btnListeTournois);

        CustomJButton btnArbitres = new CustomJButton("", 35);
        btnArbitres.setIcon(Palette.STATS);
        btnArbitres.setBackground(new Color(240, 240, 240));
        btnArbitres.setBorderColor(Palette.QUATERNARY_FOREGROUND);
        btnArbitres.setActionCommand("gererArbitres");
        btnArbitres.addActionListener(controleur);
        panelMiddle.add(btnArbitres);
        // Bottom Panel : Quitter & Divers
        CustomJPanel panelQuitterSeconnecter = new CustomJPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        contentPane.add(panelQuitterSeconnecter, BorderLayout.SOUTH);

        CustomJButton btnDeconnexion = new CustomJButton("Se déconnecter", 35);
        btnDeconnexion.setMargin(new Insets(7, 10, 7, 10));
        btnDeconnexion.setActionCommand("deconnecter");
        btnDeconnexion.setBackground(Palette.TIERTIARY_BACKGROUND);
        btnDeconnexion.addActionListener(this.controleur);
        panelQuitterSeconnecter.add(btnDeconnexion, BorderLayout.CENTER);

        CustomJButton btnQuit = new CustomJButton("Quitter", 35);
        btnQuit.setMargin(new Insets(7, 10, 7, 10));
        btnQuit.setActionCommand("quitter");
        // Définir la taille préférée en utilisant la largeur souhaitée et la hauteur
        // actuelle
        btnQuit.setPreferredSize(
                new Dimension(btnDeconnexion.getPreferredSize().width, btnQuit.getPreferredSize().height));

        btnQuit.setBackground(Palette.TIERTIARY_BACKGROUND);
        btnQuit.addActionListener(this.controleur);
        panelQuitterSeconnecter.add(btnQuit, BorderLayout.CENTER);
    }
}
