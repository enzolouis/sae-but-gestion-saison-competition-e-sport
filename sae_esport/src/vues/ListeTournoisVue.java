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
import controleurs.ListeTournoisControleur;
import modeles.TournoiModele;

import java.awt.Dimension;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.Component;

public class ListeTournoisVue extends CustomJFrame {
	
	public static final ImageIcon OEIL_INVISIBLE_ICON = new ImageIcon(new ImageIcon(IdentificationVue.class.getClassLoader().getResource
			("oeilMotDePasseInvisible.png")).getImage().getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH));
	
	public static final ImageIcon OEIL_VISIBLE_ICON = new ImageIcon(new ImageIcon(IdentificationVue.class.getClassLoader().getResource
			("oeilMotDePasseVisible.png")).getImage().getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH));
	
	
	public JTable tableTournois;
	public CustomJLabel labelTitre;
	public DefaultTableModel tableModel;
	public DefaultListModel<Equipe> listeEquipesModel;
	public DefaultListModel<Arbitre> listeArbitresModel;
	public JList<Equipe> listeEquipes;
	public JList<Arbitre> listeArbitres;
	public JDateChooser dateDebut;
	public JDateChooser dateFin;
	public CustomJTextField login;
	public CustomJPasswordField mdp;
	public CustomJToggleButton activLogins;
	public CustomJButton boutonOuverture;
	public CustomJLabel erreurOuverture;
	
	private ListeTournoisControleur controleur;
	
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
		setTitle("Liste des tournois");
		
		controleur = new ListeTournoisControleur(this);
		
		setSize(new Dimension(700, 550));
		
		CustomJPanel panel = new CustomJPanel();
		getContentPane().add(panel);
		
		CustomJPanel panelTitre = new CustomJPanel();
		CustomJLabel titre = new CustomJLabel("Liste des tournois", 20);
		titre.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
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
		tableTournois = new JTable(){
				public boolean isCellEditable(int row, int column) {                
					return false;
				};};
		tableTournois.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		tableTournois.addMouseListener(controleur);
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
		
		CustomJPanel panelEquipe = new CustomJPanel();
		CustomJPanel panelListeEquipe = new CustomJPanel();
		panelListeEquipe.setBorder(new EmptyBorder(5, 5, 0, 5));
		listeEquipes = new JList<Equipe>();
		listeEquipes.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 7));
		listeEquipesModel = new DefaultListModel<Equipe>();
		panelEquipeArbitre.setLayout(new GridLayout(0, 2, 0, 0));
		panelEquipe.setLayout(new GridLayout(2, 1, 0, 0));
		JScrollPane scrollEquipes = new JScrollPane(listeEquipes);
		panelListeEquipe.add(scrollEquipes);
		panelEquipe.add(panelListeEquipe);
		CustomJLabel equipeTitre = new CustomJLabel("Equipes", 15);
		equipeTitre.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelListeEquipe.add(equipeTitre, BorderLayout.NORTH);
		CustomJPanel panelJoueurs = new CustomJPanel();
		panelJoueurs.setBackground(new Color(44, 47, 51));
		panelEquipe.add(panelJoueurs);
		panelEquipeArbitre.add(panelEquipe); 
		
		CustomJLabel titreEquipe = new CustomJLabel("aaa", 10);
		titreEquipe.setBounds(new Rectangle(0, 0, 10, 10));
		titreEquipe.setBounds(49, 11, 53, 10);
		CustomJLabel joueurs = new CustomJLabel("bbb", 8);
		joueurs.setSize(new Dimension(15, 10));
		joueurs.setBounds(new Rectangle(10, 10, 10, 10));
		joueurs.setBounds(42, 37, 84, 10);
		CustomJLabel disposition = new CustomJLabel("ccc", 8);
		disposition.setBounds(0, 0, 149, 97);
		panelJoueurs.setLayout(null);
		panelJoueurs.add(titreEquipe); panelJoueurs.add(joueurs);
		panelJoueurs.add(disposition);
		
		CustomJPanel panelArbitre = new CustomJPanel();
		panelArbitre.setBorder(new EmptyBorder(5,5,5,5));
		listeArbitres = new JList<Arbitre>();
		listeArbitres.setBounds(new Rectangle(0, 0, 140, 0));
		listeArbitres.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 7));
		listeArbitresModel = new DefaultListModel<Arbitre>();
		JScrollPane scrollArbitres = new JScrollPane(listeArbitres);
		panelArbitre.add(scrollArbitres);
		CustomJLabel arbitreTitre = new CustomJLabel("Arbitres", 15);
		arbitreTitre.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelArbitre.add(arbitreTitre, BorderLayout.NORTH);
		
		panelEquipeArbitre.add(panelArbitre);
		
		CustomJPanel titreTournoi = new CustomJPanel();
		titreTournoi.setBorder(new EmptyBorder(5, 5, 5, 5));
		CustomJPanel boutonsTournoi = new CustomJPanel();
		CustomJPanel infosTournoi = new CustomJPanel();
		panelAttributs.add(titreTournoi, BorderLayout.NORTH);
		panelAttributs.add(boutonsTournoi, BorderLayout.SOUTH);
		panelAttributs.add(infosTournoi, BorderLayout.CENTER);
		
		CustomJPanel logins = new CustomJPanel();
		CustomJPanel dates = new CustomJPanel();
		dates.setMaximumSize(new Dimension(2147483647, 15000));
		infosTournoi.setLayout(new BoxLayout(infosTournoi, BoxLayout.Y_AXIS));
		infosTournoi.add(logins); infosTournoi.add(dates);
		
		labelTitre = new CustomJLabel("Tournoi N°XX (Notoriété)", 15);
		labelTitre.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 15));
		titreTournoi.add(labelTitre);
		
		dateDebut = new JDateChooser();
		dateDebut.setEnabled(false);
		dateDebut.setBounds(30, 5, 100, 20);
		dateFin = new JDateChooser();
		dateFin.setEnabled(false);
		dateFin.setBounds(157, 5, 100, 20);
		dates.setLayout(null);
		dates.add(dateDebut); dates.add(dateFin);
		
		login = new CustomJTextField();
		login.setEnabled(false);
		login.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 10));
		login.setBounds(94, 16, 92, 20);
		mdp = new CustomJPasswordField();
		mdp.setEnabled(false);
		mdp.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 10));
		mdp.setBounds(94, 46, 92, 20);
		CustomJLabel labelLogin = new CustomJLabel("Login d'arbitre", 11);
		labelLogin.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		labelLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		labelLogin.setText("Identifiant");
		labelLogin.setBounds(10, 11, 74, 30);
		CustomJLabel labelMdp = new CustomJLabel("Mot de passe d'arbitre", 11);
		labelMdp.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		labelMdp.setHorizontalAlignment(SwingConstants.RIGHT);
		labelMdp.setText("Mot de passe");
		labelMdp.setSize(75, 30);
		labelMdp.setLocation(10, 41);
		logins.add(labelLogin); logins.add(labelMdp);
		
		logins.setLayout(null);
		logins.add(login); logins.add(mdp);
		activLogins = new CustomJToggleButton("", 25, (EmptyBorder) null);
		activLogins.setFocusPainted(false);
		activLogins.setBackground(new Color(29, 88, 129));
		activLogins.setBorder(null);
		activLogins.setIcon(OEIL_INVISIBLE_ICON);
		activLogins.setBounds(196, 30, 40, 20);
		logins.add(activLogins);
		activLogins.addActionListener(controleur);
		
		CustomJLabel labelDateD = new CustomJLabel("Du", 15);
		labelDateD.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		labelDateD.setBounds(0, 0, 32, 30);
		CustomJLabel labelDateF = new CustomJLabel("au", 15);
		labelDateF.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		labelDateF.setBounds(125, 0, 32, 30);
		dates.add(labelDateD); dates.add(labelDateF);
		boutonOuverture = new CustomJButton("Ouvrir", 15);
		boutonOuverture.setAlignmentX(Component.CENTER_ALIGNMENT);
		boutonOuverture.setEnabled(false);
		boutonOuverture.addActionListener(controleur);
		boutonsTournoi.setLayout(new BoxLayout(boutonsTournoi, BoxLayout.Y_AXIS));
		boutonOuverture.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		boutonsTournoi.add(boutonOuverture);
		
		erreurOuverture = new CustomJLabel("",10);
		erreurOuverture.setBorder(new EmptyBorder(3, 3, 3, 3));
		erreurOuverture.setText("dcc c    ");
		erreurOuverture.setAlignmentX(Component.CENTER_ALIGNMENT);
		boutonsTournoi.add(erreurOuverture);
	}
}
