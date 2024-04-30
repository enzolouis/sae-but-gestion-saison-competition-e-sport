package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import style.*;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import classes.Equipe;
import classes.Joueur;
import controleurs.ConsultationSaisonControleur;
import modeles.ConsultationSaisonModele;
import modeles.TournoiModele;

import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class ConsultationSaisonVue extends CustomJFrame {

    public ConsultationSaisonModele modele;
    public ConsultationSaisonControleur controleur;

    public JTable table;
    public DefaultTableModel tablemodel;

    public JList<TournoiModele> listetournois;
    public DefaultListModel<TournoiModele> listetournoismodele;

    public JList<Joueur> listejoueurs;
    public DefaultListModel<Joueur> listejoueursmodele;

    public ConsultationSaisonVue() {

        // paramétrage de la page
        super(new Dimension(600, 600), "Statistiques de la saison");

        // instanciation du controleur et du modèle
        controleur = new ConsultationSaisonControleur(this);
        modele = new ConsultationSaisonModele();

        // création du panel principal
        CustomJPanel panel = new CustomJPanel();
        getContentPanel().add(panel);

        // ajout du titre
        CustomJPanel panelTitre = new CustomJPanel();
        panelTitre.setLayout(new BorderLayout(0, 0));
        CustomJLabel labelTitre = new CustomJLabel("CLASSEMENT DE SAISON", 25);
        labelTitre.setFont(Palette.FONT.deriveFont(Font.BOLD, 20));
        panelTitre.add(labelTitre, BorderLayout.CENTER);
        CustomJSeparator separator = new CustomJSeparator();
        panelTitre.add(separator, BorderLayout.SOUTH);
        panel.add(panelTitre, BorderLayout.NORTH);

        // ajout de l'affichage des équipes
        CustomJPanel panelEquipes = new CustomJPanel();
        panelEquipes.setBorder(new EmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane();
        panelEquipes.add(scrollPane, BorderLayout.CENTER);
        panel.add(panelEquipes, BorderLayout.CENTER);

        // instanciation de la table des équipes

        table = new JTable();
        new JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };

        table.setBorder(null);
        table.setOpaque(false);
        table.setFont(Palette.FONT.deriveFont(Font.PLAIN, 12));
        table.setBackground(Palette.QUATERNARY_BACKGROUND);
        table.setForeground(Palette.SECONDARY_FOREGROUND);
        table.setSelectionBackground(Palette.QUATERNARY_BACKGROUND);
        table.setGridColor(Palette.QUATERNARY_BACKGROUND);
        table.setRowHeight(34);
        table.getTableHeader().setBackground(Palette.SECONDARY_BACKGROUND);
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(Palette.FONT.deriveFont(Font.PLAIN, 11));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(controleur);
        scrollPane.setViewportView(table);

        // instanciation du modele de la table des équipes

        tablemodel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // all cells false
                return false;
            }
        };

        tablemodel.addColumn("Classement");
        tablemodel.addColumn("Id");
        tablemodel.addColumn("Nom d'équipe");
        tablemodel.addColumn("Nationalité");
        tablemodel.addColumn("Points");
        tablemodel.addColumn("Class. précédent");
        table.setModel(tablemodel);

        table.getColumnModel().getColumn(0).setPreferredWidth(35);

        // remplissage de la table avec le classement des équipes
        try {
            for (Equipe e : modele.classementRanked()) {
                tablemodel.addRow(new Object[] {
                        modele.classementWithRank().get(e),
                        e.getIdEquipe(),
                        e.getNom(),
                        e.getNationalite(),
                        e.getPointsSaison(),
                        e.getRangSaisonPrecedante() });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // masquage de la colonne de l'identifiant de l'équipe
        table.getColumnModel().removeColumn(table.getColumnModel().getColumn(1));

        // instanciation du panel des informations sur l'équipe
        CustomJPanel panelInfos = new CustomJPanel();
        panel.add(panelInfos, BorderLayout.SOUTH);
        panelInfos.setLayout(new GridLayout(0, 2, 0, 0));

        // instanciation du panel des tournois disputés par l'équipe
        CustomJPanel panelTournois = new CustomJPanel();
        panelTournois.setBorder(new EmptyBorder(0, 0, 0, 5));
        JScrollPane scrollTournois = new JScrollPane();
        scrollTournois.setPreferredSize(new Dimension(2, 100));
        listetournois = new JList<TournoiModele>();
        listetournoismodele = new DefaultListModel<TournoiModele>();
        listetournois.setModel(listetournoismodele);
        listetournois.setBackground(Palette.QUATERNARY_BACKGROUND);
        listetournois.setForeground(Palette.SECONDARY_FOREGROUND);
        listetournois.setFont(Palette.FONT);
        listetournois.setBorder(new EmptyBorder(3, 3, 3, 3));
        scrollTournois.setViewportView(listetournois);
        panelTournois.add(scrollTournois);
        CustomJLabel labelTournois = new CustomJLabel("Tournois disputés", 20);
        panelTournois.add(labelTournois, BorderLayout.NORTH);
        panelInfos.add(panelTournois);

        // instanciation du panel des joueurs de l'équipe
        CustomJPanel panelJoueurs = new CustomJPanel();
        panelJoueurs.setBorder(new EmptyBorder(0, 5, 0, 0));
        JScrollPane scrollJoueurs = new JScrollPane();
        scrollJoueurs.setPreferredSize(new Dimension(2, 100));
        listejoueurs = new JList<Joueur>();
        listejoueursmodele = new DefaultListModel<Joueur>();
        listejoueurs.setModel(listejoueursmodele);
        listejoueurs.setBackground(Palette.QUATERNARY_BACKGROUND);
        listejoueurs.setForeground(Palette.SECONDARY_FOREGROUND);
        listejoueurs.setFont(Palette.FONT);
        listejoueurs.setBorder(new EmptyBorder(3, 3, 3, 3));
        scrollJoueurs.setViewportView(listejoueurs);
        panelJoueurs.add(scrollJoueurs);
        CustomJLabel labelJoueurs = new CustomJLabel("Joueurs de l'équipe", 20);
        panelJoueurs.add(labelJoueurs, BorderLayout.NORTH);
        panelInfos.add(panelJoueurs);

        // ajout du bouton quitter la page
        CustomJPanel panelQuitter = new CustomJPanel();
        panelQuitter.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        CustomJButton btnQuitter = new CustomJButton("Quitter", 15);
        btnQuitter.setActionCommand("quitter");
        btnQuitter.setBackground(Palette.TIERTIARY_BACKGROUND);
        btnQuitter.addActionListener(controleur);
        panelQuitter.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelQuitter.add(btnQuitter);
        getContentPanel().add(panelQuitter, BorderLayout.SOUTH);

    }

    public void closeCurrentWindow() {
        super.closeCurrentWindow();
        AccueilAdministrateurVue frame = new AccueilAdministrateurVue();
        frame.setVisible(true);
    }

}
