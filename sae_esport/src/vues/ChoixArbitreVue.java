package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

import controleurs.ChoixArbitreControleur;
import style.CustomScrollBarUI;

public class ChoixArbitreVue extends JFrame {

    private JPanel contentPanel;
    private ChoixArbitreControleur controleur;
    private Connection dbConnection;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	String dirProjetJava = System.getProperty("user.dir");
            		System.setProperty("derby.system.home", dirProjetJava+"/BDDSAEEsport");
            		DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
        			String urlConnexion = "jdbc:derby:BDDSAEEsport;create=true";
        			Connection dbConnection = DriverManager.getConnection(urlConnexion);
        			ChoixArbitreVue frame = new ChoixArbitreVue(dbConnection);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public ChoixArbitreVue(Connection dbConnection) throws Exception {
    	
    	// Contrôleur + DB
    	this.dbConnection = dbConnection;
    	this.controleur = new ChoixArbitreControleur(this, dbConnection);
    	
    	// JFrame
    	contentPanel = new JPanel();
    	contentPanel.setBackground(new Color(44, 47, 51));
    	contentPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
    	contentPanel.setLayout(new BorderLayout(0, 0));
    	setMinimumSize(new Dimension(900, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setContentPane(contentPanel);
        
        // Panel Top : Title
        JPanel panelTop = new JPanel();
        panelTop.setBackground(new Color(44, 47, 51));
        panelTop.setLayout(new BorderLayout(0, 0));
        contentPanel.add(panelTop, BorderLayout.NORTH);
        
        JLabel titleTop = new JLabel("Choix des arbitres");
        titleTop.setHorizontalAlignment(SwingConstants.CENTER);
        titleTop.setForeground(new Color(102, 173, 221));
        titleTop.setFont(new Font("Tahoma", Font.BOLD, 25));
        titleTop.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelTop.add(titleTop);
        
        JSeparator separatorTop = new JSeparator();
        separatorTop.setBackground(new Color(102, 173, 221));
        separatorTop.setForeground(new Color(102, 173, 221));
        panelTop.add(separatorTop, BorderLayout.SOUTH);
        
        // Panel middle : Action et Listes
        
        JPanel panelMiddle = new JPanel();
        panelMiddle.setBorder(new EmptyBorder(10, 0, 0, 0));
        panelMiddle.setBackground(new Color(44, 47, 51));
        contentPanel.add(panelMiddle);
        
        GridBagLayout gbl_panelMiddle = new GridBagLayout();
        gbl_panelMiddle.rowHeights = new int[] {266, 133};
        gbl_panelMiddle.columnWidths = new int[] {399};
        gbl_panelMiddle.columnWeights = new double[]{1.0};
        gbl_panelMiddle.rowWeights = new double[]{0.0, 0.0};
        panelMiddle.setLayout(gbl_panelMiddle);
        
        // Panel middle --> Panel Liste : Ligne 1 et 2
        JPanel panelArbitreList = new JPanel();
        panelArbitreList.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelArbitreList.setBackground(new Color(44, 47, 51));
        
        GridLayout panelArbitreListLayout = new GridLayout(0, 4, 10, 10);
        panelArbitreList.setLayout(panelArbitreListLayout);  
        
        JScrollPane scrollpanelArbitreList = new JScrollPane(panelArbitreList);
        scrollpanelArbitreList.setBorder(BorderFactory.createEmptyBorder());
        scrollpanelArbitreList.setViewportBorder(BorderFactory.createEmptyBorder());
        scrollpanelArbitreList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpanelArbitreList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        BasicScrollBarUI scrollBarArbitreListBarUI = new CustomScrollBarUI();
        
        scrollpanelArbitreList.getVerticalScrollBar().setUI(scrollBarArbitreListBarUI);
        
        GridBagConstraints gbc_scrollpanelArbitreList = new GridBagConstraints();
        gbc_scrollpanelArbitreList.insets = new Insets(0, 0, 0, 0);
        gbc_scrollpanelArbitreList.fill = GridBagConstraints.BOTH;
        gbc_scrollpanelArbitreList.gridx = 0;
        gbc_scrollpanelArbitreList.gridy = 0;
        panelMiddle.add(scrollpanelArbitreList, gbc_scrollpanelArbitreList);
       
        for (int i = 1; i <= 35; i++) {
        	JButton button = new JButton("Arbitre n°" + i);
        	button.setBackground(new Color(29, 88, 129));
        	button.setForeground(new Color(255, 255, 255));
        	button.setBorder(new RoundBtn(5));
        	button.addActionListener(this.controleur);
        	panelArbitreList.add(button);
		}
        
        // Panel middle --> Panel Action : Ligne 3
        JPanel panelArbitreActions = new JPanel();
        panelArbitreActions.setBackground(new Color(44, 47, 51));
        GridBagConstraints gbc_panelArbitreActions = new GridBagConstraints();
        gbc_panelArbitreActions.fill = GridBagConstraints.BOTH;
        gbc_panelArbitreActions.gridx = 0;
        gbc_panelArbitreActions.gridy = 1;
        panelMiddle.add(panelArbitreActions, gbc_panelArbitreActions);
       
        
        // Panel bottom : Quit
        JPanel panelBottom = new JPanel();
        panelBottom.setBackground(new Color(44, 47, 51));
        panelBottom.setBorder(new EmptyBorder(10, 10, 0, 10));
        panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        contentPanel.add(panelBottom, BorderLayout.SOUTH);
        
        JButton btnQuit = new JButton("Quitter");
        btnQuit.setBackground(new Color(231, 76, 60));
        btnQuit.setForeground(new Color(255, 255, 255));
        btnQuit.setBorder(new RoundBtn(5));
        btnQuit.addActionListener(this.controleur);
        panelBottom.add(btnQuit);
    }
    
    public void SetArbitreList() {
    	
    }
}
