package vues;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import com.toedter.calendar.JDateChooser;

import classes.Arbitre;
import classes.Nationalite;
import classes.Notoriete;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;

public class CreationTournoiVue extends JFrame {
	
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
	public JList<Arbitre> listArbitres;
	public DefaultListModel<Arbitre> modeleList;
	
	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	String dirProjetJava = System.getProperty("user.dir");
                	System.out.println(dirProjetJava);
            		System.setProperty("derby.system.home", dirProjetJava+"/BDDSAEEsport");
            		DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
        			String urlConnexion = "jdbc:derby:BDDSAEEsport;create=true";
        			Connection dbConnection = DriverManager.getConnection(urlConnexion);
                    CreationTournoiVue frame = new CreationTournoiVue(dbConnection);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
	
	public CreationTournoiVue(Connection dbConnection) {
		
		setMinimumSize(new Dimension(858, 520));
		Color backgroundColor = new Color(44, 47, 51);
		Color policeColor = new Color(107,173,221);
		
		//création du panel principal
		contentPane = new JPanel();
		setContentPane(contentPane);
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
		
		JLabel lblCreationDeTournoi = new JLabel("CREATION DE TOURNOI");
		lblCreationDeTournoi.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreationDeTournoi.setForeground(policeColor);
		lblCreationDeTournoi.setFont(new Font("Tahoma", Font.BOLD, 25));
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
		
		JPanel panelNom = new JPanel();
		panelNom.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelNom.setBackground(backgroundColor);
		panelNomNotoriete.add(panelNom);
		panelNom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lbNom = new JLabel("Nom du tournoi :");
		lbNom.setPreferredSize(new Dimension(110, 30));
		lbNom.setHorizontalAlignment(SwingConstants.RIGHT);
		lbNom.setForeground(policeColor);
		panelNom.add(lbNom);
		
		this.textFieldNom = new JTextField();
		textFieldNom.setPreferredSize(new Dimension(110, 30));
		textFieldNom.setHorizontalAlignment(SwingConstants.CENTER);
		panelNom.add(textFieldNom);
		textFieldNom.setColumns(10);
		
		JPanel panelNotoriete = new JPanel();
		panelNotoriete.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelNotoriete.setBackground(backgroundColor);
		panelNomNotoriete.add(panelNotoriete);
		
		JLabel lbNotoriete = new JLabel("Notoriété :");
		lbNotoriete.setHorizontalAlignment(SwingConstants.RIGHT);
		lbNotoriete.setPreferredSize(new Dimension(110, 30));
		lbNotoriete.setForeground(policeColor);
		panelNotoriete.add(lbNotoriete);
		
		this.comboBoxNotoriete = new JComboBox<Notoriete>();
		this.comboBoxNotoriete.setPreferredSize(new Dimension(95, 30));
		panelNotoriete.add(comboBoxNotoriete);
		for (Notoriete n : Notoriete.values()) {
			this.comboBoxNotoriete.addItem(n);
		}
		
		//création du panel
		JPanel panelDates = new JPanel();
		panelDates.setBackground(backgroundColor);
		panelInformations.add(panelDates);
		panelDates.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panelDateDebut = new JPanel();
		panelDateDebut.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelDateDebut.setBackground(backgroundColor);
		panelDates.add(panelDateDebut);
		
		JLabel lblDateDeDbut = new JLabel("Date de début :");
		lblDateDeDbut.setPreferredSize(new Dimension(100, 30));
		lblDateDeDbut.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateDeDbut.setForeground(policeColor);
		panelDateDebut.add(lblDateDeDbut);
		
		this.dateChooserDebut = new JDateChooser();
		this.dateChooserDebut.setPreferredSize(new Dimension(100, 30));
		panelDateDebut.add(this.dateChooserDebut);
		
		JPanel panelDateFin = new JPanel();
		panelDateFin.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelDateFin.setBackground(backgroundColor);
		panelDates.add(panelDateFin);
		
		JLabel lblDateDeFin = new JLabel("Date de fin :");
		lblDateDeFin.setPreferredSize(new Dimension(100, 30));
		lblDateDeFin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateDeFin.setForeground(policeColor);
		panelDateFin.add(lblDateDeFin);
		
		this.dateChooserFin = new JDateChooser();
		this.dateChooserFin.setPreferredSize(new Dimension(100, 30));
		panelDateFin.add(this.dateChooserFin);
		
		JPanel panelEquipesArbitres = new JPanel();
		panelEquipesArbitres.setBackground(backgroundColor);
		panelContenu.add(panelEquipesArbitres);
		panelEquipesArbitres.setLayout(new BorderLayout(0, 0));
		
		JPanel panelArbitre = new JPanel();
		panelArbitre.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelArbitre.setBackground(backgroundColor);
		panelEquipesArbitres.add(panelArbitre, BorderLayout.CENTER);
		panelArbitre.setLayout(new BorderLayout(0, 0));
		
		JLabel lblChoixArbitres = new JLabel("Séléction des arbitres");
		lblChoixArbitres.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoixArbitres.setForeground(policeColor);
		panelArbitre.add(lblChoixArbitres, BorderLayout.NORTH);
		
		JPanel panelChoixArbitre = new JPanel();
		panelChoixArbitre.setBackground(backgroundColor);
		panelChoixArbitre.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelArbitre.add(panelChoixArbitre, BorderLayout.CENTER);
		
		this.comboBoxArbitre = new JComboBox<Arbitre>();
		panelChoixArbitre.add(comboBoxArbitre);
		this.comboBoxArbitre.addItem(new Arbitre(0, "arbitre", "test", Nationalite.FR));
		this.comboBoxArbitre.addItem(new Arbitre(1, "arbitre", "tentative", Nationalite.FR));
		
		this.btnAddArbitre = new JButton("Ajouter");
		panelChoixArbitre.add(this.btnAddArbitre);
		
		JPanel panelListeArbitres = new JPanel();
		panelListeArbitres.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelListeArbitres.setBackground(backgroundColor);
		panelArbitre.add(panelListeArbitres, BorderLayout.SOUTH);
		panelListeArbitres.setLayout(new BorderLayout(0, 0));
		
		this.listArbitres = new JList<Arbitre>();
		listArbitres.setMinimumSize(new Dimension(100, 100));
		this.listArbitres.setModel(modeleList);
		panelListeArbitres.add(listArbitres);
		
		JPanel panelBtnsArbitre = new JPanel();
		panelBtnsArbitre.setBackground(backgroundColor);
		panelListeArbitres.add(panelBtnsArbitre, BorderLayout.SOUTH);
		
		this.btnViderArbitres = new JButton("Vider");
		panelBtnsArbitre.add(btnViderArbitres);
		
		this.btnSupprimerArbitre = new JButton("Supprimer");
		panelBtnsArbitre.add(this.btnSupprimerArbitre);
		
		JPanel panelEquipes = new JPanel();
		panelEquipes.setBackground(backgroundColor);
		panelEquipesArbitres.add(panelEquipes, BorderLayout.SOUTH);
		
		JLabel lblEquipesFile = new JLabel("Fichier des équipes :");
		lblEquipesFile.setForeground(new Color(107, 173, 221));
		panelEquipes.add(lblEquipesFile);
		
		this.textFieldEquipesFile = new JTextField();
		this.textFieldEquipesFile.setEnabled(false);
		this.textFieldEquipesFile.setEditable(false);
		panelEquipes.add(this.textFieldEquipesFile);
		this.textFieldEquipesFile.setColumns(10);
		
		this.btnImportEquipes = new JButton("Importer");
		panelEquipes.add(this.btnImportEquipes);
		
		JPanel panelValidation = new JPanel();
		panelValidation.setBorder(new EmptyBorder(20, 20, 20, 20));
		panelValidation.setBackground(backgroundColor);
		contentPane.add(panelValidation, BorderLayout.SOUTH);
		panelValidation.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(backgroundColor);
		panelValidation.add(panel_2);
		
		JButton btnQuitter = new JButton("Quitter");
		panel_2.add(btnQuitter);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(backgroundColor);
		panelValidation.add(panel_3);
		
		this.btnValider = new JButton("Valider");
		panel_3.add(this.btnValider);

	}

}
