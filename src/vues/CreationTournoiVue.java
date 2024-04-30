package vues;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import com.toedter.calendar.JDateChooser;

import DAOs.ArbitreDAO;
import classes.Arbitre;
import classes.Notoriete;
import controleurs.CreationTournoiControleur;
import style.CustomJButton;
import style.CustomJFrame;
import style.CustomJLabel;
import style.CustomJSeparator;
import style.Palette;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Date;
import java.awt.Component;

public class CreationTournoiVue extends CustomJFrame {

    JPanel contentPane;
    public JTextField textFieldEquipesFile;
    public JTextField textFieldNom;
    public JComboBox<Notoriete> comboBoxNotoriete;
    public JDateChooser dateChooserDebut;
    public JDateChooser dateChooserFin;
    public JComboBox<Arbitre> comboBoxArbitre;
    public JButton btnAddArbitre;
    public JButton btnValider;
    public JButton btnImportEquipes;
    public JButton btnSupprimerArbitre;
    public JButton btnViderArbitres;
    public DefaultListModel<Arbitre> modeleList;
    public JList<Arbitre> listArbitres;
    private CreationTournoiControleur controleur;
    public JLabel messageCreation;

    public CreationTournoiVue() {
        super(new Dimension(530, 350), "Création de tournoi");
        setBounds(new Rectangle(Palette.SCREEN_WIDTH / 2 - 700 / 2, Palette.SCREEN_HEIGHT / 2 - 580 / 2, 700, 580));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.controleur = new CreationTournoiControleur(this);
        Color backgroundColor = Palette.PRIMARY_BACKGROUND;
        Color policeColor = new Color(107, 173, 221);

        // création du panel principal
        contentPane = getContentPanel();
        contentPane.setBackground(backgroundColor);
        contentPane.setLayout(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(30, 30, 30, 30));

        // création du panel contenant le titre
        JPanel panelTitre = new JPanel();
        panelTitre.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelTitre.setBackground(backgroundColor);

        contentPane.add(panelTitre, BorderLayout.NORTH);
        panelTitre.setLayout(new BorderLayout(0, 0));
        JSeparator separatorTitre = new CustomJSeparator();
        panelTitre.add(separatorTitre, BorderLayout.SOUTH);

        CustomJLabel lblCreationDeTournoi = new CustomJLabel("CRÉATION DE TOURNOI", 25);
        lblCreationDeTournoi.setFont(Palette.FONT.deriveFont(Font.BOLD, 20));
        lblCreationDeTournoi.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelTitre.add(lblCreationDeTournoi, BorderLayout.CENTER);

        // création du panel de contenu
        JPanel panelContenu = new JPanel();
        panelContenu.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelContenu.setBackground(backgroundColor);
        contentPane.add(panelContenu, BorderLayout.CENTER);
        panelContenu.setLayout(new GridLayout(0, 2, 0, 0));

        JPanel panelInformations = new JPanel();
        panelInformations.setBackground(backgroundColor);
        panelContenu.add(panelInformations);
        panelInformations.setLayout(new GridLayout(2, 0, 0, 0));

        // création du panel contenant la saisie du nom et de la notoriété
        JPanel panelNomNotoriete = new JPanel();
        panelNomNotoriete.setBackground(backgroundColor);
        panelInformations.add(panelNomNotoriete);
        panelNomNotoriete.setLayout(new GridLayout(2, 0, 0, 0));

        // création du champ de saisie de nom
        JPanel panelNom = new JPanel();
        panelNom.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelNom.setBackground(backgroundColor);
        panelNomNotoriete.add(panelNom);
        panelNom.setLayout(null);

        JLabel lbNom = new JLabel("Nom du tournoi :");
        lbNom.setBounds(10, 10, 110, 30);
        lbNom.setFont(Palette.FONT.deriveFont(Font.PLAIN, 11));
        lbNom.setHorizontalAlignment(SwingConstants.RIGHT);
        lbNom.setForeground(policeColor);
        panelNom.add(lbNom);

        this.textFieldNom = new JTextField();
        textFieldNom.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (textFieldNom.getText().length() >= 30) // limit to 3 characters
                    e.consume();
            }
        });

        textFieldNom.setFont(Palette.FONT.deriveFont(Font.PLAIN, 11));
        textFieldNom.setBorder(BorderFactory.createEmptyBorder());
        textFieldNom.setBounds(130, 10, 150, 30);
        textFieldNom.setPreferredSize(new Dimension(110, 30));
        textFieldNom.setHorizontalAlignment(SwingConstants.LEFT);
        textFieldNom.setFont(Palette.FONT);
        panelNom.add(textFieldNom);
        textFieldNom.setColumns(10);

        // création du champ de saisie de notoriété
        JPanel panelNotoriete = new JPanel();
        panelNotoriete.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelNotoriete.setBackground(backgroundColor);
        panelNomNotoriete.add(panelNotoriete);
        panelNotoriete.setLayout(null);

        JLabel lbNotoriete = new JLabel("Notoriété :");
        lbNotoriete.setFont(Palette.FONT.deriveFont(Font.PLAIN, 11));
        lbNotoriete.setBounds(10, 10, 110, 30);
        lbNotoriete.setHorizontalAlignment(SwingConstants.RIGHT);
        lbNotoriete.setForeground(policeColor);
        panelNotoriete.add(lbNotoriete);

        this.comboBoxNotoriete = new JComboBox<Notoriete>();
        comboBoxNotoriete.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Palette.PRIMARY_BACKGROUND));
        comboBoxNotoriete.setFont(Palette.FONT.deriveFont(Font.PLAIN, 11));
        comboBoxNotoriete.setBounds(130, 10, 150, 30);
        this.comboBoxNotoriete.setPreferredSize(new Dimension(100, 30));
        panelNotoriete.add(comboBoxNotoriete);
        for (Notoriete n : Notoriete.values()) {
            this.comboBoxNotoriete.addItem(n);
        }

        // création du panel de dates du tournoi
        JPanel panelDates = new JPanel();
        panelDates.setBackground(backgroundColor);
        panelInformations.add(panelDates);
        panelDates.setLayout(new GridLayout(2, 0, 0, 0));

        // création du champ de saisie de la date de début
        JPanel panelDateDebut = new JPanel();
        panelDateDebut.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelDateDebut.setBackground(backgroundColor);
        panelDates.add(panelDateDebut);
        panelDateDebut.setLayout(null);

        JLabel lblDateDeDbut = new JLabel("Date de début :");
        lblDateDeDbut.setBounds(10, 10, 110, 30);
        lblDateDeDbut.setFont(Palette.FONT.deriveFont(Font.PLAIN, 11));
        lblDateDeDbut.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDateDeDbut.setForeground(policeColor);
        panelDateDebut.add(lblDateDeDbut);

        this.dateChooserDebut = new JDateChooser();

        this.dateChooserDebut.getJCalendar().setMinSelectableDate(new Date());

        Calendar car = Calendar.getInstance();
        car.set(Calendar.MONTH, Calendar.DECEMBER);
        car.set(Calendar.DAY_OF_MONTH, 31);
        Date maxDate = car.getTime();

        this.dateChooserDebut.getJCalendar().setMaxSelectableDate(maxDate);

        dateChooserDebut.setForeground(Palette.SECONDARY_FOREGROUND);
        dateChooserDebut.setFont(Palette.FONT.deriveFont(Font.PLAIN, 11));
        /*for (Component c : dateChooserDebut.getComponents()) {
            ((JComponent) c).setBackground(Palette.SECONDARY_BACKGROUND);
        }*/
        dateChooserDebut.setBounds(130, 10, 150, 30);
        this.dateChooserDebut.setPreferredSize(new Dimension(100, 30));
        panelDateDebut.add(this.dateChooserDebut);

        // création du champ de saisie de la date de fin
        JPanel panelDateFin = new JPanel();
        panelDateFin.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelDateFin.setBackground(backgroundColor);
        panelDates.add(panelDateFin);
        panelDateFin.setLayout(null);

        JLabel lblDateDeFin = new JLabel("Date de fin :");
        lblDateDeFin.setBounds(10, 10, 110, 30);
        lblDateDeFin.setFont(Palette.FONT.deriveFont(Font.PLAIN, 11));
        lblDateDeFin.setPreferredSize(new Dimension(110, 30));
        lblDateDeFin.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDateDeFin.setForeground(policeColor);
        panelDateFin.add(lblDateDeFin);

        this.dateChooserFin = new JDateChooser();

        this.dateChooserFin.getJCalendar().setMinSelectableDate(new Date());

        this.dateChooserFin.getJCalendar().setMaxSelectableDate(maxDate);

        dateChooserFin.setFont(Palette.FONT.deriveFont(Font.PLAIN, 11));
        /*for (Component c : dateChooserFin.getComponents()) {
            ((JComponent) c).setBackground(Palette.SECONDARY_BACKGROUND);
        }*/
        dateChooserFin.setBounds(130, 10, 150, 30);
        this.dateChooserFin.setPreferredSize(new Dimension(100, 30));
        panelDateFin.add(this.dateChooserFin);

        // création du panel contenant le choix des équipes et des arbitres
        JPanel panelEquipesArbitres = new JPanel();
        panelEquipesArbitres.setBackground(backgroundColor);
        panelContenu.add(panelEquipesArbitres);
        panelEquipesArbitres.setLayout(new BorderLayout(0, 0));

        // création du champ de choix des arbitres
        JPanel panelArbitre = new JPanel();
        panelArbitre.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelArbitre.setBackground(backgroundColor);
        panelEquipesArbitres.add(panelArbitre, BorderLayout.CENTER);
        panelArbitre.setLayout(new BorderLayout(0, 0));

        JLabel lblChoixArbitres = new JLabel("Séléction des arbitres");
        lblChoixArbitres.setFont(Palette.FONT.deriveFont(Font.PLAIN, 11));
        lblChoixArbitres.setHorizontalAlignment(SwingConstants.CENTER);
        lblChoixArbitres.setForeground(policeColor);
        panelArbitre.add(lblChoixArbitres, BorderLayout.NORTH);

        JPanel panelChoixArbitre = new JPanel();
        panelChoixArbitre.setBackground(backgroundColor);
        panelChoixArbitre.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelArbitre.add(panelChoixArbitre, BorderLayout.CENTER);

        this.comboBoxArbitre = new JComboBox<Arbitre>();
        comboBoxArbitre.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Palette.PRIMARY_BACKGROUND));
        comboBoxArbitre.setFont(Palette.FONT.deriveFont(Font.BOLD, 11));
        panelChoixArbitre.add(comboBoxArbitre);
        try {
            for (Arbitre a : ArbitreDAO.getInstance().getAll()) {
                this.comboBoxArbitre.addItem(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.btnAddArbitre = new CustomJButton("Ajouter", 5);
        this.btnAddArbitre.setActionCommand("ajouter");
        btnAddArbitre.setFont(Palette.FONT.deriveFont(Font.PLAIN, 11));
        this.btnAddArbitre.addActionListener(controleur);
        panelChoixArbitre.add(this.btnAddArbitre);

        JPanel panelListeArbitres = new JPanel();
        panelListeArbitres.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelListeArbitres.setBackground(backgroundColor);
        panelArbitre.add(panelListeArbitres, BorderLayout.SOUTH);
        this.modeleList = new DefaultListModel<Arbitre>();

        panelListeArbitres.setMinimumSize(new Dimension(50, 50));
        panelListeArbitres.setLayout(new BorderLayout(0, 0));
        panelListeArbitres.setBackground(backgroundColor);

        this.listArbitres = new JList<Arbitre>();
        this.listArbitres.setVisibleRowCount(8);
        listArbitres.setFont(Palette.FONT.deriveFont(Font.BOLD, 11));
        listArbitres.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.listArbitres.setModel(modeleList);
        JScrollPane sp = new JScrollPane(listArbitres);
        sp.setBackground(Palette.PRIMARY_BACKGROUND);
        sp.setPreferredSize(new Dimension(50, 110));
        sp.setBounds(new Rectangle(0, 0, 50, 20));
        sp.setSize(new Dimension(100, 20));
        sp.setMaximumSize(new Dimension(100, 100));
        panelListeArbitres.add(sp);

        JPanel panelBtnsArbitre = new JPanel();
        panelBtnsArbitre.setBackground(backgroundColor);
        panelListeArbitres.add(panelBtnsArbitre, BorderLayout.SOUTH);

        this.btnViderArbitres = new CustomJButton("Vider", 5);
        this.btnViderArbitres.setActionCommand("vider");
        btnViderArbitres.setFont(Palette.FONT.deriveFont(Font.PLAIN, 11));
        this.btnViderArbitres.addActionListener(controleur);
        panelBtnsArbitre.add(btnViderArbitres);

        this.btnSupprimerArbitre = new CustomJButton("Supprimer", 5);
        this.btnSupprimerArbitre.setActionCommand("supprimer");
        btnSupprimerArbitre.setFont(Palette.FONT.deriveFont(Font.PLAIN, 11));
        this.btnSupprimerArbitre.addActionListener(controleur);
        panelBtnsArbitre.add(this.btnSupprimerArbitre);

        // création du champ de saisie des équipes
        JPanel panelEquipes = new JPanel();
        panelEquipes.setBackground(backgroundColor);
        panelEquipesArbitres.add(panelEquipes, BorderLayout.SOUTH);

        JLabel lblEquipesFile = new JLabel("Fichier des équipes :");
        lblEquipesFile.setFont(Palette.FONT.deriveFont(Font.PLAIN, 11));
        lblEquipesFile.setForeground(new Color(107, 173, 221));
        panelEquipes.add(lblEquipesFile);

        this.textFieldEquipesFile = new JTextField();
        this.textFieldEquipesFile.setEnabled(false);
        this.textFieldEquipesFile.setEditable(false);
        panelEquipes.add(this.textFieldEquipesFile);
        this.textFieldEquipesFile.setColumns(10);

        this.btnImportEquipes = new CustomJButton("Importer", 5);
        this.btnImportEquipes.setActionCommand("importer");
        btnImportEquipes.setAlignmentX(Component.RIGHT_ALIGNMENT);
        btnImportEquipes.setFont(Palette.FONT.deriveFont(Font.PLAIN, 11));
        this.btnImportEquipes.addActionListener(controleur);
        panelEquipes.add(this.btnImportEquipes);

        // création du panel de validation
        JPanel panelValidation = new JPanel();
        panelValidation.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelValidation.setBackground(backgroundColor);
        contentPane.add(panelValidation, BorderLayout.SOUTH);
        panelValidation.setLayout(new GridLayout(2, 2, 0, 0));

        JPanel panelQuitter = new JPanel();
        panelQuitter.setBackground(backgroundColor);
        panelValidation.add(panelQuitter);

        JButton btnQuitter = new CustomJButton("Quitter", 5);
        btnQuitter.setActionCommand("quitter");
        btnQuitter.setFont(Palette.FONT.deriveFont(Font.PLAIN, 11));
        btnQuitter.setBackground(Palette.TIERTIARY_BACKGROUND);
        btnQuitter.addActionListener(controleur);
        panelQuitter.add(btnQuitter);

        JPanel panelValider = new JPanel();
        panelValider.setBackground(backgroundColor);
        panelValidation.add(panelValider);

        this.btnValider = new CustomJButton("Valider", 5);
        this.btnValider.setActionCommand("valider");
        btnValider.setFont(Palette.FONT.deriveFont(Font.PLAIN, 11));
        btnValider.addActionListener(controleur);
        panelValider.add(this.btnValider);

        JPanel panelEmpty = new JPanel();
        panelEmpty.setBackground(Palette.PRIMARY_BACKGROUND);
        panelValidation.add(panelEmpty);

        JPanel panelMessage = new JPanel();
        panelMessage.setBackground(Palette.PRIMARY_BACKGROUND);
        messageCreation = new JLabel(" ");
        messageCreation.setVerticalAlignment(SwingConstants.TOP);
        messageCreation.setHorizontalAlignment(SwingConstants.CENTER);
        messageCreation.setPreferredSize(new Dimension(300, 30));
        messageCreation.setMaximumSize(new Dimension(100, 14));
        messageCreation.setMinimumSize(new Dimension(100, 30));
        messageCreation.setBounds(new Rectangle(0, 0, 100, 0));
        messageCreation.setForeground(Palette.ERROR_FOREGROUND);
        messageCreation.setFont(Palette.FONT.deriveFont(11f));
        panelMessage.add(messageCreation);
        panelValidation.add(panelMessage);

    }

    public void closeCurrentWindow() {
        super.closeCurrentWindow();
        AccueilAdministrateurVue frame = new AccueilAdministrateurVue();
        frame.setVisible(true);
    }

}
