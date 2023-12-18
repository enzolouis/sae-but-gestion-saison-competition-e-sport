package vues;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import style.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;

import DAOs.TournoiDAO;
import classes.Arbitre;
import classes.Equipe;
import modeles.TournoiModele;

import java.awt.Dimension;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.JButton;

public class ListeTournoisVue extends CustomJFrame {
	
	public JTable tableTournois;
	public CustomJLabel labelTitre;
	public DefaultTableModel tableModel;
	public JList<Equipe> listeEquipes;
	public JList<Arbitre> listeArbitres;
	public JDateChooser dateDebut;
	public JDateChooser dateFin;
	public CustomJTextField login;
	public CustomJTextField mdp;
	public JToggleButton activLogins;
	public CustomJButton boutonOuverture;
	
	 public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	        			ListeTournoisVue frame = new ListeTournoisVue();
	                    frame.setVisible(true);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }
	
	public ListeTournoisVue() {
		
		setSize(new Dimension(600, 500));
		setResizable(true);
		
		CustomJPanel panel = new CustomJPanel();
		getContentPane().add(panel);
		
		CustomJPanel panelTitre = new CustomJPanel();
		CustomJLabel titre = new CustomJLabel("Liste des tournois", 20);
		panelTitre.add(titre);
		
		CustomJPanel panelTournois = new CustomJPanel();
		panel.add(panelTitre, BorderLayout.NORTH);	
		panel.add(panelTournois, BorderLayout.CENTER);	
		panelTournois.setLayout(new GridLayout(2, 1, 0, 0));
		
		CustomJPanel panelListe = new CustomJPanel();
		panelListe.setOpaque(false);
		panelListe.setBorder(new EmptyBorder(15, 15, 15, 15));
		panelTournois.add(panelListe);
		panelListe.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JScrollPane scrollListe = new JScrollPane();
		scrollListe.setForeground(new Color(255, 255, 255));
		scrollListe.setPreferredSize(new Dimension(500, 180));
		scrollListe.setSize(new Dimension(100, 100));
		tableTournois = new JTable();
		tableTournois.setOpaque(false);
		tableTournois.setForeground(new Color(44, 47, 51));
		tableTournois.setSelectionBackground(new Color(198, 224, 242));
		tableTournois.setGridColor(new Color(44, 47, 51));
		tableModel = new DefaultTableModel();
		tableModel.addColumn("ID"); tableModel.addColumn("Titre"); 
		tableModel.addColumn("Date de début"); tableModel.addColumn("Date de fin");
		tableModel.addColumn("Notoriété"); tableModel.addColumn("Etat");
		try {
			for (TournoiModele t : TournoiDAO.getInstance().getAll()) {
				tableModel.addRow(new Object[] {t.getIDTournoi(), t.getNomTournoi(),
						t.getDateDebut(), t.getDateFin(), t.getNotoriete(), t.getEtatTournoi()});
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tableTournois.setModel(tableModel);
		tableTournois.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableTournois.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableTournois.getTableHeader().setBackground(new Color(102,172,221));
		tableTournois.getTableHeader().setForeground(Color.WHITE);
		scrollListe.setViewportView(tableTournois);
		panelListe.add(scrollListe);
		
		CustomJPanel panelInfos = new CustomJPanel();
		panelInfos.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelTournois.add(panelInfos);
		panelInfos.setLayout(new GridLayout(0, 2, 0, 0));
		
		CustomJPanel panelEquipeArbitre = new CustomJPanel();
		panelEquipeArbitre.setBorder(new EmptyBorder(5, 5, 5, 5));
		CustomJPanel panelAttributs = new CustomJPanel();
		panelInfos.add(panelAttributs);
		panelAttributs.setLayout(new BorderLayout(0, 0));
		panelInfos.add(panelEquipeArbitre);
		panelEquipeArbitre.setLayout(new GridLayout(0, 2, 0, 0));
		
		CustomJPanel panelEquipe = new CustomJPanel();
		panelEquipe.setBorder(new EmptyBorder(5, 5, 5, 5));
		JScrollPane scrollEquipes = new JScrollPane();
		panelEquipe.add(scrollEquipes);
		listeEquipes = new JList<Equipe>();
		scrollEquipes.add(listeEquipes);
		
		panelEquipeArbitre.add(panelEquipe);
		
		CustomJPanel panelArbitre = new CustomJPanel();
		panelArbitre.setBorder(new EmptyBorder(5,5,5,5));
		JScrollPane scrollArbitres = new JScrollPane();
		panelArbitre.add(scrollArbitres);
		listeArbitres = new JList<Arbitre>();
		scrollArbitres.add(listeArbitres);
		
		panelEquipeArbitre.add(panelArbitre);
		
		CustomJPanel titreTournoi = new CustomJPanel();
		titreTournoi.setBorder(new EmptyBorder(5, 5, 5, 5));
		CustomJPanel boutonsTournoi = new CustomJPanel();
		CustomJPanel infosTournoi = new CustomJPanel();
		panelAttributs.add(titreTournoi, BorderLayout.NORTH);
		panelAttributs.add(boutonsTournoi, BorderLayout.SOUTH);
		boutonsTournoi.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelAttributs.add(infosTournoi, BorderLayout.CENTER);
		
		CustomJPanel logins = new CustomJPanel();
		CustomJPanel dates = new CustomJPanel();
		dates.setMaximumSize(new Dimension(2147483647, 15000));
		infosTournoi.setLayout(new BoxLayout(infosTournoi, BoxLayout.Y_AXIS));
		infosTournoi.add(logins); infosTournoi.add(dates);
		
		labelTitre = new CustomJLabel("Tournoi N°XX (Notoriété)", 15);
		titreTournoi.add(labelTitre);
		
		dateDebut = new JDateChooser();
		dateDebut.setEnabled(false);
		dateDebut.setBounds(30, 5, 90, 20);
		dateFin = new JDateChooser();
		dateFin.setEnabled(false);
		dateFin.setBounds(152, 5, 90, 20);
		dates.setLayout(null);
		dates.add(dateDebut); dates.add(dateFin);
		
		login = new CustomJTextField();
		login.setBounds(94, 16, 92, 20);
		mdp = new CustomJTextField();
		mdp.setBounds(94, 46, 92, 20);
		CustomJLabel labelLogin = new CustomJLabel("Login d'arbitre", 11);
		labelLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		labelLogin.setText("Identifiant");
		labelLogin.setBounds(10, 11, 74, 30);
		CustomJLabel labelMdp = new CustomJLabel("Mot de passe d'arbitre", 11);
		labelMdp.setHorizontalAlignment(SwingConstants.RIGHT);
		labelMdp.setText("Mot de passe");
		labelMdp.setSize(75, 30);
		labelMdp.setLocation(10, 41);
		logins.add(labelLogin); logins.add(labelMdp);
		
		logins.setLayout(null);
		logins.add(login); logins.add(mdp);
		activLogins = new JToggleButton();
		activLogins.setFont(new Font("Tahoma", Font.PLAIN, 7));
		activLogins.setText("Afficher");
		activLogins.setBounds(193, 30, 65, 20);
		logins.add(activLogins);
		activLogins.addActionListener((new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() instanceof JToggleButton) {
					JToggleButton bouton = (JToggleButton) e.getSource();
					if (bouton.isSelected()) {
						bouton.setText("Masquer");
					} else {
						bouton.setText("Afficher");
					}
				}
			};
		}
		));
		
		CustomJLabel labelDateD = new CustomJLabel("Du", 15);
		labelDateD.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelDateD.setBounds(0, 0, 32, 30);
		CustomJLabel labelDateF = new CustomJLabel("au", 15);
		labelDateF.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelDateF.setBounds(118, 0, 32, 30);
		dates.add(labelDateD); dates.add(labelDateF);
		boutonOuverture = new CustomJButton("Ouvrir", 15);
		boutonsTournoi.add(boutonOuverture);
		
	}
	
}
