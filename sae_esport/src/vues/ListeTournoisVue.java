package vues;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import style.*;
import java.awt.GridLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

import DAOs.TournoiDAO;
import classes.Arbitre;
import classes.Equipe;
import classes.Joueur;
import controleurs.ListeTournoisControleur;
import modeles.TournoiModele;

import java.awt.Dimension;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Component;

public class ListeTournoisVue extends CustomJFrame {	
	public JTable tableTournois;
	public CustomJLabel labelTitre;
	public DefaultTableModel tableModel;
	public DefaultListModel<Equipe> listeEquipesModel;
	public DefaultListModel<Arbitre> listeArbitresModel;
	public DefaultListModel<Joueur> joueursModel;
	public JList<Equipe> listeEquipes;
	public JList<Arbitre> listeArbitres;
	public JDateChooser dateDebut;
	public JDateChooser dateFin;
	public CustomJTextField login;
	public CustomJPasswordField mdp;
	public CustomJToggleButton activLogins;
	public CustomJButton boutonOuverture;
	public CustomJLabel erreurOuverture;
	public CustomJLabel titreEquipe;
	public JList<Joueur> joueurs;
	public CustomJLabel disposition;
	
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
		super(new Dimension(500, 700), "Liste des tournois");
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		controleur = new ListeTournoisControleur(this);
		
		CustomJPanel panel = new CustomJPanel();
		getContentPanel().add(panel);
		
		CustomJPanel panelTitre = new CustomJPanel();
		CustomJLabel titre = new CustomJLabel("LES TOURNOIS", 20);
		titre.setFont(Palette.customFont.deriveFont(Font.BOLD, 20));
		
		panelTitre.add(titre);
		
		CustomJSeparator separatorTop = new CustomJSeparator();
		panelTitre.add(separatorTop, BorderLayout.SOUTH);
		
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
		scrollListe.setForeground(Palette.WHITE);
		scrollListe.setPreferredSize(new Dimension(500, 180));
		scrollListe.setSize(new Dimension(100, 100));
		tableTournois = new JTable(){
				public boolean isCellEditable(int row, int column) {                
					return false;
				};};
		tableTournois.getSelectionModel().addListSelectionListener(controleur);
		tableTournois.setBorder(null);
		tableTournois.setOpaque(false);
		tableTournois.setFont(Palette.customFont.deriveFont(Font.PLAIN, 12));
		tableTournois.setForeground(Palette.WHITE);
		tableTournois.setSelectionBackground(Palette.WHITE);
		tableTournois.setGridColor(Palette.WHITE);
		tableTournois.setRowHeight(34);
		
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
		tableTournois.getSelectionModel().addListSelectionListener(controleur);
		tableTournois.getColumnModel().getColumn(0).setPreferredWidth(10);
		
		EvenOddRenderer renderer = new EvenOddRenderer();
		tableTournois.setDefaultRenderer(Object.class, renderer);
		
		tableTournois.getTableHeader().setBackground(Palette.BLACKLIGHTER);
		tableTournois.getTableHeader().setForeground(Color.WHITE);
		tableTournois.getTableHeader().setFont(Palette.customFont.deriveFont(Font.PLAIN, 11));
		
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
		listeEquipes.addMouseListener(controleur);
		listeEquipes.setFont(Palette.customFont.deriveFont(Font.PLAIN, 9));
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
		panelJoueurs.setBorder(new EmptyBorder(0, 30, 0, 30));
		panelJoueurs.setBackground(Palette.BLACK);
		panelEquipe.add(panelJoueurs);
		panelEquipeArbitre.add(panelEquipe);
		
		titreEquipe = new CustomJLabel("aaa", 10);
		titreEquipe.setMinimumSize(new Dimension(150, 14));
		titreEquipe.setMaximumSize(new Dimension(150, 14));
		titreEquipe.setSize(new Dimension(150, 14));
		titreEquipe.setPreferredSize(new Dimension(200, 14));
		titreEquipe.setText("");
		titreEquipe.setFont(Palette.customFont.deriveFont(Font.BOLD, 11));
		titreEquipe.setBounds(new Rectangle(0, 0, 10, 10));
		titreEquipe.setBounds(0, 0, 150, 14);
		joueurs = new JList<Joueur>();
		listeEquipes.setFont(Palette.customFont.deriveFont(Font.PLAIN, 9));
		joueursModel = new DefaultListModel<Joueur>();
		joueurs.setModel(joueursModel);
		//joueurs = new CustomJLabel("bbb", 8);
		//joueurs.setText("");
		joueurs.setFont(Palette.customFont.deriveFont(Font.PLAIN, 9));
		joueurs.setBackground(Palette.BLACK);
		joueurs.setForeground(Palette.WHITE);//joueurs.setVerticalAlignment(SwingConstants.TOP);
		//joueurs.setHorizontalTextPosition(SwingConstants.CENTER);
		//joueurs.setHorizontalAlignment(SwingConstants.LEFT);
		//joueurs.setSize(new Dimension(15, 10));
		//joueurs.setBounds(new Rectangle(10, 20, 10, 20));
		joueurs.setBounds(24, 31, 50, 80);
		disposition = new CustomJLabel("ccc", 8);
		disposition.setText("");
		disposition.setFont(Palette.customFont.deriveFont(Font.BOLD, 9));
		disposition.setBounds(25, 11, 100, 20);
		panelJoueurs.setLayout(null);
		panelJoueurs.add(titreEquipe); panelJoueurs.add(joueurs);
		panelJoueurs.add(disposition);
		
