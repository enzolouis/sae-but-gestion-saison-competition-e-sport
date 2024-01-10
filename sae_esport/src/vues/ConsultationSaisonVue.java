package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import style.*;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import DAOs.TournoiDAO;
import classes.Joueur;
import controleurs.ConsultationSaisonControleur;
import modeles.ConsultationSaisonModele;
import modeles.TournoiModele;

import java.awt.Rectangle;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class ConsultationSaisonVue extends CustomJFrame {
	
	public JTable table;
	public DefaultTableModel tablemodel;
	public ConsultationSaisonModele modele;
	public JList<TournoiModele> listetournois; 
	public DefaultListModel<TournoiModele> listetournoismodele;
	public JList<Joueur> listejoueurs;
	public DefaultListModel<Joueur> listejoueursmodele;
	public ConsultationSaisonControleur controleur;
	
	
	public ConsultationSaisonVue() {
		
		setTitle("Statistiques de la saison");
		
		ImageIcon icon = new ImageIcon("src\\logo_app.png");
    	this.setIconImage(icon.getImage());
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		controleur = new ConsultationSaisonControleur(this);
		
		this.modele = new ConsultationSaisonModele();
		
		setBounds(new Rectangle(0, 0, 600, 600));
		setResizable(true);
		
		CustomJPanel panel = new CustomJPanel();
		getContentPane().add(panel);
		
		CustomJPanel panelTitre = new CustomJPanel();
		panelTitre.setBackground(Palette.BLACK);
		CustomJLabel labelTitre = new CustomJLabel("Classement de saison", 22);
		labelTitre.setFont(Palette.customFont.deriveFont(Font.BOLD, 20));
		CustomJSeparator separator = new CustomJSeparator();
		panelTitre.setLayout(new BorderLayout(0, 0));
		panelTitre.add(labelTitre, BorderLayout.CENTER);
		panelTitre.add(separator, BorderLayout.SOUTH);
		
		panel.add(panelTitre, BorderLayout.NORTH);
		
		CustomJPanel panelEquipes = new CustomJPanel();
		panelEquipes.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.add(panelEquipes, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		panelEquipes.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(); new JTable(){
			public boolean isCellEditable(int row, int column) {                
				return false;
			};};
		scrollPane.setViewportView(table);
		table.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		table.setOpaque(false);
		table.setForeground(new Color(44, 47, 51));
		table.setSelectionBackground(new Color(198, 224, 242));
		table.setGridColor(new Color(44, 47, 51));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(controleur);
		table.getTableHeader().setBackground(new Color(102,172,221));
		table.getTableHeader().setForeground(Color.WHITE);
		
		tablemodel = new DefaultTableModel() {

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		table.setBorder(null);
		table.setOpaque(false);
		table.setFont(Palette.customFont.deriveFont(Font.PLAIN, 12));
		table.setBackground(Palette.BLACKDARKER);
        table.setForeground(Palette.WHITE);
        table.setSelectionBackground(Palette.WHITE);
        table.setGridColor(Palette.WHITE);
		table.setRowHeight(34);
		table.getTableHeader().setBackground(Palette.BLACKLIGHTER);
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setFont(Palette.customFont.deriveFont(Font.PLAIN, 11));
		
		tablemodel.addColumn("Classement");
		tablemodel.addColumn("Id");
		tablemodel.addColumn("Nom d'équipe");
		tablemodel.addColumn("Nationalité");
		tablemodel.addColumn("Points");
		tablemodel.addColumn("Class. précédent");
		table.setModel(tablemodel);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(35);
		
		try {
			for (int i = 0; i<modele.classement().size(); i++) {
				tablemodel.addRow(new Object[] {i+1,
						modele.classement().get(i).getIdEquipe(),
						modele.classement().get(i).getNom(), 
						modele.classement().get(i).getNationalite(), 
						modele.classement().get(i).getPointsSaison(),
						modele.classement().get(i).getRangSaisonPrecedante()}); 
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table.getColumnModel().removeColumn(table.getColumnModel().getColumn(1));
		
		CustomJPanel panelInfos = new CustomJPanel();
		panel.add(panelInfos, BorderLayout.SOUTH);
		panelInfos.setLayout(new GridLayout(0, 2, 0, 0));
		
		CustomJPanel panelTournois = new CustomJPanel();
		panelTournois.setBorder(new EmptyBorder(0, 0, 0, 5));
		JScrollPane scrollTournois = new JScrollPane();
		scrollTournois.setPreferredSize(new Dimension(2, 100));
		listetournois = new JList<TournoiModele>();
		listetournoismodele = new DefaultListModel<TournoiModele>();
		listetournois.setModel(listetournoismodele);
		
		listetournois.setBackground(Palette.BLACKDARKER);
		listetournois.setForeground(Palette.WHITE);
		listetournois.setFont(Palette.customTextFont);
		listetournois.setBorder(new EmptyBorder(3,3,3,3));
		
		scrollTournois.setViewportView(listetournois);
		panelTournois.add(scrollTournois);
		CustomJLabel labelTournois = new CustomJLabel("Tournois disputés", 20);
		labelTournois.setFont(Palette.customFont);
		panelTournois.add(labelTournois, BorderLayout.NORTH);
		panelInfos.add(panelTournois);	
		
		CustomJPanel panelJoueurs = new CustomJPanel();
		panelJoueurs.setBorder(new EmptyBorder(0, 5, 0, 0));
		JScrollPane scrollJoueurs = new JScrollPane();
		scrollJoueurs.setPreferredSize(new Dimension(2,100));
		listejoueurs = new JList<Joueur>();
		listejoueursmodele = new DefaultListModel<Joueur>();
		listejoueurs.setModel(listejoueursmodele);
		
		listejoueurs.setBackground(Palette.BLACKDARKER);
		listejoueurs.setForeground(Palette.WHITE);
		listejoueurs.setFont(Palette.customTextFont);
		listejoueurs.setBorder(new EmptyBorder(3,3,3,3));
		
		scrollJoueurs.setViewportView(listejoueurs);
		panelJoueurs.add(scrollJoueurs);
		CustomJLabel labelJoueurs = new CustomJLabel("Joueurs de l'équipe", 20);
		labelJoueurs.setFont(Palette.customFont);
		panelJoueurs.add(labelJoueurs, BorderLayout.NORTH);
		panelInfos.add(panelJoueurs);
		
		CustomJPanel panelQuitter = new CustomJPanel();
		panelQuitter.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		CustomJButton btnQuitter = new CustomJButton("Quitter", 15);
		btnQuitter.setBackground(new Color(30,40,45));
		btnQuitter.addActionListener(controleur);
		panelQuitter.setBorder(new EmptyBorder(10,10,10,10));
		panelQuitter.add(btnQuitter);
		getContentPane().add(panelQuitter, BorderLayout.SOUTH);
		
		
	}

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
        			ConsultationSaisonVue frame = new ConsultationSaisonVue();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
		
	}

}
