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

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import java.awt.Rectangle;
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
	
	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                CreationTournoiVue frame = new CreationTournoiVue();
                frame.setVisible(true);
            }
        });
    }
	
	public CreationTournoiVue() {
		
		super(new Dimension(530,300));
		setBounds(new Rectangle(0, 0, 700, 550));
		setTitle("Création de tournoi");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		ImageIcon icon = new ImageIcon("src\\logo_app.png");
    	this.setIconImage(icon.getImage());
		
		this.controleur = new CreationTournoiControleur(this);
		Color backgroundColor = new Color(44, 47, 51);
		Color policeColor = new Color(107,173,221);
		
		//création du panel principal
		contentPane = getContentPanel();
		contentPane.setBackground(backgroundColor);
		contentPane.setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(30,30,30,30));
		
		//création du panel contenant le titre
		JPanel panelTitre = new JPanel();
		panelTitre.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelTitre.setBackground(backgroundColor);
		
		contentPane.add(panelTitre, BorderLayout.NORTH);
		panelTitre.setLayout(new BorderLayout(0, 0));
		JSeparator separatorTitre = new JSeparator();
		separatorTitre.setForeground(policeColor);
		separatorTitre.setBackground(policeColor);
		panelTitre.add(separatorTitre, BorderLayout.SOUTH);
		
		JLabel lblCreationDeTournoi = new JLabel("CRÉATION DE TOURNOI");
		lblCreationDeTournoi.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreationDeTournoi.setForeground(policeColor);
		lblCreationDeTournoi.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 25));
		lblCreationDeTournoi.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelTitre.add(lblCreationDeTournoi, BorderLayout.CENTER);
		
		//création du panel de contenu
		JPanel panelContenu = new JPanel();
		panelContenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelContenu.setBackground(backgroundColor);
		contentPane.add(panelContenu, BorderLayout.CENTER);
		panelContenu.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelInformations = new JPanel();
		panelInformations.setBackground(backgroundColor);
		panelContenu.add(panelInformations);
		panelInformations.setLayout(new GridLayout(2, 0, 0, 0));
		
		//création du panel contenant la saisie du nom et de la notoriété
		JPanel panelNomNotoriete = new JPanel();
		panelNomNotoriete.setBackground(backgroundColor);
		panelInformations.add(panelNomNotoriete);
		panelNomNotoriete.setLayout(new GridLayout(2, 0, 0, 0));
		
		//création du champ de saisie de nom
		JPanel panelNom = new JPanel();
		panelNom.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelNom.setBackground(backgroundColor);
		panelNomNotoriete.add(panelNom);
		panelNom.setLayout(null);
		
		JLabel lbNom = new JLabel("Nom du tournoi :");
		lbNom.setBounds(10, 10, 110, 30);
		lbNom.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		lbNom.setHorizontalAlignment(SwingConstants.RIGHT);
		lbNom.setForeground(policeColor);
		panelNom.add(lbNom);
		
		this.textFieldNom = new JTextField();
		textFieldNom.setBounds(130, 10, 150, 30);
		textFieldNom.setPreferredSize(new Dimension(110, 30));
		textFieldNom.setHorizontalAlignment(SwingConstants.LEFT);
		panelNom.add(textFieldNom);
		textFieldNom.setColumns(10);
		
		//création du champ de saisie de notoriété
		JPanel panelNotoriete = new JPanel();
		panelNotoriete.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelNotoriete.setBackground(backgroundColor);
		panelNomNotoriete.add(panelNotoriete);
		panelNotoriete.setLayout(null);
		
		JLabel lbNotoriete = new JLabel("Notoriété :");
		lbNotoriete.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		lbNotoriete.setBounds(10, 10, 110, 30);
		lbNotoriete.setHorizontalAlignment(SwingConstants.RIGHT);
		lbNotoriete.setForeground(policeColor);
		panelNotoriete.add(lbNotoriete);
		
		this.comboBoxNotoriete = new JComboBox<Notoriete>();
		comboBoxNotoriete.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		comboBoxNotoriete.setBounds(130, 10, 150, 30);
		this.comboBoxNotoriete.setPreferredSize(new Dimension(100, 30));
		panelNotoriete.add(comboBoxNotoriete);
		for (Notoriete n : Notoriete.values()) {
			this.comboBoxNotoriete.addItem(n);
		}
		
		//création du panel de dates du tournoi
		JPanel panelDates = new JPanel();
		panelDates.setBackground(backgroundColor);
		panelInformations.add(panelDates);
		panelDates.setLayout(new GridLayout(2, 0, 0, 0));
		
		//création du champ de saisie de la date de début
		JPanel panelDateDebut = new JPanel();
		panelDateDebut.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelDateDebut.setBackground(backgroundColor);
		panelDates.add(panelDateDebut);
		panelDateDebut.setLayout(null);
		
		JLabel lblDateDeDbut = new JLabel("Date de début :");
		lblDateDeDbut.setBounds(10, 10, 110, 30);
		lblDateDeDbut.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		lblDateDeDbut.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateDeDbut.setForeground(policeColor);
		panelDateDebut.add(lblDateDeDbut);
		
		this.dateChooserDebut = new JDateChooser();
		dateChooserDebut.setBounds(130, 10, 150, 30);
		this.dateChooserDebut.setPreferredSize(new Dimension(100, 30));
		panelDateDebut.add(this.dateChooserDebut);
		
		//création du champ de saisie de la date de fin
		JPanel panelDateFin = new JPanel();
		panelDateFin.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelDateFin.setBackground(backgroundColor);
		panelDates.add(panelDateFin);
		panelDateFin.setLayout(null);
		
		JLabel lblDateDeFin = new JLabel("Date de fin :");
		lblDateDeFin.setBounds(10, 10, 110, 30);
		lblDateDeFin.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		lblDateDeFin.setPreferredSize(new Dimension(110, 30));
		lblDateDeFin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateDeFin.setForeground(policeColor);
		panelDateFin.add(lblDateDeFin);
		
		this.dateChooserFin = new JDateChooser();
		dateChooserFin.setBounds(130, 10, 150, 30);
		this.dateChooserFin.setPreferredSize(new Dimension(100, 30));
		panelDateFin.add(this.dateChooserFin);
		
		//création du panel contenant le choix des équipes et des arbitres
		JPanel panelEquipesArbitres = new JPanel();
		panelEquipesArbitres.setBackground(backgroundColor);
		panelContenu.add(panelEquipesArbitres);
		panelEquipesArbitres.setLayout(new BorderLayout(0, 0));
		
		//création du champ de choix des arbitres
		JPanel panelArbitre = new JPanel();
		panelArbitre.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelArbitre.setBackground(backgroundColor);
		panelEquipesArbitres.add(panelArbitre, BorderLayout.CENTER);
		panelArbitre.setLayout(new BorderLayout(0, 0));
		
		JLabel lblChoixArbitres = new JLabel("Séléction des arbitres");
		lblChoixArbitres.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		lblChoixArbitres.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoixArbitres.setForeground(policeColor);
		panelArbitre.add(lblChoixArbitres, BorderLayout.NORTH);
		
		JPanel panelChoixArbitre = new JPanel();
		panelChoixArbitre.setBackground(backgroundColor);
		panelChoixArbitre.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelArbitre.add(panelChoixArbitre, BorderLayout.CENTER);
		
		this.comboBoxArbitre = new JComboBox<Arbitre>();
		comboBoxArbitre.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		panelChoixArbitre.add(comboBoxArbitre);
		try {
			for (Arbitre a : ArbitreDAO.getInstance().getAll()) {
				this.comboBoxArbitre.addItem(a);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.btnAddArbitre = new CustomJButton("Ajouter", 5);
		btnAddArbitre.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		this.btnAddArbitre.addActionListener(controleur);
		panelChoixArbitre.add(this.btnAddArbitre);
		
		JPanel panelListeArbitres = new JPanel();
		panelListeArbitres.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelListeArbitres.setBackground(backgroundColor);
		panelArbitre.add(panelListeArbitres, BorderLayout.SOUTH);
		this.modeleList = new DefaultListModel<Arbitre>();
		
		panelListeArbitres.setMinimumSize(new Dimension(50, 50));
		panelListeArbitres.setLayout(new BorderLayout(0, 0));
		
		this.listArbitres = new JList<Arbitre>();
		this.listArbitres.setVisibleRowCount(8);
		listArbitres.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.listArbitres.setModel(modeleList);
		JScrollPane sp = new JScrollPane(listArbitres);
		sp.setPreferredSize(new Dimension(50, 110));
		sp.setBounds(new Rectangle(0, 0, 50, 20));
		sp.setSize(new Dimension(100, 20));
		sp.setMaximumSize(new Dimension(100, 100));
		panelListeArbitres.add(sp);
		
		JPanel panelBtnsArbitre = new JPanel();
		panelBtnsArbitre.setBackground(backgroundColor);
		panelListeArbitres.add(panelBtnsArbitre, BorderLayout.SOUTH);
		
		this.btnViderArbitres = new CustomJButton("Vider", 5);
		btnViderArbitres.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		this.btnViderArbitres.addActionListener(controleur);
		panelBtnsArbitre.add(btnViderArbitres);
		
		this.btnSupprimerArbitre = new CustomJButton("Supprimer", 5);
		btnSupprimerArbitre.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		this.btnSupprimerArbitre.addActionListener(controleur);
		panelBtnsArbitre.add(this.btnSupprimerArbitre);
		
		//création du champ de saisie des équipes
		JPanel panelEquipes = new JPanel();
		panelEquipes.setBackground(backgroundColor);
		panelEquipesArbitres.add(panelEquipes, BorderLayout.SOUTH);
		
		JLabel lblEquipesFile = new JLabel("Fichier des équipes :");
		lblEquipesFile.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		lblEquipesFile.setForeground(new Color(107, 173, 221));
		panelEquipes.add(lblEquipesFile);
		
		this.textFieldEquipesFile = new JTextField();
		this.textFieldEquipesFile.setEnabled(false);
		this.textFieldEquipesFile.setEditable(false);
		panelEquipes.add(this.textFieldEquipesFile);
		this.textFieldEquipesFile.setColumns(10);
		
		this.btnImportEquipes = new CustomJButton("Importer", 5);
		btnImportEquipes.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnImportEquipes.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		this.btnImportEquipes.addActionListener(controleur);
		panelEquipes.add(this.btnImportEquipes);
		
		//création du panel de validation 
		JPanel panelValidation = new JPanel();
		panelValidation.setBorder(new EmptyBorder(20, 20, 20, 20));
		panelValidation.setBackground(backgroundColor);
		contentPane.add(panelValidation, BorderLayout.SOUTH);
		panelValidation.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelQuitter = new JPanel();
		panelQuitter.setBackground(backgroundColor);
		panelValidation.add(panelQuitter);
		 
		JButton btnQuitter = new CustomJButton("Quitter", 5);
		btnQuitter.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		btnQuitter.setBackground(new Color(231, 76, 60));
        btnQuitter.setForeground(new Color(255, 255, 255));
		btnQuitter.addActionListener(controleur);
		panelQuitter.add(btnQuitter);
		
		JPanel panelValider = new JPanel();
		panelValider.setBackground(backgroundColor);
		panelValidation.add(panelValider);
		
		this.btnValider = new CustomJButton("Valider", 5);
		btnValider.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		btnValider.setForeground(new Color(255, 255, 255));
        btnValider.setBackground(new Color(46, 204, 113));
        btnValider.addActionListener(controleur);
		panelValider.add(this.btnValider);

	}

}