		CustomJPanel panelArbitre = new CustomJPanel();
		panelArbitre.setBorder(new EmptyBorder(5,5,5,5));
		listeArbitres = new JList<Arbitre>();
		listeArbitres.setBounds(new Rectangle(0, 0, 140, 0));
		listeArbitres.setFont(Palette.customFont.deriveFont(Font.PLAIN, 9));
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
		labelTitre.setFont(Palette.customFont.deriveFont(Font.BOLD, 15));
		titreTournoi.add(labelTitre);
		
		dateDebut = new JDateChooser();
		dateDebut.setEnabled(false);
		dateDebut.setBounds(30, 5, 100, 20);
		dateDebut.setFont(Palette.customFont.deriveFont(Font.PLAIN, 11));
		dateFin = new JDateChooser();
		dateFin.setEnabled(false);
		dateFin.setBounds(157, 5, 100, 20);
		dateFin.setFont(Palette.customFont.deriveFont(Font.PLAIN, 11));
		dates.setLayout(null);
		dates.add(dateDebut); dates.add(dateFin);
		
		login = new CustomJTextField();
		login.setEnabled(false);
		login.setFont(Palette.customFont.deriveFont(Font.PLAIN, 10));
		login.setBounds(94, 35, 92, 20);
		mdp = new CustomJPasswordField();
		mdp.setEnabled(false);
		mdp.setFont(Palette.customFont.deriveFont(Font.PLAIN, 10));
		mdp.setBounds(95, 60, 92, 20);
		CustomJLabel labelLogin = new CustomJLabel("Login d'arbitre", 11);
		labelLogin.setFont(Palette.customFont.deriveFont(Font.PLAIN, 11));
		labelLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		labelLogin.setText("Identifiant");
		labelLogin.setBounds(10, 30, 74, 30);
		CustomJLabel labelMdp = new CustomJLabel("Mot de passe d'arbitre", 11);
		labelLogin.setFont(Palette.customFont.deriveFont(Font.PLAIN, 11));
		labelMdp.setHorizontalAlignment(SwingConstants.RIGHT);
		labelMdp.setText("Mot de passe");
		labelMdp.setSize(75, 30);
		labelMdp.setLocation(10, 55);
		logins.add(labelLogin); logins.add(labelMdp);
		
		logins.setLayout(null);
		logins.add(login); logins.add(mdp);
		activLogins = new CustomJToggleButton("", 25, (EmptyBorder) null);
		activLogins.setFocusPainted(false);
		activLogins.setBackground(new Color(29, 88, 129));
		activLogins.setBorder(null);
		activLogins.setIcon(Palette.OEIL_INVISIBLE_ICON);
		activLogins.setBounds(196, 44, 40, 20);
		logins.add(activLogins);
		
		CustomJLabel labelLogin_1 = new CustomJLabel("Login d'arbitre", 11);
		labelLogin_1.setText("Login d'arbitre :");
		labelLogin_1.setHorizontalAlignment(SwingConstants.LEFT);
		labelLogin_1.setFont(Palette.customFont.deriveFont(Font.PLAIN, 13));
		labelLogin_1.setBounds(10, 0, 174, 30);
		logins.add(labelLogin_1);
		activLogins.addActionListener(controleur);
		
		CustomJLabel labelDateD = new CustomJLabel("Du", 15);
		labelDateD.setFont(Palette.customFont.deriveFont(Font.PLAIN, 11));
		labelDateD.setBounds(0, 0, 32, 30);
		CustomJLabel labelDateF = new CustomJLabel("au", 15);
		labelDateF.setFont(Palette.customFont.deriveFont(Font.PLAIN, 11));
		labelDateF.setBounds(125, 0, 32, 30);
		dates.add(labelDateD); dates.add(labelDateF);
		boutonOuverture = new CustomJButton("Ouvrir", 15);
		boutonOuverture.setAlignmentX(Component.CENTER_ALIGNMENT);
		boutonOuverture.setEnabled(false);
		boutonOuverture.addActionListener(controleur);
		boutonsTournoi.setLayout(new BoxLayout(boutonsTournoi, BoxLayout.Y_AXIS));
		boutonOuverture.setFont(Palette.customFont.deriveFont(Font.PLAIN, 11));
		boutonsTournoi.add(boutonOuverture);
		
		erreurOuverture = new CustomJLabel("",10);
		erreurOuverture.setText(" ");
		erreurOuverture.setForeground(new Color(235, 77, 75));
		erreurOuverture.setBorder(new EmptyBorder(3, 3, 3, 3));
		erreurOuverture.setAlignmentX(Component.CENTER_ALIGNMENT);
		boutonsTournoi.add(erreurOuverture);
	}
	

	public void closeCurrentWindow() {
		super.closeCurrentWindow();
		AccueilAdministrateurVue frame = new AccueilAdministrateurVue();
		frame.setVisible(true);
	}
}
