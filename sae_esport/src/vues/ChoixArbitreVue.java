package vues;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAOs.ArbitreDAO;
import classes.Arbitre;
import classes.Nationalite;
import controleurs.ChoixArbitreControleur;
import modeles.TournoiModele;
import style.CustomJButton;
import style.CustomJFrame;
import style.CustomJLabel;
import style.CustomJPanel;
import style.CustomJScrollPane;
import style.CustomJSeparator;
import style.Palette;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JList;

public class ChoixArbitreVue extends CustomJFrame {

	public CustomJPanel contentPanel;
	public ChoixArbitreControleur controleur;
	public ArrayList<JButton> boutonsArbitres = new ArrayList<>();
	public JTextField textFieldNom;
	public JTextField textFieldPrenom;
	public JComboBox<Nationalite> comboBoxNationalite;
	public JList<TournoiModele> listTournois;
	public DefaultListModel<TournoiModele> listTournoisModele;
	public JPanel panelErreur;
	public JLabel erreur;
	public JButton btnSupprimer;
	public CustomJPanel panelArbitreList;
	public JLabel lbNomArbitre;
	public CustomJScrollPane scrollpanelArbitreList;
    
    public ChoixArbitreVue() {
   
    	
    	super(new Dimension(600, 630), "Gestion des arbitre");
    	
    	this.controleur = new ChoixArbitreControleur(this);
    	contentPanel = this.getContentPanel();
        
        // Panel Top : Title
    	CustomJPanel panelTop = new CustomJPanel();
        contentPanel.add(panelTop, BorderLayout.NORTH);
        
        CustomJLabel titleTop = new CustomJLabel("GESTION DES ARBITRES", 25);
        titleTop.setFont(Palette.customFont.deriveFont(Font.BOLD, 20));
        panelTop.add(titleTop);
        
        CustomJSeparator separatorTop = new CustomJSeparator();
        panelTop.add(separatorTop, BorderLayout.SOUTH);
        
        // Panel middle : Action et Listes
        JPanel panelMiddle = new CustomJPanel(new EmptyBorder(10, 0, 0, 0));
        contentPanel.add(panelMiddle);
        
        GridBagLayout gbl_panelMiddle = new GridBagLayout();
        gbl_panelMiddle.rowHeights = new int[] {266, 133};
        gbl_panelMiddle.columnWidths = new int[] {399};
        gbl_panelMiddle.columnWeights = new double[]{1.0};
        gbl_panelMiddle.rowWeights = new double[]{0.0, 0.0};
        panelMiddle.setLayout(gbl_panelMiddle);
        
        // Panel middle --> Panel Liste : Ligne 1 et 2
        panelArbitreList = new CustomJPanel(new EmptyBorder(10, 10, 10, 10), new GridLayout(0, 2, 10, 10)); 
        scrollpanelArbitreList = new CustomJScrollPane(panelArbitreList);
        
        GridBagConstraints gbc_scrollpanelArbitreList = new GridBagConstraints();
        gbc_scrollpanelArbitreList.insets = new Insets(0, 0, 0, 0);
        gbc_scrollpanelArbitreList.fill = GridBagConstraints.BOTH;
        gbc_scrollpanelArbitreList.gridx = 0;
        gbc_scrollpanelArbitreList.gridy = 0;
        panelMiddle.add(scrollpanelArbitreList, gbc_scrollpanelArbitreList);
        
        try {
			for (Arbitre a : ArbitreDAO.getInstance().getAll()) {
				CustomJButton button = new CustomJButton(a.toString(), 15);
				boutonsArbitres.add(button);
				button.setActionCommand(""+a.getIdArbitre());
				button.addActionListener(this.controleur);
				panelArbitreList.add(button);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // Panel d'actions par rapport aux arbitres
        CustomJPanel panelArbitreActions = new CustomJPanel(new EmptyBorder(0, 0, 0, 0), new GridLayout(2, 3, 10, 10));
        GridLayout gridLayout = (GridLayout) panelArbitreActions.getLayout();
        gridLayout.setColumns(2);
        gridLayout.setRows(0);
        GridBagConstraints gbc_panelArbitreActions = new GridBagConstraints();
        gbc_panelArbitreActions.fill = GridBagConstraints.BOTH;
        gbc_panelArbitreActions.gridx = 0;
        gbc_panelArbitreActions.gridy = 1;
        panelMiddle.add(panelArbitreActions, gbc_panelArbitreActions);
        
        // Panel de consultation d'un arbitre
        JPanel panelTournois = new JPanel();
        panelTournois.setBackground(Palette.BLACK);
        panelTournois.setLayout(new BorderLayout(0, 0));
        panelArbitreActions.add(panelTournois);
        
        JScrollPane scrollPaneTournois = new JScrollPane();
        scrollPaneTournois.setBackground(Palette.BLACK);
        panelTournois.add(scrollPaneTournois, BorderLayout.CENTER);
        
        listTournois = new JList<>();
        listTournoisModele = new DefaultListModel<>();
        listTournois.setModel(listTournoisModele);
        scrollPaneTournois.setViewportView(listTournois);
        
        lbNomArbitre = new CustomJLabel("Veuillez sélectionner un arbitre",15);
        lbNomArbitre.setBorder(new EmptyBorder(5, 5, 5, 5));
        lbNomArbitre.setHorizontalAlignment(SwingConstants.CENTER);
        panelTournois.add(lbNomArbitre, BorderLayout.NORTH);
        
        JPanel panelBoutonSupprimer = new JPanel();
        panelBoutonSupprimer.setBackground(Palette.BLACK);
        panelTournois.add(panelBoutonSupprimer, BorderLayout.SOUTH);
        
        btnSupprimer = new CustomJButton("Supprimer cet arbitre",15);
        btnSupprimer.setActionCommand("suppArbitre");
        btnSupprimer.setBackground(Palette.REDQUIT);
        btnSupprimer.addActionListener(controleur);
        btnSupprimer.setEnabled(false);
        panelBoutonSupprimer.add(btnSupprimer);
        
        JPanel panelAjout = new JPanel();
        panelAjout.setBackground(Palette.BLACK);
        panelArbitreActions.add(panelAjout);
        panelAjout.setLayout(new BorderLayout(0, 0));
        
        JLabel lbNouvelArbitre = new CustomJLabel("Création d'un nouvel arbitre",15);
        lbNouvelArbitre.setHorizontalAlignment(SwingConstants.CENTER);
        lbNouvelArbitre.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelAjout.add(lbNouvelArbitre, BorderLayout.NORTH);
        
        JPanel panelInformations = new JPanel();
        panelInformations.setBackground(Palette.BLACK);
        panelAjout.add(panelInformations, BorderLayout.CENTER);
        panelInformations.setLayout(new GridLayout(3, 0, 0, 0));
        
        JPanel panelNom = new JPanel();
        panelNom.setBackground(Palette.BLACK);
        panelInformations.add(panelNom);
        
        JLabel lblNouveauNom = new CustomJLabel("Nom",12);
        panelNom.add(lblNouveauNom);
        
        textFieldNom = new JTextField();
        panelNom.add(textFieldNom);
        textFieldNom.setColumns(10);
        
        JPanel panelPrenom = new JPanel();
        panelPrenom.setBackground(Palette.BLACK);
        panelInformations.add(panelPrenom);
        
        JLabel lblNouveauPrenom = new CustomJLabel("Prénom",12);
        panelPrenom.add(lblNouveauPrenom);
        
        textFieldPrenom = new JTextField();
        panelPrenom.add(textFieldPrenom);
        textFieldPrenom.setColumns(10);
        
        JPanel panelNationalite = new JPanel();
        panelNationalite.setBackground(Palette.BLACK);
        panelInformations.add(panelNationalite);
        
        JLabel lblNationalite = new CustomJLabel("Nationalité",12);
        panelNationalite.add(lblNationalite);
        
        comboBoxNationalite = new JComboBox<Nationalite>();
        comboBoxNationalite.setPreferredSize(new Dimension(120, 22));
        comboBoxNationalite.setMinimumSize(new Dimension(100, 34));
        comboBoxNationalite.setMaximumSize(new Dimension(100, 34));
        
        for (Nationalite n : Nationalite.values()) comboBoxNationalite.addItem(n);
        
        panelNationalite.add(comboBoxNationalite);
        
        JPanel panelBoutonAjouter = new JPanel();
        panelBoutonAjouter.setBackground(Palette.BLACK);
        panelAjout.add(panelBoutonAjouter, BorderLayout.SOUTH);
        
        JButton btnAjouterArbitre = new CustomJButton("Ajouter cet arbitre",15);
        btnAjouterArbitre.setActionCommand("ajouterArbitre");
        btnAjouterArbitre.addActionListener(controleur);
        panelBoutonAjouter.add(btnAjouterArbitre);
        
        // Panel bottom
        CustomJPanel panelBottom = new CustomJPanel(new EmptyBorder(10, 10, 0, 10), new FlowLayout(FlowLayout.CENTER, 5, 5));
        contentPanel.add(panelBottom, BorderLayout.SOUTH);
        
        JPanel panelBtnQuitter = new JPanel();
        panelBtnQuitter.setBackground(Palette.BLACK);
        
        //Bouton quitter
        CustomJButton btnQuit = new CustomJButton("Quitter", 10);
        btnQuit.setActionCommand("quitter");
        btnQuit.setBackground(Palette.REDQUIT);
        btnQuit.addActionListener(this.controleur);
        panelBottom.setLayout(new GridLayout(1, 2, 0, 0));
        panelBtnQuitter.add(btnQuit);
        
        panelBottom.add(panelBtnQuitter);
        
        panelErreur = new CustomJPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        erreur = new CustomJLabel("",10);
        erreur.setText(" ");
        erreur.setForeground(Palette.REDERRORFOREGROUND);
        erreur.setBorder(new EmptyBorder(3, 3, 3, 3));
        erreur.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelErreur.add(erreur);
        
        panelBottom.add(panelErreur);
        
    }
    
    public void closeCurrentWindow() {
		super.closeCurrentWindow();
		AccueilAdministrateurVue frame = new AccueilAdministrateurVue();
		frame.setVisible(true);
	}
    
}
