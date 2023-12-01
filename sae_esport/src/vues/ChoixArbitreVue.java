package vues;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Iterator;
import java.awt.FlowLayout;

import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.Font;

import javax.swing.SwingConstants;
import java.awt.Dimension;

import java.awt.Color;

import javax.swing.JSeparator;
import javax.swing.JTable;

import controleurs.ChoixArbitreControleur;

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
        
        // Panel middle : Action
        
        JPanel panelMiddle = new JPanel();
        panelMiddle.setLayout(new GridLayout(3, 0));
        contentPanel.add(panelMiddle);
        
        JPanel panelArbitreList = new JPanel();
        JScrollPane scrollpanelArbitreList = new JScrollPane(panelArbitreList);
        panelArbitreList.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelArbitreList.setBackground(new Color(44, 47, 51));
        GridLayout panelArbitreListLayout = new GridLayout(0, 4);
        panelArbitreListLayout.setHgap(10);
        panelArbitreListLayout.setVgap(20);
        panelArbitreList.setLayout(panelArbitreListLayout);
        
        for (int i = 1; i <= 79; i++) {
        	JButton button = new JButton("Arbitre n°" + i);
        	button.setBackground(new Color(29, 88, 129));
        	button.setForeground(new Color(255, 255, 255));
        	button.setBorder(new RoundBtn(5));
        	button.addActionListener(this.controleur);
        	panelArbitreList.add(button);
		}
        
        scrollpanelArbitreList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpanelArbitreList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollpanelArbitreList.setViewportBorder(new LineBorder(Color.RED));
        
        panelMiddle.add(scrollpanelArbitreList, BorderLayout.CENTER);
        
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
