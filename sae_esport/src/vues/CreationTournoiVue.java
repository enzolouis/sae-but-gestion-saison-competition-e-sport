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
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.BoxLayout;

public class CreationTournoiVue extends JFrame {
	
	JPanel contentPane;
	
	public CreationTournoiVue() {
		
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
		panelNom.setBorder(new EmptyBorder(10, 10, 10, 10));
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
		panelNotoriete.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelNotoriete.setBackground(new Color(44, 47, 51));
		panelNomNotoriete.add(panelNotoriete);
		
		JLabel lbNotoriete = new JLabel("Notoriété :");
		lbNotoriete.setHorizontalAlignment(SwingConstants.RIGHT);
		lbNotoriete.setPreferredSize(new Dimension(110, 30));
		lbNotoriete.setForeground(new Color(107, 173, 221));
		panelNotoriete.add(lbNotoriete);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setPreferredSize(new Dimension(95, 30));
		panelNotoriete.add(comboBox);
		
		JPanel panelDates = new JPanel();
		panelDates.setBackground(new Color(44, 47, 51));
		panelInformations.add(panelDates);
		panelDates.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panelDateDebut = new JPanel();
		panelDateDebut.setBorder(new EmptyBorder(10, 10, 10, 10));
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
		panelDateFin.setBorder(new EmptyBorder(10, 10, 10, 10));
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
		panelArbitre.setBackground(new Color(44, 47, 51));
		panelEquipesArbitres.add(panelArbitre, BorderLayout.CENTER);
		panelArbitre.setLayout(new BoxLayout(panelArbitre, BoxLayout.X_AXIS));
		
		JPanel panelEquipes = new JPanel();
		panelEquipes.setBackground(new Color(44, 47, 51));
		panelEquipesArbitres.add(panelEquipes, BorderLayout.SOUTH);
		
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
