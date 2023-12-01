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
import javax.swing.JFileChooser;
import javax.swing.JList;

public class CreationTournoiVue extends JFrame {
	
	JPanel contentPane;
	private JTextField textFieldEquipesFile;
	
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
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setBackground(new Color(44, 47, 51));
		contentPane.setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(30,30,30,30));
		JPanel panelTitre = new JPanel();
		panelTitre.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelTitre.setBackground(new Color(44, 47, 51));
		contentPane.add(panelTitre, BorderLayout.NORTH);
		panelTitre.setLayout(new BorderLayout(0, 0));
		
		JSeparator separatorTitre = new JSeparator();
		separatorTitre.setForeground(new Color(102, 173, 221));
		separatorTitre.setBackground(new Color(102, 173, 221));
		panelTitre.add(separatorTitre, BorderLayout.SOUTH);
		
		JLabel lblCreationDeTournoi = new JLabel("CREATION DE TOURNOI");
		lblCreationDeTournoi.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreationDeTournoi.setForeground(new Color(102, 173, 221));
		lblCreationDeTournoi.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblCreationDeTournoi.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelTitre.add(lblCreationDeTournoi, BorderLayout.CENTER);
		
		JPanel panelContenu = new JPanel();
		panelContenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelContenu.setBackground(new Color(44, 47, 51));
		contentPane.add(panelContenu, BorderLayout.CENTER);
		panelContenu.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelInformations = new JPanel();
		panelInformations.setBackground(new Color(44, 47, 51));
		panelContenu.add(panelInformations);
		panelInformations.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panelNomNotoriete = new JPanel();
		panelNomNotoriete.setBackground(new Color(44, 47, 51));
		panelInformations.add(panelNomNotoriete);
		panelNomNotoriete.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panelNom = new JPanel();
		panelNom.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelNom.setBackground(new Color(44, 47, 51));
		panelNomNotoriete.add(panelNom);
		panelNom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lbNom = new JLabel("Nom du tournoi :");
		lbNom.setPreferredSize(new Dimension(110, 30));
		lbNom.setHorizontalAlignment(SwingConstants.RIGHT);
		lbNom.setForeground(new Color(107, 173, 221));
		panelNom.add(lbNom);
		
		JTextField textFieldNom = new JTextField();
		textFieldNom.setPreferredSize(new Dimension(110, 30));
		textFieldNom.setHorizontalAlignment(SwingConstants.CENTER);
		panelNom.add(textFieldNom);
		textFieldNom.setColumns(10);
		
		JPanel panelNotoriete = new JPanel();
		panelNotoriete.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelNotoriete.setBackground(new Color(44, 47, 51));
		panelNomNotoriete.add(panelNotoriete);
		
		JLabel lbNotoriete = new JLabel("Notoriété :");
		lbNotoriete.setHorizontalAlignment(SwingConstants.RIGHT);
		lbNotoriete.setPreferredSize(new Dimension(110, 30));
		lbNotoriete.setForeground(new Color(107, 173, 221));
		panelNotoriete.add(lbNotoriete);
		
		JComboBox<Notoriete> comboBoxNotoriete = new JComboBox<Notoriete>();
		comboBoxNotoriete.setPreferredSize(new Dimension(95, 30));
		panelNotoriete.add(comboBoxNotoriete);
		for (Notoriete n : Notoriete.values()) {
			comboBoxNotoriete.addItem(n);
		}
		
		JPanel panelDates = new JPanel();
		panelDates.setBackground(new Color(44, 47, 51));
		panelInformations.add(panelDates);
		panelDates.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panelDateDebut = new JPanel();
		panelDateDebut.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelDateDebut.setBackground(new Color(44, 47, 51));
		panelDates.add(panelDateDebut);
		
		JLabel lblDateDeDbut = new JLabel("Date de début :");
		lblDateDeDbut.setPreferredSize(new Dimension(100, 30));
		lblDateDeDbut.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateDeDbut.setForeground(new Color(107, 173, 221));
		panelDateDebut.add(lblDateDeDbut);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setPreferredSize(new Dimension(100, 30));
		panelDateDebut.add(dateChooser);
		
		JPanel panelDateFin = new JPanel();
		panelDateFin.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelDateFin.setBackground(new Color(44, 47, 51));
		panelDates.add(panelDateFin);
		
		JLabel lblDateDeFin = new JLabel("Date de fin :");
		lblDateDeFin.setPreferredSize(new Dimension(100, 30));
		lblDateDeFin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateDeFin.setForeground(new Color(107, 173, 221));
		panelDateFin.add(lblDateDeFin);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setPreferredSize(new Dimension(100, 30));
		panelDateFin.add(dateChooser_1);
		
		JPanel panelEquipesArbitres = new JPanel();
		panelEquipesArbitres.setBackground(new Color(44, 47, 51));
		panelContenu.add(panelEquipesArbitres);
		panelEquipesArbitres.setLayout(new BorderLayout(0, 0));
		
		JPanel panelArbitre = new JPanel();
		panelArbitre.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelArbitre.setBackground(new Color(44, 47, 51));
		panelEquipesArbitres.add(panelArbitre, BorderLayout.CENTER);
		panelArbitre.setLayout(new BorderLayout(0, 0));
		
		JLabel lblChoixArbitres = new JLabel("Séléction des arbitres");
		lblChoixArbitres.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoixArbitres.setForeground(new Color(107, 173, 221));
		panelArbitre.add(lblChoixArbitres, BorderLayout.NORTH);
		
		JPanel panelChoixArbitre = new JPanel();
		panelChoixArbitre.setBackground(new Color(44, 47, 51));
		panelChoixArbitre.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelArbitre.add(panelChoixArbitre, BorderLayout.CENTER);
		
		JComboBox<Arbitre> comboBoxArbitre = new JComboBox<Arbitre>();
		panelChoixArbitre.add(comboBoxArbitre);
		comboBoxArbitre.addItem(new Arbitre(0, "arbitre", "test", Nationalite.FR));
		comboBoxArbitre.addItem(new Arbitre(1, "arbitre", "tentative", Nationalite.FR));
		
		JButton btnAddArbitre = new JButton("Ajouter");
		panelChoixArbitre.add(btnAddArbitre);
		
		JPanel panelListeArbitres = new JPanel();
		panelListeArbitres.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelListeArbitres.setBackground(new Color(44, 47, 51));
		panelArbitre.add(panelListeArbitres, BorderLayout.SOUTH);
		panelListeArbitres.setLayout(new BorderLayout(0, 0));
		
		JList<Arbitre> list = new JList<Arbitre>();
		list.setMinimumSize(new Dimension(100, 100));
		panelListeArbitres.add(list);
		
		JPanel panelBtnsArbitre = new JPanel();
		panelBtnsArbitre.setBackground(new Color(44, 47, 51));
		panelListeArbitres.add(panelBtnsArbitre, BorderLayout.SOUTH);
		
		JButton btnViderArbitres = new JButton("Vider");
		panelBtnsArbitre.add(btnViderArbitres);
		
		JButton btnSupprimerArbitre = new JButton("Supprimer");
		panelBtnsArbitre.add(btnSupprimerArbitre);
		
		JPanel panelEquipes = new JPanel();
		panelEquipes.setBackground(new Color(44, 47, 51));
		panelEquipesArbitres.add(panelEquipes, BorderLayout.SOUTH);
		
		JLabel lblEquipesFile = new JLabel("Fichier des équipes :");
		lblEquipesFile.setForeground(new Color(107, 173, 221));
		panelEquipes.add(lblEquipesFile);
		
		textFieldEquipesFile = new JTextField();
		textFieldEquipesFile.setEnabled(false);
		textFieldEquipesFile.setEditable(false);
		panelEquipes.add(textFieldEquipesFile);
		textFieldEquipesFile.setColumns(10);
		
		JButton btnImportEquipes = new JButton("Importer");
		panelEquipes.add(btnImportEquipes);
		
		JPanel panelValidation = new JPanel();
		panelValidation.setBorder(new EmptyBorder(20, 20, 20, 20));
		panelValidation.setBackground(new Color(44, 47, 51));
		contentPane.add(panelValidation, BorderLayout.SOUTH);
		panelValidation.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(44, 47, 51));
		panelValidation.add(panel_2);
		
		JButton btnQuitter = new JButton("Quitter");
		panel_2.add(btnQuitter);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(44, 47, 51));
		panelValidation.add(panel_3);
		
		JButton btnValider = new JButton("Valider");
		panel_3.add(btnValider);

	}

}
